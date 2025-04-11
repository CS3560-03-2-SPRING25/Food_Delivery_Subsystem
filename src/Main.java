import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		 String url = "jdbc:mysql://localhost:3306/food_delivery";  // Replace with your DB name
	     String user = "root"; // MySQL username
	     String password = "root"; // MySQL password
		
		// register the driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// Establish the connection
		Connection con = DriverManager.getConnection(url, user, password);
		System.out.println("connection created");
	}

}
