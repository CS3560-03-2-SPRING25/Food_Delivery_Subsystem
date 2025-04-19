package model;

//Abstract class representing users
public abstract class User {
	 private int userId;
	 private String name;
	 private String phoneNumber;
	 private String email;
	 private String password;

	 // Constructor with password for creating new users (sign up)
	public User(int userId, String name, String phoneNumber, String email, String password) {
	    this.userId = userId;
	    this.name = name;
	    this.phoneNumber = phoneNumber;
	    this.email = email;
	    this.password = password;
	}
	
	// Constructor without password (for fetching user details from DB)
	public User(int userId, String name, String phoneNumber, String email) {
	    this.userId = userId;
	    this.name = name;
	    this.phoneNumber = phoneNumber;
	    this.email = email;
	}
	 
	 public int getUserId() {
		 return userId;
	 }
	 
	 public void setUserId(int userId) {
	     this.userId = userId;
	 }
	 
	 public String getName() {
	     return name; 
	 }
	 
	 public void setName(String name) {
	     this.name = name;
	 }
	
	 public String getPhoneNumber() {
	     return phoneNumber; 
	 }
	 
	 public void setPhoneNumber(String phoneNumber) {
	     this.phoneNumber = phoneNumber;
	 }
	
	 public String getEmail() {
	     return email; 
	 }
	 
	 public void setEmail(String email) {
	     this.email = email;
	 }
	 
	 public String getPassword() {
	     return password;
	 }
	
	 public void setPassword(String password) {
	     this.password = password;
	 }
}