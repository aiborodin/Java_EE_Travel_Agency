package com.travelagency.servlet;

import com.travelagency.entity.Client;
import com.travelagency.entity.Contract;
import com.travelagency.service.interfaces.AgentService;
import com.travelagency.service.interfaces.ClientService;
import com.travelagency.service.interfaces.ContractService;
import com.travelagency.service.interfaces.TravelOfferService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "ContractOperationServlet", urlPatterns = "/contractOperation")
public class ContractOperationsServlet extends HttpServlet {

    @Inject
    ContractService contractService;

    @Inject
    ClientService clientService;

    @Inject
    AgentService agentService;

    @Inject
    TravelOfferService travelOfferService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operationType");
        switch (operation) {
            case "edit":
                int contractId = Integer.parseInt(request.getParameter("id"));
                Contract oldContract = contractService.find(contractId).get();
                contractService.update(
                        new Contract(
                                oldContract.getId(),
                                clientService.find(Integer.parseInt(request.getParameter("client"))).get(),
                                agentService.find(Integer.parseInt(request.getParameter("agent"))).get(),
                                travelOfferService.find(Integer.parseInt(request.getParameter("travelOffer"))).get(),
                                Integer.parseInt(request.getParameter("travelDays")),
                                Double.parseDouble(request.getParameter("transpCosts")),
                                Double.parseDouble(request.getParameter("visaCosts"))
                        )
                );
                break;
            case "delete":
                contractId = Integer.parseInt(request.getParameter("id"));
                contractService.delete(contractId);
                break;
            case "add":
                contractService.add(
                        new Contract(
                                -1,
                                clientService.find(Integer.parseInt(request.getParameter("client"))).get(),
                                agentService.find(Integer.parseInt(request.getParameter("agent"))).get(),
                                travelOfferService.find(Integer.parseInt(request.getParameter("travelOffer"))).get(),
                                Integer.parseInt(request.getParameter("travelDays")),
                                Double.parseDouble(request.getParameter("transpCosts")),
                                Double.parseDouble(request.getParameter("visaCosts"))
                        )
                );
        }
        request.getRequestDispatcher("/jsp/contracts/contracts.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

