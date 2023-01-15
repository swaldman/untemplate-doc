// DO NOT HAND EDIT -- Autogenerated from 'content-quickstart.md.untemplate' at 2023-01-15T06:47:32.030942Z
package untemplatedoc.readme

import java.io.{Writer,StringWriter}
import scala.collection.*

import untemplatedoc.*
import java.nio.file.Files
import com.mchange.codegenutil.*



val Untemplate_content_quickstart_md = new untemplate.Untemplate[Int,SubsectionMeta]:
  val UntemplateFunction                    : untemplate.Untemplate[Int,SubsectionMeta] = this
  val UntemplateName                        : String = "content_quickstart_md"
  val UntemplatePackage                     : String = "untemplatedoc.readme"
  val UntemplateInputName                   : String = "level"
  val UntemplateInputTypeDeclared           : String = "Int"
  val UntemplateInputTypeCanonical          : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[Int] )
  val UntemplateInputDefaultArgument        : Option[Int] = (None : Option[Int])
  val UntemplateOutputMetadataTypeDeclared  : String = "SubsectionMeta"
  val UntemplateOutputMetadataTypeCanonical : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[SubsectionMeta] )
  val UntemplateHeaderNote                  : String = ""

  val UntemplateAttributes : immutable.Map[String,Any] = immutable.Map.empty


  def apply(level : Int) : untemplate.Result[SubsectionMeta] =
    val writer             : StringWriter = new StringWriter(43820)
    val attrs              : immutable.Map[String,Any] = UntemplateAttributes
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = readme.subsection_content_transformer_md

    val title = "Quickstart"
    mbMetadata = Some( SubsectionMeta( level, title ) )

      val block0 = new Function0[String]:
        def apply() : String =
          "\n**Prerequisite**\n\nYou'll need something that can extract a [giter8](https://github.com/foundweekends/giter8) template\nfrom github to get started. We'll use `sbt new`, but `mill init` or `g8` should\nalso be fine.\n\n**Let's do it!**\n\nIn a place where you are happy to have a new project directory created, run:\n```zsh\n% sbt new swaldman/untemplate-seed.g8\n[info] welcome to sbt 1.8.2 (Oracle Corporation Java 17.0.5)\n[info] loading settings for project global-plugins from dependency-graph.sbt,gpg.sbt,metals.sbt ...\n[info] loading global plugins from /Users/swaldman/.sbt/1.0/plugins\n[info] set current project to new (in build file:/private/var/folders/by/35mx6ty94jng67n4kh2ps9tc0000gn/T/sbt_16d4daf/new/)\n```\n\nYou'll be prompted to \"fill in the blanks\" of the `giter8` template. You can do\nwant you want, but the defaults will be fine for now. Just hit return a bunch\nof times.\n\n```\nname [untemplateplay]:\nmodule [untemplateplay]:\npackage [example]:\nuntemplate_version [" + ( untemplateVersion ) +
          "]:\nmill_version [0.10.10]:\n\nTemplate applied in /Users/swaldman/tmp/./untemplateplay\n```\n\nCool. Now go into your new directory, and run `./mill untemplateplay`:\n\n```zsh\n% cd untemplateplay\n% ./mill untemplateplay\nCompiling /Users/swaldman/tmp/untemplateplay/build.sc\n[38/51] untemplateplay.compile\n[info] compiling 3 Scala sources to /Users/swaldman/tmp/untemplateplay/out/untemplateplay/compile.dest/classes ...\n[info] done compiling\n[51/51] untemplateplay.run\n```\n\nFollowing this (but no spoilers here!), you should see the output of your\nfirst untemplate! Hooray!\n\nCheck out the file `untemplateplay/src/example/core.scala`:\n```scala\npackage example\n\n@main def hello() = println( hello_txt() )\n```\n\nPretty simple! This is just an ordinary Scala 3 file. `hello_text()` is\na function defined by an untemplate, which you can check out in\n`untemplateplay/untemplate/example/hello.txt.untemplate`.\n\n"
      writer.write(block0())


    val block1 = new Function0[String]:
      def apply() : String =
        "The function `hello_text` actually accepts `name : String` as input.\nWe are just using a default argument.\nTry modifying `untemplateplay/src/example/core.scala` so the function call\nis like `hello_txt( \"<your name>\"\" )`, then rerun `./mill untemplateplay`\n"
    def exercise1() = block1()

    val spec1 = (1, "Supply untemplate input", exercise1())
    writer.write(embed_exercise_md(spec1).text)

      val block2 = new Function0[String]:
        def apply() : String =
          "\nShocking, right?\n\nBuilding your untemplate has caused two Scala source files to be generated\ninto the directory `out/untemplateplay/untemplateGenerateScala.dest/example/`.\nCheck those out!\n\n"
      writer.write(block2())


    val block3 = new Function0[String]:
      def apply() : String =
        "Using the information and examples in the documentation above, write your\nown untemplate! Modify the `@main` method to invoke it instead of (or\nin addition to) `hello_txt()`.\n\nAs long as you drop your untemplate in the package directory `example`\nunder `untemplateplay/untemplate`, your untemplate can call any scala code\nyou add to `core.scala`, or any other source file in `example`. (Of course,\nyou can also create Scala code in other packages, and access it from your untemplate\nwith import statements.)\n\nYour untemplates have seamless access to your Scala\ncode. To your Scala code, each untemplates is just ordinary functions.\n"
    def exercise2() = block3()

    val spec2 = (2, "Write your own!", exercise2())
    writer.write(embed_exercise_md(spec2).text)

      val block4 = new Function0[String]:
        def apply() : String =
          "\nBefore we go, check out the `build.sc` file that was generated for you.\nIn particular, note this section:\n\n```scala\n  override def untemplateSelectCustomizer: untemplate.Customizer.Selector = { key =>\n    var out = untemplate.Customizer.empty\n\n    // to customize, examine key and modify the customer\n    // with out = out.copy=...\n    //\n    // e.g. out = out.copy(extraImports=Seq(\"untemplateplay.*\"))\n\n    out\n  }\n```\n\nWhen you have a large body of untemplates integrating with an application,\noften collections of them will share common imports, input and output\ntypes, etc. It's tedious to repetitively specify all of this within each\nuntemplate. Here is where you might use [customizers](#customizers) to override\ndefaults that are not explicitly specified for subsets of your untemplates\nthat you select. Right now, for all untemplates, we are returning\n`Customizer.empty`, meaning no customizations, we are using untemplate library\ndefaults. But here you can selectively override these!\n\nAnd that, for now, will suffice for our quick start!\n\n\n"
      writer.write(block4())

    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )

  end apply
end Untemplate_content_quickstart_md

def content_quickstart_md(level : Int) : untemplate.Result[SubsectionMeta] = Untemplate_content_quickstart_md( level )
