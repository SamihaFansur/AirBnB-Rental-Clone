
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

public class EditUtility extends JFrame{


	private JFrame frame;
	private NavHost navForHost = new NavHost();

	public void close() {
		frame.dispose();
	}

	/**
	 * Create the application.
	 */

	 private Controller controller;
	 private Model model;
	 private MainModule mainModule;
	 private JRadioButton heatingRadioBtn; 
	 private JRadioButton washingMachineRadioBtn;
	 private JRadioButton fireExtinguisherRadioBtn;
	 private JRadioButton dryingMachineRadioBtn;
	 private JRadioButton smokeAlarmRadioBtn;
	 private JRadioButton firstAidKitRadioBtn;
	 private JButton addUtility;
	 private int idAfter;
	 
	 private boolean heating, washingMachine, dryingMachine, fireExtinguisher, smokeAlarm, firstAidKit;
	 
	Connection connection = null;

	 public EditUtility(MainModule mainModule, Controller controller, Model model) {
		//initializeEditUtility();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditUtility(int id) {
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}

		System.out.println("utility record id in edit utility facility page = "+id);
		idAfter = id;
		System.out.println("id after in init edit utility func = "+idAfter);
		
		
		JPanel editUtilityPanel = new JPanel();
		editUtilityPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(editUtilityPanel, BorderLayout.CENTER);
		editUtilityPanel.setLayout(null);

		JLabel utilityLabel = new JLabel("Add Utilities");
		utilityLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		utilityLabel.setBounds(248, 47, 183, 57);
		editUtilityPanel.add(utilityLabel);
		
		try {
			connection = ConnectionManager.getConnection();
			
			String selectUtilityRecord = "select heating, washingMachine, dryingMachine, fireExtinguisher, "
										+ "smokeAlarm, firstAidKit from Utility where utility_id=?";
			
			PreparedStatement selectingUtilityValues= connection.prepareStatement(selectUtilityRecord);
			
			selectingUtilityValues.setInt(1, id);
			ResultSet rs = selectingUtilityValues.executeQuery();
			
			while (rs.next()) {
				heating = rs.getBoolean("heating");
                washingMachine = rs.getBoolean("washingMachine");
                dryingMachine = rs.getBoolean("dryingMachine");
                fireExtinguisher = rs.getBoolean("fireExtinguisher");
                smokeAlarm = rs.getBoolean("smokeAlarm");
                firstAidKit = rs.getBoolean("firstAidKit");
            }		
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		
		JLabel heatingLabel = new JLabel("Heating");
		heatingLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		heatingLabel.setBounds(146, 135, 167, 34);
		editUtilityPanel.add(heatingLabel);
		
		heatingRadioBtn = new JRadioButton("Heating", heating);
		heatingRadioBtn.setBounds(395, 146, 21, 23);
		editUtilityPanel.add(heatingRadioBtn);
		
		JLabel washingMachineLabel = new JLabel("Washing Machine");
		washingMachineLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		washingMachineLabel.setBounds(146, 188, 167, 34);
		editUtilityPanel.add(washingMachineLabel);

		washingMachineRadioBtn = new JRadioButton("Washing machine", washingMachine);
		washingMachineRadioBtn.setBounds(395, 199, 21, 23);
		editUtilityPanel.add(washingMachineRadioBtn);
		
		JLabel fireExtinguisherLabel = new JLabel("Fire Extinguisher");
		fireExtinguisherLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fireExtinguisherLabel.setBounds(146, 254, 167, 34);
		editUtilityPanel.add(fireExtinguisherLabel);
		
		fireExtinguisherRadioBtn = new JRadioButton("fire extinguisher", fireExtinguisher);
		fireExtinguisherRadioBtn.setBounds(395, 262, 21, 23);
		editUtilityPanel.add(fireExtinguisherRadioBtn);
		
		JLabel dryingMachineLabel = new JLabel("Drying Machine");
		dryingMachineLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dryingMachineLabel.setBounds(146, 310, 167, 34);
		editUtilityPanel.add(dryingMachineLabel);

		dryingMachineRadioBtn = new JRadioButton("Drying machine", dryingMachine);
		dryingMachineRadioBtn.setBounds(395, 310, 21, 23);
		editUtilityPanel.add(dryingMachineRadioBtn);
		
		JLabel smokeAlarmLabel = new JLabel("Smoke Alarm");
		smokeAlarmLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		smokeAlarmLabel.setBounds(146, 369, 167, 34);
		editUtilityPanel.add(smokeAlarmLabel);
		
		smokeAlarmRadioBtn = new JRadioButton("Smoke alarm", smokeAlarm);
		smokeAlarmRadioBtn.setBounds(395, 380, 21, 23);
		editUtilityPanel.add(smokeAlarmRadioBtn);				
		
		JLabel firstAidKitLabel = new JLabel("First Aid Kit");
		firstAidKitLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		firstAidKitLabel.setBounds(146, 424, 167, 34);
		editUtilityPanel.add(firstAidKitLabel);
		
		firstAidKitRadioBtn = new JRadioButton("First aid kit", firstAidKit);
		firstAidKitRadioBtn.setBounds(395, 435, 21, 23);
		editUtilityPanel.add(firstAidKitRadioBtn);
		
		addUtility = new JButton("Save");
		addUtility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateUtilityDetails();
			}
		});
		addUtility.setBounds(248, 500, 91, 23);
		editUtilityPanel.add(addUtility);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(29, 66, 91, 23);
		editUtilityPanel.add(backButton);
		

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void updateUtilityDetails() {
		try {
			
			connection = ConnectionManager.getConnection();

			System.out.println("id after in updateUtility func = "+idAfter);
			model.setHeating(heatingRadioBtn.isSelected());			
			model.setWashingMachine(washingMachineRadioBtn.isSelected());			
			model.setFireExtinguisher(fireExtinguisherRadioBtn.isSelected());			
			model.setDryingMachine(dryingMachineRadioBtn.isSelected());			
			model.setSmokeAlarm(smokeAlarmRadioBtn.isSelected());
			model.setFirstAidKit(firstAidKitRadioBtn.isSelected());
			
			
			String updateUtilityRecord = "update Utility set heating=?, washingMachine=?, "
					+ "dryingMachine=?, fireExtinguisher=?, smokeAlarm=?, firstAidKit=? "
					+ "where utility_id=?";
			
			PreparedStatement ps_utility = connection.prepareStatement(updateUtilityRecord);
			
			ps_utility.setBoolean(1, model.getHeating());
			ps_utility.setBoolean(2, model.getWashingMachine());
			ps_utility.setBoolean(3, model.getFireExtinguisher());
			ps_utility.setBoolean(4, model.getDryingMachine());
			ps_utility.setBoolean(5, model.getSmokeAlarm());
			ps_utility.setBoolean(6, model.getFirstAidKit());
			ps_utility.setInt(7, idAfter);

			System.out.println(ps_utility);
			ps_utility.executeUpdate();
			
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW