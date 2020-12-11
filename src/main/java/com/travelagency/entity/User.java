package com.travelagency.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@MappedSuperclass
public abstract class User implements Identifiable, Serializable {

    protected int id;
    protected String login;
    protected String password;

    public User() {

    }

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "login")
    @NotNull
    @Pattern(regexp = "^[a-z0-9_-]+")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "pwd")
    @NotNull @Size(min = 4, max = 20)
    @Pattern(regexp = "^[a-z0-9_-]+")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
