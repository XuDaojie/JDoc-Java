<%--
  Created by IntelliJ IDEA.
  User: xdj
  Date: 2017/4/18
  Time: 上午12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Greeting</title>
</head>
<body>
<%
    String name = (String) request.getAttribute("name");
%>
Hello <%=name%>!<br>
<%-- ${param_id}直接获取参数中的某个id --%>
Hello ${name}!
<h1 id="hello"></h1>
<a href="greeting.jsp?parameter=中文">中文参数</a>
<script src="lib/js/jquery.min.js"></script>
<script type="text/javascript">
    $.get("greeting.do", function (data, status) {
        $("h1").html(data);
    });
</script>
</body>
</html>
