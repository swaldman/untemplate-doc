package untemplatedoc.readme.functionaltemplates

import java.io.{Writer,StringWriter}
import scala.collection.*

import untemplatedoc.*
import java.nio.file.Files
import com.mchange.codegenutil.*




val Untemplate_content_untemplates_packages_imports_md = new untemplate.Untemplate[Int,SubsectionMeta]:
  val UntemplateFunction                    : untemplate.Untemplate[Int,SubsectionMeta] = this
  val UntemplateName                        : String = "content_untemplates_packages_imports_md"
  val UntemplatePackage                     : String = "untemplatedoc.readme.functionaltemplates"
  val UntemplateInputName                   : String = "level"
  val UntemplateInputTypeDeclared           : String = "Int"
  val UntemplateInputTypeCanonical          : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[Int] )
  val UntemplateInputDefaultArgument        : Option[Int] = (None : Option[Int])
  val UntemplateOutputMetadataTypeDeclared  : String = "SubsectionMeta"
  val UntemplateOutputMetadataTypeCanonical : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[SubsectionMeta] )
  val UntemplateHeaderNote                  : String = ""

  def apply(level : Int) : untemplate.Result[SubsectionMeta] =
    val writer             : StringWriter = new StringWriter(18680)
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = readme.subsection_content_transformer_md

    val title = "Untemplates, packages, and imports"
    mbMetadata = Some( SubsectionMeta( level, title ) )


      val block0 = new Function0[String]:
        def apply() : String =
          "\nTop-level untemplates are top-level functions, declared directly in a Scala package.\nThey are paired with implementations in the form of `Function1` objects, which are defined\nas `Function_` prepended to the untemplate function name.\n\nUntemplates are usually generated from a source directory, and the default behavior\nis for packages to be inferred by the old-school Java convention. The directory hierarchy\nbeneath specified source directory, to the untemplate source file, will be mapped to a package\nname (or dot-separated path of package names). Untemplate source\nfiles placed in the top directory belong to the unnamed \"default\" package.\n\nHowever, you can override this default by making an explicit package declaration in the header section of your\nuntemplate (that is, the section before a [header delimeter](#introduction)).\n\nIf you wish all untemplates\nto be generated into a single flat directory, regardless of where or how deeply they were found beneath the source\ndirectory, you can set the option `flatten` to `true`.\n\nAny package declarations or import statements in a header section go at the top-level, outside of\nthe untemplate-generated function.\n\nAll other code in the header section gets placed inside the generated function.\n\n**This means that whatever input your header accepts is already in scope in the header section,\neven though its name and type may be declared at the end of the header section, inside the header\ndelimeter.**\n\nWhen generating untemplates, applications may specify a set of default imports that will be inserted into\nall generated untemplates. So, if a static site generator makes use of a common set of types and utilities,\nthese can be made automatically available to all templates.\n"
      writer.write(block0())
      
    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )
    
  end apply
end Untemplate_content_untemplates_packages_imports_md

def content_untemplates_packages_imports_md(level : Int) : untemplate.Result[SubsectionMeta] = Untemplate_content_untemplates_packages_imports_md( level )
