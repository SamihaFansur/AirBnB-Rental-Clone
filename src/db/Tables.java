package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Tables {

	public static void main(String[] args) {
		String createAddressTable = "CREATE TABLE Address("
				+ "PRIMARY KEY (houseNameNumber, postcode), houseNameNumber VARCHAR(255), streetName VARCHAR(255), "
				+ "placeName VARCHAR(255), postcode VARCHAR(255))";

		String createAccountTable = "CREATE TABLE Account("
				+ "email VARCHAR(255) NOT NULL PRIMARY KEY, title VARCHAR(255), "
				+ "firstName VARCHAR(255), surname VARCHAR(255), mobileNumber VARCHAR(255), "
				+ "password VARCHAR(255), houseNameNumber VARCHAR(255) REFERENCES Address, postcode VARCHAR(255) REFERENCES Address)";

		String createHostAccountTable = "CREATE TABLE HostAccount("
				+ "host_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, email VARCHAR(255), FOREIGN KEY (email) REFERENCES Account(email), "
				+ "rating FLOAT , superhost BOOL)";

		String createGuestAccountTable = "CREATE TABLE GuestAccount("
				+ "guest_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
				+ "email VARCHAR(255), FOREIGN KEY (email) REFERENCES Account(email))";
//
//		String createReviewTable = "CREATE TABLE Review("
//				+ "review_id INT NOT NULL PRIMARY KEY, accuracy DEC, location DEC, "
//				+ "valueForMoney DEC, communication DEC, cleanliness DEC, description VARCHAR(255))";
//
		String createOutdoorsTable = "CREATE TABLE Outdoors("
				+ "outdoors_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, freeOnSiteParking BOOL, "
				+ "onRoadParking BOOL, paidCarPark BOOL, patio BOOL, barbeque BOOL)";

		String createLivingTable = "CREATE TABLE Living("
				+ "living_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, wifi BOOL, television BOOL, satellite BOOL, "
				+ "streaming BOOL, dvdPlayer BOOL, boardGames BOOL)";

		String createKitchenTable = "CREATE TABLE Kitchen("
				+ "kitchen_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, refrigerator BOOL, microwave BOOL, "
				+ "oven BOOL, stove BOOL, dishwasher BOOL, tableware BOOL, cookware BOOL, basicProvision BOOL)";

		String createUtilityTable = "CREATE TABLE Utility("
				+ "utility_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, heating BOOL, washingMachine BOOL, "
				+ "dryingMaching BOOL, fireExtinguisher BOOL, smokeAlarm BOOL, firstAidKit BOOL)";

		String createBedTypeTable = "CREATE TABLE BedType("
				+ "bedType_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
				+ "bed1ChoiceField VARCHAR(255), bed2ChoiceField VARCHAR(255), bed1People INT, bed2People INT)";

		String createSleepingTable = "CREATE TABLE Sleeping("
				+ "sleeping_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, bedLinen BOOL, towels BOOL, noOfBedrooms INT)";
		
		String createSleeping_BedTypeTable = "CREATE TABLE Sleeping_BedType("
				+ "PRIMARY KEY (sleeping_id, bedType_id), sleeping_id INT, "
				+ "FOREIGN KEY (sleeping_id) REFERENCES Sleeping(sleeping_id), bedType_id INT, "
				+ "FOREIGN KEY (bedType_id) REFERENCES BedType(bedType_id), peopleInBedroom INT)";

		String createBathTypeTable = "CREATE TABLE BathType("
				+ "bathType_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
				+ "toilet BOOL, bath BOOL, shower BOOL, shared BOOL)";

		String createBathingTable = "CREATE TABLE Bathing("
				+ "bathing_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, hairDryer BOOL, "
				+ "toiletPaper BOOL, noOfBathrooms INT)";

		String createBathing_BathTypeTable = "CREATE TABLE Bathing_BathType("
				+ "PRIMARY KEY (bathing_id, bathType_id), bathing_id INT, bathType_id INT, "
				+ "FOREIGN KEY (bathing_id) REFERENCES Bathing(bathing_id), "
				+ "FOREIGN KEY (bathType_id) REFERENCES BathType(bathType_id))";

		String createFacilitiesTable = "CREATE TABLE Facilities("
				+ "facilities_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, kitchen_id INT,"
				+ "FOREIGN KEY (kitchen_id) REFERENCES Kitchen(kitchen_id), "
				+ "sleeping_id INT, FOREIGN KEY (sleeping_id) references Sleeping(sleeping_id), "
				+ "bathing_id INT, FOREIGN KEY (bathing_id) REFERENCES Bathing(bathing_id), "
				+ "living_id INT, FOREIGN KEY (living_id) REFERENCES Living(living_id), "
				+ "utility_id INT, FOREIGN KEY (utility_id) REFERENCES Utility(utility_id), "
				+ "outdoors_id INT, FOREIGN KEY (outdoors_id) REFERENCES Outdoors(outdoors_id)) ";

		String createPropertyTable = "CREATE TABLE Property("
				+ "property_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, houseNameNumber VARCHAR(255), postcode VARCHAR(255), "
				+ "FOREIGN KEY (houseNameNumber, postcode) REFERENCES Address(houseNameNumber, postcode), "
				+ "FOREIGN KEY (host_id) REFERENCES HostAccount(host_id), "
				+ "host_id INT , FOREIGN KEY (host_id) REFERENCES HostAccount(host_id), "
				+ "facilities_id INT, FOREIGN KEY (facilities_id) REFERENCES Facilities(facilities_id), "
//				+ "review_id INT REFERENCES Review, "
				+ "description VARCHAR(255), shortName VARCHAR(255), guestCapacity INT)";
//
//        String createBookingTable = "CREATE TABLE Booking(booking_id INT NOT NULL PRIMARY KEY, "
//                + "property_id INT REFERENCES Property, host_id INT REFERENCES HostAccount, "
//                + "guest_id INT REFERENCES GuestAccount, review_id INT REFERENCES Review, "
//                + "provisional BOOL, totalPrice FLOAT, startDate DATE, endDate DATE)";
//
//		String createChargeBandsTable = "CREATE TABLE ChargeBands"
//				+ "(PRIMARY KEY (property_id, startDate), property_id INT REFERENCES Property, "
//				+ "startDate DATE, endDate DATE, pricePerNight DOUBLE, serviceCharge DOUBLE, "
//				+ "cleaningCharge DOUBLE, totalPricePerNight DOUBLE)";
//
//		String[] allCreateQueries = {createAddressTable, createAccountTable, createHostAccountTable, createGuestAccountTable, createReviewTable, 
//				createOutdoorsTable, createLivingTable, createKitchenTable,createUtilityTable, createBathTypeTable, createBathingTable, 
//				createBathing_BathTypeTable, createSleepingTable, createBedTypeTable, createSleeping_BedTypeTable, createFacilitiesTable, createPropertyTable, createChargeBandsTable, createBookingTable};
//		

				String[] allDropQueries = { "DROP TABLE IF EXISTS HostAccount", "DROP TABLE IF EXISTS GuestAccount","DROP TABLE IF EXISTS Account", "DROP TABLE IF EXISTS Outdoors",
				"DROP TABLE IF EXISTS Living", "DROP TABLE IF EXISTS Kitchen", "DROP TABLE IF EXISTS Utility", "DROP TABLE IF EXISTS BathType",
				"DROP TABLE IF EXISTS Bathing", "DROP TABLE IF EXISTS Bathing_BathType", "DROP TABLE IF EXISTS Sleeping",
				"DROP TABLE IF EXISTS BedType","DROP TABLE IF EXISTS Sleeping_BedType", "DROP TABLE IF EXISTS Facilities", "DROP TABLE IF EXISTS Property","DROP TABLE IF EXISTS Address"};
//				"DROP TABLE IF EXISTS Review","DROP TABLE IF EXISTS ChargeBands" , "DROP TABLE IF EXISTS Booking", "DROP TABLE IF EXISTS ACCOUNT"};
		dropAllTables(allDropQueries);

		String[] create = {createAddressTable, createAccountTable, createHostAccountTable, 
							createGuestAccountTable, createOutdoorsTable,createLivingTable, 
							createKitchenTable,createUtilityTable, createBathTypeTable, 
							createBathingTable, createBathing_BathTypeTable, createSleepingTable, 
							createBedTypeTable, createSleeping_BedTypeTable, createFacilitiesTable, 
							createPropertyTable};
		createAllTables(create);
	}

	static void createTable(String query) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team018", "team018",
				"7854a03f"); Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(query);
			System.out.println("nice");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static void dropTable(String dropQuery) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team018", "team018",
				"7854a03f"); Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(dropQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static void dropAllTables(String[] allDropQueries) {
		for (int i = 0; i < allDropQueries.length; i++) {
			dropTable(allDropQueries[i]);
			System.out.println("Dropped " + i + " in given database...");
		}
	}
	
	static void createAllTables(String[] allCreateQueries) {
	
		for (int i = 0; i < allCreateQueries.length; i++) {
			createTable(allCreateQueries[i]);
			System.out.println("Created " + i + " in given database...");
		}
	}
	
}

//drop tables doesnt work
