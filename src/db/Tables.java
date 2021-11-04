package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Tables {
	public static void main(String[] args) {
	      try(Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team018", "team018", "7854a03f");
	         Statement stmt = conn.createStatement();
	      ) {		      
	          String sql = "CREATE TABLE ACCOUNT" +
	                   "(email VARCHAR(255) not NULL, " +
	                   " title VARCHAR(255), " +
	                   " firstName VARCHAR(255), " + 
	                   " surname VARCHAR(255), " + 
	                   " mobileNumber VARCHAR(255), " + 
	                   " password VARCHAR(255), " + 
	                   " addressLine1 VARCHAR(255), " + 
	                   " houseNameNumber VARCHAR(255), " + 
	                   " postcode VARCHAR(255), " + 
	                   " accountType VARCHAR(255), " + 
	                   " PRIMARY KEY ( email ))"; 

	         stmt.executeUpdate(sql);
	         System.out.println("Created ACCOUNT in given database...");   	  
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } 
	   }
}
