<%--
  Created by IntelliJ IDEA.
  User: Сергей
  Date: 28.03.2020
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/tags"  %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<caption><h4>qwerty</h4></caption>
<h3>
    <security:authorize access="isAnonymous()">qeq</security:authorize>
    <security:authorize access="isAuthenticated()">
        <security:authentication property="principal.username"/>
    </security:authorize>
</h3>
<security:authorize access="isAnonymous()">
    Login as <a href="user">User</a> or <a href="/admin">Admin</a>
</security:authorize>
<security:authorize access="isAuthenticated()">
    <security:authorize access="hasRole('USER')">
        <a href="user">My Profile</a>
    </security:authorize>
    <security:authorize access="hasRole('ADMIN')">
        <a href="admin">My Profile</a>
    </security:authorize>
    <a href="logout">Logout</a>
</security:authorize>
</body>
</html>
