<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Provide company's name and country</h1>

<form method="post" action="${pageContext.request.contextPath}/manufacturers/add">
    Company's name: <input required type="text" name="name">
    <br>
    Country: <input required type="text" name="country">
    <br>
    <button type="submit">add</button>
</form>
</body>
</html>
