package com.travelagency.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Contract implements Identifiable, Serializable {

    private int id;
    private Client client;
    private Agent agent;
    private TravelOffer travelOffer;

    private int travelDays;
    private double transportationCosts;
    private double totalVisaCosts;

    public Contract() {
    }

    public Contract(int id, Client client, Agent agent, TravelOffer travelOffer, int travelDays, double transportationCosts, double totalVisaCosts) {
        this.id = id;
        this.client = client;
        this.agent = agent;
        this.travelOffer = travelOffer;
        this.travelDays = travelDays;
        this.transportationCosts = transportationCosts;
        this.totalVisaCosts = totalVisaCosts;
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

    @ManyToOne
    @JoinColumn(name = "client_id")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @ManyToOne
    @JoinColumn(name = "agent_id")
    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    @ManyToOne
    @JoinColumn(name = "travel_offer_id")
    public TravelOffer getTravelOffer() {
        return travelOffer;
    }

    public void setTravelOffer(TravelOffer travelOffer) {
        this.travelOffer = travelOffer;
    }

    @Basic
    @Column(name = "travel_days")
    @NotNull
    public int getTravelDays() {
        return travelDays;
    }

    public void setTravelDays(int travelDays) {
        this.travelDays = travelDays;
    }

    @Basic
    @Column(name = "total_transp_costs")
    @NotNull
    public double getTransportationCosts() {
        return transportationCosts;
    }

    public void setTransportationCosts(double transportationCosts) {
        this.transportationCosts = transportationCosts;
    }

    @Basic
    @Column(name = "total_visa_costs")
    public double getTotalVisaCosts() {
        return totalVisaCosts;
    }

    public void setTotalVisaCosts(double totalVisaCosts) {
        this.totalVisaCosts = totalVisaCosts;
    }
}
