package untemplatedoc

import scala.collection.*
import java.nio.file.{Files,Path}

object Main:

  def main(args : Array[String]) : Unit =
    println(s"""cwd: ${new java.io.File(".").getAbsolutePath}""")
    println("dest: " + Path.of(args(0)))
    Files.writeString(Path.of(args(0)), untemplatedoc.readme.content_main().text )



