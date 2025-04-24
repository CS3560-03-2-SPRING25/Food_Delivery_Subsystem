package dao;

//import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.List;
import java.util.List;

import fooddelivery.DBConnection;
import model.Customer;
import model.Driver;
import model.RestaurantWorker;
import model.User;

public class UserDAO {
	public int createUser(User user) {
	    String sql = "INSERT INTO users (name, email, phone_number, password, role) VALUES (?, ?, ?, ?, ?)";
	    
	    try (Connection conn = DBConnection.getConnection(); 
	         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	    	String hashedPassword = user.getPassword(); // TODO: use hashed password
	        
	        stmt.setString(1, user.getName());
	        stmt.setString(2, user.getEmail());
	        stmt.setString(3, user.getPhoneNumber());
	        stmt.setString(4, hashedPassword); 
	        stmt.setString(5, user.getRole());
	        
	        stmt.executeUpdate();
	        
	     // Get generated user_id
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // Return the generated user_id
                }
            }
	        
	    } catch (SQLException e) {
	    	 e.printStackTrace();
	    }
	    return -1;
	}
	

	public User loginUser(String email, String password) {
	    String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
	    
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setString(1, email);
	        stmt.setString(2, password);

	        ResultSet rs = stmt.executeQuery();
	    
	        if (rs.next()) {
	        	String role = rs.getString("role"); // check role
	        	String storedHashedPassword = rs.getString("password");
	        	
//	            System.out.println(role);
	        	// TODO: check email???
	        	// Check if the provided password matches the stored hashed password
	        	// BCrypt.checkpw(password, storedHashedPassword)
                if (password.equals(storedHashedPassword)) { 
                	  System.out.println("password matches!!!");
                    int userId = rs.getInt("user_id");
                    String name = rs.getString("name");
                    String phone = rs.getString("phone_number");

                    switch (role.toLowerCase()) {
                    case "driver":
                    	String status = rs.getString("status");
                    	double rating = rs.getDouble("rating");
                    	// get driver from DB using id
                    	List<Integer> assignedOrders = DriverDAO.getAssignedOrders(userId, conn);
                        return Driver.existingDriverFromDB(userId, name, phone, email, password, status, assignedOrders, rating);
                    case "customer":
                        return Customer.existingCustomerFromDB(userId, name, phone, email, password);
                    case "restaurant_worker":
                    	String shiftStatus = rs.getString("shift_status"); 
//                        return RestaurantWorker.existingWorkerFromDB(userId, name, phone, email, password, shiftStatus);
                    default:
                        return null;
                    }
                    
                    // do we need the password?
                    // return new User(userId, name, phoneNumber, userEmail, storedHashedPassword);
                } else {
                    System.out.println("Invalid Credentials.");
                }
                
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;  // Return null if no matching user is found
	}

}
