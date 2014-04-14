package com.github.t1.html;

import java.net.URI;

public class A extends Tag {
    public A(StringBuilder out) {
        super(out, "a");
    }

    public A href(URI uri) {
        attribute("href", uri.toASCIIString());
        return this;
    }
}
