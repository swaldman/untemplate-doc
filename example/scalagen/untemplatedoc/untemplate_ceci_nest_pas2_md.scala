package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.*

def ceci_nest_pas2_md(input : immutable.Map[String,Any]) : String =
  val writer = new StringWriter(131072) //XXX: Hardcoded initial capacity

    val block0 = new Function1[immutable.Map[String,Any],String]:
      def apply( input : immutable.Map[String,Any] ) : String =
        "# Ceci n'est pas... " + math.random +
        "\n\nWell, this is _almost_ just a regular markdown file, with no\nspecial untemplate constructs. But if we wish, we can treat\nit as an unemplate, and it will be immortalized as a scala\nfunction.\n\n"
    writer.write(block0( input ))
    
  writer.toString
  
end ceci_nest_pas2_md

