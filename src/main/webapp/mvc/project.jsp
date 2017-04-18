<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="io.github.xudaojie.jdoc.model.ProjectModel" %><%--
  Created by IntelliJ IDEA.
  User: xdj
  Date: 2017/4/18
  Time: 上午8:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>项目列表</title>
</head>
<body>
<%
    List<ProjectModel> projectModelList = (List<ProjectModel>) request.getAttribute("project_list");
    Long id1 = projectModelList.get(0).getId();
    String project1 = projectModelList.get(0).getName();
    String project2 = projectModelList.get(1).getName();
%>
<a href="create_project.jsp">新建项目</a><br>
--------------
已创建项目<br>
<br>
<c:forEach var="item" items="<%=projectModelList%>">
    <a href="project_main.form?project_id=${item.id}">${item.name}</a><br>
</c:forEach>

</body>
</html>
