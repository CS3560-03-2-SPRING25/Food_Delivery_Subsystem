package dao;

//import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import constants.UserRoles;
import fooddelivery.DBConnection;
import model.Customer;
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
	        	String storedHashedPassword = rs.getString("password");
	        	String storedEmail = rs.getString("email");

	        	// Check if the provided password matches the stored hashed password
	        	// BCrypt.checkpw(password, storedHashedPassword)
                if (password.equals(storedHashedPassword) && email.equals(storedEmail)) { 
                	
                	// System.out.println("password and email matches!!!");
    	        	String role = rs.getString("role"); // check role
                    int userId = rs.getInt("user_id");
                    String name = rs.getString("name");
                    String phone = rs.getString("phone_number");

                    switch (role.toLowerCase()) {
                    case UserRoles.DRIVER: 	
                    	// get driver from DB using userId
						try {
							// TODO: using DriverDAO method here (inside of another DAO) may cause an issue
							DriverDAO driverDAO = new DriverDAO();
							return driverDAO.getDriverById(userId);
						} catch (Exception e) {
							e.printStackTrace();
						}
                    case UserRoles.CUSTOMER:
                        return Customer.existingCustomerFromDB(userId, name, phone, email, password);
                    case UserRoles.RESTAURANT_WORKER:
                        return RestaurantWorker.existingRestaurantWorkerFromDB(userId, name, phone, email, password);
                    default:
                        return null;
                    }
                    
                } else {
                	// FIXME: when credentials are not correct, the code skips this part for unknown reasons
                    System.out.println("Invalid Credentials.");
            
                }
                
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;  // Return null if no matching user is found
	}

}
