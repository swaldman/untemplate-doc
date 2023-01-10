package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.*

val Untemplate_loopy_md = new untemplate.Untemplate[immutable.Map[String,Any],Nothing]:
  val UntemplateFunction                    : untemplate.Untemplate[immutable.Map[String,Any],Nothing] = this
  val UntemplateName                        : String = "loopy_md"
  val UntemplatePackage                     : String = "untemplatedoc"
  val UntemplateInputName                   : String = "input"
  val UntemplateInputTypeDeclared           : String = "immutable.Map[String,Any]"
  val UntemplateInputTypeCanonical          : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[immutable.Map[String,Any]] )
  val UntemplateInputDefaultArgument        : Option[immutable.Map[String,Any]] = Some(immutable.Map.empty)
  val UntemplateOutputMetadataTypeDeclared  : String = "Nothing"
  val UntemplateOutputMetadataTypeCanonical : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[Nothing] )
  val UntemplateHeaderNote                  : String = ""

  def apply(input : immutable.Map[String,Any] = immutable.Map.empty) : untemplate.Result[Nothing] =
    val writer             : StringWriter = new StringWriter(1900)
    var mbMetadata         : Option[Nothing] = None
    var outputTransformer  : Function1[untemplate.Result[Nothing],untemplate.Result[Nothing]] = identity

    val num = math.round(math.random * 10).toInt

    for (i <- 0 until num)
      val block0 = new Function0[String]:
        def apply() : String =
          "# Loopy\n"
      writer.write(block0())
      

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
      
    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )
    
  end apply
end Untemplate_loopy_md

def loopy_md(input : immutable.Map[String,Any] = immutable.Map.empty) : untemplate.Result[Nothing] = Untemplate_loopy_md( input )
