package com.travelagency.servlet;

import com.travelagency.entity.Client;
import com.travelagency.service.interfaces.ClientService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "ClientOperationsServlet", urlPatterns = "/clientOperation")
public class ClientOperationsServlet extends HttpServlet {

    @Inject
    ClientService clientService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operationType");
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
                                -1,
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
