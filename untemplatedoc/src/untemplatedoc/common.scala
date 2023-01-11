package untemplatedoc

import scala.collection.*
import java.nio.file.{Path, Files}
import com.mchange.codegenutil.*

val usrcDir                        = Path.of("untemplatedoc/untemplate")
val sgenDir                        = Path.of("example/scalagen")
val ceciSrc                        = usrcDir.resolve("untemplatedoc/ceci-nest-pas.md.untemplate")
val ceciScala                      = sgenDir.resolve("untemplatedoc/ceci-nest-pas.md.untemplate.scala")
val ceci2Src                       = usrcDir.resolve("untemplatedoc/ceci-nest-pas2.md.untemplate")
val loopySrc                       = usrcDir.resolve("untemplatedoc/loopy.md.untemplate")
val loopy2badSrc                   = usrcDir.resolve("untemplatedoc/loopy2-bad.md.untemplate-off")
val loopy2Src                      = usrcDir.resolve("untemplatedoc/loopy2.md.untemplate")
val someOverridesSrc               = usrcDir.resolve("untemplatedoc/some_overrides.md.untemplate")
val readmeTopSrc                   = usrcDir.resolve("untemplatedoc/readme/frame-main.untemplate")
val readmeEmbeddedExpressionsSrc   = usrcDir.resolve("untemplatedoc/readme/somesimpleuntemplates/content-embeddable-expressions.md.untemplate")
val readmeMetainformationSrc       = usrcDir.resolve("untemplatedoc/readme/functionaltemplates/content-metainformation.md.untemplate")
val readmeOutputTransformersSrc    = usrcDir.resolve("untemplatedoc/readme/featurecreep/content-output-transformers.md.untemplate")
val readmeSubsectionTransformerSrc = usrcDir.resolve("untemplatedoc/readme/subsection-content-transformer.md.untemplate")
val readmeCustomizersSrc           = usrcDir.resolve("untemplatedoc/readme/featurecreep/content-customizers.md.untemplate")


def sgenFor( untemplateSrcPath : String ) : Path =
  sgenDir.resolve( Path.of(s"${untemplateSrcPath}.scala") )
end sgenFor

object SubsectionMeta:
  val Unknown = SubsectionMeta(5, "(title unknown)")
case class SubsectionMeta(level : Int, title : String, subsections : List[SubsectionMeta] = Nil)

def hashHeader(level : Int) =
  require( level >= 1, "Markdown doc section headers contain at least one #")
  "#" * level

val ToDashChar = immutable.Set(' ','-')
val isWordChar = Character.isJavaIdentifierPart

def toAnchor( title : String ) =
  "#" + title.toLowerCase.filter( c => isWordChar(c) || ToDashChar(c) ).map( c => if ToDashChar(c) then '-' else c )

def tocLines( indentLevel : Int, subsection : SubsectionMeta ) : List[String] =
  val indent = " " * (indentLevel * 2)
  s"""${indent}* <a href="${toAnchor(subsection.title)}">${subsection.title}</a>""" ::
    subsection.subsections.flatMap(ss => tocLines(indentLevel+1, ss))

def toc( subsection : SubsectionMeta ) = tocLines(0, subsection).mkString(LineSep)

val TableOfContentsTitle  = "Table of contents"
val TableOfContentsAnchor = toAnchor(TableOfContentsTitle)

val BackToToc = s"""<a href="${TableOfContentsAnchor}">Back to top &#x21ba;</a>"""

def box( emoji : Option[String], label : Option[String] )( block : String ) =
  val emojiPart   = emoji.map(_ + " ").getOrElse("")
  val labelPart   = label.map("**" + _ + "**").getOrElse("")
  val newLinePart = if (emoji orElse label).nonEmpty then " <br/>" + LineSep else ""
  prependEachLine("> ")( emojiPart + labelPart + newLinePart + block)


/*
object Subsection:
  final case class Rendered(title : String, text : String, subsections : List[Subsection])

  object Content:
    final case class Spec(level : Int, title : String)
    type Renderer = Function1[untemplate.Result[Subsection.Content.Spec],untemplate.Result[Nothing]]
    val DefaultRenderer : Renderer = untemplatedoc.readme.defaultRenderer
  type Content = Function1[Int,untemplate.Result[Subsection.Content.Spec]]

  type Renderer = Function[Spec, untemplate.Result[String]]

  def renderer( content: Content ) : Renderer = level => Content.DefaultRenderer(content)
  def apply( r : Renderer ) : Subsection = 

  val Introduction = Subsection( renderer(...) )
type Subsection = Function1[Int,Subsection.Rendered]
*/

// complete: (title, rawText, finalText, [complete,complete, complete])

// content: i => (title, rawText, [content,content,...])
// renderer (title, rawText) => finalText
// (content, renderer): i => (title, rawText, finalText, [content,content,...]) 

// final case class SubsectionFormatInfo( level : Int, title : String, text : String )
// def formatSubsection( level : Int, title : String)( text : String ) = untemplatedoc.readme.contentRenderer( SubsectionFormatInfo(level, title, text) )
