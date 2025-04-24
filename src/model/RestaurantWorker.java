package model;

//Restaurant worker class for creating orders
public class RestaurantWorker extends User {
	private String shiftStatus;

	 public RestaurantWorker(int userId, String name, String phoneNumber, String email, String password) {
	     super(userId, name, phoneNumber, email, password, "restaurant_worker");
	     this.shiftStatus = "Off";
	 }
	 
	 //Static Factory Method
	 public static RestaurantWorker newRestaurantWorkerForSignup(String name, String phoneNumber, String email, String password ) {
	     return new RestaurantWorker(0, name, phoneNumber, email, password);
	 }
	
	//Static Factory Method
	 public static RestaurantWorker existingRestaurantWorkerFromDB(int userId, String name, String phoneNumber, String email, String password ) {
	     return new RestaurantWorker(userId, name, phoneNumber, email, password );
	 }

	public String getShiftStatus() {
		 return shiftStatus;
	}
	 
	public void setShiftStatus(String status) {
	     this.shiftStatus = status;
	 }
 
}