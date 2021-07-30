package com.example.shop;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Customer(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }
    public Customer(){}

    //@NotBlank(message = "Name is mandatory")
    private String login;

    //@NotBlank(message = "Email is mandatory")
    private String password;

    private String email;

    public Customer(String login, String password) {
        this.login=login;
        this.password=password;
    }

    // standard constructors / setters / getters / toString
}