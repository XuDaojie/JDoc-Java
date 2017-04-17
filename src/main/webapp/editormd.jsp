<%--
  Created by IntelliJ IDEA.
  User: xdj
  Date: 2017/4/17
  Time: 下午5:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editor.md</title>
</head>
<body>
<link rel="stylesheet" href="lib/editor.md-v1.5.0/editormd.min.css" />
<div id="editormd">
    <textarea style="display:none;">### Hello Editor.md !</textarea>
</div>
<script src="lib/editor.md-v1.5.0/jquery.min.js"></script>
<script src="lib/editor.md-v1.5.0/editormd.min.js"></script>
<script type="text/javascript">
    $(function() {
        var editor = editormd("editormd", {
            path : "../lib/editor.md-v1.5.0/lib/" // Autoload modules mode, codemirror, marked... dependents libs path
        });

        /*
         // or
         var editor = editormd({
         id   : "editormd",
         path : "../lib/"
         });
         */
    });
</script>
</body>
</html>
