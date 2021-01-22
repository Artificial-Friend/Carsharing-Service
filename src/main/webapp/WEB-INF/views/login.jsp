<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        h1 {text-align: center;}
        p {text-align: center;}
        h4 {text-align: center;}
    </style>
</head>
<body>
<h1>Login</h1>
<h4 style="color: red">${errorMsg}</h4>
<form action="${pageContext.request.contextPath}/login" method="post">
    <p>
    Login: <input  type="text" required name="login">
    Password <input type="password" required name="password">
    <button type="submit">Login</button>
        <br>
        <br>
    <a href="${pageContext.request.contextPath}/"><button type="submit">Return</button></a>
        </a>
    <a href="${pageContext.request.contextPath}/drivers/add">Register</a></p>
</form>
</body>
</html>
