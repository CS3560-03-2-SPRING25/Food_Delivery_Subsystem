package fooddelivery;

import java.util.List;

import constants.DriverStatus;
import constants.UserRoles;
import dao.DriverDAO;
import model.Customer;
import model.Driver;
import model.Order;
import model.User;
import model.RestaurantWorker;

public class Main {
	
	public static void main(String[] args) {
		
		String name = "restaurant worker";
		String phone = "123-555-5523";
		String email = "worker@example.com";
		String password = "pass123";
		String accountType = UserRoles.RESTAURANT_WORKER;
		
		
		// Test viewAllOrders
//		 OrderService orderService = new OrderService();
//		 List<Order> orders = orderService.viewAllOrders();
//		 System.out.println(orders);
		 
//		 orderService.cancelOrder(1); // Test cancelOrder
		
		AuthService authService = new AuthService();
	
        // Test Signup
//        boolean success = authService.signup(name, phone, email, password, accountType);
//        System.out.println(success ? "Signup success!" : "Signup failed.");
//		
		// TEST UPDATE DRIVER STATUS
//		 DriverDAO driverDAO = new DriverDAO();
//		 DriverDAO.updateDriverStatus(8, DriverStatus.BUSY);

		 
//        Test Login
        User user = authService.login(email, password);
        if (user != null) {
            System.out.println("Login success: " + user.getName());
            System.out.println("user role: " + user.getRole());
            if (user instanceof Driver) { 
            	// open driver's page
            	System.out.println("Welcome, driver!");
            }
            if (user instanceof Customer) { 
            	// open customer's page
                System.out.println("Welcome, customer!");
            	// TEST customer places order
//				OrderService orderService = new OrderService();
//				System.out.println("Customer " + user.getName() + " ordering");
//				orderService.placeOrder(user.getUserId());
//				System.out.println("Customer " + user.getName() + " successfully placed an order!");
            }
            if (user instanceof RestaurantWorker) { 
            	// open worker's page
                System.out.println("Welcome, Restaurant Worker: " + user.getName());
               
                // TEST worker accepts an order
//                System.out.println("Accepting the order...");
                OrderService orderService = new OrderService();
//                orderService.acceptOrder(2);
//                System.out.println("Order Accepted!");
                
                System.out.println("Finding available drivers...");
                orderService.autoAssignDriver(2);
                System.out.println("Order is assigned!");
            }
   
	            SessionManager.setCurrentUser(user);
	            System.out.println("Login success: " + user.getName());
	            SessionManager.setCurrentUser(user);
	            
			
        
        } else {
            System.out.println("Login failed.");
        }
		
	}

}
