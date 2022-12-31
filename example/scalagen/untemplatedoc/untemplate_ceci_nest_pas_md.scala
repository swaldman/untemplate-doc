package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.*

// start generator-extras imports
// end generator-extras imports

// start author-defined imports
// end author-defined imports

private object Helper_ceci_nest_pas_md:
  private val BP0 = new Function2[immutable.Map[String,Any],mutable.Map[String,Any],String]:
    def apply( input : immutable.Map[String,Any], scratchpad : mutable.Map[String,Any]) : String =
      "# Ceci n'est pas...\n\nWell, this is just a regular markdown file, with no\nspecial untemplate constructs. But if we wish, we can treat\nit as an unemplate, and it will be immortalized as a scala\nfunction.\n\n"

  val BlockPrinters = Vector( BP0 )

end Helper_ceci_nest_pas_md

def ceci_nest_pas_md(input : immutable.Map[String,Any]) : String =
  import Helper_ceci_nest_pas_md.*

  val scratchpad : mutable.Map[String,Any] = mutable.Map.empty[String,Any]
  val writer = new StringWriter(131072) //XXX: Hardcoded initial capacity

    writer.write(BlockPrinters(0)( input, scratchpad ))
    
  writer.toString
  
end ceci_nest_pas_md

