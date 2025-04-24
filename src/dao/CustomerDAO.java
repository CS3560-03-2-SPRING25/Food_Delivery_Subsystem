package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fooddelivery.DBConnection;
import model.Customer;

public class CustomerDAO {
	public boolean createCustomer(Customer customer) {
	    String query = "INSERT INTO customers (customer_id) VALUES (?)";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	        stmt.setInt(1, customer.getUserId());
//	        stmt.setString(2, customer.getAddress());

	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}
