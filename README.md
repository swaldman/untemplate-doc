
# Untemplate Docs

This project documents the `untemplate` project. For the code repository, please see [swaldman/untemplate](https://github.com/swaldman/untemplate).

---

Let's look at an untemplate so simple it seems not to be an untemplate at all.

```markdown
# Ceci n'est pas...

Well, this is just a regular markdown file, with no
special untemplate constructs. But if we wish, we can treat
it as an unemplate, and it will be immortalized as a scala
function.
```
It's just a markdown file! But it's stored in an untemplate source directory as `ceci-nest-pas.md.untemplate`, so it gets
compiled to a simple scala function.

```scala
package untemplatedoc

import java.io.{Writer,StringWriter}
import scala.collection.*

// start generator-extras imports
// end generator-extras imports

// start author-defined imports
// end author-defined imports

private object Helper_ceci_nest_pas_md:
  private val BP0 = new Function2[immutable.Map[String,Any],mutable.Map[String,Any],String]:
    def apply( input : immutable.Map[String,Any], scratchpad : mutable.Map[String,Any]) : String =
      "# Ceci n'est pas...\n\nWell, this is just a regular markdown file, with no\nspecial untemplate constructs. But if we wish, we can treat\nit as an unemplate, and it will be immortalized as a scala\nfunction.\n\n"

  val BlockPrinters = Vector( BP0 )

end Helper_ceci_nest_pas_md

def ceci_nest_pas_md(input : immutable.Map[String,Any]) : String =
  import Helper_ceci_nest_pas_md.*

  val scratchpad : mutable.Map[String,Any] = mutable.Map.empty[String,Any]
  val writer = new StringWriter(131072) //XXX: Hardcoded initial capacity

    writer.write(BlockPrinters(0)( input, scratchpad ))
    
  writer.toString
  
end ceci_nest_pas_md
```

We'd like, of course, for our (un)template library to do a bit more than just spit out unmodified
text files though. Let's modify our example just a bit:

```markdown
# Ceci n'est pas... <(math.random)>

Well, this is just a regular markdown file, with no
special untemplate constructs. But if we wish, we can treat
it as an unemplate, and it will be immortalized as a scala
function.
```

Now, the [generated scala](example/scalagen/untemplatedoc/untemplate_ceci_nest_pas2_md.scala) would _transform_ the markdown, like this:

```markdown
# Ceci n'est pas... 0.26618930700872356

Well, this is just a regular markdown file, with no
special untemplate constructs. But if we wish, we can treat
it as an unemplate, and it will be immortalized as a scala
function.


```
