<%@ page import="io.github.xudaojie.jdoc.model.MarkdownModel" %><%--
  Created by IntelliJ IDEA.
  User: xdj
  Date: 2017/4/17
  Time: 下午5:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<%
    String action = "save_markdown.do";
    String projectId = request.getParameter("project_id");
    Long id = null;
    String name = "";
    String markdown = "";
    MarkdownModel markdownModel = (MarkdownModel) request.getAttribute("markdown");
    if (markdownModel != null) {
        markdown = markdownModel.getContent();
        id = markdownModel.getId();
        name = markdownModel.getName();
    } else {
        action = "create_markdown.do";
    }
%>
<head>
    <meta charset="utf-8"/>
    <title>Simple example - Editor.md examples</title>
    <!--<link rel="stylesheet" href="lib/editor.md-v1.5.0/examples/css/style.css" />-->
    <link rel="stylesheet" href="lib/editor.md-v1.5.0/css/editormd.css"/>
    <link rel="shortcut icon" href="https://pandao.github.io/editor.md/favicon.ico"
          type="image/x-icon"/>
    <script type="text/javascript">
        function submitMarkdown() {
            var mdName = $("#name").val();
            var mdContent = editor.getMarkdown();
            if(!mdName || mdName === '') {
                alert('接口名不能为空');
                return;
            }
            if(!mdContent || mdContent === '') {
                alert('Markdown不能为空');
                return;
            }

            $.get('<%=action%>',
                {
                    id:  <%=id%>,
                    project_id: <%=projectId%>,
                    name: mdName,
                    markdown: mdContent
                }
                , function (data, status) {
                    alert(status)
                });
        }
        function insertApiTempl() {
            editor.insertValue("`Template`")
        }
    </script>
</head>
<body>
<input type="button" value="插入接口模板" onclick="insertApiTempl()"><br>
<input id="test" type="button" value="Test插入接口模板"><br>
接口名:<input id="name" name="name" value="<%=name%>"><br>
<div id="layout">
    <header>
    </header>
    <div id="editormd">
        <textarea id="markdown" style="display: none"><%=markdown%></textarea>
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
