package GuestGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Controller.Controller;
import GUI.ConnectionManager;
import GUI.MainModule;
import GUI.MainModule.EDITPROPERTY;
import HostGUI.NavHost;
import Model.Model;

public class Booking extends JFrame {
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
	private JTextField jTextField_startDate;

	public Booking(MainModule mainModule, Controller controller, Model model) {
		initializeBooking();
		this.model = model;
		this.mainModule = mainModule;
		this.controller = controller;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeBooking(int propertyId, int id) {
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

		JLabel bookPropertyTitleLabel = new JLabel("Booking");
		bookPropertyTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		bookPropertyTitleLabel.setBounds(265, 39, 196, 55);
		bookPropertyPanel.add(bookPropertyTitleLabel);

		JLabel endDatelabel = new JLabel("End Date:");
		endDatelabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		endDatelabel.setBounds(31, 205, 112, 35);
		bookPropertyPanel.add(endDatelabel);

		shortNameTextField = new JTextField();
		shortNameTextField.setBounds(166, 211, 360, 29);
		bookPropertyPanel.add(shortNameTextField);
		shortNameTextField.setColumns(10);

		JButton btnBookProperty = new JButton("Book Property");
		btnBookProperty.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBookProperty.setBounds(200, 625, 237, 29);
		bookPropertyPanel.add(btnBookProperty);

		JButton reviewsButton = new JButton("Property Reviews");
		reviewsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState = EDITPROPERTY.REVIEWS;
				// needs to take in the properyId and hostId
				model.setPropertyId(Integer.parseInt(jTextField_startDate.getText()));
				MainModule.controller.editPropertyView(Integer.parseInt(jTextField_startDate.getText()),
						model.getHostId());
				frame.dispose();
			}
		});
		reviewsButton.setBounds(224, 573, 196, 29);
		bookPropertyPanel.add(reviewsButton);

		JLabel startDateLabel = new JLabel("Start Date:");
		startDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		startDateLabel.setBounds(31, 142, 112, 35);
		bookPropertyPanel.add(startDateLabel);

		jTextField_startDate = new JTextField();
		jTextField_startDate.setText("");
		jTextField_startDate.setColumns(10);
		jTextField_startDate.setBounds(166, 147, 360, 29);
		bookPropertyPanel.add(jTextField_startDate);

		// SET PROPETY ID BOX
		jTextField_startDate.setText(String.valueOf(propertyId));

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
			String guestCapacityString = String.valueOf(guestCapacity);

			while (rs.next()) {
				shortName = rs.getString("shortName");
				guestCapacity = rs.getInt("guestCapacity");
				description = rs.getString("description");
			}
			shortNameTextField.setText(shortName);

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
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}

		frame.setBounds(100, 100, 600, 800);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}