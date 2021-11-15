
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
import java.sql.ResultSet;
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
	 private int idAfter;
	 
	 private boolean freeOnSiteParking, onRoadParking, paidCarPark, patio, barbeque;
		
	 
	 Connection connection = null;
	 
	 public EditOutdoors(MainModule mainModule, Controller controller, Model model) {
		//initializeEditOutdoors();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditOutdoors(int id) {
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println("Outdoors record id in edit Outdoors facility page = "+id);
		idAfter = id;
		System.out.println("id after in init edit Outdoors func = "+idAfter);
		
		JPanel editOutdoorsPanel = new JPanel();
		editOutdoorsPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(editOutdoorsPanel, BorderLayout.CENTER);
		editOutdoorsPanel.setLayout(null);

		JLabel editOutdoorsLabel = new JLabel("Add Outdoor Facility");
		editOutdoorsLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editOutdoorsLabel.setBounds(173, 64, 229, 57);
		editOutdoorsPanel.add(editOutdoorsLabel);
		
		try {
			connection = ConnectionManager.getConnection();

			String selectOutdoorsRecord = "select freeOnSiteParking, onRoadParking, paidCarPark, patio, "
										+ "barbeque from Outdoors where outdoors_id=?";
			
			PreparedStatement selectingOutdoorsValues= connection.prepareStatement(selectOutdoorsRecord);
			
			selectingOutdoorsValues.setInt(1, id);
			ResultSet rs = selectingOutdoorsValues.executeQuery();
			
			while (rs.next()) {
				freeOnSiteParking = rs.getBoolean("freeOnSiteParking");
                onRoadParking = rs.getBoolean("onRoadParking");
                paidCarPark= rs.getBoolean("paidCarPark");
                patio = rs.getBoolean("patio");
                barbeque = rs.getBoolean("barbeque");
            }		
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		
		JLabel freeOnSiteParkingLabel = new JLabel("Free On Site Parking");
		freeOnSiteParkingLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		freeOnSiteParkingLabel.setBounds(159, 179, 167, 34);
		editOutdoorsPanel.add(freeOnSiteParkingLabel);
		
		freeOnSiteParkingRadioButton = new JRadioButton("Free on site parking", freeOnSiteParking);
		freeOnSiteParkingRadioButton.setBounds(394, 193, 21, 23);
		editOutdoorsPanel.add(freeOnSiteParkingRadioButton);
		
		JLabel onRoadParkingLabel = new JLabel("On Road Parking");
		onRoadParkingLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		onRoadParkingLabel.setBounds(159, 235, 167, 34);
		editOutdoorsPanel.add(onRoadParkingLabel);
		
		onRoadParkingRadioButton = new JRadioButton("On road parking", onRoadParking);
		onRoadParkingRadioButton.setBounds(394, 246, 21, 23);
		editOutdoorsPanel.add(onRoadParkingRadioButton);
		
		
		JLabel paidCarParkLabel = new JLabel("Paid Car Parking");
		paidCarParkLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		paidCarParkLabel.setBounds(159, 298, 167, 34);
		editOutdoorsPanel.add(paidCarParkLabel);
		
		paidCarParkRadioButton = new JRadioButton("Paid Car Parking", paidCarPark);
		paidCarParkRadioButton.setBounds(394, 309, 21, 23);
		editOutdoorsPanel.add(paidCarParkRadioButton);
		
		JLabel patioLabel = new JLabel("Patio");
		patioLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		patioLabel.setBounds(159, 368, 167, 34);
		editOutdoorsPanel.add(patioLabel);
		
		patioRadioButton = new JRadioButton("Patio", patio);
		patioRadioButton.setBounds(394, 379, 21, 23);
		editOutdoorsPanel.add(patioRadioButton);
		
		JLabel barbequeLabel = new JLabel("Barbeque");
		barbequeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		barbequeLabel.setBounds(159, 431, 167, 34);
		editOutdoorsPanel.add(barbequeLabel);
		
		barbequeRadioButton = new JRadioButton("Barbeque", barbeque);
		barbequeRadioButton.setBounds(394, 442, 21, 23);
		editOutdoorsPanel.add(barbequeRadioButton);
		
		addOutdoors = new JButton("Save");
		addOutdoors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateOutdoorsDetails();
			}
		});
		addOutdoors.setBounds(250, 510, 91, 23);
		editOutdoorsPanel.add(addOutdoors);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(21, 83, 91, 23);
		editOutdoorsPanel.add(backButton);

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void updateOutdoorsDetails() {
		try {
			connection = ConnectionManager.getConnection();

			System.out.println("id after in updateOutdoors func = "+idAfter);
			model.setFreeOnSiteParking(freeOnSiteParkingRadioButton.isSelected());
			model.setOnRoadParking(onRoadParkingRadioButton.isSelected());
			model.setPaidCarPark(paidCarParkRadioButton.isSelected());
			model.setPatio(patioRadioButton.isSelected());
			model.setBarbeque(barbequeRadioButton.isSelected());
			
			String updateOutdoorsRecord = "update Outdoors set freeOnSiteParking=?, onRoadParking=?, "
											+ "paidCarPark=?, patio=?, barbeque=? "
											+ "where outdoors_id=?";
			
			PreparedStatement updatingOutdoorsValues = connection.prepareStatement(updateOutdoorsRecord);
			
			updatingOutdoorsValues.setBoolean(1, model.getFreeOnSiteParking());
			updatingOutdoorsValues.setBoolean(2, model.getOnRoadParking());
			updatingOutdoorsValues.setBoolean(3, model.getPaidCarPark());
			updatingOutdoorsValues.setBoolean(4, model.getPatio());
			updatingOutdoorsValues.setBoolean(5, model.getBarbeque());
			updatingOutdoorsValues.setInt(6, idAfter);
			
			System.out.println(updatingOutdoorsValues);
			updatingOutdoorsValues.executeUpdate();
			
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW