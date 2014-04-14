package com.github.t1.html;

public class LI extends Tag {
    LI(StringBuilder out) {
        super(out, "li");
    }

    public A a() {
        open();
        return new A(out);
    }
}
