// DO NOT HAND EDIT -- Autogenerated from 'embed-exercise.md.untemplate' at 2023-02-13T00:21:48.690217Z
package untemplatedoc.readme

import java.io.{Writer,StringWriter}
import scala.collection.*



val Untemplate_embed_exercise_md = new untemplate.Untemplate[Tuple3[Int,String,String],Nothing]:
  val UntemplateFunction                    : untemplate.Untemplate[Tuple3[Int,String,String],Nothing] = this
  val UntemplateName                        : String = "embed_exercise_md"
  val UntemplatePackage                     : String = "untemplatedoc.readme"
  val UntemplateInputName                   : String = "spec"
  val UntemplateInputTypeDeclared           : String = "Tuple3[Int,String,String]"
  val UntemplateInputTypeCanonical          : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[Tuple3[Int,String,String]] )
  val UntemplateInputDefaultArgument        : Option[Tuple3[Int,String,String]] = (None : Option[Tuple3[Int,String,String]])
  val UntemplateOutputMetadataTypeDeclared  : String = "Nothing"
  val UntemplateOutputMetadataTypeCanonical : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[Nothing] )
  val UntemplateHeaderNote                  : String = ""

  val UntemplateAttributes : immutable.Map[String,Any] = immutable.Map.empty


  def apply(spec : Tuple3[Int,String,String]) : untemplate.Result[Nothing] =
    val writer             : StringWriter = new StringWriter(1230)
    val attrs              : immutable.Map[String,Any] = UntemplateAttributes
    var mbMetadata         : Option[Nothing] = None
    var outputTransformer  : Function1[untemplate.Result[Nothing],untemplate.Result[Nothing]] = identity

    val (num, title, body) = spec

      val block0 = new Function0[String]:
        def apply() : String =
          "---\n**Exercise " + (num) +
          ": " + ( title ) +
          "**\n\n" + (body) +
          "\n---\n"
      writer.write(block0())

    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )

  end apply
end Untemplate_embed_exercise_md

def embed_exercise_md(spec : Tuple3[Int,String,String]) : untemplate.Result[Nothing] = Untemplate_embed_exercise_md( spec )
