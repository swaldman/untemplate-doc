package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.*

val Function_loopy_md = new Function1[immutable.Map[String,Any],untemplate.Result[Nothing]]:
  val UntemplateFunction : Function1[immutable.Map[String,Any],untemplate.Result[Nothing]] = this
  val UntemplateName               = "loopy_md"
  val UntemplateInputName          = "input"
  val UntemplateInputType          = "immutable.Map[String,Any]"
  val UntemplateOutputMetadataType = "Nothing"

  def apply(input : immutable.Map[String,Any]) : untemplate.Result[Nothing] =
    val writer     : java.io.StringWriter = new StringWriter(380)
    var mbMetadata : Option[Nothing] = None

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
      
    untemplate.Result( mbMetadata, writer.toString )
    
  end apply
end Function_loopy_md

def loopy_md(input : immutable.Map[String,Any]) : untemplate.Result[Nothing] = Function_loopy_md( input )
