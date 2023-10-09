// DO NOT HAND EDIT -- Autogenerated from 'some_overrides.md.untemplate' at 2023-10-09T22:09:09.893012Z
package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.{immutable,mutable}

import java.time.{Instant, ZoneId}
import java.time.format.DateTimeFormatter

val Untemplate_untemplateDoc = new untemplate.Untemplate[Instant,Nothing]:
  val UntemplateFunction                    : untemplate.Untemplate[Instant,Nothing] = this
  val UntemplateName                        : String = "untemplateDoc"
  val UntemplatePackage                     : String = "untemplatedoc"
  val UntemplateInputName                   : String = "pubDate"
  val UntemplateInputTypeDeclared           : String = "Instant"
  val UntemplateInputTypeCanonical          : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[Instant] )
  val UntemplateInputDefaultArgument        : Option[Instant] = (None : Option[Instant])
  val UntemplateOutputMetadataTypeDeclared  : String = "Nothing"
  val UntemplateOutputMetadataTypeCanonical : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[Nothing] )
  val UntemplateHeaderNote                  : String = ""

  val UntemplateAttributes : immutable.Map[String,Any] = immutable.Map.empty


  def apply(pubDate : Instant) : untemplate.Result[Nothing] =
    val writer             : StringWriter = new StringWriter(4400)
    val attrs              : immutable.Map[String,Any] = UntemplateAttributes
    var mbMetadata         : Option[Nothing] = None
    var outputTransformer  : Function1[untemplate.Result[Nothing],untemplate.Result[Nothing]] = identity


    // note that all non-import (and non-package) lines in the header get 
    // generated WITHIN the untemplate function, so pubDate is in scope!

    val formatted = DateTimeFormatter.RFC_1123_DATE_TIME.format( pubDate.atZone( ZoneId.systemDefault() ) )


      val block0 = new Function0[String]:
        def apply() : String =
          "\n# Birthday Post\n\nHappy Birthday to me!\n\n_I was published on " + (formatted) +
          "._\n\n"
      writer.write(block0())

    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )

  end apply
end Untemplate_untemplateDoc

def untemplateDoc(pubDate : Instant) : untemplate.Result[Nothing] = Untemplate_untemplateDoc( pubDate )