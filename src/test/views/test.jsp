<%@ page import="com.travelagency.entity.User" %>
<%@ page import="com.travelagency.entity.Agent" %>
<%@ page import="com.travelagency.entity.Admin" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.travelagency.entity.Client" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%--%>
<%--    User user = (User) session.getAttribute("validUser");--%>
<%--    if (user == null) {--%>
<%--        RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");--%>
<%--        rd.forward(request, response);--%>
<%--    } else if (!(user instanceof Agent) && !(user instanceof Admin)) {--%>
<%--        RequestDispatcher rd = request.getRequestDispatcher("/jsp/error/accessDenied.jsp");--%>
<%--        rd.forward(request, response);--%>
<%--    }--%>
<%--%>--%>
<html>
<head>
<%--    <meta charset="utf-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <title>Bootstrap CRUD Data Table for Database with Modal Form</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            // Activate tooltip
            $('[data-toggle="tooltip"]').tooltip();

            // Select/Deselect checkboxes
            var checkbox = $('table tbody input[type="checkbox"]');
            $("#selectAll").click(function () {
                if (this.checked) {
                    checkbox.each(function () {
                        this.checked = true;
                    });
                } else {
                    checkbox.each(function () {
                        this.checked = false;
                    });
                }
            });
            checkbox.click(function () {
                if (!this.checked) {
                    $("#selectAll").prop("checked", false);
                }
            });
        });
    </script>
</head>
<body>
<div class="container">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2>Manage <b>Clients</b></h2>
                </div>
                <div class="col-sm-6">
                    <a href="#addClientModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i>
                        <span>Add New Client</span></a>
                    <a href="#deleteClientModal" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i>
                        <span>Delete</span></a>
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>Full Name</th>
                <th>Age</th>
                <th>Phone Number</th>
                <th>Email</th>
                <th>Customer From</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${clientService.all}" var="contract">
                <tr>
                    <td><c:out value="${contract.firstName}"/> <c:out value="${contract.lastName}"/></td>
                    <td><c:out value="${contract.age}"/></td>
                    <td><c:out value="${contract.phone}"/></td>
                    <td><c:out value="${contract.email}"/></td>
                    <td><c:out value="${contract.getCustomerFrom().toString()}"/></td>
                    <td>
                        <!-- Нужно передать параметр id на сервер и получить ответ в виде объекта Client -->
                        <a href="${pageContext.request.contextPath}/clientOperation?id=${contract.id}" class="edit" data-toggle="modal"><i
                                class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                        <a href="?id=${contract.id}#deleteClientModal" data-id="${contract.id}" class="delete" data-toggle="modal"><i
                                class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<!-- Add Modal HTML -->
<div id="addClientModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/clientOperation?operationType=add" method="post">
                <div class="modal-header">
                    <h4 class="modal-title">Add Client</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Login</label>
                        <input type="text" class="form-control" name="login" required>
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="text" class="form-control" name="password" required>
                    </div>
                    <div class="form-group">
                        <label>First Name</label>
                        <input type="text" name="firstName" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Last Name</label>
                        <input type="text" name="lastName" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Age</label>
                        <input type="text" name="age" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Phone</label>
                        <input type="text" name="phone" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type="text" name="email" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Customer from</label>
                        <input type="date" name="customer_from" class="form-control">
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-success" value="Add">
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Edit Modal HTML -->
<div id="editClientModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form>
                <!-- Получить параметр кликнутого объекта от сервера -->
<%--                <c:set var="clientId" value="${param.id}"/>--%>
                <h3><% out.println(request.getParameter("id"));%></h3>
<%--                <c:set var="contract" value="${clientService.find(clientId).get()}"/>--%>
                <div class="modal-header">
                    <h4 class="modal-title">Edit Client</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>First Name</label>
                        <input type="text" name="firstName" class="form-control" value="<c:out value="${contract.firstName}"/>" required>
                    </div>
                    <div class="form-group">
                        <label>Last Name</label>
                        <input type="text" name="lastName" class="form-control" value="<c:out value="${contract.lastName}"/>" required>
                    </div>
                    <div class="form-group">
                        <label>Age</label>
                        <input type="text" name="age" value="<c:out value="${contract.age}"/>" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Phone</label>
                        <input type="text" name="phone" value="<c:out value="${contract.phone}"/>" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type="text" name="email" value="<c:out value="${contract.email}"/>" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Customer from</label>
                        <input type="date" name="customer_from" value="<c:out value="${contract.getCustomerFrom().toString()}"/>"
                               class="form-control">
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-info" value="Save">
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Delete Modal HTML -->
<div id="deleteClientModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form>
                <div class="modal-header">
                    <h4 class="modal-title">Delete Employee</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p>Are you sure you want to delete these Records?</p>
                    <p class="text-warning"><small>This action cannot be undone.</small></p>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-danger" value="Delete">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>