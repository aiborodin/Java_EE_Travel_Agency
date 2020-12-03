<%@ page import="com.travelagency.entity.User" %>
<%@ page import="com.travelagency.entity.Agent" %>
<%@ page import="com.travelagency.entity.Admin" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table_styles.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<%
    User user = (User) session.getAttribute("validUser");
    if (user == null) {
        request.setAttribute("requestedPage", request.getRequestURI());
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp?requestedPage=" + request.getRequestURI().substring(request.getContextPath().length()));
        rd.forward(request, response);
    } else if (!(user instanceof Agent) && !(user instanceof Admin)) {
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/error/accessDenied.jsp");
        rd.forward(request, response);
    }
%>
<html>
<head>
    <title>Contracts</title>
</head>
<body>
<div class="container">

    <label for="filters">Search by</label>
    <select id="filters" class="custom-select">
        <option value="client">Client</option>
        <option value="agent">Agent</option>
        <option value="travelOffer">Travel Offer</option>
    </select>
    <input class="form-control" type="search" placeholder="Search" aria-label="Search" id="input" onkeyup="filter()">

    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2>Manage <b>Contracts</b></h2>
                </div>
                <div class="col-sm-6">
                    <a href="${pageContext.request.contextPath}/jsp/contracts/addContract.jsp" class="btn btn-success"><i
                            class="material-icons">&#xE147;</i>
                        <span>Add New Contract</span></a>
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover" id="contractsTable">
            <thead>
            <tr>
                <th>Client</th>
                <th>Agent</th>
                <th>Travel Offer</th>
                <th>Days</th>
                <th>Transportation Costs</th>
                <th>Visa Costs</th>
                <th>Total Price</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${contractService.all}" var="contract" >
                <tr>
                    <c:set var="clientId" value="${contract.client.id}"/>
                    <c:set var="agentId" value="${contract.agent.id}"/>
                    <c:set var="offerId" value="${contract.travelOffer.id}"/>

                    <td>${clientService.find(clientId).get().getFirstName()} ${clientService.find(clientId).get().getLastName()}</td>
                    <td>${agentService.find(agentId).get().getFirstName()} ${agentService.find(agentId).get().getLastName()}</td>
                    <td>${travelOfferService.find(offerId).get().getName()}</td>
                    <td>${contract.travelDays}</td>
                    <td>${contract.transportationCosts}</td>
                    <td>${contract.totalVisaCosts}</td>
                    <c:set var="dayPrice" value="${travelOfferService.find(offerId).get().getDayPrice()}"/>
                    <td>${ String.format("%.2f", (contract.travelDays * dayPrice + contract.transportationCosts + contract.totalVisaCosts) * 1.2)}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/jsp/contracts/editContract.jsp?id=${contract.id}"
                           class="edit" data-toggle="modal">
                            <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>
                        </a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/contractOperation?operationType=delete&id=${contract.id}"
                           class="delete" data-toggle="modal">
                            <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>

<script>
    function filter() {
        let input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("input");
        filter = input.value.toUpperCase();
        table = document.getElementById("contractsTable");
        tr = table.getElementsByTagName("tr");
        let filterCriteriaIdx;
        switch (document.getElementById("filters").value) {
            case 'client':
                filterCriteriaIdx = 0;
                break;
            case 'agent':
                filterCriteriaIdx = 1;
                break;
            case 'travelOffer':
                filterCriteriaIdx = 2;
                break;
        }
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[filterCriteriaIdx];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
</script>
