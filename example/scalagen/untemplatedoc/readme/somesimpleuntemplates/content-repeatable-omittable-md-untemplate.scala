// DO NOT HAND EDIT -- Autogenerated from 'content-repeatable-omittable.md.untemplate' at 2024-06-18T04:02:12.851846Z
package untemplatedoc.readme.somesimpleuntemplates

import java.io.{Writer,StringWriter}
import scala.collection.{immutable,mutable}

import untemplatedoc.*
import java.nio.file.Files
import com.mchange.codegenutil.*



val Untemplate_content_repeatable_omittable_md = new untemplate.Untemplate[Int,SubsectionMeta]:
  val UntemplateFunction                    : untemplate.Untemplate[Int,SubsectionMeta] = this
  val UntemplateName                        : String = "content_repeatable_omittable_md"
  val UntemplatePackage                     : String = "untemplatedoc.readme.somesimpleuntemplates"
  val UntemplateInputName                   : String = "level"
  val UntemplateInputTypeDeclared           : String = "Int"
  val UntemplateInputTypeCanonical          : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[Int] )
  val UntemplateInputDefaultArgument        : Option[Int] = (None : Option[Int])
  val UntemplateOutputMetadataTypeDeclared  : String = "SubsectionMeta"
  val UntemplateOutputMetadataTypeCanonical : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[SubsectionMeta] )
  val UntemplateHeaderNote                  : String = ""
  val UntemplateLastModified                : Option[Long] = Some(1673397546000L)
  val UntemplateSynthetic                   : Boolean = false

  val UntemplateAttributes : immutable.Map[String,Any] = immutable.Map.empty


  def apply(level : Int) : untemplate.Result[SubsectionMeta] =
    val writer             : StringWriter = new StringWriter(7130)
    val attrs              : immutable.Map[String,Any] = UntemplateAttributes
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = readme.subsection_content_transformer_md

    val title = "Repeatable, omittable, blocks"
    mbMetadata = Some( SubsectionMeta( level, title ) )


      val block0 = new Function0[String]:
        def apply() : String =
          "\nOften you'd like to do more than just embed a few very simple expressions in some text.\nSo, you can break up your text into code blocks and text blocks. Let's do that, and repeat\na block of text in a loop.\n\n```markdown\n"
      writer.write(block0())

    writer.writeln(Files.readString(loopySrc).trim)
      val block1 = new Function0[String]:
        def apply() : String =
          "```\n\nLet's get a look at what it produces:\n```markdown\n"
      writer.write(block1())

    writer.writeln(untemplatedoc.loopy_md(immutable.Map.empty).text)
      val block2 = new Function0[String]:
        def apply() : String =
          "```\n\nAnd again!\n```markdown\n"
      writer.write(block2())

    writer.writeln(untemplatedoc.loopy_md(immutable.Map.empty).text)
      val block3 = new Function0[String]:
        def apply() : String =
          "```\n([generated scala](" + ( sgenFor("untemplatedoc/loopy.md.untemplate") ) +
          "))\n\n"
      writer.write(block3())

    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )

  end apply
end Untemplate_content_repeatable_omittable_md

def content_repeatable_omittable_md(level : Int) : untemplate.Result[SubsectionMeta] = Untemplate_content_repeatable_omittable_md( level )
