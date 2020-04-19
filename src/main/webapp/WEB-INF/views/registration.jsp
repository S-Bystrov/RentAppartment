<%--
  Created by IntelliJ IDEA.
  User: Сергей
  Date: 28.03.2020
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Registration</title>
</head>
<body>
<%--Enter yor information
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
</form>--%>
<div>
    <form:form method="POST" modelAttribute="userForm">
        <h2>Регистрация</h2>
        <div>
            <form:input type="text" path="login" placeholder="Login"
                        autofocus="true"></form:input>
            <form:errors path="login"></form:errors>
                ${usernameError}
        </div>
        <div>
            <form:input type="password" path="password" placeholder="Password"></form:input>
        </div>
        <div>
            <form type="password" name="passwordConfirm"
                  placeholder="Confirm your password"></form>
            <form:errors path="password"></form:errors>
                ${passwordError}
        </div>
        <button type="submit">Зарегистрироваться</button>
    </form:form>
    <a href="/">Главная</a>
</div>
</body>
</html>
