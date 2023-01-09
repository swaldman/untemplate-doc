package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.*

val Untemplate_loopy2_md = new untemplate.Untemplate[immutable.Map[String,Any],Nothing]:
  val UntemplateFunction                    : untemplate.Untemplate[immutable.Map[String,Any],Nothing] = this
  val UntemplateName                        : String = "loopy2_md"
  val UntemplateInputName                   : String = "input"
  val UntemplateInputTypeDeclared           : String = "immutable.Map[String,Any]"
  val UntemplateInputTypeCanonical          : Option[String] = untemplate.recursiveCanonicalName[immutable.Map[String,Any]]
  val UntemplateInputDefaultArgument        : Option[immutable.Map[String,Any]] = Some(immutable.Map.empty)
  val UntemplateOutputMetadataTypeDeclared  : String = "Nothing"
  val UntemplateOutputMetadataTypeCanonical : Option[String] = untemplate.recursiveCanonicalName[immutable.Map[String,Any]]

  def apply(input : immutable.Map[String,Any] = immutable.Map.empty) : untemplate.Result[Nothing] =
    val writer             : StringWriter = new StringWriter(6210)
    var mbMetadata         : Option[Nothing] = None
    var outputTransformer  : Function1[untemplate.Result[Nothing],untemplate.Result[Nothing]] = identity

    val num = math.round(math.random * 10).toInt

    // comments in code blocks are fine!
    // here is one way to turn text blocks into functions
    val block0 = new Function0[String]:
      def apply() : String =
        "# Loopy\n"
    def loopy() = block0()
    for (i <- 0 until num)
      writer.write(loopy()) // you have a java.io.Writer, called writer, to send output to

    // below is another, perhaps even simpler way to turn blocks into functions
    //
    // the indent of the if and else clauses must be lined up,
    // the statement that prints becomes indented from that level!
    def reportCard() : Unit =
      if (num >= 5)
        val block1 = new Function0[String]:
          def apply() : String =
            "\nAnd we're a winner! (num = " + (num) +
            ")\n"
        writer.write(block1())
        
      else
        val block2 = new Function0[String]:
          def apply() : String =
            "\nIt sucks to be us. (num = " + (num) +
            ")\n"
        writer.write(block2())
        
    reportCard()
    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )
    
  end apply
end Untemplate_loopy2_md

def loopy2_md(input : immutable.Map[String,Any] = immutable.Map.empty) : untemplate.Result[Nothing] = Untemplate_loopy2_md( input )
