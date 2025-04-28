package dao;

import java.sql.Connection;
import model.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constants.DriverStatus;
import fooddelivery.DBConnection;

public class DriverDAO {
	
	public boolean createDriver(Driver driver) {
	    String query = "INSERT INTO drivers (driver_id, status) VALUES (?, ?, ?)";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	        stmt.setInt(1, driver.getUserId()); // Assuming userId is already set
	        stmt.setString(2, driver.getStatus());

	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


    public Driver getDriverById(int driverId) {
        Driver driver = null;
        String query = "SELECT u.user_id, u.name, u.phone_number, u.email, u.password, d.status " +
                       "FROM users u JOIN drivers d ON u.user_id = d.driver_id WHERE d.driver_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, driverId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String name = rs.getString("name");
                String phone = rs.getString("phone_number");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String status = rs.getString("status");
                List<Integer> assignedOrders = getAssignedOrders(driverId, conn);
        
                driver = Driver.existingDriverFromDB(userId, name, phone, email, password, status, assignedOrders);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return driver;
    }

    public static List<Integer> getAssignedOrders(int driverId, Connection conn) {
        List<Integer> orderIds = new ArrayList<>();
        String sql = "SELECT order_id FROM orders WHERE driver_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, driverId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orderIds.add(rs.getInt("order_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderIds;
    }
    
    public boolean updateDriverStatus(int driverId, String status) {
        String query = "UPDATE drivers SET status = ? WHERE driver_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, status);
            stmt.setInt(2, driverId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
//    public static List<Driver> getAvailableDrivers() {
//        List<Driver> availableDrivers = new ArrayList<>();
//        String query = "SELECT u.user_id, u.name, u.phone_number, u.email, u.password, d.status " +
//                       "FROM users u JOIN drivers d ON u.user_id = d.driver_id " +
//                       "WHERE d.status = 'available'";
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query);
//             ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
//                int id = rs.getInt("user_id");
//                String name = rs.getString("name");
//                String phone = rs.getString("phone_number");
//                String email = rs.getString("email");
//                String password = rs.getString("password");
//                String status = rs.getString("status");
//                
//                List<Integer> assignedOrders = getAssignedOrders(id, conn);
//              
//                Driver driver = Driver.existingDriverFromDB(id, name, phone, email, password, status, assignedOrders);
//                availableDrivers.add(driver);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return availableDrivers;
//    }
    
    public Driver findAvailableDriver() {
        String sql = "SELECT * FROM drivers WHERE status = ? LIMIT 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, DriverStatus.AVAILABLE);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Driver(
                    rs.getInt("driver_id"),
                    "", "", "", "",
                    rs.getString("status"),
                    new ArrayList<>()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public double getDriverAverageRating(int driverId) {
        String sql = "SELECT AVG(rating) FROM reviews WHERE order_id IN (SELECT order_id FROM deliveries WHERE driver_id = ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, driverId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0; // If no ratings found, return 0
    }



}
