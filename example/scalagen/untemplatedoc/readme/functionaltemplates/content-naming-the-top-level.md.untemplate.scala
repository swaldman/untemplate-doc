package untemplatedoc.readme.functionaltemplates

import java.io.{Writer,StringWriter}
import scala.collection.*

import java.nio.file.Files
import com.mchange.codegenutil.*
import untemplatedoc.*


val Function_content_naming_the_top_level_md = new Function1[Int,untemplate.Result[SubsectionMeta]]:
  val UntemplateFunction             = this
  val UntemplateName                 = "content_naming_the_top_level_md"
  val UntemplateInputName            = "level"
  val UntemplateInputType            = "Int"
  val UntemplateInputDefaultArgument = (None : Option[String])
  val UntemplateOutputMetadataType   = "SubsectionMeta"

  def apply(level : Int) : untemplate.Result[SubsectionMeta] =
    val writer             : StringWriter = new StringWriter(23720)
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = identity



    val title = "Naming the top-level untemplate function"

    mbMetadata = Some( SubsectionMeta( level, title ) )

    outputTransformer = readme.subsection_content_transformer_md


      val block0 = new Function0[String]:
        def apply() : String =
          "\nThe `untemplate` app and file-system based tooling in the library will derive a default name for the\ntop-level generated function by transforming its filename. Untemplate are expected to have the suffix\n`.untemplate`. The file you are reading is is [`README.md.untemplate`](" + (readmeTopSrc) +
          "), and [generates a\nfunction](" + ( sgenFor("untemplatedoc/readme/content-main.untemplate") ) +
          ") like...\n\n```scala\ndef README_md( input: immutable.Map[String,Any] ) : untemplate.Result[Nothing] = ???\n```\n"
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
          "\nYou can override the generated function name in\nthe same way block function names are defined. Header `()[]~(untemplateDoc)>` would generate\n\n```scala\ndef untemplateDoc( input: immutable.Map[String,Any] ) : untemplate.Result[Nothing] = ???\n```\nHeader `(pubDate: Instant)[]~(untemplateDoc)>` would generate\n\n```scala\ndef untemplateDoc( pubDate: Instant ) : untemplate.Result[Nothing] = ???\n```\n\nHere's an example. Check out the [generated Scala](" + ( sgenFor("untemplatedoc/some_overrides.md.untemplate") ) +
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
end Function_content_naming_the_top_level_md

def content_naming_the_top_level_md(level : Int) : untemplate.Result[SubsectionMeta] = Function_content_naming_the_top_level_md( level )
