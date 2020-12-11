package com.travelagency.entity;

import java.util.Arrays;

public enum OfferType {

    PACKAGE_HOLIDAY ("Package holiday"),
    VOLUNTEER ("Volunteer"),
    MEDICAL ("Medical"),
    EVENT("Event");

    private final String typeName;

    OfferType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public static OfferType getBy(String typeName) {
        for (OfferType type : OfferType.values()) {
            if (type.getTypeName().equals(typeName)) {
                return type;
            }
        }
        return null;
    }
}
