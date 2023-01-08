package untemplatedoc.readme.introduction

import java.io.{Writer,StringWriter}
import scala.collection.*

import untemplatedoc.*
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
    val writer             : StringWriter = new StringWriter(26700)
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = readme.subsection_content_transformer_md


    val title = "Introduction"
    val meta  =  SubsectionMeta( level, title )

    mbMetadata = Some( SubsectionMeta( level, title ) )

    outputTransformer = readme.subsection_content_transformer_md


      val block0 = new Function0[String]:
        def apply() : String =
          "\nEvery once in a while, I find I need to build a little website for something.\nI've become a fan of static site generators for that. I've worked with [hugo](https://gohugo.io/) and\n[hexo](https://hexo.io/) and [paradox](https://developer.lightbend.com/docs/paradox/current/index.html),\nand they've all been great in their way.\n\nBut each time, I find myself spending a lot of time in their docs, figuring out each\nSSG's specific DSLs, their tricks for doing things, what variables are exposed in\ntemplates, etc.\n\nI found myself yearning for simplicity. Why can't I just specify my static sites in my language\nof choice? (For me, Scala.)\n\nStatic (and dynamic) site generation is in practice largely about templates. No one enjoys\nembedding tons of HTML or Markdown or CSS in programming-language string literals, even\nin modern languages that support multiline literals and interpolated strings.\n\nBut templates are a step along the slippery path to DSLs with clever, powerful features\nthat become the idiosyncracies and quirks I'm trying to escape. As much as possible, I\nwant my specification language to be straightforward Scala.\n\n_Untemplate_ is my attempt to create the thinnest possible template veneer over vanilla Scala 3.\nAn untemplate is just a text file that optionally includes any of precisely four special delimeters:\n\n| Delimeter | Description |\n| --- | --- |\n| `<(expression)>` | Text-embedded Scala expression |\n| `()>` | Code / text boundary |\n| `<()` | Text / code boundary |\n| `()[]~()>` | Header delimeter |\n\nThese have the following effects:\n\n* `<(expression)>` breaks out of plain text and inserts the result into the text\n* `()>` alone, at the beginning of a line, divides the file into a Scala code region, and a\ntext region. The region above is a Scala code region.\n*  `<()` alone, at the beginning of a line, is the inverse of the prior delimeter. It divides the\nfile into a text region and a Scala code region, with text in the region above, and code in the\nregion beneath.\n*  `()[]~()>` is a special header delimiter. Like `()>`, it divides the file into a Scala code\nregion above and a text region below. However, import statements in the code region above become\ntop-level imports in the generated file.\n\n" + ( box(Some(":bulb:"), Some("Mnemonic"))( """For every construct, whatever an "arrow", `<` or `>`, points at is a text region. Whatever a parenthesis is adjacent to is code.""" ) ) +
          "\n\n"
      writer.write(block0())
      
    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )
    
  end apply
end Untemplate_content_main_md

def content_main_md(level : Int) : untemplate.Result[SubsectionMeta] = Untemplate_content_main_md( level )
