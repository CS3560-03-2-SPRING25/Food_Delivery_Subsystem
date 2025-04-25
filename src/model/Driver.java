package model;

//import java.sql.Connection;
//import java.util.ArrayList;
import java.util.List;
import constants.UserRoles;


//Delivery driver class responsible for deliveries
public class Driver extends User {
	 private String status;
	 private List<Integer> assignedOrders;
	 private double rating;
	 
	 public Driver(int userId, String name, String phoneNumber, String email, String password, String status, List<Integer> assignedOrders, double rating) {
	     super(userId, name, phoneNumber, email, password, UserRoles.DRIVER);
	     this.status = status;
	     this.assignedOrders = assignedOrders;
	     this.rating = rating;
	 }
	 
	 // Static Factory Method
	 public static Driver newDriverForSignup(String name, String phoneNumber, String email, String password, String status, List<Integer> assignedOrders) {
	     return new Driver(0, name, phoneNumber, email, password, status, assignedOrders, 0.0);
	 }
	
	 // Static Factory Method
	 public static Driver existingDriverFromDB(int userId, String name, String phoneNumber, String email, String password, String status, List<Integer> assignedOrders, double rating) {
	     return new Driver(userId, name, phoneNumber, email, password, status, assignedOrders, rating);
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