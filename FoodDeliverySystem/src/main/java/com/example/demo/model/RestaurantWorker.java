package com.example.demo.model;

import jakarta.persistence.Entity;


@Entity
// Restaurant worker class for creating orders
public class RestaurantWorker extends User {

    
    public RestaurantWorker(String name, String phoneNumber, String email) {
        super(name, phoneNumber, email);
    }
    
    public int getRestaurantWorkerId() {
        return id; 
    }
}