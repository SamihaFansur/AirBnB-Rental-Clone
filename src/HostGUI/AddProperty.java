package HostGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Controller.Controller;
import GUI.ConnectionManager;
import GUI.MainModule;
import GUI.MainModule.EDITPROPERTY;
import GUI.MainModule.STATE;
import GUI.MainModule.USER;
import Model.Model;

//Class to add a property to the database
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
	private JRadioButton breakfastRadioBtn;
	private boolean breakfastValue;
	private int propertyId;
	private int hostId;
	private int facilitiesId;
	Connection connection = null;


	private Controller controller;
	private Model model;
	private MainModule mainModule;

	//function to close the frame
	public void close() {
		frame.dispose();
	}

	//Constructor for addProperty
	public AddProperty(MainModule mainModule, Controller controller, Model model) {
		//initializeEditProperty();
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

		model.setPreviouslyInPropertiesList(false);
		try {
			frame = new JFrame();
			frame.setResizable(false);
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
			@Override
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState = EDITPROPERTY.ADD_FACILITY;
				model.setPreviouslyInPropertiesList(false);

				if (model.getEditPropertyPostcode() == null) {
					adddingFacility();
				} else {
					model.setFacilitiesId(facilitiesId);
					frame.dispose();
					MainModule.controller.editPropertyView(facilitiesId, 0);
					close();
				}
			}
		});
		addFacilityButton.setBounds(206, 142, 183, 34);
		editPropertyPanel.add(addFacilityButton);

		JLabel postcodeLabel = new JLabel("Postcode:");
		postcodeLabel.setBounds(97, 248, 93, 34);
		editPropertyPanel.add(postcodeLabel);

		postcodeTextField = new JTextField();
		postcodeTextField.setColumns(10);
		postcodeTextField.setBounds(195, 248, 274, 34);
		editPropertyPanel.add(postcodeTextField);

		JLabel streetNameLabel = new JLabel("Street Name:");
		streetNameLabel.setBounds(97, 293, 93, 34);
		editPropertyPanel.add(streetNameLabel);

		streetNameTextField = new JTextField();
		streetNameTextField.setColumns(10);
		streetNameTextField.setBounds(195, 293, 274, 34);
		editPropertyPanel.add(streetNameTextField);

		JLabel cityLabel = new JLabel("City/Town:");
		cityLabel.setBounds(97, 338, 93, 34);
		editPropertyPanel.add(cityLabel);

		String cityNames[] = { "Bath", "Birmingham", "Bradford", "Brighton and Hove", "Bristol", "Cambridge",
				"Canterbury", "Carlisle", "Chelmsford", "Chester", "Chichester", "Coventry", "Derby", "Durham", "Ely",
				"Exeter", "Gloucester", "Hereford", "Kingston upon Hull", "Lancaster", "Leeds", "Leicester",
				"Lichfield", "Lincoln", "Liverpool", "London", "Manchester", "Newcastle upon Tyne", "Norwich",
				"Nottingham", "Oxford", "Peterborough", "Plymouth", "Portsmouth", "Preston", "Ripon", "Salford",
				"Salisbury", "Sheffield", "Southampton", "St Albans", "Stoke-on-Trent", "Sunderland", "Truro",
				"Wakefield", "Wells", "Westminster", "Winchester", "Wolverhampton", "Worcester", "York" };
		cityComboBox = new JComboBox(cityNames);
		cityComboBox.setBounds(195, 338, 274, 34);
		editPropertyPanel.add(cityComboBox);

		JLabel houseNameNumberLabel = new JLabel("House Name/Number:");
		houseNameNumberLabel.setBounds(97, 379, 93, 34);
		editPropertyPanel.add(houseNameNumberLabel);

		houseNameNumberTextField = new JTextField();
		houseNameNumberTextField.setColumns(10);
		houseNameNumberTextField.setBounds(195, 383, 274, 34);
		editPropertyPanel.add(houseNameNumberTextField);

		JLabel shortNameLabel = new JLabel("Short Name:");
		shortNameLabel.setBounds(97, 431, 93, 34);
		editPropertyPanel.add(shortNameLabel);

		shortNameTextField = new JTextField();
		shortNameTextField.setColumns(10);
		shortNameTextField.setBounds(195, 431, 274, 34);
		editPropertyPanel.add(shortNameTextField);

		JLabel guestCapacityLabel = new JLabel("Guest Capacity");
		guestCapacityLabel.setBounds(97, 472, 93, 34);
		editPropertyPanel.add(guestCapacityLabel);

		guestCapacityTextField = new JTextField();
		guestCapacityTextField.setColumns(10);
		guestCapacityTextField.setBounds(195, 476, 274, 34);
		editPropertyPanel.add(guestCapacityTextField);

		JLabel descriptionLabel = new JLabel("Description"); // fix text box dimensions
		descriptionLabel.setBounds(97, 521, 93, 34);
		editPropertyPanel.add(descriptionLabel);

		descriptionTextField = new JTextField();
		descriptionTextField.setColumns(10);
		descriptionTextField.setBounds(195, 521, 274, 120);
		editPropertyPanel.add(descriptionTextField);
		
		JLabel breakfastLabel = new JLabel("Breakfast offered"); // fix text box dimensions
		breakfastLabel.setBounds(97, 655, 93, 34);
		editPropertyPanel.add(breakfastLabel);

		breakfastRadioBtn = new JRadioButton();
		breakfastRadioBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (breakfastRadioBtn.isSelected()) {
					breakfastValue = true;
				} else {
					breakfastValue = false;
				}
			}
		});
		
		breakfastRadioBtn.setBounds(196, 666, 30, 23);
		editPropertyPanel.add(breakfastRadioBtn);

		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(22, 75, 91, 23);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainModule.currentState = STATE.HOST_ACCOUNT;
				mainModule.userState = USER.HOST;
				MainModule.controller.drawNewView();
				model.setEditPropertyPostcode(null);
				frame.dispose();
			}
		});
		editPropertyPanel.add(backButton);
		//Button adds a charge band to the table and to the database
		JButton addChargeBandsButton = new JButton("Add Charge Bands");
		addChargeBandsButton.setBounds(206, 191, 183, 34);
		addChargeBandsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState = EDITPROPERTY.CHARGEBANDS;
				int property_id = 0;

				if (model.getEditPropertyPostcode() == null) {
					adddingChargeband();
				} else {

					try {

						connection = ConnectionManager.getConnection();
						int tempId = 0;
						Statement getPropertyId = connection.createStatement();
						ResultSet propertyIds = getPropertyId.executeQuery("SELECT * FROM Property");
						while (propertyIds.next()) {
							tempId = propertyIds.getInt(1);
						}
						propertyId = tempId;
						model.setPropertyId(property_id);

						connection.close();
					} catch (Exception s) {
						System.err.print(s.getMessage());
					}
					mainModule.userState = USER.HOST;
					MainModule.controller.editPropertyView(propertyId, model.getHostId());
					frame.dispose();
					close();
				}
			}
		});
		editPropertyPanel.add(addChargeBandsButton);

		//Button to save changes made to property textFields in the database
		addEditPropertyButton = new JButton("Save");
		addEditPropertyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Boolean validateGuestCapacityInput = validateGuestCapacity(guestCapacityTextField.getText());
				if (validateGuestCapacityInput) {
					model.setCurrentKitchedId(0);
					model.setCurrentBathingId(0);
					model.setCurrentLivingId(0);
					model.setCurrentUtilityId(0);
					model.setCurrentOutdoorsId(0);
					addEditPropertyDetails();
				} else {
					JOptionPane.showMessageDialog(guestCapacityTextField,
							"Guest capacity must be a number greater than 0");
				}
			}
		});
		addEditPropertyButton.setBounds(250, 711, 91, 23);
		editPropertyPanel.add(addEditPropertyButton);

		resetEditPropertyButton = new JButton("Reset");
		resetEditPropertyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				houseNameNumberTextField.setText("");
				streetNameTextField.setText("");
				cityComboBox.setSelectedItem("Bath");
				postcodeTextField.setText("");
			}
		});
		resetEditPropertyButton.setBounds(201, 611, 91, 23);
		editPropertyPanel.add(resetEditPropertyButton);

		frame.setBounds(100, 100, 600, 800);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	//Inserts edited property details into the database
	public void addEditPropertyDetails() {
		try {
			connection = ConnectionManager.getConnection();

			model.setEditPropertyHouseNameNum(houseNameNumberTextField.getText());
			model.setEditPropertyStreetName(streetNameTextField.getText());
			model.setEditPropertyCity(cityComboBox.getSelectedItem().toString());
			model.setEditPropertyPostcode(postcodeTextField.getText());
			model.setEditPropertyShortName(shortNameTextField.getText());
			model.setEditPropertyGuestCapacity(Integer.parseInt(guestCapacityTextField.getText()));
			model.setEditPropertyDescription(descriptionTextField.getText());
			model.setEditPropertyBreakfast(breakfastRadioBtn.isSelected());

			String insertPropertyAddressQuery = "insert into Address(houseNameNumber, streetName, placeName, postcode) values(?,?,?,?) ";

			PreparedStatement propertyAddress = connection.prepareStatement(insertPropertyAddressQuery);
			propertyAddress.setString(1, model.getEditPropertyHouseNameNum());
			propertyAddress.setString(2, model.getEditPropertyStreetName());
			propertyAddress.setString(3, model.getEditPropertyCity());
			propertyAddress.setString(4, model.getEditPropertyPostcode());

			propertyAddress.executeUpdate();

			String insertPropertyAddressInPropertyQuery = "insert into Property (address_id , shortName, guestCapacity, description, breakfast) values((SELECT address_id FROM Address WHERE houseNameNumber = ? AND postcode = ?),?,?,?,?) ";

			PreparedStatement propertyAddressInProperty = connection
					.prepareStatement(insertPropertyAddressInPropertyQuery);
			propertyAddressInProperty.setString(1, model.getEditPropertyHouseNameNum());
			propertyAddressInProperty.setString(2, model.getEditPropertyPostcode());
			propertyAddressInProperty.setString(3, model.getEditPropertyShortName());
			propertyAddressInProperty.setInt(4, model.getEditPropertyGuestCapacity());
			propertyAddressInProperty.setString(5, model.getEditPropertyDescription());
			propertyAddressInProperty.setBoolean(6, model.getEditPropertyBreakfast());

			propertyAddressInProperty.executeUpdate();

			String getHostIDOfUser = "select host_id from HostAccount where email=?";
			PreparedStatement hostIDfromHostAccountTable = connection.prepareStatement(getHostIDOfUser);
			hostIDfromHostAccountTable.setString(1, model.getEmail());
			int id = 0;
			ResultSet h_id = hostIDfromHostAccountTable.executeQuery();
			while (h_id.next()) {
				id = h_id.getInt(1);
			}
			hostId = id;
			model.setHostId(hostId);

			String insertHostIDInProperty = "update Property set host_id=? where address_id = (SELECT address_id FROM Address WHERE houseNameNumber = ? AND postcode = ?) ";
			PreparedStatement hostIDInProperty = connection.prepareStatement(insertHostIDInProperty);
			hostIDInProperty.setInt(1, model.getHostId());
			hostIDInProperty.setString(2, model.getEditPropertyHouseNameNum());
			hostIDInProperty.setString(3, model.getEditPropertyPostcode());
			hostIDInProperty.executeUpdate();

			String insertFacilitiesId = "insert into Facilities (property_id, kitchen_id, sleeping_id, bathing_id, "
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
			ps_facilities.executeUpdate();

			ResultSet rs = ps_facilities.getGeneratedKeys();
			if (rs.next()) {
				facilitiesId = rs.getInt(1);
			}

			connection.close();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
	//checks if guest capacity is a valid amount
	public boolean validateGuestCapacity(String guestCapacity) {
		if (guestCapacity.matches("[1-9]*") && (guestCapacity.length() >= 1)) {
			return true;
		} else {
			return false;
		}
	}
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW