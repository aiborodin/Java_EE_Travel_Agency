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
    <title>Edit Travel Offer</title>
</head>
<body>
<h2>Add Travel Offer</h2>

<form action="${pageContext.request.contextPath}/offerOperation?operationType=add" method="post">
    <table>
        <tr>
            <td>Offer name</td>
            <td>
                <input type="text" name="offerName" required>
            </td>
        </tr>
        <tr>

            <td>Day price</td>
            <td>
                <input type="number" name="visaCosts" required>
            </td>
        </tr>
        <tr>
            <td>Type</td>
            <td>
                <input type="text" name="offerType" required>
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Add Offer"/></td>
        </tr>
    </table>
</form>
</body>
</html>
