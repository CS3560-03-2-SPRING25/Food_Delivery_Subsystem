package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fooddelivery.DBConnection;
import model.RestaurantWorker;

public class RestaurantWorkerDAO {
	public boolean createRestaurantWorker(RestaurantWorker worker) {
	    String query = "INSERT INTO restaurant_workers (worker_id, shift_status) VALUES (?, ?)";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	        stmt.setInt(1, worker.getUserId()); // This assumes userId is already set
	        stmt.setString(2, worker.getShiftStatus());

	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public static RestaurantWorker getRestWorkerById(int workerId) {
		RestaurantWorker worker = null;
        String query = "SELECT u.user_id, u.name, u.phone_number, u.email, u.password, rw.shift_status " +
                       "FROM users u JOIN restaurant_workers rw ON u.user_id = rw.worker_id WHERE rw.worker_id = ?";
  
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, workerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String name = rs.getString("name");
                String phone = rs.getString("phone_number");
                String email = rs.getString("email");
                String password = rs.getString("password");
//                String shiftStatus = rs.getString("shift_status");
    
                worker = RestaurantWorker.existingRestaurantWorkerFromDB(userId, name, phone, email, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return worker;
    }

}
