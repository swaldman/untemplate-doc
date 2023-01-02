package untemplatedoc

import scala.collection.*
import java.nio.file.{Path, Files}
import com.mchange.codegenutil.*

val usrcDir          = Path.of("src/main/untemplate")
val egenDir          = Path.of("example/untemplate")
val sgenDir          = Path.of("example/scalagen")
val ceciSrc          = usrcDir.resolve("untemplatedoc/ceci-nest-pas.md.untemplate")
val ceciScala        = sgenDir.resolve("untemplatedoc/untemplate_ceci_nest_pas_md.scala")
val ceci2Src         = usrcDir.resolve("untemplatedoc/ceci-nest-pas2.md.untemplate")
val loopySrc         = usrcDir.resolve("untemplatedoc/loopy.md.untemplate")
val loopy2badSrc     = usrcDir.resolve("untemplatedoc/loopy2-bad.md.untemplate-off")
val loopy2Src        = usrcDir.resolve("untemplatedoc/loopy2.md.untemplate")
val someOverridesSrc = usrcDir.resolve("untemplatedoc/some_overrides.md.untemplate")
val thisFileSrc      = usrcDir.resolve("untemplatedoc/README.md.untemplate")

def sgenFor( underscoredName : String ) : Path =
  sgenDir.resolve( s"untemplatedoc/untemplate_${underscoredName}.scala" )
end sgenFor

case class Subsection(title : String, subsections : List[Subsection] = Nil)

def hashHeader(level : Int) =
  require( level >= 1, "Markdown doc section headers contain at least one #")
  "#" * level

val ToDashChar = immutable.Set(' ','-')
val isWordChar = Character.isJavaIdentifierPart

def toAnchor( title : String ) =
  "#" + title.toLowerCase.filter( c => isWordChar(c) || ToDashChar(c) ).map( c => if ToDashChar(c) then '-' else c )

def tocLines( indentLevel : Int, subsection : Subsection ) : List[String] =
  val indent = " " * (indentLevel * 2)
  s"""${indent}* <a href="${toAnchor(subsection.title)}">${subsection.title}</a>""" ::
    subsection.subsections.flatMap(ss => tocLines(indentLevel+1, ss))

def toc( subsection : Subsection ) = tocLines(0, subsection).mkString(LineSep)


