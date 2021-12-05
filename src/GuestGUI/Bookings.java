package GuestGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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

	// Creates a list of bookings from mysql database and puts them into an ArrayList
	// to use.
	public ArrayList<BookingObject> getBookingsList() {

		ArrayList<BookingObject> bookingsList = new ArrayList<>();
		Connection connection = getConnection();
		if (mainModule.userState == USER.GUEST) {

			// Gets the information for a booking from the guest database
			try {
				String query = "SELECT * FROM `Booking` where guest_id=? and provisional!=?";
				PreparedStatement st = connection.prepareStatement(query);
				st.setInt(1, model.getGuestId());
				st.setString(2, "Pending");

				ResultSet rs = st.executeQuery();

				BookingObject bookings;
				while (rs.next()) {
					bookings = new BookingObject(rs.getInt("booking_id"), rs.getInt("property_id"),
							rs.getInt("host_id"), rs.getInt("guest_id"), rs.getString("provisional"),
							rs.getDouble("totalPrice"), rs.getString("startDate"), rs.getString("endDate"));
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
					bookings = new BookingObject(rs.getInt("booking_id"), rs.getInt("property_id"),
							rs.getInt("host_id"), rs.getInt("guest_id"), rs.getString("provisional"),
							rs.getDouble("totalPrice"), rs.getString("startDate"), rs.getString("endDate"));
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
				.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] { "Booking_ID",
						"Property_ID", "Host_ID", "Guest_ID", "Provisional", "TotalPrice", "Start Date", "End Date" }));

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
	private JButton confidentialInformationButton;
}
//code partially from https://1bestcsharp.blogspot.com/2016/01/java-and-mysql-insert-update-delete-display.html
