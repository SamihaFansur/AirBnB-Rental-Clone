package HostGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Controller.Controller;
import GUI.MainModule;
import GUI.MainModule.EDITPROPERTY;
import GUI.MainModule.STATE;
import GUI.MainModule.USER;
import Model.Model;


/**
 * Class for displaying the facilities in the database onto a GUI
 */
public class Facilities extends javax.swing.JFrame {

	private Controller controller;
	private Model model;
	private MainModule mainModule;

	//Constructor for facilities
	public Facilities(MainModule mainModule, Controller controller, Model model) {
		this.model = model;
		this.mainModule = mainModule;
		this.controller = controller;

		initComponents();
		Show_Facilities_In_JTable();
	}

	// Sets the database information to get a connection later
	private static String serverName = "jdbc:mysql://stusql.dcs.shef.ac.uk/team018";
	private static String username = "team018";
	private static String pwd = "7854a03f";

	//Gets a connection to the database
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

	// Creates a list of facility objects using the information in the Facility table 
	// within the database
	public ArrayList<FacilitiesObject> getFacilitiesList() {
		ArrayList<FacilitiesObject> facilitiesList = new ArrayList<>();
		Connection connection = getConnection();

		String query = "SELECT * FROM `Facilities` where property_id=" + propertyId;
		Statement st;
		ResultSet rs;

		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			FacilitiesObject facilities;
			while (rs.next()) {
				facilities = new FacilitiesObject(rs.getInt("facilities_id"), rs.getInt("utility_id"),
						rs.getInt("outdoors_id"), rs.getInt("kitchen_id"), rs.getInt("sleeping_id"),
						rs.getInt("bathing_id"), rs.getInt("living_id"));
				facilitiesList.add(facilities);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return facilitiesList;
	}

	// Displays the facility Objects' information from the facilitiesList on to a JTable
	public void Show_Facilities_In_JTable() {
		ArrayList<FacilitiesObject> list = getFacilitiesList();
		DefaultTableModel model = (DefaultTableModel) jTable_Display_Facilities.getModel();
		Object[] row = new Object[7];
		for (FacilitiesObject element : list) {
			row[0] = element.getFacilitiesId();

			if (element.getUtilityId() != 0) {
				row[1] = "Yes";
			} else {
				row[1] = "No";
			}

			if (element.getOutdoorsId() != 0) {
				row[2] = "Yes";
			} else {
				row[2] = "No";
			}

			if (element.getKitchenId() != 0) {
				row[3] = "Yes";
			} else {
				row[3] = "No";
			}

			if (element.getSleepingId() != 0) {
				row[4] = "Yes";
			} else {
				row[4] = "No";
			}

			if (element.getBathingId() != 0) {
				row[5] = "Yes";
			} else {
				row[5] = "No";
			}

			if (element.getLivingId() != 0) {
				row[6] = "Yes";
			} else {
				row[6] = "No";
			}

			model.addRow(row);
		}
	}

	// Execute The Insert Update And Delete Querys
	public void executeSQlQuery(String query, String message) {
		Connection connection = getConnection();
		Statement st;
		try {
			st = connection.createStatement();
			if ((st.executeUpdate(query)) == 1) {
				DefaultTableModel model = (DefaultTableModel) jTable_Display_Facilities.getModel();
				model.setRowCount(0);
				Show_Facilities_In_JTable();

				JOptionPane.showMessageDialog(null, "Data " + message + " Successfully");
			} else {
				JOptionPane.showMessageDialog(null, "Data Not " + message);
			}
			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Function for defining all of the GUI objects and their attributes
	@SuppressWarnings("unchecked")
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();

		jTextField_facilities_id = new javax.swing.JTextField();
		jTextField_utility_id = new javax.swing.JTextField();
		jTextField_outdoors_id = new javax.swing.JTextField();
		jTextField_kitchen_id = new javax.swing.JTextField();
		jTextField_sleeping_id = new javax.swing.JTextField();
		jTextField_bathing_id = new javax.swing.JTextField();
		jTextField_living_id = new javax.swing.JTextField();

		jScrollPane1 = new javax.swing.JScrollPane();
		jTable_Display_Facilities = new javax.swing.JTable();
		jButton_Update = new javax.swing.JButton();
		jButton_Delete = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jPanel1.setBackground(new java.awt.Color(204, 255, 255));

		jLabel1.setFont(new java.awt.Font("Verdana", 0, 18));
		jLabel1.setText("Facility ID:");

		jLabel2.setFont(new java.awt.Font("Verdana", 0, 18));
		jLabel2.setText("Utility:");

		jLabel3.setFont(new java.awt.Font("Verdana", 0, 18));
		jLabel3.setText("Outdoors:");

		jLabel4.setFont(new java.awt.Font("Verdana", 0, 18));
		jLabel4.setText("Kitchen");

		jLabel5.setFont(new java.awt.Font("Verdana", 0, 18));
		jLabel5.setText("Sleeping:");

		jLabel6.setFont(new java.awt.Font("Verdana", 0, 18));
		jLabel6.setText("Bathing:");

		jLabel7.setFont(new java.awt.Font("Verdana", 0, 18));
		jLabel7.setText("Living:");

		jTextField_facilities_id.setFont(new java.awt.Font("Verdana", 0, 14));

		jTextField_utility_id.setFont(new java.awt.Font("Verdana", 0, 14));

		jTextField_outdoors_id.setFont(new java.awt.Font("Verdana", 0, 14));

		jTextField_kitchen_id.setFont(new java.awt.Font("Verdana", 0, 14));

		jTextField_sleeping_id.setFont(new java.awt.Font("Verdana", 0, 14));

		jTextField_bathing_id.setFont(new java.awt.Font("Verdana", 0, 14));

		jTextField_living_id.setFont(new java.awt.Font("Verdana", 0, 14));

		//Creates a JTable for displaying facilities and Sets the headers for the columns
		jTable_Display_Facilities.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {},
				new String[] { "Facility ID", "Utility", "Outdoors", "Kitchen", "Sleeping", "Bathing", "Living" }));

		jTable_Display_Facilities.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jTable_Display_FacilitiesMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(jTable_Display_Facilities);

		//Button for updating the information of a facility
		jButton_Update.setFont(new java.awt.Font("Verdana", 1, 14)); 
		jButton_Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("assets/refresh.png"))); 
		jButton_Update.setText("Edit");
		jButton_Update.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton_UpdateActionPerformed(evt);
			}
		});

		//Button for deleting a facility
		jButton_Delete.setFont(new java.awt.Font("Verdana", 1, 14)); 
		jButton_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("assets/delete.png"))); 
		jButton_Delete.setText("Delete");
		jButton_Delete.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton_DeleteActionPerformed(evt);
			}
		});

		//Button for returning to the previous GUI page
		backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainModule.currentState = STATE.HOST_ACCOUNT;
				mainModule.editPropertyState = EDITPROPERTY.PROPERTIES;
				mainModule.userState = USER.HOST;
				MainModule.controller.editPropertyView(model.getHostId(), model.getPropertyId());
				setVisible(false);

			}
		});

		// Adds all of the GUI objects to the frame and panels.
		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING).addGroup(jPanel1Layout
				.createSequentialGroup().addContainerGap(33, Short.MAX_VALUE)
				.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
						.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
						.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
								.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup()
										.addGroup(jPanel1Layout
												.createParallelGroup(Alignment.LEADING).addComponent(jLabel2)
												.addComponent(jLabel4).addComponent(jLabel3).addComponent(jLabel1))
										.addGap(10)
										.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
												.addComponent(jTextField_facilities_id, GroupLayout.PREFERRED_SIZE, 129,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(jTextField_utility_id, GroupLayout.PREFERRED_SIZE, 129,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(jTextField_outdoors_id, GroupLayout.PREFERRED_SIZE, 129,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(jTextField_kitchen_id, GroupLayout.PREFERRED_SIZE, 129,
														GroupLayout.PREFERRED_SIZE)))
								.addGroup(jPanel1Layout.createSequentialGroup().addGap(99).addGroup(jPanel1Layout
										.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(jButton_Update, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jButton_Delete, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
								.addGap(24).addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 409,
										GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(21).addComponent(backButton).addGap(18)
						.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
												.addComponent(jTextField_facilities_id, GroupLayout.PREFERRED_SIZE, 35,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel1))
										.addGap(11)
										.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
												.addComponent(jLabel2).addComponent(jTextField_utility_id,
														GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
												.addComponent(jTextField_outdoors_id, GroupLayout.PREFERRED_SIZE, 34,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel3))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
												.addComponent(jTextField_kitchen_id, GroupLayout.PREFERRED_SIZE, 34,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel4))
										.addGap(69)
										.addComponent(jButton_Update, GroupLayout.PREFERRED_SIZE, 46,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(jButton_Delete, GroupLayout.PREFERRED_SIZE, 46,
												GroupLayout.PREFERRED_SIZE)
										.addGap(45)))));
		jPanel1.setLayout(jPanel1Layout);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}

	// Function that displays the information of a facility that is clicked on with
	// mouse within the JTable into their
	// corresponding TextFields
	private void jTable_Display_FacilitiesMouseClicked(java.awt.event.MouseEvent evt) {
		// Get The Index Of The Selected Row
		int i = jTable_Display_Facilities.getSelectedRow();

		TableModel model = jTable_Display_Facilities.getModel();

		// Display Selected Row In JTextFields
		jTextField_facilities_id.setText(model.getValueAt(i, 0).toString());
		jTextField_utility_id.setText(model.getValueAt(i, 1).toString());
		jTextField_outdoors_id.setText(model.getValueAt(i, 2).toString());
		jTextField_kitchen_id.setText(model.getValueAt(i, 3).toString());
		jTextField_sleeping_id.setText(model.getValueAt(i, 4).toString());
	}

	// Button to edit Facility
	private void jButton_UpdateActionPerformed(java.awt.event.ActionEvent evt) {
		mainModule.editPropertyState = EDITPROPERTY.EDIT_PROPERTY_FACILITIES;
		MainModule.controller.editPropertyView(Integer.parseInt(jTextField_facilities_id.getText()), 0);
	}

	// Button to delete Facility
	private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {
		String query = "DELETE FROM `Facilities` WHERE facilities_id = " + jTextField_facilities_id.getText();
		executeSQlQuery(query, "Deleted");
	}

	// Initialises the Facilities GUI when called from other GUI pages
	public void initializeFacilities(int fId, int id) {
		propertyId = fId;
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Facilities.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Facilities.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Facilities.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Facilities.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				setLocationRelativeTo(null);
				new Facilities(mainModule, controller, model).setVisible(true);

			}
		});
	}
	// Variables used on the GUI initialised.
	private javax.swing.JButton jButton_Delete;
	private javax.swing.JButton jButton_Update;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable_Display_Facilities;
	private javax.swing.JTextField jTextField_facilities_id;
	private javax.swing.JTextField jTextField_utility_id;
	private javax.swing.JTextField jTextField_outdoors_id;
	private javax.swing.JTextField jTextField_kitchen_id;
	private javax.swing.JTextField jTextField_sleeping_id;
	private javax.swing.JTextField jTextField_bathing_id;
	private javax.swing.JTextField jTextField_living_id;
	private JButton backButton;
	private static int propertyId;
}

//code partially from https://1bestcsharp.blogspot.com/2016/01/java-and-mysql-insert-update-delete-display.html
