package com.travelagency.servlet;

import com.travelagency.entity.Agent;
import com.travelagency.entity.Client;
import com.travelagency.entity.User;
import com.travelagency.service.interfaces.AgentService;
import com.travelagency.service.interfaces.ClientService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Inject
    private ClientService clientService;

    @Inject
    private AgentService agentService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String role = request.getParameter("role");
        String login = request.getParameter("username");
        String password = request.getParameter("password");
        Optional<? extends User> userOptional;

        if (role == null) {
            return;
        }

        if (role.equals("client")) {
            userOptional = clientService.authorize(login, password);
        } else {
            userOptional = agentService.authorize(login, password);
        }

        if (userOptional.isPresent()) {
            User validatedUser = userOptional.get();
            request.getSession().setAttribute("validUser", validatedUser);
            String homePage;
            String userName;

            if (validatedUser instanceof Client) {
                homePage = "/jsp/clientMenu.jsp";
                userName = ((Client) validatedUser).getFirstName();
            } else {
                homePage = "/jsp/agentMenu.jsp";
                userName = ((Agent) validatedUser).getFirstName();
            }

            response.addCookie(new Cookie("homePage", homePage));
            response.addCookie(new Cookie("userName", userName));

            String requestedPage = request.getParameter("requestedPage");
            if (requestedPage != null) {
                request.getRequestDispatcher(requestedPage).forward(request, response);
            } else {
                request.getRequestDispatcher(homePage).forward(request, response);
            }
        } else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<h3 color=red>Either user name or password is incorrect!</h3>");
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
            rd.include(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
