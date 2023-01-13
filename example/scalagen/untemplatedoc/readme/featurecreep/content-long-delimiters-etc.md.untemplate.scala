// DO NOT HAND EDIT -- Autogenerated from 'content-long-delimiters-etc.md.untemplate' at 2023-01-13T18:59:56.475997Z
package untemplatedoc.readme.featurecreep

import java.io.{Writer,StringWriter}
import scala.collection.*

import untemplatedoc.*
import java.nio.file.Files
import com.mchange.codegenutil.*



val Untemplate_content_long_delimiters_etc_md = new untemplate.Untemplate[Int,SubsectionMeta]:
  val UntemplateFunction                    : untemplate.Untemplate[Int,SubsectionMeta] = this
  val UntemplateName                        : String = "content_long_delimiters_etc_md"
  val UntemplatePackage                     : String = "untemplatedoc.readme.featurecreep"
  val UntemplateInputName                   : String = "level"
  val UntemplateInputTypeDeclared           : String = "Int"
  val UntemplateInputTypeCanonical          : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[Int] )
  val UntemplateInputDefaultArgument        : Option[Int] = (None : Option[Int])
  val UntemplateOutputMetadataTypeDeclared  : String = "SubsectionMeta"
  val UntemplateOutputMetadataTypeCanonical : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[SubsectionMeta] )
  val UntemplateHeaderNote                  : String = ""

  val UntemplateAttributes : immutable.Map[String,Any] = immutable.Map.empty


  def apply(level : Int) : untemplate.Result[SubsectionMeta] =
    val writer             : StringWriter = new StringWriter(30880)
    val attrs              : immutable.Map[String,Any] = UntemplateAttributes
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = readme.subsection_content_transformer_md

    val title = "Long delimiters, header notes, and comments"
    mbMetadata = Some( SubsectionMeta( level, title ) )


      val block0 = new Function0[String]:
        def apply() : String =
          "\n**Long delimiters**\n\nRecall from our [introduction](#introduction) the four untemplate delimiters:\n\n| Delimiter | Description |\n| --- | --- |\n| `<(expression)>` | Text-embedded Scala expression |\n| `()>` | Code / text boundary |\n| `<()` | Text / code boundary |\n| `()[]~()>` | Header delimiter |\n\nThe three \"line delimiters\" &mdash; `()>`, `<()`, and `()[]~()>` &mdash\nmust be tucked against the left-hand margin. They are\nsmall and easy to miss while scanning a file, even though they\ndramatically switch the meaning of portions of the file from code to text.\n\nSometimes you may wish these boundaries were more obvious. So they can be!\nThe untemplate transpiler will let you expand `()>` and `<()` by placing\narbitrary numbers of `-` characters, and also the delimiter appropriate\n`>` or `<` between the parentheses and the arrow. So all of the following\nare equivalent to the untemplate engine:\n\n* `()>`\n* `()>>>>>>>>>>>>>>>>>>>>>>>>>>>`\n* `()-------------------------->`\n* `()------>------>------>----->`\n\nSimilarly, you can close your textblock, equivalently, with\n\n* `<()`\n* `<<<<<<<<<<<<<<<<<<<<<<<<<<<<()`\n* `<---------------------------()`\n* `<-------<-------<-------<---()`\n\nThe styles don't have to match. The transpiler interprets all of the first list\nas `()>`, and all of the second list as `<()`, regardless of what style you\nchoose.\n\nIn any of its styles, you can enter a name in the parentheses of a start-text\nblock to [convert the block following into a named function](#text-blocks-can-be-nested-functions).\n\nThe headers can also be elongated, with the `~` character. So, the following are\nall equivalent:\n\n* `()[]~()>`\n* `()[]~~~()>`\n* `()[]~~~~~~~~~~~~~~~~~~~~~~~~~~~~()>`\n\nAgain, the meaning of the header, including your\n[ability to optionally include](#naming-the-top-level-untemplate-function) an input\nname, type, and default argument in the first `()`, an output metadata type in\nthe `[]`, and a function name in the secon `()` remain unchanged.\n\n**Delimiter comments and header notes**\n\nIn general, nothing should be placed to the right of `()>` and `<()` delimiters (in their\nstandard or long variants), but if you wish, you may place a comment beginning\nwith a `#` character. So this is a fine [text-bloc-as-function](#text-blocks-can-be-nested-functions):\n\n```\n(regreet)>--->  # because we love to greet the user, a function we can reuse a lot\n       >>>>>>>>> Hello <( name )>!!! <<<<<<<<<\n<----------<()\n```\nHowever, for the header delimiter, untemplates support a \"header note\", at most\none per untemplate:\n\n```\n()[]~~~~()> This is a header note. # This is a comment\n```\n\nThe header note becomes [metainformation](#metainformation), available\nwithin your untemplate and published externally. It is also included in\n`Customizer.Key`, so you can set-up defaults for whole groups of untemplates\nbased on header notes if you wish. See [Customizers](#customizers) above.\n\n\n\n"
      writer.write(block0())

    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )

  end apply
end Untemplate_content_long_delimiters_etc_md

def content_long_delimiters_etc_md(level : Int) : untemplate.Result[SubsectionMeta] = Untemplate_content_long_delimiters_etc_md( level )
