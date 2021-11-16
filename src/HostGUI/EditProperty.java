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

public class EditProperty extends JFrame{

	private NavHost navForHost = new NavHost();
	private JFrame frame;
	private JTextField postcodeTextField;
	private JTextField streetNameTextField;
	private JTextField cityTextField;
	private JTextField houseNameNumberTextField;
	private JButton resetEditPropertyButton;
	private JButton addEditPropertyButton;
	private JTextField shortNameTextField;
	 private JTextField guestCapacityTextField;
	 
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
	 
	 public EditProperty(MainModule mainModule, Controller controller, Model model) {
		//initializeEditProperty();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditProperty() {
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);
			
		}catch(Exception e) {
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
		
		JButton facilitiesButton = new JButton("Facilities");
		facilitiesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState= EDITPROPERTY.FACILITIES;
				MainModule.controller.editPropertyView(0);
				close();
			}
		});
		facilitiesButton.setBounds(203, 163, 183, 34);
		editPropertyPanel.add(facilitiesButton);
		
		JButton addFacilityButton = new JButton("Add Facility");
		addFacilityButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState= EDITPROPERTY.ADD_FACILITY;
				MainModule.controller.editPropertyView(0);
				close();
			}
		});
		addFacilityButton.setBounds(203, 212, 183, 34);
		editPropertyPanel.add(addFacilityButton);
				
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
		
		cityTextField = new JTextField();
		cityTextField.setColumns(10);
		cityTextField.setBounds(202, 415, 274, 34);
		editPropertyPanel.add(cityTextField);
		
		JLabel houseNameNumberLabel = new JLabel("House Name/Number:");
		houseNameNumberLabel.setBounds(104, 456, 93, 34);
		editPropertyPanel.add(houseNameNumberLabel);
		
		houseNameNumberTextField = new JTextField();
		houseNameNumberTextField.setColumns(10);
		houseNameNumberTextField.setBounds(202, 460, 274, 34);
		editPropertyPanel.add(houseNameNumberTextField);
		
		
		
		resetEditPropertyButton = new JButton("Reset");
		resetEditPropertyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				houseNameNumberTextField.setText("");
				streetNameTextField.setText("");
				cityTextField.setText("");
				postcodeTextField.setText("");
			}
		});
		resetEditPropertyButton.setBounds(201, 611, 91, 23);
		editPropertyPanel.add(resetEditPropertyButton);
		
		JLabel shortNameLabel = new JLabel("Short Name:");
		shortNameLabel.setBounds(104, 508, 93, 34);
		editPropertyPanel.add(shortNameLabel);
		
		JLabel guestCapacityLabel = new JLabel("Guest Capacity");
		guestCapacityLabel.setBounds(104, 549, 93, 34);
		editPropertyPanel.add(guestCapacityLabel);
		
		shortNameTextField = new JTextField();
		shortNameTextField.setColumns(10);
		shortNameTextField.setBounds(202, 508, 274, 34);
		editPropertyPanel.add(shortNameTextField);
		
		guestCapacityTextField = new JTextField();
		guestCapacityTextField.setColumns(10);
		guestCapacityTextField.setBounds(202, 553, 274, 34);
		editPropertyPanel.add(guestCapacityTextField);
		
		JButton reviewsButton = new JButton("Reviews");
		reviewsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState= EDITPROPERTY.REVIEWS;
				MainModule.controller.editPropertyView(0);
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
				//Homepage sp = new Homepage();

				mainModule.currentState=STATE.HOST_ACCOUNT;
				mainModule.userState=USER.HOST;
				MainModule.controller.drawNewView();
//				close();
				frame.dispose();
				
			}
		});
		
		editPropertyPanel.add(backButton);
		
		JButton addChargeBandsButton = new JButton("Charge Bands");
		addChargeBandsButton.setBounds(203, 261, 183, 34);
		editPropertyPanel.add(addChargeBandsButton);


		
		addEditPropertyButton = new JButton("Save");
			addEditPropertyButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Boolean validateGuestCapacityInput = validateGuestCapacity(guestCapacityTextField.getText());
					//System.out.println(guestCapacityTextField.getSelectedText());
					
					if (validateGuestCapacityInput) {
						addEditPropertyDetails();
						
					}else {
						JOptionPane.showMessageDialog(guestCapacityTextField,"Guest capacity must be a number greater than 0");
					}
					
				}
			});
		addEditPropertyButton.setBounds(385, 611, 91, 23);
		editPropertyPanel.add(addEditPropertyButton);
		
	
		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void addEditPropertyDetails() {
		try {
			connection = ConnectionManager.getConnection();
			String insertPropertyAddressQuery = "insert into Address values(?,?,?,?) ";	
			String insertPropertyAddressInPropertyQuery = "insert into Property (houseNameNumber, postcode) values(?,?) ";	
			
			model.setEditPropertyHouseNameNum(houseNameNumberTextField.getText());
			model.setEditPropertyStreetName(streetNameTextField.getText());
			model.setEditPropertyCity(cityTextField.getText()); 
			model.setEditPropertyPostcode(postcodeTextField.getText());
			
			PreparedStatement propertyAddress = connection.prepareStatement(insertPropertyAddressQuery);
			propertyAddress.setString(1, model.getEditPropertyHouseNameNum());
			propertyAddress.setString(2, model.getEditPropertyStreetName());
			propertyAddress.setString(3, model.getEditPropertyCity());
			propertyAddress.setString(4, model.getEditPropertyPostcode());
			
			int  y = propertyAddress.executeUpdate();
			if(y>0) {
				System.out.println(this);
				JOptionPane.showMessageDialog(this, "Saved property address!"); //remove later
			}
			
			PreparedStatement propertyAddressInProperty = connection.prepareStatement(insertPropertyAddressInPropertyQuery);
			propertyAddressInProperty.setString(1, model.getEditPropertyHouseNameNum());
			propertyAddressInProperty.setString(2, model.getEditPropertyPostcode());

			int  u = propertyAddressInProperty.executeUpdate();
			if(u>0) {
				System.out.println(this);
				JOptionPane.showMessageDialog(this, "Saved property address!"); //remove later
			}


			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ = "+model.getEmail());
			
			
			String getHostIDOfUser = "select host_id from HostAccount where email=?";			
			PreparedStatement hostIDfromHostAccountTable = connection.prepareStatement(getHostIDOfUser);
			hostIDfromHostAccountTable.setString(1, model.getEmail());
			int id = 0;
			ResultSet h_id = hostIDfromHostAccountTable.executeQuery();
			while (h_id.next()) {
			 id = h_id.getInt(1);
			 System.out.println("host id = "+id);
			}
			 System.out.println("host id  after = "+id);
			 
			String insertHostIDInProperty = "update Property set host_id=? where houseNameNumber=? AND postcode=?";
			PreparedStatement hostIDInProperty= connection.prepareStatement(insertHostIDInProperty);
			hostIDInProperty.setInt(1, id);
			hostIDInProperty.setString(2, model.getEditPropertyHouseNameNum());
			hostIDInProperty.setString(3, model.getEditPropertyPostcode());
			hostIDInProperty.executeUpdate();
			System.out.println(hostIDInProperty.toString());
			
			
//			String insertHostIDInProperty = "insert into Property (host_id) values (?)";			
//			PreparedStatement insertingHostIDInProperty = connection.prepareStatement(insertHostIDInProperty);
//			
//			
//			PreparedStatement hostIDInProperty = connection.prepareStatement(insertHostIDInProperty);
//			hostIDInProperty.setInt(1, model.getEditPropertyHouseNameNum());
//			hostIDInProperty.setString(2, model.getEditPropertyPostcode());
//
//			int v = hostIDInProperty.executeUpdate();
//			if(v>0) {
//				System.out.println(this);
//				JOptionPane.showMessageDialog(this, "Saved property address!"); //remove later
//			}
			
//			String finalQuery = "insert into Property(houseNameNumber, postcode, host_id) "
//								+ "select houseNameNumber, postcode, host_id "
//								+ "from Account, HostAccount "
//								+ "where email=?";
			
//			String finalQuery = "insert into Property(host_id) "
////					+ "select houseNameNumber, postcode from Address"
//					+ "select host_id from HostAccount"
//					+ "where email=?";
////					+ "select houseNameNumber, postcode from Address"
////					+ "where host_id =";
//			
//			PreparedStatement finalQ = connection.prepareStatement(finalQuery);
//			finalQ.setString(1, model.getEmail());
//			
//			int i  = finalQ.executeUpdate();
//			if(i>0) {
//				System.out.println("7");
//				System.out.println(this);
//				 //remove later
//			}
//			
//			String getHostIDOfUser = "select host_id from HostAccount where email=?";			
//			PreparedStatement hostIDfromHostAccountTable = connection.prepareStatement(getHostIDOfUser);
//			hostIDfromHostAccountTable.setString(1, model.getEmail());
//			
//			String insertHostIDInProperty = "insert into Property (host_id) values (?)";			
//			PreparedStatement insertingHostIDInProperty = connection.prepareStatement(insertHostIDInProperty);
//			
//			String getHouseNameHumAndPostcodeOfUser = "select houseNameNumber, postcode from Address where email=?";			
//			PreparedStatement houseNameNumAndPostcodeFromAccountTable = connection.prepareStatement(getHouseNameHumAndPostcodeOfUser);
//			houseNameNumAndPostcodeFromAccountTable.setString(1, model.getEmail());
//			
//			String insertHouseNameNumAndPostcodeInProperty = "insert into Property (houseNameNumber, postcode) values (?,?)";			
//			PreparedStatement insertingHouseNameNumAndPostcodeInProperty = connection.prepareStatement(insertHouseNameNumAndPostcodeInProperty);
//			
//			int id = 0;
//			ResultSet h_id = hostIDfromHostAccountTable.executeQuery();
//			while (h_id.next()) {
//			 id = h_id.getInt(1);
//			 System.out.println("host id = "+id);
//			}
//			 System.out.println("host id  after = "+id);
//			 
//			insertingHostIDInProperty.setInt(1, id);
//			int  w = insertingHostIDInProperty.executeUpdate();
//			if(w>0) {
//				System.out.println(this);
//				JOptionPane.showMessageDialog(this, "inserted host id into property!"); //remove later
//			}
//			
//			String hnhn = "";
//			String pc = "";
//			ResultSet h_hnhmAndPc = houseNameNumAndPostcodeFromAccountTable.executeQuery();
//			while (h_hnhmAndPc.next()) {
//				 hnhn = h_hnhmAndPc.getString(1);
//				 pc = h_hnhmAndPc.getString(2);
//			 System.out.println("host hmhm = "+hnhn+" postcode = "+pc);
//			}
//			 System.out.println("host hmhm after = "+hnhn+" postcode after = "+pc);
//			 
//			 insertingHouseNameNumAndPostcodeInProperty.setString(1, hnhn);
//			 insertingHouseNameNumAndPostcodeInProperty.setString(2, pc);
//			int  z = insertingHouseNameNumAndPostcodeInProperty.executeUpdate();
//			if(z>0) {
//				System.out.println(this);
//				JOptionPane.showMessageDialog(this, "inserted hnhn and pc into property!"); //remove later
//			}
			 
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
	
	public boolean validateGuestCapacity(String guestCapacity) {
		System.out.println("HERE****************");	

		if (guestCapacity.matches("[1-9]*") && (guestCapacity.length() >= 1)) {
			//System.out.println("First name contains a characters not between a-z or A-Z");
			System.out.println(guestCapacity+" IS  VALID****************");	

			return true;
		}
		else {
			System.out.println(guestCapacity+" IS NOT VALID***************");
			return false;
		}

	}
	
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW