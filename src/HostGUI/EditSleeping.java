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

public class EditSleeping extends JFrame {

	private JFrame frame;
	private NavHost navForHost = new NavHost();
	private JRadioButton towelsRadioBtn;
	private JRadioButton bedLinenRadioBtn;

	private int idAfter;
	private int facilitiesidAfter;

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
	private JTextField numberOfBedsTextField;

	public EditSleeping(MainModule mainModule, Controller controller, Model model) {
		// initializeEditSleeping();
		this.model = model;
		this.mainModule = mainModule;
		this.controller = controller;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditSleeping(int facilitiesId, int id) {
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		idAfter = id;
		facilitiesidAfter = facilitiesId;

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

			String selectSleepingRecord = "select bedLinen, towels from Sleeping " + "where sleeping_id=?";

			PreparedStatement selectingSleepingValues = connection.prepareStatement(selectSleepingRecord);

			selectingSleepingValues.setInt(1, id);
			ResultSet rs = selectingSleepingValues.executeQuery();
			while (rs.next()) {
				bedLinen = rs.getBoolean("bedLinen");
				towels = rs.getBoolean("towels");
			}
			connection.close();
		} catch (Exception e) {
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
			@Override
			public void actionPerformed(ActionEvent e) {
				updateSleepingDetails(id);
				mainModule.editPropertyState = EDITPROPERTY.EDIT_BEDROOM;
				MainModule.controller.editPropertyView(facilitiesidAfter, idAfter); // fix params
				frame.dispose();
			}
		});
		addBedroomButton.setBounds(197, 482, 209, 46);
		editSleepingPanel.add(addBedroomButton);

		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(27, 69, 91, 23);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainModule.userState = USER.HOST;
				mainModule.editPropertyState = EDITPROPERTY.EDIT_PROPERTY_FACILITIES;
				MainModule.controller.editPropertyView(facilitiesidAfter, idAfter); // fix params
				frame.dispose();

			}
		});
		editSleepingPanel.add(backButton);

		JLabel noOfBedsLabel = new JLabel("Number of Beds in Faciity");
		noOfBedsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		noOfBedsLabel.setBounds(171, 358, 209, 34);
		editSleepingPanel.add(noOfBedsLabel);

		numberOfBedsTextField = new JTextField();
		numberOfBedsTextField.setBounds(385, 358, 49, 29);
		editSleepingPanel.add(numberOfBedsTextField);
		numberOfBedsTextField.setColumns(10);

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void updateSleepingDetails(int id) {
		try {
			connection = ConnectionManager.getConnection();

			model.setBedLinen(bedLinenRadioBtn.isSelected());
			model.setTowels(towelsRadioBtn.isSelected());
			model.setNoOfBeds(Integer.parseInt(numberOfBedsTextField.getText()));
			String updateSleepingRecord = "update Sleeping set bedLinen=?, towels=?, noOfBeds=? "
					+ "where sleeping_id=?";

			PreparedStatement updatingSleepingValues = connection.prepareStatement(updateSleepingRecord);
			updatingSleepingValues.setBoolean(1, model.getBedLinen());
			updatingSleepingValues.setBoolean(2, model.getTowels());
			updatingSleepingValues.setInt(3, model.getNoOfBeds());
			updatingSleepingValues.setInt(4, idAfter);
			updatingSleepingValues.executeUpdate();

			String updateSleepingIdInFacilities = "update Facilities set sleeping_id=? where facilities_id=?";

			PreparedStatement updatingSleepingIdInFacilities = connection
					.prepareStatement(updateSleepingIdInFacilities);
			updatingSleepingIdInFacilities.setInt(1, idAfter);
			updatingSleepingIdInFacilities.setInt(2, facilitiesidAfter);
			updatingSleepingIdInFacilities.executeUpdate();
			
			connection.close();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
}
//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW