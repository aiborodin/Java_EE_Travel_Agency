package com.travelagency.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity
@Table(name = "travel_offer", schema = "travel_agency")
public class TravelOffer implements Identifiable, Serializable {

    private int id;
    private String name;
    private double dayPrice;
    private OfferType offerType;

    public TravelOffer() {
    }

    public TravelOffer(int id, String name, double dayPrice, OfferType offerType) {
        this.id = id;
        this.name = name;
        this.dayPrice = dayPrice;
        this.offerType = offerType;
    }

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "offer_type")
    @NotNull
    @Size(max = 20)
    public OfferType getOfferType() {
        return offerType;
    }

    public void setOfferType(OfferType offerType) {
        this.offerType = offerType;
    }

    @Basic
    @Column(name = "name")
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "day_price")

    public double getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(double dayPrice) {
        this.dayPrice = dayPrice;
    }
}
