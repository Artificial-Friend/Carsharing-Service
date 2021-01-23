<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Start page</title>
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
<h1>Menu</h1>

<p><a class="center" href="${pageContext.request.contextPath}/injectData">Inject mock data</a>
    <br>
    <a href="${pageContext.request.contextPath}/login">Login</a>
    <br>
    <a href="${pageContext.request.contextPath}/drivers/add">Register</a>
    <br>
    <a href="${pageContext.request.contextPath}/cars">Cars page</a>
    <br>
    <a href="${pageContext.request.contextPath}/drivers">Drivers page</a>
    <br>
    <a href="${pageContext.request.contextPath}/drivers/cars">Get all your cars</a>
    <br>
    <a href="${pageContext.request.contextPath}/manufacturers">Manufacturers page</a></p>


</body>
</html>
