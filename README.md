
# Untemplate Documentation

_This project only documents the `untemplate` project. For the code, please see [swaldman/untemplate](https://github.com/swaldman/untemplate)._

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

_Untemplate_ is my attempt to create the thinnest possible template veneer over vanilla Scala.
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

> :bulb:
> For every construct, whatever an "arrow", `<` or `>`, points at is a text region. Whatever a parenthesis is adjacent to is code.

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
  val UntemplateFunction           = this
  val UntemplateName               = "ceci_nest_pas_md"
  val UntemplateInputName          = "input"
  val UntemplateInputType          = "immutable.Map[String,Any]"
  val UntemplateOutputMetadataType = "Nothing"

  def apply(input : immutable.Map[String,Any]) : untemplate.Result[Nothing] =
    val writer     : StringWriter = new StringWriter(406)
    var mbMetadata : Option[Nothing] = None

      val block0 = new Function0[String]:
        def apply() : String =
          "# Ceci n'est pas...\n\nWell, this is just a regular markdown file, with no\nspecial untemplate constructs. But if we wish, we can treat\nit as an unemplate, and it will be immortalized as a scala\nfunction.\n\n"
      writer.write(block0())
      
    untemplate.Result( mbMetadata, writer.toString )
    
  end apply
end Function_ceci_nest_pas_md

def ceci_nest_pas_md(input : immutable.Map[String,Any]) : untemplate.Result[Nothing] = Function_ceci_nest_pas_md( input )
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

Now, the [generated scala](example/scalagen/untemplatedoc/untemplate_ceci_nest_pas2_md.scala) _would_ transform the markdown, like this:

```markdown
# Ceci n'est pas... 0.5885127000679287

Well, this is _almost_ just a regular markdown file, with no
special untemplate constructs. But if we wish, we can treat
it as an unemplate, and it will be immortalized as a scala
function.


```

The delimeter `<( expression )>` causes the `expression` to be evaluated into the text.

> :point_right: This `README.md` is [generated by](example/scalagen/untemplatedoc/untemplate_README_md.scala) an untemplate!
> So how did I slip that delimiter in? Any
> of the untemplate delimeters &mdash; there are only four! &mdash; can be escaped with a `\` character
> just prior to them. The `\` will be stripped, then the delimeter included in the text unmodified.

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

It sucks to be us. (num = 4)

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

And we're a winner! (num = 7)

```
([generated scala](example/scalagen/untemplatedoc/untemplate_loopy_md.scala.scala))

## Functional templates

### Untemplates are functions

Every untemplate defines a Scala function, and every text block defines a nested function.

The top-level function accepts a single, author-specifiable input. It returns
the template output as a simple `String`, along with any metadata that untemplate chooses to provide.

More specifically, each template returns a `untemplate.Result[+A]( mbMetadata : Option[A], text : String)`.

Untemplate authors (optionally!) specify the input name and type of the untemplate function, and output metadata type,
in the header delimiter:

```scala
(sourceMarkdown : String)[immutable.Map[String,String]]~()>
```
This header causes the generated untemplate function to require a `String` input, which the template author can work with in the
template as `sourceMarkdown`.

The function will return whatever text it generates, along with an `Option[immutable.Map[String,String]]`.

By default, this returned metadata will be `None`, but the template can provide `Some(metadata)` by overwriting the `var` called `mbMetadata`.

> :point_right: Ick, a `var`! `mbMetadata` is a strictly local variable, in the single-threaded context of a function
> call. Your function will remain very functional as long as the input type and output metadata types that you specify
> are immutable.

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
# Loopy

And we're a winner! (num = 8)

```
([generated scala](example/scalagen/untemplatedoc/untemplate_loopy2_md.scala))

### Naming the top-level untemplate function

The `untemplate` app and file-system based tooling in the library will derive a default name for the
top-level generated function by transforming its filename. Untemplate are expected to have the suffix
`.untemplate`. The file you are reading is is [`README.md.untemplate`](src/main/untemplate/untemplatedoc/README.md.untemplate), and [generates a
function](example/scalagen/untemplatedoc/untemplate_README_md.scala) like...

```scala
def README_md( input: immutable.Map[String,Any] ) : untemplate.Result[Nothing] = ???
```

> :point_right: `untemplate.Result[Nothing]` looks intimidating, but it's just a
> fancy wrapper for a `String`. The result has a `text` field &mdash; that `String`.
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

> :question: What if you want to override the name of the top level function _and_ use
> the first text block as a function? You can! The header `()[]~(mamaFunction.startText)>`
> would override the outer function name with `mamaFunction`, and turn the first text block into
> a function `startText()`. `()[]~(.startText)>` would turn the first text block into a function
> called `startText()`, but leave the top-level function name alone.

### untemplates, packages, and imports.

The easiest way to make sense of all this is by example.

My name is `README_md`.

My input type is `immutable.Map[String,Any]`.


