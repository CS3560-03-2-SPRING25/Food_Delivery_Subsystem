package model;

//import java.sql.Connection;
//import java.util.ArrayList;
import java.util.List;

//Delivery driver class responsible for deliveries
public class Driver extends User {
	 private String status;
	 private List<Integer> assignedOrders;
	 private double rating;
 
	 // Constructor with password
	 // add List<Integer> assignedOrders if needed
 public Driver(int userId, String name, String phoneNumber, String email, String password, String status, List<Integer> assignedOrders, double rating) {
     super(userId, name, phoneNumber, email, password);
     this.status = status;
     this.assignedOrders = assignedOrders;
     this.rating = rating;
 }

 // Constructor without password (used when retrieving a driver from DB)
//add List<Integer> assignedOrders if needed
 public Driver(int userId, String name, String phoneNumber, String email, String status, List<Integer> assignedOrders, double rating) {
     super(userId, name, phoneNumber, email); 
     this.status = status;
     this.assignedOrders = assignedOrders;
     this.rating = rating;
 }

 public String getStatus() {
     return status;
 }
 
 public void setStatus(String status) {
     this.status = status;
 }

 public List<Integer> getAssignedOrders() {
	 return assignedOrders;
 }
 
 public double getRating() {
     return rating;
 }


 public void setRating(double rating) {
     this.rating = rating;
 }
 
}