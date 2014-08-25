# html [![Dependency Status](https://www.versioneye.com/user/projects/53fb9440e09da383cd000674/badge.svg?style=flat)](https://www.versioneye.com/user/projects/53fb9440e09da383cd000674)

Very simple writer for html source utilizing AutoClosable.

Example:

	@GET
	@Produces(TEXT_HTML)
	public String get() {
		try (Html html = new Html("Example")) {
			try (A a = html.a().href(URI.create("http://example.com"))) {
				a.print("a link");
			}
			return html.toString();
		}
	}

Produces:

	<html><head>
	<title>Example</title>
	</head><body>
	<h1>Example</h1>
	<a href="http://example.com">a link</a>
	</body></html>
