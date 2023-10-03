<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>

<h1>Enter a Number</h1>
<form action="hello-servlet" method="POST">
    <label for="numberInput">Number:</label>
    <input type="number" id="numberInput" name="number" required>
    <br><br>
    <button type="submit">Submit</button>
</form>
</body>
</html>