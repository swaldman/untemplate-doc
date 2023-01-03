package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.*

import java.time.{Instant, ZoneId}
import java.time.format.DateTimeFormatter


val Function_untemplateDoc = new Function1[Instant,untemplate.Result[Nothing]]:
  val UntemplateFunction             = this
  val UntemplateName                 = "untemplateDoc"
  val UntemplateInputName            = "pubDate"
  val UntemplateInputType            = "Instant"
  val UntemplateInputDefaultArgument = (None : Option[String])
  val UntemplateOutputMetadataType   = "Nothing"

  def apply(pubDate : Instant) : untemplate.Result[Nothing] =
    val writer     : StringWriter = new StringWriter(880)
    var mbMetadata : Option[Nothing] = None


    // note that all non-import (and non-package) lines in the header get 
    // generated WITHIN the untemplate function, so pubDate is in scope!

    val formatted = DateTimeFormatter.RFC_1123_DATE_TIME.format( pubDate.atZone( ZoneId.systemDefault() ) )


      val block0 = new Function0[String]:
        def apply() : String =
          "\n# Birthday Post\n\nHappy Birthday to me!\n\n_I was published on " + formatted +
          "._\n\n"
      writer.write(block0())
      
    untemplate.Result( mbMetadata, writer.toString )
    
  end apply
end Function_untemplateDoc

def untemplateDoc(pubDate : Instant) : untemplate.Result[Nothing] = Function_untemplateDoc( pubDate )
