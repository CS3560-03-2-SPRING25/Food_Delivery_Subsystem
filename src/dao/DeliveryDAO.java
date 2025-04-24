package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import fooddelivery.DBConnection;
import model.Delivery;

public class DeliveryDAO {
	// Create a new delivery
    public boolean createDelivery(Delivery delivery) {
        String query = "INSERT INTO deliveries (order_id, driver_id, status, start_time, finish_time) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
        	// Need orderId, driverId in Delivery class
//            stmt.setInt(1, delivery.getOrderId());
//            stmt.setInt(2, delivery.getDriverId());
//            stmt.setString(3, delivery.getStatus());
//            stmt.setTimestamp(4, new Timestamp(delivery.getStartTime().getTime()));
//            stmt.setTimestamp(5, new Timestamp(delivery.getFinishTime().getTime()));
        	
        	
            
            return stmt.executeUpdate() > 0; // Returns true if delivery is created
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
 // Update the status of a delivery
    public boolean updateDeliveryStatus(int deliveryId, String status) {
        String query = "UPDATE deliveries SET status = ? WHERE delivery_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, status);
            stmt.setInt(2, deliveryId);
            
            return stmt.executeUpdate() > 0; // Returns true if status is updated
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
 // Get a delivery by its ID
    public Delivery getDeliveryById(int deliveryId) {
        String query = "SELECT * FROM deliveries WHERE delivery_id = ?";
        Delivery delivery = null;
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, deliveryId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                int orderId = rs.getInt("order_id");
                int driverId = rs.getInt("driver_id");
                String status = rs.getString("status");
                // startTime = rs.getTimestamp("start_time");
                // finishTime = rs.getTimestamp("finish_time");

//                delivery = new Delivery();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return delivery;
    }
}
