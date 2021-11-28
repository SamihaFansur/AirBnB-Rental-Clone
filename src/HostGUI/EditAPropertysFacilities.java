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
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class EditAPropertysFacilities extends JFrame{


	private NavHost navForHost = new NavHost();
	private JFrame frame;
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
	 
	 public EditAPropertysFacilities(MainModule mainModule, Controller controller, Model model) {
		//initializeEditAPropertysFacilities();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditAPropertysFacilities(int facilityId, int id) {
		int facilityIdAfter = facilityId;
		
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
	
		System.out.println("ID OF FACILITIES BEING EDITED RN = "+facilityIdAfter);
		JPanel addFacilityPanel = new JPanel();
		addFacilityPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(addFacilityPanel, BorderLayout.CENTER);
		addFacilityPanel.setLayout(null);

		JLabel addFacilityLabel = new JLabel("Edit Facilities");
		addFacilityLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		addFacilityLabel.setBounds(222, 53, 183, 57);
		addFacilityPanel.add(addFacilityLabel);
		
		JButton addSleepingButton = new JButton("Edit Sleeping Facility");
		addSleepingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState= EDITPROPERTY.EDIT_SLEEPING;	
				System.out.println("IN edit sleeping btn");
				System.out.println("facilities id = "+facilityIdAfter);
				
				int id = 0;
				try {
					connection = ConnectionManager.getConnection();
					
					String getSleepingIdQuery = "select sleeping_id from Facilities where facilities_id=?";
					PreparedStatement ps_sleepingId = connection.prepareStatement(getSleepingIdQuery);
					
					ps_sleepingId.setInt(1, facilityIdAfter);

					System.out.println(ps_sleepingId);
					ps_sleepingId.executeQuery();
					
					ResultSet rs = ps_sleepingId.executeQuery();
					
					while (rs.next()) {
						id = rs.getInt("sleeping_id");
		            }	
					
					connection.close();
				} catch(Exception s) {
					System.err.println("Got an exception!");
					System.err.println(s.getMessage());
				}
				System.out.println("IDDDDDDDDDDDD = "+id);
				
				frame.dispose();
				MainModule.controller.editPropertyView(facilityIdAfter, id);
					
			}
		});
		addSleepingButton.setBounds(190, 160, 196, 51);
		addFacilityPanel.add(addSleepingButton);
		
		JButton btnAddBathingFacility = new JButton("Edit Bathing Facility");
		btnAddBathingFacility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState= EDITPROPERTY.EDIT_BATHING;
				
				System.out.println("IN edit bathing btn");
				System.out.println("facilities id = "+facilityIdAfter);
				
				int id = 0;
				try {
					connection = ConnectionManager.getConnection();
					
					String getBathingIdQuery = "select bathing_id from Facilities where facilities_id=?";
					PreparedStatement ps_bathingId = connection.prepareStatement(getBathingIdQuery);
					
					ps_bathingId.setInt(1, facilityIdAfter);

					System.out.println(ps_bathingId);
					ps_bathingId.executeQuery();
					
					ResultSet rs = ps_bathingId.executeQuery();
					
					while (rs.next()) {
						id = rs.getInt("bathing_id");
		            }	
					
					connection.close();
				} catch(Exception s) {
					System.err.println("Got an exception!");
					System.err.println(s.getMessage());
				}
				System.out.println("IDDDDDDDDDDDD = "+id);
				
				frame.dispose();
				MainModule.controller.editPropertyView(facilityIdAfter, id);
				
			}
		});
		btnAddBathingFacility.setBounds(190, 222, 196, 51);
		addFacilityPanel.add(btnAddBathingFacility);
				
		JButton btnAddKitchenfacility = new JButton("Edit Kitchen Facility");
		btnAddKitchenfacility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState= EDITPROPERTY.EDIT_KITCHEN;
				System.out.println("IN edit kitchen btn");
				System.out.println("facilities id = "+facilityIdAfter);
				
				int id = 0;
				try {
					connection = ConnectionManager.getConnection();
					
					String getKitchenIdQuery = "select kitchen_id from Facilities where facilities_id=?";
					PreparedStatement ps_kitchenId = connection.prepareStatement(getKitchenIdQuery);
					
					ps_kitchenId.setInt(1, facilityIdAfter);

					System.out.println(ps_kitchenId);
					ps_kitchenId.executeQuery();
					
					ResultSet rs = ps_kitchenId.executeQuery();
					
					while (rs.next()) {
						id = rs.getInt("kitchen_id");
		            }	
					
					connection.close();
				} catch(Exception s) {
					System.err.println("Got an exception!");
					System.err.println(s.getMessage());
				}
				System.out.println("IDDDDDDDDDDDD = "+id);
				
				frame.dispose();
				MainModule.controller.editPropertyView(facilityIdAfter, id);

			}
		});
		btnAddKitchenfacility.setBounds(190, 289, 196, 57);
		addFacilityPanel.add(btnAddKitchenfacility);
		
		JButton btnAddUtilityFacility = new JButton("Edit Utility Facility");
		btnAddUtilityFacility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState= EDITPROPERTY.EDIT_UTILITY;
				System.out.println("IN edit utility btn");
				System.out.println("facilities id = "+facilityIdAfter);
				
				int id = 0;
				try {
					connection = ConnectionManager.getConnection();
					
					String getUtilityIdQuery = "select utility_id from Facilities where facilities_id=?";
					PreparedStatement ps_UtilityId = connection.prepareStatement(getUtilityIdQuery);
					
					ps_UtilityId.setInt(1, facilityIdAfter);

					System.out.println(ps_UtilityId);
					ps_UtilityId.executeQuery();
					
					ResultSet rs = ps_UtilityId.executeQuery();
					
					while (rs.next()) {
						id = rs.getInt("utility_id");
		            }	
					
					connection.close();
				} catch(Exception s) {
					System.err.println("Got an exception!");
					System.err.println(s.getMessage());
				}
				System.out.println("IDDDDDDDDDDDD = "+id);
				
				frame.dispose();
				MainModule.controller.editPropertyView(facilityIdAfter, id);
				}
		});
		btnAddUtilityFacility.setBounds(192, 363, 194, 57);
		addFacilityPanel.add(btnAddUtilityFacility);
		
		JButton btnAddLivingFacility = new JButton("Edit Living Facility");
		btnAddLivingFacility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState= EDITPROPERTY.EDIT_LIVING;
				System.out.println("IN edit living btn");
				System.out.println("facilities id = "+facilityIdAfter);
				
				int id = 0;
				try {
					connection = ConnectionManager.getConnection();
					
					String getLivingIdQuery = "select living_id from Facilities where facilities_id=?";
					PreparedStatement ps_livingId = connection.prepareStatement(getLivingIdQuery);
					
					ps_livingId.setInt(1, facilityIdAfter);

					System.out.println(ps_livingId);
					ps_livingId.executeQuery();
					
					ResultSet rs = ps_livingId.executeQuery();
					
					while (rs.next()) {
						id = rs.getInt("living_id");
		            }	
					
					connection.close();
				} catch(Exception s) {
					System.err.println("Got an exception!");
					System.err.println(s.getMessage());
				}
				System.out.println("IDDDDDDDDDDDD = "+id);
				
				frame.dispose();
				MainModule.controller.editPropertyView(facilityIdAfter, id);
			}
		});
		btnAddLivingFacility.setBounds(192, 437, 194, 57);
		addFacilityPanel.add(btnAddLivingFacility);
		
		JButton btnAddOutdoorsFacility = new JButton("Edit Outdoors Facility");
		btnAddOutdoorsFacility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState= EDITPROPERTY.EDIT_OUTDOORS;
				System.out.println("IN edit outdoors btn");
				System.out.println("facilities id = "+facilityIdAfter);
				
				int id = 0;
				try {
					connection = ConnectionManager.getConnection();
					
					String getOutdoorsIdQuery = "select outdoors_id from Facilities where facilities_id=?";
					PreparedStatement ps_outdoorsId = connection.prepareStatement(getOutdoorsIdQuery);
					
					ps_outdoorsId.setInt(1, facilityIdAfter);

					System.out.println(ps_outdoorsId);
					ps_outdoorsId.executeQuery();
					
					ResultSet rs = ps_outdoorsId.executeQuery();
					
					while (rs.next()) {
						id = rs.getInt("outdoors_id");
		            }	
					
					connection.close();
				} catch(Exception s) {
					System.err.println("Got an exception!");
					System.err.println(s.getMessage());
				}
				System.out.println("IDDDDDDDDDDDD = "+id);
				
				frame.dispose();
				MainModule.controller.editPropertyView(facilityIdAfter, id);
			}
		});
		btnAddOutdoorsFacility.setBounds(190, 505, 196, 51);
		addFacilityPanel.add(btnAddOutdoorsFacility);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(22, 75, 91, 23);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.userState=USER.HOST;
				mainModule.editPropertyState = EDITPROPERTY.PROPERTIES;
				int id = 0;
				try {
					connection = ConnectionManager.getConnection();
					
					String getHostIDOfUser = "select host_id from HostAccount where email=?";	
					
					PreparedStatement hostIDfromHostAccountTable = connection.prepareStatement(getHostIDOfUser);
					hostIDfromHostAccountTable.setString(1, model.getEmail());
					
					ResultSet h_id = hostIDfromHostAccountTable.executeQuery();
					while (h_id.next()) {
					 id = h_id.getInt(1);
					 System.out.println("host id = "+id);
					}
					 System.out.println("host id  after = "+id);
					 connection.close();
				}catch(Exception ex) {
					System.err.println(ex.getMessage());
				}
				 
				System.out.println(model.getEmail());
				MainModule.controller.editPropertyView(id, 0);
				frame.dispose();
			}
		});
		
		addFacilityPanel.add(backButton);

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}