<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Adding Driver</title>
    <style>
        <%@include file="/WEB-INF/views/css/driverAdd.css"%>
        body {
            /*background-image: url('https://mirkinohd.ru/uploads/thumbs/256427bc0-1.jpg');*/
            background-color: dimgrey;
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: 100% 100%;
        }
    </style>
</head>
<div class="login">
<h1>Register new driver</h1>

<form method="post" action="${pageContext.request.contextPath}/drivers/add">
    <p>
        <input required placeholder="Name" type="text" name="name">
        <input required placeholder="License number" type="text" name="licenseNumber">
        <br>
        <input required placeholder="Login" type="text" name="login">
        <input required placeholder="Password" type="password" name="password">
        <br>
        <button class="btn btn-primary btn-block btn-large" type="submit">Register</button>
        <button class="btn btn-primary btn-block btn-large"
                onclick="window.location.href='${pageContext.request.contextPath}/';" type="button">Return
        </button>
    </p>
</form>
</div>
</html>
