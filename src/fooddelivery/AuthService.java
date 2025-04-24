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

public class AuthService {
//	private final UserDAO userDAO = new UserDAO();
//    private final DriverDAO driverDAO = new DriverDAO();
//    private final CustomerDAO customerDAO = new CustomerDAO();
//    private final RestaurantWorkerDAO workerDAO = new RestaurantWorkerDAO();
    
    public boolean signup(String name, String email, String phone, String password, String role) {
//        boolean userCreated = userDAO.createUser(name, phone, email, password, role);
//
//        if (!userCreated) return false;
//
//        int userId = userDAO.getUserIdByEmail(email);  // You'll need to implement this method
//        switch (role.toLowerCase()) {
//            case "driver":
//                Driver driver = Driver.newDriverForSignup(name, phone, email, password, "available");
//                driverDAO.createDriver(driver, userId);
//                break;
//            case "customer":
//                Customer customer = Customer.newCustomerForSignup(name, phone, email, password);
//                customerDAO.createCustomer(customer, userId);
//                break;
//            case "restaurant_worker":
//                RestaurantWorker worker = RestaurantWorker.newWorkerForSignup(name, phone, email, password);
//                workerDAO.createRestaurantWorker(worker, userId);
//                break;
//            default:
//                return false;
//        }
//
//        return true;
        
		User user = null;
		
        if ("Driver".equalsIgnoreCase(role)) {
        	// TODO: status and assignedOrders list can be set by default in driver class??
        	user = Driver.newDriverForSignup(name, phone, email, password, "available", new ArrayList<Integer>());
        } else if ("Customer".equalsIgnoreCase(role)) {
            user = Customer.newCustomerForSignup(name, phone, email, password);
        } else if ("Restaurant_worker".equalsIgnoreCase(role)) {
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
    }

    public User login(String email, String password) {
//    	User user = null;
    	UserDAO userDAO = new UserDAO();
//        int userId  = userDAO.createUser(user); 
        return userDAO.loginUser(email, password);  // This will return the right type based on role
    }
}
