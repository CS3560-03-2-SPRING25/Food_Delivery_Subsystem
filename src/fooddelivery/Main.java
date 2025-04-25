package fooddelivery;

import model.Customer;
import model.Driver;
import model.User;
import model.RestaurantWorker;

public class Main {
	public static void main(String[] args) {		

    	String name = "First5 Last5";
      String phone = "111-222-3333";
      String email = "driver5@example.com";
      String password = "pass123";
      String accountType = "driver";  // based on user selection: "driver" | "customer" | "restaurant_worker"
//      String accountType = "Customer";  
//      String accountType = "Restaurant_worker"; 
		
		AuthService authService = new AuthService();
	
        // Test Signup
        boolean success = authService.signup(name, phone, email, password, accountType);
        System.out.println(success ? "Signup success!" : "Signup failed.");

//        Test Login
//        User user = authService.login(email, password);
//        if (user != null) {
//            System.out.println("Login success: " + user.getName());
//            System.out.println("user role: " + user.getRole());
//            if (user instanceof Driver) { // or user.getRole() == 'driver'
//            	// open driver's page
//            	System.out.println("Welcome, driver!");
//            }
//            if (user instanceof Customer) { // or user.getRole() == 'customer'
//            	// open customer's page
//                System.out.println("Welcome, customer!");
//            }
//            if (user instanceof RestaurantWorker) { // or user.getRole() == 'restaurant_worker'
//            	// open worker's page
//                System.out.println("Welcome, Restaurant Worker!");
//            }
//   
//	            SessionManager.setCurrentUser(user);
//	            System.out.println("Login success: " + user.getName());
//        SessionManager.setCurrentUser(user, user.getRole());
//        
//        } else {
//            System.out.println("Login failed.");
//        }
		
	}

}
