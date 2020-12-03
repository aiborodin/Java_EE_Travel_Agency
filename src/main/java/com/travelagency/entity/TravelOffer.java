package com.travelagency.entity;

public class TravelOffer implements Identifiable {

    private int id;
    private String name;
    private double dayPrice;
    private String offerType;

    public TravelOffer(int id, String name, double dayPrice, String offerType) {
        this.id = id;
        this.name = name;
        this.dayPrice = dayPrice;
        this.offerType = offerType;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(double dayPrice) {
        this.dayPrice = dayPrice;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }
}
