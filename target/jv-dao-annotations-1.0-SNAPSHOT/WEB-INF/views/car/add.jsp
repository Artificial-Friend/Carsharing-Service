<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding Driver</title>
    <style>
        h1 {text-align: center;}
        p {text-align: center;}
    </style>
</head>
<body>
<h1>Provide your name and license number</h1>

<form method="post" action="${pageContext.request.contextPath}/cars/add">
    Car's name: <input required type="text" name="model">
    <br>
    Car's manufacturer id: <input required type="number" name="manufacturer_id">
    <br>
    <button type="submit">Add</button>
    <p>
        <a href="${pageContext.request.contextPath}/">
            Return to the main page
        </a>
    </p>
</form>
</body>
</html>
