<%@ page import="com.travelagency.entity.User" %>
<%@ page import="com.travelagency.entity.Agent" %>
<%@ page import="com.travelagency.entity.Admin" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contracts</title>
</head>
<body>
<h3>Travel Offers for Clients</h3>

<%
    User user = (User) session.getAttribute("validUser");
    if (user == null) {
        request.setAttribute("requestedPage", request.getRequestURI());
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp?requestedPage=" + request.getRequestURI().substring(request.getContextPath().length()));
        rd.forward(request, response);
    }
%>
<table id="offersTable" border="1" width="90%">
    <tr class="header">
        <th>Offer Name (Destination)</th>
        <th>Day price</th>
        <th>Offer Type</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${travelOfferService.all}" var="travelOffer">
        <tr>
            <td>${travelOffer.name}</td>
            <td>${travelOffer.dayPrice}</td>
            <td>${travelOffer.offerType}</td>
            <td>
                <a href="${pageContext.request.contextPath}/jsp/travelOffers/editOffer.jsp?id=${travelOffer.id}">Edit</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/offerOperation?operationType=delete&id=${travelOffer.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<label for="filters">Search by</label>
<select id="filters" name="filters">
    <option value="name">Offer Name</option>
    <option value="type">Type</option>
</select>
<input type="text" id="input" onkeyup="filter()">
<br/>
<a href="${pageContext.request.contextPath}/jsp/travelOffers/addOffer.jsp">Add New Travel Offer</a>

</body>
</html>

<script>
    function filter() {
        let input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("input");
        filter = input.value.toUpperCase();
        table = document.getElementById("offersTable");
        tr = table.getElementsByTagName("tr");
        let filterCriteriaIdx;
        switch (document.getElementById("filters").value) {
            case 'name':
                filterCriteriaIdx = 0;
                break;
            case 'type':
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
