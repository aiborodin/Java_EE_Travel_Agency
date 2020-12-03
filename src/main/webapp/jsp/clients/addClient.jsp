<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 03.10.2020
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Client</title>
</head>
<body>
<h1>Add Client Form</h1>
<form action="${pageContext.request.contextPath}/clientOperation?operationType=add" method="post">
    <table>
        <tr>
            <td>Login:</td>
            <td><input type="text" name="login" required/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td>First Name:</td>
            <td><input type="text" name="firstName" required/></td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><input type="text" name="lastName" required/></td>
        </tr>
        <tr>
            <td>Age:</td>
            <td><input type="number" name="age"/></td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><input type="tel" name="phone"/></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="email" name="email"/></td>
        </tr>
        <tr>
            <td>Customer from:</td>
            <td><input type="date" id="customer_from" name="customer_from"></td>
        </tr>
        <tr><td colspan="2"><input type="submit"/></td></tr>
    </table>
</form>
</body>
</html>
