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
	          String account= "CREATE TABLE ACCOUNT" +
	                   "(email VARCHAR(255) not NULL, " +
	                   " title VARCHAR(255), " +
	                   " firstName VARCHAR(255), " + 
	                   " surname VARCHAR(255), " + 
	                   " mobileNumber VARCHAR(255), " + 
	                   " password VARCHAR(255), " +  
	                   " houseNameNumber VARCHAR(255), " +
	                   " streetName VARCHAR(255), " +
	                   " city VARCHAR(255), " + 
	                   " postcode VARCHAR(255), " + 
	                   " accountType VARCHAR(255), " + 
	                   " PRIMARY KEY ( email ))"; 
//	    	  String sql = "DROP TABLE TEST";

	         stmt.executeUpdate(account);
	         System.out.println("Created ACCOUNT in given database...");   	  
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } 
	   }
}
