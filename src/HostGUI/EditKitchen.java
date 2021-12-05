
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
import javax.swing.WindowConstants;

import Controller.Controller;
import GUI.ConnectionManager;
import GUI.MainModule;
import GUI.MainModule.EDITPROPERTY;
import GUI.MainModule.USER;
import Model.Model;


/*
 * Class to editKitchen object's information already in database
 */
public class EditKitchen extends JFrame {

	private JFrame frame;
	private NavHost navForHost = new NavHost();

	public void close() {
		frame.dispose();
	}

	/**
	 *  Initialses buttons and variables needed for functions
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

	//Constrctor for the EditKitchen class
	public EditKitchen(MainModule mainModule, Controller controller, Model model) {
		this.model = model;
		this.mainModule = mainModule;
		this.controller = controller;
	}

	/**
	 * Initialize the contents of the frame so that is can be used from other GUIs
	 */
	public void initializeEditKitchen(int facilitiesId, int id) {
		//Creates frame and adds a NavBar
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		idAfter = id;
		facilitiesidAfter = facilitiesId;

		//Creates main panel and adds to frame
		JPanel editKitchenPanel = new JPanel();
		editKitchenPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(editKitchenPanel, BorderLayout.CENTER);
		editKitchenPanel.setLayout(null);

		JLabel editKitchenLabel = new JLabel("Kitchen facility");
		editKitchenLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editKitchenLabel.setBounds(186, 48, 261, 57);
		editKitchenPanel.add(editKitchenLabel);

		//Selects the kitchen that is being edited from database and sets
		// the values to variables
		try {
			connection = ConnectionManager.getConnection();

			String selectKitchenRecord = "select refrigerator, microwave, " + "oven, stove, dishwasher, tableware, "
					+ "cookware, basicProvision from Kitchen " + "where kitchen_id=?";

			PreparedStatement selectingKitchenValues = connection.prepareStatement(selectKitchenRecord);

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
			connection.close();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}

		//Creates the objects for the GUi such as labels and textFields
		// and adds them to the panel/frame
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

		addKitchen = new JButton("Save");
		addKitchen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateKitchenDetails();
			}
		});
		addKitchen.setBounds(248, 602, 91, 23);
		editKitchenPanel.add(addKitchen);

		//Button to return user to previous GUI page
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(31, 58, 91, 23);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainModule.userState = USER.HOST;
				mainModule.editPropertyState = EDITPROPERTY.EDIT_PROPERTY_FACILITIES;
				MainModule.controller.editPropertyView(facilitiesidAfter, idAfter); // fix params
				frame.dispose();
			}
		});
		editKitchenPanel.add(backButton);

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	//Function to update the information of a kitchen in the database
	public void updateKitchenDetails() {
		try {
			connection = ConnectionManager.getConnection();

			//displays the kitchen to be editeded's information in the radiobuttons 
			//so user can see current values
			model.setRefrigerator(refrigeratorRadioBtn.isSelected());
			model.setMicrowave(microwaveRadioBtn.isSelected());
			model.setOven(ovenRadioBtn.isSelected());
			model.setStove(stoveRadioBtn.isSelected());
			model.setDishwasher(dishwasherRadioBtn.isSelected());
			model.setTableware(tablewareRadioBtn.isSelected());
			model.setCookware(cookwareRadioBtn.isSelected());
			model.setBasicProvisions(basicProvisionsRadioBtn.isSelected());

			//Updates the database with the new values that the use imputs 
			String updateKitchenRecord = "update Kitchen set refrigerator=?, microwave=?, "
					+ "oven=?, stove=?, dishwasher=?, tableware=?, " + "cookware=?, basicProvision=?  "
					+ "where kitchen_id=?";

			PreparedStatement updatingKitchenValues = connection.prepareStatement(updateKitchenRecord);
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

			String updateKitchenIdInFacilities = "update Facilities set kitchen_id=? where facilities_id=?";

			PreparedStatement updatingKitchenIdInFacilities = connection.prepareStatement(updateKitchenIdInFacilities);
			updatingKitchenIdInFacilities.setInt(1, idAfter);
			updatingKitchenIdInFacilities.setInt(2, facilitiesidAfter);
			updatingKitchenIdInFacilities.executeUpdate();

			connection.close();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
}
