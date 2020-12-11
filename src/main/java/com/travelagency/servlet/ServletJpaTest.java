package com.travelagency.servlet;

import com.travelagency.dao.interfaces.AgentDao;
import com.travelagency.dao.interfaces.ContractDao;
import com.travelagency.dao.interfaces.TravelOfferDao;
import com.travelagency.dao.interfaces.annotations.JPA;
import com.travelagency.entity.Contract;
import com.travelagency.entity.OfferType;
import com.travelagency.entity.TravelOffer;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ServletJpaTest", urlPatterns = "/test")
public class ServletJpaTest extends HttpServlet {

    @Inject @JPA
    AgentDao agentDao;

    @Inject @JPA
    TravelOfferDao travelOfferDao;

    @Inject @JPA
    ContractDao contractDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        List<TravelOffer> offers = travelOfferDao.getByType(OfferType.VOLUNTEER);
        offers.forEach(offer -> out.println("<p>" + offer.getName() + "</p>"));
//        List<Contract> contracts = contractDao.findAll();
//        contracts.forEach(contract -> out.println("<p> Agent: " + contract.getAgent().getFirstName() + " Client: " + contract.getClient().getFirstName()));
//        int id = agentDao.persist(new Agent("11s", "11s", 0, "dc", "cdv", true));
//        out.println("<p>" + id + "</p>");
    }
}
