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
					bookings = new BookingObject(rs.getInt("booking_id"), rs.getInt("property_id"),
							rs.getInt("host_id"), rs.getInt("guest_id"), rs.getString("provisional"),
							rs.getDouble("totalPrice"), rs.getString("startDate"), rs.getString("endDate"));
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
					bookings = new BookingObject(r.getInt("booking_id"), r.getInt("property_id"),
							r.getInt("host_id"), r.getInt("guest_id"), r.getString("provisional"),
							r.getDouble("totalPrice"), r.getString("startDate"), r.getString("endDate"));
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
		Object[] row = new Object[8];
		for (BookingObject element : list) {
			row[0] = element.getBooking_id();
			row[1] = element.getProperty_id();
			row[2] = element.getHost_id();
			row[3] = element.getGuest_id();
			row[4] = element.getProvisional();
			row[5] = element.getTotalPrice();
			row[6] = element.getStartDate();
			row[7] = element.getEndDate();
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
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();

		jTextField_booking_id = new javax.swing.JTextField();
		jTextField_sleeping_id = new javax.swing.JTextField();
		jTextField_bathing_id = new javax.swing.JTextField();
		jTextField_living_id = new javax.swing.JTextField();

		jScrollPane1 = new javax.swing.JScrollPane();
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
				.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] { "Booking_ID",
						"Property_ID", "Host_ID", "Guest_ID", "Provisional", "TotalPrice", "Start Date", "End Date" }));

		jTable_Display_Reviews.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jTable_Display_ReviewsMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(jTable_Display_Reviews);

		//Button to return you to previous GUI page
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

		//Button to accept the provisional booking and update it to a booking
		acceptButton = new JButton("Accept");
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
				}
			}
		});

		//Button to decline provisional booking and deltes it from the database
		declineButton = new JButton("Decline");
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
				}
			}
		});
		
		// Adds all of the GUI objects to the frame and panels.
		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1Layout.setHorizontalGroup(
			jPanel1Layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(jPanel1Layout.createSequentialGroup()
					.addContainerGap(33, Short.MAX_VALUE)
					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
						.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
						.addGroup(jPanel1Layout.createSequentialGroup()
							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(jLabel1)
									.addGap(61)
									.addComponent(jTextField_booking_id, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
								.addGroup(jPanel1Layout.createSequentialGroup()
									.addGap(95)
									.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
										.addComponent(declineButton, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
										.addComponent(acceptButton, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))))
							.addGap(18)
							.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		jPanel1Layout.setVerticalGroup(
			jPanel1Layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(jPanel1Layout.createSequentialGroup()
					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
							.addGap(21)
							.addComponent(backButton)
							.addGap(55)
							.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jTextField_booking_id, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel1))
							.addGap(105)
							.addComponent(acceptButton)
							.addGap(73)
							.addComponent(declineButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGroup(jPanel1Layout.createSequentialGroup()
							.addGap(62)
							.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
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
