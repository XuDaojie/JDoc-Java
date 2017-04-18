<%--
  Created by IntelliJ IDEA.
  User: xdj
  Date: 2017/4/17
  Time: 下午5:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
    <meta charset="utf-8" />
    <title>Simple example - Editor.md examples</title>
    <!--<link rel="stylesheet" href="lib/editor.md-v1.5.0/examples/css/style.css" />-->
    <link rel="stylesheet" href="lib/editor.md-v1.5.0/css/editormd.css" />
    <link rel="shortcut icon" href="https://pandao.github.io/editor.md/favicon.ico" type="image/x-icon" />
    <script type="text/javascript">
        function testSubmit() {
            console.log(testEditor.getMarkdown())
        }
    </script>
</head>
<body>
<div id="layout">
    <header>
    </header>
    <div id="editormd">
    </div>
</div>
<input type="button" onclick="testSubmit()" value="Submit">
<script src="lib/editor.md-v1.5.0/examples/js/jquery.min.js"></script>
<script src="lib/editor.md-v1.5.0/editormd.min.js"></script>
<script type="text/javascript">
    var testEditor;

    $(function() {
        testEditor = editormd("editormd", {
            width   : "90%",
            height  : 640,
            syncScrolling : "single",
            path    : "lib/editor.md-v1.5.0/lib/"
        });

        /*
         // or
         testEditor = editormd({
         id      : "test-editormd",
         width   : "90%",
         height  : 640,
         path    : "../lib/"
         });
         */
    });
</script>
</body>
</html
