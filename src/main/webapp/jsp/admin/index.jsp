<%@ page import="java.util.Optional" %>
<%@ page import="java.util.Arrays" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 01.10.2020
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/global_styles.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<c:if test="${sessionScope.validUser != null}">
    <jsp:forward page="${cookie.homePage.value}"/>
</c:if>
<html>
<head>
    <title>Welcome Admin</title>
</head>
<body>
<div class="wrapper fadeInDown">
    <div id="formContent">
        <form method="post" action="${pageContext.request.contextPath}/admin">
            <input type="text" id="username" name="username" placeholder="Username" required>
            <input type="password" id="password" name="password" placeholder="Password" required>
            <br/><br/>
            <input type="submit" value="Login">
        </form>
    </div>
</div>
</body>
</html>


