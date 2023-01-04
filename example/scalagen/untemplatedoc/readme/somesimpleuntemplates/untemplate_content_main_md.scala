package untemplatedoc.readme.somesimpleuntemplates

import java.io.{Writer,StringWriter}
import scala.collection.*

import java.nio.file.Files
import com.mchange.codegenutil.*
import untemplatedoc.*


val Function_content_main_md = new Function1[Int,untemplate.Result[SubsectionMeta]]:
  val UntemplateFunction             = this
  val UntemplateName                 = "content_main_md"
  val UntemplateInputName            = "level"
  val UntemplateInputType            = "Int"
  val UntemplateInputDefaultArgument = (None : Option[String])
  val UntemplateOutputMetadataType   = "SubsectionMeta"

  def apply(level : Int) : untemplate.Result[SubsectionMeta] =
    val writer             : StringWriter = new StringWriter(8960)
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = identity



    val title = "Some simple untemplates"

    val ( eeMbMeta, eeText ) = content_embeddable_expressions_md( level + 1 ).asTuple
    val ( roMbMeta, roBlocks ) = content_repeatable_omittable_md( level + 1 ).asTuple

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
      
    outputTransformer( untemplate.Result.Simple( mbMetadata, writer.toString ) )
    
  end apply
end Function_content_main_md

def content_main_md(level : Int) : untemplate.Result[SubsectionMeta] = Function_content_main_md( level )
