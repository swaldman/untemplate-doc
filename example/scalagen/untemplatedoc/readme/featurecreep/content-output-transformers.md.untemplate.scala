// DO NOT HAND EDIT -- Autogenerated from 'content-output-transformers.md.untemplate' at 2023-01-13T00:43:24.485832Z
package untemplatedoc.readme.featurecreep

import java.io.{Writer,StringWriter}
import scala.collection.*

import untemplatedoc.*
import java.nio.file.Files
import com.mchange.codegenutil.*



val Untemplate_content_output_transformers_md = new untemplate.Untemplate[Int,SubsectionMeta]:
  val UntemplateFunction                    : untemplate.Untemplate[Int,SubsectionMeta] = this
  val UntemplateName                        : String = "content_output_transformers_md"
  val UntemplatePackage                     : String = "untemplatedoc.readme.featurecreep"
  val UntemplateInputName                   : String = "level"
  val UntemplateInputTypeDeclared           : String = "Int"
  val UntemplateInputTypeCanonical          : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[Int] )
  val UntemplateInputDefaultArgument        : Option[Int] = (None : Option[Int])
  val UntemplateOutputMetadataTypeDeclared  : String = "SubsectionMeta"
  val UntemplateOutputMetadataTypeCanonical : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[SubsectionMeta] )
  val UntemplateHeaderNote                  : String = ""

  val UntemplateAttributes : immutable.Map[String,Any] = immutable.Map.empty

  def apply(level : Int) : untemplate.Result[SubsectionMeta] =
    val writer             : StringWriter = new StringWriter(22450)
    val attrs              : immutable.Map[String,Any] = UntemplateAttributes
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = readme.subsection_content_transformer_md

    val title = "Output transformers"
    mbMetadata = Some( SubsectionMeta( level, title ) )


      val block0 = new Function0[String]:
        def apply() : String =
          "\nIn the header or any code section of an untemplate, you can define an `OutputTransformer`,\nlike this:\n\n```scala\noutputTransformer = myOutputTransformer\n```\n\nAs the name suggests, an output transformer will simply transform\nthe function output.\n\nIf you haven't defied a custom output metadata type,\nthen it must be a `Function1` that maps `untemplate.Result[Nothing] => untemplate.Result[Nothing]`.\n\nIf you have defined an output metadata type, say `HttpMetadata`, then it must be\na function `untemplate.Result[HttpMetadata] => untemplate.Result[HttpMetadata]`.\n\nBy default, every untemplate output travels through the identity transformer `identity`.\nIt's as if you had set:\n\n```scala\noutputTransformer = myOutputTranformer\n```\nBut you can set your own, more interesting, transformer.\n\nYou can set output transfers as above, \"by hand\", or you can use an [`untemplate.Customizer`](#customizers)\nto transform a whole class of untemplates. For example, you could have all untemplates\ngenerated from a file like `something.md.untemplate` pass through an output transformer that\nconverts Markdown to HTML.\n\nThe untemplate [you are now reading](" + ( readmeOutputTransformersSrc ) +
          ") is passed through\nan output transformer, which embeds the text in a markdown subsection,\nrendering at the appropriate level something like\n\n```markdown\n### Section Title\n\nLorem ipsum dolor sit amet, consectetur adipiscing elit,\nsed do eiusmod tempor incididunt ut labore et dolore magna\naliqua.\n```\n\nThat output transformer is itself defined by an untemplate,\n[untemplatedoc.readme.subsection_content_transformer_md](" + ( readmeSubsectionTransformerSrc ) +
          ").\n\nMost output transformers will not be untemplates! But an untemplate is just a\nway to define a function that returns an `untemplate.Result`, and\nan output transformer is just that kind of function.\n\nIt is as the untemplate for this section includes the line\n\n```scala\noutputTransformer = untemplatedoc.readme.subsection_content_transformer_md\n```\nand in earlier drafts, it did precisely that! But that got repetitive\nto include for every subsection, so we turn to [customizers](#customizers).\n"
      writer.write(block0())

    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )

  end apply
end Untemplate_content_output_transformers_md

def content_output_transformers_md(level : Int) : untemplate.Result[SubsectionMeta] = Untemplate_content_output_transformers_md( level )
