package GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionManager {
	private static String serverName = "jdbc:mysql://stusql.dcs.shef.ac.uk/team018";
	private static String username = "team018";
	private static String pwd = "7854a03f";
	private static Connection connection;

	public static Connection getConnection() throws SQLException {
		try {
			//creating a connection to server so database can be connected
			connection = DriverManager.getConnection(serverName, username, pwd);
			System.out.println("Connection successful");
		} catch (SQLException ex) {
			System.out.println("Failed to make connection");
			ex.printStackTrace();
		} 
		return connection;
	}

}