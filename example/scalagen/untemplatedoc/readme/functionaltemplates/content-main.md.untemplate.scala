package untemplatedoc.readme.functionaltemplates

import java.io.{Writer,StringWriter}
import scala.collection.*

import java.nio.file.Files
import com.mchange.codegenutil.*
import untemplatedoc.*


val Function_content_main_md = new Function1[Int,untemplate.Result[SubsectionMeta]]:
  val UntemplateFunction             = this
  val UntemplateName                 = "content_main_md"
  val UntemplateInputName            = "level"
  val UntemplateInputType            = "Int"
  val UntemplateInputDefaultArgument = (None : Option[String])
  val UntemplateOutputMetadataType   = "SubsectionMeta"

  def apply(level : Int) : untemplate.Result[SubsectionMeta] =
    val writer             : StringWriter = new StringWriter(7290)
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = identity



    val title = "Functional untemplates"

    val subsectionFunctions = List (
        content_untemplates_r_functions_md,
        content_text_blocks_as_functions_md,
        content_naming_the_top_level_md,
        content_untemplates_packages_imports_md,
        content_metainformation_md
    )

    val subsectionResults = subsectionFunctions.map( fcn => fcn(level + 1) )

    mbMetadata = Some( SubsectionMeta( level, title, subsectionResults.flatMap( _.mbMetadata.toList ) ) )

    outputTransformer = readme.subsection_content_transformer_md


      val block0 = new Function0[String]:
        def apply() : String =
          "\n"
      writer.write(block0())
      
    subsectionResults.foreach { result =>
        writer.writeln( result.text )
        writer.writeln()
    }




    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )
    
  end apply
end Function_content_main_md

def content_main_md(level : Int) : untemplate.Result[SubsectionMeta] = Function_content_main_md( level )
