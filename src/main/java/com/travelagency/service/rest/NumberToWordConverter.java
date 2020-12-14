package com.travelagency.service.rest;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;

@Path("/RestNumberConverterService")
public class NumberToWordConverter {

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

    @GET
    @Path("{number}")
    @Produces("text/plain")
    public String convertToWord(@PathParam("number") int number) {
        if (number < 1 || number > 20) {
            return "Number must be between 1 and 20";
        } else {
            return words[number-1];
        }
    }
}
