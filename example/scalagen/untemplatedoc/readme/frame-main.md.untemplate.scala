// DO NOT HAND EDIT -- Autogenerated from 'frame-main.md.untemplate' at 2023-01-11T18:15:29.759259Z

package untemplatedoc.readme

import java.io.{Writer,StringWriter}
import scala.collection.*

import untemplatedoc.*


val Untemplate_frame_main_md = new untemplate.Untemplate[immutable.Map[String,Any],Nothing]:
  val UntemplateFunction                    : untemplate.Untemplate[immutable.Map[String,Any],Nothing] = this
  val UntemplateName                        : String = "frame_main_md"
  val UntemplatePackage                     : String = "untemplatedoc.readme"
  val UntemplateInputName                   : String = "input"
  val UntemplateInputTypeDeclared           : String = "immutable.Map[String,Any]"
  val UntemplateInputTypeCanonical          : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[immutable.Map[String,Any]] )
  val UntemplateInputDefaultArgument        : Option[immutable.Map[String,Any]] = Some(immutable.Map.empty)
  val UntemplateOutputMetadataTypeDeclared  : String = "Nothing"
  val UntemplateOutputMetadataTypeCanonical : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[Nothing] )
  val UntemplateHeaderNote                  : String = ""

  def apply(input : immutable.Map[String,Any] = immutable.Map.empty) : untemplate.Result[Nothing] =
    val writer             : StringWriter = new StringWriter(13920)
    var mbMetadata         : Option[Nothing] = None
    var outputTransformer  : Function1[untemplate.Result[Nothing],untemplate.Result[Nothing]] = identity


    val title = "Untemplate documentation"
    val tocTitle = "Table of contents"

    val level = 1
    val intro                    : untemplate.Result[SubsectionMeta] = introduction.content_main_md( level + 1 )
    val someSimpleUntemplates    : untemplate.Result[SubsectionMeta] = somesimpleuntemplates.content_main_md( level + 1 )
    val functionalTemplates      : untemplate.Result[SubsectionMeta] = functionaltemplates.content_main_md( level + 1 )
    val featureCreep             : untemplate.Result[SubsectionMeta] = featurecreep.content_main_md( level + 1 )
    val acknowledgments          : untemplate.Result[SubsectionMeta] = content_acknowledgments_md( level + 1 )

    val topSubsectionMeta =
        SubsectionMeta(
            level,
            title,
            SubsectionMeta(2, tocTitle) ::
            intro.mbMetadata.toList :::
            someSimpleUntemplates.mbMetadata.toList :::
            functionalTemplates.mbMetadata.toList :::
            featureCreep.mbMetadata.toList :::
            acknowledgments.mbMetadata.toList :::
            Nil
        )
    end topSubsectionMeta


      val block0 = new Function0[String]:
        def apply() : String =
          "\n# " + ( title ) +
          "\n\n_This project only documents the `untemplate` project. For the code, please see [swaldman/untemplate](https://github.com/swaldman/untemplate)._\n\n## " + ( tocTitle ) +
          "\n\n" + ( toc(topSubsectionMeta) ) +
          "\n\n" + ( intro ) +
          "\n\n" + ( someSimpleUntemplates ) +
          "\n\n" + ( functionalTemplates ) +
          "\n\n" + ( featureCreep ) +
          "\n\n" + ( acknowledgments ) +
          "\n\n\n\n\n\n\n\n\n"
      writer.write(block0())
      
    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )
    
  end apply
end Untemplate_frame_main_md

def frame_main_md(input : immutable.Map[String,Any] = immutable.Map.empty) : untemplate.Result[Nothing] = Untemplate_frame_main_md( input )
