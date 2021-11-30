package GuestGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Controller.Controller;
import GUI.ConnectionManager;
import GUI.MainModule;
import GUI.MainModule.EDITPROPERTY;
import GUI.MainModule.USER;
import HostGUI.NavHost;
import Model.Model;

public class BookProperty extends JFrame {
	private NavHost navForHost = new NavHost();
	private JFrame frame;

	public void close() {
		frame.dispose();
	}

	/**
	 * Create the application.
	 */
	private Controller controller;
	private Model model;
	private MainModule mainModule;
	private int idAfter;
	private int propertyidAfter;

	Connection connection = null;

	private JTextField shortNameTextField;
	private JTextField guestCapacityTextField;
	private JTextField descriptionTextField;
	private JTextField numberOfBedroomsTextField;
	private JTextField numberOfBedsTextField;
	private JTextField numberOfBathsTextField;
	private JTextField jTextField_property_id;
	private JTextField startDateTextField;
	private JTextField endDateTextField;
	private int propertyIdForBooking;
	private java.util.Date formattedStartDateForComparison;
	private java.util.Date formattedEndDateForComparison;
	private java.util.Date formattedStartDateFromUser;
	private java.util.Date formattedEndDateFromUser;
	DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
	private Boolean provisionalBookingPossible;
	private Boolean datesWithinSearchCriteria;
	private String startDateFromSearch;
	private String endDateFromSearch;
	String userStartDateForBooking;
	String userEndDateForBooking;
	private int hostIdForProperty;
	private double pricePerNightForProperty;
	private static String serverName = "jdbc:mysql://stusql.dcs.shef.ac.uk/team018";
	private static String username = "team018";
	private static String pwd = "7854a03f";
	
	

	public BookProperty(MainModule mainModule, Controller controller, Model model) {
		// initializeBookProperty();
		this.model = model;
		this.mainModule = mainModule;
		this.controller = controller;
	}

	
	public Connection getConnection() {
		Connection connection;
		try {
			connection = DriverManager.getConnection(serverName, username, pwd);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeBookProperty(int propertyId, int id) {
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		JPanel bookPropertyPanel = new JPanel();
		bookPropertyPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(bookPropertyPanel, BorderLayout.CENTER);
		bookPropertyPanel.setLayout(null);

		idAfter = id;
		propertyidAfter = propertyId;
		//model.setGuestId(idAfter);
		System.out.println("THIS IS THE GUEST ID FORM THE FUNCTION:   "+idAfter);
		System.out.println("THIS IS THE GUEST ID:   "+model.getGuestId());

		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(31, 58, 91, 23);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				frame.dispose();

			}
		});
		bookPropertyPanel.add(backButton);

		JLabel bookPropertyTitleLabel = new JLabel("Property");
		bookPropertyTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		bookPropertyTitleLabel.setBounds(265, 39, 196, 55);
		bookPropertyPanel.add(bookPropertyTitleLabel);

		JLabel shortNamelabel = new JLabel("Shortname:");
		shortNamelabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		shortNamelabel.setBounds(31, 155, 112, 35);
		bookPropertyPanel.add(shortNamelabel);

		JLabel guestCapacityLabel = new JLabel("Guest Capacity:");
		guestCapacityLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		guestCapacityLabel.setBounds(31, 201, 112, 35);
		bookPropertyPanel.add(guestCapacityLabel);

		JLabel descriptionLabel = new JLabel("Description:");
		descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		descriptionLabel.setBounds(31, 247, 112, 35);
		bookPropertyPanel.add(descriptionLabel);

		JLabel numOfBedroomsLabel = new JLabel("Number of Bedrooms");
		numOfBedroomsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		numOfBedroomsLabel.setBounds(31, 367, 160, 35);
		bookPropertyPanel.add(numOfBedroomsLabel);

		shortNameTextField = new JTextField();
		shortNameTextField.setEditable(false);
		shortNameTextField.setBounds(166, 161, 360, 29);
		bookPropertyPanel.add(shortNameTextField);
		shortNameTextField.setColumns(10);

		guestCapacityTextField = new JTextField();
		guestCapacityTextField.setEditable(false);
		guestCapacityTextField.setColumns(10);
		guestCapacityTextField.setBounds(166, 201, 131, 29);
		bookPropertyPanel.add(guestCapacityTextField);

		descriptionTextField = new JTextField();
		descriptionTextField.setEditable(false);
		descriptionTextField.setColumns(10);
		descriptionTextField.setBounds(166, 247, 360, 93);
		bookPropertyPanel.add(descriptionTextField);

		JButton btnBookProperty = new JButton("Book Property");
		btnBookProperty.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
/*				Boolean startDateIsEmpty = startDateTextField.getText().isBlank();
				Boolean endDateIsEmpty = endDateTextField.getText().isBlank();
				if (!startDateIsEmpty && !endDateIsEmpty) {
					// checking date format:
					Boolean startDateValidation = startDateTextField.getText().matches("\\d{2}/\\d{2}/\\d{4}");
					Boolean endDateValidation = endDateTextField.getText().matches("\\d{2}/\\d{2}/\\d{4}");
					
					// checking if date matches DATE object format
					if (startDateValidation && endDateValidation) {
						
						// Date formattedEndDate = parseDate(endDate.getText());
						// checking if start date < end date
						// NOTE: cant do the following time check until dates are converted to date
						// objects
						Boolean timeCheck = formattedStartDate.before(formattedEndDate);
				

						// checking if dates are in the year 2022
						String startDateParts[] = startDateTextField.getText().split("/");
						String endDateParts[] = endDateTextField.getText().split("/");

						// Getting day, month, and year for start date:
						int startDay = Integer.parseInt(startDateParts[0]);
						int startMonth = Integer.parseInt(startDateParts[1]);
						int startYear = Integer.parseInt(startDateParts[2]);

						// getting day, month, and year for end date:
						int endDay = Integer.parseInt(endDateParts[0]);
						int endMonth = Integer.parseInt(endDateParts[1]);
						int endYear = Integer.parseInt(endDateParts[2]);

						// call function to validate the date:
						Boolean startDateAccepted = validateDate(startDay, startMonth, startYear);
						Boolean endDateAccepted = validateDate(endDay, endMonth, endYear);

						// finally checking if the start time is less than the end time:
						if !(timeCheck && startDateAccepted && endDateAccepted) {
							displayInvalidStartEndTimeMessage();
						}

					} else {
						displayInvalidDateMessage();

					}

				} else {
					displayEmptyStringsMessage();
				}
				
*/				
				//check if user is logged in first:
				if(mainModule.userState == USER.ENQUIRER || mainModule.userState == USER.HOST) {
					displayMessageToLoginAsGuest();
				}else if(mainModule.userState == USER.GUEST){
					
					// first check if start and end dates entered on this page are within start date and 
					// end date entered on search page 
					
					userStartDateForBooking = startDateTextField.getText();
					userEndDateForBooking = endDateTextField.getText();
					startDateFromSearch = model.getSD();
					endDateFromSearch = model.getED();
					
					//checking if search dates overlap with input dates:
					// this should be true to in order to add the booking
					Boolean datesWithinSearchCriteria = checkingForDatesOverlap(userStartDateForBooking,userEndDateForBooking,startDateFromSearch,endDateFromSearch);					
					
					// checking if a booking already exists within the users dates
					Boolean provisonalBookingIsPossible = checkForExistingBooking(userStartDateForBooking, userEndDateForBooking);
					
					System.out.println("booking dates within start&end dates from search: "+datesWithinSearchCriteria);
					System.out.println("Is the BOOKING POSSIBLE???? "+provisonalBookingIsPossible);
				
					
					// if the users start and end dates are within search start&end dates AND a booking doesnt already exists
					// between these dates, then add the booking:
					if (datesWithinSearchCriteria && provisonalBookingIsPossible) {
						// add booking to the table
						addBooking();
						// show pane saying booking accepted
						
					}else if(!datesWithinSearchCriteria) {
						displayMessageForSearchDate();

					}else if(!provisonalBookingIsPossible) {
						displayMessageUnableToBook();
					}
					
					System.out.println("GOT HERE");

				}
				
			}
		});
		btnBookProperty.setBounds(195, 725, 237, 29);
		bookPropertyPanel.add(btnBookProperty);

		JButton reviewsButton = new JButton("Property Reviews");
		reviewsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState = EDITPROPERTY.REVIEWS;
				// needs to take in the properyId and hostId
				model.setPropertyId(Integer.parseInt(jTextField_property_id.getText()));
				MainModule.controller.editPropertyView(Integer.parseInt(jTextField_property_id.getText()),
						model.getHostId());
				frame.dispose();
			}
		});
		reviewsButton.setBounds(207, 548, 196, 29);
		bookPropertyPanel.add(reviewsButton);

		JLabel numOfBedsLabel = new JLabel("Number of Beds");
		numOfBedsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		numOfBedsLabel.setBounds(31, 427, 160, 35);
		bookPropertyPanel.add(numOfBedsLabel);

		JLabel numOfBathsLabel = new JLabel("Number of Baths");
		numOfBathsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		numOfBathsLabel.setBounds(31, 484, 160, 35);
		bookPropertyPanel.add(numOfBathsLabel);

		numberOfBedroomsTextField = new JTextField();
		numberOfBedroomsTextField.setEditable(false);
		numberOfBedroomsTextField.setText("");
		numberOfBedroomsTextField.setColumns(10);
		numberOfBedroomsTextField.setBounds(195, 367, 331, 29);
		bookPropertyPanel.add(numberOfBedroomsTextField);

		numberOfBedsTextField = new JTextField();
		numberOfBedsTextField.setEditable(false);
		numberOfBedsTextField.setText("");
		numberOfBedsTextField.setColumns(10);
		numberOfBedsTextField.setBounds(195, 427, 331, 29);
		bookPropertyPanel.add(numberOfBedsTextField);

		numberOfBathsTextField = new JTextField();
		numberOfBathsTextField.setEditable(false);
		numberOfBathsTextField.setText("");
		numberOfBathsTextField.setColumns(10);
		numberOfBathsTextField.setBounds(195, 493, 331, 29);
		bookPropertyPanel.add(numberOfBathsTextField);

		JLabel lblPropertyId = new JLabel("Property ID:");
		lblPropertyId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPropertyId.setBounds(31, 116, 112, 35);
		bookPropertyPanel.add(lblPropertyId);

		jTextField_property_id = new JTextField();
		jTextField_property_id.setEditable(false);
		jTextField_property_id.setText("");
		jTextField_property_id.setColumns(10);
		jTextField_property_id.setBounds(166, 121, 360, 29);
		bookPropertyPanel.add(jTextField_property_id);

		// SET PROPETY ID BOX
		jTextField_property_id.setText(String.valueOf(propertyId));

		// INSERT Property INFO INTO TEXT FIELDS
		try {
			connection = ConnectionManager.getConnection();

			Statement stmt = connection.createStatement();
			String getInfo = "SELECT shortName, guestCapacity, description FROM Property WHERE property_id = "
					+ propertyidAfter;
			ResultSet rs = stmt.executeQuery(getInfo);
			int guestCapacity = 0;
			String shortName = "";
			String description = "";
			

			while (rs.next()) {
				shortName = rs.getString("shortName");
				guestCapacity = rs.getInt("guestCapacity");
				description = rs.getString("description");
			}
			
			
			String guestCapacityString = String.valueOf(guestCapacity);
			
			shortNameTextField.setText(shortName);
			guestCapacityTextField.setText(guestCapacityString);
			descriptionTextField.setText(description);

			// INSERT facilities INTFO into text fields
			Statement stmt2 = connection.createStatement();
			String getSleeping = "SELECT sleeping_id FROM Facilities WHERE property_id = " + propertyidAfter;
			ResultSet rs2 = stmt2.executeQuery(getSleeping);

			int sleeping = 0;
			while (rs2.next()) {
				sleeping = rs2.getInt("sleeping_id");
			}

			Statement stmt3 = connection.createStatement();
			String getBedrooms = "SELECT noOfBedrooms FROM Sleeping WHERE sleeping_id = " + sleeping;
			ResultSet rs3 = stmt3.executeQuery(getBedrooms);

			int bedrooms = 0;
			while (rs3.next()) {
				bedrooms = rs3.getInt("noOfBedrooms");
			}
			Statement stmt4 = connection.createStatement();
			String getBeds = "SELECT noOfBeds FROM Sleeping WHERE sleeping_id = " + sleeping;
			ResultSet rs4 = stmt4.executeQuery(getBeds);

			int beds = 0;
			while (rs4.next()) {
				beds = rs4.getInt("noOfBeds");
			}

			Statement stmt5 = connection.createStatement();
			String getBathing = "SELECT bathing_id FROM Facilities WHERE property_id = " + propertyidAfter;
			ResultSet rs5 = stmt5.executeQuery(getBathing);

			int bathing = 0;
			while (rs5.next()) {
				bathing = rs5.getInt("bathing_id");
			}
			Statement stmt6 = connection.createStatement();
			String getBathrooms = "SELECT noOfBathrooms FROM Bathing WHERE bathing_id = " + bathing;
			ResultSet rs6 = stmt6.executeQuery(getBathrooms);

			int bathrooms = 0;
			while (rs6.next()) {
				bathrooms = rs6.getInt("noOfBathrooms");
			}
			numberOfBedroomsTextField.setText(String.valueOf(bedrooms));
			numberOfBedsTextField.setText(String.valueOf(beds));
			numberOfBathsTextField.setText(String.valueOf(bathrooms));
			
			JLabel lblEndDate = new JLabel("End Date:");
			lblEndDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblEndDate.setBounds(125, 670, 160, 35);
			bookPropertyPanel.add(lblEndDate);
			
			JLabel lblStartDate = new JLabel("Start Date:");
			lblStartDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblStartDate.setBounds(125, 630, 160, 35);
			bookPropertyPanel.add(lblStartDate);
			
			startDateTextField = new JTextField();
			startDateTextField.setText("");
			startDateTextField.setColumns(10);
			startDateTextField.setBounds(289, 630, 203, 29);
			bookPropertyPanel.add(startDateTextField);
			
			endDateTextField = new JTextField();
			endDateTextField.setText("");
			endDateTextField.setColumns(10);
			endDateTextField.setBounds(289, 679, 203, 29);
			bookPropertyPanel.add(endDateTextField);
			
			JLabel lblNewLabel = new JLabel("Book this Property:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel.setBounds(125, 605, 196, 14);
			bookPropertyPanel.add(lblNewLabel);
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}

		frame.setBounds(100, 100, 600, 800);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void addBooking() {
		
		try {
			connection = ConnectionManager.getConnection();
			System.out.println("IN PLACE TO ADD BOOKING TO THE DB");


			String addBookingQuery = "insert into Booking (property_id, host_id, guest_id, provisional, "
					+ "totalPrice, startDate, endDate)" + " values(?,?,?,?,?,?,?)";
			PreparedStatement addBookingPS = connection.prepareStatement(addBookingQuery);


			addBookingPS.setInt(1, propertyIdForBooking);
			try {
			//GEtting the hostID for this particular propertyId
				ResultSet rs = null;
				String queryToGetHostID = "Select host_id from Property where property_id=?";
				PreparedStatement hostIDFromPropertyTable;
			
				hostIDFromPropertyTable = connection.prepareStatement(queryToGetHostID);
				hostIDFromPropertyTable.setInt(1, propertyIdForBooking);
				rs = hostIDFromPropertyTable.executeQuery();
				while (rs.next()){
					hostIdForProperty = rs.getInt(1);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("THIS IS THE host ID "+hostIdForProperty+" belonging to the property being booked: "+propertyIdForBooking);
			
			
			addBookingPS.setInt(2, hostIdForProperty);
			
			addBookingPS.setInt(3, model.getGuestId());
			addBookingPS.setBoolean(4, true);
			try {
			//getting the total price for the property being booked
				ResultSet rsForPrice = null;
				String queryForPricePerNight = "Select totalPricePerNight from ChargeBands where property_id=?";
				PreparedStatement pricePerNightFromCB;
				
				pricePerNightFromCB = connection.prepareStatement(queryForPricePerNight);
				pricePerNightFromCB.setInt(1, propertyIdForBooking);
				rsForPrice = pricePerNightFromCB.executeQuery();
				while (rsForPrice.next()){
					pricePerNightForProperty = rsForPrice.getInt(1);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("THIS IS THE price per night ID "+pricePerNightForProperty+" belonging to the property being booked: "+propertyIdForBooking);
			
			addBookingPS.setDouble(5, pricePerNightForProperty);
			addBookingPS.setString(6, userStartDateForBooking);
			addBookingPS.setString(7, userEndDateForBooking);
			
			
			addBookingPS.executeUpdate();

			connection.close();
			
		} catch (Exception e2) {
			System.err.println("Got an exception!");
			System.err.println(e2.getMessage());
		}

	}

	public Boolean validateDate(int day, int month, int year) {

		boolean dateAccepted = false;
		if (year == 2022 && month >= 1 && month <= 12 && day >= 1) {

			switch (month) {
			// jan
			case 1:
				if (day <= 31)
					dateAccepted = true;
				break;
			// feb
			case 2:
				if (day <= 28)
					dateAccepted = true;
				break;
			// march
			case 3:
				if (day <= 31)
					dateAccepted = true;
				break;
			// april
			case 4:
				if (day <= 30)
					dateAccepted = true;
				break;
			// may
			case 5:
				if (day <= 31)
					dateAccepted = true;
				break;
			// june
			case 6:
				if (day <= 30)
					dateAccepted = true;

				break;
			// july
			case 7:
				if (day <= 31)
					dateAccepted = true;
				break;
			// aug
			case 8:
				if (day <= 31)
					dateAccepted = true;

				break;
			// sept
			case 9:
				if (day <= 30)
					dateAccepted = true;
				break;
			// oct
			case 10:
				if (day <= 31)
					dateAccepted = true;
				break;
			// nov
			case 11:
				if (day <= 30)
					dateAccepted = true;
				break;
			// dec
			case 12:
				if (day <= 31)
					dateAccepted = true;
				break;

			default:
				return dateAccepted = false;
			}

		} else {
			return dateAccepted = false;
		}

		return dateAccepted;

	}
	
	private boolean datesOverlap;
	
	// checking if dates entered are within dates from search:
	public boolean checkingForDatesOverlap(String userStartDateForBooking, String userEndDateForBooking, String startDateForComparison, String endDateForComparison) {

		try {
			formattedStartDateForComparison= sourceFormat.parse(startDateForComparison);
			formattedEndDateForComparison = sourceFormat.parse(endDateForComparison);
			formattedStartDateFromUser = sourceFormat.parse(userStartDateForBooking);
			formattedEndDateFromUser = sourceFormat.parse(userEndDateForBooking);

		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		// doing following checks:
		// userStartDateInput and userEndDateInput are equal to the dates in startDate and EndDate for booking OR
		// userStartDateInput is equal to the startdate from the table and userEndDateInput is less than the end date from the table OR
		// userStartDateInput is after the start date from the table and userEndDate is equal to the end date form the table OR
		// userStartDateInput is after start date from the table and userEndDateInput before the end date from table
		//if one of these conditions are true then a booking has already been made for this time

		if (	(formattedStartDateFromUser.equals(formattedStartDateForComparison) && formattedEndDateFromUser.equals(formattedEndDateForComparison)) 
				|| (formattedStartDateFromUser.equals(formattedStartDateForComparison) && formattedEndDateFromUser.before(formattedEndDateForComparison))
				|| (formattedStartDateFromUser.after(formattedStartDateForComparison) && formattedEndDateFromUser.equals(formattedEndDateForComparison))
				|| (formattedStartDateFromUser.after(formattedStartDateForComparison) && formattedEndDateFromUser.before(formattedEndDateForComparison))) {
				//break out of the loop
				
			datesOverlap = true;
		}
		else {
			// the dates dont overlap
			datesOverlap = false;
		}
		
		return datesOverlap;
	}
	
	
	//checking if booking overlaps with current bookings
	public boolean checkingForOverlappingBookingDates(String userStartDateForBooking, String userEndDateForBooking, String startDateForComparison, String endDateForComparison) {

		try {
			formattedStartDateForComparison= sourceFormat.parse(startDateForComparison);
			formattedEndDateForComparison = sourceFormat.parse(endDateForComparison);
			formattedStartDateFromUser = sourceFormat.parse(userStartDateForBooking);
			formattedEndDateFromUser = sourceFormat.parse(userEndDateForBooking);

		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		// doing following checks:
		//checking if the booking can be made
		// need to check if the dates are INSIDE the star&end dates that aleardy exist for booking AND:
		// check 4 more things:
		// if user end date is after the start date from booking and before the end date from booking
		// if user start date is after the start date from booking and before the end date from booking
		// if the user start date ==  booking start date
		// if the user start date == booking end date
		// if the user end date == booking start date
		// if the user end date == booking end date
		
		
		if (	(formattedStartDateFromUser.after(formattedStartDateForComparison) && formattedStartDateFromUser.before(formattedEndDateForComparison)) 
				|| (formattedEndDateFromUser.after(formattedStartDateForComparison) && formattedEndDateFromUser.before(formattedEndDateForComparison))
				|| (formattedStartDateFromUser.equals(formattedStartDateForComparison))
				|| (formattedStartDateFromUser.equals(formattedEndDateForComparison))
				|| (formattedEndDateFromUser.equals(formattedStartDateForComparison))
				|| (formattedEndDateFromUser.equals(formattedEndDateForComparison)) ){
				//break out of the loop
				
			datesOverlap = true;
		}
		else {
			// the dates dont overlap
			datesOverlap = false;
		}
		
		return datesOverlap;
	}
	
	// checking for existing bookings in the Bookings table
	public boolean checkForExistingBooking(String userStartDate, String userEndDate) {
		provisionalBookingPossible = true;
		
		
		try {
			
			String propertyBookingQuery = "select property_id, startDate, endDate from Booking where property_id=?";
			PreparedStatement bookingPS = connection.prepareStatement(propertyBookingQuery);
			propertyIdForBooking = Integer.parseInt(jTextField_property_id.getText());
			bookingPS.setInt(1,propertyIdForBooking);
			ResultSet bookingRS = bookingPS.executeQuery();

			//looping through result set of all the bookings for a particular propertyID
			while(bookingRS.next()) {
				// getting start date and end date from booking table
				String startDateFromBookingTable = bookingRS.getString("startDate");
				String endDateFromBookingTable = bookingRS.getString("endDate");

				Boolean datesOvelapWithExistingBookings = checkingForOverlappingBookingDates(userStartDate, userEndDate, startDateFromBookingTable, endDateFromBookingTable);
				
				
				if (datesOvelapWithExistingBookings) {
					provisionalBookingPossible = false;
					System.out.println("breaking out of the loop because a booking between these dates already exists");
					break;
				}else {
					System.out.println("The booking is possible");
					provisionalBookingPossible = true;

				}
				
			
			}
		}
		catch(SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		return provisionalBookingPossible;
		
	}
	
	public void displayInvalidDateMessage() {
		JOptionPane.showMessageDialog(this,
				"Please check entries for prices and dates. Prices should be numbers or decimals. Please type dates in the format DD/MM/YYYY.");
	}

	public void displayEmptyStringsMessage() {
		JOptionPane.showMessageDialog(this, "All fields must be completed to add a chargeband");
	}

	public void displayInvalidStartEndTimeMessage() {
		JOptionPane.showMessageDialog(this,
				"The start date is after end date or the dates you have picked are not in 2022. ");
	}

	public boolean validatePricingFields(String money) {
		if (money.matches("[0-9]*") && (money.length() >= 1)) {
			// System.out.println("First name contains a characters not between a-z or
			// A-Z");
			return true;
		} else {
			return false;
		}

	}

	public void displayMessageToLoginAsGuest() {
		JOptionPane.showMessageDialog(this, "You must be logged in as a guest to book a property.");
	}
	public void displayMessageForSearchDate() {
		JOptionPane.showMessageDialog(this, "The dates entered are not within the search dates on the previous page. ");
	}
	public void displayMessageUnableToBook() {
		JOptionPane.showMessageDialog(this, "The property has already been booked for these dates. Please choose different a different start date or end date. ");
	}

}