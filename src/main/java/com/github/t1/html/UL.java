package com.github.t1.html;

public class UL extends Tag {
    UL(StringBuilder out) {
        super(out, "ul");
    }

    @Override
    protected void appendOpen() {
        super.appendOpen();
        println();
    }

    public LI li() {
        open();
        return new LI(out);
    }
}
