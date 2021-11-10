
package HostGUI;

import java.awt.EventQueue;
import javax.swing.*;

import Controller.Controller;
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
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class EditProperty extends JFrame{


	private JFrame frame;
	private JTextField listingNameTextField;
	private JTextField postcodeTextField;
	private JTextField streetNameTextField;
	private JTextField placeNameTextField;
	private JTextField houseNameTextField;

//	public void close() {
//		frame.dispose();
//	}

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
		
		listingNameTextField = new JTextField();
		listingNameTextField.setBounds(204, 305, 274, 34);
		registerPanel.add(listingNameTextField);
		listingNameTextField.setColumns(10);
		
		JLabel listingNameLabel = new JLabel("Listing Name:");
		listingNameLabel.setBounds(105, 305, 93, 34);
		registerPanel.add(listingNameLabel);
		
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
		
		JLabel placeNameLabel = new JLabel("Place Name:");
		placeNameLabel.setBounds(105, 440, 93, 34);
		registerPanel.add(placeNameLabel);
		
		placeNameTextField = new JTextField();
		placeNameTextField.setColumns(10);
		placeNameTextField.setBounds(203, 440, 274, 34);
		registerPanel.add(placeNameTextField);
		
		JLabel houseNameLabel = new JLabel("House Name:");
		houseNameLabel.setBounds(105, 481, 93, 34);
		registerPanel.add(houseNameLabel);
		
		houseNameTextField = new JTextField();
		houseNameTextField.setColumns(10);
		houseNameTextField.setBounds(203, 485, 274, 34);
		registerPanel.add(houseNameTextField);


		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW