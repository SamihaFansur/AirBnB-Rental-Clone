package HostGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Controller.Controller;
import GUI.ConnectionManager;
import GUI.MainModule;
import GUI.MainModule.EDITPROPERTY;
import GUI.MainModule.USER;
import Model.Model;

public class EditBathing extends JFrame {

	private JFrame frame;
	private JRadioButton toiletPaperRadioBtn;
	private JRadioButton hairDryerRadioBtn;
	private JButton addBathing;
	private NavHost navForHost = new NavHost();

	private int idAfter;
	private int facilitiesidAfter;

	private boolean hairDryer, toiletPaper;

	Connection connection = null;


	/**
	 * Create the application.
	 */

	private Controller controller;
	private Model model;
	private MainModule mainModule;

	public EditBathing(MainModule mainModule, Controller controller, Model model) {
		// initializeEditBathing();
		this.model = model;
		this.mainModule = mainModule;
		this.controller = controller;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditBathing(int facilitiesId, int id) {

		//Nav bar for logged in users; Host
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		idAfter = id;
		facilitiesidAfter = facilitiesId;
		
		JPanel editBathingPanel = new JPanel();
		editBathingPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(editBathingPanel, BorderLayout.CENTER);
		editBathingPanel.setLayout(null);

		JLabel editBathingLabel = new JLabel("Add Bathing Facility");
		editBathingLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editBathingLabel.setBounds(185, 57, 261, 57);
		editBathingPanel.add(editBathingLabel);

		//displaying Bathing facility amenities stored in the database for a particular bathing id 
		//which related to a bathing facility for a particular property
		try {
			connection = ConnectionManager.getConnection();

			String selectBathingRecord = "select hairDryer, toiletPaper from Bathing " + "where bathing_id=?";

			PreparedStatement selectingBathingValues = connection.prepareStatement(selectBathingRecord);

			selectingBathingValues.setInt(1, id);
			ResultSet rs = selectingBathingValues.executeQuery();
			while (rs.next()) {
				hairDryer = rs.getBoolean("hairDryer");
				toiletPaper = rs.getBoolean("toiletPaper");
			}

			connection.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		JLabel hairDryerLabel = new JLabel("Hair Dryer");
		hairDryerLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		hairDryerLabel.setBounds(170, 188, 167, 34);
		editBathingPanel.add(hairDryerLabel);

		hairDryerRadioBtn = new JRadioButton("Hair Dryer", hairDryer);
		hairDryerRadioBtn.setBounds(364, 188, 21, 23);
		editBathingPanel.add(hairDryerRadioBtn);

		JLabel toiletPaperLabel = new JLabel("Toilet Paper");
		toiletPaperLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toiletPaperLabel.setBounds(170, 285, 167, 34);
		editBathingPanel.add(toiletPaperLabel);

		toiletPaperRadioBtn = new JRadioButton("Toilet paper", toiletPaper);
		toiletPaperRadioBtn.setBounds(364, 296, 21, 23);
		editBathingPanel.add(toiletPaperRadioBtn);

		addBathing = new JButton("Add Bathrooms");
		addBathing.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				updateBathingDetails(id);
				mainModule.editPropertyState = EDITPROPERTY.EDIT_BATHROOM;
				MainModule.controller.editPropertyView(facilitiesidAfter, id);
				frame.dispose();
			}
		});
		addBathing.setBounds(185, 480, 200, 23);
		editBathingPanel.add(addBathing);

		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(44, 76, 91, 23);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainModule.userState = USER.HOST;
				mainModule.editPropertyState = EDITPROPERTY.EDIT_PROPERTY_FACILITIES;
				MainModule.controller.editPropertyView(facilitiesidAfter, idAfter); 
				frame.dispose();

			}
		});
		editBathingPanel.add(backButton);

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	//Saves changes and updates all relevant database tables
	public void updateBathingDetails(int id) {
		try {
			connection = ConnectionManager.getConnection();

			model.setHairDryer(hairDryerRadioBtn.isSelected());
			model.setToiletPaper(toiletPaperRadioBtn.isSelected());

			String updateBathingRecord = "update Bathing set hairDryer=?, toiletPaper=? " + "where bathing_id=?";

			PreparedStatement updatingBathingValues = connection.prepareStatement(updateBathingRecord);
			updatingBathingValues.setBoolean(1, model.getHairDryer());
			updatingBathingValues.setBoolean(2, model.getToiletPaper());
			updatingBathingValues.setInt(3, idAfter);
			updatingBathingValues.executeUpdate();

			String updateBathingIdInFacilities = "update Facilities set bathing_id=? where facilities_id=?";

			PreparedStatement updatingBathingIdInFacilities = connection.prepareStatement(updateBathingIdInFacilities);
			updatingBathingIdInFacilities.setInt(1, idAfter);
			updatingBathingIdInFacilities.setInt(2, facilitiesidAfter);

			updatingBathingIdInFacilities.executeUpdate();

			connection.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}