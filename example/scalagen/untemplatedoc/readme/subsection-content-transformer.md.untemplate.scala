package untemplatedoc.readme

import java.io.{Writer,StringWriter}
import scala.collection.*

import untemplatedoc.*


val Untemplate_subsection_content_transformer_md = new untemplate.Untemplate[untemplate.Result[SubsectionMeta],SubsectionMeta]:
  val UntemplateFunction                    : untemplate.Untemplate[untemplate.Result[SubsectionMeta],SubsectionMeta] = this
  val UntemplateName                        : String = "subsection_content_transformer_md"
  val UntemplateInputName                   : String = "result"
  val UntemplateInputTypeDeclared           : String = "untemplate.Result[SubsectionMeta]"
  val UntemplateInputTypeCanonical          : Option[String] = untemplate.recursiveCanonicalName[untemplate.Result[SubsectionMeta]]
  val UntemplateInputDefaultArgument        : Option[untemplate.Result[SubsectionMeta]] = (None : Option[untemplate.Result[SubsectionMeta]])
  val UntemplateOutputMetadataTypeDeclared  : String = "SubsectionMeta"
  val UntemplateOutputMetadataTypeCanonical : Option[String] = untemplate.recursiveCanonicalName[untemplate.Result[SubsectionMeta]]

  def apply(result : untemplate.Result[SubsectionMeta]) : untemplate.Result[SubsectionMeta] =
    val writer             : StringWriter = new StringWriter(3110)
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = identity


    val meta = result.mbMetadata.getOrElse( SubsectionMeta.Unknown )
    mbMetadata = result.mbMetadata


      val block0 = new Function0[String]:
        def apply() : String =
          "\n" + ( hashHeader(meta.level) ) +
          " " + ( meta.title ) +
          "\n\n" + ( result.text ) +
          "\n\n" + ( if meta.subsections.isEmpty then BackToToc else "" ) +
          "\n"
      writer.write(block0())
      
    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )
    
  end apply
end Untemplate_subsection_content_transformer_md

def subsection_content_transformer_md(result : untemplate.Result[SubsectionMeta]) : untemplate.Result[SubsectionMeta] = Untemplate_subsection_content_transformer_md( result )
