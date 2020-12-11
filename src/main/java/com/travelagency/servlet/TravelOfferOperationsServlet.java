package com.travelagency.servlet;

import com.travelagency.entity.Client;
import com.travelagency.entity.OfferType;
import com.travelagency.entity.TravelOffer;
import com.travelagency.service.interfaces.TravelOfferService;

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

@WebServlet(name = "TravelOfferOperationsServlet", urlPatterns = "/offerOperation")
public class TravelOfferOperationsServlet extends HttpServlet {

    @Inject
    TravelOfferService travelOfferService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operationType");
        try {
            switch (operation) {
                case "edit":
                    int offerId = Integer.parseInt(request.getParameter("id"));
                    TravelOffer oldOffer = travelOfferService.find(offerId).get();
                    travelOfferService.update(
                            new TravelOffer(
                                    oldOffer.getId(),
                                    request.getParameter("offerName"),
                                    Double.parseDouble(request.getParameter("dayPrice")),
                                    OfferType.getBy(request.getParameter("offerType"))
                            )
                    );
                    break;
                case "delete":
                    offerId = Integer.parseInt(request.getParameter("id"));
                    travelOfferService.delete(offerId);
                    break;
                case "add":
                    travelOfferService.add(
                            new TravelOffer(
                                    0,
                                    request.getParameter("offerName"),
                                    Double.parseDouble(request.getParameter("dayPrice")),
                                    OfferType.getBy(request.getParameter("offerType"))
                            )
                    );
            }
            response.sendRedirect(request.getContextPath() + "/jsp/travelOffers/offers.jsp");
        }
        catch (EJBException e) {
            Throwable t = e.getCause();
            while ((t != null) && !(t instanceof ConstraintViolationException)) {
                t = t.getCause();
            }
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<div style=\"display: table;\" class=\"alert alert-danger\" role=\"alert\"> Incorrect values entered! </div>");
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/travelOffers/offers.jsp");
            rd.include(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
