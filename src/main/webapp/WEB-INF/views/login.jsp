<%--
  Created by IntelliJ IDEA.
  User: Сергей
  Date: 07.04.2020
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign in</title>
</head>
<body>
<h3>
    <security:authorize access="isAnonymous()">title</security:authorize>
    <security:authorize access="isAuthenticated()">
        <security:authentication property="principal.username"/>
    </security:authorize>
</h3>
<security:authorize access="isAnonymous()">
    <label>login</label></br>
    <input type="text" name="login" minlength="1"></br>
    <label>password</label></br>
    <input type="password" name="password" minlength="1"></br>
    <input type="submit" value="Ok">
</security:authorize>
</body>
</html>
