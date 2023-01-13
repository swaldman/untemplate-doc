// DO NOT HAND EDIT -- Autogenerated from 'content-naming-the-top-level.md.untemplate' at 2023-01-13T18:59:56.427646Z
package untemplatedoc.readme.functionaltemplates

import java.io.{Writer,StringWriter}
import scala.collection.*

import untemplatedoc.*
import java.nio.file.Files
import com.mchange.codegenutil.*



val Untemplate_content_naming_the_top_level_md = new untemplate.Untemplate[Int,SubsectionMeta]:
  val UntemplateFunction                    : untemplate.Untemplate[Int,SubsectionMeta] = this
  val UntemplateName                        : String = "content_naming_the_top_level_md"
  val UntemplatePackage                     : String = "untemplatedoc.readme.functionaltemplates"
  val UntemplateInputName                   : String = "level"
  val UntemplateInputTypeDeclared           : String = "Int"
  val UntemplateInputTypeCanonical          : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[Int] )
  val UntemplateInputDefaultArgument        : Option[Int] = (None : Option[Int])
  val UntemplateOutputMetadataTypeDeclared  : String = "SubsectionMeta"
  val UntemplateOutputMetadataTypeCanonical : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[SubsectionMeta] )
  val UntemplateHeaderNote                  : String = ""

  val UntemplateAttributes : immutable.Map[String,Any] = immutable.Map.empty


  def apply(level : Int) : untemplate.Result[SubsectionMeta] =
    val writer             : StringWriter = new StringWriter(22810)
    val attrs              : immutable.Map[String,Any] = UntemplateAttributes
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = readme.subsection_content_transformer_md

    val title = "Naming the top-level untemplate function"
    mbMetadata = Some( SubsectionMeta( level, title ) )


      val block0 = new Function0[String]:
        def apply() : String =
          "\nThe `untemplate` app and file-system based tooling in the library will derive a default name for the\ntop-level generated function by transforming its filename. Untemplate are expected to have the suffix\n`.untemplate`. The top-level file you are reading is [`frame-main.md.untemplate`](" + (readmeTopSrc) +
          "), and [generates a\nfunction](" + ( sgenFor("untemplatedoc/readme/frame-main.md.untemplate") ) +
          ") like...\n\n```scala\npackage untemplatedoc.readme\n\ndef frame_main_md( input: immutable.Map[String,Any] ) : untemplate.Result[Nothing] = ???\n```\n"
      writer.write(block0())


    val block1 = new Function0[String]:
      def apply() : String =
        "Return type `untemplate.Result[Nothing]` looks intimidating, but it's just a\nfancy wrapper for a `String`, as a field called `text`.\nThe `[Nothing]` part just means there cannot be metadata attached to this result.\n"
    def chillOnReturnType() = block1()
    writer.writeln {
      box( None, Some("Note") )( chillOnReturnType() )
    }
      val block2 = new Function0[String]:
        def apply() : String =
          "\nYou can override the generated function name in\nthe same way block function names are defined. Header `()[]~(untemplateDoc)>` would generate\n\n```scala\ndef untemplateDoc( input: immutable.Map[String,Any] ) : untemplate.Result[Nothing] = ???\n```\nHeader `(pubDate: Instant)[]~(untemplateDoc)>` would generate\n\n```scala\ndef untemplateDoc( pubDate: Instant ) : untemplate.Result[Nothing] = ???\n```\n\nHere's an example untemplate. Check out the [generated scala](" + ( sgenFor("untemplatedoc/some_overrides.md.untemplate") ) +
          ") code.\n\n```scala\n"
      writer.write(block2())

    writer.writeln(Files.readString(someOverridesSrc).trim)
      val block3 = new Function0[String]:
        def apply() : String =
          "```\nWhich generates...\n\n```markdown\n"
      writer.write(block3())

    writer.writeln(untemplatedoc.untemplateDoc( java.time.Instant.now() ).text)
      val block4 = new Function0[String]:
        def apply() : String =
          "```\n"
      writer.write(block4())


    val block5 = new Function0[String]:
      def apply() : String =
        "What if you want to override the name of the top level function _and_ use\nthe first text block as a function? You can!\n\nThe header `()[]~(mamaFunction.startText)>`\nwould override the outer function name with `mamaFunction`, and turn the first text block into\na function `startText()`.\n\nThe header `()[]~(.startText)>` would turn the first text block into a function\ncalled `startText()`, but leave the top-level function name alone.\n"
    def headerWithNamedBlock() = block5()
    writer.writeln {
      box( Some(":question:"), Some("Question") )( headerWithNamedBlock() )
    }


    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )

  end apply
end Untemplate_content_naming_the_top_level_md

def content_naming_the_top_level_md(level : Int) : untemplate.Result[SubsectionMeta] = Untemplate_content_naming_the_top_level_md( level )
