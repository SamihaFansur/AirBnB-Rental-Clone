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

public class EditSleeping extends JFrame{


	private JFrame frame;
	private NavHost navForHost = new NavHost();
	private JRadioButton towelsRadioBtn;
	private JRadioButton bedLinenRadioBtn;

	private int idAfter;
	
	private boolean bedLinen, towels;
	
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
	 public EditSleeping(MainModule mainModule, Controller controller, Model model) {
		//initializeEditSleeping();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditSleeping(int id) {
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}

		System.out.println("sleeping record id in edit sleepingfacility page = "+id);
		idAfter = id;
		System.out.println("id after in init edit sleepingfunc = "+idAfter);
		
		JPanel editSleepingPanel = new JPanel();
		editSleepingPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(editSleepingPanel, BorderLayout.CENTER);
		editSleepingPanel.setLayout(null);

		JLabel editSleepingLabel = new JLabel("Add Sleeping Facility");
		editSleepingLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editSleepingLabel.setBounds(197, 50, 249, 57);
		editSleepingPanel.add(editSleepingLabel);
		
		try {
			connection = ConnectionManager.getConnection();

			System.out.println("id in try block where im tryna get values from db = "+id);
			
			String selectSleepingRecord = "select bedLinen, towels from Sleeping "
										+ "where sleeping_id=?";
			
			PreparedStatement selectingSleepingValues= connection.prepareStatement(selectSleepingRecord);
			
			selectingSleepingValues.setInt(1, id);
			ResultSet rs = selectingSleepingValues.executeQuery();
			while (rs.next()) {
				bedLinen = rs.getBoolean("bedLinen");
                towels = rs.getBoolean("towels");
            }		
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		
		JLabel bedLinenLabel = new JLabel("Bed Linen");
		bedLinenLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bedLinenLabel.setBounds(170, 167, 167, 34);
		editSleepingPanel.add(bedLinenLabel);

		bedLinenRadioBtn = new JRadioButton("Bed Linen", bedLinen);
		bedLinenRadioBtn.setBounds(398, 177, 21, 23);
		editSleepingPanel.add(bedLinenRadioBtn);
		
		JLabel towelsLabel = new JLabel("Towels");
		towelsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		towelsLabel.setBounds(170, 265, 167, 34);
		editSleepingPanel.add(towelsLabel);
				
		towelsRadioBtn = new JRadioButton("Towels", towels);
		towelsRadioBtn.setBounds(398, 264, 21, 23);
		editSleepingPanel.add(towelsRadioBtn);
		
		JButton addBedroomButton = new JButton("Add Bedrooms");
		addBedroomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("printing id in add bedroom btn before calling updateSleepingDetails func = "+id);
				System.out.println("printing idAfter in add bedroom btn before calling updateSleepingDetails func = "+idAfter);
				updateSleepingDetails(id);
				mainModule.editPropertyState= EDITPROPERTY.EDIT_BEDROOM;
				MainModule.controller.editPropertyView(id);
			}
		});
		addBedroomButton.setBounds(197, 405, 209, 46);
		editSleepingPanel.add(addBedroomButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(27, 69, 91, 23);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Homepage sp = new Homepage();

			  //  mainModule.currentState=STATE.EDIT_PROPERTY;
				mainModule.userState=USER.HOST;
				mainModule.editPropertyState = EDITPROPERTY.ADD_FACILITY;
				MainModule.controller.editPropertyView(1);
//				close();
				frame.dispose();
				
			}
		});	
		editSleepingPanel.add(backButton);
		
		
		
		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void updateSleepingDetails(int id) {
		System.out.println("Printing id fed into updateSleepingDetails = "+id);
		try {
			connection = ConnectionManager.getConnection();

			System.out.println("id after in updateSleeping func = "+idAfter);
			model.setBedLinen(bedLinenRadioBtn.isSelected());
			model.setTowels(towelsRadioBtn.isSelected());
			
			String updateSleepingRecord = "update Sleeping set bedLinen=?, towels=? "
										+ "where sleeping_id=?";
			
			PreparedStatement updatingSleepingValues= connection.prepareStatement(updateSleepingRecord);
			updatingSleepingValues.setBoolean(1, model.getBedLinen());
			updatingSleepingValues.setBoolean(2, model.getTowels());
			updatingSleepingValues.setInt(3, idAfter);
			updatingSleepingValues.executeUpdate();
			System.out.println(updatingSleepingValues.toString());
			
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW