<%--
  Created by IntelliJ IDEA.
  User: xdj
  Date: 2017/4/17
  Time: 下午11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="lib/js/jquery.min.js"></script>
    <script type="text/javascript">
        function login() {
            var username = $('#username').val();
            var password = $('#password').val();
            if (username === null || username === '') {
                alert("用户名不能为空");
                return;
            } else if (password === null || password === '') {
                alert("密码不能为空");
                return;
            }

            $.ajax({
                url: "login.do",
                data: {
                    username: username,
                    password: password
                },
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                success: function (data) {
                    alert(data);
                }
            });

            $.post("login.do",
                {
                    username: username,
                    password: password
                },
                function (data) {
                    var result = JSON.parse(data);
                    if (result.code === 0) {
                        window.location.href = "login.form";
                    } else {
                        alert(result.msg);
                    }
                })
        }
    </script>
    <title>登录</title>
</head>
<body>
<form action="login.form" method="get">
    Username:<input id="username" name="username" type="text"><br>
    Password:<input id="password" name="password" type="password"><br>
    <input id="btn" type="button" value="登录" onclick="login()">
</form>
</body>
</html>
