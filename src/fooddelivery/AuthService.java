package fooddelivery;

import java.util.ArrayList;

import dao.CustomerDAO;
import dao.DriverDAO;
import dao.RestaurantWorkerDAO;
import dao.UserDAO;
import model.Customer;
import model.Driver;
import model.RestaurantWorker;
import model.User;
import constants.UserRoles;

public class AuthService {	
    public boolean signup(String name, String phone, String email, String password, String role) {
    	
    	User user = null;
		
        if (UserRoles.DRIVER.equalsIgnoreCase(role)) {
        	// TODO: status and assignedOrders list can be set by default in driver class??
        	user = Driver.newDriverForSignup(name, phone, email, password, "available", new ArrayList<Integer>());
        } else if (UserRoles.CUSTOMER.equalsIgnoreCase(role)) {
            user = Customer.newCustomerForSignup(name, phone, email, password);
        } else if (UserRoles.RESTAURANT_WORKER.equalsIgnoreCase(role)) {
            user = RestaurantWorker.newRestaurantWorkerForSignup(name, phone, email, password);
        }
        
     // Create User in DB
        UserDAO userDAO = new UserDAO();
        int userId  = userDAO.createUser(user); 
        
        if (userId > 0) {
            System.out.println("User created successfully!");
            
            // Set the generated user_id for the user object
            user.setUserId(userId);
            
            // After user is created, respective DAO inserts role-specific data
            if (user instanceof Driver) {
                DriverDAO driverDAO = new DriverDAO();
                driverDAO.createDriver((Driver) user);
            } else if (user instanceof Customer) {
                CustomerDAO customerDAO = new CustomerDAO();
                customerDAO.createCustomer((Customer) user);
            } else if (user instanceof RestaurantWorker) {
                RestaurantWorkerDAO workerDAO = new RestaurantWorkerDAO();
                workerDAO.createRestaurantWorker((RestaurantWorker) user);
            }
            return true;
        } else {
            System.out.println("User creation failed.");
            return false;
        }
    	
//    	 // Create User in DB
//    	 User user = User.newUserForSignup(name, phone, email, password, role);
//    	 UserDAO userDAO = new UserDAO();
//    	 int userId  = userDAO.createUser(user); 
////        int userCreated = UserDAO.createUser(user);
//
//        if (userId < 0) {
//        	System.out.println("userId: " + userId);
//        	System.out.println("User creation failed.");
//        	return false;
//        }
//        
//        // Set the generated user_id for the user object
//        user.setUserId(userId);
//       
//        // After user is created, respective DAO inserts role-specific data
//        switch (role.toLowerCase()) {
//            case UserRoles.DRIVER:
//            	// TODO: status and assignedOrders list can be set by default in driver class??
//                Driver driver = Driver.newDriverForSignup(user.getName(), user.getPhoneNumber(), user.getEmail(), user.getPassword(), "available", new ArrayList<Integer>());
//                DriverDAO driverDAO = new DriverDAO();
//                driverDAO.createDriver((Driver) driver);
//                break;
//            case UserRoles.CUSTOMER:
//                Customer customer = Customer.newCustomerForSignup(user.getName(), user.getPhoneNumber(), user.getEmail(), user.getPassword());
//                CustomerDAO customerDAO = new CustomerDAO();
//                customerDAO.createCustomer((Customer) customer);
//                break;
//            case UserRoles.RESTAURANT_WORKER:
//                RestaurantWorker worker = RestaurantWorker.newRestaurantWorkerForSignup(user.getName(), user.getPhoneNumber(), user.getEmail(), user.getPassword());
//                RestaurantWorkerDAO restaurantWorkerDAO = new RestaurantWorkerDAO();
//                restaurantWorkerDAO.createRestaurantWorker((RestaurantWorker) worker);
//                break;
//            default:
//                return false;
//        }
//        System.out.println("User created successfully!");
//        return true;
        
    }

    public User login(String email, String password) {
    	UserDAO userDAO = new UserDAO();
        return userDAO.loginUser(email, password);  // This will return the right type based on role
    }
}
