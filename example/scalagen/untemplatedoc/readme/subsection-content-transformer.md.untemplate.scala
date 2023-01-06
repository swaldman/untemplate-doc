package untemplatedoc.readme

import java.io.{Writer,StringWriter}
import scala.collection.*

import untemplatedoc.*


val Function_subsection_content_transformer_md = new Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]]:
  val UntemplateFunction             = this
  val UntemplateName                 = "subsection_content_transformer_md"
  val UntemplateInputName            = "result"
  val UntemplateInputType            = "untemplate.Result[SubsectionMeta]"
  val UntemplateInputDefaultArgument = (None : Option[String])
  val UntemplateOutputMetadataType   = "SubsectionMeta"

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
end Function_subsection_content_transformer_md

def subsection_content_transformer_md(result : untemplate.Result[SubsectionMeta]) : untemplate.Result[SubsectionMeta] = Function_subsection_content_transformer_md( result )
