package com.travelagency.entity;

public class Agent extends User {

    private String first_name;
    private String last_name;
    private boolean active;

    public Agent(String login, String password, int id, String first_name, String last_name, boolean active) {
        super(id, login, password);
        this.first_name = first_name;
        this.last_name = last_name;
        this.active = active;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
