// DO NOT HAND EDIT -- Autogenerated from 'content-side-scala.md.untemplate' at 2024-01-05T05:57:54.067374Z
package untemplatedoc.readme.featurecreep

import java.io.{Writer,StringWriter}
import scala.collection.{immutable,mutable}

import untemplatedoc.*
import java.nio.file.Files
import com.mchange.codegenutil.*



val Untemplate_content_side_scala_md = new untemplate.Untemplate[Int,SubsectionMeta]:
  val UntemplateFunction                    : untemplate.Untemplate[Int,SubsectionMeta] = this
  val UntemplateName                        : String = "content_side_scala_md"
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
    val writer             : StringWriter = new StringWriter(12780)
    val attrs              : immutable.Map[String,Any] = UntemplateAttributes
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = readme.subsection_content_transformer_md

    val title = "Side Scala Files"
    mbMetadata = Some( SubsectionMeta( level, title ) )


      val block0 = new Function0[String]:
        def apply() : String =
          "\nGenerated directory hierarchies of untemplates usually map to parallel\npackage locations.\n\nOften untemplates will want to share some ordinary Scala utility functions.\n\nYou can define these utilities in your main, non-untemplate Scala source code,\nbuilding a parallel package hierarchy there, or adding imports to your untemplates\n(directly or via a [Customizer](#customizers)) to access the utilities wherever you've defined them.\n\nHowever, for small utilities to be used only by untemplates within a particular\npackage, it's natural to just write a `whatever.scala` file next to your untemplates,\nand then access your utilities directly.\n\nThis is now supported (as of v0.1.2).\n\nThese \"side scala\" files will be placed in the same package as untemplates you defined in the same\ndirectory. Your untemplates will be able to access your utilities without imports\nor fully-qualified names.\n\n\n"
      writer.write(block0())

    writer.writeln {
      box( None, Some("Note") ){
    """All of this assumes that you've placed no explicit package declarations in your untemplate or
    side scala files, and that you haven't overridden the default package location with a [Customizer](#customizers))"""
      }
    }

    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )

  end apply
end Untemplate_content_side_scala_md

def content_side_scala_md(level : Int) : untemplate.Result[SubsectionMeta] = Untemplate_content_side_scala_md( level )
