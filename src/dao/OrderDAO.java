package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import constants.OrderStatus;
import fooddelivery.DBConnection;
import model.Order;

public class OrderDAO {
    public int createOrder(Order order) {
        String sql = "INSERT INTO orders (customer_id, order_time, status) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, order.getCustomerId());
            stmt.setTimestamp(2, Timestamp.valueOf(order.getOrderTime()));
            stmt.setString(3, order.getOrderStatus());

            int rows = stmt.executeUpdate();
            if (rows == 0) return -1;

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) return keys.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean acceptOrder(int orderId) {
        String sql = "UPDATE orders SET status = ? WHERE order_id = ? AND status = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, OrderStatus.ACCEPTED);
            stmt.setInt(2, orderId);
            stmt.setString(3, OrderStatus.PENDING);

            int updated = stmt.executeUpdate();
            return updated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	
//	public boolean assignDriverToOrder(int orderId, int driverId) {
//	    String sql = "UPDATE orders SET driver_id = ?, status = ? WHERE order_id = ?";
//	    try (Connection conn = DBConnection.getConnection();
//	         PreparedStatement stmt = conn.prepareStatement(sql)) {
//	        stmt.setInt(1, driverId);
//	        stmt.setString(2, "assigned");
//	        stmt.setInt(3, orderId);
//	        return stmt.executeUpdate() > 0;
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	        return false;
//	    }
//	}
    
    // TODO: get all orders that aren't canceled
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Order order = new Order(
                    rs.getInt("order_id"),
                    rs.getInt("customer_id"),
                    rs.getInt("driver_id"),
                    rs.getTimestamp("order_time").toLocalDateTime(),
                    rs.getString("status")
                );
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public boolean cancelOrder(int orderId) {
        String sql = "UPDATE orders SET status = ? WHERE order_id = ? AND (status = ? OR status = ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, OrderStatus.CANCELED);
            stmt.setInt(2, orderId);
            stmt.setString(3, OrderStatus.PENDING);
            stmt.setString(4, OrderStatus.ACCEPTED);

            int updated = stmt.executeUpdate();
            return updated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean markOrderReady(int orderId) {
        String sql = "UPDATE orders SET status = ? WHERE order_id = ? AND status = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, OrderStatus.READY);
            stmt.setInt(2, orderId);
            stmt.setString(3, OrderStatus.ACCEPTED);

            int updated = stmt.executeUpdate();
            return updated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean assignDriverToOrder(int orderId, int driverId) {
        String sql = "UPDATE orders SET driver_id = ?, status = ? WHERE order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, driverId);
            stmt.setString(2, OrderStatus.ASSIGNED);
            stmt.setInt(3, orderId);

            int updated = stmt.executeUpdate();
            return updated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
