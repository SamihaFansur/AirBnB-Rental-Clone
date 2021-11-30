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

	public BookProperty(MainModule mainModule, Controller controller, Model model) {
		//initializeBookProperty();
		this.model = model;
		this.mainModule = mainModule;
		this.controller = controller;
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
		numOfBedroomsLabel.setBounds(31, 392, 160, 35);
		bookPropertyPanel.add(numOfBedroomsLabel);

		shortNameTextField = new JTextField();
		shortNameTextField.setBounds(166, 161, 360, 29);
		bookPropertyPanel.add(shortNameTextField);
		shortNameTextField.setColumns(10);

		guestCapacityTextField = new JTextField();
		guestCapacityTextField.setColumns(10);
		guestCapacityTextField.setBounds(166, 201, 131, 29);
		bookPropertyPanel.add(guestCapacityTextField);

		descriptionTextField = new JTextField();
		descriptionTextField.setColumns(10);
		descriptionTextField.setBounds(166, 247, 360, 93);
		bookPropertyPanel.add(descriptionTextField);

		JButton btnBookProperty = new JButton("Book Property");
		btnBookProperty.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBookProperty.setBounds(203, 672, 237, 29);
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
		reviewsButton.setBounds(227, 620, 196, 29);
		bookPropertyPanel.add(reviewsButton);

		JLabel numOfBedsLabel = new JLabel("Number of Beds");
		numOfBedsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		numOfBedsLabel.setBounds(31, 452, 160, 35);
		bookPropertyPanel.add(numOfBedsLabel);

		JLabel numOfBathsLabel = new JLabel("Number of Baths");
		numOfBathsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		numOfBathsLabel.setBounds(31, 509, 160, 35);
		bookPropertyPanel.add(numOfBathsLabel);

		numberOfBedroomsTextField = new JTextField();
		numberOfBedroomsTextField.setText("");
		numberOfBedroomsTextField.setColumns(10);
		numberOfBedroomsTextField.setBounds(195, 392, 331, 29);
		bookPropertyPanel.add(numberOfBedroomsTextField);

		numberOfBedsTextField = new JTextField();
		numberOfBedsTextField.setText("");
		numberOfBedsTextField.setColumns(10);
		numberOfBedsTextField.setBounds(195, 452, 331, 29);
		bookPropertyPanel.add(numberOfBedsTextField);

		numberOfBathsTextField = new JTextField();
		numberOfBathsTextField.setText("");
		numberOfBathsTextField.setColumns(10);
		numberOfBathsTextField.setBounds(195, 518, 331, 29);
		bookPropertyPanel.add(numberOfBathsTextField);

		JLabel lblPropertyId = new JLabel("Property ID:");
		lblPropertyId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPropertyId.setBounds(31, 116, 112, 35);
		bookPropertyPanel.add(lblPropertyId);

		jTextField_property_id = new JTextField();
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
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}

		frame.setBounds(100, 100, 600, 800);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}