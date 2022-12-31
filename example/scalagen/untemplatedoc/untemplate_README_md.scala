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
  private val BP0 = new Function2[immutable.Map[String,Any],mutable.Map[String,Any],String]:
    def apply( input : immutable.Map[String,Any], scratchpad : mutable.Map[String,Any]) : String =
      "\n# Untemplate Docs\n\nThis project documents the `untemplate` project. For the code repository, please see [swaldman/untemplate](https://github.com/swaldman/untemplate).\n\n---\n\nLet's look at an untemplate so simple it seems not to be an untemplate at all.\n\n```markdown\n"
  private val BP1 = new Function2[immutable.Map[String,Any],mutable.Map[String,Any],String]:
    def apply( input : immutable.Map[String,Any], scratchpad : mutable.Map[String,Any]) : String =
      "```\nIt's just a markdown file! But it's stored in an untemplate source directory as `ceci-nest-pas.md.untemplate`, so it gets\ncompiled to a simple scala function.\n\n```scala\n"
  private val BP2 = new Function2[immutable.Map[String,Any],mutable.Map[String,Any],String]:
    def apply( input : immutable.Map[String,Any], scratchpad : mutable.Map[String,Any]) : String =
      "```\n\nWe'd like, of course, for our (un)template library to do a bit more than just spit out unmodified\ntext files though. Let's modify our example just a bit:\n\n```markdown\n"
  private val BP3 = new Function2[immutable.Map[String,Any],mutable.Map[String,Any],String]:
    def apply( input : immutable.Map[String,Any], scratchpad : mutable.Map[String,Any]) : String =
      "```\n\nNow, the [generated scala](" + scratchpad("sgenDir") +
      "/untemplatedoc/untemplate_ceci_nest_pas2_md.scala) would _transform_ the markdown, like this:\n\n```markdown\n"
  private val BP4 = new Function2[immutable.Map[String,Any],mutable.Map[String,Any],String]:
    def apply( input : immutable.Map[String,Any], scratchpad : mutable.Map[String,Any]) : String =
      "```\n"

  val BlockPrinters = Vector( BP0, BP1, BP2, BP3, BP4 )

end Helper_README_md

def README_md(input : immutable.Map[String,Any]) : String =
  import Helper_README_md.*

  val scratchpad : mutable.Map[String,Any] = mutable.Map.empty[String,Any]
  val writer = new StringWriter(131072) //XXX: Hardcoded initial capacity


  val usrcDir   = Path.of("src/main/untemplate")
  val egenDir   = Path.of("example/untemplate")
  val sgenDir   = Path.of("example/scalagen")
  val ceciSrc   = usrcDir.resolve("untemplatedoc/ceci-nest-pas.md.untemplate")
  val ceciScala = sgenDir.resolve("untemplatedoc/untemplate_ceci_nest_pas_md.scala")
  val ceci2Src   = usrcDir.resolve("untemplatedoc/ceci-nest-pas2.md.untemplate")

  scratchpad += Tuple2("sgenDir",sgenDir)
    writer.write(BlockPrinters(0)( input, scratchpad ))
    
  writer.writeln(Files.readString(ceciSrc).trim)
    writer.write(BlockPrinters(1)( input, scratchpad ))
    
  writer.writeln(Files.readString(ceciScala).trim)
    writer.write(BlockPrinters(2)( input, scratchpad ))
    
  writer.writeln(Files.readString(ceci2Src).trim)
    writer.write(BlockPrinters(3)( input, scratchpad ))
    
  writer.writeln(untemplatedoc.ceci_nest_pas2_md(immutable.Map.empty))
    writer.write(BlockPrinters(4)( input, scratchpad ))
    
  writer.toString
  
end README_md

