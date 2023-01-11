
# Untemplate documentation

_This project only documents the `untemplate` project. For the code, please see [swaldman/untemplate](https://github.com/swaldman/untemplate)._

## Table of contents

* <a href="#untemplate-documentation">Untemplate documentation</a>
  * <a href="#table-of-contents">Table of contents</a>
  * <a href="#introduction">Introduction</a>
  * <a href="#some-simple-untemplates">Some simple untemplates</a>
    * <a href="#embedded-expressions">Embedded expressions</a>
    * <a href="#repeatable-omittable-blocks">Repeatable, omittable, blocks</a>
  * <a href="#functional-untemplates">Functional untemplates</a>
    * <a href="#untemplates-are-functions">Untemplates are functions</a>
    * <a href="#text-blocks-can-be-nested-functions">Text blocks can be nested functions</a>
    * <a href="#naming-the-top-level-untemplate-function">Naming the top-level untemplate function</a>
    * <a href="#untemplates-packages-and-imports">Untemplates, packages, and imports</a>
    * <a href="#metainformation">Metainformation</a>
  * <a href="#feature-creep">Feature Creep</a>
    * <a href="#output-transformers">Output transformers</a>
    * <a href="#customizers">Customizers</a>
    * <a href="#long-delimeters-header-notes-and-comments">Long delimeters, header notes, and comments</a>
  * <a href="#acknowledgments">Acknowledgments</a>


## Introduction


Every once in a while, I find I need to build a little website for something.
I've become a fan of static site generators for that. I've worked with [hugo](https://gohugo.io/) and
[hexo](https://hexo.io/) and [paradox](https://developer.lightbend.com/docs/paradox/current/index.html),
and they've all been great in their way.

But each time, I find myself spending a lot of time in their docs, figuring out each
SSG's specific DSLs, their tricks for doing things, what variables are exposed in
templates, etc.

I found myself yearning for simplicity. Why can't I just specify my static sites in my language
of choice? (For me, Scala.)

Static (and dynamic) site generation is in practice largely about templates. No one enjoys
embedding tons of HTML or Markdown or CSS in programming-language string literals, even
in modern languages that support multiline literals and interpolated strings.

But templates are a step along the slippery path to DSLs with clever, powerful features
that become the idiosyncracies and quirks I'm trying to escape. As much as possible, I
want my specification language to be straightforward Scala.

_Untemplate_ is my attempt to create the thinnest possible template veneer over vanilla Scala 3.
An untemplate is just a text file that optionally includes any of four special delimeters:

| Delimeter | Description |
| --- | --- |
| `<(expression)>` | Text-embedded Scala expression |
| `()>` | Code / text boundary |
| `<()` | Text / code boundary |
| `()[]~()>` | Header delimeter |

These have the following effects:

* `<(expression)>` breaks out of plain text and inserts the result into the text
* `()>` alone, at the beginning of a line, divides the file into a Scala code region, and a
text region. The region above is a Scala code region.
*  `<()` alone, at the beginning of a line, is the inverse of the prior delimeter. It divides the
file into a text region and a Scala code region, with text in the region above, and code in the
region beneath.
*  `()[]~()>` is a special header delimiter. Like `()>`, it divides the file into a Scala code
region above and a text region below. However, import statements in the code region above become
top-level imports in the generated file.

> :bulb: **Mnemonic** <br/>
> For every construct, whatever an "arrow" &mdash; `<` or `>`&mdash points at is a text region. Whatever a parenthesis &mdash; `(` or `)` or `()` &mdash; is adjacent to is code.



<a href="#table-of-contents">Back to top &#x21ba;</a>



## Some simple untemplates


Let's look at an untemplate so simple it seems not to be an untemplate at all.

```markdown
# Ceci n'est pas...

Well, this is just a regular markdown file, with no
special untemplate constructs. But if we wish, we can treat
it as an unemplate, and it will be immortalized as a scala
function.
```
It's just a markdown file! But if it's stored in an untemplate source directory as `ceci-nest-pas.md.untemplate`, it gets
compiled to a simple scala function, `ceci_nest_pas_md()`. (It's the little `def` declaration at the very end.)

```scala
// DO NOT HAND EDIT -- Autogenerated from 'ceci-nest-pas.md.untemplate' at 2023-01-11T16:23:51.936839Z

package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.*

val Untemplate_ceci_nest_pas_md = new untemplate.Untemplate[immutable.Map[String,Any],Nothing]:
  val UntemplateFunction                    : untemplate.Untemplate[immutable.Map[String,Any],Nothing] = this
  val UntemplateName                        : String = "ceci_nest_pas_md"
  val UntemplatePackage                     : String = "untemplatedoc"
  val UntemplateInputName                   : String = "input"
  val UntemplateInputTypeDeclared           : String = "immutable.Map[String,Any]"
  val UntemplateInputTypeCanonical          : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[immutable.Map[String,Any]] )
  val UntemplateInputDefaultArgument        : Option[immutable.Map[String,Any]] = Some(immutable.Map.empty)
  val UntemplateOutputMetadataTypeDeclared  : String = "Nothing"
  val UntemplateOutputMetadataTypeCanonical : Option[String] = untemplate.Macro.nonEmptyStringOption( untemplate.Macro.recursiveCanonicalName[Nothing] )
  val UntemplateHeaderNote                  : String = ""

  def apply(input : immutable.Map[String,Any] = immutable.Map.empty) : untemplate.Result[Nothing] =
    val writer             : StringWriter = new StringWriter(2030)
    var mbMetadata         : Option[Nothing] = None
    var outputTransformer  : Function1[untemplate.Result[Nothing],untemplate.Result[Nothing]] = identity

      val block0 = new Function0[String]:
        def apply() : String =
          "# Ceci n'est pas...\n\nWell, this is just a regular markdown file, with no\nspecial untemplate constructs. But if we wish, we can treat\nit as an unemplate, and it will be immortalized as a scala\nfunction.\n\n"
      writer.write(block0())
      
    outputTransformer( untemplate.Result( mbMetadata, writer.toString ) )
    
  end apply
end Untemplate_ceci_nest_pas_md

def ceci_nest_pas_md(input : immutable.Map[String,Any] = immutable.Map.empty) : untemplate.Result[Nothing] = Untemplate_ceci_nest_pas_md( input )
```


### Embedded expressions


We'd like, of course, for our (un)template library to do a bit more than just spit out unmodified
text files though. Let's modify our example just a bit:

```markdown
# Ceci n'est pas... <(math.random)>

Well, this is _almost_ just a regular markdown file, with no
special untemplate constructs. But if we wish, we can treat
it as an unemplate, and it will be immortalized as a scala
function.
```

Now, the [generated scala](example/scalagen/untemplatedoc/ceci-nest-pas2.md.untemplate.scala) _would_ transform the markdown, like this:

```markdown
# Ceci n'est pas... 0.20114585959214415

Well, this is _almost_ just a regular markdown file, with no
special untemplate constructs. But if we wish, we can treat
it as an unemplate, and it will be immortalized as a scala
function.


```

The delimeter `<( expression )>` causes the `expression` to be evaluated into the text.

> **Note** <br/>
> This `README.md` is [generated by](untemplatedoc/untemplate/untemplatedoc/readme/frame-main.untemplate) an untemplate! [[current subsection](untemplatedoc/untemplate/untemplatedoc/readme/somesimpleuntemplates/content-embeddable-expressions.md.untemplate)]
> So how did I slip that delimiter in? Any
> of the untemplate delimeters &mdash; there are only four! &mdash; can be escaped with a `\` character
> just prior to them. The `\` will be stripped, then the delimeter included in the text unmodified.
> 
> 


<a href="#table-of-contents">Back to top &#x21ba;</a>



### Repeatable, omittable, blocks


Often you'd like to do more than just embed a few very simple expressions in some text.
So, you can break up your text into code blocks and text blocks. Let's do that, and repeat
a block of text in a loop.

```markdown
val num = math.round(math.random * 10).toInt

for (i <- 0 until num)
()>
# Loopy
<()

if (num >= 5)
()>

And we're a winner! (num = <(num)>)
<()
else
()>

It sucks to be us. (num = <(num)>)
```

Let's get a look at what it produces:
```markdown
# Loopy
# Loopy
# Loopy
# Loopy
# Loopy
# Loopy

And we're a winner! (num = 6)

```

And again!
```markdown
# Loopy
# Loopy
# Loopy
# Loopy
# Loopy

And we're a winner! (num = 5)

```
([generated scala](example/scalagen/untemplatedoc/loopy.md.untemplate.scala))



<a href="#table-of-contents">Back to top &#x21ba;</a>









## Functional untemplates



### Untemplates are functions


Every untemplate defines a Scala function. By default, from a file called `awesomeness.md.untemplate`, this
function would look like...

```scala
def awesomeness_md( input : immutable.Map[String,Any] = immutable.Map.empty ) : untemplate.Result[Nothing]
```

The top-level function accepts a single, author-specifiable input. (`immutable.Map[String,Any]` is just a default.)

It returns the template output as a simple `String`, along with any metadata that untemplate chooses to provide.

More specifically, each template returns a

```scala
package untemplate

final case class Result[+A](mbMetadata : Option[A], text : String ):
  override def toString() : String = text
```

Note that the `toString()` method is overridden, so you can embed `Result` directly an untemplate expressions.
The text will be printed, without metadata.

Untemplate authors may (optionally!) specify the input name and type of the untemplate function, and output metadata type,
in the header delimiter:

```scala
(sourceMarkdown : String)[immutable.Map[String,String]]~()>
```
This header causes the generated untemplate function to require a `String` input, which the template author can work with in the
template as `sourceMarkdown`.

The function will return whatever text it generates, along with an `Option[immutable.Map[String,String]]`.

By default, this returned metadata will be `None`, but the template can provide `Some(metadata)` by overwriting the `var` called `mbMetadata`.
> :blush: **It's okay!** <br/>
> Ick, it's a `var`. It's okay! `mbMetadata` is a strictly local variable, in the single-threaded context of a function
> call. Your function will remain very functional as long as the input type and output metadata types that you specify
> are immutable.
> 

> :bulb: **Tip!** <br/>
> You can specify a default argument along with your custom untemplate input type, using the usual scala
> syntax of `( myVar : MyType = DefaultVal )`
> 


<a href="#table-of-contents">Back to top &#x21ba;</a>



### Text blocks can be nested functions


Every text block within an untemplate can be a function.


Ordinarily, text blocks just print themselves
automatically into the generated `String`. However, if you embed a name in the `()>` delimeter that begins
the block, like `(entry)>`, then nothing is automatically printed into the `String`. Instead you will have a function
`entry()` to work with in code blocks.

The block function will return a simple `String`.

Use `writer.write(entry())` to generate text into untemplate output.

Let's try to redo our ["Loopy" template](#repeatable-omittable-blocks) making the text block that prints `# Loopy` into a function.

Instead of beginning our blocks with `()>`, we embed a valid scala identifier into the parenthesis,
like `(loopy)>`.

However, doing that carries with it some complications. If we just try that in our loopy markdown
file as it was, we'll get compilation errors.

The file...
```scala
val num = math.round(math.random * 10).toInt

for (i <- 0 until num)
(loopy)>
# Loopy
<()

if (num >= 5)
()>

And we're a winner! (num = <(num)>)
<()
else
()>

It sucks to be us. (num = <(num)>)
```
And the ickies...
```
[info] compiling 7 Scala sources to /Users/swaldman/Dropbox/BaseFolders/development-why/gitproj/untemplate-doc/target/scala-3.2.1/classes ...
[error] -- [E018] Syntax Error: /Users/swaldman/Dropbox/BaseFolders/development-why/gitproj/untemplate-doc/target/scala-3.2.1/src_managed/main/untemplate/untemplatedoc/untemplate_loopy2_bad_md.scala:19:26
[error] 19 |    for (i <- 0 until num)
[error]    |                          ^
[error]    |                          expression expected but val found
[error]    |
[error]    | longer explanation available when compiling with `-explain`
[error] -- [E006] Not Found Error: /Users/swaldman/Dropbox/BaseFolders/development-why/gitproj/untemplate-doc/target/scala-3.2.1/src_managed/main/untemplate/untemplatedoc/untemplate_loopy2_bad_md.scala:23:18
[error] 23 |    def loopy() = block0()
[error]    |                  ^^^^^^
[error]    |                  Not found: block0
[error]    |
[error]    | longer explanation available when compiling with `-explain`
[error] two errors found
[error] (Compile / compileIncremental) Compilation failed
```

Before things worked, because when we're just printing an expression to output, we indent the call to write in
the generated code so that it falls inside of any loops, if expressions, or other language constructs that the
prior code block has set up.

If we are going to want to treat the block as a reusable function, then we do not wish to enclose its declaration
in a very narrow scope. So, the function declaration provoked by named blocks is not indented, and named blocks
do not print by default.

If you want to use a named block, define it before you get to branches in your code flow,
then call your named function, which returns a `String` you can write. Let's fix our _Loopy_.

```scala
val num = math.round(math.random * 10).toInt

// comments in code blocks are fine!
// here is one way to turn text blocks into functions
(loopy)>
# Loopy
<()
for (i <- 0 until num)
  writer.write(loopy()) // you have a java.io.Writer, called writer, to send output to

// below is another, perhaps even simpler way to turn blocks into functions
//
// the indent of the if and else clauses must be lined up,
// the statement that prints becomes indented from that level!
def reportCard() : Unit =
  if (num >= 5)
()>

And we're a winner! (num = <(num)>)
<()
  else
()>

It sucks to be us. (num = <(num)>)
<()
reportCard()
```

Not the loveliest file. But educational.
Here is the output...

```markdown
# Loopy
# Loopy
# Loopy
# Loopy
# Loopy
# Loopy
# Loopy

And we're a winner! (num = 7)

```
([generated scala](example/scalagen/untemplatedoc/loopy2.md.untemplate.scala))


<a href="#table-of-contents">Back to top &#x21ba;</a>



### Naming the top-level untemplate function


The `untemplate` app and file-system based tooling in the library will derive a default name for the
top-level generated function by transforming its filename. Untemplate are expected to have the suffix
`.untemplate`. The file you are reading is is [`README.md.untemplate`](untemplatedoc/untemplate/untemplatedoc/readme/frame-main.untemplate), and [generates a
function](example/scalagen/untemplatedoc/readme/content-main.untemplate.scala) like...

```scala
def README_md( input: immutable.Map[String,Any] ) : untemplate.Result[Nothing] = ???
```
> **Note** <br/>
> Return type `untemplate.Result[Nothing]` looks intimidating, but it's just a
> fancy wrapper for a `String`, as a field called `text`.
> The `[Nothing]` part just means there cannot be metadata attached to this result.
> 

You can override the generated function name in
the same way block function names are defined. Header `()[]~(untemplateDoc)>` would generate

```scala
def untemplateDoc( input: immutable.Map[String,Any] ) : untemplate.Result[Nothing] = ???
```
Header `(pubDate: Instant)[]~(untemplateDoc)>` would generate

```scala
def untemplateDoc( pubDate: Instant ) : untemplate.Result[Nothing] = ???
```

Here's an example untemplate. Check out the [generated scala](example/scalagen/untemplatedoc/some_overrides.md.untemplate.scala) code.

```scala
import java.time.{Instant, ZoneId}
import java.time.format.DateTimeFormatter

// note that all non-import (and non-package) lines in the header get 
// generated WITHIN the untemplate function, so pubDate is in scope!

val formatted = DateTimeFormatter.RFC_1123_DATE_TIME.format( pubDate.atZone( ZoneId.systemDefault() ) )

(pubDate: Instant)[]~(untemplateDoc)>

# Birthday Post

Happy Birthday to me!

_I was published on <(formatted)>._
```
Which generates...

```markdown

# Birthday Post

Happy Birthday to me!

_I was published on Wed, 11 Jan 2023 11:24:06 -0500._


```
> :question: **Question** <br/>
> What if you want to override the name of the top level function _and_ use
> the first text block as a function? You can!
> 
> The header `()[]~(mamaFunction.startText)>`
> would override the outer function name with `mamaFunction`, and turn the first text block into
> a function `startText()`.
> 
> The header `()[]~(.startText)>` would turn the first text block into a function
> called `startText()`, but leave the top-level function name alone.
> 


<a href="#table-of-contents">Back to top &#x21ba;</a>



### Untemplates, packages, and imports


Top-level untemplates are top-level functions, declared directly in a Scala package.
They are paired with implementations in the form of `Function1` objects, which are defined
as `Untemplate_` prepended to the untemplate function name.

Untemplates are usually generated from a source directory, and the default behavior
is for packages to be inferred by the old-school Java convention. The directory hierarchy
beneath specified source directory, to the untemplate source file, will be mapped to a package
name (or dot-separated path of package names). Untemplate source
files placed in the top directory belong to the unnamed "default" package.

However, you can override this default by making an explicit package declaration in the header section of your
untemplate (that is, the section before a [header delimeter](#introduction)).

If you wish all untemplates
to be generated into a single flat directory, regardless of where or how deeply they were found beneath the source
directory, you can set the option `flatten` to `true`.

Any package declarations or import statements in a header section go at the top-level, outside of
the untemplate-generated function.

All other code in the header section gets placed inside the generated function.

**This means that whatever input your header accepts is already in scope in the header section,
even though its name and type may be declared at the end of the header section, inside the header
delimeter.**

When generating untemplates, applications may specify a set of default imports that will be inserted into
all generated untemplates. So, if a static site generator makes use of a common set of types and utilities,
these can be made automatically available to all templates.


<a href="#table-of-contents">Back to top &#x21ba;</a>



### Metainformation


Within an untemplate, you have access to variables containing metainformation about the generated function.

It may be useful to use `UntemplateFunction` as a Map key, in order to decorate it with metadata.
Beyond that, if this will be useful at all, it will probably be for debugging.

For the [untemplate you are reading](untemplatedoc/untemplate/untemplatedoc/readme/functionaltemplates/content-metainformation.md.untemplate) [[generated scala](example/scalagen/untemplatedoc/readme/functionaltemplates/content-metainformation.md.untemplate.scala)]:

```
UntemplateFunction:                      untemplate.Untemplate[Int,SubsectionMeta]
UntemplateName:                         "content_metainformation_md"
UntemplatePackage:                      "untemplatedoc.readme.functionaltemplates"
UntemplateInputTypeDeclared:            "Int"
UntemplateInputTypeCanonical:            Some(scala.Int)
UntemplateInputDefaultArgument:          None
UntemplateOutputMetadataTypeDeclared:   "SubsectionMeta"
UntemplateOutputMetadataTypeCanonical:   Some(untemplatedoc.SubsectionMeta)
UntemplateHeaderNote:                   "This is a header note."
```

`UntemplateFunction` is a reference to the `Untemplate` (which is a subtype of `Function1`) that implements your untemplate.

"Declared" type values are just `String`, and names _may not be fully qualified_.

"Canonical" types are, if possible, resolved to fully qualified type names that look through (non-opaque) aliases.
However, for some types such resolution may not be possible, so these are `Option[String]`

`UntemplateInputDefaultArgument` is an `Option[T]` where `T` is the input type and the value is `Some(defaultInputArg)`
if one was defined or `None` otherwise.

Untemplate metadata is available not only within your untemplates. Each untemplate
becomes becomes an `untemplate.Untemplate` object, on which all the metadata
fields are available as public vals. If you have an untemplate called
`mypkg.hello_md`, there will be an `untemplate.Untemplate` object called
`mypkg.Untemplate_hello_md`, so you can write, e.g.

```scala
val helloHeaderNote = mypkg.Untemplate_hello_md.UntemplateHeaderNote
```

This can be particularly useful in combination with [indexing](#indexing).


<a href="#table-of-contents">Back to top &#x21ba;</a>








## Feature Creep



### Output transformers


In the header or any code section of an untemplate, you can define an `OutputTransformer`,
like this:

```scala
outputTransformer = myOutputTransformer
```

As the name suggests, an output transformer will simply transform
the function output.

If you haven't defied a custom output metadata type,
then it must be a `Function1` that maps `untemplate.Result[Nothing] => untemplate.Result[Nothing]`.

If you have defined an output metadata type, say `HttpMetadata`, then it must be
a function `untemplate.Result[HttpMetadata] => untemplate.Result[HttpMetadata]`.

By default, every untemplate output travels through the identity transformer `identity`.
It's as if you had set:

```scala
outputTransformer = myOutputTranformer
```
But you can set your own, more interesting, transformer.

You can set output transfers as above, "by hand", or you can use an [`untemplate.Customizer`](#customizers)
to transform a whole class of untemplates. For example, you could have all untemplates
generated from a file like `something.md.untemplate` pass through an output transformer that
converts Markdown to HTML.

The untemplate [you are now reading](untemplatedoc/untemplate/untemplatedoc/readme/featurecreep/content-output-transformers.md.untemplate) is passed through
an output transformer, which embeds the text in a markdown subsection,
rendering at the appropriate level something like

```markdown
### Section Title

Lorem ipsum dolor sit amet, consectetur adipiscing elit,
sed do eiusmod tempor incididunt ut labore et dolore magna
aliqua.
```

That output transformer is itself defined by an untemplate,
[untemplatedoc.readme.subsection_content_transformer_md](untemplatedoc/untemplate/untemplatedoc/readme/subsection-content-transformer.md.untemplate).

Most output transformers will not be untemplates! But an untemplate is just a
way to define a function that returns an `untemplate.Result`, and
an output transformer is just that kind of function.

It is as the untemplate for this section includes the line

```scala
outputTransformer = untemplatedoc.readme.subsection_content_transformer_md
```
and in earlier drafts, it did precisely that! But that got repetitive
to include for every subsection, so we turn to [customizers](#customizers).


<a href="#table-of-contents">Back to top &#x21ba;</a>



### Customizers


In generating, for example, a website, one might find oneself repeating the same
boilerplate over and over again. Perhaps every `*.md` file should be piped into
a Markdown renderer, and every `*.adoc` file Asciidoctor. Perhaps many of
your untemplates make use of the same suite Scala or Java libraries, for which
you would have to repetitively declare import statements.

Customizers to the rescue!

When you generate a collection of untemplates, you can specify a `Customizer.Selector`,
which is just a function `Customizer.Key => Customizer`.

During the "transpilation" to Scala of each untemplate, a `Customizer.Key`
is generated, like

```scala
  final case class Key(inferredPackage      : String, // empty string is the default package
                       resolvedPackage      : String, // empty string is the default package
                       inferredFunctionName : String,
                       resolvedFunctionName : String,
                       outputMetadataType   : String,
                       headerNote           : String,
                       sourceIdentifier     : Option[String])
```
* `inferredPackage` is the package that would be inferred for the customizer
based on its place in the directory hierarchy being transpiled. It may be
overridden by explicit package declarations in the header (but it usually isn't).
* `resolvedPackage` is usually the same as `inferredPackage`, unless
the untemplate author has
[overridden that with explicit package declarations](#untemplates-packages-and-imports),
in which case `resolvedPackage` will be derived from those.
* `inferredFunctionName` is the function name automatically inferred from
the untemplate file name (or equivalent, for nonfile sources of untemplates).
It is usually just the file name itself with the `.untemplate` suffix stripped off, and both
`-` and `.` characters converted to underscore `_`.
* `resolvedFunctionName` may just be `inferredFunctionName`, but if a user has
[explicitly defined a function name](#naming-the-top-level-untemplate-function)
in the untemplate header delimeter, the explcitly defined name will be used
instead.
* `outputMetadataType` is the output metadata type of the `untemplate`,
`Nothing` by default but [often declared](#untemplates-are-functions)
to become something more interesting.
* `headerNote` will usually be an empty string, but untemplate authors can
[provide a small note](#) next to the untemplate header definition, and
this note will be provided in the `Customizer.Key`.
* Finally, there may be a `sourceIdentifier`, which in file-system-based
implementations (i.e. the current implementation) will be the original
untemplate file name (without '.' or '-' converted to underscores).

For the [subsection you are reading]( untemplatedoc/untemplate/untemplatedoc/readme/featurecreep/content-customizers.md.untemplate ),
the key would have been:

```scala
   Key(inferredPackage      = "untemplatedoc.readme.featurecreep",
       resolvedPackage      = "untemplatedoc.readme.featurecreep",
       inferredFunctionName = "content_customizers_md",
       resolvedFunctionName = "content_customizers_md",
       outputMetadataType   = "SubsectionMeta",
       headerNote           = "",
       sourceIdentifier     = Some("content-customizers.md.untemplate"))
```

From the key that your selector is provided, you produce an `untemplate.Customizer`.
Ordinarily you begin with `Customizer.empty`, which looks like this:

```scala
final case class Customizer(mbOverrideInferredFunctionName : Option[String]              = None,
                            mbDefaultInputName             : Option[String]              = None,
                            mbDefaultInputTypeDefaultArg   : Option[InputTypeDefaultArg] = None,
                            mbOverrideInferredPackage      : Option[String]              = None, // You can use empty String to override inferred package to the default package
                            mbDefaultMetadataType          : Option[String]              = None,
                            mbDefaultMetadataValue         : Option[String]              = None,
                            mbDefaultOutputTransformer     : Option[String]              = None,
                            extraImports                   : Seq[String]                 = Nil)
```

This customizer does nothing at all. Note that all of its fields are options
or collections, which can be (and so far are) empty.

However, you can use case class `copy=` to selectively fill some of these
values. You can override the default input type for a whole range of
untemplates from its usual `immutable.Map[String,Any]` to, well, anything.
Similarly you can override the input name, the function name,
the default input argument, the inferred package, the metadata type and its
default value, or the default output transformer.

**Note that it is always "defaults" that you are overriding.**

If an untemplate
author, [in the untemplate header delimeter](#untemplates-are-functions), explicitly specifies the
input name, type, or input default argument, the function name or output metadata type,
if she sets the output metadata value or the output transformer, the author's action will
override the customizer provided defaults. The customizer customizes how
untemplates are generate what authors do not explicitly specify.

An exception to this rule is `extraImports`, which will always be provided
in addition to any imports the author provides.


<a href="#table-of-contents">Back to top &#x21ba;</a>



### Long delimeters, header notes, and comments


**Long headers**

Recall from our [introduction](#introduction) the four untemplate delimeters:

| Delimeter | Description |
| --- | --- |
| `<(expression)>` | Text-embedded Scala expression |
| `()>` | Code / text boundary |
| `<()` | Text / code boundary |
| `()[]~()>` | Header delimeter |

The three "line delimeters" &mdash; `()>`, `<()`, and `()[]~()>` &mdash
must be tucked against the left-hand margin. They are
small and easy to miss while scanning a file, even though they
dramatically switch the meaning of portions of the file from code to text.

Sometimes you may wish these boundaries were more obvious. So they can be!
The untemplate transpiler will let you expand `()>` and `<()` by placing
arbitrary numbers of `-` characters, and also the delimeter appropriate
`>` or `<` between the parentheses and the arrow. So all of the following
are equivalent to the untemplate engine:

* `()>`
* `()>>>>>>>>>>>>>>>>>>>>>>>>>>>`
* `()-------------------------->`
* `()------>------>------>----->`

Similarly, you can close your textblock, equivalently, with

* `<()`
* `<<<<<<<<<<<<<<<<<<<<<<<<<<<()`
* `<---------------------------()`
* `<----- -<-------<-------<---()`

The styles don't have to match. The transpiler interprets all of the first list
as `()>`, and all of the second list as `<()`, regardless of what style you
choose.

In any of its styles, you can enter a name in the parentheses of a start-text
block to [convert the block following into a named function](#text-blocks-can-be-nested-functions).

The headers can also be elongated, with the `~` character. So, the following are
all equivalent:

* `()[]~()>`
* `()[]~~~()>`
* `()[]~~~~~~~~~~~~~~~~~~~~~~~~~~~~()>`

Again, the meaning of the header, including your
[ability to optionally include](#naming-the-top-level-untemplate-function) an input
name, type, and default argument in the `()`, and an output metadata type in
the `[]`, remain unchanged.

**Delimeter comments and header notes**

In general, nothing should be placed to the right of `()>` and `<()` delimeters (in their
standard or long variants), but if you wish, you may place a comment beginning
with a `#` character. So this is a fine text block:

```
(regreet)>--->  # because we love to greet the user, a function we can reuse a lot
       >>>>>>>>> Hello <( name )>!!! <<<<<<<<<
<----------<()
```
However, for the header delimeter, untemplates support a "header note", at most
one per untemplate:

```
()[]~~~~()> This is a header note. # This is a comment
```

The header note becomes [metainformation](#metainformation), available
within your untemplate and published externally. It is also included in
`Customizer.Key`, so you can set-up defaults for whole groups of untemplates
based on header notes if yu wish. See [Customizers](#customizers) above.





<a href="#table-of-contents">Back to top &#x21ba;</a>








## Acknowledgments


This project owes a debt to Java Server Pages (JSPs), and the special place they will always have in my heart.

The [mill](https://github.com/com-lihaoyi/mill) plugin I am currently working on owes a debt to
[Twirl](https://github.com/playframework/twirl)'s [plugin](https://github.com/com-lihaoyi/mill/blob/8e2fef20886650882e49ba1aed0f719ddbf72365/contrib/playlib/src/mill/playlib/Twirl.scala),
from which I am gently (and much less sophisticatedly) cribbing.


<a href="#table-of-contents">Back to top &#x21ba;</a>









