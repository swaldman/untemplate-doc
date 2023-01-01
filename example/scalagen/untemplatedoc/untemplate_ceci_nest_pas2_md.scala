package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.*

val Function_ceci_nest_pas2_md = new Function1[immutable.Map[String,Any],untemplate.Result[Nothing]]:
  val UntemplateFunction : Function1[immutable.Map[String,Any],untemplate.Result[Nothing]] = this
  val UntemplateName               = "ceci_nest_pas2_md"
  val UntemplateInputName          = "input"
  val UntemplateInputType          = "immutable.Map[String,Any]"
  val UntemplateOutputMetadataType = "Nothing"

  def apply(input : immutable.Map[String,Any]) : untemplate.Result[Nothing] =
    val writer     : java.io.StringWriter          = new StringWriter(456)
    var mbMetadata : Option[Nothing] = None

      val block0 = new Function1[immutable.Map[String,Any],String]:
        def apply( input : immutable.Map[String,Any] ) : String =
          "# Ceci n'est pas... " + math.random +
          "\n\nWell, this is _almost_ just a regular markdown file, with no\nspecial untemplate constructs. But if we wish, we can treat\nit as an unemplate, and it will be immortalized as a scala\nfunction.\n\n"
      writer.write(block0( input ))
      
    untemplate.Result( mbMetadata, writer.toString )
    
  end apply
end Function_ceci_nest_pas2_md

def ceci_nest_pas2_md(input : immutable.Map[String,Any]) : untemplate.Result[Nothing] = Function_ceci_nest_pas2_md( input )
