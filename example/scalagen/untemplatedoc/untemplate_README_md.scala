package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.*




val Function_README_md = new Function1[immutable.Map[String,Any],untemplate.Result[Nothing]]:
  val UntemplateFunction           = this
  val UntemplateName               = "README_md"
  val UntemplateInputName          = "input"
  val UntemplateInputType          = "immutable.Map[String,Any]"
  val UntemplateOutputMetadataType = "Nothing"

  def apply(input : immutable.Map[String,Any]) : untemplate.Result[Nothing] =
    val writer     : StringWriter = new StringWriter(1788)
    var mbMetadata : Option[Nothing] = None

    val title = "Untemplate documentation"
    val tocTitle = "Table of contents"

    val childLevel = 2
    val intro : untemplate.Result[Subsection]                 = README_introduction_md( childLevel )
    val someSimpleUntemplates : untemplate.Result[Subsection] = README_some_simple_untemplates_md( childLevel )
    val functionalTemplates   : untemplate.Result[Subsection] = README_functional_templates_md( childLevel )

    val topSubsection = Subsection( title, Subsection(tocTitle) :: intro.mbMetadata.toList ::: someSimpleUntemplates.mbMetadata.toList ::: functionalTemplates.mbMetadata.toList ::: Nil )


      val block0 = new Function0[String]:
        def apply() : String =
          "\n# " +  title  +
          "\n\n_This project only documents the `untemplate` project. For the code, please see [swaldman/untemplate](https://github.com/swaldman/untemplate)._\n\n## " +  tocTitle  +
          "\n\n" +  toc(topSubsection)  +
          "\n\n" +  intro.text  +
          "\n\n" +  someSimpleUntemplates.text  +
          "\n\n" +  functionalTemplates.text  +
          "\n\n\n\n\n\n\n\n"
      writer.write(block0())
      
    untemplate.Result( mbMetadata, writer.toString )
    
  end apply
end Function_README_md

def README_md(input : immutable.Map[String,Any]) : untemplate.Result[Nothing] = Function_README_md( input )
