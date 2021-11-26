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

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}