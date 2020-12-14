package com.travelagency.service.soap;

import javax.xml.ws.WebFault;

@WebFault(name = "WordConversionFault")
public class NumberConverterException extends Exception {

    public NumberConverterException() {
    }

    public NumberConverterException(String message) {
        super(message);
    }
}
