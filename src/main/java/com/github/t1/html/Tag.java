package com.github.t1.html;

import java.text.*;
import java.util.*;

class Tag implements AutoCloseable {
    protected final StringBuilder out;
    private Map<String, String> attributes;
    private final String type;
    private boolean open = false;

    public Tag(StringBuilder out, String type) {
        this.out = out;
        this.type = type;
    }

    public void attribute(String name, String value) {
        if (attributes == null)
            attributes = new LinkedHashMap<>();
        attributes.put(name, value);
    }

    public void open() {
        if (!open) {
            open = true;
            appendOpen();
        }
    }

    protected void appendOpen() {
        out.append("<").append(type);
        appendAttributes();
        out.append(">");
    }

    private void appendAttributes() {
        if (attributes != null) {
            for (Map.Entry<String, String> attribute : attributes.entrySet()) {
                out.append(" ").append(attribute.getKey());
                out.append("=\"").append(attribute.getValue()).append("\"");
            }
        }
    }

    public Tag println(String string) {
        return print(string).println();
    }

    public Tag println() {
        return print("\n");
    }

    public Tag print(String string) {
        open();
        out.append(string);
        return this;
    }


    public void printLiteral(String text) {
        StringCharacterIterator iter = new StringCharacterIterator(text);
        boolean newline = true;
        for (char c = iter.first(); c != CharacterIterator.DONE; c = iter.next()) {
            if (newline) {
                if (c == ' ') {
                    out.append("&nbsp;");
                } else if (c == '\t') {
                    out.append("&nbsp;&nbsp;");
                } else if (c != '\n') {
                    newline = false;
                }
            } else {
                if (c == '\n') {
                    out.append("<br/>");
                    newline = true;
                }
            }
            if (c == '&') {
                out.append("&amp;");
            } else if (c == '<') {
                out.append("&lt;");
            } else if (c == '>') {
                out.append("&gt;");
            } else {
                out.append(c);
            }
        }
    }

    @Override
    public void close() {
        if (open) {
            out.append("</").append(type).append(">\n");
        } else {
            out.append("<").append(type);
            appendAttributes();
            out.append("/>\n");
        }
    }

    @Override
    public String toString() {
        close();
        return out.toString();
    }
}
