package model;

//Car class for vehicle used by a delivery driver
public class Car {
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
