// DO NOT HAND EDIT -- Autogenerated from 'content-indexes.md.untemplate' at 2023-01-11T22:53:10.082804Z

package untemplatedoc.readme.featurecreep

import java.io.{Writer,StringWriter}
import scala.collection.*

import untemplatedoc.*
import java.nio.file.Files
import com.mchange.codegenutil.*




val Untemplate_content_indexes_md = new untemplate.Untemplate[Int,SubsectionMeta]:
  val UntemplateFunction                    : untemplate.Untemplate[Int,SubsectionMeta] = this
  val UntemplateName                        : String = "content_indexes_md"
  val UntemplatePackage                     : String = "untemplatedoc.readme.featurecreep"
  val UntemplateInputName                   : String = "level"
  val UntemplateInputTypeDeclared           : String = "Int"
  val UntemplateInputTypeCanonical          : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[Int] )
  val UntemplateInputDefaultArgument        : Option[Int] = (None : Option[Int])
  val UntemplateOutputMetadataTypeDeclared  : String = "SubsectionMeta"
  val UntemplateOutputMetadataTypeCanonical : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[SubsectionMeta] )
  val UntemplateHeaderNote                  : String = ""

  def apply(level : Int) : untemplate.Result[SubsectionMeta] =
    val writer             : StringWriter = new StringWriter(18280)
    var mbMetadata         : Option[SubsectionMeta] = None
    var outputTransformer  : Function1[untemplate.Result[SubsectionMeta],untemplate.Result[SubsectionMeta]] = readme.subsection_content_transformer_md

    val title = "Indexes"
    mbMetadata = Some( SubsectionMeta( level, title ) )


      val block0 = new Function0[String]:
        def apply() : String =
          "\nWhen you generate a collection (usually a directory hierarchy) of untemplates,\nyou can request that the untemplate library generate an index of the untemplates\nproduced that you can work with at runtime.\n\nHere is an example, where I have specified `Some(\"untemplatedoc.Untemplates\")` as\nmy index, which indexes the untemplates generating this documentation:\n\n```scala\n"
      writer.write(block0())
      
    writer.writeln( Files.readString( indexGen ) )
      val block1 = new Function0[String]:
        def apply() : String =
          "```\n\nYou'll note that, a bit uselessly, the untemplates in indexes are typed very generically\nas accepting `Nothing` for their input and generating an `Option[Any]` as\ntheir metadata. In order to call the indexed untemplate functions, you must\ncast them first to `untemplate.Untemplate[<InputType>,<OutputMetadataType>]`\nor more generically `Function1[<InputType>,<OutputMetadataType>]`.\n\nUsually you will know, by conventions you've adopted, what kinds of functions\nuntemplates are likely to be. Perhaps all the functions under a package called\n`posts` accept a `PageInfo` type and return `EntryMetadata`. You might then\ntry something like\n\n```scala\n  val blogPosts =\n    Untemplates // my generated index\n      .filter( _(0).indexOf(\".posts.\") >= 0 )\n      .map( tup => (tup(0), tup(1).asInstanceOf[untemplate.Untemplate[PageInfo,EntryMetadata]]) )\n      .to(immutable.SortedMap)\n```\n\nNow you have an index of functions you can call, like\n\n```scala\nval my result : unteplate.Result[EntryMetadata] = blogPosts(\"poopyblog.posts.firstPost_md\")( myPageInfo )\n```\n\nIf you do not know the types of your untemplates, even the generic, uncallably\ntyped untemplates held by the default index can be queried for [all their metainformation](#metainformation),\nincluding input and output metadata types.\n\n"
      writer.write(block1())
      
    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )
    
  end apply
end Untemplate_content_indexes_md

def content_indexes_md(level : Int) : untemplate.Result[SubsectionMeta] = Untemplate_content_indexes_md( level )
