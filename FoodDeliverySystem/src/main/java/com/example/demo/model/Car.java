package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
// Car class for vehicle used by a delivery driver
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carId;
    private String make;
    private String model;
    private String licensePlate;
    
    public Car(int carId, String make, String model, String licensePlate) {
        this.carId = carId;
        this.make = make;
        this.model = model;
        this.licensePlate = licensePlate;
    }
    
    public int getCarId() {
        return carId;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getLicensePlate() { 
        return licensePlate;
    }
}