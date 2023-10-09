// DO NOT HAND EDIT -- Autogenerated from 'content-main.md.untemplate' at 2023-10-09T22:09:09.893445Z
package untemplatedoc.readme.somesimpleuntemplates

import java.io.{Writer,StringWriter}
import scala.collection.{immutable,mutable}

import untemplatedoc.*
import java.nio.file.Files
import com.mchange.codegenutil.*



val Untemplate_content_main_md = new untemplate.Untemplate[Int,SubsectionMeta]:
  val UntemplateFunction                    : untemplate.Untemplate[Int,SubsectionMeta] = this
  val UntemplateName                        : String = "content_main_md"
  val UntemplatePackage                     : String = "untemplatedoc.readme.somesimpleuntemplates"
  val UntemplateInputName                   : String = "level"
  val UntemplateInputTypeDeclared           : String = "Int"
  val UntemplateInputTypeCanonical          : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[Int] )
  val UntemplateInputDefaultArgument        : Option[Int] = (None : Option[Int])
  val UntemplateOutputMetadataTypeDeclared  : String = "SubsectionMeta"
  val UntemplateOutputMetadataTypeCanonical : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[SubsectionMeta] )
  val UntemplateHeaderNote                  : String = ""

  val UntemplateAttributes : immutable.Map[String,Any] = immutable.Map.empty


  def apply(level : Int) : untemplate.Result[SubsectionMeta] =
    val writer             : StringWriter = new StringWriter(8420)
    val attrs              : immutable.Map[String,Any] = UntemplateAttributes
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = readme.subsection_content_transformer_md

    val title = "Some simple untemplates"

    val untemplate.Result( eeMbMeta, eeText ) = content_embeddable_expressions_md( level + 1 )
    val untemplate.Result( roMbMeta, roBlocks ) = content_repeatable_omittable_md( level + 1 )

    mbMetadata = Some( SubsectionMeta( level, title, eeMbMeta.toList ::: roMbMeta.toList ::: Nil) )


      val block0 = new Function0[String]:
        def apply() : String =
          "\nLet's look at an untemplate so simple it seems not to be an untemplate at all.\n\n```markdown\n"
      writer.write(block0())

    writer.writeln(Files.readString(ceciSrc).trim)
      val block1 = new Function0[String]:
        def apply() : String =
          "```\nIt's just a markdown file! But if it's stored in an untemplate source directory as `ceci-nest-pas.md.untemplate`, it gets\ncompiled to a simple scala function, `ceci_nest_pas_md()`.\n\n(See the little `def` declaration at the very end.)\n\n```scala\n"
      writer.write(block1())

    writer.writeln(Files.readString(ceciScala).trim)
      val block2 = new Function0[String]:
        def apply() : String =
          "```\n\n" + ( eeText ) +
          "\n\n" + ( roBlocks ) +
          "\n\n\n"
      writer.write(block2())

    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )

  end apply
end Untemplate_content_main_md

def content_main_md(level : Int) : untemplate.Result[SubsectionMeta] = Untemplate_content_main_md( level )