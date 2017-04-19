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
    Long projectId = null;
    Long moduleId = null;
    // todo 新增和编辑获取项目id方式待统一
    if (request.getParameter("project_id") != null) {
        projectId = Long.parseLong(request.getParameter("project_id"));
    }
    if (request.getParameter("module_id") != null) {
        moduleId = Long.parseLong(request.getParameter("module_id"));
    }
    Long id = null;
    String name = "";
    String markdown = "";
    MarkdownModel markdownModel = (MarkdownModel) request.getAttribute("markdown");
    if (markdownModel != null) {
        projectId = markdownModel.getProjectId();
        moduleId = markdownModel.getModuleId();
        id = markdownModel.getId();
        name = markdownModel.getName();
        markdown = markdownModel.getContent();
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
        var markdownId = <%=id%>;
        var moduleId = <%=moduleId%>;
        var formAction = '<%=action%>';

        function submitMarkdown() {
            var mdName = $("#name").val();
            var mdContent = editor.getMarkdown();
            if (!mdName || mdName === '') {
                alert('接口名不能为空');
                return;
            }
            if (!mdContent || mdContent === '') {
                alert('Markdown不能为空');
                return;
            }

            $.post(formAction,
                {
                    id: markdownId,
                    project_id: <%=projectId%>,
                    module_id: moduleId,
                    name: mdName,
                    markdown: mdContent
                },
                function (data, status) {
                    var result = JSON.parse(data);
                    alert(result.msg);
                    // 新建页面保存成功后变为编辑
                    if(result.code === 0 && formAction !== 'save_markdown.do') {
                        formAction = 'save_markdown.do';
                        markdownId = result.id;
                    }
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
