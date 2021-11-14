
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

public class EditKitchen extends JFrame{


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
	private JRadioButton refrigeratorRadioBtn;
	private JRadioButton ovenRadioBtn;
	private JRadioButton microwaveRadioBtn;
	private JRadioButton stoveRadioBtn;
	private JRadioButton dishwasherRadioBtn;
	private JRadioButton basicProvisionsRadioBtn;
	private JRadioButton tablewareRadioBtn;
	private JRadioButton cookwareRadioBtn;
	private JButton addKitchen;
	private int idAfter;
	Connection connection = null;
	 
	 
	 public EditKitchen(MainModule mainModule, Controller controller, Model model) {
		//initializeHomePage();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditKitchen(int id) {
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}

		System.out.println("kitchen record id in edit kitchen facility page = "+id);
		idAfter = id;
		System.out.println("id after in init edit kitchen func = "+idAfter);
		
		JPanel registerPanel = new JPanel();
		registerPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(registerPanel, BorderLayout.CENTER);
		registerPanel.setLayout(null);

		JLabel editKitchenLabel = new JLabel("Add Kitchen facility");
		editKitchenLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editKitchenLabel.setBounds(248, 47, 183, 57);
		registerPanel.add(editKitchenLabel);
		
		JLabel refrigeratorLabel = new JLabel("Refrigerator");
		refrigeratorLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		refrigeratorLabel.setBounds(170, 135, 167, 34);
		registerPanel.add(refrigeratorLabel);
		
		refrigeratorRadioBtn = new JRadioButton("Refrigerator", false);
		refrigeratorRadioBtn.setBounds(364, 146, 21, 23);
		registerPanel.add(refrigeratorRadioBtn);
		
		JLabel microwaveLabel = new JLabel("Microwave");
		microwaveLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		microwaveLabel.setBounds(170, 191, 167, 34);
		registerPanel.add(microwaveLabel);

		microwaveRadioBtn = new JRadioButton("Microwave", false);
		microwaveRadioBtn.setBounds(364, 199, 21, 23);
		registerPanel.add(microwaveRadioBtn);
		
		JLabel ovenLabel = new JLabel("Oven");
		ovenLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ovenLabel.setBounds(170, 254, 167, 34);
		registerPanel.add(ovenLabel);
		
		ovenRadioBtn = new JRadioButton("Oven", false);
		ovenRadioBtn.setBounds(364, 262, 21, 23);
		registerPanel.add(ovenRadioBtn);
		
		JLabel stoveLabel = new JLabel("Stove");
		stoveLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		stoveLabel.setBounds(170, 310, 167, 34);
		registerPanel.add(stoveLabel);

		stoveRadioBtn = new JRadioButton("Stove", false);
		stoveRadioBtn.setBounds(364, 310, 21, 23);
		registerPanel.add(stoveRadioBtn);
		
		JLabel dishwasherLabel = new JLabel("Dishwasher");
		dishwasherLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dishwasherLabel.setBounds(170, 369, 167, 34);
		registerPanel.add(dishwasherLabel);

		dishwasherRadioBtn = new JRadioButton("Dishwasher", false);
		dishwasherRadioBtn.setBounds(364, 380, 21, 23);
		registerPanel.add(dishwasherRadioBtn);
		
		JLabel tablewareLabel = new JLabel("Tableware");
		tablewareLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tablewareLabel.setBounds(170, 424, 167, 34);
		registerPanel.add(tablewareLabel);

		tablewareRadioBtn = new JRadioButton("Tableware", false);
		tablewareRadioBtn.setBounds(364, 435, 21, 23);
		registerPanel.add(tablewareRadioBtn);
		
		JLabel CookwareLabel = new JLabel("Cookware");
		CookwareLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		CookwareLabel.setBounds(170, 480, 167, 34);
		registerPanel.add(CookwareLabel);

		cookwareRadioBtn = new JRadioButton("Cookware", false);
		cookwareRadioBtn.setBounds(364, 488, 21, 23);
		registerPanel.add(cookwareRadioBtn);
		
		JLabel lblBasicProvisions = new JLabel("Basic Provisions");
		lblBasicProvisions.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBasicProvisions.setBounds(170, 543, 167, 34);
		registerPanel.add(lblBasicProvisions);

		basicProvisionsRadioBtn = new JRadioButton("Basic Provisions", false);
		basicProvisionsRadioBtn.setBounds(364, 551, 21, 23);
		registerPanel.add(basicProvisionsRadioBtn);
		
		addKitchen= new JButton("Save");
		addKitchen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateKitchenDetails();
			}
		});
		addKitchen.setBounds(275, 500, 91, 23);
		registerPanel.add(addKitchen);

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void updateKitchenDetails() {
		try {
			connection = ConnectionManager.getConnection();

			System.out.println("id after in updateKitchen func = "+idAfter);
			model.setRefrigerator(refrigeratorRadioBtn.isSelected());
			model.setMicrowave(microwaveRadioBtn.isSelected());
			model.setOven(ovenRadioBtn.isSelected());
			model.setStove(stoveRadioBtn.isSelected());
			model.setDishwasher(dishwasherRadioBtn.isSelected());
			model.setTableware(tablewareRadioBtn.isSelected());
			model.setCookware(cookwareRadioBtn.isSelected());
			model.setBasicProvisions(basicProvisionsRadioBtn.isSelected());
			
			String updateKitchenRecord = "update Kitchen set refrigerator=?, microwave=?, "
										+ "oven=?, stove=?, dishwasher=?, tableware=?, "
										+ "cookware=?, basicProvision=?  "
										+ "where kitchen_id=?";
			
			PreparedStatement updatingKitchenValues= connection.prepareStatement(updateKitchenRecord);
			updatingKitchenValues.setBoolean(1, model.getRefrigerator());
			updatingKitchenValues.setBoolean(2, model.getMicrowave());
			updatingKitchenValues.setBoolean(3, model.getOven());
			updatingKitchenValues.setBoolean(4, model.getStove());
			updatingKitchenValues.setBoolean(5, model.getDishwasher());
			updatingKitchenValues.setBoolean(6, model.getTableware());
			updatingKitchenValues.setBoolean(7, model.getCookware());
			updatingKitchenValues.setBoolean(8, model.getBasicProvisions());
			updatingKitchenValues.setInt(9, idAfter);
			updatingKitchenValues.executeUpdate();
			System.out.println(updatingKitchenValues.toString());
			
//			String insertKitchenQuery = "insert into Kitchen (refrigerator, microwave, oven, "
//										+ "stove, dishwasher, tableware, cookware, basicProvision)"
//										+ " values(?,?,?,?,?,?,?,?) ";
//			PreparedStatement ps_kitchen = connection.prepareStatement(insertKitchenQuery);
//			
//			ps_kitchen.setBoolean(1, model.getRefrigerator());
//			ps_kitchen.setBoolean(2, model.getMicrowave());
//			ps_kitchen.setBoolean(3, model.getOven());
//			ps_kitchen.setBoolean(4, model.getStove());
//			ps_kitchen.setBoolean(5, model.getDishwasher());
//			ps_kitchen.setBoolean(6, model.getTableware());
//			ps_kitchen.setBoolean(7, model.getCookware());
//			ps_kitchen.setBoolean(8, model.getBasicProvisions());
//
//			System.out.println(ps_kitchen);
//			ps_kitchen.executeUpdate();
			
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
	
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW