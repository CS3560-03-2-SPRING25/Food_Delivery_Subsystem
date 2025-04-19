package fooddelivery;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dao.DriverDAO;

public class Main {
	public static void main(String[] args) {
		
//		try (Connection connection = DBConnection.getConnection()) {
//            Statement statement = connection.createStatement();
//            String query = "SELECT * FROM users";
//            ResultSet resultSet = statement.executeQuery(query);
//           
//            while (resultSet.next()) {
//                int userId = resultSet.getInt("user_id");
//                String name = resultSet.getString("name");
//                String phone = resultSet.getString("phone_number");
//                String email = resultSet.getString("email");
//                String role = resultSet.getString("role");
//           
//                System.out.println("ID: " + userId +
//                                   ", Name: " + name +
//                                   ", Phone: " + phone +
//                                   ", Email: " + email +
//                                   ", Role: " + role);
//            }
//            
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
		
		
		 try {
	            Connection conn = DBConnection.getConnection(); // establish DB connection

	            DriverDAO driverDAO = new DriverDAO(); // DAO object
	            int driverId = 1; // test with an existing driver ID

	            List<Integer> assignedOrders = driverDAO.getAssignedOrders(driverId, conn);

	            System.out.println("Assigned Orders for Driver ID " + driverId + ":");
	            for (int orderId : assignedOrders) {
	                System.out.println("Order ID: " + orderId);
	            }

	            conn.close(); // always close connection when done

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	}

}
