package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.*

import java.nio.file.Files
import com.mchange.codegenutil.*


val Function_README_acknowledgments_md = new Function1[Int,untemplate.Result[Subsection]]:
  val UntemplateFunction             = this
  val UntemplateName                 = "README_acknowledgments_md"
  val UntemplateInputName            = "level"
  val UntemplateInputType            = "Int"
  val UntemplateInputDefaultArgument = (None : Option[String])
  val UntemplateOutputMetadataType   = "Subsection"

  def apply(level : Int) : untemplate.Result[Subsection] =
    val writer     : StringWriter = new StringWriter(1360)
    var mbMetadata : Option[Subsection] = None


    val title = "Acknowledgments"

    mbMetadata = Some( Subsection( title ) )


      val block0 = new Function0[String]:
        def apply() : String =
          "\n" +  hashHeader(level)  +
          " " +  title  +
          "\n\nThis project owes a debt to Java Server Pages (JSPs), and the special place they will always have in my heart.\n\nThe [mill](https://github.com/com-lihaoyi/mill) plugin I am currently working on owes a debt to\n[Twirl](https://github.com/playframework/twirl)'s [plugin](https://github.com/com-lihaoyi/mill/blob/8e2fef20886650882e49ba1aed0f719ddbf72365/contrib/playlib/src/mill/playlib/Twirl.scala),\nfrom which I am gently (and much less sophisticatedly) cribbing.\n\n" +  BackToToc  +
          "\n"
      writer.write(block0())
      
    untemplate.Result( mbMetadata, writer.toString )
    
  end apply
end Function_README_acknowledgments_md

def README_acknowledgments_md(level : Int) : untemplate.Result[Subsection] = Function_README_acknowledgments_md( level )
