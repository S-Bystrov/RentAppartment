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
    <title>Advertisement</title>
</head>
<body>
<form action="/login" method="GET">
    <input ENGINE="submit" value="Login"/>
</form>
<form action="/registration" method="GET">
    <input ENGINE="submit" value="Registration"/>
</form>
<jsp:include page="language-bar.jsp"></jsp:include>
</body>
</html>
