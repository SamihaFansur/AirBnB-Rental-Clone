package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
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
import GUI.MainModule.STATE;
import GUI.MainModule.USER;
import GuestGUI.BookingObject;
import Model.Model;


/*
 * Class for displaying booking objects and initialising reviews for the provisional bookings.
 */
public class ProvisionalBookings extends javax.swing.JFrame {

	private Controller controller;
	private Model model;
	private MainModule mainModule;

	/**
	 * Gui for displaying all of the booking objects within the database in a table that are provisional
	 */
	public ProvisionalBookings(MainModule mainModule, Controller controller, Model model) {
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

	// Creates a list of bookings from mysql database and puts them into an ArrayList
	// to use.
	public ArrayList<BookingObject> getBookingsList() {

		ArrayList<BookingObject> bookingsList = new ArrayList<>();
		Connection connection = getConnection();
		if (mainModule.userState == USER.HOST) {			
			try {		
			
				connection = ConnectionManager.getConnection();
				
				// Gets the information for a booking from the host database if it is provisional
				String query = "select * from Booking where host_id=? and provisional=?";

				BookingObject bookings;
				
    			PreparedStatement st = connection.prepareStatement(query);
				st.setInt(1, model.getHostId());
				st.setString(2, "Pending");

				ResultSet rs = st.executeQuery();
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

		} else if (mainModule.userState == USER.GUEST){			
			
			try {		
				
				// Gets the information for a booking from the guest database if it is provisional
				String query = "select * from Booking where guest_id=? and provisional=?";

				connection = ConnectionManager.getConnection();

				BookingObject bookings;
				PreparedStatement st = connection.prepareStatement(query);
				st.setInt(1, model.getGuestId());
				st.setString(2, "Pending");
				
				System.out.println(st);
				ResultSet r = st.executeQuery();
				
			
				while (r.next()) {
					
					// use dates to comapre against chargeband dates later to find which chargeband the booking was made with.
					String bookingStartDate = r.getString("startDate");
					String bookingEndDate = r.getString("endDate");
					int bookingPropertyId = r.getInt("property_id");
					
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
					
					
					bookings = new BookingObject(r.getInt("booking_id"), r.getInt("property_id"), 
							propertyServiceCharge, propertyCleaningCharge,
							totalPriceForAllNights, (int)days, 
							r.getString("provisional"), r.getString("startDate"),
							r.getString("endDate"));
					bookingsList.add(bookings);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bookingsList;
	}

	// Display bookings from the bookings list  In JTable
	public void Show_Bookings_In_JTable() {
		ArrayList<BookingObject> list = getBookingsList();
		DefaultTableModel model = (DefaultTableModel) jTable_Display_Reviews.getModel();
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
				DefaultTableModel model = (DefaultTableModel) jTable_Display_Reviews.getModel();
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
	// Function for defining all of the GUI objects
	@SuppressWarnings("unchecked")
	private void initComponents() {
		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel1.setBounds(26, 109, 113, 23);
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();

		jTextField_booking_id = new javax.swing.JTextField();
		jTextField_booking_id.setBounds(159, 105, 91, 35);
		jTextField_sleeping_id = new javax.swing.JTextField();
		jTextField_bathing_id = new javax.swing.JTextField();
		jTextField_living_id = new javax.swing.JTextField();

		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane1.setBounds(260, 62, 599, 462);
		jTable_Display_Reviews = new javax.swing.JTable();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jPanel1.setBackground(new java.awt.Color(204, 255, 255));

		jLabel1.setFont(new java.awt.Font("Verdana", 0, 18));
		jLabel1.setText("Booking_ID:");

		jLabel5.setFont(new java.awt.Font("Verdana", 0, 18));
		jLabel5.setText("Sleeping:");

		jLabel6.setFont(new java.awt.Font("Verdana", 0, 18));
		jLabel6.setText("Bathing:");

		jLabel7.setFont(new java.awt.Font("Verdana", 0, 18));
		jLabel7.setText("Living:");

		// NAVBAR
		jTextField_booking_id.setFont(new java.awt.Font("Verdana", 0, 14));

		jTextField_sleeping_id.setFont(new java.awt.Font("Verdana", 0, 14));

		jTextField_bathing_id.setFont(new java.awt.Font("Verdana", 0, 14));

		jTextField_living_id.setFont(new java.awt.Font("Verdana", 0, 14));

		jTable_Display_Reviews
				.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] {
						"Booking Id", "Property Id", "Service Charge (£)", "Cleaning Charge (£)",
						"Overall Price (£)", "Total Nights", "Provisional", "Start Date", "End Date" }));

		jTable_Display_Reviews.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jTable_Display_ReviewsMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(jTable_Display_Reviews);

		//Button to return you to previous GUI page
		backButton = new JButton("Back");
		backButton.setBounds(33, 21, 91, 29);
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


		//Button to accept the provisional booking and update it to a booking
		acceptButton = new JButton("Accept");
		acceptButton.setBounds(78, 223, 91, 29);
		acceptButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		acceptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mainModule.userState == USER.HOST) {

					//Decline all other bookings for the same property id, within the same table
					try {
						connection = ConnectionManager.getConnection();
						
						int bookingID = 	Integer.parseInt(jTextField_booking_id.getText());
						
						String acceptBooking = "update Booking set provisional=? where booking_id=?";
						PreparedStatement acceptingBooking = connection.prepareStatement(acceptBooking);
						acceptingBooking.setString(1, "Accepted");
						acceptingBooking.setInt(2, bookingID);
	
						acceptingBooking.executeUpdate();

					
					}catch(Exception e2) {
						System.err.println(e2.getMessage());
					}
					
					
					try {
							
						
						int bookingID = 	Integer.parseInt(jTextField_booking_id.getText());
					    //Updates the property with a booking id
						String propertyDetailsForAcptdBooking = "select property_id, startDate, endDate from Booking where booking_id=? and provisional=?";
						PreparedStatement propertyDetailsForAcptdBookingPS = connection.prepareStatement(propertyDetailsForAcptdBooking);
						propertyDetailsForAcptdBookingPS.setInt(1, bookingID);
						propertyDetailsForAcptdBookingPS.setString(2, "Accepted");
	
	
						int propId = 0;
						String startDate = "";
						String endDate = "";
	
						ResultSet propertyDetailsForAcptdBookingRS = propertyDetailsForAcptdBookingPS.executeQuery();
						while(propertyDetailsForAcptdBookingRS.next()) {
							propId = propertyDetailsForAcptdBookingRS.getInt("property_id");
							startDate = propertyDetailsForAcptdBookingRS.getString("startDate");
							endDate = propertyDetailsForAcptdBookingRS.getString("endDate");
						}
	
	
						// Looks through all provisional bookings. If the dates for the accepted booking overlap with existing provisional
						// bookings for the same property then decline those bookings.
						String query = "select * from Booking where host_id=? and provisional=? and property_id=?";
						//BookingObject bookings;
						PreparedStatement st = connection.prepareStatement(query);
						st.setInt(1, model.getHostId());
						st.setString(2, "Pending");
						st.setInt(3, propId);
	
						ResultSet rs = st.executeQuery();
						while (rs.next()) {
							int tempBookingId = rs.getInt("booking_id");
							// Gets start date, end date from Bookings table
							String startDateProvBooking = rs.getString("startDate");
							String endDateProvBooking = rs.getString("endDate");
							String provisionalStatus = rs.getString("provisional");
							
							// Sets the start and end dates for accepted bookings to variables 
							String startDateAcceptedBooking = startDate;
							String endDateAcceptedBooking = endDate;
							
							//Formats dates the strings to dates
							Date fmtdStartDateProvBkng = sourceFormat.parse(startDateProvBooking);
							Date fmtdEndDateProvBkng = sourceFormat.parse(endDateProvBooking);
							Date fmtdStartDateAcptdBkng = sourceFormat.parse(startDateAcceptedBooking);
							Date fmtdEndDateAcptdBkng = sourceFormat.parse(endDateAcceptedBooking);
							
							//Call function to check what current provisonal bookings overlap with the accepted booking.
							// if true then this provisional booking overlaps with the accepted booking

							if (	fmtdStartDateAcptdBkng.equals(fmtdStartDateProvBkng) || 
									fmtdEndDateAcptdBkng.equals(fmtdEndDateProvBkng)
									|| (fmtdStartDateProvBkng.after(fmtdStartDateAcptdBkng) && fmtdStartDateProvBkng.before(fmtdEndDateAcptdBkng))
									|| (fmtdEndDateProvBkng.after(fmtdStartDateAcptdBkng) && fmtdEndDateProvBkng.before(fmtdEndDateAcptdBkng))
									|| (fmtdStartDateProvBkng.before(fmtdStartDateAcptdBkng) && fmtdEndDateProvBkng.after(fmtdEndDateAcptdBkng)
									|| (fmtdEndDateAcptdBkng.equals(fmtdStartDateProvBkng))
									|| (fmtdStartDateAcptdBkng.equals(fmtdEndDateProvBkng)))) {
							
								
								// Change this provisional booking to declined if it has been declines by host
								String declineBooking = "update Booking set provisional=? where booking_id=? and property_id=? and host_id=? ";
								PreparedStatement declineBookingPS = connection.prepareStatement(declineBooking);
								declineBookingPS.setString(1, "Declined");
								declineBookingPS.setInt(2, tempBookingId);
								declineBookingPS.setInt(3, propId);
								declineBookingPS.setInt(4, model.getHostId());
	
								declineBookingPS.executeUpdate();							
							}	
					}
					}catch(Exception e1) {
						e1.printStackTrace();
						
					}
				} else if(mainModule.userState == USER.GUEST) {
					displayMessageUnableToAcceptBookingAsGuest();
				}
			}
		});


		//Button to decline provisional booking and deltes it from the database
		declineButton = new JButton("Decline");
		declineButton.setBounds(78, 343, 91, 29);
		declineButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		declineButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mainModule.userState == USER.HOST) {
					//Gets the booking id
					//Updates value in provisional column form pending to decline 
					try {
					connection = ConnectionManager.getConnection();
					
					int bookingID = 	Integer.parseInt(jTextField_booking_id.getText());
					
					String declineBooking = "update Booking set provisional=? where booking_id=?";
					PreparedStatement decliningBooking = connection.prepareStatement(declineBooking);
					decliningBooking.setString(1, "Declined");
					decliningBooking.setInt(2, bookingID);

					decliningBooking.executeUpdate();

					}catch(Exception e1) {
						e1.printStackTrace();
						
					}
				} else if(mainModule.userState == USER.GUEST) {
					displayMessageUnableToDeclineBookingAsGuest();
				}
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
		);
		jPanel1.setLayout(null);
		jPanel1.add(backButton);
		jPanel1.add(jLabel1);
		jPanel1.add(jTextField_booking_id);
		jPanel1.add(declineButton);
		jPanel1.add(acceptButton);
		jPanel1.add(jScrollPane1);
		getContentPane().setLayout(layout);

		pack();
	}

	
	public void displayMessageUnableToAcceptBookingAsGuest() {
		JOptionPane.showMessageDialog(this,
				"Unable to accept provisional booking as a guest. Please login as a host to accept a provisional booking.");
	}

	public void displayMessageUnableToDeclineBookingAsGuest() {
		JOptionPane.showMessageDialog(this,
				"Unable to decline provisional booking as a guest. Please login as a host to decline a provisional booking.");
	}

	// Function that displays the information of a booking that is clicked on with
	// mouse within the JTable into theie
	// corresponding TextFields
	private void jTable_Display_ReviewsMouseClicked(java.awt.event.MouseEvent evt) {
		// Get The Index Of The Selected Row
		int i = jTable_Display_Reviews.getSelectedRow();

		TableModel model = jTable_Display_Reviews.getModel();
		// Display Slected Row In JTexteFields
		jTextField_booking_id.setText(model.getValueAt(i, 0).toString());
		jTextField_property_id.setText(model.getValueAt(i, 1).toString());
		jTextField_host_id.setText(model.getValueAt(i, 2).toString());
		jTextField_guest_id.setText(model.getValueAt(i, 3).toString());
		jTextField_provisional.setText(model.getValueAt(i, 4).toString());
		jTextField_totalPrice.setText(model.getValueAt(i, 5).toString());
		jTextField_startDate.setText(model.getValueAt(i, 6).toString());
		jTextField_endDate.setText(model.getValueAt(i, 7).toString());
	}

	// Initialises the Provisional Bookings GUI when called from other GUI pages
	public void initializeProvisionalBookings(int fId, int id_id) {
		propertyId = fId;
		id = id_id;
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(ProvisionalBookings.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(ProvisionalBookings.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(ProvisionalBookings.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(ProvisionalBookings.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				setLocationRelativeTo(null);
				new ProvisionalBookings(mainModule, controller, model).setVisible(true);

			}
		});
	}
	// Variables used on the GUI initialised.
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable_Display_Reviews;
	private javax.swing.JTextField jTextField_booking_id;
	private javax.swing.JTextField jTextField_property_id;
	private javax.swing.JTextField jTextField_host_id;
	private javax.swing.JTextField jTextField_guest_id;
	private javax.swing.JTextField jTextField_provisional;
	private javax.swing.JTextField jTextField_totalPrice;
	private javax.swing.JTextField jTextField_startDate;
	private javax.swing.JTextField jTextField_endDate;

	private javax.swing.JTextField jTextField_sleeping_id;
	private javax.swing.JTextField jTextField_bathing_id;
	private javax.swing.JTextField jTextField_living_id;
	private JButton backButton;
	private static int propertyId;
	private  int id;
	private JButton declineButton;
	private JButton acceptButton;
	private Connection connection;
	DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");

}

//code partially from https://1bestcsharp.blogspot.com/2016/01/java-and-mysql-insert-update-delete-display.html
