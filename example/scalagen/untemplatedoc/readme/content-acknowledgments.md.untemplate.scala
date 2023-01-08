package untemplatedoc.readme

import java.io.{Writer,StringWriter}
import scala.collection.*

import untemplatedoc.*
import java.nio.file.Files
import com.mchange.codegenutil.*

import java.nio.file.Files
import com.mchange.codegenutil.*
import untemplatedoc.*


val Untemplate_content_acknowledgments_md = new untemplate.Untemplate[Int,SubsectionMeta]:
  val UntemplateFunction             = this
  val UntemplateName                 = "content_acknowledgments_md"
  val UntemplateInputName            = "level"
  val UntemplateInputType            = "Int"
  val UntemplateInputDefaultArgument = (None : Option[String])
  val UntemplateOutputMetadataType   = "SubsectionMeta"

  def apply(level : Int) : untemplate.Result[SubsectionMeta] =
    val writer             : StringWriter = new StringWriter(7270)
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = readme.subsection_content_transformer_md



    val title = "Acknowledgments"

    mbMetadata = Some( SubsectionMeta( level, title ) )

    outputTransformer = readme.subsection_content_transformer_md


      val block0 = new Function0[String]:
        def apply() : String =
          "\nThis project owes a debt to Java Server Pages (JSPs), and the special place they will always have in my heart.\n\nThe [mill](https://github.com/com-lihaoyi/mill) plugin I am currently working on owes a debt to\n[Twirl](https://github.com/playframework/twirl)'s [plugin](https://github.com/com-lihaoyi/mill/blob/8e2fef20886650882e49ba1aed0f719ddbf72365/contrib/playlib/src/mill/playlib/Twirl.scala),\nfrom which I am gently (and much less sophisticatedly) cribbing.\n"
      writer.write(block0())
      
    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )
    
  end apply
end Untemplate_content_acknowledgments_md

def content_acknowledgments_md(level : Int) : untemplate.Result[SubsectionMeta] = Untemplate_content_acknowledgments_md( level )
