package com.travelagency.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "client", schema = "travel_agency")
public class Client extends User {

    private String first_name;
    private String last_name;
    private int age;
    private String phone;
    private String email;
    private LocalDate customerFrom;

    public Client() {
    }

    public Client(String login, String password, int id,
                  String first_name, String last_name, int age, String phone, String email, LocalDate customerFrom) {
        super(id, login, password);
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.customerFrom = customerFrom;
    }

    @Basic
    @Column(name = "first_name")
    @NotNull
    @Size(max = 30)
    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    @Basic
    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Basic
    @Column(name = "phone")
    @Pattern(regexp = "^[+]*[(]?[0-9]{1,4}[)]?[-\\s\\./0-9]*$")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "cust_from")
    public LocalDate getCustomerFrom() {
        return customerFrom;
    }

    public void setCustomerFrom(LocalDate customerFrom) {
        this.customerFrom = customerFrom;
    }
}
