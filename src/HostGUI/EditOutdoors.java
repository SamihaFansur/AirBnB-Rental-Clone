
package HostGUI;

import java.awt.EventQueue;
import javax.swing.*;

import Controller.Controller;
import GUI.ConnectionManager;
import GUI.Login;
import GUI.MainModule;
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
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class EditOutdoors extends JFrame{

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
	 private JButton addOutdoors;
	 private JRadioButton freeOnSiteParkingRadioButton;
	 private JRadioButton paidCarParkRadioButton;
	 private JRadioButton onRoadParkingRadioButton;
	 private JRadioButton patioRadioButton;
	 private JRadioButton barbequeRadioButton;
	 
	 Connection connection = null;
	 
	 public EditOutdoors(MainModule mainModule, Controller controller, Model model) {
		//initializeHomePage();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditOutdoors() {
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		JPanel registerPanel = new JPanel();
		registerPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(registerPanel, BorderLayout.CENTER);
		registerPanel.setLayout(null);

		JLabel editOutdoorsLabel = new JLabel("Add Outdoor Facility");
		editOutdoorsLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editOutdoorsLabel.setBounds(248, 61, 183, 57);
		registerPanel.add(editOutdoorsLabel);
		
		JLabel freeOnSiteParkingLabel = new JLabel("Free On Site Parking");
		freeOnSiteParkingLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		freeOnSiteParkingLabel.setBounds(173, 194, 167, 34);
		registerPanel.add(freeOnSiteParkingLabel);
		
		freeOnSiteParkingRadioButton = new JRadioButton("Free on site parking", false);
		freeOnSiteParkingRadioButton.setBounds(367, 205, 21, 23);
		registerPanel.add(freeOnSiteParkingRadioButton);
		
		JLabel onRoadParkingLabel = new JLabel("On Road Parking");
		onRoadParkingLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		onRoadParkingLabel.setBounds(173, 250, 167, 34);
		registerPanel.add(onRoadParkingLabel);
		
		onRoadParkingRadioButton = new JRadioButton("On road parking", false);
		onRoadParkingRadioButton.setBounds(367, 258, 21, 23);
		registerPanel.add(onRoadParkingRadioButton);
		
		
		JLabel paidCarParkLabel = new JLabel("Paid Car Parking");
		paidCarParkLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		paidCarParkLabel.setBounds(173, 313, 167, 34);
		registerPanel.add(paidCarParkLabel);
		
		paidCarParkRadioButton = new JRadioButton("Paid Car Parking", false);
		paidCarParkRadioButton.setBounds(367, 321, 21, 23);
		registerPanel.add(paidCarParkRadioButton);
		
		JLabel patioLabel = new JLabel("Patio");
		patioLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		patioLabel.setBounds(173, 383, 167, 34);
		registerPanel.add(patioLabel);
		
		patioRadioButton = new JRadioButton("Patio", false);
		patioRadioButton.setBounds(367, 391, 21, 23);
		registerPanel.add(patioRadioButton);
		
		JLabel barbequeLabel = new JLabel("Barbeque");
		barbequeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		barbequeLabel.setBounds(173, 446, 167, 34);
		registerPanel.add(barbequeLabel);
		
		barbequeRadioButton = new JRadioButton("Barbeque", false);
		barbequeRadioButton.setBounds(367, 454, 21, 23);
		registerPanel.add(barbequeRadioButton);
		
		addOutdoors = new JButton("Save");
		addOutdoors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addOutdoorsDetails();
			}
		});
		addOutdoors.setBounds(275, 500, 91, 23);
		registerPanel.add(addOutdoors);

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void addOutdoorsDetails() {
		try {
			connection = ConnectionManager.getConnection();

			model.setFreeOnSiteParking(freeOnSiteParkingRadioButton.isSelected());
			model.setOnRoadParking(onRoadParkingRadioButton.isSelected());
			model.setPaidCarPark(paidCarParkRadioButton.isSelected());
			model.setPatio(patioRadioButton.isSelected());
			model.setBarbeque(barbequeRadioButton.isSelected());
			
			String insertOutdoorsQuery = "insert into Outdoors (freeOnSiteParking, onRoadParking, "
										+ "paidCarPark, patio, barbeque)"
										+ " values(?,?,?,?,?) ";
			PreparedStatement ps_outdoors = connection.prepareStatement(insertOutdoorsQuery);
			
			ps_outdoors.setBoolean(1, model.getFreeOnSiteParking());
			ps_outdoors.setBoolean(2, model.getOnRoadParking());
			ps_outdoors.setBoolean(3, model.getPaidCarPark());
			ps_outdoors.setBoolean(4, model.getPatio());
			ps_outdoors.setBoolean(5, model.getBarbeque());

			System.out.println(ps_outdoors);
			ps_outdoors.executeUpdate();
			
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW