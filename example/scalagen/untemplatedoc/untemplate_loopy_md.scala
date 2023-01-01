package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.*

val Function_loopy_md = new Function1[immutable.Map[String,Any],String]:
  def apply(input : immutable.Map[String,Any]) =
    val ThisFunction : Function1[immutable.Map[String,Any],String] = this
    val writer = new StringWriter(131072) //XXX: Hardcoded initial capacity

    val num = math.round(math.random * 10).toInt

    for (i <- 0 until num)
      val block0 = new Function1[immutable.Map[String,Any],String]:
        def apply( input : immutable.Map[String,Any] ) : String =
          "# Loopy\n"
      writer.write(block0( input ))
      

    if (num >= 5)
      val block1 = new Function1[immutable.Map[String,Any],String]:
        def apply( input : immutable.Map[String,Any] ) : String =
          "\nAnd we're a winner! (num = " + num +
          ")\n"
      writer.write(block1( input ))
      
    else
      val block2 = new Function1[immutable.Map[String,Any],String]:
        def apply( input : immutable.Map[String,Any] ) : String =
          "\nIt sucks to be us. (num = " + num +
          ")\n"
      writer.write(block2( input ))
      
    writer.toString
    
  end apply
end Function_loopy_md

def loopy_md(input : immutable.Map[String,Any]) : String = Function_loopy_md( input )
