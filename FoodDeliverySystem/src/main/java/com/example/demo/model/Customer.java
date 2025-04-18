package com.example.demo.model;

import jakarta.persistence.Entity;


@Entity
// Customer class extending User for placing orders
public class Customer extends User {
    private String address;
    
    public Customer(String name, String phoneNumber, String email, String address) {
        super(name, phoneNumber, email);
        this.address = address;
    }
    
    public int getCustomerId() {
        return id; 
    }

    public String getAddress() {
        return address; 
    }
}
