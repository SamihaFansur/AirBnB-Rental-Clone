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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Controller.Controller;
import GUI.MainModule;
import Model.Model;

public class Reviews extends javax.swing.JFrame {

	private Controller controller;
	private Model model;
	private MainModule mainModule;

	/**
	 * Creates new form Java_Insert_Update_Delete_Display
	 */
	public Reviews(MainModule mainModule, Controller controller, Model model) {
		this.model = model;
		this.mainModule = mainModule;
		this.controller = controller;

		initComponents();
		Show_Reviews_In_JTable();
	}
	
	// get the connection
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

	// get a list of users from mysql database
	public ArrayList<ReviewObject> getReviewsList() {
		ArrayList<ReviewObject> reviewsList = new ArrayList<>();
		Connection connection = getConnection();

		String query = "SELECT * FROM `Review` where property_id=" + propertyId;
		System.out.println(query);
		Statement st;
		ResultSet rs;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			ReviewObject reviews;
			while (rs.next()) {
				reviews = new ReviewObject(rs.getInt("review_id"), rs.getInt("property_id"), rs.getDouble("accuracy"),
						rs.getDouble("location"), rs.getDouble("valueForMoney"), rs.getDouble("communication"),
						rs.getDouble("cleanliness"), rs.getString("description"));
				reviewsList.add(reviews);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reviewsList;
	}

	// Display Data In JTable
	public void Show_Reviews_In_JTable() {
		ArrayList<ReviewObject> list = getReviewsList();
		DefaultTableModel model = (DefaultTableModel) jTable_Display_Reviews.getModel();
		Object[] row = new Object[8];
		for (ReviewObject element : list) {
			row[0] = element.getReview_id();
			row[1] = element.getProperty_id();
			row[2] = element.getAccuracy();
			row[3] = element.getLocation();
			row[4] = element.getValueForMoney();
			row[5] = element.getCommunication();
			row[6] = element.getCleanliness();
			row[7] = element.getDescription();
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
				Show_Reviews_In_JTable();

				JOptionPane.showMessageDialog(null, "Data " + message + " Successfully");
			} else {
				JOptionPane.showMessageDialog(null, "Data Not " + message);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

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

		jTextField_review_id = new javax.swing.JTextField();
		jTextField_accuracy = new javax.swing.JTextField();
		jTextField_location = new javax.swing.JTextField();
		jTextField_valueForMoney = new javax.swing.JTextField();
		jTextField_sleeping_id = new javax.swing.JTextField();
		jTextField_bathing_id = new javax.swing.JTextField();
		jTextField_living_id = new javax.swing.JTextField();
		
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable_Display_Reviews = new javax.swing.JTable();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jPanel1.setBackground(new java.awt.Color(204, 255, 255));

		jLabel1.setFont(new java.awt.Font("Verdana", 0, 18));
		jLabel1.setText("Review ID:");

		jLabel2.setFont(new java.awt.Font("Verdana", 0, 18));
		jLabel2.setText("Accuracy:");

		jLabel3.setFont(new java.awt.Font("Verdana", 0, 18));
		jLabel3.setText("Location:");

		jLabel4.setFont(new java.awt.Font("Verdana", 0, 18));
		jLabel4.setText("Value For Money:");

		jLabel5.setFont(new java.awt.Font("Verdana", 0, 18));
		jLabel5.setText("Sleeping:");

		jLabel6.setFont(new java.awt.Font("Verdana", 0, 18));
		jLabel6.setText("Bathing:");

		jLabel7.setFont(new java.awt.Font("Verdana", 0, 18));
		jLabel7.setText("Living:");

		// NAVBAR

		jTextField_review_id.setFont(new java.awt.Font("Verdana", 0, 14));

		jTextField_accuracy.setFont(new java.awt.Font("Verdana", 0, 14));

		jTextField_location.setFont(new java.awt.Font("Verdana", 0, 14));

		jTextField_valueForMoney.setFont(new java.awt.Font("Verdana", 0, 14));

		jTextField_sleeping_id.setFont(new java.awt.Font("Verdana", 0, 14));

		jTextField_bathing_id.setFont(new java.awt.Font("Verdana", 0, 14));

		jTextField_living_id.setFont(new java.awt.Font("Verdana", 0, 14));

		jTable_Display_Reviews.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] {
				"Review_ID", "Accuracy", "Location", "ValueForMoney", "Communication", "Cleanliness", "Description" }));

		jTable_Display_Reviews.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jTable_Display_ReviewsMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(jTable_Display_Reviews);

		backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		lblCommunication = new JLabel();
		lblCommunication.setText("Communication:");
		lblCommunication.setFont(new Font("Verdana", Font.PLAIN, 18));

		lblCleanliness = new JLabel();
		lblCleanliness.setText("Cleanliness:");
		lblCleanliness.setFont(new Font("Verdana", Font.PLAIN, 18));

		lblDescription = new JLabel();
		lblDescription.setText("Description:");
		lblDescription.setFont(new Font("Verdana", Font.PLAIN, 18));

		jTextField_communication = new JTextField();
		jTextField_communication.setFont(new Font("Verdana", Font.PLAIN, 14));

		jTextField_cleanliness = new JTextField();
		jTextField_cleanliness.setFont(new Font("Verdana", Font.PLAIN, 14));

		jTextField_description = new JTextField();
		jTextField_description.setFont(new Font("Verdana", Font.PLAIN, 14));

		lblReviewRating = new JLabel();
		lblReviewRating.setText("Review Rating:");
		lblReviewRating.setFont(new Font("Verdana", Font.PLAIN, 18));

		jTextField_propertyRating = new JTextField();
		jTextField_propertyRating.setFont(new Font("Verdana", Font.PLAIN, 14));
		
		lblProeprtyRating = new JLabel();
		lblProeprtyRating.setText("Property Rating:");
		lblProeprtyRating.setFont(new Font("Verdana", Font.PLAIN, 18));
		
		propertyRatingTextField = new JTextField();
		propertyRatingTextField.setFont(new Font("Verdana", Font.PLAIN, 14));

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
									.addComponent(lblProeprtyRating, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(propertyRatingTextField, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
									.addGap(6)
									.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
										.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
											.addComponent(lblReviewRating, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(jTextField_propertyRating, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
										.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
											.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(jTextField_description, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
										.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
											.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
												.addComponent(jLabel2)
												.addComponent(jLabel4)
												.addComponent(jLabel1)
												.addComponent(jLabel3)
												.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING, false)
													.addComponent(lblCleanliness, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addComponent(lblCommunication, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
												.addComponent(jTextField_cleanliness, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
												.addComponent(jTextField_communication, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
												.addComponent(jTextField_location, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
												.addComponent(jTextField_review_id, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
												.addComponent(jTextField_accuracy, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
												.addComponent(jTextField_valueForMoney, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))))))
							.addGap(18)
							.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		jPanel1Layout.setVerticalGroup(
			jPanel1Layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(jPanel1Layout.createSequentialGroup()
					.addGap(21)
					.addComponent(backButton)
					.addGap(14)
					.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(jPanel1Layout.createSequentialGroup()
							.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblProeprtyRating, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(propertyRatingTextField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jTextField_propertyRating, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblReviewRating, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jTextField_review_id, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel1))
							.addGap(11)
							.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jLabel2)
								.addComponent(jTextField_accuracy, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jLabel3)
								.addComponent(jTextField_location, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
							.addGap(22)
							.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jTextField_valueForMoney, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel4))
							.addGap(18)
							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCommunication, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(jTextField_communication, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
							.addGap(17)
							.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCleanliness, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(jTextField_cleanliness, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jTextField_description, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		jPanel1.setLayout(jPanel1Layout);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}

	// show jtable row data in jtextfields in the mouse clicked event
	private void jTable_Display_ReviewsMouseClicked(java.awt.event.MouseEvent evt) {
		// Get The Index Of The Slected Row
		int i = jTable_Display_Reviews.getSelectedRow();

		TableModel model = jTable_Display_Reviews.getModel();

		// Display Slected Row In JTexteFields
		jTextField_review_id.setText(model.getValueAt(i, 0).toString());
		jTextField_accuracy.setText(model.getValueAt(i, 1).toString());
		jTextField_location.setText(model.getValueAt(i, 2).toString());
		jTextField_valueForMoney.setText(model.getValueAt(i, 3).toString());
		jTextField_communication.setText(model.getValueAt(i, 4).toString());
		jTextField_cleanliness.setText(model.getValueAt(i, 5).toString());
		jTextField_description.setText(model.getValueAt(i, 6).toString());

		String reviewid = (model.getValueAt(i, 0).toString());
		Double accuracy1 = (Double.parseDouble(model.getValueAt(i, 1).toString()));
		Double location1 = (Double.parseDouble(model.getValueAt(i, 2).toString()));
		Double value1 = (Double.parseDouble(model.getValueAt(i, 3).toString()));
		Double communication1 = (Double.parseDouble(model.getValueAt(i, 4).toString()));
		Double cleanliness1 = (Double.parseDouble(model.getValueAt(i, 5).toString()));
		Double propertyRating1 = (accuracy1 + location1 + value1 + communication1 + cleanliness1 ) / 5.0;
		// round to 2 dp
		propertyRating1 = (double) Math.round(propertyRating1 * 100.0);
		propertyRating1 = propertyRating1 / 100.0;

		String propertyRating2 = Double.toString(propertyRating1);
		jTextField_propertyRating.setText(propertyRating2);
		
		
		
		
	}

	/**
	 * @param args the command line arguments
	 */
	public void initializeReviews(int fId, int id) {
		propertyId = fId;
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Reviews.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Reviews.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Reviews.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Reviews.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {

				new Reviews(mainModule, controller, model).setVisible(true);

			}
		});
	}
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable_Display_Reviews;
	private javax.swing.JTextField jTextField_review_id;
	private javax.swing.JTextField jTextField_accuracy;
	private javax.swing.JTextField jTextField_location;
	private javax.swing.JTextField jTextField_valueForMoney;
	private javax.swing.JTextField jTextField_sleeping_id;
	private javax.swing.JTextField jTextField_bathing_id;
	private javax.swing.JTextField jTextField_living_id;
	private JButton backButton;
	private static int propertyId;
	private JLabel lblCommunication;
	private JLabel lblCleanliness;
	private JLabel lblDescription;
	private JTextField jTextField_communication;
	private JTextField jTextField_cleanliness;
	private JTextField jTextField_description;
	private JLabel lblReviewRating;
	private JTextField jTextField_propertyRating;
	private JLabel lblProeprtyRating;
	private JTextField propertyRatingTextField;
}

//code partially from https://1bestcsharp.blogspot.com/2016/01/java-and-mysql-insert-update-delete-display.html
