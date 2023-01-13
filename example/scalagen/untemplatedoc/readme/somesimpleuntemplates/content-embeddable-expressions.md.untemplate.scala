// DO NOT HAND EDIT -- Autogenerated from 'content-embeddable-expressions.md.untemplate' at 2023-01-13T18:59:56.411801Z
package untemplatedoc.readme.somesimpleuntemplates

import java.io.{Writer,StringWriter}
import scala.collection.*

import untemplatedoc.*
import java.nio.file.Files
import com.mchange.codegenutil.*



val Untemplate_content_embeddable_expressions_md = new untemplate.Untemplate[Int,SubsectionMeta]:
  val UntemplateFunction                    : untemplate.Untemplate[Int,SubsectionMeta] = this
  val UntemplateName                        : String = "content_embeddable_expressions_md"
  val UntemplatePackage                     : String = "untemplatedoc.readme.somesimpleuntemplates"
  val UntemplateInputName                   : String = "level"
  val UntemplateInputTypeDeclared           : String = "Int"
  val UntemplateInputTypeCanonical          : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[Int] )
  val UntemplateInputDefaultArgument        : Option[Int] = (None : Option[Int])
  val UntemplateOutputMetadataTypeDeclared  : String = "SubsectionMeta"
  val UntemplateOutputMetadataTypeCanonical : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[SubsectionMeta] )
  val UntemplateHeaderNote                  : String = ""

  val UntemplateAttributes : immutable.Map[String,Any] = immutable.Map.empty


  def apply(level : Int) : untemplate.Result[SubsectionMeta] =
    val writer             : StringWriter = new StringWriter(11450)
    val attrs              : immutable.Map[String,Any] = UntemplateAttributes
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = readme.subsection_content_transformer_md


    val title = "Embedded expressions"
    mbMetadata = Some( SubsectionMeta( level, title ) )


      val block0 = new Function0[String]:
        def apply() : String =
          "\nWe'd like, of course, for our (un)template library to do a bit more than just spit out unmodified\ntext files though. Let's modify our example just a bit:\n\n```markdown\n"
      writer.write(block0())

    writer.writeln(Files.readString(ceci2Src).trim)
      val block1 = new Function0[String]:
        def apply() : String =
          "```\n\nNow, the [generated scala](" + ( sgenFor("untemplatedoc/ceci-nest-pas2.md.untemplate") ) +
          ") _would_ transform the markdown, like this:\n\n```markdown\n"
      writer.write(block1())

    writer.writeln(untemplatedoc.ceci_nest_pas2_md(immutable.Map.empty).text)
      val block2 = new Function0[String]:
        def apply() : String =
          "```\n\nThe delimiter `<( expression )>` causes the `expression` to be evaluated into the text.\n\n"
      writer.write(block2())


    val block3 = new Function0[String]:
      def apply() : String =
        "This `README.md` is [generated by](" + ( readmeTopSrc ) +
        ") an untemplate! [[current subsection](" + ( readmeEmbeddedExpressionsSrc ) +
        ")]\nSo how did I slip that delimiter in? Any\nof the untemplate delimiters &mdash; there are only four! &mdash; can be escaped with a `\\` character\njust prior to them. The `\\` will be stripped, then the delimiter included in the text unmodified.\n\n"
    def escapingDelimiters() = block3()
    writer.writeln {
      box( None, Some("Note") )( escapingDelimiters() )
    }
    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )

  end apply
end Untemplate_content_embeddable_expressions_md

def content_embeddable_expressions_md(level : Int) : untemplate.Result[SubsectionMeta] = Untemplate_content_embeddable_expressions_md( level )
