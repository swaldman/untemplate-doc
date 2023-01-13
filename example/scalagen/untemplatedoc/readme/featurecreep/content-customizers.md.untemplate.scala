// DO NOT HAND EDIT -- Autogenerated from 'content-customizers.md.untemplate' at 2023-01-13T00:05:48.215998Z
package untemplatedoc.readme.featurecreep

import java.io.{Writer,StringWriter}
import scala.collection.*

import untemplatedoc.*
import java.nio.file.Files
import com.mchange.codegenutil.*



val Untemplate_content_customizers_md = new untemplate.Untemplate[Int,SubsectionMeta]:
  val UntemplateFunction                    : untemplate.Untemplate[Int,SubsectionMeta] = this
  val UntemplateName                        : String = "content_customizers_md"
  val UntemplatePackage                     : String = "untemplatedoc.readme.featurecreep"
  val UntemplateInputName                   : String = "level"
  val UntemplateInputTypeDeclared           : String = "Int"
  val UntemplateInputTypeCanonical          : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[Int] )
  val UntemplateInputDefaultArgument        : Option[Int] = (None : Option[Int])
  val UntemplateOutputMetadataTypeDeclared  : String = "SubsectionMeta"
  val UntemplateOutputMetadataTypeCanonical : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[SubsectionMeta] )
  val UntemplateHeaderNote                  : String = ""

  val UntemplateAttributes : immutable.Map[String,Any] = immutable.Map.empty

    def apply(level : Int) : untemplate.Result[SubsectionMeta] =
    val writer             : StringWriter = new StringWriter(55340)
    val attrs              : immutable.Map[String,Any] = UntemplateAttributes
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = readme.subsection_content_transformer_md

    val title = "Customizers"
    mbMetadata = Some( SubsectionMeta( level, title ) )


      val block0 = new Function0[String]:
        def apply() : String =
          "\nIn generating, for example, a website, one might find oneself repeating the same\nboilerplate over and over again. Perhaps every `*.md` file should be piped into\na Markdown renderer, and every `*.adoc` file Asciidoctor. Perhaps many of\nyour untemplates make use of the same suite Scala or Java libraries, for which\nyou would have to repetitively declare import statements.\n\nCustomizers to the rescue!\n\nWhen you generate a collection of untemplates, you can specify a `Customizer.Selector`,\nwhich is just a function `Customizer.Key => Customizer`.\n\nDuring the \"transpilation\" to Scala of each untemplate, a `Customizer.Key`\nis generated, like\n\n```scala\n  final case class Key(inferredPackage      : String, // empty string is the default package\n                       resolvedPackage      : String, // empty string is the default package\n                       inferredFunctionName : String,\n                       resolvedFunctionName : String,\n                       outputMetadataType   : String,\n                       headerNote           : String,\n                       sourceIdentifier     : Option[String])\n```\n* `inferredPackage` is the package that would be inferred for the customizer\nbased on its place in the directory hierarchy being transpiled. It may be\noverridden by explicit package declarations in the header (but it usually isn't).\n* `resolvedPackage` is usually the same as `inferredPackage`, unless\nthe untemplate author has\n[overridden that with explicit package declarations](#untemplates-packages-and-imports),\nin which case `resolvedPackage` will be derived from those.\n* `inferredFunctionName` is the function name automatically inferred from\nthe untemplate file name (or equivalent, for nonfile sources of untemplates).\nIt is usually just the file name itself with the `.untemplate` suffix stripped off, and both\n`-` and `.` characters converted to underscore `_`.\n* `resolvedFunctionName` may just be `inferredFunctionName`, but if a user has\n[explicitly defined a function name](#naming-the-top-level-untemplate-function)\nin the untemplate header delimiter, the explcitly defined name will be used\ninstead.\n* `outputMetadataType` is the output metadata type of the `untemplate`,\n`Nothing` by default but [often declared](#untemplates-are-functions)\nto become something more interesting.\n* `headerNote` will usually be an empty string, but untemplate authors can\n[provide a small note](#) next to the untemplate header definition, and\nthis note will be provided in the `Customizer.Key`.\n* Finally, there may be a `sourceIdentifier`, which in file-system-based\nimplementations (i.e. the current implementation) will be the original\nuntemplate file name (without '.' or '-' converted to underscores).\n\nFor the [subsection you are reading]( " + ( readmeCustomizersSrc ) +
          " ),\nthe key would have been:\n\n```scala\n   Key(inferredPackage      = \"untemplatedoc.readme.featurecreep\",\n       resolvedPackage      = \"untemplatedoc.readme.featurecreep\",\n       inferredFunctionName = \"content_customizers_md\",\n       resolvedFunctionName = \"content_customizers_md\",\n       outputMetadataType   = \"SubsectionMeta\",\n       headerNote           = \"\",\n       sourceIdentifier     = Some(\"content-customizers.md.untemplate\"))\n```\n\nFrom the key that your selector is provided, you produce an `untemplate.Customizer`.\nOrdinarily you begin with `Customizer.empty`, which looks like this:\n\n```scala\nfinal case class Customizer(mbOverrideInferredFunctionName : Option[String]              = None,\n                            mbDefaultInputName             : Option[String]              = None,\n                            mbDefaultInputTypeDefaultArg   : Option[InputTypeDefaultArg] = None,\n                            mbOverrideInferredPackage      : Option[String]              = None, // You can use empty String to override inferred package to the default package\n                            mbDefaultMetadataType          : Option[String]              = None,\n                            mbDefaultMetadataValue         : Option[String]              = None,\n                            mbDefaultOutputTransformer     : Option[String]              = None,\n                            extraImports                   : Seq[String]                 = Nil)\n```\n\nThis customizer does nothing at all. Note that all of its fields are options\nor collections, which can be (and so far are) empty.\n\nHowever, you can use case class `copy=` to selectively fill some of these\nvalues. You can override the default input type for a whole range of\nuntemplates from its usual `immutable.Map[String,Any]` to, well, anything.\nSimilarly you can override the input name, the function name,\nthe default input argument, the inferred package, the metadata type and its\ndefault value, or the default output transformer.\n\n**Note that it is always \"defaults\" that you are overriding.**\n\nIf an untemplate\nauthor, [in the untemplate header delimiter](#untemplates-are-functions), explicitly specifies the\ninput name, type, or input default argument, the function name or output metadata type,\nif she sets the output metadata value or the output transformer, the author's action will\noverride the customizer provided defaults. The customizer customizes how\nuntemplates are generate what authors do not explicitly specify.\n\nAn exception to this rule is `extraImports`, which will always be provided\nin addition to any imports the author provides.\n"
      writer.write(block0())
      
    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )
    
  end apply
end Untemplate_content_customizers_md

def content_customizers_md(level : Int) : untemplate.Result[SubsectionMeta] = Untemplate_content_customizers_md( level )
