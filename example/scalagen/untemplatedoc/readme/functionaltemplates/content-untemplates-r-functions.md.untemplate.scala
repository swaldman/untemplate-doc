package untemplatedoc.readme.functionaltemplates

import java.io.{Writer,StringWriter}
import scala.collection.*

import untemplatedoc.*
import java.nio.file.Files
import com.mchange.codegenutil.*




val Untemplate_content_untemplates_r_functions_md = new untemplate.Untemplate[Int,SubsectionMeta]:
  val UntemplateFunction                    : untemplate.Untemplate[Int,SubsectionMeta] = this
  val UntemplateName                        : String = "content_untemplates_r_functions_md"
  val UntemplateInputName                   : String = "level"
  val UntemplateInputTypeDeclared           : String = "Int"
  val UntemplateInputTypeCanonical          : Option[String] = untemplate.recursiveCanonicalName[Int]
  val UntemplateInputDefaultArgument        : Option[Int] = (None : Option[Int])
  val UntemplateOutputMetadataTypeDeclared  : String = "SubsectionMeta"
  val UntemplateOutputMetadataTypeCanonical : Option[String] = untemplate.recursiveCanonicalName[Int]

  def apply(level : Int) : untemplate.Result[SubsectionMeta] =
    val writer             : StringWriter = new StringWriter(22390)
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = readme.subsection_content_transformer_md

    val title = "Untemplates are functions"
    mbMetadata = Some( SubsectionMeta( level, title ) )


      val block0 = new Function0[String]:
        def apply() : String =
          "\nEvery untemplate defines a Scala function. By default, from a file called `awesomeness.md.untemplate`, this\nfunction would look like...\n\n```scala\ndef awesomeness_md( input : immutable.Map[String,Any] = immutable.Map.empty ) : untemplate.Result[Nothing]\n```\n\nThe top-level function accepts a single, author-specifiable input. (`immutable.Map[String,Any]` is just a default.)\n\nIt returns the template output as a simple `String`, along with any metadata that untemplate chooses to provide.\n\nMore specifically, each template returns a\n\n```scala\npackage untemplate\n\nfinal case class Result[+A](mbMetadata : Option[A], text : String ):\n  override def toString() : String = text\n```\n\nNote that the `toString()` method is overridden, so you can embed `Result` directly an untemplate expressions.\nThe text will be printed, without metadata.\n\nUntemplate authors may (optionally!) specify the input name and type of the untemplate function, and output metadata type,\nin the header delimiter:\n\n```scala\n(sourceMarkdown : String)[immutable.Map[String,String]]~()>\n```\nThis header causes the generated untemplate function to require a `String` input, which the template author can work with in the\ntemplate as `sourceMarkdown`.\n\nThe function will return whatever text it generates, along with an `Option[immutable.Map[String,String]]`.\n\nBy default, this returned metadata will be `None`, but the template can provide `Some(metadata)` by overwriting the `var` called `mbMetadata`.\n"
      writer.write(block0())
      

    // let's write some boxes

    val block1 = new Function0[String]:
      def apply() : String =
        "Ick, it's a `var`. It's okay! `mbMetadata` is a strictly local variable, in the single-threaded context of a function\ncall. Your function will remain very functional as long as the input type and output metadata types that you specify\nare immutable.\n"
    def varIsOkay() = block1()

    val block2 = new Function0[String]:
      def apply() : String =
        "You can specify a default argument along with your custom untemplate input type, using the usual scala\nsyntax of `( myVar : MyType = DefaultVal )`\n"
    def defaultArgIsOkay() = block2()

    writer.writeln {
      box(Some(":blush:"), Some("It's okay!"))( varIsOkay() )
    }
    writer.writeln()
    writer.writeln {
      box(Some(":bulb:"), Some("Tip!"))( defaultArgIsOkay() )
    }
    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )
    
  end apply
end Untemplate_content_untemplates_r_functions_md

def content_untemplates_r_functions_md(level : Int) : untemplate.Result[SubsectionMeta] = Untemplate_content_untemplates_r_functions_md( level )
