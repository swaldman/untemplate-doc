package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.*




val Function_README_top_md = new Function1[Int,untemplate.Result[Subsection]]:
  val UntemplateFunction           = this
  val UntemplateName               = "README_top_md"
  val UntemplateInputName          = "level"
  val UntemplateInputType          = "Int"
  val UntemplateOutputMetadataType = "Subsection"

  def apply(level : Int) : untemplate.Result[Subsection] =
    val writer     : StringWriter = new StringWriter(1206)
    var mbMetadata : Option[Subsection] = None

    val intro : untemplate.Result[Subsection] = README_introduction_md( level + 1 )
    val functionalTemplates : untemplate.Result[Subsection] = README_functional_templates_md( level + 1 )
    val title = "Untemplate Documentation"

    mbMetadata = Some( Subsection( title, intro.mbMetadata.toList ::: functionalTemplates.mbMetadata.toList ::: Nil ) )


      val block0 = new Function0[String]:
        def apply() : String =
          "\n" +  hashHeader(level)  +
          " " +  title  +
          "\n\n_This project only documents the `untemplate` project. For the code, please see [swaldman/untemplate](https://github.com/swaldman/untemplate)._\n\n" +  intro.text  +
          "\n\n<( functionalTemplates.text )>\n\n"
      writer.write(block0())
      
    untemplate.Result( mbMetadata, writer.toString )
    
  end apply
end Function_README_top_md

def README_top_md(level : Int) : untemplate.Result[Subsection] = Function_README_top_md( level )
