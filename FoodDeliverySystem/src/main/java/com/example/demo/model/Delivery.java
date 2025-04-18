package com.example.demo.model;

import java.time.Duration;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
// Delivery class for delivery details
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deliveryId; 
    private Duration estimatedTime;
    private Duration deliveryTime;
    private String deliveryStatus;

    public Delivery(int deliveryId, Duration estimatedTime, Duration deliveryTime, String deliveryStatus) {
        this.deliveryId = deliveryId;
        this.estimatedTime = estimatedTime;
        this.deliveryTime = deliveryTime;
        this.deliveryStatus = deliveryStatus;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public Duration getDeliveryTime() {
        return deliveryTime;
    }

    public Duration getEstimatedTime() {
        return estimatedTime;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setEstimatedTime(Duration estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
