package com.example.demo.model;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.persistence.Entity;
@CrossOrigin(origins = "*") // Allows frontend to connect

@Entity
// Delivery driver class responsible for deliveries
public class Driver extends User {
    private String status;
    private List<Integer> assignedOrders;
    private double rating;
    
    public Driver(String name, String phoneNumber, String email, String status, List<Integer> assignedOrders, double rating) {
        super(name, phoneNumber, email);
        this.status = status;
        this.assignedOrders = assignedOrders;
        this.rating = rating;
    }
    
    public int getDriverId() {
        return id; 
    }

    public String getStatus() {
        return status;
    }

    public List<Integer> getAssignedOrders() {
        return assignedOrders;
    }
    
    public double getRating() {
        return rating;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
