<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding Driver to Car</title>
</head>
<body>
<h1>Provide your name and license number then car id</h1>

<form method="post" action="${pageContext.request.contextPath}/cars/drivers/add">
    Driver's name: <input type="text" name="name">
    Driver's license number: <input type="text" name="licenseNumber">
    <br>
    Car's id: <input type="number" name="car_id">
    <button type="submit">add</button>
</form>
</body>
</html>
