package com.travelagency.servlet;

import com.travelagency.entity.Admin;
import com.travelagency.entity.Agent;
import com.travelagency.entity.Client;
import com.travelagency.entity.User;

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

@WebServlet(name = "AdminServlet", urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("username");
        String password = request.getParameter("password");
        Admin admin;

        // Simulates AdminServiceBean
        if (login.equals("admin") && password.equals("admin")) {
            admin = new Admin(1, "admin", "admin");
            request.getSession().setAttribute("validUser", admin);

            String homePage = "/jsp/admin/menu.jsp";
            response.addCookie(new Cookie("homePage", homePage));
            response.addCookie(new Cookie("userName", "Admin"));
            request.getRequestDispatcher(homePage).forward(request, response);
        } else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<h3 color=red>Either user name or password is incorrect!</h3>");
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/admin/index.jsp");
            rd.include(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
