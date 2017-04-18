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
    <meta charset="utf-8"/>
    <title>Simple example - Editor.md examples</title>
    <!--<link rel="stylesheet" href="lib/editor.md-v1.5.0/examples/css/style.css" />-->
    <link rel="stylesheet" href="lib/editor.md-v1.5.0/css/editormd.css"/>
    <link rel="shortcut icon" href="https://pandao.github.io/editor.md/favicon.ico"
          type="image/x-icon"/>
    <script type="text/javascript">
        function submitMarkdown() {
            console.log(editor.getMarkdown())
        }
        function insertApiTempl() {
            editor.insertValue("`Template`")
        }
    </script>
</head>
<body>
<input type="button" value="插入接口模板" onclick="insertApiTempl()"><br>
<div id="layout">
    <header>
    </header>
    <div id="editormd">
         <textarea style="display:none;">[TOC]

#### Disabled options

- TeX (Based on KaTeX);
- Emoji;
- Task lists;
- HTML tags decode;
- Flowchart and Sequence Diagram;

#### Editor.md directory

    editor.md/
            lib/
            css/
            scss/
            tests/
            fonts/
            images/
            plugins/
            examples/
            languages/
            editormd.js
            ...

```html
&lt;!-- English --&gt;
&lt;script src="../dist/js/languages/en.js"&gt;&lt;/script&gt;

&lt;!-- 繁體中文 --&gt;
&lt;script src="../dist/js/languages/zh-tw.js"&gt;&lt;/script&gt;
```
</textarea>
    </div>
</div>
<input type="button" onclick="submitMarkdown()" value="Submit">
<script src="lib/editor.md-v1.5.0/examples/js/jquery.min.js"></script>
<script src="lib/editor.md-v1.5.0/editormd.min.js"></script>
<script type="text/javascript">
    var editor;

    $(function () {
        editor = editormd("editormd", {
            width: "90%",
            height: 640,
            syncScrolling: "single",
            path: "lib/editor.md-v1.5.0/lib/"
        });

        /*
         // or
         editor = editormd({
         id      : "test-editormd",
         width   : "90%",
         height  : 640,
         path    : "../lib/"
         });
         */
    });
</script>
</body>
</html>
