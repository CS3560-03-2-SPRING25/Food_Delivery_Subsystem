package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@CrossOrigin(origins = "*") // Allows frontend to connect
@Entity
public class DeliveryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @ElementCollection
    private List<String> orderItems;
    private LocalDateTime orderTime;
    private String orderStatus;

    public DeliveryOrder() {}
    
    public DeliveryOrder(int orderId, List<String> orderItems, LocalDateTime orderTime, String orderStatus) {
        this.orderId = orderId;
        this.orderItems = orderItems;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
    }
    
    public int getOrderId() {
        return orderId;
    }

    public List<String> getOrderItems() {
        return orderItems;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

}
