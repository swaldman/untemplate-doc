package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.*

val Function_ceci_nest_pas_md = new Function1[immutable.Map[String,Any],String]:
  val UntemplateFunction : Function1[immutable.Map[String,Any],String] = this
  val UntemplateName      = "ceci_nest_pas_md"
  val UntemplateInputType = "immutable.Map[String,Any]"

  def apply(input : immutable.Map[String,Any]) =
    val writer = new StringWriter(131072) //XXX: Hardcoded initial capacity

      val block0 = new Function1[immutable.Map[String,Any],String]:
        def apply( input : immutable.Map[String,Any] ) : String =
          "# Ceci n'est pas...\n\nWell, this is just a regular markdown file, with no\nspecial untemplate constructs. But if we wish, we can treat\nit as an unemplate, and it will be immortalized as a scala\nfunction.\n\n"
      writer.write(block0( input ))
      
    writer.toString
    
  end apply
end Function_ceci_nest_pas_md

def ceci_nest_pas_md(input : immutable.Map[String,Any]) : String = Function_ceci_nest_pas_md( input )
