<%@ page import="com.travelagency.entity.User" %>
<%@ page import="com.travelagency.entity.Agent" %>
<%@ page import="com.travelagency.entity.Admin" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 03.10.2020
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%
    User user = (User) session.getAttribute("validUser");
    if (user == null) {
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
        rd.forward(request, response);
    } else if (!(user instanceof Agent) && !(user instanceof Admin)) {
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/error/accessDenied.jsp");
        rd.forward(request, response);
    }
%>
<html>
<head>
    <title>Edit Client</title>
</head>
<body>
<h1>Edit Client Form</h1>
<c:set var="clientId" value="${param.id}"/>
<c:set var="client" value="${clientService.find(clientId).get()}"/>
<form action="${pageContext.request.contextPath}/clientOperation?operationType=edit" method="post">
    <input type="hidden" name="id" value="${clientId}"/>
    <table>
        <tr>
            <td>First Name:</td>
            <td><input type="text" name="firstName" value="${client.firstName}"/></td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><input type="text" name="lastName" value="${client.lastName}"/></td>
        </tr>
        <tr>
            <td>Age:</td>
            <td><input type="text" name="age" value="${client.age}"/></td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><input type="text" name="phone" value="${client.phone}"/></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="text" name="email" value="${client.email}"/></td>
        </tr>
        <tr>
            <td>Customer from:</td>
            <td><input type="date" id="customer_from" name="customer_from" value="${client.getCustomerFrom().toString()}"></td>
        </tr>
        <tr><td colspan="2"><input type="submit" value="Edit User"/></td></tr>
    </table>
</form>
</body>
</html>
