// DO NOT HAND EDIT -- Autogenerated from 'ceci-nest-pas2.md.untemplate' at 2023-01-13T00:05:48.209841Z
package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.*

val Untemplate_ceci_nest_pas2_md = new untemplate.Untemplate[immutable.Map[String,Any],Nothing]:
  val UntemplateFunction                    : untemplate.Untemplate[immutable.Map[String,Any],Nothing] = this
  val UntemplateName                        : String = "ceci_nest_pas2_md"
  val UntemplatePackage                     : String = "untemplatedoc"
  val UntemplateInputName                   : String = "input"
  val UntemplateInputTypeDeclared           : String = "immutable.Map[String,Any]"
  val UntemplateInputTypeCanonical          : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[immutable.Map[String,Any]] )
  val UntemplateInputDefaultArgument        : Option[immutable.Map[String,Any]] = Some(immutable.Map.empty)
  val UntemplateOutputMetadataTypeDeclared  : String = "Nothing"
  val UntemplateOutputMetadataTypeCanonical : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[Nothing] )
  val UntemplateHeaderNote                  : String = ""

  val UntemplateAttributes : immutable.Map[String,Any] = immutable.Map.empty

    def apply(input : immutable.Map[String,Any] = immutable.Map.empty) : untemplate.Result[Nothing] =
    val writer             : StringWriter = new StringWriter(2280)
    val attrs              : immutable.Map[String,Any] = UntemplateAttributes
    var mbMetadata         : Option[Nothing] = None
    var outputTransformer  : Function1[untemplate.Result[Nothing],untemplate.Result[Nothing]] = identity

      val block0 = new Function0[String]:
        def apply() : String =
          "# Ceci n'est pas... " + (math.random) +
          "\n\nWell, this is _almost_ just a regular markdown file, with no\nspecial untemplate constructs. But if we wish, we can treat\nit as an unemplate, and it will be immortalized as a scala\nfunction.\n\n"
      writer.write(block0())
      
    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )
    
  end apply
end Untemplate_ceci_nest_pas2_md

def ceci_nest_pas2_md(input : immutable.Map[String,Any] = immutable.Map.empty) : untemplate.Result[Nothing] = Untemplate_ceci_nest_pas2_md( input )
