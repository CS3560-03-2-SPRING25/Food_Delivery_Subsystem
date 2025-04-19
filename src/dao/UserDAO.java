package dao;

//import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.List;
import java.util.List;

import fooddelivery.DBConnection;
import model.Driver;
import model.RestaurantWorker;
import model.Customer;
import model.User;

public class UserDAO {
	public boolean createUser(User user) {
	    String sql = "INSERT INTO users (name, email, phone_number, password) VALUES (?, ?, ?, ?)";
	    String hashedPassword = user.getPassword();
	    
	    try (Connection conn = DBConnection.getConnection(); 
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setString(1, user.getName());
	        stmt.setString(2, user.getEmail());
	        stmt.setString(3, user.getPhoneNumber());
	        stmt.setString(4, hashedPassword); 

	        int rowsAffected = stmt.executeUpdate();
	        return rowsAffected > 0;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return false;
	}
	

	public User loginUser(String email, String password) {
	    String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
	    
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setString(1, email);
	        stmt.setString(2, password);

	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	        	String storedHashedPassword = rs.getString("password");
	           
	        	
	        	// Check if the provided password matches the stored hashed password
                if (password == storedHashedPassword) { // BCrypt.checkpw(password, storedHashedPassword)
                    int userId = rs.getInt("user_id");
                    String name = rs.getString("name");
                    String phoneNumber = rs.getString("phone_number");
                    String userEmail = rs.getString("email");
                    String status = rs.getString("status");
                    double rating = rs.getDouble("rating");   
                    
                   
                    
                 // Check if it's a Driver or Customer 
                    if (status != null) { // status means Driver (need to change this)
                    	// Get assigned orders from the driver_orders table
                        // List<Integer> assignedOrders = getAssignedOrders();
                    	 List<Integer> assignedOrders = getAssignedOrders(userId, conn);
                    	return new Driver(userId, name, phoneNumber, userEmail, storedHashedPassword, status, rating);
                    } else {
                        // Create a Customer or other User type
                        return new Customer(userId, name, phoneNumber, userEmail, storedHashedPassword); 
                    }
                } else {
                    System.out.println("Invalid password.");
                }
                
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;  // Return null if no matching user is found
	}

}
