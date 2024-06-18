import $meta._

import mill._
//import mill.define._
import mill.scalalib._
import mill.define.Source
import mill.util.Jvm
import mill.api.Result

import $ivy.`com.mchange::untemplate-mill:0.1.4-SNAPSHOT`
import untemplate.mill._

// NOTE: Documentation-visible untemplate version is defined in common.scala (package untemplatedoc)!

object untemplatedoc extends UntemplateModule {

  override def scalaVersion = "3.3.3"

  override def untemplateIndexNameFullyQualified : Option[String] = Some("untemplatedoc.Untemplates")

  override def untemplateSelectCustomizer: untemplate.Customizer.Selector = { key =>
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

  def installDir  = millSourcePath / os.up
  def exampleDir  = installDir / "example"
  def scalagenDir = exampleDir / "scalagen"

  def regenerate = T {
    val scalagenSrc = untemplateGenerateScala().path
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

  // abortive attempts to address terminal / mill untemplatedoc.console problems
  // import $ivy.`org.fusesource.jansi:jansi:2.4.0`
  // override def ivyDeps = T{ super.ivyDeps() ++ Agg(ivy"org.fusesource.jansi:jansi:2.4.0") }
}

