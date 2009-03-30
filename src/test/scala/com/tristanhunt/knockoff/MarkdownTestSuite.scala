package com.tristanhunt.knockoff

import org.testng.Assert._
import org.testng.annotations._

/**
 * The markdown test suite, used so I can break down what's supported, what isn't, and be pretty
 * confident everything is how I want it.
 */
@Test
class MarkdownTestSuite {
    
    import xml.Utility.trim
    
    def ampsAndAngleEncoding1 = _compare(
        "AT&T has an ampersand in their name.",
        <p>AT&amp;T has an ampersand in their name.</p>
    )
    
    // TODO Currently failing, because I probably need to capture all variations of entities as
    // HTML Nads.
    def ampsAndAngleEncoding2 = _compare(
        "AT&amp;T is another way to write it.",
        <p>AT&amp;T is another way to write it.</p>
    )
    
    def ampsAndAngleEncoding3 = _compare(
        "This & that.",
        <p>This &amp; that.</p>
    )
    
    def ampsAndAngleEncoding4 = _compare(
        "4 < 5.",
        <p>4 &lt; 5.</p>
    )
    
    def ampsAndAngleEncoding5 = _compare(
        "Here's a [link] [1] with an ampersand in the URL.\n\n[1]: http://example.com/?foo=1&bar=2\n",
        "<p>Here's a <a href=\"http://example.com/?foo=1&amp;bar=2\" >link</a> with an ampersand in the URL.\n</p>"
    )
    
    def ampsAndAngleEncoding6 = _compare(
        "Here's a link with an amersand in the link text: [AT&T] [2].\n\n[2]: http://att.com/  \"AT&T\"",
        "<p>Here's a link with an amersand in the link text: <a href=\"http://att.com/\" title=\"AT&amp;T\">AT&amp;T</a>.\n</p>"
    )
    
    def ampsAndAngleEncoding7 = _compare(
        "Here's an inline [link](/script?foo=1&bar=2).",
        "<p>Here's an inline <a href=\"/script?foo=1&amp;bar=2\" >link</a>.</p>"
    )
    
    def ampsAndAngleEncoding8 = _compare(
        "Here's an inline [link](</script?foo=1&bar=2>).",
        "<p>Here's an inline <a href=\"/script?foo=1&amp;bar=2\" >link</a>.</p>"
    )
    
    def autoLinks1 = _compare(
        "Link: <http://example.com/>.",
        "<p>Link: <a href=\"http://example.com/\" >http://example.com/</a>.</p>"
    )
    
    def autoLinks2 = _compare(
        "With an ampersand: <http://example.com/?foo=1&bar=2>",
        "<p>With an ampersand: <a href=\"http://example.com/?foo=1&amp;bar=2\" >http://example.com/?foo=1&amp;bar=2</a></p>"
    )
    
    def autoLinks3 = _compare(
        "* In a list?\n* <http://example.com/>\n* It should.",
        "<ul>" +
            "<li>In a list?\n</li>" +
            "<li><a href=\"http://example.com/\" >http://example.com/</a>\n</li>" +
            "<li>It should.</li>" +
        "</ul>"
    )
    
    def autoLinks4 = _compare(
        "> Blockquoted: <http://example.com/>",
        "<blockquote>" +
              "<p>Blockquoted: <a href=\"http://example.com/\" >http://example.com/</a></p>" +
        "</blockquote>"
    )
    
    def autoLinks5 = _compare(
        "Auto-links should not occur here: `<http://example.com/>`",
        <p>Auto-links should not occur here: <code>&lt;http://example.com/&gt;</code></p>
    )

    def autoLinks6 = _compare(
        "	or here: <http://example.com/>",
        "<pre><code>or here: &lt;http://example.com/&gt;</code></pre>"
    )
    
    def backslashEscapes1Backslash = _compare(
        """Backslash: \\""",
        """<p>Backslash: \</p>"""
    )
        
    def backslashEscapes2Backtick = _compare(
        """Backtick: \`""",
        """<p>Backtick: `</p>"""
    )

    def backslashEscapesAsterisk = _compare(
		"""Asterisk: \*""",
		"""<p>Asterisk: *</p>"""
	)

    def backslashEscapesUnderscore = _compare(
		"""Underscore: \_""",
		"""<p>Underscore: _</p>"""
	)

    def backslashEscapesLeftbrace = _compare(
		"""Left brace: \{""",
		"""<p>Left brace: {</p>"""
	)

    def backslashEscapesRightbrace = _compare(
		"""Right brace: \}""",
		"""<p>Right brace: }</p>"""
	)

    def backslashEscapesLeftbracket = _compare(
		"""Left bracket: \[""",
		"""<p>Left bracket: [</p>"""
	)

    def backslashEscapesRightbracket = _compare(
		"""Right bracket: \]""",
		"""<p>Right bracket: ]</p>"""
	)

    def backslashEscapesLeftparen = _compare(
		"""Left paren: \(""",
		"""<p>Left paren: (</p>"""
	)

    def backslashEscapesRightparen = _compare(
		"""Right paren: \)""",
		"""<p>Right paren: )</p>"""
	)

    /**
     * This is an intentional break from the Markdown Test Suite. I have no clue why we would want
     * to ever write a normal > symbol in XHTML. I think this was mostly to provide escaping 
     * control, and that's what it achieves.
     *
     * Of course, I could be wrong. We'll see.
     */
    def backslashEscapesGreaterThan = _compare(
		"""Greater-than: \>""",
		"""<p>Greater-than: &gt;</p>"""
	)

    def backslashEscapesHash = _compare(
		"""Hash: \#""",
		"""<p>Hash: #</p>"""
	)

    def backslashEscapesPeriod = _compare(
		"""Period: \.""",
		"""<p>Period: .</p>"""
	)

    def backslashEscapesBang = _compare(
		"""Bang: \!""",
		"""<p>Bang: !</p>"""
	)

    def backslashEscapesPlus = _compare(
		"""Plus: \+""",
		"""<p>Plus: +</p>"""
	)

    def backslashEscapesMinus = _compare(
		"""Minus: \-""",
		"""<p>Minus: -</p>"""
	)
    
    def backslashEscapesCodeBlock = _compare(
        MarkdownExamples.backslashEscapesCodeBlock,
        ConversionExamples.backslashEscapesCodeBlock
    )
    
    def backslashEscapesCodeSpans = _compare(
        MarkdownExamples.backslashEscapesCodeSpans,
        ConversionExamples.backslashEscapesCodeSpans
    )
    
    /**
     * I'm not sure why the official test suite did not entitize the quotes.
     */
    def blockquotesWithCodeBlocks = _compare(
        
        "> Example:\n" +
        "> \n" +
        ">     sub status {\n" +
        ">         print \"working\";\n" +
        ">     }\n" +
        "> \n" +
        "> Or:\n" +
        "> \n" +
        ">     sub status {\n" +
        ">         return \"working\";\n" +
        ">     }",
        
        "<blockquote>" +
            "<p>Example:\n</p>" +
            "<pre><code>sub status {\n" +
            "    print &quot;working&quot;;\n" +
            "}\n" +
            "</code></pre>" +
            "<p>Or:\n</p>" +
            "<pre><code>sub status {\n" +
            "    return &quot;working&quot;;\n" +
            "}" +
            "</code></pre>" +
        "</blockquote>"
    )
    
    def strongAndEmTogether = {
        
        _compare(
            "***This is strong and em.***",
            "<p><strong><em>This is strong and em.</em></strong></p>"
        )
        
        _compare(
            "So is ***this*** word.",
            "<p>So is <strong><em>this</em></strong> word.</p>"
        )
        
        _compare(
            "___This is strong and em.___",
            "<p><strong><em>This is strong and em.</em></strong></p>"
        )
        
        _compare(
            "So is ___this___ word.",
            "<p>So is <strong><em>this</em></strong> word.</p>"
        )
    }
    
    def tabs = _compare(
        MarkdownExamples.tabs,
        ConversionExamples.tabs
    )

    /**
     * Doing a couple of tricks so that each of the tests are just a bit easier to read.
     */
    private def _compare(markdown:String, node:xml.Node) {
     
        assertEquals({<div>{KnockOff.convert(markdown).get}</div>}.toString,
            {<div>{node}</div>}.toString)
    }
    
    private def _compare(markdown:String, expected:String) {
        
        assertEquals({<div>{KnockOff.convert(markdown).get}</div>}.toString,
            "<div>" + expected + "</div>")
    }
}


/**
 * Scala's multiline strings are a little weak, and can be hard to read when tossed in the middle
 * of code. This contains a few "documents" placed inline.
 */
object MarkdownExamples {
    
    val backslashEscapesCodeBlock = """These should not, because they occur within a code block:

    Backslash: \\
    
    Backtick: \`
    
    Asterisk: \*
    
    Underscore: \_
    
    Left brace: \{
    
    Right brace: \}
    
    Left bracket: \[
    
    Right bracket: \]
    
    Left paren: \(
    
    Right paren: \)
    
    Greater-than: \>
    
    Hash: \#
    
    Period: \.
    
    Bang: \!
    
    Plus: \+
    
    Minus: \-
"""

    val backslashEscapesCodeSpans = """Nor should these, which occur in code spans:

Backslash: `\\`

Backtick: `` \` ``

Asterisk: `\*`

Underscore: `\_`

Left brace: `\{`

Right brace: `\}`

Left bracket: `\[`

Right bracket: `\]`

Left paren: `\(`

Right paren: `\)`

Greater-than: `\>`

Hash: `\#`

Period: `\.`

Bang: `\!`

Plus: `\+`

Minus: `\-`
"""

    val tabs = """+	this is a list item
	indented with tabs

+   this is a list item
    indented with spaces

Code:

	this code block is indented by one tab

And:

		this code block is indented by two tabs

And:

	+   this is an example list item
		indented with tabs
    
	+   this is an example list item
	    indented with spaces
"""
}

/**
 * Markdown conversion examples stored inline. Note that these examples are altered to match the
 * current Knockoff expectations, which may be a bit different from the official suite.
 */
object ConversionExamples {
 
    val backslashEscapesCodeBlock = """<p>These should not, because they occur within a code block:
</p><pre><code>Backslash: \\

Backtick: \`

Asterisk: \*

Underscore: \_

Left brace: \{

Right brace: \}

Left bracket: \[

Right bracket: \]

Left paren: \(

Right paren: \)

Greater-than: \&gt;

Hash: \#

Period: \.

Bang: \!

Plus: \+

Minus: \-
</code></pre>"""

    val backslashEscapesCodeSpans = """<p>Nor should these, which occur in code spans:
</p><p>Backslash: <code>\\</code>
</p><p>Backtick: <code> \` </code>
</p><p>Asterisk: <code>\*</code>
</p><p>Underscore: <code>\_</code>
</p><p>Left brace: <code>\{</code>
</p><p>Right brace: <code>\}</code>
</p><p>Left bracket: <code>\[</code>
</p><p>Right bracket: <code>\]</code>
</p><p>Left paren: <code>\(</code>
</p><p>Right paren: <code>\)</code>
</p><p>Greater-than: <code>\&gt;</code>
</p><p>Hash: <code>\#</code>
</p><p>Period: <code>\.</code>
</p><p>Bang: <code>\!</code>
</p><p>Plus: <code>\+</code>
</p><p>Minus: <code>\-</code>
</p>"""

    val tabs = """<ul><li>this is a list item
    indented with tabs
</li><li>this is a list item
    indented with spaces
</li></ul><p>Code:
</p><pre><code>this code block is indented by one tab
</code></pre><p>And:
</p><pre><code>    this code block is indented by two tabs
</code></pre><p>And:
</p><pre><code>+   this is an example list item
    indented with tabs

+   this is an example list item
    indented with spaces
</code></pre>"""
}
