package untemplatedoc

import scala.collection.*
import java.nio.file.{Files,Path}

object Main:
  val IckyHardcoded = Path.of("/tmp/untemplate-doc/README.md") // should match build.sbt TmpGenReadme value!

  def main(args : Array[String]) : Unit =
    // println(s"""cwd: ${new java.io.File(".").getAbsolutePath}""")
    Files.createDirectories(IckyHardcoded.getParent())
    Files.writeString(IckyHardcoded, untemplatedoc.readme.content_main().text )



