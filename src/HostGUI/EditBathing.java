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
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class EditBathing extends JFrame{


	private JFrame frame;
	private JTextField noOfBathroomsTextField; 
	private JRadioButton toiletPaperRadioBtn;
	private JRadioButton hairDryerRadioBtn;
	private JButton addBathing;
	private NavHost navForHost = new NavHost();
	
	Connection connection = null;

	public void close() {
		frame.dispose();
	}

	/**
	 * Create the application.
	 */

	 private Controller controller;
	 private Model model;
	 private MainModule mainModule;
	 public EditBathing(MainModule mainModule, Controller controller, Model model) {
		//initializeEditBathing();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditBathing() {
		
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		/*
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
		*/
		JPanel editBathingPanel = new JPanel();
		editBathingPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(editBathingPanel, BorderLayout.CENTER);
		editBathingPanel.setLayout(null);

		JLabel editBathingLabel = new JLabel("Add Bathing Facility");
		editBathingLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editBathingLabel.setBounds(170, 47, 261, 57);
		editBathingPanel.add(editBathingLabel);
		
		JLabel hairDryerLabel = new JLabel("Hair Dryer");
		hairDryerLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		hairDryerLabel.setBounds(170, 135, 167, 34);
		editBathingPanel.add(hairDryerLabel);

		hairDryerRadioBtn = new JRadioButton("Hair Dryer", false);
		hairDryerRadioBtn.setBounds(364, 146, 21, 23);
		editBathingPanel.add(hairDryerRadioBtn);
		
		JLabel toiletPaperLabel = new JLabel("Toilet Paper");
		toiletPaperLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toiletPaperLabel.setBounds(170, 191, 167, 34);
		editBathingPanel.add(toiletPaperLabel);
		
		toiletPaperRadioBtn = new JRadioButton("Toilet paper", false);
		toiletPaperRadioBtn.setBounds(364, 199, 21, 23);
		editBathingPanel.add(toiletPaperRadioBtn);
		
//		JLabel noOfBathroomsLabel = new JLabel("Number Of Bathrooms");
//		noOfBathroomsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		noOfBathroomsLabel.setBounds(170, 254, 167, 34);
//		editBathingPanel.add(noOfBathroomsLabel);
//		
//		noOfBathroomsTextField = new JTextField();
//		noOfBathroomsTextField.setBounds(347, 254, 106, 29);
//		editBathingPanel.add(noOfBathroomsTextField);
//		noOfBathroomsTextField.setColumns(10);
//		
//		JButton addBathroomButton = new JButton("Add Bathroom");
//		addBathroomButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				mainModule.editPropertyState= EDITPROPERTY.EDIT_BATHROOM;
//				MainModule.controller.editPropertyView(0);
//			}
//		});
//		addBathroomButton.setBounds(207, 395, 209, 46);
//		editBathingPanel.add(addBathroomButton);
		
		addBathing= new JButton("Add Bathrooms");
		addBathing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				addBathingDetails();
				mainModule.editPropertyState= EDITPROPERTY.EDIT_BATHROOM;
				MainModule.controller.editPropertyView(0);
			}
		});
		addBathing.setBounds(258, 329, 200, 23);
		editBathingPanel.add(addBathing);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(29, 57, 91, 23);
		editBathingPanel.add(backButton);

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
//	public void addBathingDetails() { //has to be fixed to exclude noOfBathrooms
//		try {
//			connection = ConnectionManager.getConnection();
//
//			model.setHairDryer(hairDryerRadioBtn.isSelected());
//			model.setToiletPaper(toiletPaperRadioBtn.isSelected());
//			model.setNoOfBathrooms(Integer.parseInt(noOfBathroomsTextField.getText().toString()));
//			
//			String insertBathingQuery = "insert into Bathing (hairDryer, toiletPaper, noOfBathrooms)"
//										+ " values(?,?,?)";
//			PreparedStatement ps_bathing= connection.prepareStatement(insertBathingQuery);
//			
//			ps_bathing.setBoolean(1, model.getHairDryer());
//			ps_bathing.setBoolean(2, model.getToiletPaper());
//			ps_bathing.setInt(3, model.getNoOfBathrooms());
//
//			System.out.println(ps_bathing);
//			ps_bathing.executeUpdate();
//			
//			
//		} catch(Exception e) {
//			System.err.println("Got an exception!");
//			System.err.println(e.getMessage());
//		}
//	}
//	
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW