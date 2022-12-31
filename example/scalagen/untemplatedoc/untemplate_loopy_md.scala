package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.*

private object Helper_loopy_md:
  private val BP0 = new Function2[immutable.Map[String,Any],mutable.Map[String,Any],String]:
    def apply( input : immutable.Map[String,Any], scratchpad : mutable.Map[String,Any]) : String =
      "# Loopy\n"
  private val BP1 = new Function2[immutable.Map[String,Any],mutable.Map[String,Any],String]:
    def apply( input : immutable.Map[String,Any], scratchpad : mutable.Map[String,Any]) : String =
      "\nAnd we're a winner!\n"
  private val BP2 = new Function2[immutable.Map[String,Any],mutable.Map[String,Any],String]:
    def apply( input : immutable.Map[String,Any], scratchpad : mutable.Map[String,Any]) : String =
      "\nIt sucks to be us.\n"

  val BlockPrinters = Vector( BP0, BP1, BP2 )

end Helper_loopy_md

def loopy_md(input : immutable.Map[String,Any]) : String =
  import Helper_loopy_md.*

  val scratchpad : mutable.Map[String,Any] = mutable.Map.empty[String,Any]
  val writer = new StringWriter(131072) //XXX: Hardcoded initial capacity

  val num = math.round(math.random * 10).toInt

  for (i <- 0 until num)
    writer.write(BlockPrinters(0)( input, scratchpad ))
    

  if (num >= 5)
    writer.write(BlockPrinters(1)( input, scratchpad ))
    
  else
    writer.write(BlockPrinters(2)( input, scratchpad ))
    
  writer.toString
  
end loopy_md

