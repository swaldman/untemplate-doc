package untemplatedoc.readme.functionaltemplates

import java.io.{Writer,StringWriter}
import scala.collection.*

import java.nio.file.Files
import com.mchange.codegenutil.*
import untemplatedoc.*


val Function_content_text_blocks_as_functions_md = new Function1[Int,untemplate.Result[SubsectionMeta]]:
  val UntemplateFunction             = this
  val UntemplateName                 = "content_text_blocks_as_functions_md"
  val UntemplateInputName            = "level"
  val UntemplateInputType            = "Int"
  val UntemplateInputDefaultArgument = (None : Option[String])
  val UntemplateOutputMetadataType   = "SubsectionMeta"

  def apply(level : Int) : untemplate.Result[SubsectionMeta] =
    val writer             : StringWriter = new StringWriter(33610)
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = identity



    val title = "Text blocks can be nested functions"

    mbMetadata = Some( SubsectionMeta( level, title ) )

    outputTransformer = readme.subsection_content_transformer_md


      val block0 = new Function0[String]:
        def apply() : String =
          "\nEvery text block within an untemplate can be a function.\n\n\nOrdinarily, text blocks just print themselves\nautomatically into the generated `String`. However, if you embed a name in the `()>` delimeter that begins\nthe block, like `(entry)>`, then nothing is automatically printed into the `String`. Instead you will have a function\n`entry()` to work with in code blocks.\n\nThe block function will return a simple `String`.\n\nUse `writer.write(entry())` to generate text into untemplate output.\n\nLet's try to redo our [\"Loopy\" template](#repeatable-omittable-blocks) making the text block that prints `# Loopy` into a function.\n\nInstead of beginning our blocks with `()>`, we embed a valid scala identifier into the parenthesis,\nlike `(loopy)>`.\n\nHowever, doing that carries with it some complications. If we just try that in our loopy markdown\nfile as it was, we'll get compilation errors.\n\nThe file...\n```scala\n"
      writer.write(block0())
      
    writer.writeln(Files.readString(loopy2badSrc).trim)
      val block1 = new Function0[String]:
        def apply() : String =
          "```\nAnd the ickies...\n```\n[info] compiling 7 Scala sources to /Users/swaldman/Dropbox/BaseFolders/development-why/gitproj/untemplate-doc/target/scala-3.2.1/classes ...\n[error] -- [E018] Syntax Error: /Users/swaldman/Dropbox/BaseFolders/development-why/gitproj/untemplate-doc/target/scala-3.2.1/src_managed/main/untemplate/untemplatedoc/untemplate_loopy2_bad_md.scala:19:26\n[error] 19 |    for (i <- 0 until num)\n[error]    |                          ^\n[error]    |                          expression expected but val found\n[error]    |\n[error]    | longer explanation available when compiling with `-explain`\n[error] -- [E006] Not Found Error: /Users/swaldman/Dropbox/BaseFolders/development-why/gitproj/untemplate-doc/target/scala-3.2.1/src_managed/main/untemplate/untemplatedoc/untemplate_loopy2_bad_md.scala:23:18\n[error] 23 |    def loopy() = block0()\n[error]    |                  ^^^^^^\n[error]    |                  Not found: block0\n[error]    |\n[error]    | longer explanation available when compiling with `-explain`\n[error] two errors found\n[error] (Compile / compileIncremental) Compilation failed\n```\n\nBefore things worked, because when we're just printing an expression to output, we indent the call to write in\nthe generated code so that it falls inside of any loops, if expressions, or other language constructs that the\nprior code block has set up.\n\nIf we are going to want to treat the block as a reusable function, then we do not wish to enclose its declaration\nin a very narrow scope. So, the function declaration provoked by named blocks is not indented, and named blocks\ndo not print by default.\n\nIf you want to use a named block, define it before you get to branches in your code flow,\nthen call your named function, which returns a `String` you can write. Let's fix our _Loopy_.\n\n```scala\n"
      writer.write(block1())
      
    writer.writeln(Files.readString(loopy2Src).trim)
      val block2 = new Function0[String]:
        def apply() : String =
          "```\n\nNot the loveliest file. But educational.\nHere is the output...\n\n```markdown\n"
      writer.write(block2())
      
    writer.writeln(untemplatedoc.loopy2_md(immutable.Map.empty).text)
      val block3 = new Function0[String]:
        def apply() : String =
          "```\n([generated scala](" + ( sgenFor("untemplatedoc/loopy2.md.untemplate") ) +
          "))\n"
      writer.write(block3())
      
    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )
    
  end apply
end Function_content_text_blocks_as_functions_md

def content_text_blocks_as_functions_md(level : Int) : untemplate.Result[SubsectionMeta] = Function_content_text_blocks_as_functions_md( level )
