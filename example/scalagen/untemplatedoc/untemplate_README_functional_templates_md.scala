package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.*

import java.nio.file.Files
import com.mchange.codegenutil.*


val Function_README_functional_templates_md = new Function1[Int,untemplate.Result[Subsection]]:
  val UntemplateFunction           = this
  val UntemplateName               = "README_functional_templates_md"
  val UntemplateInputName          = "level"
  val UntemplateInputType          = "Int"
  val UntemplateOutputMetadataType = "Subsection"

  def apply(level : Int) : untemplate.Result[Subsection] =
    val writer     : StringWriter = new StringWriter(18758)
    var mbMetadata : Option[Subsection] = None


    val title = "Functional templates"

    val subsections = Vector (
      Subsection("Untemplates are functions"),
      Subsection("Naming the top-level untemplate function"),
      Subsection("Untemplates, packages, and imports"),
      Subsection("Reflection"),

    )

    mbMetadata = Some( Subsection( title, subsections.toList ) )


      val block0 = new Function0[String]:
        def apply() : String =
          "\n" +  hashHeader(level)  +
          " " +  title  +
          "\n\n" +  hashHeader(level+1)  +
          " " +  subsections(0).title  +
          "\n\nEvery untemplate defines a Scala function. By default, from a file called `awesomeness.md.untemplate`, this\nfunction would look like...\n\n```scala\ndef awesomeness_md( input : immutable.Map[String,Any] ) : untemplate.Result[Nothing]\n```\n\nThe top-level function accepts a single, author-specifiable input. (`immutable.Map[String,Any]` is just a default.)\n\nIt returns the template output as a simple `String`, along with any metadata that untemplate chooses to provide.\n\nMore specifically, each template returns a\n\n```scala\npackage untemplate\n\ncase class Result[+A]( mbMetadata : Option[A], text : String)`.\n```\n\nUntemplate authors may (optionally!) specify the input name and type of the untemplate function, and output metadata type,\nin the header delimiter:\n\n```scala\n(sourceMarkdown : String)[immutable.Map[String,String]]~()>\n```\nThis header causes the generated untemplate function to require a `String` input, which the template author can work with in the\ntemplate as `sourceMarkdown`.\n\nThe function will return whatever text it generates, along with an `Option[immutable.Map[String,String]]`.\n\nBy default, this returned metadata will be `None`, but the template can provide `Some(metadata)` by overwriting the `var` called `mbMetadata`.\n\n> :point_right: Ick, a `var`! `mbMetadata` is a strictly local variable, in the single-threaded context of a function\n> call. Your function will remain very functional as long as the input type and output metadata types that you specify\n> are immutable.\n\n" +  hashHeader(level+1)  +
          " " +  subsections(1).title  +
          "\n\nEvery text block within an untemplate can be a function.\n\nOrdinarily, text blocks just print themselves\nautomatically into the generated `String`. However, if you embed a name in the `()>` delimeter that begins\nthe block, like `(entry)>`, then nothing is automatically printed into the `String`. Instead you will have a function\n`entry()` to work with in code blocks.\n\nThe block function will return a simple `String`.\n\nUse `writer.write(entry())` to generate text into untemplate output.\n\nLet's try to redo our [\"Loopy\" template](#repeatable-omittable-blocks) making the text block that prints `# Loopy` into a function.\n\nInstead of beginning our blocks with `()>`, we embed a valid scala identifier into the parenthesis,\nlike `(loopy)>`.\n\nHowever, doing that carries with it some complications. If we just try that in our loopy markdown\nfile as it was, we'll get compilation errors.\n\nThe file...\n```scala\n"
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
          "```\n([generated scala](" +  sgenFor("loopy2_md")  +
          "))\n\n" +  hashHeader(level+1)  +
          " " +  subsections(1).title  +
          "\n\nThe `untemplate` app and file-system based tooling in the library will derive a default name for the\ntop-level generated function by transforming its filename. Untemplate are expected to have the suffix\n`.untemplate`. The file you are reading is is [`README.md.untemplate`](" + thisFileSrc +
          "), and [generates a\nfunction](" +  sgenFor("README_md")  +
          ") like...\n\n```scala\ndef README_md( input: immutable.Map[String,Any] ) : untemplate.Result[Nothing] = ???\n```\n\n> :point_right: **Note** <br/>\n> Return type `untemplate.Result[Nothing]` looks intimidating, but it's just a\n> fancy wrapper for a `String`, as a field called `text`.\n> The `[Nothing]` part just means there cannot be metadata attached to this result.\n\nYou can override the generated function name in\nthe same way block function names are defined. Header `()[]~(untemplateDoc)>` would generate\n\n```scala\ndef untemplateDoc( input: immutable.Map[String,Any] ) : untemplate.Result[Nothing] = ???\n```\nHeader `(pubDate: Instant)[]~(untemplateDoc)>` would generate\n\n```scala\ndef untemplateDoc( pubDate: Instant ) : untemplate.Result[Nothing] = ???\n```\n\nHere's an example. Check out the [generated Scala](" +  sgenFor("untemplateDoc")  +
          ") code.\n\n```scala\n"
      writer.write(block3())
      
    writer.writeln(Files.readString(someOverridesSrc).trim)
      val block4 = new Function0[String]:
        def apply() : String =
          "```\nWhich generates...\n\n```markdown\n"
      writer.write(block4())
      
    writer.writeln(untemplatedoc.untemplateDoc( java.time.Instant.now() ).text)
      val block5 = new Function0[String]:
        def apply() : String =
          "```\n\n> :question: **Question** <br/>\n> What if you want to override the name of the top level function _and_ use\n> the first text block as a function? You can!\n>\n> The header `()[]~(mamaFunction.startText)>`\n> would override the outer function name with `mamaFunction`, and turn the first text block into\n> a function `startText()`.\n>\n> The header `()[]~(.startText)>` would turn the first text block into a function\n> called `startText()`, but leave the top-level function name alone.\n\n" +  hashHeader(level+1)  +
          " " +  subsections(2).title  +
          "\n\nTop-level untemplates are top-level functions, declared directly in a Scala package.\nThey are paired with implementations in the form of `Function0` objects, which are defined\nas `Function_` prepended to the untemplate function name.\n\nUntemplates are usually generated from a source directory, and the default behavior\nis for packages to be inferred by the old-school Java convention. The directory hierarchy\nbeneath specified source directory, to the untemplate source file, will be mapped to a package\nname (or dot-separated path of package names). Untemplate source\nfiles placed in the top directory belong to the unnamed \"default\" package.\n\nHowever, you can override this default by making an explicit package declaration in the header section of your\nuntemplate (that is, the section before a [header delimeter](#introduction)). If you wish all untemplates\nto be generated into a single flat directory, regardless of where or how deeply they were found beneath the source\ndirectory, you can set the option `flatten` to `true`.\n\nAny package declarations or import statements in a header section go at the top-level, outside of\nthe untemplate-generated function.\n\nAll other code in the header section gets placed inside the generated function.\n\n**This means that whatever input your header accepts is already in scope in the header section,\neven though its name and type may be declared at the end of the header section, inside the header\ndelimeter.**\n\nWhen generating untemplates, applications may specify a set of default imports that will be inserted into\nall generated untemplates. So, if a static site generator makes use of a common set of types and utilities,\nthese can be made automatically available to all templates.\n\n" +  hashHeader(level+1)  +
          " " +  subsections(3).title  +
          "\n\nWithin an untemplate, you have access to variables containing metainformation about the generated function:\n\n```\nUntemplateFunction:           `" + UntemplateFunction +
          "`\nUntemplateName:               `" + UntemplateName +
          "`.\nUntemplateInputType:          `" + UntemplateInputType +
          "`\nUntemplateOutputMetadataType: `" + UntemplateOutputMetadataType +
          "`\n```\n\nThe types are just Strings, and names _may not be fully qualified_.\n\n`UntemplateFunction` is a reference to the `Function1` object that implements your untemplate.\n\n\n\n\n"
      writer.write(block5())
      
    untemplate.Result( mbMetadata, writer.toString )
    
  end apply
end Function_README_functional_templates_md

def README_functional_templates_md(level : Int) : untemplate.Result[Subsection] = Function_README_functional_templates_md( level )
