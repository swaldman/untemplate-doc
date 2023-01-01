package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.*

val Function_loopy2_md = new Function1[immutable.Map[String,Any],untemplate.Result[Nothing]]:
  val UntemplateFunction : Function1[immutable.Map[String,Any],untemplate.Result[Nothing]] = this
  val UntemplateName               = "loopy2_md"
  val UntemplateInputName          = "input"
  val UntemplateInputType          = "immutable.Map[String,Any]"
  val UntemplateOutputMetadataType = "Nothing"

  def apply(input : immutable.Map[String,Any]) : untemplate.Result[Nothing] =
    val writer = new StringWriter(131072) //XXX: Hardcoded initial capacity

    var mbMetadata : Option[Nothing] = None
    val num = math.round(math.random * 10).toInt

    // comments in code blocks are fine!
    // here is one way to turn text blocks into functions
    val block0 = new Function1[immutable.Map[String,Any],String]:
      def apply( input : immutable.Map[String,Any] ) : String =
        "# Loopy\n"
    def loopy( arg : immutable.Map[String,Any] = input ) = block0( arg )
    for (i <- 0 until num)
      writer.write(loopy()) // you have a java.io.Writer, called writer, to send output to

    // below is another, perhaps even simpler way to turn blocks into functions
    //
    // the indent of the if and else clauses must be lined up,
    // the statement that prints becomes indented from that level!
    def reportCard() : Unit =
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
        
    reportCard()
    untemplate.Result( mbMetadata, writer.toString )
    
  end apply
end Function_loopy2_md

def loopy2_md(input : immutable.Map[String,Any]) : untemplate.Result[Nothing] = Function_loopy2_md( input )
