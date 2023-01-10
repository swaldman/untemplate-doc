// DO NOT HAND EDIT -- Autogenerated from 'content-metainformation.md.untemplate' at 2023-01-10T22:39:22.387974Z

package untemplatedoc.readme.functionaltemplates

import java.io.{Writer,StringWriter}
import scala.collection.*

import untemplatedoc.*
import java.nio.file.Files
import com.mchange.codegenutil.*




val Untemplate_content_metainformation_md = new untemplate.Untemplate[Int,SubsectionMeta]:
  val UntemplateFunction                    : untemplate.Untemplate[Int,SubsectionMeta] = this
  val UntemplateName                        : String = "content_metainformation_md"
  val UntemplatePackage                     : String = "untemplatedoc.readme.functionaltemplates"
  val UntemplateInputName                   : String = "level"
  val UntemplateInputTypeDeclared           : String = "Int"
  val UntemplateInputTypeCanonical          : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[Int] )
  val UntemplateInputDefaultArgument        : Option[Int] = (None : Option[Int])
  val UntemplateOutputMetadataTypeDeclared  : String = "SubsectionMeta"
  val UntemplateOutputMetadataTypeCanonical : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[SubsectionMeta] )
  val UntemplateHeaderNote                  : String = "This is a header note."

  def apply(level : Int) : untemplate.Result[SubsectionMeta] =
    val writer             : StringWriter = new StringWriter(18860)
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = readme.subsection_content_transformer_md

    val title = "Metainformation"
    mbMetadata = Some( SubsectionMeta( level, title ) )


      val block0 = new Function0[String]:
        def apply() : String =
          "\nWithin an untemplate, you have access to variables containing metainformation about the generated function.\n\nIt may be useful to use `UntemplateFunction` as a Map key, in order to decorate it with metadata.\nBeyond that, if this will be useful at all, it will probably be for debugging.\n\nFor the [untemplate you are reading](" + ( readmeMetainformationSrc ) +
          ") [[generated scala](" + ( sgenFor("untemplatedoc/readme/functionaltemplates/content-metainformation.md.untemplate") ) +
          ")]:\n\n```\nUntemplateFunction:                      " + (UntemplateFunction) +
          "\nUntemplateName:                         \"" + (UntemplateName) +
          "\"\nUntemplatePackage:                      \"" + (UntemplatePackage) +
          "\"\nUntemplateInputTypeDeclared:            \"" + (UntemplateInputTypeDeclared) +
          "\"\nUntemplateInputTypeCanonical:            " + (UntemplateInputTypeCanonical) +
          "\nUntemplateInputDefaultArgument:          " + (UntemplateInputDefaultArgument) +
          "\nUntemplateOutputMetadataTypeDeclared:   \"" + (UntemplateOutputMetadataTypeDeclared) +
          "\"\nUntemplateOutputMetadataTypeCanonical:   " + (UntemplateOutputMetadataTypeCanonical) +
          "\nUntemplateHeaderNote:                   \"" + (UntemplateHeaderNote) +
          "\"\n```\n\n`UntemplateFunction` is a reference to the `Untemplate` (which is a subtype of `Function1`) that implements your untemplate.\n\n\"Declared\" type values are just `String`, and names _may not be fully qualified_.\n\n\"Canonical\" types are, if possible, resolved to fully qualified type names that look through (non-opaque) aliases.\nHowever, for some types such resolution may not be possible, so these are `Option[String]`\n\n`UntemplateInputDefaultArgument` is an `Option[T]` where `T` is the input type and the value is `Some(defaultInputArg)`\nif one was defined or `None` otherwise.\n\n"
      writer.write(block0())
      
    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )
    
  end apply
end Untemplate_content_metainformation_md

def content_metainformation_md(level : Int) : untemplate.Result[SubsectionMeta] = Untemplate_content_metainformation_md( level )
