
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

public class EditBathroom extends JFrame{


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
	 private JRadioButton toiletRadioBtn;
	 private JRadioButton showerRadioBtn;
	 private JRadioButton bathRadioBtn;
	 private JRadioButton sharedBathroomRadioBtn;
	 private JButton addBathType;
	 
	Connection connection = null;
	
	 public EditBathroom(MainModule mainModule, Controller controller, Model model) {
		//initializeHomePage();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditBathroom() {
		
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

		JLabel editBathroomLabel = new JLabel("Add Bathroom facilities");
		editBathroomLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editBathroomLabel.setBounds(217, 55, 183, 57);
		registerPanel.add(editBathroomLabel);
		
		JLabel toiletLabel = new JLabel("Toilet");
		toiletLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toiletLabel.setBounds(170, 150, 167, 34);
		registerPanel.add(toiletLabel);

		toiletRadioBtn = new JRadioButton("Toilet", false);
		toiletRadioBtn.setBounds(364, 161, 21, 23);
		registerPanel.add(toiletRadioBtn);
		
		JLabel bathLabel = new JLabel("Bath");
		bathLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bathLabel.setBounds(170, 226, 167, 34);
		registerPanel.add(bathLabel);

		bathRadioBtn = new JRadioButton("Bath", false);
		bathRadioBtn.setBounds(364, 237, 21, 23);
		registerPanel.add(bathRadioBtn);
		
		JLabel showerLabel = new JLabel("Shower");
		showerLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		showerLabel.setBounds(170, 315, 167, 34);
		registerPanel.add(showerLabel);

		showerRadioBtn = new JRadioButton("Shower", false);
		showerRadioBtn.setBounds(364, 315, 21, 23);
		registerPanel.add(showerRadioBtn);
		
		JLabel sharedHostLabel = new JLabel("Shared Bathroom with host");
		sharedHostLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sharedHostLabel.setBounds(170, 392, 167, 34);
		registerPanel.add(sharedHostLabel);		
		
		sharedBathroomRadioBtn = new JRadioButton("Shared bathroom", false);
		sharedBathroomRadioBtn.setBounds(364, 403, 21, 23);
		registerPanel.add(sharedBathroomRadioBtn);


		addBathType = new JButton("Save");
		addBathType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBathTypeDetails();
			}
		});
		addBathType.setBounds(275, 500, 91, 23);
		registerPanel.add(addBathType);

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void addBathTypeDetails() {
		try {
			connection = ConnectionManager.getConnection();

			model.setToilet(toiletRadioBtn.isSelected());
			model.setBath(bathRadioBtn.isSelected());
			model.setShower(showerRadioBtn.isSelected());
			model.setShared(sharedBathroomRadioBtn.isSelected());
			
			String insertBathTypeQuery = "insert into BathType (toilet, bath, shower, shared)"
										+ " values(?,?,?,?)";
			PreparedStatement ps_bathType= connection.prepareStatement(insertBathTypeQuery);
			
			ps_bathType.setBoolean(1, model.getToilet());
			ps_bathType.setBoolean(2, model.getBath());
			ps_bathType.setBoolean(3, model.getShower());
			ps_bathType.setBoolean(4, model.getShared());

			System.out.println(ps_bathType);
			ps_bathType.executeUpdate();
			
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
	
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW