<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding Driver to Car</title>
</head>
<body>
<h1>Provide driver and car id's</h1>

<form method="post" action="${pageContext.request.contextPath}/cars/drivers/add">
    Driver's id: <input type="number" name="driver_id">
    <br>
    Car's id: <input type="number" name="car_id">
    <button type="submit">add</button>
</form>
</body>
</html>
