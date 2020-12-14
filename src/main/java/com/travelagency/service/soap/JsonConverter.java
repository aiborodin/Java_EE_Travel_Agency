package com.travelagency.service.soap;

import javax.jws.WebService;

@WebService(targetNamespace = "http://travelagency.com/")
public interface JsonConverter {

    String convertToJson(String className);

}
