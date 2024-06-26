// DO NOT HAND EDIT -- Autogenerated from 'content-attributes.md.untemplate' at 2024-06-18T04:02:12.851483Z
package untemplatedoc.readme.featurecreep

import java.io.{Writer,StringWriter}
import scala.collection.{immutable,mutable}

import untemplatedoc.*
import java.nio.file.Files
import com.mchange.codegenutil.*



val Untemplate_content_attributes_md = new untemplate.Untemplate[Int,SubsectionMeta]:
  val UntemplateFunction                    : untemplate.Untemplate[Int,SubsectionMeta] = this
  val UntemplateName                        : String = "content_attributes_md"
  val UntemplatePackage                     : String = "untemplatedoc.readme.featurecreep"
  val UntemplateInputName                   : String = "level"
  val UntemplateInputTypeDeclared           : String = "Int"
  val UntemplateInputTypeCanonical          : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[Int] )
  val UntemplateInputDefaultArgument        : Option[Int] = (None : Option[Int])
  val UntemplateOutputMetadataTypeDeclared  : String = "SubsectionMeta"
  val UntemplateOutputMetadataTypeCanonical : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[SubsectionMeta] )
  val UntemplateHeaderNote                  : String = ""
  val UntemplateLastModified                : Option[Long] = Some(1676247606000L)
  val UntemplateSynthetic                   : Boolean = false


  val UntemplateAttributes = immutable.Map (
    "FavoriteColor" -> "Blue",
    "FavoriteDay"   -> "Tuesday",
  )


  def apply(level : Int) : untemplate.Result[SubsectionMeta] =
    val writer             : StringWriter = new StringWriter(20010)
    val attrs              : immutable.Map[String,Any] = UntemplateAttributes
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = readme.subsection_content_transformer_md


    val title = "Attributes"
    mbMetadata = Some( SubsectionMeta( level, title ) )


      val block0 = new Function0[String]:
        def apply() : String =
          "\nWhen you write an untemplate, you can associate attributes with it\nthat are accessible within the untemplate as `attrs`, but also published by your\n`Untemplate` as [metainformation](#metainformation).\n\nIn combination with [indexes](#indexes), this lets you filter\nin pretty arbitrary ways for untemplates that may be of interest.\n\nIn the header section of an untemplate, if you define code lines that\nbegin with '>', those lines will be generated into the body or constructor\nof the `Untemplate` subclass rather than within the function body.\nTo define attributes, you just construct an `immutable.Map` as\nconstructor text.\n\nFor example, [this untemplate's](" + ( readmeAttributesSrc ) +
          ") header includes\nthe following in its header section:\n\n```scala\n>\n> val UntemplateAttributes = immutable.Map (\n>   \"FavoriteColor\" -> \"Blue\",\n>   \"FavoriteDay\"   -> \"Tuesday\",\n> )\n>\n```\n\nFind where that appears in the [generated scala](" + ( readmeAttributesScala ) +
          ").\n\nNow if I write the expression `<( attrs(\"FavoriteColor\") )>`, it\ngenerates: " + ( attrs("FavoriteColor") ) +
          "\n\nIf I write the expression `<( attrs(\"FavoriteDay\") )>`, it\ngenerates: " + ( attrs("FavoriteDay") ) +
          "\n\nSuppose we included a \"tags\" key in untemplate attributes, whose value might\nbe a `Set[String]`. It would be easy to filter through our [index](#indexes)\nto find untemplates tagged with some value.\n\nBecause it's often convenient to resolve attributes in a case-independent way, untemplates also\noffer an `immutable.Map[LowerCased,Any]`, where `LowerCased` is an opaque type representing\na lowercased `String`. For example,\n\n```scala\nimport untemplate.LowerCased\n\nval pubDateKey = LowerCased(\"PubDate\")\nval mbPubDate = myUntemplate.UntemplateAttributesLowerCased.get(pubDateKey)\n```\n\n\n"
      writer.write(block0())

    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )

  end apply
end Untemplate_content_attributes_md

def content_attributes_md(level : Int) : untemplate.Result[SubsectionMeta] = Untemplate_content_attributes_md( level )
