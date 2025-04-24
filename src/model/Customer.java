package model;

//Customer class extending User for placing orders
public class Customer extends User {
//	private String address;
 
	//Constructor with password
	 public Customer(int userId, String name, String phoneNumber, String email, String password) {
	     super(userId, name, phoneNumber, email, password, "customer");
//	     this.address = address;
	 }

	 //Static Factory Method
	 public static Customer newCustomerForSignup(String name, String phoneNumber, String email, String password) {
	     return new Customer(0, name, phoneNumber, email, password);
	 }
	
	//Static Factory Method
	 public static Customer existingCustomerFromDB(int userId, String name, String phoneNumber, String email, String password) {
	     return new Customer(userId, name, phoneNumber, email, password);
	 }
 
//	 public String getAddress() {
//	     return address; 
//	 }
}
