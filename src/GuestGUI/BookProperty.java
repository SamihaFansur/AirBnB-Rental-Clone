package GuestGUI;

import java.awt.EventQueue;
import javax.swing.*;

import Controller.Controller;
import GUI.ConnectionManager;
import GUI.Login;
import GUI.MainModule;
import GUI.MainModule.EDITPROPERTY;
import GUI.MainModule.STATE;
import GUI.MainModule.USER;
import HostGUI.NavHost;
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

public class BookProperty extends JFrame{

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
	 
	 public BookProperty(MainModule mainModule, Controller controller, Model model) {
		//initializeBookProperty();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeBookProperty(int propertyId, int id) {
		
		
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		JPanel bookPropertyPanel = new JPanel();
		bookPropertyPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(bookPropertyPanel, BorderLayout.CENTER);
		bookPropertyPanel.setLayout(null);
		
		idAfter = id;
		propertyidAfter = propertyId;
		System.out.println("Property ID  = "+propertyidAfter);
		System.out.println("id after = "+idAfter);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(31, 58, 91, 23);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mainModule.currentState = STATE.SEARCH;
				MainModule.controller.drawNewView(); //fix params
//				close();
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
		shortNamelabel.setBounds(31, 115, 112, 35);
		bookPropertyPanel.add(shortNamelabel);
		
		JLabel guestCapacityLabel = new JLabel("Guest Capacity:");
		guestCapacityLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		guestCapacityLabel.setBounds(31, 184, 112, 35);
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
		shortNameTextField.setBounds(166, 121, 360, 29);
		bookPropertyPanel.add(shortNameTextField);
		shortNameTextField.setColumns(10);
		
		guestCapacityTextField = new JTextField();
		guestCapacityTextField.setColumns(10);
		guestCapacityTextField.setBounds(166, 184, 131, 29);
		bookPropertyPanel.add(guestCapacityTextField);
		
		descriptionTextField = new JTextField();
		descriptionTextField.setColumns(10);
		descriptionTextField.setBounds(166, 247, 360, 93);
		bookPropertyPanel.add(descriptionTextField);
		
		JButton btnBookProperty = new JButton("Book Property");
		btnBookProperty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBookProperty.setBounds(200, 625, 237, 29);
		bookPropertyPanel.add(btnBookProperty);
		
		
		//INSERT Property INFO INTO TEXT FIELDS
		try {
		connection = ConnectionManager.getConnection();
		
		Statement stmt = connection.createStatement();
		String getInfo = "SELECT shortName, guestCapacity, description FROM Property WHERE property_id = " + propertyidAfter;
		System.out.println(getInfo);
		ResultSet rs = stmt.executeQuery(getInfo);
		int guestCapacity = 0;
		
		String shortName = "";
		String description = "";
		String guestCapacityString = String.valueOf(guestCapacity);
		
		while(rs.next()) {
			shortName = rs.getString("shortName");
			guestCapacity = rs.getInt("guestCapacity");
			description = rs.getString("description");

		}
		System.out.println(shortName);
		System.out.println(guestCapacity);
		System.out.println(description);
		
		
		shortNameTextField.setText(shortName);
		guestCapacityTextField.setText(guestCapacityString);
		descriptionTextField.setText(description);
		
		
		//INSERT facilities INTFO into text fields
		

		Statement stmt2 = connection.createStatement();
		String getSleeping = "SELECT sleeping_id FROM Facilities WHERE property_id = " + propertyidAfter;
		System.out.println(getSleeping);
		ResultSet rs2 = stmt2.executeQuery(getSleeping);
		
		
		
		int sleeping = 0;
	
		
		while(rs2.next()) {
			sleeping = rs2.getInt("sleeping");
			

		}
		System.out.println(sleeping);
		
		

		Statement stmt3 = connection.createStatement();
		String getBedrooms = "SELECT noOfBedrooms FROM Sleeping WHERE sleeping_id = " + sleeping;
		System.out.println(getBedrooms);
		ResultSet rs3 = stmt3.executeQuery(getBedrooms);
		
		
		
		int bedrooms = 0;
	
		
		while(rs3.next()) {
			bedrooms = rs3.getInt("noOfBedrooms");
			

		}
		System.out.println(bedrooms);
		
		Statement stmt4 = connection.createStatement();
		String getBeds = "SELECT noOfBeds FROM Sleeping WHERE sleeping_id = " + sleeping;
		System.out.println(getBeds);
		ResultSet rs4 = stmt4.executeQuery(getBeds);
		
		
		
		int beds = 0;
	
		
		while(rs4.next()) {
			beds = rs4.getInt("noOfBeds");
			

		}
		System.out.println(beds);
		
		Statement stmt5 = connection.createStatement();
		String getBathing = "SELECT bathing_id FROM Facilities WHERE property_id = " + propertyidAfter;
		System.out.println(getBathing);
		ResultSet rs5 = stmt5.executeQuery(getBathing);
		
		
		
		int bathing = 0;
	
		
		while(rs5.next()) {
			bathing = rs5.getInt("bathing");
			

		}
		System.out.println(bathing);
		
		
		Statement stmt6 = connection.createStatement();
		String getBathrooms = "SELECT noOfBathrooms FROM Bathing WHERE bathing_id = " + bathing;
		System.out.println(getBathrooms);
		ResultSet rs6 = stmt6.executeQuery(getBathrooms);
		
		
		
		int bathrooms = 0;
	
		
		while(rs6.next()) {
			bathrooms = rs6.getInt("noOfBathrooms");
			

		}
		System.out.println(bathrooms);
		
		
		
		numberOfBedroomsTextField.setText(String.valueOf(bedrooms));
		numberOfBedsTextField.setText(String.valueOf(beds));
		numberOfBathsTextField.setText(String.valueOf(bathrooms));
		

		
		JButton reviewsButton = new JButton("Property Reviews");
		reviewsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		reviewsButton.setBounds(224, 573, 196, 29);
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
		
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}