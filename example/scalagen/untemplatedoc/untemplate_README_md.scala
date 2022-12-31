package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.*

// start generator-extras imports
// end generator-extras imports

// start author-defined imports
import java.nio.file.{Path, Files}
import com.mchange.codegenutil.*
// end author-defined imports

private object Helper_README_md:
  private val BP0 = new Function2[Unit,mutable.Map[String,Any],String]:
    def apply( input : Unit, scratchpad : mutable.Map[String,Any]) : String =
      "\n# Untemplate Docs\n\nThis project documents the `untemplate` project. For the code repository, please see [swaldman/untemplate](https://github.com/swaldman/untemplate)!\n\n---\n\nLet's look at a very simple untemplate, an untemplate so simple it seems not to be an untemplate at all!\n\n```markdown\n"
  private val BP1 = new Function2[Unit,mutable.Map[String,Any],String]:
    def apply( input : Unit, scratchpad : mutable.Map[String,Any]) : String =
      "```\nIt's just a markdown file! But it's stored in an untemplate source directory as `ceci-nest-pas.md.untemplate`, so it gets\ncompiled to a simple scala function.\n\n```scala\n"
  private val BP2 = new Function2[Unit,mutable.Map[String,Any],String]:
    def apply( input : Unit, scratchpad : mutable.Map[String,Any]) : String =
      "```\n"

  val BlockPrinters = Vector( BP0, BP1, BP2 )

end Helper_README_md

def README_md(input : Unit) : String =
  import Helper_README_md.*

  extension (s : mutable.Map[String,Any])
    def as[T](key: String): T = s(key).asInstanceOf[T]
    def check[T](key: String): Option[T] = s.get(key).map(_.asInstanceOf[T])

  val scratchpad : mutable.Map[String,Any] = mutable.Map.empty[String,Any]
  val writer = new StringWriter(102400) //XXX: Hardcoded initial capacity


  val usrcDir   = Path.of("src/main/untemplate")
  val egenDir   = Path.of("example/untemplate")
  val sgenDir   = Path.of("example/scalagen")
  val ceciSrc   = usrcDir.resolve("untemplatedoc/ceci-nest-pas.md.untemplate")
  val ceciScala = sgenDir.resolve("untemplatedoc/untemplate_ceci_nest_pas_md.scala")
    writer.write(BlockPrinters(0)( input, scratchpad ))
    
  writer.writeln(Files.readString(ceciSrc).trim)
    writer.write(BlockPrinters(1)( input, scratchpad ))
    
  writer.writeln(Files.readString(ceciScala).trim)
    writer.write(BlockPrinters(2)( input, scratchpad ))
    
  writer.toString
  
end README_md

