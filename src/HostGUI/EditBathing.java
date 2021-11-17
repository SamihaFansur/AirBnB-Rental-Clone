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

	private int idAfter;
	private int facilitiesidAfter;
	
	private boolean hairDryer, toiletPaper;
	
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
	public void initializeEditBathing(int facilitiesId, int id) {
		
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}

		idAfter = id;
		facilitiesidAfter = facilitiesId;
		System.out.println("FACILITY ID FOR WHICH AM CREATING BATHING RN = "+facilitiesidAfter);
		System.out.println("id after in init edit BATHING func = "+idAfter);
		
		JPanel editBathingPanel = new JPanel();
		editBathingPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(editBathingPanel, BorderLayout.CENTER);
		editBathingPanel.setLayout(null);

		JLabel editBathingLabel = new JLabel("Add Bathing Facility");
		editBathingLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editBathingLabel.setBounds(185, 57, 261, 57);
		editBathingPanel.add(editBathingLabel);
		
		try {
			connection = ConnectionManager.getConnection();

			System.out.println("id in try block where im tryna get values from db = "+id);
			
			String selectBathingRecord = "select hairDryer, toiletPaper from Bathing "
										+ "where bathing_id=?";
			
			PreparedStatement selectingBathingValues= connection.prepareStatement(selectBathingRecord);
			
			selectingBathingValues.setInt(1, id);
			ResultSet rs = selectingBathingValues.executeQuery();
			while (rs.next()) {
				hairDryer = rs.getBoolean("hairDryer");
                toiletPaper = rs.getBoolean("toiletPaper");
            }		
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		
		JLabel hairDryerLabel = new JLabel("Hair Dryer");
		hairDryerLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		hairDryerLabel.setBounds(170, 188, 167, 34);
		editBathingPanel.add(hairDryerLabel);

		hairDryerRadioBtn = new JRadioButton("Hair Dryer", hairDryer);
		hairDryerRadioBtn.setBounds(364, 188, 21, 23);
		editBathingPanel.add(hairDryerRadioBtn);
		
		JLabel toiletPaperLabel = new JLabel("Toilet Paper");
		toiletPaperLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toiletPaperLabel.setBounds(170, 285, 167, 34);
		editBathingPanel.add(toiletPaperLabel);
		
		toiletPaperRadioBtn = new JRadioButton("Toilet paper", toiletPaper);
		toiletPaperRadioBtn.setBounds(364, 296, 21, 23);
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
				System.out.println("printing id in add bathroom btn before calling updateBathingDetails func = "+id);
				System.out.println("printing idAfter in add bathroom btn before calling updateBathingDetails func = "+idAfter);
				updateBathingDetails(id);
				mainModule.editPropertyState= EDITPROPERTY.EDIT_BATHROOM;
				MainModule.controller.editPropertyView(facilitiesidAfter, id);
			}
		});
		addBathing.setBounds(185, 480, 200, 23);
		editBathingPanel.add(addBathing);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(44, 76, 91, 23);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Homepage sp = new Homepage();

			  //  mainModule.currentState=STATE.EDIT_PROPERTY;
				mainModule.userState=USER.HOST;
				mainModule.editPropertyState = EDITPROPERTY.ADD_FACILITY;
				MainModule.controller.editPropertyView(facilitiesidAfter, idAfter); //fix params
//				close();
				frame.dispose();
				
			}
		});	
		editBathingPanel.add(backButton);

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void updateBathingDetails(int id) {
		System.out.println("Printing id fed into updateBathingDetails = "+id);
		try {
			connection = ConnectionManager.getConnection();

			System.out.println("id after in updateBathing func = "+idAfter);
			model.setHairDryer(hairDryerRadioBtn.isSelected());
			model.setToiletPaper(toiletPaperRadioBtn.isSelected());
			
			String updateBathingRecord = "update Bathing set hairDryer=?, toiletPaper=? "
										+ "where bathing_id=?";
			
			PreparedStatement updatingBathingValues= connection.prepareStatement(updateBathingRecord);
			updatingBathingValues.setBoolean(1, model.getHairDryer());
			updatingBathingValues.setBoolean(2, model.getToiletPaper());
			updatingBathingValues.setInt(3, idAfter);
			updatingBathingValues.executeUpdate();
			System.out.println(updatingBathingValues.toString());
			

			String updateBathingIdInFacilities = "update Facilities set bathing_id=? where facilities_id=?";
			
			PreparedStatement updatingBathingIdInFacilities = connection.prepareStatement(updateBathingIdInFacilities);
			updatingBathingIdInFacilities.setInt(1, idAfter);
			updatingBathingIdInFacilities.setInt(2, facilitiesidAfter);

			updatingBathingIdInFacilities.executeUpdate();
			System.out.println(updatingBathingIdInFacilities.toString());		
			
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
	
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW