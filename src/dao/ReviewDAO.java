package dao;

import java.sql.*;

import fooddelivery.DBConnection;

public class ReviewDAO {

    public boolean leaveReview(int customerId, int orderId, int rating, String comments) {
        String sql = "INSERT INTO reviews (customer_id, order_id, rating, comments) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            stmt.setInt(2, orderId);
            stmt.setInt(3, rating);
            stmt.setString(4, comments);

            int inserted = stmt.executeUpdate();
            return inserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
