package com.travelagency.entity;

public class Contract implements Identifiable{

    private int id;
    private Client client;
    private Agent agent;
    private TravelOffer travelOffer;

    private int travelDays;
    private double transportationCosts;
    private double totalVisaCosts;

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
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public TravelOffer getTravelOffer() {
        return travelOffer;
    }

    public void setTravelOffer(TravelOffer travelOffer) {
        this.travelOffer = travelOffer;
    }

    public int getTravelDays() {
        return travelDays;
    }

    public void setTravelDays(int travelDays) {
        this.travelDays = travelDays;
    }

    public double getTransportationCosts() {
        return transportationCosts;
    }

    public void setTransportationCosts(double transportationCosts) {
        this.transportationCosts = transportationCosts;
    }

    public double getTotalVisaCosts() {
        return totalVisaCosts;
    }

    public void setTotalVisaCosts(double totalVisaCosts) {
        this.totalVisaCosts = totalVisaCosts;
    }
}
