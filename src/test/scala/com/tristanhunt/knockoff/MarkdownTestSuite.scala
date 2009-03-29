package com.tristanhunt.knockoff

import org.testng.Assert._
import org.testng.annotations._

/**
 * The markdown test suite, used so I can break down what's supported, what isn't, and be pretty
 * confident everything is how I want it.
 */
@Test
class MarkdownTestSuite {
    
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