
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


	private JFrame frame;
	private JTextField postcodeTextField;
	private JTextField streetNameTextField;
	private JTextField cityTextField;
	private JTextField houseNameNumberTextField;
	private JButton resetEditPropertyButton;
	private JButton addEditPropertyButton;

	Connection connection = null;

	/**
	 * Create the application.
	 */

	 private Controller controller;
	 private Model model;
	 private MainModule mainModule;
	 
	 public EditProperty(MainModule mainModule, Controller controller, Model model) {
		//initializeHomePage();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditProperty() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(204, 255, 255));

		JPanel navBarPanel = new JPanel();
		navBarPanel.setBackground(new Color(51, 255, 255));
		frame.getContentPane().add(navBarPanel, BorderLayout.NORTH);

		JButton navHomeButton = new JButton("Home");
		navHomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.currentState = STATE.HOMEPAGE;
				MainModule.controller.drawNewView();
//				close();
			}
		});
		navBarPanel.add(navHomeButton);
		JButton navSearchButton = new JButton("Search");
		navSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.currentState = STATE.SEARCH;
				MainModule.controller.drawNewView();
//				close();
			}
		});
		navBarPanel.add(navSearchButton);

		JButton navRegisterButton = new JButton("Register");
		navRegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.currentState = STATE.SELF_REGISTRATION;
				MainModule.controller.drawNewView();
//				close();
			}
		});
		navBarPanel.add(navRegisterButton);

		JButton navLogoutButton = new JButton("Logout");
		navLogoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.currentState = STATE.HOMEPAGE;
				mainModule.userState = USER.ENQUIRER;
				MainModule.controller.drawNewView();
//				close();
			}
		});
		navBarPanel.add(navLogoutButton);

		JPanel registerPanel = new JPanel();
		registerPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(registerPanel, BorderLayout.CENTER);
		registerPanel.setLayout(null);

		JLabel editPropertyLabel = new JLabel("Property details");
		editPropertyLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editPropertyLabel.setBounds(222, 53, 183, 57);
		registerPanel.add(editPropertyLabel);
		
		JButton facilitiesButton = new JButton("Facilities");
		facilitiesButton.setBounds(203, 177, 183, 34);
		registerPanel.add(facilitiesButton);
		
		JButton addFacilityButton = new JButton("Add Facility");
		addFacilityButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState= EDITPROPERTY.ADD_FACILITY;
				MainModule.controller.editPropertyView();
			}
		});
		addFacilityButton.setBounds(203, 235, 183, 34);
		registerPanel.add(addFacilityButton);
				
		JLabel postcodeLabel = new JLabel("Postcode:");
		postcodeLabel.setBounds(105, 350, 93, 34);
		registerPanel.add(postcodeLabel);
		
		postcodeTextField = new JTextField();
		postcodeTextField.setColumns(10);
		postcodeTextField.setBounds(203, 350, 274, 34);
		registerPanel.add(postcodeTextField);
		
		JLabel streetNameLabel = new JLabel("Street Name:");
		streetNameLabel.setBounds(105, 395, 93, 34);
		registerPanel.add(streetNameLabel);
		
		streetNameTextField = new JTextField();
		streetNameTextField.setColumns(10);
		streetNameTextField.setBounds(203, 395, 274, 34);
		registerPanel.add(streetNameTextField);
		
		JLabel cityLabel = new JLabel("City/Town:");
		cityLabel.setBounds(105, 440, 93, 34);
		registerPanel.add(cityLabel);
		
		cityTextField = new JTextField();
		cityTextField.setColumns(10);
		cityTextField.setBounds(203, 440, 274, 34);
		registerPanel.add(cityTextField);
		
		JLabel houseNameNumberLabel = new JLabel("House Name/Number:");
		houseNameNumberLabel.setBounds(105, 481, 93, 34);
		registerPanel.add(houseNameNumberLabel);
		
		houseNameNumberTextField = new JTextField();
		houseNameNumberTextField.setColumns(10);
		houseNameNumberTextField.setBounds(203, 485, 274, 34);
		registerPanel.add(houseNameNumberTextField);
		
		addEditPropertyButton = new JButton("Save");
		addEditPropertyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEditPropertyDetails();
			}
		});
		addEditPropertyButton.setBounds(385, 553, 91, 23);
		registerPanel.add(addEditPropertyButton);
		
		
		resetEditPropertyButton = new JButton("Reset");
		resetEditPropertyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				houseNameNumberTextField.setText("");
				streetNameTextField.setText("");
				cityTextField.setText("");
				postcodeTextField.setText("");
			}
		});
		resetEditPropertyButton.setBounds(185, 553, 91, 23);
		registerPanel.add(resetEditPropertyButton);


		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void addEditPropertyDetails() {
		try {
			connection = ConnectionManager.getConnection();
			String insertPropertyAddressQuery = "insert into Address values(?,?,?,?) ";	
			
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
			

			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ = "+model.getEmail());
			
			String finalQuery = "insert into Property(houseNameNumber, postcode, host_id) "
								+ "select houseNameNumber, postcode, host_id "
								+ "from Account, HostAccount "
								+ "where Account.email=?";

			PreparedStatement finalQ = connection.prepareStatement(finalQuery);
			finalQ.setString(1, model.getEmail());
			
			int i  = finalQ.executeUpdate();
			if(i>0) {
				System.out.println("7");
				System.out.println(this);
				 //remove later
			}
			
//			String getHostIDOfUser = "select host_id from HostAccount where email=?";			
//			PreparedStatement hostIDfromHostAccountTable = connection.prepareStatement(getHostIDOfUser);
//			hostIDfromHostAccountTable.setString(1, model.getEmail());
//			
//			String insertHostIDInProperty = "insert into Property (host_id) values (?)";			
//			PreparedStatement insertingHostIDInProperty = connection.prepareStatement(insertHostIDInProperty);
//			
//			String getHouseNameHumAndPostcodeOfUser = "select houseNameNumber, postcode from Account where email=?";			
//			PreparedStatement houseNameNumAndPostcodeFromAccountTable = connection.prepareStatement(getHouseNameHumAndPostcodeOfUser);
//			houseNameNumAndPostcodeFromAccountTable.setString(1, model.getEmail());
//			
//			String insertHouseNameNumAndPostcodeInProperty = "insert into Property (houseNameNumber, postcode) values (?,?)";			
//			PreparedStatement insertingHouseNameNumAndPostcodeInProperty = connection.prepareStatement(insertHouseNameNumAndPostcodeInProperty);
			
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
	
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW