package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.*




val Function_README_introduction_md = new Function1[Int,untemplate.Result[Subsection]]:
  val UntemplateFunction           = this
  val UntemplateName               = "README_introduction_md"
  val UntemplateInputName          = "level"
  val UntemplateInputType          = "Int"
  val UntemplateOutputMetadataType = "Subsection"

  def apply(level : Int) : untemplate.Result[Subsection] =
    val writer     : StringWriter = new StringWriter(5116)
    var mbMetadata : Option[Subsection] = None

    val title = "Introduction"

    mbMetadata = Some( Subsection( title, Nil ) )


      val block0 = new Function0[String]:
        def apply() : String =
          "\n" +  hashHeader(level)  +
          " " +  title  +
          "\n\nEvery once in a while, I find I need to build a little website for something.\nI've become a fan of static site generators for that. I've worked with [hugo](https://gohugo.io/) and\n[hexo](https://hexo.io/) and [paradox](https://developer.lightbend.com/docs/paradox/current/index.html),\nand they've all been great in their way.\n\nBut each time, I find myself spending a lot of time in their docs, figuring out each\nSSG's specific DSLs, their tricks for doing things, what variables are exposed in\ntemplates, etc.\n\nI found myself yearning for simplicity. Why can't I just specify my static sites in my language\nof choice? (For me, Scala.)\n\nStatic (and dynamic) site generation is in practice largely about templates. No one enjoys\nembedding tons of HTML or Markdown or CSS in programming-language string literals, even\nin modern languages that support multiline literals and interpolated strings.\n\nBut templates are a step along the slippery path to DSLs with clever, powerful features\nthat become the idiosyncracies and quirks I'm trying to escape. As much as possible, I\nwant my specification language to be straightforward Scala.\n\n_Untemplate_ is my attempt to create the thinnest possible template veneer over vanilla Scala.\nAn untemplate is just a text file that optionally includes any of precisely four special delimeters:\n\n| Delimeter | Description |\n| --- | --- |\n| `<(expression)>` | Text-embedded Scala expression |\n| `()>` | Code / text boundary |\n| `<()` | Text / code boundary |\n| `()[]~()>` | Header delimeter |\n\nThese have the following effects:\n\n* `<(expression)>` breaks out of plain text and inserts the result into the text\n* `()>` alone, at the beginning of a line, divides the file into a Scala code region, and a\ntext region. The region above is a Scala code region.\n*  `<()` alone, at the beginning of a line, is the inverse of the prior delimeter. It divides the\nfile into a text region and a Scala code region, with text in the region above, and code in the\nregion beneath.\n*  `()[]~()>` is a special header delimiter. Like `()>`, it divides the file into a Scala code\nregion above and a text region below. However, import statements in the code region above become\ntop-level imports in the generated file.\n\n> :bulb: **Mnemonic**<br/>\n> For every construct, whatever an \"arrow\", `<` or `>`, points at is a text region. Whatever a parenthesis is adjacent to is code.\n\n" +  BackToToc  +
          "\n\n"
      writer.write(block0())
      
    untemplate.Result( mbMetadata, writer.toString )
    
  end apply
end Function_README_introduction_md

def README_introduction_md(level : Int) : untemplate.Result[Subsection] = Function_README_introduction_md( level )
