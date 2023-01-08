import mill._
import mill.define._
import mill.scalalib._
import mill.define.Source
import mill.modules.Jvm
import mill.api.Result

// huge thanks to @lolgab onn the Scala discord!
import $file.buildCompilationSettings

import $ivy.`com.mchange::untemplate-mill:0.0.1-SNAPSHOT`
import untemplate.mill._

def veryclean() = T.command {
  // println( s"Very Clean! ${millSourcePath}")
  os.remove(millSourcePath / "README.md")
  os.remove.all(millSourcePath / "example")

  // we're writing to out during this, weird things happen
  // os.remove.all(millSourcePath / "out")
  println("Consider removing the out directory, this command can't cleanly do that.")

  os.mtime.set( millSourcePath / "build.sc", System.currentTimeMillis()) // synthesizing touch
  ()
}

object untemplatedocs extends UntemplateModule {
  override def scalaVersion = "3.2.1"

  def untemplateSelectCustomizer: untemplate.Customizer.Selector = { key =>
    var out = untemplate.Customizer.empty
    if (key.inferredPackage.startsWith("untemplatedoc.readme")) {
      key.inferredFunctionName match {
        case s"content_${whatever}" =>
          out = out.copy(extraImports = Seq("untemplatedoc.*", "java.nio.file.Files", "com.mchange.codegenutil.*"))
          if (whatever.endsWith("_md"))
            out = out.copy(mbDefaultOutputTransformer = Some("readme.subsection_content_transformer_md"))
        case _ => /* ignore */
      }
    }
    out
  }

  override def untemplateSource: Source = T.source {
    millSourcePath / "src" / "main" / "untemplate"
  }

  def installDir  = millSourcePath / os.up
  def exampleDir  = installDir / "example"
  def scalagenDir = exampleDir / "scalagen"

  override def run(args: String*) = T.command {
    val scalagenSrc = untemplateGenerateScala().path
    if (args.nonEmpty)
      throw new Exception("Command line arguments not supported. Please edit the task installDir to set the output. Unwanted args: " + args.mkString(", "))
    val installFile = (installDir / "README.md").toString
    println(s">>>> installFile: ${installFile}")
    os.makeDir.all(scalagenDir)
    os.copy.over(scalagenSrc, scalagenDir)

    // literally copied from JavaModule.run()
    // there must be a better way
    try Result.Success(
      Jvm.runSubprocess(
        finalMainClass(),
        runClasspath().map(_.path),
        forkArgs(),
        forkEnv(),
        Seq(installFile),
        workingDir = forkWorkingDir(),
        useCpPassingJar = runUseArgsFile()
      )
    )
    catch {
      case e: Exception =>
        Result.Failure("subprocess failed")
    }

    // super.run(Seq(installFile) :_*)
    // ()
  }
}

