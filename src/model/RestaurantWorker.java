package model;

//Restaurant worker class for creating orders
public class RestaurantWorker extends User {
 
		 // Constructor with password
	 public RestaurantWorker(int userId, String name, String phoneNumber, String email, String password) {
	     super(userId, name, phoneNumber, email, password);
	     // ...
	     // this.shiftStatus = status;
	 }
 
	 // Constructor without password
	 public RestaurantWorker(int userId, String name, String phoneNumber, String email ) {
	     super(userId, name, phoneNumber, email);
	     // ...
	     // this.shiftStatus = status;
	 }
 
// public String getShiftStatus() {
//     return shiftStatus;
// }
// 
// public void setShiftStatus(String status) {
//     this.shiftStatus = status;
// }
 
}