package com.github.t1.html;


public class Html extends Tag {
    public static class BR extends Tag {
        public BR(StringBuilder out) {
            super(out, "br");
        }
    }

    public static class HR extends Tag {
        public HR(StringBuilder out) {
            super(out, "hr");
        }
    }

    public Html(String title) {
        super(new StringBuilder(), "html");
        println("<head>");
        print("<title>").print(title).println("</title>");
        println("</head><body>");
        print("<h1>").print(title).println("</h1>");
    }

    @Override
    public void close() {
        print("</body>");
        super.close();
    }

    public A a() {
        open();
        return new A(out);
    }

    public UL ul() {
        open();
        return new UL(out);
    }

    public void hr() {
        open();
        try (HR hr = new HR(out)) {}
    }

    public B b() {
        open();
        return new B(out);
    }

    public void br() {
        close();
        try (BR br = new BR(out)) {}
    }
}
