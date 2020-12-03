package com.travelagency.servlet;

import com.travelagency.entity.Client;
import com.travelagency.entity.TravelOffer;
import com.travelagency.service.interfaces.TravelOfferService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "TravelOfferOperationsServlet", urlPatterns = "/offerOperation")
public class TravelOfferOperationsServlet extends HttpServlet {

    @Inject
    TravelOfferService travelOfferService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operationType");
        switch (operation) {
            case "edit":
                int offerId = Integer.parseInt(request.getParameter("id"));
                TravelOffer oldOffer = travelOfferService.find(offerId).get();
                travelOfferService.update(
                        new TravelOffer(
                                oldOffer.getId(),
                                request.getParameter("offerName"),
                                Double.parseDouble(request.getParameter("dayPrice")),
                                request.getParameter("offerType")
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
                                -1,
                                request.getParameter("offerName"),
                                Double.parseDouble(request.getParameter("dayPrice")),
                                request.getParameter("offerType")
                        )
                );
        }
        response.sendRedirect(request.getContextPath() + "/jsp/travelOffers/offers.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
