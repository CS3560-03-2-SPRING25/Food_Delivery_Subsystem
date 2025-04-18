package model;

//Customer class extending User for placing orders
public class Customer extends User {
 private String address;
 
 public Customer(int userId, String name, String phoneNumber, String email, String address) {
     super(userId, name, phoneNumber, email);
     this.address = address;
 }
 
 public String getAddress() {
     return address; 
 }
}
