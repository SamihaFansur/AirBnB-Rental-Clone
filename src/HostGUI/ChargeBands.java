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

public class ChargeBands extends JFrame{

	private NavHost navForHost = new NavHost();
	private JFrame frame;
	private JTextField pricePerNightTextField;
	private JTextField serviceChargeTextField;
	private JTextField cleaningChargeTextField;
	private JButton addEditPropertyButton;
	private JComboBox titleComboBox = new JComboBox();
	 
	Connection connection = null;

	/**
	 * Create the application.
	 */

	 private Controller controller;
	 private Model model;
	 private MainModule mainModule;


	 
	 public ChargeBands(MainModule mainModule, Controller controller, Model model) {
//		initializeChargeBands();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeChargeBands() {
		System.out.println("IN CHARGE BANDS.JAVA");
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		JPanel chargeBandsPanel = new JPanel();
		chargeBandsPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(chargeBandsPanel, BorderLayout.CENTER);
		chargeBandsPanel.setLayout(null);

		JLabel chargeBandsLabel = new JLabel("Charge bands");
		chargeBandsLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		chargeBandsLabel.setBounds(222, 53, 183, 57);
		chargeBandsPanel.add(chargeBandsLabel);
				
		JLabel startDateLabel = new JLabel("Start Date");
		startDateLabel.setBounds(104, 236, 93, 34);
		chargeBandsPanel.add(startDateLabel);
		
		JLabel pricePerNightLabel = new JLabel("Price Per Night:");
		pricePerNightLabel.setBounds(104, 336, 93, 34);
		chargeBandsPanel.add(pricePerNightLabel);
		
		pricePerNightTextField = new JTextField();
		pricePerNightTextField.setColumns(10);
		pricePerNightTextField.setBounds(207, 336, 274, 34);
		chargeBandsPanel.add(pricePerNightTextField);
		
		JLabel serviceChargeLabel = new JLabel("Service Charge:");
		serviceChargeLabel.setBounds(104, 402, 93, 34);
		chargeBandsPanel.add(serviceChargeLabel);
		
		serviceChargeTextField = new JTextField();
		serviceChargeTextField.setColumns(10);
		serviceChargeTextField.setBounds(207, 402, 274, 34);
		chargeBandsPanel.add(serviceChargeTextField);
		
		JLabel cleaningChargeLabel = new JLabel("Cleaning Charge:");
		cleaningChargeLabel.setBounds(104, 456, 93, 34);
		chargeBandsPanel.add(cleaningChargeLabel);
		
		cleaningChargeTextField = new JTextField();
		cleaningChargeTextField.setColumns(10);
		cleaningChargeTextField.setBounds(207, 456, 274, 34);
		chargeBandsPanel.add(cleaningChargeTextField);
		
		addEditPropertyButton = new JButton("Save");
		addEditPropertyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEditAccountDetails();
			}
		});
		addEditPropertyButton.setBounds(245, 548, 91, 23);
		chargeBandsPanel.add(addEditPropertyButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(22, 75, 91, 23);
		chargeBandsPanel.add(backButton);
		
		JComboBox startDateDayComboBox = new JComboBox();
		startDateDayComboBox.setBounds(246, 242, 31, 22);
		chargeBandsPanel.add(startDateDayComboBox);
		
		JComboBox startDateMonthComboBox = new JComboBox();
		startDateMonthComboBox.setBounds(332, 242, 31, 22);
		chargeBandsPanel.add(startDateMonthComboBox);
		
		JComboBox startDateYearComboBox = new JComboBox();
		startDateYearComboBox.setBounds(426, 242, 31, 22);
		chargeBandsPanel.add(startDateYearComboBox);
		
		JLabel startDateDayLabel = new JLabel("Day:");
		startDateDayLabel.setBounds(207, 236, 93, 34);
		chargeBandsPanel.add(startDateDayLabel);
		
		JLabel startDateMonthLabel = new JLabel("Month:");
		startDateMonthLabel.setBounds(287, 236, 93, 34);
		chargeBandsPanel.add(startDateMonthLabel);
		
		JLabel startDateYearLabel = new JLabel("Year:");
		startDateYearLabel.setBounds(388, 236, 93, 34);
		chargeBandsPanel.add(startDateYearLabel);
		
		JLabel endDateLabel = new JLabel("Start Date");
		endDateLabel.setBounds(104, 281, 93, 34);
		chargeBandsPanel.add(endDateLabel);
		
		JLabel endDateDayLabel_1 = new JLabel("Day:");
		endDateDayLabel_1.setBounds(207, 281, 31, 34);
		chargeBandsPanel.add(endDateDayLabel_1);
		
		JComboBox endDateDayComboBox_1 = new JComboBox();
		endDateDayComboBox_1.setBounds(246, 287, 31, 22);
		chargeBandsPanel.add(endDateDayComboBox_1);
		
		JComboBox endDateMonthComboBox_1 = new JComboBox();
		endDateMonthComboBox_1.setBounds(332, 287, 31, 22);
		chargeBandsPanel.add(endDateMonthComboBox_1);
		
		JLabel endDateMonthLabel_1 = new JLabel("Month:");
		endDateMonthLabel_1.setBounds(287, 281, 49, 34);
		chargeBandsPanel.add(endDateMonthLabel_1);
		
		JComboBox endDateYearComboBox_1 = new JComboBox();
		endDateYearComboBox_1.setBounds(426, 287, 31, 22);
		chargeBandsPanel.add(endDateYearComboBox_1);
		
		JLabel endDateYearLabel_1 = new JLabel("Year:");
		endDateYearLabel_1.setBounds(388, 281, 28, 34);
		chargeBandsPanel.add(endDateYearLabel_1);

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void addEditAccountDetails() {
		
		}
	}


//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW