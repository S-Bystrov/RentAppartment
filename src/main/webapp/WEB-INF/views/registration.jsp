<%--
  Created by IntelliJ IDEA.
  User: Сергей
  Date: 28.03.2020
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
Enter yor information
<form action="/registration" method="POST">
    <label>login</label></br>
    <input type="text" name="login" minlength="1"></br>
    <label>email</label></br>
    <input type="text" name="email" minlength="1"></br>
    <label>password</label></br>
    <input type="password" name="password" minlength="1"></br>
    <label>confirm password</label></br>
    <input type="password" name="confirmPassword" minlength="1"></br>
    <input type="submit" value="Ok">
</form>
</body>
</html>
