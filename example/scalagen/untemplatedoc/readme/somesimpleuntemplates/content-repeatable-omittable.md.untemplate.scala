package untemplatedoc.readme.somesimpleuntemplates

import java.io.{Writer,StringWriter}
import scala.collection.*

import java.nio.file.Files
import com.mchange.codegenutil.*
import untemplatedoc.*


val Function_content_repeatable_omittable_md = new Function1[Int,untemplate.Result[SubsectionMeta]]:
  val UntemplateFunction             = this
  val UntemplateName                 = "content_repeatable_omittable_md"
  val UntemplateInputName            = "level"
  val UntemplateInputType            = "Int"
  val UntemplateInputDefaultArgument = (None : Option[String])
  val UntemplateOutputMetadataType   = "SubsectionMeta"

  def apply(level : Int) : untemplate.Result[SubsectionMeta] =
    val writer             : StringWriter = new StringWriter(8610)
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = identity



    val title = "Repeatable, omittable, blocks"

    mbMetadata = Some( SubsectionMeta( level, title ) )

    outputTransformer = readme.subsection_content_transformer_md


      val block0 = new Function0[String]:
        def apply() : String =
          "\nOften you'd like to do more than just embed a few very simple expressions in some text.\nSo, you can break up your text into code blocks and text blocks. Let's do that, and repeat\na block of text in a loop.\n\n```markdown\n"
      writer.write(block0())
      
    writer.writeln(Files.readString(loopySrc).trim)
      val block1 = new Function0[String]:
        def apply() : String =
          "```\n\nLet's get a look at what it produces:\n```markdown\n"
      writer.write(block1())
      
    writer.writeln(untemplatedoc.loopy_md(immutable.Map.empty).text)
      val block2 = new Function0[String]:
        def apply() : String =
          "```\n\nAnd again!\n```markdown\n"
      writer.write(block2())
      
    writer.writeln(untemplatedoc.loopy_md(immutable.Map.empty).text)
      val block3 = new Function0[String]:
        def apply() : String =
          "```\n([generated scala](" + ( sgenFor("untemplatedoc/loopy.md.untemplate") ) +
          "))\n\n"
      writer.write(block3())
      
    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )
    
  end apply
end Function_content_repeatable_omittable_md

def content_repeatable_omittable_md(level : Int) : untemplate.Result[SubsectionMeta] = Function_content_repeatable_omittable_md( level )
