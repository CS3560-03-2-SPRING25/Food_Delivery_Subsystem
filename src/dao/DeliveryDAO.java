package dao;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import constants.DeliveryStatus;
import constants.DriverStatus;
import constants.OrderStatus;
import fooddelivery.DBConnection;
import model.Delivery;

public class DeliveryDAO {
	// Create a new delivery
//    public boolean createDelivery(Delivery delivery) {
//        String query = "INSERT INTO deliveries (order_id, driver_id, status, start_time, finish_time) VALUES (?, ?, ?, ?, ?)";
//        
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            
//        	// Need orderId, driverId in Delivery class
////            stmt.setInt(1, delivery.getOrderId());
////            stmt.setInt(2, delivery.getDriverId());
////            stmt.setString(3, delivery.getStatus());
////            stmt.setTimestamp(4, new Timestamp(delivery.getStartTime().getTime()));
////            stmt.setTimestamp(5, new Timestamp(delivery.getFinishTime().getTime()));
//        	
//        	
//            
//            return stmt.executeUpdate() > 0; // Returns true if delivery is created
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
    
 // Update the status of a delivery
    public boolean updateDeliveryStatus(int deliveryId, String status) {
        String query = "UPDATE deliveries SET status = ? WHERE delivery_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, status);
            stmt.setInt(2, deliveryId);
            
            return stmt.executeUpdate() > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Get a delivery by its ID
//    public Delivery getDeliveryById(int deliveryId) {
//        String query = "SELECT * FROM deliveries WHERE delivery_id = ?";
//        Delivery delivery = null;
//        
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            
//            stmt.setInt(1, deliveryId);
//            ResultSet rs = stmt.executeQuery();
//            
//            if (rs.next()) {
//                int orderId = rs.getInt("order_id");
//                int driverId = rs.getInt("driver_id");
//                String status = rs.getString("status");
//                // startTime = rs.getTimestamp("start_time");
//                // finishTime = rs.getTimestamp("finish_time");
//
////                delivery = new Delivery();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return delivery;
//    }
    
    public boolean startDelivery(int orderId, int driverId, String address) {
        String sql = "INSERT INTO deliveries (order_id, driver_id, delivery_start_time, status, address) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);
            stmt.setInt(2, driverId);
            stmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setString(4, DeliveryStatus.IN_PROGRESS);
            stmt.setString(5, address);

            int inserted = stmt.executeUpdate();
            return inserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean completeDelivery(int deliveryId) {
        String selectSql = "SELECT status, delivery_start_time, order_id, driver_id FROM deliveries WHERE delivery_id = ?";
        String updateDeliverySql = "UPDATE deliveries SET delivery_end_time = ?, status = ? WHERE delivery_id = ?";
        String updateDriverSql = "UPDATE drivers SET status = ? WHERE driver_id = ?";
        String updateOrderSql = "UPDATE orders SET status = ? WHERE order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {

            selectStmt.setInt(1, deliveryId);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                String currentStatus = rs.getString("status");
                Timestamp startTime = rs.getTimestamp("delivery_start_time");
                int orderId = rs.getInt("order_id");
                int driverId = rs.getInt("driver_id");

                // Check if the delivery is in progress
                if (!DeliveryStatus.IN_PROGRESS.equals(currentStatus)) {
                    System.out.println("Cannot complete delivery. Current status: " + currentStatus);
                    return false;
                }

                // Calculate delivery time using
                Timestamp endTime = Timestamp.valueOf(LocalDateTime.now());
                long seconds = ChronoUnit.SECONDS.between(startTime.toLocalDateTime(), endTime.toLocalDateTime());
                Duration deliveryDuration = Duration.ofSeconds(seconds);

                // Update the delivery status to "completed"
                try (PreparedStatement updateDeliveryStmt = conn.prepareStatement(updateDeliverySql)) {
                    updateDeliveryStmt.setTimestamp(1, endTime);
                    updateDeliveryStmt.setString(2, DeliveryStatus.DELIVERED);
                    updateDeliveryStmt.setInt(3, deliveryId);
                    int updated = updateDeliveryStmt.executeUpdate();

                    if (updated > 0) {
                        System.out.println("Delivery completed! Total delivery time: " + deliveryDuration.toMinutes() + " minutes.");
                    } else {
                        return false;
                    }
                }

                // Update the driver status to "available"
                try (PreparedStatement updateDriverStmt = conn.prepareStatement(updateDriverSql)) {
                    updateDriverStmt.setString(1, DriverStatus.AVAILABLE);
                    updateDriverStmt.setInt(2, driverId);
                    updateDriverStmt.executeUpdate();
                }

                // Update the order status to "complete"
                try (PreparedStatement updateOrderStmt = conn.prepareStatement(updateOrderSql)) {
                    updateOrderStmt.setString(1, OrderStatus.COMPLETED);
                    updateOrderStmt.setInt(2, orderId);
                    updateOrderStmt.executeUpdate();
                }

                return true;

            } else {
                System.out.println("Delivery not found.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
