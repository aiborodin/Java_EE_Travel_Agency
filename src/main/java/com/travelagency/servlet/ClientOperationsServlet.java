package com.travelagency.servlet;

import com.travelagency.entity.Client;
import com.travelagency.service.interfaces.ClientService;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@WebServlet(name = "ClientOperationsServlet", urlPatterns = "/clientOperation")
public class ClientOperationsServlet extends HttpServlet {

    @Inject
    ClientService clientService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operationType");
        try {
            switch (operation) {
                case "edit":
                    int clientId = Integer.parseInt(request.getParameter("id"));
                    Client oldClient = clientService.find(clientId).get();
                    clientService.update(
                            new Client(
                                    oldClient.getLogin(),
                                    oldClient.getPassword(),
                                    oldClient.getId(),
                                    request.getParameter("firstName"),
                                    request.getParameter("lastName"),
                                    Integer.parseInt(request.getParameter("age")),
                                    request.getParameter("phone"),
                                    request.getParameter("email"),
                                    LocalDate.parse(request.getParameter("customer_from"))
                            )
                    );
                    break;
                case "delete":
                    clientId = Integer.parseInt(request.getParameter("id"));
                    clientService.delete(clientId);
                    break;
                case "add":
                    clientService.add(
                            new Client(
                                    request.getParameter("login"),
                                    request.getParameter("password"),
                                    0,
                                    request.getParameter("firstName"),
                                    request.getParameter("lastName"),
                                    Integer.parseInt(request.getParameter("age")),
                                    request.getParameter("phone"),
                                    request.getParameter("email"),
                                    LocalDate.parse(request.getParameter("customer_from"))
                            )
                    );
            }
            response.sendRedirect(request.getContextPath() + "/jsp/clients/clients.jsp");
        }
        catch (EJBException e) {
            Throwable t = e.getCause();
            while ((t != null) && !(t instanceof ConstraintViolationException)) {
                t = t.getCause();
            }
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<div style=\"display: table;\" class=\"alert alert-danger\" role=\"alert\"> Incorrect values entered! </div>");
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/clients/clients.jsp");
            rd.include(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
