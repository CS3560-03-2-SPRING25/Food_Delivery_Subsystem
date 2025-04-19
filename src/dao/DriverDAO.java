package dao;

import java.sql.Connection;
import model.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fooddelivery.DBConnection;

public class DriverDAO {

    public Driver getDriverById(int driverId) {
        Driver driver = null;
        String query = "SELECT u.user_id, u.name, u.phone_number, u.email, d.status, d.rating " +
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
                String status = rs.getString("status");
                double rating = rs.getDouble("rating");

                List<Integer> assignedOrders = getAssignedOrders(driverId, conn);
                
                driver = new Driver(userId, name, phone, email, status, assignedOrders, rating);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return driver;
    }

    public List<Integer> getAssignedOrders(int driverId, Connection conn) {
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
            return false;
        }
    }
    
    public List<Driver> getAvailableDrivers() {
        List<Driver> availableDrivers = new ArrayList<>();
        String query = "SELECT u.user_id, u.name, u.phone_number, u.email, d.status, d.rating " +
                       "FROM users u JOIN drivers d ON u.user_id = d.driver_id " +
                       "WHERE d.status = 'available'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("name");
                String phone = rs.getString("phone_number");
                String email = rs.getString("email");
                String status = rs.getString("status");
                double rating = rs.getDouble("rating");

                List<Integer> assignedOrders = getAssignedOrders(id, conn);

                Driver driver = new Driver(id, name, phone, email, status, assignedOrders, rating);
                availableDrivers.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return availableDrivers;
    }


}
