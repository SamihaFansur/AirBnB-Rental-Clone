package HostGUI;

import java.awt.EventQueue;
import javax.swing.*;

import Controller.Controller;
import GUI.ConnectionManager;
import GUI.Login;
import GUI.MainModule;
import GUI.MainModule.EDITPROPERTY;
import GUI.MainModule.STATE;
import GUI.MainModule.USER;
import Model.Model;

import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class AddProperty extends JFrame {

	private NavHost navForHost = new NavHost();
	private JFrame frame;
	private JTextField postcodeTextField;
	private JTextField streetNameTextField;
	private JComboBox cityComboBox;
	private JTextField houseNameNumberTextField;
	private JButton resetEditPropertyButton;
	private JButton addEditPropertyButton;
	private JTextField shortNameTextField;
	private JTextField guestCapacityTextField;
	private JTextField descriptionTextField;
	private int propertyId;
	private int hostId;
	Connection connection = null;

	/**
	 * Create the application.
	 */

	private Controller controller;
	private Model model;
	private MainModule mainModule;

	public void close() {
		frame.dispose();
	}

	public AddProperty(MainModule mainModule, Controller controller, Model model) {
		// initializeEditProperty();
		this.model = model;
		this.mainModule = mainModule;
		this.controller = controller;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void adddingFacility() {
		JOptionPane.showMessageDialog(this, "You must save a property before adding a facility");
	}

	public void adddingChargeband() {
		JOptionPane.showMessageDialog(this, "You must save a property before adding a charge band");
	}

	public void initializeEditProperty() {
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		JPanel editPropertyPanel = new JPanel();
		editPropertyPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(editPropertyPanel, BorderLayout.CENTER);
		editPropertyPanel.setLayout(null);

		JLabel editPropertyLabel = new JLabel("Property details");
		editPropertyLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editPropertyLabel.setBounds(222, 53, 183, 57);
		editPropertyPanel.add(editPropertyLabel);

		JButton addFacilityButton = new JButton("Add Facility");

		addFacilityButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState = EDITPROPERTY.ADD_FACILITY;
				int facilitiesId = 0;

				if (model.getEditPropertyPostcode() == null) {
					adddingFacility();
				} else {
					try {

						String insertFacilitiesId = "insert into Facilities(property_id, kitchen_id, sleeping_id, bathing_id, "
								+ "living_id, utility_id, outdoors_id) values((SELECT property_id FROM Property WHERE address_id = (SELECT address_id FROM Address WHERE houseNameNumber = ? AND postcode =?)),?,?,?,?,?,?)";
						PreparedStatement ps_facilities = connection.prepareStatement(insertFacilitiesId,
								Statement.RETURN_GENERATED_KEYS);

						ps_facilities.setString(1, model.getEditPropertyHouseNameNum());
						ps_facilities.setString(2, model.getEditPropertyPostcode());
						ps_facilities.setNull(3, 0);
						ps_facilities.setNull(4, 0);
						ps_facilities.setNull(5, 0);
						ps_facilities.setNull(6, 0);
						ps_facilities.setNull(7, 0);
						ps_facilities.setNull(8, 0);

						System.out.println(ps_facilities);
						ps_facilities.executeUpdate();

						ResultSet rs = ps_facilities.getGeneratedKeys();
						if (rs.next()) {
							facilitiesId = rs.getInt(1);
						}

						// get propertyID and put into the Fcailties table. By default it's the latest

					} catch (Exception s) {
						System.err.println("Got an exception!");
						System.err.println(s.getMessage());
					}

					System.out.println("FACILITIES IDDDDDDDDDDDD = " + facilitiesId);
					model.setFacilitiesId(facilitiesId);
					frame.dispose();
					MainModule.controller.editPropertyView(facilitiesId, 0);
					close();
				}
			}
		});
		addFacilityButton.setBounds(203, 212, 183, 34);
		editPropertyPanel.add(addFacilityButton);

//		JButton facilitiesButton = new JButton("Facilities");
//		facilitiesButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				mainModule.editPropertyState= EDITPROPERTY.FACILITIES;
//				MainModule.controller.editPropertyView(0, 0); //fix the params
//				close();
//			}
//		});
//		facilitiesButton.setBounds(203, 163, 183, 34);
//		editPropertyPanel.add(facilitiesButton);

		JLabel postcodeLabel = new JLabel("Postcode:");
		postcodeLabel.setBounds(104, 325, 93, 34);
		editPropertyPanel.add(postcodeLabel);

		postcodeTextField = new JTextField();
		postcodeTextField.setColumns(10);
		postcodeTextField.setBounds(202, 325, 274, 34);
		editPropertyPanel.add(postcodeTextField);

		JLabel streetNameLabel = new JLabel("Street Name:");
		streetNameLabel.setBounds(104, 370, 93, 34);
		editPropertyPanel.add(streetNameLabel);

		streetNameTextField = new JTextField();
		streetNameTextField.setColumns(10);
		streetNameTextField.setBounds(202, 370, 274, 34);
		editPropertyPanel.add(streetNameTextField);

		JLabel cityLabel = new JLabel("City/Town:");
		cityLabel.setBounds(104, 415, 93, 34);
		editPropertyPanel.add(cityLabel);
		
		 String cityNames[] = { "Bath", "Birmingham", "Bradford", "Brighton and Hove", "Bristol", "Cambridge", 
					"Canterbury", "Carlisle", "Chelmsford", "Chester", "Chichester", "Coventry", 
					"Derby", "Durham", "Ely", "Exeter", "Gloucester", "Hereford", "Kingston upon Hull", 
					"Lancaster", "Leeds", "Leicester", "Lichfield", "Lincoln", "Liverpool", "London", "Manchester", 
					"Newcastle upon Tyne", "Norwich", "Nottingham", "Oxford", "Peterborough", "Plymouth", "Portsmouth", 
					"Preston", "Ripon", "Salford", "Salisbury", "Sheffield", "Southampton", "St Albans", "Stoke-on-Trent", 
					"Sunderland", "Truro", "Wakefield", "Wells", "Westminster", "Winchester", "Wolverhampton", "Worcester", "York" };
	 	cityComboBox = new JComboBox(cityNames);
		cityComboBox.setBounds(202, 415, 274, 34);
		editPropertyPanel.add(cityComboBox);

		JLabel houseNameNumberLabel = new JLabel("House Name/Number:");
		houseNameNumberLabel.setBounds(104, 456, 93, 34);
		editPropertyPanel.add(houseNameNumberLabel);

		houseNameNumberTextField = new JTextField();
		houseNameNumberTextField.setColumns(10);
		houseNameNumberTextField.setBounds(202, 460, 274, 34);
		editPropertyPanel.add(houseNameNumberTextField);

		JLabel shortNameLabel = new JLabel("Short Name:");
		shortNameLabel.setBounds(104, 508, 93, 34);
		editPropertyPanel.add(shortNameLabel);

		shortNameTextField = new JTextField();
		shortNameTextField.setColumns(10);
		shortNameTextField.setBounds(202, 508, 274, 34);
		editPropertyPanel.add(shortNameTextField);

		JLabel guestCapacityLabel = new JLabel("Guest Capacity");
		guestCapacityLabel.setBounds(104, 549, 93, 34);
		editPropertyPanel.add(guestCapacityLabel);

		guestCapacityTextField = new JTextField();
		guestCapacityTextField.setColumns(10);
		guestCapacityTextField.setBounds(202, 553, 274, 34);
		editPropertyPanel.add(guestCapacityTextField);

		JLabel descriptionLabel = new JLabel("Description"); // fix text box dimensions
		descriptionLabel.setBounds(104, 598, 93, 34);
		editPropertyPanel.add(descriptionLabel);

		descriptionTextField = new JTextField();
		descriptionTextField.setColumns(10);
		descriptionTextField.setBounds(202, 598, 274, 120);
		editPropertyPanel.add(descriptionTextField);

		JButton reviewsButton = new JButton("Reviews");
		reviewsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState = EDITPROPERTY.REVIEWS;
				MainModule.controller.editPropertyView(0, 0); // fix params
				close();
			}
		});
		reviewsButton.setBounds(203, 118, 183, 34);

		editPropertyPanel.add(reviewsButton);

		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(22, 75, 91, 23);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Homepage sp = new Homepage();

				mainModule.currentState = STATE.HOST_ACCOUNT;
				mainModule.userState = USER.HOST;
				MainModule.controller.drawNewView();
//				close();
				model.setEditPropertyPostcode(null);
				frame.dispose();

			}
		});

		editPropertyPanel.add(backButton);

		JButton addChargeBandsButton = new JButton("Add Charge Bands");
		addChargeBandsButton.setBounds(203, 261, 183, 34);
		addChargeBandsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState = EDITPROPERTY.CHARGEBANDS;

				int property_id = 0;

				if (model.getEditPropertyPostcode() == null) {
					adddingChargeband();
				} else {

					try {

						// START HERE
						connection = ConnectionManager.getConnection();
						int tempId = 0;
						Statement getPropertyId = connection.createStatement();
						ResultSet propertyIds = getPropertyId.executeQuery("SELECT * FROM Property");
						while (propertyIds.next()) {
							tempId = propertyIds.getInt(1);
							// System.out.println("THIS IS THE ID: "+tempId);
						}
						System.out.println("THIS IS THE LAST PROPERTY ID IN THE TABLE ID: " + tempId);
						// System.out.println("THIS RSULT SET OF IDS "+propertyIds);
//							String getLastPropertyId = "SELECT propert_id FROM Property";
//							PreparedStatement ps_getLastPropertyId = getLastPropertyId.executeQuery();

						// String currentPostcode = model.getEditPropertyPostcode();
						// System.out.println("PROPERTY POSTCODEEEEEEE = "+currentPostcode);
						// String getCurrentPropertyId = "select property_id from Property where
						// postcode=?";
						// PreparedStatement gettingCurrentPropertyId =
						// connection.prepareStatement(getCurrentPropertyId);

						// gettingCurrentPropertyId.setString(1, currentPostcode);

						// int property_id = 0;

						// ResultSet prop_id = gettingCurrentPropertyId.executeQuery();
						// while (prop_id.next()) {
						// property_id = prop_id.getInt("property_id");
						// }
						// System.out.println("CURRENT PROPERTY ID ---------------------"+property_id);
						propertyId = tempId;
						model.setPropertyId(property_id);

						// END HERE

						// System.out.println("CURRENT PROPERTY ID ---------------------"+property_id);

					} catch (Exception s) {
						System.err.print(s.getMessage());
					}

					// System.out.println("PCCCCCCCCCCCC = "+model.getEditPropertyPostcode());
					mainModule.userState = USER.HOST;
					// System.out.println("�����������������������ADD CHARGE BANDS BTN CLICKED
					// "+mainModule.editPropertyState);
					System.out.println("PROPERTY ID AT THE END OF ADD PROPERTY: " + propertyId);
					System.out.println("HOST ID IN ADD PROPERTY: "+model.getHostId());
					MainModule.controller.editPropertyView(propertyId, model.getHostId());
					// close();
					// model.setEditPropertyPostcode(null);
					frame.dispose();
					close();
				}
			}
		});
		editPropertyPanel.add(addChargeBandsButton);

		addEditPropertyButton = new JButton("Save");
		addEditPropertyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Boolean validateGuestCapacityInput = validateGuestCapacity(guestCapacityTextField.getText());
				// System.out.println(guestCapacityTextField.getSelectedText());

				if (validateGuestCapacityInput) {
					// setting all temp facility id's to zero so host can get access to fill in each
					// facility once

					model.setCurrentKitchedId(0);
					model.setCurrentBathingId(0);
					model.setCurrentLivingId(0);
					model.setCurrentUtilityId(0);
					model.setCurrentOutdoorsId(0);
					System.out.println("btn pressed");
					addEditPropertyDetails();

				} else {
					JOptionPane.showMessageDialog(guestCapacityTextField,
							"Guest capacity must be a number greater than 0");
				}

			}
		});
		addEditPropertyButton.setBounds(415, 701, 91, 23);
		editPropertyPanel.add(addEditPropertyButton);

		resetEditPropertyButton = new JButton("Reset");
		resetEditPropertyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				houseNameNumberTextField.setText("");
				streetNameTextField.setText("");
				cityComboBox.setSelectedItem("Bath");
				postcodeTextField.setText("");
			}
		});
		resetEditPropertyButton.setBounds(201, 611, 91, 23);
		editPropertyPanel.add(resetEditPropertyButton);

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void addEditPropertyDetails() {
		System.out.println("in btn addprop func");
		try {
			connection = ConnectionManager.getConnection();

			model.setEditPropertyHouseNameNum(houseNameNumberTextField.getText());
			model.setEditPropertyStreetName(streetNameTextField.getText());
			model.setEditPropertyCity(cityComboBox.getSelectedItem().toString());
			model.setEditPropertyPostcode(postcodeTextField.getText());
			model.setEditPropertyShortName(shortNameTextField.getText());
			model.setEditPropertyGuestCapacity(Integer.parseInt(guestCapacityTextField.getText()));
			model.setEditPropertyDescription(descriptionTextField.getText());

			String insertPropertyAddressQuery = "insert into Address(houseNameNumber, streetName, placeName, postcode) values(?,?,?,?) ";

			PreparedStatement propertyAddress = connection.prepareStatement(insertPropertyAddressQuery);
			propertyAddress.setString(1, model.getEditPropertyHouseNameNum());
			propertyAddress.setString(2, model.getEditPropertyStreetName());
			propertyAddress.setString(3, model.getEditPropertyCity());
			propertyAddress.setString(4, model.getEditPropertyPostcode());

			propertyAddress.executeUpdate();
			System.out.println(propertyAddress.toString());

			String insertPropertyAddressInPropertyQuery = "insert into Property (address_id , shortName, guestCapacity, description) values((SELECT address_id FROM Address WHERE houseNameNumber = ? AND postcode = ?),?,?,?) ";

			PreparedStatement propertyAddressInProperty = connection
					.prepareStatement(insertPropertyAddressInPropertyQuery);
			propertyAddressInProperty.setString(1, model.getEditPropertyHouseNameNum());
			propertyAddressInProperty.setString(2, model.getEditPropertyPostcode());
			propertyAddressInProperty.setString(3, model.getEditPropertyShortName());
			propertyAddressInProperty.setInt(4, model.getEditPropertyGuestCapacity());
			propertyAddressInProperty.setString(5, model.getEditPropertyDescription());

			propertyAddressInProperty.executeUpdate();
			System.out.println(propertyAddressInProperty.toString());

			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ = " + model.getEmail());

			String getHostIDOfUser = "select host_id from HostAccount where email=?";
			PreparedStatement hostIDfromHostAccountTable = connection.prepareStatement(getHostIDOfUser);
			hostIDfromHostAccountTable.setString(1, model.getEmail());
			int id = 0;
			ResultSet h_id = hostIDfromHostAccountTable.executeQuery();
			while (h_id.next()) {
				id = h_id.getInt(1);
				System.out.println("host id = " + id);
			}
			System.out.println("host id  after = " + id);
			hostId=id;
			model.setHostId(hostId);
			
			String insertHostIDInProperty = "update Property set host_id=? where address_id = (SELECT address_id FROM Address WHERE houseNameNumber = ? AND postcode = ?) ";
			PreparedStatement hostIDInProperty = connection.prepareStatement(insertHostIDInProperty);
			hostIDInProperty.setInt(1, model.getHostId());
			hostIDInProperty.setString(2, model.getEditPropertyHouseNameNum());
			hostIDInProperty.setString(3, model.getEditPropertyPostcode());
			hostIDInProperty.executeUpdate();
			System.out.println(hostIDInProperty.toString());

		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}

	public boolean validateGuestCapacity(String guestCapacity) {
		System.out.println("HERE****************");

		if (guestCapacity.matches("[1-9]*") && (guestCapacity.length() >= 1)) {
			// System.out.println("First name contains a characters not between a-z or
			// A-Z");
			System.out.println(guestCapacity + " IS  VALID****************");

			return true;
		} else {
			System.out.println(guestCapacity + " IS NOT VALID***************");
			return false;
		}

	}

}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW