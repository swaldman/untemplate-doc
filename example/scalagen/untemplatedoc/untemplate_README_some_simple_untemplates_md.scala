package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.*

import java.nio.file.Files
import com.mchange.codegenutil.*


val Function_README_some_simple_untemplates_md = new Function1[Int,untemplate.Result[Subsection]]:
  val UntemplateFunction             = this
  val UntemplateName                 = "README_some_simple_untemplates_md"
  val UntemplateInputName            = "level"
  val UntemplateInputType            = "Int"
  val UntemplateInputDefaultArgument = (None : Option[String])
  val UntemplateOutputMetadataType   = "Subsection"

  def apply(level : Int) : untemplate.Result[Subsection] =
    val writer     : StringWriter = new StringWriter(4892)
    var mbMetadata : Option[Subsection] = None


    val title = "Some simple untemplates"

    val subsections = Vector (
      Subsection("Embedded expressions"),
      Subsection("Repeatable, omittable, blocks")
    )

    mbMetadata = Some( Subsection( title, subsections.toList ) )


      val block0 = new Function0[String]:
        def apply() : String =
          "\n" +  hashHeader(level)  +
          " " +  title  +
          "\n\nLet's look at an untemplate so simple it seems not to be an untemplate at all.\n\n```markdown\n"
      writer.write(block0())
      
    writer.writeln(Files.readString(ceciSrc).trim)
      val block1 = new Function0[String]:
        def apply() : String =
          "```\nIt's just a markdown file! But if it's stored in an untemplate source directory as `ceci-nest-pas.md.untemplate`, it gets\ncompiled to a simple scala function.\n\n```scala\n"
      writer.write(block1())
      
    writer.writeln(Files.readString(ceciScala).trim)
      val block2 = new Function0[String]:
        def apply() : String =
          "```\n" +  BackToToc  +
          "\n\n" +  hashHeader(level+1)  +
          " " +  subsections(0).title  +
          "\n\nWe'd like, of course, for our (un)template library to do a bit more than just spit out unmodified\ntext files though. Let's modify our example just a bit:\n\n```markdown\n"
      writer.write(block2())
      
    writer.writeln(Files.readString(ceci2Src).trim)
      val block3 = new Function0[String]:
        def apply() : String =
          "```\n\nNow, the [generated scala](" +  sgenFor("ceci_nest_pas2_md")  +
          ") _would_ transform the markdown, like this:\n\n```markdown\n"
      writer.write(block3())
      
    writer.writeln(untemplatedoc.ceci_nest_pas2_md(immutable.Map.empty).text)
      val block4 = new Function0[String]:
        def apply() : String =
          "```\n\nThe delimeter `<( expression )>` causes the `expression` to be evaluated into the text.\n\n"
      writer.write(block4())
      

    val block5 = new Function0[String]:
      def apply() : String =
        "This `README.md` is [generated by](" +  readmeTopSrc  +
        ") an untemplate! [[current subsection](" +  readmeSomeSimpleTemplatesSrc  +
        ")]\nSo how did I slip that delimiter in? Any\nof the untemplate delimeters &mdash; there are only four! &mdash; can be escaped with a `\\` character\njust prior to them. The `\\` will be stripped, then the delimeter included in the text unmodified.\n\n"
    def escapingDelimeters() = block5()
    writer.writeln {
      box( None, Some("Note") )( escapingDelimeters() )
    }
      val block6 = new Function0[String]:
        def apply() : String =
          "\n" +  BackToToc  +
          "\n\n" +  hashHeader(level+1)  +
          " " +  subsections(1).title  +
          "\n\nOften you'd like to do more than just embed a few very simple expressions in some text.\nSo, you can break up your text into code blocks and text blocks. Let's do that, and repeat\na block of text in a loop.\n\n```markdown\n"
      writer.write(block6())
      
    writer.writeln(Files.readString(loopySrc).trim)
      val block7 = new Function0[String]:
        def apply() : String =
          "```\n\nLet's get a look at what it produces:\n```markdown\n"
      writer.write(block7())
      
    writer.writeln(untemplatedoc.loopy_md(immutable.Map.empty).text)
      val block8 = new Function0[String]:
        def apply() : String =
          "```\n\nAnd again!\n```markdown\n"
      writer.write(block8())
      
    writer.writeln(untemplatedoc.loopy_md(immutable.Map.empty).text)
      val block9 = new Function0[String]:
        def apply() : String =
          "```\n([generated scala](" +  sgenFor("loopy_md")  +
          "))\n\n" +  BackToToc  +
          "\n\n\n\n\n\n\n"
      writer.write(block9())
      
    untemplate.Result( mbMetadata, writer.toString )
    
  end apply
end Function_README_some_simple_untemplates_md

def README_some_simple_untemplates_md(level : Int) : untemplate.Result[Subsection] = Function_README_some_simple_untemplates_md( level )
