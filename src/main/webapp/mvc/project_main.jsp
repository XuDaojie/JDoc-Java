<%@ page import="java.util.List" %>
<%@ page import="io.github.xudaojie.jdoc.model.ModuleModel" %>
<%@ page import="io.github.xudaojie.jdoc.model.MarkdownModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xdj
  Date: 2017/4/18
  Time: 下午2:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${project_name}</title>
</head>
<body>
<%
    List<ModuleModel> modelList = (List<ModuleModel>) request.getAttribute("module_list");
    List<MarkdownModel> nestedItems = (List<MarkdownModel>) request.getAttribute("markdown_list");
%>
${project_name}<br>
-------------------<br>
<a href="create_markdown.form?project_id=<%=request.getParameter("project_id")%>">新建页面</a><br>
----------Module---------<br>
<c:forEach var="item" items="<%=modelList%>">
    <a href="editormd.form?id=${item.id}">${item.name}</a><br>
</c:forEach>
----------Markdown---------<br>
<c:forEach var="item" items="<%=nestedItems%>">
    <a href="editormd.form?id=${item.id}">${item.name}</a><br>
</c:forEach>
</body>
</html>
