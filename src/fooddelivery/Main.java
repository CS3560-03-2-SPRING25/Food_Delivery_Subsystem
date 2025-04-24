package fooddelivery;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.CustomerDAO;
import dao.DriverDAO;
import dao.RestaurantWorkerDAO;
import dao.UserDAO;
import model.Customer;
import model.Driver;
import model.User;
import model.RestaurantWorker;

public class Main {
	public static void main(String[] args) {		

//    	String name = "Restaurant Worker";
//      String phone = "111-222-3333";
//      String email = "restaurant.worker@gmail.com";
//      String password = "workerPassword";
//      String accountType = "Driver";  // based on user selection
//      String accountType = "Customer";  
//      String accountType = "Restaurant_worker"; 
		
		AuthService authService = new AuthService();

        // Signup
//        boolean success = authService.signup("Alex", "alex@example.com", "1234567890", "secret123", "driver");
//        System.out.println(success ? "Signup success!" : "Signup failed.");

//         Login
        User user = authService.login("alex@example.com", "secret123");
        if (user != null) {
            System.out.println("Login success: " + user.getName());

            if (user instanceof Driver) {
                System.out.println("Welcome, driver!");
            }
        } else {
            System.out.println("Login failed.");
        }
		
	}

}
