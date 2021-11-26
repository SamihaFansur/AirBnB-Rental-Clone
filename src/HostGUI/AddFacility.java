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

public class AddFacility extends JFrame{


	private NavHost navForHost = new NavHost();
	private JFrame frame;
	private int kitchenId;
	private int sleepingId;
	private int bathingId;
	private int utilityId;
	private int livingId;
	private int outdoorsId;
	
	
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
	 
	 public AddFacility(MainModule mainModule, Controller controller, Model model) {
		//initializeAddFacility();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeAddFacility(int facilityId, int id) {
		int facilityIdAfter = facilityId;
		
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
	
		
		JPanel addFacilityPanel = new JPanel();
		addFacilityPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(addFacilityPanel, BorderLayout.CENTER);
		addFacilityPanel.setLayout(null);

		JLabel addFacilityLabel = new JLabel("Add Facility");
		addFacilityLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		addFacilityLabel.setBounds(222, 53, 183, 57);
		addFacilityPanel.add(addFacilityLabel);
		
		JButton addSleepingButton = new JButton("Add Sleeping Facility");
		addSleepingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState= EDITPROPERTY.EDIT_SLEEPING;
				
				//checking value of sleeping id. Once it's been completed for the first time the host wont have access to edit this facility from here.
				
				if(model.getCurrentSleepingId()==0) {
					
					
					try {
						connection = ConnectionManager.getConnection();
						
						String insertSleepingQuery = "insert into Sleeping (bedLinen, towels, noOfBedrooms)"
													+ " values(?,?,?)";
						PreparedStatement ps_sleeping = connection.prepareStatement(insertSleepingQuery, Statement.RETURN_GENERATED_KEYS);
						
						ps_sleeping.setBoolean(1, false);
						ps_sleeping.setBoolean(2, false);
						ps_sleeping.setInt(3, 0);
	
						System.out.println(ps_sleeping);
						ps_sleeping.executeUpdate();
						
						ResultSet rs=ps_sleeping.getGeneratedKeys();
						if(rs.next()){
							sleepingId=rs.getInt(1);
						}
						model.setCurrentSleepingId(sleepingId);
						
						String insertSleepingId = "UPDATE Facilities SET sleeping_id = ? WHERE facilities_id = ?";
						PreparedStatement ps_sleepingid = connection.prepareStatement(insertSleepingId);
						ps_sleepingid.setInt(1, model.getCurrentSleepingId());
						ps_sleepingid.setInt(2, model.getFacilitiesId());
						connection.close();
					} catch(Exception s) {
						System.err.println("Got an exception!");
						System.err.println(s.getMessage());
					}
					System.out.println("IDDDDDDDDDDDD = "+model.getCurrentSleepingId());
					
					frame.dispose();
					MainModule.controller.editPropertyView(facilityId, model.getCurrentSleepingId());
					}
				else {
					displayMessageAlreadyMade();
				}
			}
		});
		addSleepingButton.setBounds(190, 160, 196, 51);
		addFacilityPanel.add(addSleepingButton);
		
		JButton btnAddBathingFacility = new JButton("Add Bathing Facility");
		btnAddBathingFacility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState= EDITPROPERTY.EDIT_BATHING;
				
				//checking value of bathing id. Once it's been completed for the first time the host wont have access to edit this facility from here.
				if(model.getCurentBathingId()==0) {
				
				
					try {
						connection = ConnectionManager.getConnection();
						
						String insertBathingQuery = "insert into Bathing (hairDryer, toiletPaper, noOfBathrooms)"
													+ " values(?,?,?)";
						PreparedStatement ps_bathing = connection.prepareStatement(insertBathingQuery, Statement.RETURN_GENERATED_KEYS);
						
						ps_bathing.setBoolean(1, false);
						ps_bathing.setBoolean(2, false);
						ps_bathing.setInt(3, 0);
	
						System.out.println(ps_bathing);
						ps_bathing.executeUpdate();
						
						ResultSet rs=ps_bathing.getGeneratedKeys();
						if(rs.next()){
							bathingId=rs.getInt(1);
						}
						//sets bathing id to new row value
						model.setCurrentBathingId(bathingId);
						String insertBathingId = "UPDATE Facilities SET bathing_id = ? WHERE facilities_id = ?";
						PreparedStatement ps_bathingid = connection.prepareStatement(insertBathingId);
						ps_bathingid.setInt(1, model.getCurentBathingId());
						ps_bathingid.setInt(2, model.getFacilitiesId());
						connection.close();
					} catch(Exception s) {
						System.err.println("Got an exception!");
						System.err.println(s.getMessage());
					}
					System.out.println("IDDDDDDDDDDDD = "+model.getCurentBathingId());
					
					frame.dispose();
					MainModule.controller.editPropertyView(facilityId, model.getCurentBathingId());
					}
				else {
					displayMessageAlreadyMade();
				}
			}
		});
		btnAddBathingFacility.setBounds(190, 222, 196, 51);
		addFacilityPanel.add(btnAddBathingFacility);
		
		//int kitchenId = 0;
		
		JButton btnAddKitchenfacility = new JButton("Add Kitchen Facility");
		btnAddKitchenfacility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState= EDITPROPERTY.EDIT_KITCHEN;
				//create a record and set values to null
				//int kitchenId=0;
				//model.setCurrentKitchedId(0);
				//setting 
				//kitchenId = 0;
				
				if (model.getCurrentKitchedId()== 0) {
						
					
					try {
						connection = ConnectionManager.getConnection();
						
						String insertKitchenQuery = "insert into Kitchen (refrigerator, microwave, oven, "
													+ "stove, dishwasher, tableware, cookware, basicProvision)"
													+ " values(?,?,?,?,?,?,?,?) ";
						PreparedStatement ps_kitchen = connection.prepareStatement(insertKitchenQuery, Statement.RETURN_GENERATED_KEYS);
						
						ps_kitchen.setBoolean(1, false);
						ps_kitchen.setBoolean(2, false);
						ps_kitchen.setBoolean(3, false);
						ps_kitchen.setBoolean(4, false);
						ps_kitchen.setBoolean(5, false);
						ps_kitchen.setBoolean(6, false);
						ps_kitchen.setBoolean(7, false);
						ps_kitchen.setBoolean(8, false);
	
						System.out.println(ps_kitchen);
						ps_kitchen.executeUpdate();
						
						ResultSet rs=ps_kitchen.getGeneratedKeys();
						if(rs.next()){
							kitchenId=rs.getInt(1);
						}
						model.setCurrentKitchedId(kitchenId);
						String insertKitchenId = "UPDATE Facilities SET kitchen_id = ? WHERE facilities_id = ?";
						PreparedStatement ps_kitchenid = connection.prepareStatement(insertKitchenId);
						ps_kitchenid.setInt(1, model.getCurrentKitchedId());
						ps_kitchenid.setInt(2, model.getFacilitiesId());
						connection.close();
					} catch(Exception s) {
						System.err.println("Got an exception!");
						System.err.println(s.getMessage());
					}
					System.out.println("IDDDDDDDDDDDD = "+model.getCurrentKitchedId());
					
					frame.dispose();
					MainModule.controller.editPropertyView(facilityIdAfter, model.getCurrentKitchedId());
				}
				else {
					displayMessageAlreadyMade();
				}
					System.out.println("KITCHEN IS IS*************"+kitchenId);
			}
		});
		btnAddKitchenfacility.setBounds(190, 289, 196, 57);
		addFacilityPanel.add(btnAddKitchenfacility);
		
		JButton btnAddUtilityFacility = new JButton("Add Utility Facility");
		btnAddUtilityFacility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState= EDITPROPERTY.EDIT_UTILITY;
				
				if(model.getCurrentUtilityId()==0) {
					try {
						connection = ConnectionManager.getConnection();
						
						String insertUtilityQuery = "insert into Utility (heating, washingMachine, dryingMachine, " //change to machine spelling
													+ "fireExtinguisher, smokeAlarm, firstAidKit)"
													+ " values(?,?,?,?,?,?) ";
						PreparedStatement ps_utility = connection.prepareStatement(insertUtilityQuery, Statement.RETURN_GENERATED_KEYS);
						
						ps_utility.setBoolean(1, false);
						ps_utility.setBoolean(2, false);
						ps_utility.setBoolean(3, false);
						ps_utility.setBoolean(4, false);
						ps_utility.setBoolean(5, false);
						ps_utility.setBoolean(6, false);
	
						System.out.println(ps_utility);
						ps_utility.executeUpdate();
						
						ResultSet rs=ps_utility.getGeneratedKeys();
						if(rs.next()){
							utilityId=rs.getInt(1);
						}
						model.setCurrentUtilityId(utilityId);
						String insertUtilityId = "UPDATE Facilities SET utility_id = ? WHERE facilities_id = ?";
						PreparedStatement ps_utilityid = connection.prepareStatement(insertUtilityId);
						ps_utilityid.setInt(1, model.getCurrentUtilityId());
						ps_utilityid.setInt(2, model.getFacilitiesId());
						connection.close();
					} catch(Exception s) {
						System.err.println("Got an exception!");
						System.err.println(s.getMessage());
					}
					System.out.println("IDDDDDDDDDDDD = "+model.getCurrentUtilityId());
					
					frame.dispose();
					MainModule.controller.editPropertyView(facilityIdAfter, model.getCurrentUtilityId());
					}
				else {
					displayMessageAlreadyMade();
				}
			}
		});
		btnAddUtilityFacility.setBounds(192, 363, 194, 57);
		addFacilityPanel.add(btnAddUtilityFacility);
		
		JButton btnAddLivingFacility = new JButton("Add Living Facility");
		btnAddLivingFacility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState= EDITPROPERTY.EDIT_LIVING;
				//create a record and set values to null
				
				if(model.getCurrentLivingId()==0) {
				
					try {
						connection = ConnectionManager.getConnection();
						
						String insertLivingQuery = "insert into Living (wifi, television, satellite, "
													+ "streaming, dvdPlayer, boardGames)"
													+ " values(?,?,?,?,?,?) ";
	
						PreparedStatement ps_living = connection.prepareStatement(insertLivingQuery, Statement.RETURN_GENERATED_KEYS);
						
						ps_living.setBoolean(1, false);
						ps_living.setBoolean(2, false);
						ps_living.setBoolean(3, false);
						ps_living.setBoolean(4, false);
						ps_living.setBoolean(5, false);
						ps_living.setBoolean(6, false);
	
						System.out.println(ps_living);
						ps_living.executeUpdate();
						
						ResultSet rs=ps_living.getGeneratedKeys();
						if(rs.next()){
							livingId=rs.getInt(1);
						}
						model.setCurrentLivingId(livingId);
						String insertLivingId = "UPDATE Facilities SET living_id = ? WHERE facilities_id = ?";
						PreparedStatement ps_livingid = connection.prepareStatement(insertLivingId);
						ps_livingid.setInt(1, model.getCurrentLivingId());
						ps_livingid.setInt(2, model.getFacilitiesId());
						connection.close();
					} catch(Exception s) {
						System.err.println("Got an exception!");
						System.err.println(s.getMessage());
					}
					System.out.println("IDDDDDDDDDDDD = "+model.getCurrentLivingId());
					
					frame.dispose();
					MainModule.controller.editPropertyView(facilityId, model.getCurrentLivingId());
					}
				else{
					displayMessageAlreadyMade();
				}
			}
		});
		btnAddLivingFacility.setBounds(192, 437, 194, 57);
		addFacilityPanel.add(btnAddLivingFacility);
		
		JButton btnAddOutdoorsFacility = new JButton("Add Outdoors Facility");
		btnAddOutdoorsFacility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState= EDITPROPERTY.EDIT_OUTDOORS;
				//create a record and set values to null
				if(model.getCurrentOutdoorsId()==0) {
					try {
						connection = ConnectionManager.getConnection();
						
						String insertOutdoorsQuery = "insert into Outdoors (freeOnSiteParking, onRoadParking, paidCarPark, "
													+ "patio, barbeque) "
													+ " values(?,?,?,?,?) ";
	
						PreparedStatement ps_outdoors = connection.prepareStatement(insertOutdoorsQuery, Statement.RETURN_GENERATED_KEYS);
						
						ps_outdoors.setBoolean(1, false);
						ps_outdoors.setBoolean(2, false);
						ps_outdoors.setBoolean(3, false);
						ps_outdoors.setBoolean(4, false);
						ps_outdoors.setBoolean(5, false);
	
						System.out.println(ps_outdoors);
						ps_outdoors.executeUpdate();
						
						ResultSet rs=ps_outdoors.getGeneratedKeys();
						if(rs.next()){
							outdoorsId=rs.getInt(1);
						}
						model.setCurrentOutdoorsId(outdoorsId);
						String inserOutdoorsId = "UPDATE Facilities SET outdoors_id = ? WHERE facilities_id = ?";
						PreparedStatement ps_outdoorsid = connection.prepareStatement(inserOutdoorsId);
						ps_outdoorsid.setInt(1, model.getCurrentOutdoorsId());
						ps_outdoorsid.setInt(2, model.getFacilitiesId());
						connection.close();
					} catch(Exception s) {
						System.err.println("Got an exception!");
						System.err.println(s.getMessage());
					}
					System.out.println("IDDDDDDDDDDDD = "+model.getCurrentOutdoorsId());
					
					frame.dispose();
					MainModule.controller.editPropertyView(facilityId, model.getCurrentOutdoorsId());
					}
				else {
					displayMessageAlreadyMade();
				}
			}
		});
		btnAddOutdoorsFacility.setBounds(190, 505, 196, 51);
		addFacilityPanel.add(btnAddOutdoorsFacility);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(22, 75, 91, 23);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Homepage sp = new Homepage();

			  //  mainModule.currentState=STATE.EDIT_PROPERTY;
				mainModule.userState=USER.HOST;
				mainModule.editPropertyState = EDITPROPERTY.EDIT_PROPERTY;
				MainModule.controller.editPropertyView(facilityId, 0); //need to check what 2nd param is
//				close();
				frame.dispose();
				
			}
		});
		
		addFacilityPanel.add(backButton);


		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void displayMessageAlreadyMade(){
		 JOptionPane.showMessageDialog(this, "You have already saved this facility. Go to Facilties to edit this facility");
	}
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW