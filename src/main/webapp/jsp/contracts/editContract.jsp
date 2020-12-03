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
    <title>Edit Contract</title>
</head>
<body>
<h1>Edit Contract Form</h1>

<c:set var="contractId" value="${param.id}"/>
<c:set var="contract" value="${contractService.find(contractId).get()}"/>

<form action="${pageContext.request.contextPath}/contractOperation?operationType=edit" method="post">
    <input type="hidden" name="id" value="${contractId}"/>
    <table>
        <tr>
            <td>Client</td>
            <td>
                <select name="client" required>
                    <c:forEach items="${clientService.all}" var="client">
                        <option value="${client.id}">${client.firstName} ${client.lastName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>

            <td>Agent</td>
            <td>
                <select name="agent" required>
                    <c:forEach items="${agentService.all}" var="agent">
                        <option value="${agent.id}">${agent.firstName} ${agent.lastName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Travel Offer</td>
            <td>
                <select name="travelOffer" required>
                    <c:forEach items="${travelOfferService.all}" var="travelOffer">
                        <option value="${travelOffer.id}">${travelOffer.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Travel Days</td>
            <td><input type="text" name="travelDays" value="${contract.travelDays}" required/></td>
        </tr>
        <tr>
            <td>Transportation Costs</td>
            <td><input type="text" name="transpCosts" value="${contract.transportationCosts}" required/></td>
        </tr>
        <tr>
            <td>Visa Costs</td>
            <td><input type="text" name="visaCosts" value="${contract.totalVisaCosts}" required></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Edit Contract"/></td>
        </tr>
    </table>
</form>
</body>
</html>
