package com.travelagency.service.soap.client;

import com.travelagency.service.soap.JsonConverter;
import com.travelagency.service.soap.NumberConverter;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws Exception{

        Service convertorService = Service.create(
                new URL("http://localhost:8080/Travel_Agency_war/ConverterService?wsdl"),
                new QName("http://travelagency.com/", "ConverterService")
        );

        NumberConverter numberConverter = convertorService.getPort(NumberConverter.class);
        System.out.println(numberConverter.convertToWord(10));

        Service jsonService = Service.create(
                new URL("http://localhost:8080/Travel_Agency_war/JsonConverterService?wsdl"),
                new QName("http://travelagency.com/", "JsonConverterService")
        );

        JsonConverter jsonConverter = jsonService.getPort(JsonConverter.class);
        System.out.println(jsonConverter.convertToJson("client"));

    }
}
