package HostGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import Controller.Controller;
import GUI.ConnectionManager;
import GUI.MainModule;
import GUI.MainModule.EDITPROPERTY;
import GUI.MainModule.STATE;
import Model.Model;

public class AddFacility extends JFrame {

	private NavHost navForHost = new NavHost();
	private JFrame frame;
	private int kitchenId;
	private int sleepingId;
	private int bathingId;
	private int utilityId;
	private int livingId;
	private int outdoorsId;
	private boolean previouslyInPropertiesList;

	private int facilityIdAfter;
	private int hostId;
	private int faciltiesId;

	Connection connection = null;

	public void close() {
		frame.dispose();
	}

	/**
	 * Class for adding a facility to a property
	 */

	private Controller controller;
	private Model model;
	private MainModule mainModule;

	
	public AddFacility(MainModule mainModule, Controller controller, Model model) {
		// initializeAddFacility();
		this.model = model;
		this.mainModule = mainModule;
		this.controller = controller;
	}

	/**
	 * Initializes the contents of the frame.
	 */
	public void initializeAddFacility(int facilityId, int id) {
		faciltiesId = model.getFacilitiesId();
		facilityIdAfter = facilityId;
		hostId = id;
		previouslyInPropertiesList = model.getPreviouslyInPropertiesList();

		//Creates a frame with a navbar
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		JPanel addFacilityPanel = new JPanel();
		addFacilityPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(addFacilityPanel, BorderLayout.CENTER);
		addFacilityPanel.setLayout(null);

		JLabel addFacilityLabel = new JLabel("Add Facility");
		addFacilityLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		addFacilityLabel.setBounds(234, 56, 183, 57);
		addFacilityPanel.add(addFacilityLabel);

		JButton addSleepingButton = new JButton("Add Sleeping Facility");
		addSleepingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState = EDITPROPERTY.EDIT_SLEEPING;

				// Checks value of sleeping id. Once it's been completed for the first time
				// the host wont have access to edit this facility from here.

				if (model.getCurrentSleepingId() == 0) {

					try {
						connection = ConnectionManager.getConnection();

						String insertSleepingQuery = "insert into Sleeping (bedLinen, towels, noOfBeds, noOfBedrooms)"
								+ " values(?,?,?,?)";
						PreparedStatement ps_sleeping = connection.prepareStatement(insertSleepingQuery,
								Statement.RETURN_GENERATED_KEYS);

						ps_sleeping.setBoolean(1, false);
						ps_sleeping.setBoolean(2, false);
						ps_sleeping.setInt(3, 0);
						ps_sleeping.setInt(4, 0);
						ps_sleeping.executeUpdate();

						ResultSet rs = ps_sleeping.getGeneratedKeys();
						if (rs.next()) {
							sleepingId = rs.getInt(1);
						}
						model.setCurrentSleepingId(sleepingId);

						String insertSleepingId = "UPDATE Facilities SET sleeping_id = ? WHERE facilities_id = ?";
						PreparedStatement ps_sleepingid = connection.prepareStatement(insertSleepingId);
						ps_sleepingid.setInt(1, model.getCurrentSleepingId());
						ps_sleepingid.setInt(2, model.getFacilitiesId());
						connection.close();
					} catch (Exception s) {
						System.err.println("Got an exception!");
						System.err.println(s.getMessage());
					}
					frame.dispose();
					MainModule.controller.editPropertyView(facilityId, model.getCurrentSleepingId());
				} else {
					displayMessageAlreadyMade();
				}
			}
		});
		addSleepingButton.setBounds(192, 221, 196, 51);
		addFacilityPanel.add(addSleepingButton);

		//Button For adding a Bathing facility with initially empty values to property
		JButton btnAddBathingFacility = new JButton("Add Bathing Facility");
		btnAddBathingFacility.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState = EDITPROPERTY.EDIT_BATHING;

				// checking value of bathing id. Once it's been completed for the first time the
				// host wont have access to edit this facility from here.
				if (model.getCurentBathingId() == 0) {

					try {
						connection = ConnectionManager.getConnection();

						String insertBathingQuery = "insert into Bathing (hairDryer, toiletPaper, noOfBathrooms)"
								+ " values(?,?,?)";
						PreparedStatement ps_bathing = connection.prepareStatement(insertBathingQuery,
								Statement.RETURN_GENERATED_KEYS);

						ps_bathing.setBoolean(1, false);
						ps_bathing.setBoolean(2, false);
						ps_bathing.setInt(3, 0);
						ps_bathing.executeUpdate();

						ResultSet rs = ps_bathing.getGeneratedKeys();
						if (rs.next()) {
							bathingId = rs.getInt(1);
						}
						// sets bathing id to new row value
						model.setCurrentBathingId(bathingId);
						String insertBathingId = "UPDATE Facilities SET bathing_id = ? WHERE facilities_id = ?";
						PreparedStatement ps_bathingid = connection.prepareStatement(insertBathingId);
						ps_bathingid.setInt(1, model.getCurentBathingId());
						ps_bathingid.setInt(2, model.getFacilitiesId());
						connection.close();
					} catch (Exception s) {
						System.err.println("Got an exception!");
						System.err.println(s.getMessage());
					}
					frame.dispose();
					MainModule.controller.editPropertyView(facilityId, model.getCurentBathingId());
				} else {
					displayMessageAlreadyMade();
				}
			}
		});
		btnAddBathingFacility.setBounds(192, 283, 196, 51);
		addFacilityPanel.add(btnAddBathingFacility);

		//Button For adding a Kitchen facility with initially empty values to property
		JButton btnAddKitchenfacility = new JButton("Add Kitchen Facility");
		btnAddKitchenfacility.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState = EDITPROPERTY.EDIT_KITCHEN;
				if (model.getCurrentKitchedId() == 0) {
					try {
						connection = ConnectionManager.getConnection();

						String insertKitchenQuery = "insert into Kitchen (refrigerator, microwave, oven, "
								+ "stove, dishwasher, tableware, cookware, basicProvision)"
								+ " values(?,?,?,?,?,?,?,?) ";
						PreparedStatement ps_kitchen = connection.prepareStatement(insertKitchenQuery,
								Statement.RETURN_GENERATED_KEYS);

						ps_kitchen.setBoolean(1, false);
						ps_kitchen.setBoolean(2, false);
						ps_kitchen.setBoolean(3, false);
						ps_kitchen.setBoolean(4, false);
						ps_kitchen.setBoolean(5, false);
						ps_kitchen.setBoolean(6, false);
						ps_kitchen.setBoolean(7, false);
						ps_kitchen.setBoolean(8, false);
						ps_kitchen.executeUpdate();

						ResultSet rs = ps_kitchen.getGeneratedKeys();
						if (rs.next()) {
							kitchenId = rs.getInt(1);
						}
						model.setCurrentKitchedId(kitchenId);
						String insertKitchenId = "UPDATE Facilities SET kitchen_id = ? WHERE facilities_id = ?";
						PreparedStatement ps_kitchenid = connection.prepareStatement(insertKitchenId);
						ps_kitchenid.setInt(1, model.getCurrentKitchedId());
						ps_kitchenid.setInt(2, model.getFacilitiesId());
						connection.close();
					} catch (Exception s) {
						System.err.println("Got an exception!");
						System.err.println(s.getMessage());
					}
					frame.dispose();
					MainModule.controller.editPropertyView(facilityIdAfter, model.getCurrentKitchedId());
				} else {
					displayMessageAlreadyMade();
				}
			}
		});
		btnAddKitchenfacility.setBounds(192, 350, 196, 57);
		addFacilityPanel.add(btnAddKitchenfacility);
		
		//Button For adding a Utility facility with initially empty values to property
		JButton btnAddUtilityFacility = new JButton("Add Utility Facility");
		btnAddUtilityFacility.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState = EDITPROPERTY.EDIT_UTILITY;

				if (model.getCurrentUtilityId() == 0) {
					try {
						connection = ConnectionManager.getConnection();

						String insertUtilityQuery = "insert into Utility (heating, washingMachine, dryingMachine, " // change
																													// to
																													// machine
																													// spelling
								+ "fireExtinguisher, smokeAlarm, firstAidKit)" + " values(?,?,?,?,?,?) ";
						PreparedStatement ps_utility = connection.prepareStatement(insertUtilityQuery,
								Statement.RETURN_GENERATED_KEYS);

						ps_utility.setBoolean(1, false);
						ps_utility.setBoolean(2, false);
						ps_utility.setBoolean(3, false);
						ps_utility.setBoolean(4, false);
						ps_utility.setBoolean(5, false);
						ps_utility.setBoolean(6, false);
						ps_utility.executeUpdate();

						ResultSet rs = ps_utility.getGeneratedKeys();
						if (rs.next()) {
							utilityId = rs.getInt(1);
						}
						model.setCurrentUtilityId(utilityId);
						String insertUtilityId = "UPDATE Facilities SET utility_id = ? WHERE facilities_id = ?";
						PreparedStatement ps_utilityid = connection.prepareStatement(insertUtilityId);
						ps_utilityid.setInt(1, model.getCurrentUtilityId());
						ps_utilityid.setInt(2, model.getFacilitiesId());
						connection.close();
					} catch (Exception s) {
						System.err.println("Got an exception!");
						System.err.println(s.getMessage());
					}
					frame.dispose();
					MainModule.controller.editPropertyView(facilityIdAfter, model.getCurrentUtilityId());
				} else {
					displayMessageAlreadyMade();
				}
			}
		});
		btnAddUtilityFacility.setBounds(194, 424, 194, 57);
		addFacilityPanel.add(btnAddUtilityFacility);

		//Button For adding a Facility facility with initially empty values to property
		JButton btnAddLivingFacility = new JButton("Add Living Facility");
		btnAddLivingFacility.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState = EDITPROPERTY.EDIT_LIVING;
				// create a record and set values to null

				if (model.getCurrentLivingId() == 0) {

					try {
						connection = ConnectionManager.getConnection();

						String insertLivingQuery = "insert into Living (wifi, television, satellite, "
								+ "streaming, dvdPlayer, boardGames)" + " values(?,?,?,?,?,?) ";

						PreparedStatement ps_living = connection.prepareStatement(insertLivingQuery,
								Statement.RETURN_GENERATED_KEYS);

						ps_living.setBoolean(1, false);
						ps_living.setBoolean(2, false);
						ps_living.setBoolean(3, false);
						ps_living.setBoolean(4, false);
						ps_living.setBoolean(5, false);
						ps_living.setBoolean(6, false);
						ps_living.executeUpdate();

						ResultSet rs = ps_living.getGeneratedKeys();
						if (rs.next()) {
							livingId = rs.getInt(1);
						}
						model.setCurrentLivingId(livingId);
						String insertLivingId = "UPDATE Facilities SET living_id = ? WHERE facilities_id = ?";
						PreparedStatement ps_livingid = connection.prepareStatement(insertLivingId);
						ps_livingid.setInt(1, model.getCurrentLivingId());
						ps_livingid.setInt(2, model.getFacilitiesId());
						connection.close();
					} catch (Exception s) {
						System.err.println(s.getMessage());
					}
					frame.dispose();
					MainModule.controller.editPropertyView(facilityId, model.getCurrentLivingId());
				} else {
					displayMessageAlreadyMade();
				}
			}
		});
		btnAddLivingFacility.setBounds(194, 498, 194, 57);
		addFacilityPanel.add(btnAddLivingFacility);

		//Button For adding a Outdoors facility with initially empty values to property
		JButton btnAddOutdoorsFacility = new JButton("Add Outdoors Facility");
		btnAddOutdoorsFacility.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState = EDITPROPERTY.EDIT_OUTDOORS;
				// create a record and set values to null
				if (model.getCurrentOutdoorsId() == 0) {
					try {
						connection = ConnectionManager.getConnection();

						String insertOutdoorsQuery = "insert into Outdoors (freeOnSiteParking, onRoadParking, paidCarPark, "
								+ "patio, barbeque) " + " values(?,?,?,?,?) ";

						PreparedStatement ps_outdoors = connection.prepareStatement(insertOutdoorsQuery,
								Statement.RETURN_GENERATED_KEYS);

						ps_outdoors.setBoolean(1, false);
						ps_outdoors.setBoolean(2, false);
						ps_outdoors.setBoolean(3, false);
						ps_outdoors.setBoolean(4, false);
						ps_outdoors.setBoolean(5, false);
						ps_outdoors.executeUpdate();

						ResultSet rs = ps_outdoors.getGeneratedKeys();
						if (rs.next()) {
							outdoorsId = rs.getInt(1);
						}
						model.setCurrentOutdoorsId(outdoorsId);
						String inserOutdoorsId = "UPDATE Facilities SET outdoors_id = ? WHERE facilities_id = ?";
						PreparedStatement ps_outdoorsid = connection.prepareStatement(inserOutdoorsId);
						ps_outdoorsid.setInt(1, model.getCurrentOutdoorsId());
						ps_outdoorsid.setInt(2, model.getFacilitiesId());
						connection.close();
					} catch (Exception s) {
						System.err.println("Got an exception!");
						System.err.println(s.getMessage());
					}

					frame.dispose();
					MainModule.controller.editPropertyView(facilityId, model.getCurrentOutdoorsId());
				} else {
					displayMessageAlreadyMade();
				}
			}
		});
		btnAddOutdoorsFacility.setBounds(192, 566, 196, 51);
		addFacilityPanel.add(btnAddOutdoorsFacility);
		//Button for returning to the previous GUI page
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(22, 75, 91, 23);

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				previouslyInPropertiesList = model.getPreviouslyInPropertiesList();
				if (previouslyInPropertiesList) {
					mainModule.currentState = STATE.HOST_ACCOUNT;
					MainModule.controller.drawNewView();
					System.out.println(hostId);
					System.out.println(facilityId);
					frame.setVisible(false);
				} else {
					mainModule.currentState = STATE.HOST_ACCOUNT;
					MainModule.controller.editPropertyView(hostId, facilityId);
					frame.setVisible(false);
				}
			}
		});
		addFacilityPanel.add(backButton);

		JLabel messegeLabel = new JLabel("Create all Facilities Before Leaving Page");
		messegeLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		messegeLabel.setBounds(178, 141, 288, 42);
		addFacilityPanel.add(messegeLabel);

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	//Displays error message if user tries to add a facility that already exists in the property
	public void displayMessageAlreadyMade() {
		JOptionPane.showMessageDialog(this,
				"You have already saved this facility. Go to Facilties to edit this facility");
	}
}
//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW