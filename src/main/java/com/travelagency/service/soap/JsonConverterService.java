package com.travelagency.service.soap;

import com.google.gson.Gson;
import com.mysql.cj.xdevapi.JsonArray;
import com.travelagency.entity.Identifiable;
import com.travelagency.service.interfaces.AgentService;
import com.travelagency.service.interfaces.ClientService;
import com.travelagency.service.interfaces.ContractService;
import com.travelagency.service.interfaces.TravelOfferService;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@WebService(
        serviceName = "JsonConverterService",
        endpointInterface = "com.travelagency.service.soap.JsonConverter",
        targetNamespace = "http://travelagency.com/"
)
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class JsonConverterService implements JsonConverter{

    @EJB
    ClientService clientService;

    @EJB
    AgentService agentService;

    @EJB
    TravelOfferService travelOfferService;

    @EJB
    ContractService contractService;

    @WebResult(name = "json")
    @WebMethod
    public String convertToJson(@WebParam(name = "className", mode = WebParam.Mode.IN) String className) {

        List<? extends Identifiable> entities;
        Gson gson = new Gson();

        switch (className) {
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
