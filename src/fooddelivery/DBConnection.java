package fooddelivery;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/food_delivery";
	private static final String USER = "root"; // MySQL username
	private static final String PASSWORD = "root"; // MySQL password
	
	public static Connection getConnection() throws SQLException {
		
		try {
			// register the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			return DriverManager.getConnection(URL, USER, PASSWORD);
		
		} catch (ClassNotFoundException | SQLException e) {
			
			throw new SQLException("Failed to esablish connection to the database.", e);
		
		}
	}
}
