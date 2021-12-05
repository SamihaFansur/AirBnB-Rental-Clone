package GuestGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.time.temporal.ChronoUnit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Controller.Controller;
import GUI.MainModule;
import GUI.MainModule.EDITPROPERTY;
import GUI.MainModule.STATE;
import GUI.MainModule.USER;
import Model.Model;

/*
 * Class for displaying booking objects and initialising reviews for the bookings.
 */
public class Bookings extends javax.swing.JFrame {

	private Controller controller;
	private Model model;
	private MainModule mainModule;

	/**
	 * Gui for displaying all of the booking objects within the database in a table.
	 */
	public Bookings(MainModule mainModule, Controller controller, Model model) {
		this.model = model;
		this.mainModule = mainModule;
		this.controller = controller;

		initComponents();
		Show_Bookings_In_JTable();
	}

	// Gets the connection to the database
	private static String serverName = "jdbc:mysql://stusql.dcs.shef.ac.uk/team018";
	private static String username = "team018";
	private static String pwd = "7854a03f";

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

	DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");

	
	// Creates a list of bookings from mysql database and puts them into an ArrayList
	// to use.
	public ArrayList<BookingObject> getBookingsList() {

		ArrayList<BookingObject> bookingsList = new ArrayList<>();
		Connection connection = getConnection();
		if (mainModule.userState == USER.GUEST) {

			// Gets the information for a booking from the guest database
			try {
				
				//cc, sc, overall price, total number of nights
				
				String query = "SELECT * FROM `Booking` where guest_id=? and provisional!=?";
				PreparedStatement st = connection.prepareStatement(query);
				st.setInt(1, model.getGuestId());
				st.setString(2, "Pending");

				ResultSet rs = st.executeQuery();

				BookingObject bookings;
				
				while (rs.next()) {
					
					// use dates to comapre against chargeband dates later to find which chargeband the booking was made with.
					String bookingStartDate = rs.getString("startDate");
					String bookingEndDate = rs.getString("endDate");
					int bookingPropertyId = rs.getInt("property_id");
					
					// for this property id, go to chargebands table and get sc, cc, total price per night, and start 
					// date and end date
					String propertyInfoQuery = "SELECT * FROM ChargeBands where property_id=?";
					PreparedStatement propertyInfoPS = connection.prepareStatement(propertyInfoQuery);
					propertyInfoPS.setInt(1, bookingPropertyId);

					ResultSet propertyInfoRS = propertyInfoPS.executeQuery();
					
					String chargeBandStartDate ="";
					String chargeBandEndDate ="";
					double propertyServiceCharge = 0.0;
					double propertyCleaningCharge = 0.0;
					double propertyTotalPricePerNight= 0.0;
					
					Date formattedCBStartDate = null;
					Date formattedCBEndDate = null;
					Date formattedBookingStartDate= null;
					Date formattedBookingEndDate = null;
					
					while(propertyInfoRS.next()) {
						chargeBandStartDate = propertyInfoRS.getString("startDate");
						chargeBandEndDate = propertyInfoRS.getString("endDate");
						
						//converting string dates into Date object:
						try {
							formattedCBStartDate = sourceFormat.parse(chargeBandStartDate);
							formattedCBEndDate = sourceFormat.parse(chargeBandEndDate);
							formattedBookingStartDate = sourceFormat.parse(bookingStartDate);
							formattedBookingEndDate = sourceFormat.parse(bookingEndDate);
						} catch (Exception e1) {
							e1.printStackTrace();
						}

						//checking booking start and end dates between charge band start and end dates:
						// if the following statement is true then the booking start dates and end dates exist within this
						// chargeband. Get the cleaning charge, service charge...
						if( (formattedBookingStartDate.equals(formattedCBStartDate) && formattedBookingEndDate.equals(formattedCBEndDate))
								|| (formattedBookingStartDate.after(formattedCBStartDate) && formattedBookingEndDate.equals(formattedCBEndDate)) 	
								|| (formattedBookingStartDate.after(formattedCBStartDate) && formattedBookingEndDate.before(formattedCBEndDate)) 	
								|| (formattedBookingStartDate.equals(formattedCBStartDate) && formattedBookingEndDate.before(formattedCBEndDate)) 	
								 ) {
							propertyServiceCharge = propertyInfoRS.getDouble("serviceCharge");
							propertyCleaningCharge = propertyInfoRS.getDouble("cleaningCharge");
							propertyTotalPricePerNight = propertyInfoRS.getDouble("totalPricePerNight");
					
							//Get the cleaning charge, service charge...	
							System.out.println("breaking out of the loop because booking dates exists within this CB dates");
							break;
						} 
						
						
					}
					
					System.out.println("cleaning charge: "+propertyCleaningCharge);
					System.out.println("service charge: "+propertyServiceCharge);
					System.out.println("total price per night: "+propertyTotalPricePerNight);
					
					//calculating total price for all nights
					double totalPriceForAllNights = 0.0;
					long timeDifference = formattedBookingEndDate.getTime() - formattedBookingStartDate.getTime();
				    long diffDays = timeDifference / (24 * 60 * 60 * 1000);
				    int days = (int) Math.ceil(diffDays);
					

					System.out.println("tempPropertyId: "+bookingPropertyId);
					System.out.println("Number of days between the dates: "+days);
					
					totalPriceForAllNights = days*propertyTotalPricePerNight;
					System.out.println("Total price per nights: "+totalPriceForAllNights);
					
					
					bookings = new BookingObject(rs.getInt("booking_id"), rs.getInt("property_id"), 
							propertyServiceCharge, propertyCleaningCharge,
							totalPriceForAllNights, (int)days, 
							rs.getString("provisional"), rs.getString("startDate"),
							rs.getString("endDate"));
					bookingsList.add(bookings);
					
				}
				 
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Gets the information for a booking from the host database
		} else if (mainModule.userState == USER.HOST) {
			try {
				String query = "SELECT * FROM `Booking` where host_id=? and provisional!=?";
				PreparedStatement st = connection.prepareStatement(query);
				st.setInt(1, model.getHostId());
				st.setString(2, "Pending");

				ResultSet rs = st.executeQuery();
				BookingObject bookings;
				while (rs.next()) {
					
					// use dates to comapre against chargeband dates later to find which chargeband the booking was made with.
					String bookingStartDate = rs.getString("startDate");
					String bookingEndDate = rs.getString("endDate");
					int bookingPropertyId = rs.getInt("property_id");
					
					// for this property id, go to chargebands table and get sc, cc, total price per night, and start 
					// date and end date
					String propertyInfoQuery = "SELECT * FROM ChargeBands where property_id=?";
					PreparedStatement propertyInfoPS = connection.prepareStatement(propertyInfoQuery);
					propertyInfoPS.setInt(1, bookingPropertyId);

					ResultSet propertyInfoRS = propertyInfoPS.executeQuery();
					
					String chargeBandStartDate ="";
					String chargeBandEndDate ="";
					double propertyServiceCharge = 0.0;
					double propertyCleaningCharge = 0.0;
					double propertyTotalPricePerNight= 0.0;
					
					Date formattedCBStartDate = null;
					Date formattedCBEndDate = null;
					Date formattedBookingStartDate= null;
					Date formattedBookingEndDate = null;
					
					while(propertyInfoRS.next()) {
						chargeBandStartDate = propertyInfoRS.getString("startDate");
						chargeBandEndDate = propertyInfoRS.getString("endDate");
						
						//converting string dates into Date object:
						try {
							formattedCBStartDate = sourceFormat.parse(chargeBandStartDate);
							formattedCBEndDate = sourceFormat.parse(chargeBandEndDate);
							formattedBookingStartDate = sourceFormat.parse(bookingStartDate);
							formattedBookingEndDate = sourceFormat.parse(bookingEndDate);
						} catch (Exception e1) {
							e1.printStackTrace();
						}

						//checking booking start and end dates between charge band start and end dates:
						// if the following statement is true then the booking start dates and end dates exist within this
						// chargeband. Get the cleaning charge, service charge...
						if( (formattedBookingStartDate.equals(formattedCBStartDate) && formattedBookingEndDate.equals(formattedCBEndDate))
								|| (formattedBookingStartDate.after(formattedCBStartDate) && formattedBookingEndDate.equals(formattedCBEndDate)) 	
								|| (formattedBookingStartDate.after(formattedCBStartDate) && formattedBookingEndDate.before(formattedCBEndDate)) 	
								|| (formattedBookingStartDate.equals(formattedCBStartDate) && formattedBookingEndDate.before(formattedCBEndDate)) 	
								 ) {
							propertyServiceCharge = propertyInfoRS.getDouble("serviceCharge");
							propertyCleaningCharge = propertyInfoRS.getDouble("cleaningCharge");
							propertyTotalPricePerNight = propertyInfoRS.getDouble("totalPricePerNight");
					
							//Get the cleaning charge, service charge...	
							System.out.println("breaking out of the loop because booking dates exists within this CB dates");
							break;
						} 
						
						
					}
					
					System.out.println("cleaning charge: "+propertyCleaningCharge);
					System.out.println("service charge: "+propertyServiceCharge);
					System.out.println("total price per night: "+propertyTotalPricePerNight);
					
					//calculating total price for all nights
					double totalPriceForAllNights = 0.0;
					long timeDifference = formattedBookingEndDate.getTime() - formattedBookingStartDate.getTime();
				    long diffDays = timeDifference / (24 * 60 * 60 * 1000);
				    int days = (int) Math.ceil(diffDays);
					

					System.out.println("tempPropertyId: "+bookingPropertyId);
					System.out.println("Number of days between the dates: "+days);
					
					totalPriceForAllNights = days*propertyTotalPricePerNight;
					System.out.println("Total price per nights: "+totalPriceForAllNights);
					
					
					bookings = new BookingObject(rs.getInt("booking_id"), rs.getInt("property_id"), 
							propertyServiceCharge, propertyCleaningCharge,
							totalPriceForAllNights, (int)days, 
							rs.getString("provisional"), rs.getString("startDate"),
							rs.getString("endDate"));
					bookingsList.add(bookings);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return bookingsList;
	}

	// Displays the Booking Objects from the Booking ArrayList In a JTable
	public void Show_Bookings_In_JTable() {
		ArrayList<BookingObject> list = getBookingsList();
		DefaultTableModel model = (DefaultTableModel) jTable_Display_Bookings.getModel();
		Object[] row = new Object[9];
		for (BookingObject element : list) {
			row[0] = element.getBooking_id();
			row[1] = element.getProperty_id();
			row[2] = element.getServiceCharge();
			row[3] = element.getCleaningCharge();
			row[4] = element.getOverallPrice();
			row[5] = element.getNoOfNights();
			row[6] = element.getProvisional();
			row[7] = element.getStartDate();
			row[8] = element.getEndDate();
			model.addRow(row);
		}
	}

	// Execute The Insert Update And Delete Querys
	public void executeSQlQuery(String query, String message) {
		Connection con = getConnection();
		Statement st;
		try {
			st = con.createStatement();
			if ((st.executeUpdate(query)) == 1) {
				// refresh jtable data
				DefaultTableModel model = (DefaultTableModel) jTable_Display_Bookings.getModel();
				model.setRowCount(0);
				Show_Bookings_In_JTable();

				JOptionPane.showMessageDialog(null, "Data " + message + " Successfully");
			} else {
				JOptionPane.showMessageDialog(null, "Data Not " + message);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	// Function for defining all of the GUI objects
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();

		jTextField_booking_id = new javax.swing.JTextField();
		jTextField_property_id = new javax.swing.JTextField();
		jTextField_sleeping_id = new javax.swing.JTextField();
		jTextField_bathing_id = new javax.swing.JTextField();
		jTextField_living_id = new javax.swing.JTextField();

		jScrollPane1 = new javax.swing.JScrollPane();
		jTable_Display_Bookings = new javax.swing.JTable();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jPanel1.setBackground(new java.awt.Color(204, 255, 255));

		jLabel1.setFont(new java.awt.Font("Verdana", 0, 18));
		jLabel1.setText("Booking_ID:");

		jLabel2.setFont(new java.awt.Font("Verdana", 0, 18));
		jLabel2.setText("Property_ID");

		jLabel5.setFont(new java.awt.Font("Verdana", 0, 18));
		jLabel5.setText("Sleeping:");

		jLabel6.setFont(new java.awt.Font("Verdana", 0, 18));
		jLabel6.setText("Bathing:");

		jLabel7.setFont(new java.awt.Font("Verdana", 0, 18));
		jLabel7.setText("Living:");

		jTextField_booking_id.setFont(new java.awt.Font("Verdana", 0, 14));

		jTextField_property_id.setFont(new java.awt.Font("Verdana", 0, 14));

		jTextField_sleeping_id.setFont(new java.awt.Font("Verdana", 0, 14));

		jTextField_bathing_id.setFont(new java.awt.Font("Verdana", 0, 14));

		jTextField_living_id.setFont(new java.awt.Font("Verdana", 0, 14));

		
		jTable_Display_Bookings
				.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] { 
						"Booking Id", "Property Id", "Sercive Charge (£)", "Cleaning Charge (£)",
						"Overall Price (£)", "Total Nights", "Provisional", "Start Date", "End Date" }));

		jTable_Display_Bookings.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jTable_Display_BookingsMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(jTable_Display_Bookings);

		backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mainModule.userState == USER.HOST) {
					mainModule.currentState = STATE.HOST_ACCOUNT;
					MainModule.controller.drawNewView();
					setVisible(false);
				} else {
					mainModule.userState = USER.GUEST;
					mainModule.currentState = STATE.GUEST_ACCOUNT;
					MainModule.controller.drawNewView();
					setVisible(false);
				}
			}
		});

		JButton reviewButton = new JButton("Review");
		reviewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mainModule.userState == USER.GUEST) {
					mainModule.editPropertyState = EDITPROPERTY.REVIEW;
					MainModule.controller.editPropertyView(Integer.parseInt(jTextField_property_id.getText()),
							Integer.parseInt(jTextField_booking_id.getText()));
					setVisible(false);
				}
			}
		});
		reviewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));

		
		confidentialInformationButton = new JButton("View Confidential Info");
		confidentialInformationButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		confidentialInformationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//first check if the booking is accepted.
				// get booking id, propid, hostid, guest id,
				// use above to show guest info in a pop up
				
				
				
				try {
					bookingId = Integer.parseInt(jTextField_booking_id.getText().toString());
					hostId = 0;
					guestId = 0;
					propId = Integer.parseInt(jTextField_property_id.getText().toString());


					Connection connection = getConnection();
					
					String checkIfBookingAccepted = "select booking_id, host_id, guest_id from Booking where booking_id=? and provisional=?";
					PreparedStatement checksProvisional = connection.prepareStatement(checkIfBookingAccepted);
					checksProvisional.setInt(1, bookingId);
					checksProvisional.setString(2, "Accepted");
					
					//this resultset will gimme booking id for all accepted bookings
					//now need to get confi info
					ResultSet getsConfidentialInfo = checksProvisional.executeQuery();
					while(getsConfidentialInfo.next()) {
						hostId = getsConfidentialInfo.getInt("host_id");
						guestId = getsConfidentialInfo.getInt("guest_id");

					
						System.out.println("Booking: "+bookingId);
						System.out.println("Guest id: "+guestId);
						System.out.println("host id: "+hostId);
						
						String addressInfo = "select address_id from Property where property_id=?";
						PreparedStatement getAddressInfo = connection.prepareStatement(addressInfo);
						getAddressInfo.setInt(1, propId);

				
						System.out.println("property id: "+propId);

						int addressIdProperty = 0;
						ResultSet gettingAddressIdProperty = getAddressInfo.executeQuery();
						while(gettingAddressIdProperty.next()) {
							addressIdProperty = gettingAddressIdProperty.getInt("address_id");

							String propertyInformation = "select houseNameNumber, streetName, placeName, postcode from Address where address_id=?";
							PreparedStatement getPropertyInfoPS = connection.prepareStatement(propertyInformation);
							getPropertyInfoPS.setInt(1, addressIdProperty);
							
							System.out.println("addressid property: "+addressIdProperty);
							
							houseNameNumber = "";
							streetName = "";
							placeName = "";
							postcode = "";


							ResultSet gettingPropertyInfo = getPropertyInfoPS.executeQuery();

							while(gettingPropertyInfo.next()) {
								houseNameNumber = gettingPropertyInfo.getString("houseNameNumber");
								streetName = gettingPropertyInfo.getString("streetName");
								placeName = gettingPropertyInfo.getString("placeName");
								postcode = gettingPropertyInfo.getString("postcode");
							}
						
							System.out.println("houseNameNumber: "+houseNameNumber);
							System.out.println("streetName: "+streetName);
							System.out.println("placeName: "+placeName);
							System.out.println("postcode: "+postcode);



						}
						
						

						
						/////////-----host confidential info--------------
						
						// first go to hostAccoutn table to get email using host_id, 
						//se email to get address id from address table
						// use address id to get hosts address from address table
						//use email to get hosts confidential info from Account table
						
						String hostEmailQuery = "select email from HostAccount where host_id=?";
						PreparedStatement hostEmailPS = connection.prepareStatement(hostEmailQuery);
						hostEmailPS.setInt(1, hostId);
						ResultSet hostIdRS = hostEmailPS.executeQuery();
						hostEmail = "";
						while(hostIdRS.next()) {
							hostEmail = hostIdRS.getString("email");
						}
						
						System.out.println("host email = "+hostEmail);
						
						// getting hosts personal information from Account table (including address id to be used later):
						String hostInfoIDQuery = "select title, firstName, surname, mobileNumber, address_id from Account where email=?";
						PreparedStatement hostInfoPS = connection.prepareStatement(hostInfoIDQuery);
						hostInfoPS.setString(1, hostEmail);
						ResultSet hostInfoRS = hostInfoPS.executeQuery();
						hostTitle = "";
						hostFirstName = "";
						hostSurname="";
						hostMobileNumber="";
						
						int hostAddressId = 0;
						
						while(hostInfoRS.next()) {
							hostTitle = hostInfoRS.getString("title");
							hostFirstName = hostInfoRS.getString("firstName");
							hostSurname = hostInfoRS.getString("surname");
							hostMobileNumber = hostInfoRS.getString("mobileNumber");
							hostAddressId = hostInfoRS.getInt("address_id");
						}
						System.out.println("hostTitle: "+hostTitle);
						System.out.println("hostFirstName: "+hostFirstName);
						System.out.println("hostSurname: "+hostSurname);
						System.out.println("hostMobileNumber: "+hostMobileNumber);
						System.out.println("hostAddressId: "+hostAddressId);
						
						//getting hosts address from Address table:
						String hostPropertyInfo = "select houseNameNumber, streetName, placeName, postcode from Address where address_id=?";
						PreparedStatement getHostPropertyInfoPS = connection.prepareStatement(hostPropertyInfo);
						getHostPropertyInfoPS.setInt(1, hostAddressId);
						
						hostHouseNameNumber = "";
						hostStreetName = "";
						hostPlaceName = "";
						hostPostcode = "";

						ResultSet gettingHostPropertyInfo = getHostPropertyInfoPS.executeQuery();

						while(gettingHostPropertyInfo.next()) {
							hostHouseNameNumber = gettingHostPropertyInfo.getString("houseNameNumber");
							hostStreetName = gettingHostPropertyInfo.getString("streetName");
							hostPlaceName = gettingHostPropertyInfo.getString("placeName");
							hostPostcode = gettingHostPropertyInfo.getString("postcode");
						}
						
						
							System.out.println("hosthouseNameNumber: "+hostHouseNameNumber);
							System.out.println("hoststreetName: "+hostStreetName);
							System.out.println("hostplaceName: "+hostPlaceName);
							System.out.println("hostpostcode: "+hostPostcode);

						//--------guest confidential info--------
						guestEmail = "";
						
						String getGuestEmail = "select email from GuestAccount where guest_id=?";
						PreparedStatement getsGuestEmail = connection.prepareStatement(getGuestEmail);
						getsGuestEmail.setInt(1, guestId);

						ResultSet gettingGuestEmail = getsGuestEmail.executeQuery();
						while(gettingGuestEmail.next()){
							guestEmail = gettingGuestEmail.getString("email");
							
							//gets address id from accounts table
							int guestAddressId = 0;
							guestTitle = "";
							guestFirstName = "";
							guestSurname = "";
							guestMobileNum = "";

							
							String getGuestAddressId = "select address_id, title, firstName, surname, mobileNumber from Account where email=?";
							PreparedStatement getsGuestAddressId = connection.prepareStatement(getGuestAddressId);
							getsGuestAddressId.setString(1, guestEmail);
							
							ResultSet gettingGuestAddressId = getsGuestAddressId.executeQuery();
							while(gettingGuestAddressId.next()) {
								guestAddressId = gettingGuestAddressId.getInt("address_id");
								guestFirstName = gettingGuestAddressId.getString("firstName");
								guestSurname = gettingGuestAddressId.getString("surname");
								guestTitle = gettingGuestAddressId.getString("title");
								guestMobileNum = gettingGuestAddressId.getString("mobileNumber");

								String propertyInfoGuest = "select houseNameNumber, streetName, placeName, postcode from Address where address_id=?";
								PreparedStatement getPropertyInfoGuest = connection.prepareStatement(propertyInfoGuest);
								getPropertyInfoGuest.setInt(1, guestAddressId);
								
								
								System.out.println("guestEmail: "+guestEmail);
								System.out.println("guestTitle: "+guestTitle);
								System.out.println("guestFirstName: "+guestFirstName);
								System.out.println("guestSurname: "+guestSurname);
								System.out.println("guestMobileNum: "+guestMobileNum);
								
								
								houseNameNumberGuest = "";
								streetNameGuest = "";
								placeNameGuest = "";
								postcodeGuest = "";


								ResultSet gettingPropertyInfoGuest = getPropertyInfoGuest.executeQuery();

								while(gettingPropertyInfoGuest.next()) {
									houseNameNumberGuest = gettingPropertyInfoGuest.getString("houseNameNumber");
									streetNameGuest = gettingPropertyInfoGuest.getString("streetName");
									placeNameGuest = gettingPropertyInfoGuest.getString("placeName");
									postcodeGuest = gettingPropertyInfoGuest.getString("postcode");
								}	

								
							System.out.println("guesthouseNameNumber: "+houseNameNumberGuest);
							System.out.println("guestplaceName: "+placeNameGuest);
							System.out.println("guestStreetName: "+streetNameGuest);
							System.out.println("guestpostcode: "+postcodeGuest);		
							}
						}
					}
					
					displayConfidentialInfoMessage();
					
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		
		
		
		// Adds all of the GUI objects to the frame and panels.
		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1Layout.setHorizontalGroup(
			jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
					.addContainerGap(35, Short.MAX_VALUE)
					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
						.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
						.addGroup(jPanel1Layout.createSequentialGroup()
							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup()
									.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
										.addComponent(jLabel1)
										.addComponent(jLabel2))
									.addGap(61)
									.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
										.addComponent(jTextField_booking_id, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
										.addComponent(jTextField_property_id, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
								.addGroup(jPanel1Layout.createSequentialGroup()
									.addGap(112)
									.addComponent(reviewButton, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
								.addGroup(jPanel1Layout.createSequentialGroup()
									.addGap(47)
									.addComponent(confidentialInformationButton, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		jPanel1Layout.setVerticalGroup(
			jPanel1Layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(jPanel1Layout.createSequentialGroup()
					.addGap(62)
					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(44))
				.addGroup(Alignment.LEADING, jPanel1Layout.createSequentialGroup()
					.addGap(21)
					.addComponent(backButton)
					.addGap(64)
					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jTextField_booking_id, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel1))
					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
							.addGap(156)
							.addComponent(reviewButton))
						.addGroup(jPanel1Layout.createSequentialGroup()
							.addGap(32)
							.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jLabel2)
								.addComponent(jTextField_property_id, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))))
					.addGap(65)
					.addComponent(confidentialInformationButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(107, Short.MAX_VALUE))
		);
		jPanel1.setLayout(jPanel1Layout);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(jPanel1,
				GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 535, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);

		pack();
	}

	// Function that displays the information of a booking that is clicked on with
	// mouse within the JTable into theie
	// corresponding TextFields
	private void jTable_Display_BookingsMouseClicked(java.awt.event.MouseEvent evt) {

		// Gets The Index Of The Slected Row
		int i = jTable_Display_Bookings.getSelectedRow();

		TableModel model = jTable_Display_Bookings.getModel();

		// Display Slected Row In JTexteFields
		jTextField_booking_id.setText(model.getValueAt(i, 0).toString());
		jTextField_property_id.setText(model.getValueAt(i, 1).toString());
	}

	// Initialises the Booking GUI when called from other GUI pages
	public void initializeBookings(int fId, int id) {
		propertyId = fId;
		Id = id;
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Bookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Bookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Bookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Bookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Bookings(mainModule, controller, model).setVisible(true);

			}
		});
	}
	
	public void displayConfidentialInfoMessage() {
		JOptionPane.showMessageDialog(this,
				"Confidential Information:\n"
				+ "\nHost Address:\nHouse Name/Number: "+hostHouseNameNumber+"\nStreet Name: "+hostStreetName+"\nCity: "+hostPlaceName+"\nPostcode: "+hostPostcode+"\n"
				+ "\nGuest Address \nHouse Name/Number: "+houseNameNumberGuest+"\nStreet Name: "+streetNameGuest+"\nCity: "+placeNameGuest+"\nPostcode: "+postcodeGuest+"\n"
				+ "\nProperty Address \nHouse Name/Number: "+houseNameNumber+"\nStreet Name: "+streetName+"\nCity: "+placeName+"\nPostcode: "+postcode+"\n"
				+ "\nHost personal information: \nEmail: "+hostEmail+"\nTitle: "+hostTitle+"\nFirst name: "+hostFirstName+"\nSurname: "+hostSurname+"\nMobile number: "+hostMobileNumber+"\n"
				+ "\nGuest personal information: \nEmail: "+guestEmail+"\nTitle: "+guestTitle+"\nFirst name: "+guestFirstName+"\nSurname: "+guestSurname+"\nMobile number: "+guestMobileNum);
	}

	// Variables used on the GUI initialised.
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable_Display_Bookings;
	private javax.swing.JTextField jTextField_booking_id;
	private javax.swing.JTextField jTextField_property_id;
	private javax.swing.JTextField jTextField_sleeping_id;
	private javax.swing.JTextField jTextField_bathing_id;
	private javax.swing.JTextField jTextField_living_id;
	private JButton backButton;
	private static int propertyId;
	private static int Id;
	private int bookingId, hostId, guestId, propId;
	private String hostHouseNameNumber, hostStreetName, hostPlaceName, hostPostcode;
	private String houseNameNumberGuest, streetNameGuest, placeNameGuest, postcodeGuest;
	private String houseNameNumber, streetName, placeName, postcode;
	private String hostEmail, hostTitle, hostFirstName, hostSurname, hostMobileNumber;
	private String guestEmail, guestTitle, guestFirstName, guestSurname, guestMobileNum;
	

	private JButton confidentialInformationButton;
}
//code partially from https://1bestcsharp.blogspot.com/2016/01/java-and-mysql-insert-update-delete-display.html
