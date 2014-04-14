package com.github.t1.html;

import static java.util.Arrays.*;
import static org.junit.Assert.*;

import java.net.URI;

import org.junit.Test;

public class HtmlTest {

    private final Html html = new Html("the title");

    @Test
    public void shouldWriteHtmlWithBody() {
        html.println("the body");

        assertEquals(html("the body"), html.toString());
    }

    private String html(String body) {
        return "<html><head>\n" //
                + "<title>the title</title>\n" //
                + "</head><body>\n" //
                + "<h1>the title</h1>\n" //
                + body + "\n" //
                + "</body></html>\n";
    }

    @Test
    public void shouldWriteHref() {
        try (A a = html.a().href(URI.create("http://example.com"))) {
            a.print("the label");
        }

        assertEquals(html("<a href=\"http://example.com\">the label</a>"), html.toString());
    }

    @Test
    public void shouldWriteUL() {
        try (UL ul = html.ul()) {
            for (String item : asList("one", "two", "three")) {
                try (LI li = ul.li()) {
                    li.print(item);
                }
            }
        }

        assertEquals(html("<ul>\n<li>one</li>\n<li>two</li>\n<li>three</li>\n</ul>"), html.toString());
    }

    @Test
    public void shouldWriteEmptyTag() {
        try (UL ul = html.ul()) {}

        assertEquals(html("<ul/>"), html.toString());
    }
}
