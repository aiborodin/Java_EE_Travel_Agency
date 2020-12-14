package com.travelagency.service.soap;

import com.travelagency.service.soap.NumberConverterException;

import javax.jws.WebService;

@WebService(targetNamespace = "http://travelagency.com/")
public interface NumberConverter {
    String convertToWord(int number) throws NumberConverterException;
}
