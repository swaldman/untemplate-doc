
# Untemplate documentation

_This project only documents the `untemplate` project. For the code, please see [swaldman/untemplate](https://github.com/swaldman/untemplate)._

## Table of contents

* <a href="#untemplate-documentation">Untemplate documentation</a>
  * <a href="#table-of-contents">Table of contents</a>
  * <a href="#introduction">Introduction</a>
  * <a href="#some-simple-untemplates">Some simple untemplates</a>
    * <a href="#embedded-expressions">Embedded expressions</a>
    * <a href="#repeatable-omittable-blocks">Repeatable, omittable, blocks</a>
  * <a href="#functional-templates">Functional templates</a>
    * <a href="#untemplates-are-functions">Untemplates are functions</a>
    * <a href="#text-blocks-can-be-nested-functions">Text blocks can be nested functions</a>
    * <a href="#naming-the-top-level-untemplate-function">Naming the top-level untemplate function</a>
    * <a href="#untemplates-packages-and-imports">Untemplates, packages, and imports</a>
    * <a href="#reflection">Reflection</a>
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
An untemplate is just a text file that optionally includes any of precisely four special delimeters:

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

> :bulb: **Mnemonic**<br/>
> For every construct, whatever an "arrow", `<` or `>`, points at is a text region. Whatever a parenthesis is adjacent to is code.

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
compiled to a simple scala function.

```scala
package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.*

val Function_ceci_nest_pas_md = new Function1[immutable.Map[String,Any],untemplate.Result[Nothing]]:
  val UntemplateFunction             = this
  val UntemplateName                 = "ceci_nest_pas_md"
  val UntemplateInputName            = "input"
  val UntemplateInputType            = "immutable.Map[String,Any]"
  val UntemplateInputDefaultArgument = Some("immutable.Map.empty")
  val UntemplateOutputMetadataType   = "Nothing"

  def apply(input : immutable.Map[String,Any] = immutable.Map.empty) : untemplate.Result[Nothing] =
    val writer     : StringWriter = new StringWriter(406)
    var mbMetadata : Option[Nothing] = None

      val block0 = new Function0[String]:
        def apply() : String =
          "# Ceci n'est pas...\n\nWell, this is just a regular markdown file, with no\nspecial untemplate constructs. But if we wish, we can treat\nit as an unemplate, and it will be immortalized as a scala\nfunction.\n\n"
      writer.write(block0())
      
    untemplate.Result( mbMetadata, writer.toString )
    
  end apply
end Function_ceci_nest_pas_md

def ceci_nest_pas_md(input : immutable.Map[String,Any] = immutable.Map.empty) : untemplate.Result[Nothing] = Function_ceci_nest_pas_md( input )
```
<a href="#table-of-contents">Back to top &#x21ba;</a>

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

Now, the [generated scala](example/scalagen/untemplatedoc/untemplate_ceci_nest_pas2_md.scala) _would_ transform the markdown, like this:

```markdown
# Ceci n'est pas... 0.9687621045754629

Well, this is _almost_ just a regular markdown file, with no
special untemplate constructs. But if we wish, we can treat
it as an unemplate, and it will be immortalized as a scala
function.


```

The delimeter `<( expression )>` causes the `expression` to be evaluated into the text.

> **Note** <br/>
> This `README.md` is [generated by](src/main/untemplate/untemplatedoc/README.md.untemplate) an untemplate! [[current subsection](src/main/untemplate/untemplatedoc/README_some_simple_untemplates.md.untemplate)]
> So how did I slip that delimiter in? Any
> of the untemplate delimeters &mdash; there are only four! &mdash; can be escaped with a `\` character
> just prior to them. The `\` will be stripped, then the delimeter included in the text unmodified.

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

It sucks to be us. (num = 2)

```

And again!
```markdown
# Loopy
# Loopy
# Loopy
# Loopy
# Loopy
# Loopy
# Loopy
# Loopy
# Loopy
# Loopy

And we're a winner! (num = 10)

```
([generated scala](example/scalagen/untemplatedoc/untemplate_loopy_md.scala))

<a href="#table-of-contents">Back to top &#x21ba;</a>









## Functional templates

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

case class Result[+A]( mbMetadata : Option[A], text : String)`.
```

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
> Ick, it's a `var`! It's okay. `mbMetadata` is a strictly local variable, in the single-threaded context of a function
> call. Your function will remain very functional as long as the input type and output metadata types that you specify
> are immutable.

> :bulb: **Tip!** <br/>
> You can specify a default argument along with your custom untemplate input type, using the usual scala
> syntax of `( myVar : MyType = DefaultVal )`

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

It sucks to be us. (num = 3)

```
([generated scala](example/scalagen/untemplatedoc/untemplate_loopy2_md.scala))

<a href="#table-of-contents">Back to top &#x21ba;</a>

### Naming the top-level untemplate function

The `untemplate` app and file-system based tooling in the library will derive a default name for the
top-level generated function by transforming its filename. Untemplate are expected to have the suffix
`.untemplate`. The file you are reading is is [`README.md.untemplate`](src/main/untemplate/untemplatedoc/README.md.untemplate), and [generates a
function](example/scalagen/untemplatedoc/untemplate_README_md.scala) like...

```scala
def README_md( input: immutable.Map[String,Any] ) : untemplate.Result[Nothing] = ???
```

> **Note** <br/>
> Return type `untemplate.Result[Nothing]` looks intimidating, but it's just a
> fancy wrapper for a `String`, as a field called `text`.
> The `[Nothing]` part just means there cannot be metadata attached to this result.

You can override the generated function name in
the same way block function names are defined. Header `()[]~(untemplateDoc)>` would generate

```scala
def untemplateDoc( input: immutable.Map[String,Any] ) : untemplate.Result[Nothing] = ???
```
Header `(pubDate: Instant)[]~(untemplateDoc)>` would generate

```scala
def untemplateDoc( pubDate: Instant ) : untemplate.Result[Nothing] = ???
```

Here's an example. Check out the [generated Scala](example/scalagen/untemplatedoc/untemplate_untemplateDoc.scala) code.

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

_I was published on Tue, 3 Jan 2023 03:49:26 -0500._


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

<a href="#table-of-contents">Back to top &#x21ba;</a>

### Untemplates, packages, and imports

Top-level untemplates are top-level functions, declared directly in a Scala package.
They are paired with implementations in the form of `Function0` objects, which are defined
as `Function_` prepended to the untemplate function name.

Untemplates are usually generated from a source directory, and the default behavior
is for packages to be inferred by the old-school Java convention. The directory hierarchy
beneath specified source directory, to the untemplate source file, will be mapped to a package
name (or dot-separated path of package names). Untemplate source
files placed in the top directory belong to the unnamed "default" package.

However, you can override this default by making an explicit package declaration in the header section of your
untemplate (that is, the section before a [header delimeter](#introduction)). If you wish all untemplates
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

### Reflection

Within an untemplate, you have access to variables containing metainformation about the generated function.

It may be useful to use `UntemplateFunction` as a Map key, in order to decorate it with metadata.
Beyond that, if this will be useful at all, it will probably be for debugging.

For the [untemplate you are reading](src/main/untemplate/untemplatedoc/README_functional_templates.md.untemplate):

```
UntemplateFunction:              <function1>
UntemplateName:                 "README_functional_templates_md"
UntemplateInputType:            "Int"
UntemplateInputDefaultArgument:  None
UntemplateOutputMetadataType:   "Subsection"
```

`UntemplateFunction` is a reference to the `Function1` object that implements your untemplate.

The type values are just `String`s, and names _may not be fully qualified_.

`UntemplateInputDefaultArgument` is an `Option[String]`, the default value as declared, if declared.
It is not the actual value of the default argument!

<a href="#table-of-contents">Back to top &#x21ba;</a>



## Acknowledgments

This project owes a debt to Java Server Pages (JSPs), and the special place they will always have in my heart.

The [mill](https://github.com/com-lihaoyi/mill) plugin I am currently working on owes a debt to
[Twirl](https://github.com/playframework/twirl)'s [plugin](https://github.com/com-lihaoyi/mill/blob/8e2fef20886650882e49ba1aed0f719ddbf72365/contrib/playlib/src/mill/playlib/Twirl.scala),
from which I am gently (and much less sophisticatedly) cribbing.

<a href="#table-of-contents">Back to top &#x21ba;</a>









