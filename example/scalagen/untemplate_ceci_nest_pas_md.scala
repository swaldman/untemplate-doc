
import java.io.{Writer,StringWriter}
import scala.collection.*

// start generator-extras imports
// end generator-extras imports

// start author-defined imports
// end author-defined imports

private object Helper_ceci_nest_pas_md:
  private val BP0 = new Function2[Unit,mutable.Map[String,Any],String]:
    def apply( input : Unit, scratchpad : mutable.Map[String,Any]) : String =
      "# Ceci n'est pas...\n\nWell, this is just a regular markdown file, with no\nspecial untemplate constructs. But if we wish, we can treat\nit as an unemplate, and it will be immortalized as a scala\nfunctiion.\n\n"

  val BlockPrinters = Vector( BP0 )

end Helper_ceci_nest_pas_md

def ceci_nest_pas_md(input : Unit) : String =
  import Helper_ceci_nest_pas_md.*

  extension (s : mutable.Map[String,Any])
    def as[T](key: String): T = s(key).asInstanceOf[T]
    def check[T](key: String): Option[T] = s.get(key).map(_.asInstanceOf[T])

  val scratchpad : mutable.Map[String,Any] = mutable.Map.empty[String,Any]
  val writer = new StringWriter(102400) //XXX: Hardcoded initial capacity

    writer.write(BlockPrinters(0)( input, scratchpad ))
    
  writer.toString
  
end ceci_nest_pas_md

