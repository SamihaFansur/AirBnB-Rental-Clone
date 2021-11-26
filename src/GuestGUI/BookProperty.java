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
	 private JButton addOutdoors;
	 private JRadioButton freeOnSiteParkingRadioButton;
	 private JRadioButton paidCarParkRadioButton;
	 private JRadioButton onRoadParkingRadioButton;
	 private JRadioButton patioRadioButton;
	 private JRadioButton barbequeRadioButton;
	 private int idAfter;
	private int facilitiesidAfter;
	 
	 private boolean freeOnSiteParking, onRoadParking, paidCarPark, patio, barbeque;
		
	 
	 Connection connection = null;
	 private JTextField shortNameTextField;
	 private JTextField guestCapacityTextField;
	 private JTextField textField_2;
	 
	 public BookProperty(MainModule mainModule, Controller controller, Model model) {
		//initializeBookProperty();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeBookProperty(int facilitiesId, int id) {
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
		facilitiesidAfter = facilitiesId;
		System.out.println("FACILITY ID FOR WHICH AM CREATING OUTDOORS RN = "+facilitiesidAfter);
		System.out.println("id after in init edit OUTDOORS func = "+idAfter);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(31, 58, 91, 23);
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
		bookPropertyPanel.add(backButton);
		
		JLabel bookPropertyTitleLabel = new JLabel("Property");
		bookPropertyTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		bookPropertyTitleLabel.setBounds(241, 39, 196, 55);
		bookPropertyPanel.add(bookPropertyTitleLabel);
		
		JLabel shortNamelabel = new JLabel("Shortname:");
		shortNamelabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		shortNamelabel.setBounds(31, 117, 112, 35);
		bookPropertyPanel.add(shortNamelabel);
		
		JLabel guestCapacityLabel = new JLabel("Guest Capacity:");
		guestCapacityLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		guestCapacityLabel.setBounds(31, 163, 112, 35);
		bookPropertyPanel.add(guestCapacityLabel);
		
		JLabel descriptionLabel = new JLabel("Description:");
		descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		descriptionLabel.setBounds(31, 208, 112, 35);
		bookPropertyPanel.add(descriptionLabel);
		
		JLabel lblFacilities = new JLabel("Facilities:");
		lblFacilities.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFacilities.setBounds(31, 323, 112, 35);
		bookPropertyPanel.add(lblFacilities);
		
		JLabel lblKitchen = new JLabel("Kitchen:");
		lblKitchen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblKitchen.setBounds(31, 369, 112, 35);
		bookPropertyPanel.add(lblKitchen);
		
		JLabel lblSleeping = new JLabel("Sleeping:");
		lblSleeping.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSleeping.setBounds(31, 415, 112, 35);
		bookPropertyPanel.add(lblSleeping);
		
		JLabel lblLiving = new JLabel("Living:");
		lblLiving.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLiving.setBounds(31, 507, 112, 35);
		bookPropertyPanel.add(lblLiving);
		
		JLabel lblBathing = new JLabel("Bathing:");
		lblBathing.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBathing.setBounds(31, 461, 112, 35);
		bookPropertyPanel.add(lblBathing);
		
		JLabel lblUtility = new JLabel("Utility:");
		lblUtility.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUtility.setBounds(31, 553, 112, 35);
		bookPropertyPanel.add(lblUtility);
		
		JLabel lblOutdoors = new JLabel("Outdoors:");
		lblOutdoors.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOutdoors.setBounds(31, 600, 112, 35);
		bookPropertyPanel.add(lblOutdoors);
		
		shortNameTextField = new JTextField();
		shortNameTextField.setBounds(166, 123, 360, 29);
		bookPropertyPanel.add(shortNameTextField);
		shortNameTextField.setColumns(10);
		
		guestCapacityTextField = new JTextField();
		guestCapacityTextField.setColumns(10);
		guestCapacityTextField.setBounds(166, 163, 131, 29);
		bookPropertyPanel.add(guestCapacityTextField);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(166, 208, 360, 93);
		bookPropertyPanel.add(textField_2);
		
		JButton kitchenButton = new JButton("Kitchen");
		kitchenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		kitchenButton.setBounds(241, 371, 155, 29);
		bookPropertyPanel.add(kitchenButton);
		
		JButton sleepingButton = new JButton("Sleeping");
		sleepingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sleepingButton.setBounds(241, 415, 155, 29);
		bookPropertyPanel.add(sleepingButton);
		
		JButton bathingButton = new JButton("Bathing");
		bathingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bathingButton.setBounds(241, 461, 155, 29);
		bookPropertyPanel.add(bathingButton);
		
		JButton livingButton = new JButton("Living");
		livingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		livingButton.setBounds(241, 507, 155, 29);
		bookPropertyPanel.add(livingButton);
		
		JButton UtilityButton = new JButton("Utility");
		UtilityButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		UtilityButton.setBounds(241, 553, 155, 29);
		bookPropertyPanel.add(UtilityButton);
		
		JButton OutdoorsButton = new JButton("Outdoors");
		OutdoorsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		OutdoorsButton.setBounds(241, 600, 155, 29);
		bookPropertyPanel.add(OutdoorsButton);

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}