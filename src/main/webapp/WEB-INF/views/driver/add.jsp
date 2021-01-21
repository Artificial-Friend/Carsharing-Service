<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding Driver</title>
</head>
<body>
    <h1>Provide your name and license number</h1>

<form method="post" action="${pageContext.request.contextPath}/drivers/add">
    Drivers name: <input required type="text" name="name">
    <br>
    Drivers license number: <input required type="text" name="licenseNumber">
    <br>
    <button type="submit">add</button>
</form>
</body>
</html>
