<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Login</title>
    <style><%@include file="/WEB-INF/views/css/login.css"%>
    h1 {
        text-align: center;
    }

    p {
        text-align: center;
    }

    h4 {
        text-align: center;
    }
    </style>

</head>
<div class="login">
    <h1>Login</h1>
    <h4 style="color: red">${errorMsg}</h4>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <p>
            <input placeholder="Username" type="text" required name="login">
            <input placeholder="Password" type="password" required name="password">
            <button class="btn btn-primary btn-block btn-large" type="submit">Login</button>
            <button class="btn btn-primary btn-block btn-large"
                    onclick="window.location.href='${pageContext.request.contextPath}/drivers/add';" type="button">
                Register
            </button>

            <br>
            <button class="btn btn-primary btn-block btn-large"
                    onclick="window.location.href='${pageContext.request.contextPath}/';" type="button">
                Return
            </button>
        </p>
    </form>
</div>
</html>
