package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.*

import java.nio.file.{Path, Files}
import com.mchange.codegenutil.*


val Function_README_md = new Function1[immutable.Map[String,Any],untemplate.Result[Nothing]]:
  val UntemplateFunction           = this
  val UntemplateName               = "README_md"
  val UntemplateInputName          = "input"
  val UntemplateInputType          = "immutable.Map[String,Any]"
  val UntemplateOutputMetadataType = "Nothing"

  def apply(input : immutable.Map[String,Any]) : untemplate.Result[Nothing] =
    val writer     : StringWriter = new StringWriter(23966)
    var mbMetadata : Option[Nothing] = None


    val usrcDir          = Path.of("src/main/untemplate")
    val egenDir          = Path.of("example/untemplate")
    val sgenDir          = Path.of("example/scalagen")
    val ceciSrc          = usrcDir.resolve("untemplatedoc/ceci-nest-pas.md.untemplate")
    val ceciScala        = sgenDir.resolve("untemplatedoc/untemplate_ceci_nest_pas_md.scala")
    val ceci2Src         = usrcDir.resolve("untemplatedoc/ceci-nest-pas2.md.untemplate")
    val loopySrc         = usrcDir.resolve("untemplatedoc/loopy.md.untemplate")
    val loopy2badSrc     = usrcDir.resolve("untemplatedoc/loopy2-bad.md.untemplate-off")
    val loopy2Src        = usrcDir.resolve("untemplatedoc/loopy2.md.untemplate")
    val someOverridesSrc = usrcDir.resolve("untemplatedoc/some_overrides.md.untemplate")
    val thisFileSrc      = usrcDir.resolve("untemplatedoc/README.md.untemplate")

    def sgenFor( underscoredName : String ) : Path =
      sgenDir.resolve( s"untemplatedoc/untemplate_${underscoredName}.scala" )
    end sgenFor


      val block0 = new Function0[String]:
        def apply() : String =
          "\n# Untemplate Documentation\n\n_This project only documents the `untemplate` project. For the code, please see [swaldman/untemplate](https://github.com/swaldman/untemplate)._\n\n## Introduction\n\nEvery once in a while, I find I need to build a little website for something.\nI've become a fan of static site generators for that. I've worked with [hugo](https://gohugo.io/) and\n[hexo](https://hexo.io/) and [paradox](https://developer.lightbend.com/docs/paradox/current/index.html),\nand they've all been great in their way.\n\nBut each time, I find myself spending a lot of time in their docs, figuring out each\nSSG's specific DSLs, their tricks for doing things, what variables are exposed in\ntemplates, etc.\n\nI found myself yearning for simplicity. Why can't I just specify my static sites in my language\nof choice? (For me, Scala.)\n\nStatic (and dynamic) site generation is in practice largely about templates. No one enjoys\nembedding tons of HTML or Markdown or CSS in programming-language string literals, even\nin modern languages that support multiline literals and interpolated strings.\n\nBut templates are a step along the slippery path to DSLs with clever, powerful features\nthat become the idiosyncracies and quirks I'm trying to escape. As much as possible, I\nwant my specification language to be straightforward Scala.\n\n_Untemplate_ is my attempt to create the thinnest possible template veneer over vanilla Scala.\nAn untemplate is just a text file that optionally includes any of precisely four special delimeters:\n\n| Delimeter | Description |\n| --- | --- |\n| `<(expression)>` | Text-embedded Scala expression |\n| `()>` | Code / text boundary |\n| `<()` | Text / code boundary |\n| `()[]~()>` | Header delimeter |\n\nThese have the following effects:\n\n* `<(expression)>` breaks out of plain text and inserts the result into the text\n* `()>` alone, at the beginning of a line, divides the file into a Scala code region, and a\ntext region. The region above is a Scala code region.\n*  `<()` alone, at the beginning of a line, is the inverse of the prior delimeter. It divides the\nfile into a text region and a Scala code region, with text in the region above, and code in the\nregion beneath.\n*  `()[]~()>` is a special header delimiter. Like `()>`, it divides the file into a Scala code\nregion above and a text region below. However, import statements in the code region above become\ntop-level imports in the generated file.\n\n> :bulb:\n> For every construct, whatever an \"arrow\", `<` or `>`, points at is a text region. Whatever a parenthesis is adjacent to is code.\n\n## Some simple untemplates\n\nLet's look at an untemplate so simple it seems not to be an untemplate at all.\n\n```markdown\n"
      writer.write(block0())
      
    writer.writeln(Files.readString(ceciSrc).trim)
      val block1 = new Function0[String]:
        def apply() : String =
          "```\nIt's just a markdown file! But if it's stored in an untemplate source directory as `ceci-nest-pas.md.untemplate`, it gets\ncompiled to a simple scala function.\n\n```scala\n"
      writer.write(block1())
      
    writer.writeln(Files.readString(ceciScala).trim)
      val block2 = new Function0[String]:
        def apply() : String =
          "```\n### Embedded expressions\n\nWe'd like, of course, for our (un)template library to do a bit more than just spit out unmodified\ntext files though. Let's modify our example just a bit:\n\n```markdown\n"
      writer.write(block2())
      
    writer.writeln(Files.readString(ceci2Src).trim)
      val block3 = new Function0[String]:
        def apply() : String =
          "```\n\nNow, the [generated scala](" +  sgenFor("ceci_nest_pas2_md")  +
          ") _would_ transform the markdown, like this:\n\n```markdown\n"
      writer.write(block3())
      
    writer.writeln(untemplatedoc.ceci_nest_pas2_md(immutable.Map.empty).text)
      val block4 = new Function0[String]:
        def apply() : String =
          "```\n\nThe delimeter `<( expression )>` causes the `expression` to be evaluated into the text.\n\n> :point_right: This `README.md` is [generated by](" +  sgenFor("README_md")  +
          ") an untemplate!\n> So how did I slip that delimiter in? Any\n> of the untemplate delimeters &mdash; there are only four! &mdash; can be escaped with a `\\` character\n> just prior to them. The `\\` will be stripped, then the delimeter included in the text unmodified.\n\n### Repeatable, omittable, blocks\n\nOften you'd like to do more than just embed a few very simple expressions in some text.\nSo, you can break up your text into code blocks and text blocks. Let's do that, and repeat\na block of text in a loop.\n\n```markdown\n"
      writer.write(block4())
      
    writer.writeln(Files.readString(loopySrc).trim)
      val block5 = new Function0[String]:
        def apply() : String =
          "```\n\nLet's get a look at what it produces:\n```markdown\n"
      writer.write(block5())
      
    writer.writeln(untemplatedoc.loopy_md(immutable.Map.empty).text)
      val block6 = new Function0[String]:
        def apply() : String =
          "```\n\nAnd again!\n```markdown\n"
      writer.write(block6())
      
    writer.writeln(untemplatedoc.loopy_md(immutable.Map.empty).text)
      val block7 = new Function0[String]:
        def apply() : String =
          "```\n([generated scala](" +  sgenFor("loopy_md.scala")  +
          "))\n\n## Functional templates\n\n### Untemplates are functions\n\nEvery untemplate defines a Scala function, and every text block defines a nested function.\n\nThe top-level function accepts a single, author-specifiable input. It returns\nthe template output as a simple `String`, along with any metadata that untemplate chooses to provide.\n\nMore specifically, each template returns a `untemplate.Result[+A]( mbMetadata : Option[A], text : String)`.\n\nUntemplate authors may (optionally!) specify the input name and type of the untemplate function, and output metadata type,\nin the header delimiter:\n\n```scala\n(sourceMarkdown : String)[immutable.Map[String,String]]~()>\n```\nThis header causes the generated untemplate function to require a `String` input, which the template author can work with in the\ntemplate as `sourceMarkdown`.\n\nThe function will return whatever text it generates, along with an `Option[immutable.Map[String,String]]`.\n\nBy default, this returned metadata will be `None`, but the template can provide `Some(metadata)` by overwriting the `var` called `mbMetadata`.\n\n> :point_right: Ick, a `var`! `mbMetadata` is a strictly local variable, in the single-threaded context of a function\n> call. Your function will remain very functional as long as the input type and output metadata types that you specify\n> are immutable.\n\n### Text blocks can be nested functions\n\nEvery text block within an untemplate can be a function.\n\nOrdinarily, text blocks just print themselves\nautomatically into the generated `String`. However, if you embed a name in the `()>` delimeter that begins\nthe block, like `(entry)>`, then nothing is automatically printed into the `String`. Instead you will have a function\n`entry()` to work with in code blocks.\n\nThe block function will return a simple `String`.\n\nUse `writer.write(entry())` to generate text into untemplate output.\n\nLet's try to redo our [\"Loopy\" template](#repeatable-omittable-blocks) making the text block that prints `# Loopy` into a function.\n\nInstead of beginning our blocks with `()>`, we embed a valid scala identifier into the parenthesis,\nlike `(loopy)>`.\n\nHowever, doing that carries with it some complications. If we just try that in our loopy markdown\nfile as it was, we'll get compilation errors.\n\nThe file...\n```scala\n"
      writer.write(block7())
      
    writer.writeln(Files.readString(loopy2badSrc).trim)
      val block8 = new Function0[String]:
        def apply() : String =
          "```\nAnd the ickies...\n```\n[info] compiling 7 Scala sources to /Users/swaldman/Dropbox/BaseFolders/development-why/gitproj/untemplate-doc/target/scala-3.2.1/classes ...\n[error] -- [E018] Syntax Error: /Users/swaldman/Dropbox/BaseFolders/development-why/gitproj/untemplate-doc/target/scala-3.2.1/src_managed/main/untemplate/untemplatedoc/untemplate_loopy2_bad_md.scala:19:26\n[error] 19 |    for (i <- 0 until num)\n[error]    |                          ^\n[error]    |                          expression expected but val found\n[error]    |\n[error]    | longer explanation available when compiling with `-explain`\n[error] -- [E006] Not Found Error: /Users/swaldman/Dropbox/BaseFolders/development-why/gitproj/untemplate-doc/target/scala-3.2.1/src_managed/main/untemplate/untemplatedoc/untemplate_loopy2_bad_md.scala:23:18\n[error] 23 |    def loopy() = block0()\n[error]    |                  ^^^^^^\n[error]    |                  Not found: block0\n[error]    |\n[error]    | longer explanation available when compiling with `-explain`\n[error] two errors found\n[error] (Compile / compileIncremental) Compilation failed\n```\n\nBefore things worked, because when we're just printing an expression to output, we indent the call to write in\nthe generated code so that it falls inside of any loops, if expressions, or other language constructs that the\nprior code block has set up.\n\nIf we are going to want to treat the block as a reusable function, then we do not wish to enclose its declaration\nin a very narrow scope. So, the function declaration provoked by named blocks is not indented, and named blocks\ndo not print by default.\n\nIf you want to use a named block, define it before you get to branches in your code flow,\nthen call your named function, which returns a `String` you can write. Let's fix our _Loopy_.\n\n```scala\n"
      writer.write(block8())
      
    writer.writeln(Files.readString(loopy2Src).trim)
      val block9 = new Function0[String]:
        def apply() : String =
          "```\n\nNot the loveliest file. But educational.\nHere is the output...\n\n```markdown\n"
      writer.write(block9())
      
    writer.writeln(untemplatedoc.loopy2_md(immutable.Map.empty).text)
      val block10 = new Function0[String]:
        def apply() : String =
          "```\n([generated scala](" +  sgenFor("loopy2_md")  +
          "))\n\n### Naming the top-level untemplate function\n\nThe `untemplate` app and file-system based tooling in the library will derive a default name for the\ntop-level generated function by transforming its filename. Untemplate are expected to have the suffix\n`.untemplate`. The file you are reading is is [`README.md.untemplate`](" + thisFileSrc +
          "), and [generates a\nfunction](" +  sgenFor("README_md")  +
          ") like...\n\n```scala\ndef README_md( input: immutable.Map[String,Any] ) : untemplate.Result[Nothing] = ???\n```\n\n> :point_right: Return type `untemplate.Result[Nothing]` looks intimidating, but it's just a\n> fancy wrapper for a `String`, as a field called `text`.\n> The `[Nothing]` part just means there cannot be metadata attached to this result.\n\nYou can override the generated function name in\nthe same way block function names are defined. Header `()[]~(untemplateDoc)>` would generate\n\n```scala\ndef untemplateDoc( input: immutable.Map[String,Any] ) : untemplate.Result[Nothing] = ???\n```\nHeader `(pubDate: Instant)[]~(untemplateDoc)>` would generate\n\n```scala\ndef untemplateDoc( pubDate: Instant ) : untemplate.Result[Nothing] = ???\n```\n\nHere's an example. Check out the [generated Scala](" +  sgenFor("untemplateDoc")  +
          ") code.\n\n```scala\n"
      writer.write(block10())
      
    writer.writeln(Files.readString(someOverridesSrc).trim)
      val block11 = new Function0[String]:
        def apply() : String =
          "```\nWhich generates...\n\n```markdown\n"
      writer.write(block11())
      
    writer.writeln(untemplatedoc.untemplateDoc( java.time.Instant.now() ).text)
      val block12 = new Function0[String]:
        def apply() : String =
          "```\n\n> :question: What if you want to override the name of the top level function _and_ use\n> the first text block as a function? You can!\n>\n> The header `()[]~(mamaFunction.startText)>`\n> would override the outer function name with `mamaFunction`, and turn the first text block into\n> a function `startText()`.\n>\n> The header `()[]~(.startText)>` would turn the first text block into a function\n> called `startText()`, but leave the top-level function name alone.\n\n### untemplates, packages, and imports.\n\nThe easiest way to make sense of all this is by example.\n\nMy name is `" + UntemplateName +
          "`.\n\nMy input type is `" + UntemplateInputType +
          "`.\n\n\n"
      writer.write(block12())
      
    untemplate.Result( mbMetadata, writer.toString )
    
  end apply
end Function_README_md

def README_md(input : immutable.Map[String,Any]) : untemplate.Result[Nothing] = Function_README_md( input )
