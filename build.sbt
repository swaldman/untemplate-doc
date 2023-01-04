import java.nio.file.{Path, Files, StandardCopyOption}
import java.util.stream.Collectors
import scala.jdk.CollectionConverters._

ThisBuild / organization := "com.mchange"
ThisBuild / version      := "0.0.1-SNAPSHOT"

val TmpGenReadme = Path.of("/tmp/untemplate-doc/README.md") // hackish as F, but a way to cross the build / built boundary

val exampleDir = settingKey[File]("The directory into which examples will be lifted")
// val exampleUntemplate = settingKey[File]("The directory into which untemplate examples will be lifted")
val exampleScalagen = settingKey[File]("The directory into which generated scala will be lifted")
val readmeMarkdown = settingKey[File]("The file that should be the generated README.md")

val liftSources = taskKey[Unit]("Lifts untemplate and scala sources into an example directory")

lazy val root = project
  .in(file("."))
  .enablePlugins(UntemplateSbtPlugin)
  .settings(
    name := "untemplate-doc",
    scalaVersion := "3.2.1",
    resolvers += Resolver.mavenLocal,
    libraryDependencies += "com.mchange" %% "codegenutil" % "0.0.1-SNAPSHOT",
    libraryDependencies += "com.mchange" %% "untemplate" % "0.0.1-SNAPSHOT",
    // untemplateExtraImports := Seq("com.mchange.codegenutil.*","java.nio.file.Files","untemplatedoc.*"),
    exampleDir := file(".") / "example",
    // exampleUntemplate := exampleDir.value / "untemplate",
    exampleScalagen := exampleDir.value / "scalagen",
    readmeMarkdown := file(".") / "README.md",
    liftSources := {
      val log = streams.value.log
      val ensure = (Compile / compile).value
      val untemplates = untemplateSource.value
      val scalagens = untemplateScala.value
      //val untemplateDest = exampleUntemplate.value
      val scalagenDest = exampleScalagen.value
      // log.info("Lifting sources...")
      //untemplateDest.mkdirs()
      scalagenDest.mkdirs()
      //recursiveCopyBySuffix(untemplates,untemplateDest,".untemplate", log)
      recursiveCopyBySuffix(scalagens,scalagenDest,".scala", log)
    },
    (Compile / run) := {
      val ensure0 = (Compile / run).evaluated
      val ensure1 = liftSources.value
      val destFile = readmeMarkdown.value
      Files.copy(TmpGenReadme, destFile.toPath, StandardCopyOption.REPLACE_EXISTING)
    }
  )

def recursiveCopyBySuffix( srcBase : File, destBase : File, suffix : String, log : sbt.Logger ) : Unit = {
  val srcPath = srcBase.toPath
  val destPath = destBase.toPath

  def rebase(srcFile : Path) = destPath.resolve(srcPath.relativize(srcFile))

  val allSrcPaths = Files.walk(srcPath).collect(Collectors.toList[Path]).asScala //
  allSrcPaths.filter(p => Files.isDirectory(p)).map(rebase).foreach(p => Files.createDirectories(p))
  allSrcPaths.filter(p => Files.isRegularFile(p)).filter( _.toString.endsWith(suffix) ).foreach { srcFile =>
    val destFile = rebase(srcFile)
    // log.info( s"${srcFile} -> ${destFile}" )
    Files.copy(srcFile, destFile, StandardCopyOption.REPLACE_EXISTING)
  }
}



