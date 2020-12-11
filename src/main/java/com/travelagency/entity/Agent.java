package com.travelagency.entity;

import com.travelagency.entity.constraints.ActiveAgent;

import javax.inject.Named;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "agent")
@NamedQueries({
        @NamedQuery(name = "findActive", query = "SELECT a FROM Agent a WHERE a.active = true"),
        @NamedQuery(name = "findByFirstName", query = "SELECT a FROM Agent a WHERE a.firstName = :firstName")
})
public class Agent extends User {

    private String first_name;
    private String last_name;
    private boolean active;

    public Agent() {
    }

    public Agent(String login, String password, int id, String first_name, String last_name, boolean active) {
        super(id, login, password);
        this.first_name = first_name;
        this.last_name = last_name;
        this.active = active;
    }

    @Basic
    @Column(name = "f_name")
    @NotNull
    @Size(max = 20)
    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    @Basic
    @Column(name = "l_name")
    @Size(max = 20)
    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    @Basic
    @Column(name = "active")
    @ActiveAgent
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
