package untemplatedoc.readme.somesimpleuntemplates

import java.io.{Writer,StringWriter}
import scala.collection.*

import untemplatedoc.*
import java.nio.file.Files
import com.mchange.codegenutil.*

import java.nio.file.Files
import com.mchange.codegenutil.*
import untemplatedoc.*


val Untemplate_content_main_md = new untemplate.Untemplate[Int,SubsectionMeta]:
  val UntemplateFunction             = this
  val UntemplateName                 = "content_main_md"
  val UntemplateInputName            = "level"
  val UntemplateInputType            = "Int"
  val UntemplateInputDefaultArgument = (None : Option[String])
  val UntemplateOutputMetadataType   = "SubsectionMeta"

  def apply(level : Int) : untemplate.Result[SubsectionMeta] =
    val writer             : StringWriter = new StringWriter(9140)
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = readme.subsection_content_transformer_md



    val title = "Some simple untemplates"

    val untemplate.Result( eeMbMeta, eeText ) = content_embeddable_expressions_md( level + 1 )
    val untemplate.Result( roMbMeta, roBlocks ) = content_repeatable_omittable_md( level + 1 )

    mbMetadata = Some( SubsectionMeta( level, title, eeMbMeta.toList ::: roMbMeta.toList ::: Nil) )

    outputTransformer = readme.subsection_content_transformer_md


      val block0 = new Function0[String]:
        def apply() : String =
          "\nLet's look at an untemplate so simple it seems not to be an untemplate at all.\n\n```markdown\n"
      writer.write(block0())
      
    writer.writeln(Files.readString(ceciSrc).trim)
      val block1 = new Function0[String]:
        def apply() : String =
          "```\nIt's just a markdown file! But if it's stored in an untemplate source directory as `ceci-nest-pas.md.untemplate`, it gets\ncompiled to a simple scala function.\n\n```scala\n"
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
