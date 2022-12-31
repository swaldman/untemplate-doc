package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.*

import java.nio.file.{Path, Files}
import com.mchange.codegenutil.*

def README_md(input : immutable.Map[String,Any]) : String =
  val writer = new StringWriter(131072) //XXX: Hardcoded initial capacity


  val usrcDir      = Path.of("src/main/untemplate")
  val egenDir      = Path.of("example/untemplate")
  val sgenDir      = Path.of("example/scalagen")
  val ceciSrc      = usrcDir.resolve("untemplatedoc/ceci-nest-pas.md.untemplate")
  val ceciScala    = sgenDir.resolve("untemplatedoc/untemplate_ceci_nest_pas_md.scala")
  val ceci2Src     = usrcDir.resolve("untemplatedoc/ceci-nest-pas2.md.untemplate")
  val loopySrc     = usrcDir.resolve("untemplatedoc/loopy.md.untemplate")
  val loopy2badSrc = usrcDir.resolve("untemplatedoc/loopy2-bad.md.untemplate-off")
  val loopy2Src    = usrcDir.resolve("untemplatedoc/loopy2.md.untemplate")
  val thisFileSrc  = usrcDir.resolve("untemplatedoc/README.md.untemplate")
    val block0 = new Function1[immutable.Map[String,Any],String]:
      def apply( input : immutable.Map[String,Any] ) : String =
        "\n# Untemplate Documentation\n\n_This project documents the `untemplate` project. For the code, please see [swaldman/untemplate](https://github.com/swaldman/untemplate)._\n\n## Introduction\n\nEvery once in a while, I find I need to build a little website for something.\nI've become a fan of static site generators for that. I've worked with [hugo](https://gohugo.io/) and\n[hexo](https://hexo.io/) and (paradox)[https://developer.lightbend.com/docs/paradox/current/index.html],\nand they've all been great in their way.\n\nBut each time, I find myself spending a lot of time in their docs, figuring out each\nSSG's specific DSLs, their tricks for doing things, what variables are exposed in\ntemplates, etc.\n\nI found myself yearning for simplicity. Why can't I just specify my static sites in my language\nof choice? (For me, that's Scala these days.)\n\nStatic (and dynamic) site generation is in practice largely about templates. No one enjoys\nembedding tons of HTML or Markdown or CSS in programming-language string literals, even\nin modern languages that support multiline literals and interpolated strings.\n\nBut templates are a step along the slippery path to DSLs with clever, powerful features\nthat become the idiosyncracies and quirks I'm trying to escape. As much as possible, I\nwant my specification language to be straightforward Scala.\n\n_Untemplate_ is my attempt to create the thinnest possible template veneer over vanilla Scala.\nAn untemplate is just a text file that optionally includes any of precisely four special delimeters:\n\n1. `<(expression)>` breaks out of plain text and inserts the result into the text\n2. `()>` alone, at the beginning of a line, divides the file into a Scala code region, and a\ntext region. The region above is a Scala code region.\n3. `<()` alone, at the beginning of a line, is the inverse of the prior delimeter. It divides the\nfile into a text region and a Scala code region, with text in the region above, and code in the\nregion beneath.\n4. `()[]~>` is a special header delimiter. Like `()>`, it divides the file into a Scala code\nregion above and a text region below. However, import statements in the code region above become\ntop-level imports in the generated file.\n\n---\n\n**Mnemonic:** _For every construct, whatever an \"arrow\", `<` or `>`, is pointing at is a text region,\nwhatever a parenthesis is adjacent to is code._\n\n---\n\n### Functional templates\n\nEvery untemplate is a Scala function that returns a simple `String`.\n\nEvery text block within an untemplate can be a function. Ordinarily, text blocks just print themselves\nautomatically into the generated String. However, if you embed a name in the `()>` delimeter that begins\nthe block, like `(entry)>`, nothing is automatically printed into the String, but you will have a function\n`entry()` to work with in code blocks. `writer.write(entry)` will generate text into untemplate output.\n\nYou control the input type and name of the larger function that the full untemplate becomes by\nspecifying them in the header delimeter. Untemplate-generated functions always return a simple\n`String`, and accept a single parameter. By default, that parameter is `input: immutable.Map[String,Any]`,\nbut if you choose a header delimeter like `(users)[List[String]]~()>` then the input parameter will be\n`users : List[String]`. By default the name of the generated function is determined by the untemplate\nfile name. The file you are reading is is `[README.md.untemplate](" + thisFileSrc +
        ")`, and generates a\nfunction\n\n```scala\ndef README_md( input: immutable.Map[String,Any] ) : String = ???\n```\n\nNot yet implemented, but you should soon be able to override the generated function name in\nthe same way block function names are defined. `()[]~(userList)>` would become\n\n```scala\ndef userList( input: immutable.Map[String,Any] ) : String = ???\n```\n`(users)[List[String]]~(userList)>` would become\n\n```scala\ndef userList( input: immutable.Map[String,Any] ) : String = ???\n```\n\nThe easiest way to make sense of all this is by example.\n\n## A Tour of Untemplate\n\nLet's look at an untemplate so simple it seems not to be an untemplate at all.\n\n```markdown\n"
    writer.write(block0( input ))
    
  writer.writeln(Files.readString(ceciSrc).trim)
    val block1 = new Function1[immutable.Map[String,Any],String]:
      def apply( input : immutable.Map[String,Any] ) : String =
        "```\nIt's just a markdown file! But it's stored in an untemplate source directory as `ceci-nest-pas.md.untemplate`, so it gets\ncompiled to a simple scala function.\n\n```scala\n"
    writer.write(block1( input ))
    
  writer.writeln(Files.readString(ceciScala).trim)
    val block2 = new Function1[immutable.Map[String,Any],String]:
      def apply( input : immutable.Map[String,Any] ) : String =
        "```\n### Embedded expressions\n\nWe'd like, of course, for our (un)template library to do a bit more than just spit out unmodified\ntext files though. Let's modify our example just a bit:\n\n```markdown\n"
    writer.write(block2( input ))
    
  writer.writeln(Files.readString(ceci2Src).trim)
    val block3 = new Function1[immutable.Map[String,Any],String]:
      def apply( input : immutable.Map[String,Any] ) : String =
        "```\n\nNow, the [generated scala](" + sgenDir +
        "/untemplatedoc/untemplate_ceci_nest_pas2_md.scala) _would_ transform the markdown, like this:\n\n```markdown\n"
    writer.write(block3( input ))
    
  writer.writeln(untemplatedoc.ceci_nest_pas2_md(immutable.Map.empty))
    val block4 = new Function1[immutable.Map[String,Any],String]:
      def apply( input : immutable.Map[String,Any] ) : String =
        "```\n\nThe delimeter `<( expression )>` causes the `expression` to be evaluated into the text.\n\n---\n\n<i>Note: This `README.md` is [generated by](" + sgenDir +
        "/untemplatedoc/untemplate_README_md.scala) an untemplate!\nSo how did I slip that delimiter in? Any\nof the untemplate delimeters &mdash; there are only four! &mdash; can be escaped with a `\\` character\njust prior to them. The `\\` will be stripped, then the delimeter included in the text unmodified.</i>\n\n### Reapeatable, omitable, blocks\n\nOften you'd like to do more than just embed a few very simple expressions in some text.\nSo, you can break up your text into code blocks and text blocks. Let's do that, and repeat\na block of text in a loop.\n\n```markdown\n"
    writer.write(block4( input ))
    
  writer.writeln(Files.readString(loopySrc).trim)
    val block5 = new Function1[immutable.Map[String,Any],String]:
      def apply( input : immutable.Map[String,Any] ) : String =
        "```\n\nLet's get a look at what it produces:\n```markdown\n"
    writer.write(block5( input ))
    
  writer.writeln(untemplatedoc.loopy_md(immutable.Map.empty))
    val block6 = new Function1[immutable.Map[String,Any],String]:
      def apply( input : immutable.Map[String,Any] ) : String =
        "```\n\nAnd again!\n```markdown\n"
    writer.write(block6( input ))
    
  writer.writeln(untemplatedoc.loopy_md(immutable.Map.empty))
    val block7 = new Function1[immutable.Map[String,Any],String]:
      def apply( input : immutable.Map[String,Any] ) : String =
        "```\n([generated scala](" + sgenDir +
        "/untemplatedoc/untemplate_loopy_md.scala))\n\n### Named blocks as functions\n\nMaybe we want to use our expression-enriched text blocks in more than one place on our page.\nWe can name our blocks, and then they become functions. To do that, instead of beginning our\nblocks with `()>`, we embed a valid identifier in the parenthesis, like `(loopy)>`.\n\nHowever, that carries with it a few complications. If we just try that in our loopy markdown\nfile as it was, we'll get compilation errors.\n\nThe file...\n```scala\n"
    writer.write(block7( input ))
    
  writer.writeln(Files.readString(loopy2badSrc).trim)
    val block8 = new Function1[immutable.Map[String,Any],String]:
      def apply( input : immutable.Map[String,Any] ) : String =
        "```\nAnd the ickies...\n```\n[info] compiling 1 Scala source to /Users/swaldman/Dropbox/BaseFolders/development-why/gitproj/untemplate-doc/target/scala-3.2.1/classes ...\n[error] -- [E018] Syntax Error: /Users/swaldman/Dropbox/BaseFolders/development-why/gitproj/untemplate-doc/target/scala-3.2.1/src_managed/main/untemplate/untemplatedoc/untemplate_loopy2_bad_md.scala:11:24\n[error] 11 |  for (i <- 0 until num)\n[error]    |                        ^\n[error]    |                        expression expected but val found\n[error]    |\n[error]    | longer explanation available when compiling with `-explain`\n[error] -- [E006] Not Found Error: /Users/swaldman/Dropbox/BaseFolders/development-why/gitproj/untemplate-doc/target/scala-3.2.1/src_managed/main/untemplate/untemplatedoc/untemplate_loopy2_bad_md.scala:15:57\n[error] 15 |  def loopy( arg : immutable.Map[String,Any] = input ) = block0( arg )\n[error]    |                                                         ^^^^^^\n[error]    |                                                       Not found: block0\n[error]    |\n[error]    | longer explanation available when compiling with `-explain`\n[error] two errors found\n```\n\nBefore things worked, because when we're just printing an expression to output, we indent the call to write in\nthe generated code so that it falls inside of any loops, if expressions, or other language constructs that the\nprior code block has set up.\n\nIf we are going to want to treat the block as a reusable function, we do not wish to enclose its declaration\nin a very narrow scope. So, the declaration of named blocks is not indented, and named blocks do not print by default.\nIf you want to use a named block, define it before you get to branches in your code flow,\nthen call your named function, which returns a `String` you can write. Let's fix our _Loopy_.\n\n```scala\n"
    writer.write(block8( input ))
    
  writer.writeln(Files.readString(loopy2Src).trim)
    val block9 = new Function1[immutable.Map[String,Any],String]:
      def apply( input : immutable.Map[String,Any] ) : String =
        "```\n\nNot the loveliest file. But educational.\nHere is the output...\n\n```markdown\n"
    writer.write(block9( input ))
    
  writer.writeln(untemplatedoc.loopy2_md(immutable.Map.empty))
    val block10 = new Function1[immutable.Map[String,Any],String]:
      def apply( input : immutable.Map[String,Any] ) : String =
        "```\n([generated scala](" + sgenDir +
        "/untemplatedoc/untemplate_loopy2_md.scala))\n"
    writer.write(block10( input ))
    
  writer.toString
  
end README_md

