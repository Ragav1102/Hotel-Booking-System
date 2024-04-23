package database_connection;

import java.sql.*;

public class Database_Connection {
	public static  Connection getdbConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_booking","root","poovarasan@13");
return con;
	}
}