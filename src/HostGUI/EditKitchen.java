
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
	private int facilitiesidAfter;
	
	private boolean refrigerator, microwave, oven, stove, dishwasher, tableware, cookware, basicProvision;
	
	
	Connection connection = null;
	 
	 
	 public EditKitchen(MainModule mainModule, Controller controller, Model model) {
		//initializeEditKitchen();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditKitchen(int facilitiesId, int id) {
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}

		idAfter = id;
		facilitiesidAfter = facilitiesId;
		System.out.println("FACILITY ID FOR WHICH AM CREATING KITCHEN RN = "+facilitiesidAfter);
		System.out.println("id after in init edit kitchen func = "+idAfter);
		
		JPanel editKitchenPanel = new JPanel();
		editKitchenPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(editKitchenPanel, BorderLayout.CENTER);
		editKitchenPanel.setLayout(null);

		JLabel editKitchenLabel = new JLabel("Add Kitchen facility");
		editKitchenLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editKitchenLabel.setBounds(186, 48, 261, 57);
		editKitchenPanel.add(editKitchenLabel);
		
		try {
			connection = ConnectionManager.getConnection();

			System.out.println("id in try block where im tryna get values from db = "+id);
			
			String selectKitchenRecord = "select refrigerator, microwave, "
										+ "oven, stove, dishwasher, tableware, "
										+ "cookware, basicProvision from Kitchen "
										+ "where kitchen_id=?";
			
			PreparedStatement selectingKitchenValues= connection.prepareStatement(selectKitchenRecord);
			
			selectingKitchenValues.setInt(1, id);
			ResultSet rs = selectingKitchenValues.executeQuery();
			
			while (rs.next()) {
				refrigerator = rs.getBoolean("refrigerator");
                microwave = rs.getBoolean("microwave");
                oven = rs.getBoolean("oven");
                stove = rs.getBoolean("stove");
                dishwasher = rs.getBoolean("dishwasher");
                tableware = rs.getBoolean("tableware");
                cookware = rs.getBoolean("cookware");
                basicProvision = rs.getBoolean("basicProvision");
            }		
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
				
		JLabel refrigeratorLabel = new JLabel("Refrigerator");
		refrigeratorLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		refrigeratorLabel.setBounds(170, 135, 167, 34);
		editKitchenPanel.add(refrigeratorLabel);
		
		refrigeratorRadioBtn = new JRadioButton("Refrigerator", refrigerator);
		refrigeratorRadioBtn.setBounds(387, 147, 21, 23);
		editKitchenPanel.add(refrigeratorRadioBtn);
		
		JLabel microwaveLabel = new JLabel("Microwave");
		microwaveLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		microwaveLabel.setBounds(170, 191, 167, 34);
		editKitchenPanel.add(microwaveLabel);

		microwaveRadioBtn = new JRadioButton("Microwave", microwave);
		microwaveRadioBtn.setBounds(387, 200, 21, 23);
		editKitchenPanel.add(microwaveRadioBtn);
		
		JLabel ovenLabel = new JLabel("Oven");
		ovenLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ovenLabel.setBounds(170, 254, 167, 34);
		editKitchenPanel.add(ovenLabel);
		
		ovenRadioBtn = new JRadioButton("Oven", oven);
		ovenRadioBtn.setBounds(387, 263, 21, 23);
		editKitchenPanel.add(ovenRadioBtn);
		
		JLabel stoveLabel = new JLabel("Stove");
		stoveLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		stoveLabel.setBounds(170, 310, 167, 34);
		editKitchenPanel.add(stoveLabel);

		stoveRadioBtn = new JRadioButton("Stove", stove);
		stoveRadioBtn.setBounds(387, 311, 21, 23);
		editKitchenPanel.add(stoveRadioBtn);
		
		JLabel dishwasherLabel = new JLabel("Dishwasher");
		dishwasherLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dishwasherLabel.setBounds(170, 369, 167, 34);
		editKitchenPanel.add(dishwasherLabel);

		dishwasherRadioBtn = new JRadioButton("Dishwasher", dishwasher);
		dishwasherRadioBtn.setBounds(387, 381, 21, 23);
		editKitchenPanel.add(dishwasherRadioBtn);
		
		JLabel tablewareLabel = new JLabel("Tableware");
		tablewareLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tablewareLabel.setBounds(170, 424, 167, 34);
		editKitchenPanel.add(tablewareLabel);

		tablewareRadioBtn = new JRadioButton("Tableware", tableware);
		tablewareRadioBtn.setBounds(387, 436, 21, 23);
		editKitchenPanel.add(tablewareRadioBtn);
		
		JLabel CookwareLabel = new JLabel("Cookware");
		CookwareLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		CookwareLabel.setBounds(170, 480, 167, 34);
		editKitchenPanel.add(CookwareLabel);

		cookwareRadioBtn = new JRadioButton("Cookware", cookware);
		cookwareRadioBtn.setBounds(387, 489, 21, 23);
		editKitchenPanel.add(cookwareRadioBtn);
		
		JLabel lblBasicProvisions = new JLabel("Basic Provisions");
		lblBasicProvisions.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBasicProvisions.setBounds(170, 543, 167, 34);
		editKitchenPanel.add(lblBasicProvisions);

		basicProvisionsRadioBtn = new JRadioButton("Basic Provisions", basicProvision);
		basicProvisionsRadioBtn.setBounds(387, 552, 21, 23);
		editKitchenPanel.add(basicProvisionsRadioBtn);
		
		addKitchen= new JButton("Save");
		addKitchen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateKitchenDetails();
			}
		});
		addKitchen.setBounds(248, 602, 91, 23);
		editKitchenPanel.add(addKitchen);
		
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
		editKitchenPanel.add(backButton);

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void updateKitchenDetails() {
		try {
			connection = ConnectionManager.getConnection();

			System.out.println("id after in updateKitchen func = "+idAfter);
			System.out.println("facilities id after in updateKitchen func = "+facilitiesidAfter);
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
			
			String updateKitchenIdInFacilities = "update Facilities set kitchen_id=? where facilities_id=?";
			
			PreparedStatement updatingKitchenIdInFacilities = connection.prepareStatement(updateKitchenIdInFacilities);
			updatingKitchenIdInFacilities.setInt(1, idAfter);
			updatingKitchenIdInFacilities.setInt(2, facilitiesidAfter);

			updatingKitchenIdInFacilities.executeUpdate();
			System.out.println(updatingKitchenIdInFacilities.toString());		
			
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
	
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW