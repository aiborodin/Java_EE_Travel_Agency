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
<h1>Edit Travel Offer Form</h1>

<c:set var="offerId" value="${param.id}"/>
<c:set var="travelOffer" value="${travelOfferService.find(offerId).get()}"/>

<form action="${pageContext.request.contextPath}/offerOperation?operationType=edit" method="post">
    <input type="hidden" name="id" value="${offerId}"/>
    <table>
        <tr>
            <td>Offer name</td>
            <td>
                <select name="offerName" required>
                    <c:forEach items="${travelOfferService.all}" var="offer">
                        <option value="${offer.name}">${offer.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Day price</td>
            <td>
                <input type="text" name="dayPrice" value="${travelOffer.dayPrice}" required>
            </td>
        </tr>
        <tr>
            <td>Type</td>
            <td>
                <select name="offerType" required>
                    <c:forEach items="${travelOfferService.getOfferTypes()}" var="type">
                        <option value="${type}">${type}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Edit Contract"/></td>
        </tr>
    </table>
</form>
</body>
</html>
