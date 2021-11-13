
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
	 
	Connection connection = null;

	 public EditUtility(MainModule mainModule, Controller controller, Model model) {
		//initializeHomePage();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditUtility() {
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

		JLabel utilityLabel = new JLabel("Add Utilities");
		utilityLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		utilityLabel.setBounds(248, 47, 183, 57);
		registerPanel.add(utilityLabel);
		
		JLabel heatingLabel = new JLabel("Heating");
		heatingLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		heatingLabel.setBounds(170, 135, 167, 34);
		registerPanel.add(heatingLabel);
		
		heatingRadioBtn = new JRadioButton("Heating", false);
		heatingRadioBtn.setBounds(364, 146, 21, 23);
		registerPanel.add(heatingRadioBtn);
		
		JLabel washingMachineLabel = new JLabel("Washing Machine");
		washingMachineLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		washingMachineLabel.setBounds(170, 188, 167, 34);
		registerPanel.add(washingMachineLabel);

		washingMachineRadioBtn = new JRadioButton("Washing machine", false);
		washingMachineRadioBtn.setBounds(364, 199, 21, 23);
		registerPanel.add(washingMachineRadioBtn);
		
		JLabel fireExtinguisherLabel = new JLabel("Fire Extinguisher");
		fireExtinguisherLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fireExtinguisherLabel.setBounds(170, 254, 167, 34);
		registerPanel.add(fireExtinguisherLabel);
		
		fireExtinguisherRadioBtn = new JRadioButton("fire extinguisher", false);
		fireExtinguisherRadioBtn.setBounds(364, 262, 21, 23);
		registerPanel.add(fireExtinguisherRadioBtn);
		
		JLabel dryingMachineLabel = new JLabel("Drying Machine");
		dryingMachineLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dryingMachineLabel.setBounds(170, 310, 167, 34);
		registerPanel.add(dryingMachineLabel);

		dryingMachineRadioBtn = new JRadioButton("Drying machine", false);
		dryingMachineRadioBtn.setBounds(364, 310, 21, 23);
		registerPanel.add(dryingMachineRadioBtn);
		
		JLabel smokeAlarmLabel = new JLabel("Smoke Alarm");
		smokeAlarmLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		smokeAlarmLabel.setBounds(170, 369, 167, 34);
		registerPanel.add(smokeAlarmLabel);
		
		smokeAlarmRadioBtn = new JRadioButton("Smoke alarm", false);
		smokeAlarmRadioBtn.setBounds(364, 380, 21, 23);
		registerPanel.add(smokeAlarmRadioBtn);				
		
		JLabel firstAidKitLabel = new JLabel("First Aid Kit");
		firstAidKitLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		firstAidKitLabel.setBounds(170, 424, 167, 34);
		registerPanel.add(firstAidKitLabel);
		
		firstAidKitRadioBtn = new JRadioButton("First aid kit", false);
		firstAidKitRadioBtn.setBounds(364, 435, 21, 23);
		registerPanel.add(firstAidKitRadioBtn);
		
		addUtility = new JButton("Save");
		addUtility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUtilityDetails();
			}
		});
		addUtility.setBounds(275, 500, 91, 23);
		registerPanel.add(addUtility);
		

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void addUtilityDetails() {
		try {
			connection = ConnectionManager.getConnection();

			model.setHeating(heatingRadioBtn.isSelected());
			System.out.println("heating = "+heatingRadioBtn.isSelected());
			model.setWashingMachine(washingMachineRadioBtn.isSelected());
			System.out.println("washing machine = "+washingMachineRadioBtn.isSelected());
			model.setFireExtinguisher(fireExtinguisherRadioBtn.isSelected());
			System.out.println("fire extinguisher = "+fireExtinguisherRadioBtn.isSelected());
			model.setDryingMachine(dryingMachineRadioBtn.isSelected());
			System.out.println("dryer = "+dryingMachineRadioBtn.isSelected());
			model.setSmokeAlarm(smokeAlarmRadioBtn.isSelected());
			System.out.println("smoke alarm= "+smokeAlarmRadioBtn.isSelected());
			model.setFirstAidKit(firstAidKitRadioBtn.isSelected());
			System.out.println("first aid= "+firstAidKitRadioBtn.isSelected());
			
			String insertUtilitiesQuery = "insert into Utility (heating, washingMachine, "
										+ "fireExtinguisher, dryingMaching, smokeAlarm, firstAidKit)"
										+ " values(?,?,?,?,?,?) ";
			PreparedStatement ps_utility = connection.prepareStatement(insertUtilitiesQuery);
			
			ps_utility.setBoolean(1, model.getHeating());
			ps_utility.setBoolean(2, model.getWashingMachine());
			ps_utility.setBoolean(3, model.getFireExtinguisher());
			ps_utility.setBoolean(4, model.getDryingMachine());
			ps_utility.setBoolean(5, model.getSmokeAlarm());
			ps_utility.setBoolean(6, model.getFirstAidKit());

			System.out.println(ps_utility);
			ps_utility.executeUpdate();
			
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW