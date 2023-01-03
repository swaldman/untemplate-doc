package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.*

val Function_loopy_md = new Function1[immutable.Map[String,Any],untemplate.Result[Nothing]]:
  val UntemplateFunction           = this
  val UntemplateName               = "loopy_md"
  val UntemplateInputName          = "input"
  val UntemplateInputType          = "immutable.Map[String,Any]"
  val UntemplateOutputMetadataType = "Nothing"

  def apply(input : immutable.Map[String,Any] = immutable.Map.empty) : untemplate.Result[Nothing] =
    val writer     : StringWriter = new StringWriter(380)
    var mbMetadata : Option[Nothing] = None

    val num = math.round(math.random * 10).toInt

    for (i <- 0 until num)
      val block0 = new Function0[String]:
        def apply() : String =
          "# Loopy\n"
      writer.write(block0())
      

    if (num >= 5)
      val block1 = new Function0[String]:
        def apply() : String =
          "\nAnd we're a winner! (num = " + num +
          ")\n"
      writer.write(block1())
      
    else
      val block2 = new Function0[String]:
        def apply() : String =
          "\nIt sucks to be us. (num = " + num +
          ")\n"
      writer.write(block2())
      
    untemplate.Result( mbMetadata, writer.toString )
    
  end apply
end Function_loopy_md

def loopy_md(input : immutable.Map[String,Any] = immutable.Map.empty) : untemplate.Result[Nothing] = Function_loopy_md( input )
