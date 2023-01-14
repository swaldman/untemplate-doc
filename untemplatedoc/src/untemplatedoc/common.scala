package untemplatedoc

import scala.collection.*
import java.nio.file.{Path, Files}
import com.mchange.codegenutil.*

val libScalaDir                    = Path.of("untemplatdoc/src")
val usrcDir                        = Path.of("untemplatedoc/untemplate")
val sgenDir                        = Path.of("example/scalagen")
val ceciSrc                        = usrcDir.resolve("untemplatedoc/ceci-nest-pas.md.untemplate")
val ceciScala                      = sgenDir.resolve("untemplatedoc/ceci-nest-pas.md.untemplate.scala")
val ceci2Src                       = usrcDir.resolve("untemplatedoc/ceci-nest-pas2.md.untemplate")
val loopySrc                       = usrcDir.resolve("untemplatedoc/loopy.md.untemplate")
val loopy2badSrc                   = usrcDir.resolve("untemplatedoc/loopy2-bad.md.untemplate-off")
val loopy2Src                      = usrcDir.resolve("untemplatedoc/loopy2.md.untemplate")
val someOverridesSrc               = usrcDir.resolve("untemplatedoc/some_overrides.md.untemplate")
val readmeTopSrc                   = usrcDir.resolve("untemplatedoc/readme/frame-main.md.untemplate")
val readmeEmbeddedExpressionsSrc   = usrcDir.resolve("untemplatedoc/readme/somesimpleuntemplates/content-embeddable-expressions.md.untemplate")
val readmeMetainformationSrc       = usrcDir.resolve("untemplatedoc/readme/functionaltemplates/content-metainformation.md.untemplate")
val readmeOutputTransformersSrc    = usrcDir.resolve("untemplatedoc/readme/featurecreep/content-output-transformers.md.untemplate")
val readmeSubsectionTransformerSrc = usrcDir.resolve("untemplatedoc/readme/subsection-content-transformer.md.untemplate")
val readmeCustomizersSrc           = usrcDir.resolve("untemplatedoc/readme/featurecreep/content-customizers.md.untemplate")
val readmeAttributesSrc            = usrcDir.resolve("untemplatedoc/readme/featurecreep/content-attributes.md.untemplate")
val readmeAttributesScala          = sgenDir.resolve("untemplatedoc/readme/featurecreep/content-attributes.md.untemplate.scala")

val indexGen = sgenDir.resolve( "untemplatedoc/UntemplateIndex_Untemplates.scala" )

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

val untemplateVersion = "0.0.1-SNAPSHOT"