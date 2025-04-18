package model;

//Abstract class representing users
public abstract class User {
	 private int userId;
	 private String name;
	 private String phoneNumber;
	 private String email;
 
 public User(int userId, String name, String phoneNumber, String email) {
     this.userId = userId;
	 this.name = name;
     this.phoneNumber = phoneNumber;
     this.email = email;
 }
 
 public int getUserId() {
	 return userId;
 }
 
 public String getName() {
     return name; 
 }

 public String getPhoneNumber() {
     return phoneNumber; 
 }

 public String getEmail() {
     return email; 
 }
}