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
    <title>Add Contract</title>
</head>
<body>
<h1>Add Contract Form</h1>
<form action="${pageContext.request.contextPath}/contractOperation?operationType=add" method="post">
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
            <td><input type="number" name="travelDays" required/></td>
        </tr>
        <tr>
            <td>Transportation Costs</td>
            <td><input type="number" name="transpCosts" required/></td>
        </tr>
        <tr>
            <td>Visa Costs</td>
            <td><input type="number" name="visaCosts" required></td>
        </tr>
    </table>
    <input type="submit" value="Add Contract">
</form>
</body>
</html>
