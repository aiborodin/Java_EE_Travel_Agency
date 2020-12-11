package com.travelagency.entity;

import java.io.Serializable;

// Non-persistable entity
public class Admin extends User {
    public Admin(int id, String login, String password) {
        super(id, login, password);
    }
}
