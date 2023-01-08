package untemplatedoc.readme.functionaltemplates

import java.io.{Writer,StringWriter}
import scala.collection.*

import java.nio.file.Files
import com.mchange.codegenutil.*
import untemplatedoc.*


val Untemplate_content_metainformation_md = new untemplate.Untemplate[Int,SubsectionMeta]:
  val UntemplateFunction             = this
  val UntemplateName                 = "content_metainformation_md"
  val UntemplateInputName            = "level"
  val UntemplateInputType            = "Int"
  val UntemplateInputDefaultArgument = (None : Option[String])
  val UntemplateOutputMetadataType   = "SubsectionMeta"

  def apply(level : Int) : untemplate.Result[SubsectionMeta] =
    val writer             : StringWriter = new StringWriter(12610)
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = identity



    val title = "Metainformation"

    mbMetadata = Some( SubsectionMeta( level, title ) )

    outputTransformer = readme.subsection_content_transformer_md


      val block0 = new Function0[String]:
        def apply() : String =
          "\nWithin an untemplate, you have access to variables containing metainformation about the generated function.\n\nIt may be useful to use `UntemplateFunction` as a Map key, in order to decorate it with metadata.\nBeyond that, if this will be useful at all, it will probably be for debugging.\n\nFor the [untemplate you are reading](" + ( readmeMetainformationSrc ) +
          "):\n\n```\nUntemplateFunction:              " + (UntemplateFunction) +
          "\nUntemplateName:                 \"" + (UntemplateName) +
          "\"\nUntemplateInputType:            \"" + (UntemplateInputType) +
          "\"\nUntemplateInputDefaultArgument:  " + (UntemplateInputDefaultArgument) +
          "\nUntemplateOutputMetadataType:   \"" + (UntemplateOutputMetadataType) +
          "\"\n```\n\n`UntemplateFunction` is a reference to the `Function1` object that implements your untemplate.\n\nThe type values are just `String`, and names _may not be fully qualified_.\n\n`UntemplateInputDefaultArgument` is an `Option[String]`, the default value as declared, if declared.\nIt is not the actual value of the default argument!\n\n"
      writer.write(block0())
      
    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )
    
  end apply
end Untemplate_content_metainformation_md

def content_metainformation_md(level : Int) : untemplate.Result[SubsectionMeta] = Untemplate_content_metainformation_md( level )
