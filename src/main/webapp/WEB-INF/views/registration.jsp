<%--
  Created by IntelliJ IDEA.
  User: Сергей
  Date: 28.03.2020
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <title>Registration</title>
</head>
<body>
Enter yor information
<form action="#" th:action="@{/registration}" th:object="${userDTO}" method="POST">
    <label>login</label></br>
    <input type="text" th:field="*{username}" id="username" placeholder="username"></br>
    <label>email</label></br>
    <input type="text" th:field="*{email}" id="email" placeholder="email"></br>
    <label>password</label></br>
    <input type="password" th:field="*{password}" id="password" placeholder="password"></br>
    <label>confirm password</label></br>
    <input type="password" th:field="*{confirmPassword}" id="confirmPassword" placeholder="confirm password"></br>
    <input type="submit" value="Ok">
</form>

</body>
</html>
