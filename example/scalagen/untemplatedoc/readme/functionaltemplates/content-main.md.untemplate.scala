// DO NOT HAND EDIT -- Autogenerated from 'content-main.md.untemplate' at 2023-01-11T22:56:07.525617Z

package untemplatedoc.readme.functionaltemplates

import java.io.{Writer,StringWriter}
import scala.collection.*

import untemplatedoc.*
import java.nio.file.Files
import com.mchange.codegenutil.*




val Untemplate_content_main_md = new untemplate.Untemplate[Int,SubsectionMeta]:
  val UntemplateFunction                    : untemplate.Untemplate[Int,SubsectionMeta] = this
  val UntemplateName                        : String = "content_main_md"
  val UntemplatePackage                     : String = "untemplatedoc.readme.functionaltemplates"
  val UntemplateInputName                   : String = "level"
  val UntemplateInputTypeDeclared           : String = "Int"
  val UntemplateInputTypeCanonical          : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[Int] )
  val UntemplateInputDefaultArgument        : Option[Int] = (None : Option[Int])
  val UntemplateOutputMetadataTypeDeclared  : String = "SubsectionMeta"
  val UntemplateOutputMetadataTypeCanonical : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[SubsectionMeta] )
  val UntemplateHeaderNote                  : String = ""

  def apply(level : Int) : untemplate.Result[SubsectionMeta] =
    val writer             : StringWriter = new StringWriter(5810)
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = readme.subsection_content_transformer_md

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
end Untemplate_content_main_md

def content_main_md(level : Int) : untemplate.Result[SubsectionMeta] = Untemplate_content_main_md( level )
