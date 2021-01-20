<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Provide company's name and country</h1>

<form method="post" action="${pageContext.request.contextPath}/manufacturers/add">
    Company's name: <input type="text" name="name">
    Country: <input type="text" name="country">
    <button type="submit">add</button>
</form>
</body>
</html>
