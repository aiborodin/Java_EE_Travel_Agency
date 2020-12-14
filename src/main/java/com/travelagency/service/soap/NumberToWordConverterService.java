package com.travelagency.service.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(
        serviceName = "ConverterService",
        endpointInterface = "com.travelagency.service.soap.NumberConverter",
        targetNamespace = "http://travelagency.com/"
)
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class NumberToWordConverterService {

    private final String[] words = {
            " one",
            " two",
            " three",
            " four",
            " five",
            " six",
            " seven",
            " eight",
            " nine",
            " ten",
            " eleven",
            " twelve",
            " thirteen",
            " fourteen",
            " fifteen",
            " sixteen",
            " seventeen",
            " eighteen",
            " nineteen",
            "twenty"
    };

    @WebResult(name = "numberName")
    @WebMethod
    public String convertToWord(@WebParam(name = "number", mode = WebParam.Mode.IN) int number) throws NumberConverterException {
        if (number < 1 || number > 20) {
            throw new NumberConverterException("Number must be between 1 and 20");
        } else {
            return words[number-1];
        }
    }

}
