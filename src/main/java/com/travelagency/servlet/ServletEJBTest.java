package com.travelagency.servlet;

import com.travelagency.ejb.TravelGroupEJB;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@WebServlet(name = "ServletEJBTest", urlPatterns = "/test1")
public class ServletEJBTest extends HttpServlet {

    @EJB
    TravelGroupEJB travelGroup;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        travelGroup.addMember(1);
        travelGroup.addMember(4);
        travelGroup.setTravelOffer(3);
        travelGroup.setAgent(3);

        Future<Double> averageAge = travelGroup.calculateAverageGroupAge();
        while (!averageAge.isDone()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            out.println("<h1>Average age: "+ averageAge.get() + "</h1>");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        travelGroup.checkOut();

    }
}
