<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding Driver</title>
    <style>
        h1 {
            text-align: center;
        }

        p {
            text-align: center;
        }
    </style>
</head>
<body>
<h1>Provide your name, license number, login and password</h1>

<form method="post" action="${pageContext.request.contextPath}/drivers/add">
    <p>
        Driver's name: <input required type="text" name="name">
        Driver's license number: <input required type="text" name="licenseNumber">
        <br>
        Driver's login: <input required type="text" name="login">
        Driver's password: <input required type="password" name="password">
        <br>
        <button type="submit">Register</button>
        <a href="${pageContext.request.contextPath}/">
            <button type="button">Return</button>
        </a>
    </p>
</form>
</body>
</html>
