package com.travelagency.service.rest;

import com.google.gson.Gson;
import com.travelagency.entity.Identifiable;
import com.travelagency.service.interfaces.AgentService;
import com.travelagency.service.interfaces.ClientService;
import com.travelagency.service.interfaces.ContractService;
import com.travelagency.service.interfaces.TravelOfferService;

import javax.ejb.EJB;
import javax.jws.WebParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;

@Path("/RestJsonConverterService")
public class JsonConverterService {

    @EJB
    ClientService clientService;

    @EJB
    AgentService agentService;

    @EJB
    TravelOfferService travelOfferService;

    @EJB
    ContractService contractService;

    @GET
    @Path("{entity}")
    @Produces(MediaType.APPLICATION_JSON)
    public String convertToJson(@PathParam("entity") String entity) {
        List<? extends Identifiable> entities;
        Gson gson = new Gson();

        switch (entity) {
            case "agent":
                entities = agentService.getAll();
                break;
            case "client":
                entities = clientService.getAll();
                break;
            case "travelOffer":
                entities = travelOfferService.getAll();
                break;
            case "contract":
                entities = contractService.getAll();
                break;
            default:
                return null;
        }
        return gson.toJson(entities);
    }

}
