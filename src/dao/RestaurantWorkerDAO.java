package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

}
