// DO NOT HAND EDIT -- Autogenerated from 'content-cheat-sheet.md.untemplate' at 2024-06-18T04:02:12.851483Z
package untemplatedoc.readme

import java.io.{Writer,StringWriter}
import scala.collection.{immutable,mutable}

import untemplatedoc.*
import java.nio.file.Files
import com.mchange.codegenutil.*



val Untemplate_content_cheat_sheet_md = new untemplate.Untemplate[Int,SubsectionMeta]:
  val UntemplateFunction                    : untemplate.Untemplate[Int,SubsectionMeta] = this
  val UntemplateName                        : String = "content_cheat_sheet_md"
  val UntemplatePackage                     : String = "untemplatedoc.readme"
  val UntemplateInputName                   : String = "level"
  val UntemplateInputTypeDeclared           : String = "Int"
  val UntemplateInputTypeCanonical          : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[Int] )
  val UntemplateInputDefaultArgument        : Option[Int] = (None : Option[Int])
  val UntemplateOutputMetadataTypeDeclared  : String = "SubsectionMeta"
  val UntemplateOutputMetadataTypeCanonical : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[SubsectionMeta] )
  val UntemplateHeaderNote                  : String = ""
  val UntemplateLastModified                : Option[Long] = Some(1673766125000L)
  val UntemplateSynthetic                   : Boolean = false

  val UntemplateAttributes : immutable.Map[String,Any] = immutable.Map.empty


  def apply(level : Int) : untemplate.Result[SubsectionMeta] =
    val writer             : StringWriter = new StringWriter(57710)
    val attrs              : immutable.Map[String,Any] = UntemplateAttributes
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = readme.subsection_content_transformer_md

    val title = "Cheat sheet"
    mbMetadata = Some( SubsectionMeta( level, title ) )

      val block0 = new Function0[String]:
        def apply() : String =
          "```\nUntemplate Cheat Sheet\n\nLine Delimiters (start of line):\n  ()>       -- text start (code end)\n  <()       -- text end   (code start)\n  ()[]~()>  -- header delimiter (at most one, begins first text block of file)\n\nEmbedded expression:\n  <( expression )>\n\nFunction generated:\n  Given a file hello.md.untemplate with\n    no header or\n    default header ()[]~()> generates\n       def hello_md(input:immutable.Map[String,Any]=immutable.Map.empty): untemplate.Result[Nothing]\n    (i:Int=0)[]~()> generates\n       def hello_md(i:Int=0): untemplate.Result[Nothing]\n    ()[Instant]~()> generates\n       def hello_md(input:immutable.Map[String,Any]=immutable.Map.empty): untemplate.Result[Instant]\n    ()[]~(hi)> generates\n       def hi(input:immutable.Map[String,Any]=immutable.Map.empty): untemplate.Result[Nothing]\n  Fill in any or all of the header fields. Mix or match.\n\n  Output type:\n    final case class Result[+A](mbMetadata : Option[A], text : String ):\n      override def toString() : String = text\n\n  Function object:\n    def hello_md(input : In) : untemplate.Result[Out] <==> val Untemplate_hello_md : untemplateUntemplate[In,Out] <: Function1[In,Out]\n\nInside template:\n  val writer            <: java.io.Writer\n  val attrs              : immutable.Map[String,Any]\n  var mbMetadata         : Option[Out] = None\n  var outputTransformer  : Function1[untemplate.Result[out],untemplate.Result[out]] = identity\n\n  Textblocks automatically write to writer, unless defined as block functions. (Below)\n  Code blocks write to writer, can set mbMetadata and/or outputTransformer\n\nBlock functions:\n  Text blocks began as\n   (myFunction)>\n     - are not automatically written into output\n     - take no arguments and return strings\n     - so, e.g.\n         for (i <- 0 until 3) writer.write(myFunction().toUpperCase)\n\nMetainformation:\n  Available within untemplates and as public vals from Untemplate function objects\n    val UntemplateFunction                    : untemplate.Untemplate[In,Out]\n    val UntemplateName                        : String\n    val UntemplatePackage                     : String\n    val UntemplateInputName                   : String\n    val UntemplateInputTypeDeclared           : String\n    val UntemplateInputTypeCanonical          : Option[String]\n    val UntemplateInputDefaultArgument        : Option[In]\n    val UntemplateOutputMetadataTypeDeclared  : String\n    val UntemplateOutputMetadataTypeCanonical : Option[String]\n    val UntemplateHeaderNote                  : String\n    val UntemplateAttributes                  : immutable.Map[String,Any]\n\nCustomizers:\n  Bulk customization of templates to avoid having to write repetative headers or code\n  - You provide Customizer.Selector: (key : Customizer.Key) => Customizer\n  - For each untemplate, selector will be provided with key\n\n      final case class Key(inferredPackage      : String, // empty string is the default package\n                           resolvedPackage      : String, // empty string is the default package\n                           inferredFunctionName : String,\n                           resolvedFunctionName : String,\n                           outputMetadataType   : String,\n                           headerNote           : String,\n                           sourceIdentifier     : Option[String])\n\n    selector returns customizer\n\n      final case class Customizer(mbOverrideInferredFunctionName : Option[String]              = None,\n                                  mbDefaultInputName             : Option[String]              = None,\n                                  mbDefaultInputTypeDefaultArg   : Option[InputTypeDefaultArg] = None,\n                                  mbOverrideInferredPackage      : Option[String]              = None, // You can use empty String to override inferred package to the default package\n                                  mbDefaultMetadataType          : Option[String]              = None,\n                                  mbDefaultMetadataValue         : Option[String]              = None,\n                                  mbDefaultOutputTransformer     : Option[String]              = None,\n                                  extraImports                   : Seq[String]                 = Nil)\n\n    overriding defaults, ie whatever untemplate author does not explicitly specify\n\nDelimiter variations:\n  Long delimiters:\n  ()> equivalents\n     ()>>>>>>>>>>> # Comments beginning '#' are permissible\n     ()---------->\n     ()->-->>->--> # any combination of '>' and '-' between '()' and terminal '>'\n  <() equivalents\n     <<<<<<<<<<<<() # Comments beginning '#' are permissible\n     <-----------()\n     <<--<-<<<--<() # any combination of '<' and '-' between the first '<' and terminal '()'\n  ()[]~()> equivalents\n     ()[]~~~()> header note # Comments beginning '#' are permissible. Also \"header notes\"\n     ()[]~~~~~~~()>         # Any number at least one of `~` characters are permissible\n\nAttributes:\n  You can associate an immutable.Map[String,Any] which can be queried from\n  inside or outside of your untemplate function. You can thus \"tag\" untemplates\n  in ways that may be useful to applications that will autogenerate text.\n\nIndexes:\n  All the untemplates your project generates can be collected into an index\n  of type immutable.Map[String,Untemplate[Nothing,Any]]. Either by convention\n  or by examining type metainformation (see above), you'll have to downcast\n  these to more specific types before you can call them. But if you will\n  be autogenerating text, you can filter through this index based on name\n  and/or metainformation to organize what gets generated where.\n```\n"
      writer.write(block0())

    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )

  end apply
end Untemplate_content_cheat_sheet_md

def content_cheat_sheet_md(level : Int) : untemplate.Result[SubsectionMeta] = Untemplate_content_cheat_sheet_md( level )
