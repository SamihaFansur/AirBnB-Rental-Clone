package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Controller.Controller;
import GUI.MainModule.STATE;
import GUI.MainModule.USER;
import GuestGUI.NavGuest;
import HostGUI.NavHost;
import Model.Model;

/*
 * GUI pages for editing the account of a user
 */

public class EditAccount extends JFrame {

	private NavHost navForHost = new NavHost();
	private NavGuest navForGuest = new NavGuest();
	private JFrame frame;
	private JTextField firstNameTextField;
	private JTextField surnameTextField;
	private JTextField passwordTextField;
	private JButton addEditPropertyButton;
	private JComboBox titleComboBox;

	private String title, firstName, surname, password;
	Connection connection = null;

	private Controller controller;
	private Model model;
	private MainModule mainModule;
	private JTextField newPasswordTextField;
	private JTextField confirmNewPasswordTextField;

	public EditAccount(MainModule mainModule, Controller controller, Model model) {
		this.model = model;
		this.mainModule = mainModule;
		this.controller = controller;
	}

	/**
	 * Initialize the contents of the GUI so that it can be called from other GUI
	 * pages.
	 */
	public void initializeEditAccount() {
		// Creates a frame and adds a NavBar
		try {
			frame = new JFrame();
			if (mainModule.userState == USER.GUEST) {
				navForGuest.addGuestNav(frame, mainModule);
			} else {
				navForHost.addHostNav(frame, mainModule);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		// Creates the main editAccount Panel to display GUI objects
		JPanel editACcountPanel = new JPanel();
		editACcountPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(editACcountPanel, BorderLayout.CENTER);
		editACcountPanel.setLayout(null);

		JLabel editAccountLabel = new JLabel("Edit Account");
		editAccountLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editAccountLabel.setBounds(222, 53, 183, 57);
		editACcountPanel.add(editAccountLabel);

		// Gets a connection with the database
		try {
			connection = ConnectionManager.getConnection();

			// Gets the preexisting information of the account being edited from the
			// database.
			String selectAccountRecord = "SELECT title, firstName," + " surname, password from Account where email = ?";

			PreparedStatement selectingAccountValues = connection.prepareStatement(selectAccountRecord);
			selectingAccountValues.setString(1, model.getEmail());
			ResultSet rs = selectingAccountValues.executeQuery();

			// Creates variables using account information from account primed for editing
			while (rs.next()) {
				title = rs.getString("title");
				firstName = rs.getString("firstName");
				surname = rs.getString("surname");
				password = rs.getString("password");
			}
			connection.close();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		// Objects for GUI are added to panel/frame
		JLabel TitleLabel = new JLabel("Title:");
		TitleLabel.setBounds(103, 174, 132, 34);
		editACcountPanel.add(TitleLabel);

		String titles[] = { "Mr", "Mrs", "Miss", "Ms", "Dr" };
		for (int i = 0; i < titles.length; i++) {
			if (titles[i].equals(title)) {
				String temp = titles[0];
				titles[0] = titles[i];
				titles[i] = temp;
			}
		}
		JComboBox titleComboBox = new JComboBox(titles);
		JLabel firstNameLabel = new JLabel("First Name:");
		firstNameLabel.setBounds(103, 242, 93, 34);
		editACcountPanel.add(firstNameLabel);

		firstNameTextField = new JTextField(firstName);
		firstNameTextField.setColumns(10);
		firstNameTextField.setBounds(245, 242, 274, 34);
		editACcountPanel.add(firstNameTextField);

		JLabel surnameLabel = new JLabel("Surname:");
		surnameLabel.setBounds(103, 308, 93, 34);
		editACcountPanel.add(surnameLabel);

		surnameTextField = new JTextField(surname);
		surnameTextField.setColumns(10);
		surnameTextField.setBounds(245, 308, 274, 34);
		editACcountPanel.add(surnameTextField);

		JLabel passwordLabel = new JLabel("Enter Current Password:");
		passwordLabel.setBounds(103, 362, 132, 34);
		editACcountPanel.add(passwordLabel);

		passwordTextField = new JTextField(password);
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(245, 362, 274, 34);
		editACcountPanel.add(passwordTextField);

		// Button to save the changes of the edited account and update the database.
		addEditPropertyButton = new JButton("Save");
		addEditPropertyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean validateFirstNameInput = validateName(firstNameTextField.getText());
				boolean validateSurnameInput = validateName(surnameTextField.getText());
				// Checks if the information in the text fields is valid
				if (validateFirstNameInput && validateSurnameInput) {
					// Sets the values of the edited Account using the information in
					// the textFields
					model.setTitle(titleComboBox.getSelectedItem().toString());
					model.setFirstName(firstNameTextField.getText());
					model.setSurname(surnameTextField.getText());
					model.setPassword(passwordTextField.getText());
					// Calls the function to edit the Account
					addEditAccountDetails();
					frame.dispose();

					if (mainModule.userState == USER.GUEST) {
						mainModule.currentState = STATE.GUEST_ACCOUNT;
						mainModule.userState = USER.GUEST;
					} else {
						mainModule.currentState = STATE.HOST_ACCOUNT;
						mainModule.userState = USER.HOST;
					}
					MainModule.controller.drawNewView();
				} else {
					displayError();
				}
			}
		});
		addEditPropertyButton.setBounds(369, 576, 113, 23);
		editACcountPanel.add(addEditPropertyButton);

		// Button to take you back to previous GUI page
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(22, 75, 91, 23);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mainModule.userState == USER.HOST) {
					mainModule.currentState = STATE.HOST_ACCOUNT;
					mainModule.userState = USER.HOST;
				} else if (mainModule.userState == USER.GUEST) {
					mainModule.currentState = STATE.GUEST_ACCOUNT;
					mainModule.userState = USER.GUEST;
				}
				MainModule.controller.drawNewView();
				model.setEditPropertyPostcode(null);
				frame.dispose();
			}
		});
		editACcountPanel.add(backButton);

		// Button to delete Account and remove information from database
		JButton deleteAccountButton = new JButton("Delete Account");
		deleteAccountButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		deleteAccountButton.setBounds(211, 576, 113, 23);
		editACcountPanel.add(deleteAccountButton);
		deleteAccountButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteAccount();
				frame.dispose();
				mainModule.currentState = STATE.HOMEPAGE;
				mainModule.userState = USER.ENQUIRER;
				MainModule.controller.drawNewView();
			}
		}

		);

		titleComboBox.setBounds(245, 180, 276, 23);
		editACcountPanel.add(titleComboBox);
		
		JLabel newPasswordLabel = new JLabel("New Password:");
		newPasswordLabel.setBounds(103, 419, 93, 34);
		editACcountPanel.add(newPasswordLabel);
		
		newPasswordTextField = new JTextField((String) null);
		newPasswordTextField.setColumns(10);
		newPasswordTextField.setBounds(245, 419, 274, 34);
		editACcountPanel.add(newPasswordTextField);
		
		JLabel confirmNewPasswordLabel = new JLabel("Confirm New Password:");
		confirmNewPasswordLabel.setBounds(103, 483, 132, 34);
		editACcountPanel.add(confirmNewPasswordLabel);
		
		confirmNewPasswordTextField = new JTextField((String) null);
		confirmNewPasswordTextField.setColumns(10);
		confirmNewPasswordTextField.setBounds(245, 483, 274, 34);
		editACcountPanel.add(confirmNewPasswordTextField);

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	// Function to validate the user name using regex
	public boolean validateName(String name) {
		if (!name.matches("[a-zA-Z]*") || name.matches("")) {
			return false;
		} else {
			return true;
		}
	}

	// Displays error when user input invalid information into the textFields
	public void displayError() {
		JOptionPane.showMessageDialog(this, "Invalid input, please try again.");
	}

	// Updates the database with the new information for the account
	public void addEditAccountDetails() {
		try {
			connection = ConnectionManager.getConnection();
//			String updateAccountQuery = "UPDATE Account set title = ?, firstName = ?, surname = ?, password = AES_ENCRYPT(?,'key') where email = ?";

			// Updates account with new user information in the database
			String updateAccountQuery = "UPDATE Account set title = ?, firstName = ?, surname = ?, password = ? where email = ?";

			PreparedStatement updateAccount = connection.prepareStatement(updateAccountQuery);
			updateAccount.setString(1, model.getTitle());
			updateAccount.setString(2, model.getFirstName());
			updateAccount.setString(3, model.getSurname());
			updateAccount.setString(4, model.getPassword());
			updateAccount.setString(5, model.getEmail());

			int i = updateAccount.executeUpdate();
			if (i > 0) {
				frame.dispose();
				JOptionPane.showMessageDialog(this, "Account details updated.");

				// remove later
			}
			connection.close();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}

	// Function for deleting account and removing all related information from the
	// database
	public void deleteAccount() {
		try {
			connection = ConnectionManager.getConnection();
			String getHostId = "SELECT host_id from HostAccount WHERE email = ?";
			PreparedStatement stmt = connection.prepareStatement(getHostId);
			int host_id = 0;
			stmt.setString(1, model.getEmail());
			ResultSet rs_host = stmt.executeQuery();
			while (rs_host.next()) {
				host_id = rs_host.getInt("host_id");
			}
			// Gets all of the properties that the account owns if it is a host
			String selectPropertyRecord = "SELECT property_id,address_id from Property where host_id = ?";

			PreparedStatement selectPropertyValues = connection.prepareStatement(selectPropertyRecord,
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			int address = 0;
			selectPropertyValues.setInt(1, host_id);
			ResultSet rs = selectPropertyValues.executeQuery();

			while (rs.next()) {
				model.setPropertyId(rs.getInt("property_id"));
				address = rs.getInt("address_id");
				// Gets all the facilities related to the accounts properties
				String selectFacilitiesRecord = "SELECT facilities_id,outdoors_id, utility_id, living_id, bathing_id, sleeping_id, kitchen_id FROM Facilities WHERE property_id = ? ";

				PreparedStatement selectFacilitiesValues = connection.prepareStatement(selectFacilitiesRecord,
						ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
				selectFacilitiesValues.setInt(1, model.getPropertyId());
				ResultSet rs_facilities = selectFacilitiesValues.executeQuery();

				int outdoors = 0, utility = 0, living = 0, bathing = 0, sleeping = 0, kitchen = 0;
				while (rs_facilities.next()) {
					outdoors = rs_facilities.getInt("outdoors_id");
					utility = rs_facilities.getInt("utility_id");
					living = rs_facilities.getInt("living_id");
					bathing = rs_facilities.getInt("bathing_id");
					sleeping = rs_facilities.getInt("sleeping_id");
					kitchen = rs_facilities.getInt("kitchen_id");
					rs_facilities.deleteRow();

					// Deletes the information out of the facilities related to the account
					String deleteSleeping_BedTypeQuery = "DELETE FROM Sleeping_BedType WHERE sleeping_id = ?";
					PreparedStatement deleteSleeping_BedType = connection.prepareStatement(deleteSleeping_BedTypeQuery);
					deleteSleeping_BedType.setInt(1, sleeping);
					int e = deleteSleeping_BedType.executeUpdate();
					if (e > 0) {
					}
					String deleteSleepingQuery = "DELETE FROM Sleeping WHERE sleeping_id = ?";
					PreparedStatement deleteSleeping = connection.prepareStatement(deleteSleepingQuery);
					deleteSleeping.setInt(1, sleeping);
					int d = deleteSleeping.executeUpdate();
					if (d > 0) {
					}
					String deleteOutdoorsQuery = "DELETE FROM Outdoors WHERE outdoors_id = ?";
					PreparedStatement deleteOutdoors = connection.prepareStatement(deleteOutdoorsQuery);
					deleteOutdoors.setInt(1, outdoors);
					int f = deleteOutdoors.executeUpdate();
					if (f > 0) {
					}
					String deleteKitchenQuery = "DELETE FROM Kitchen WHERE kitchen_id = ?";
					PreparedStatement deleteKitchen = connection.prepareStatement(deleteKitchenQuery);
					deleteKitchen.setInt(1, kitchen);
					int g = deleteKitchen.executeUpdate();
					if (g > 0) {
					}
					String deleteLivingQuery = "DELETE FROM Living WHERE living_id = ?";
					PreparedStatement deleteLiving = connection.prepareStatement(deleteLivingQuery);
					deleteLiving.setInt(1, living);
					int h = deleteLiving.executeUpdate();
					if (h > 0) {
					}
					String deleteUtilityQuery = "DELETE FROM Utility WHERE utility_id = ?";
					PreparedStatement deleteUtility = connection.prepareStatement(deleteUtilityQuery);
					deleteUtility.setInt(1, utility);
					int i = deleteUtility.executeUpdate();
					if (i > 0) {
					}
					String deleteBathing_BathTypeQuery = "DELETE FROM Bathing_BathType WHERE bathing_id = ?";
					PreparedStatement deleteBathing_BathType = connection.prepareStatement(deleteBathing_BathTypeQuery);
					deleteBathing_BathType.setInt(1, bathing);
					int j = deleteBathing_BathType.executeUpdate();
					if (j > 0) {
					}
					String deleteBathingQuery = "DELETE FROM Bathing WHERE bathing_id = ?";
					PreparedStatement deleteBathing = connection.prepareStatement(deleteBathingQuery);
					deleteBathing.setInt(1, bathing);
					int k = deleteBathing.executeUpdate();
					if (k > 0) {
					}
				}
				// Deletes the account from the address database
				rs.deleteRow();
				String deleteAddressQuery = "DELETE FROM Address WHERE address_id = ?";
				PreparedStatement deleteAddress = connection.prepareStatement(deleteAddressQuery);
				deleteAddress.setInt(1, address);
				int m = deleteAddress.executeUpdate();
				if (m > 0) {
				}
			}
			// Deletes account out of the address table
			String deleteAccountAddressQuery = "DELETE FROM Address WHERE houseNameNumber = ? AND postcode = ?";
			PreparedStatement deleteAccountAddress = connection.prepareStatement(deleteAccountAddressQuery);
			deleteAccountAddress.setString(1, model.getHouseNameNum());
			deleteAccountAddress.setString(2, model.getPostcode());
			int l = deleteAccountAddress.executeUpdate();
			if (l > 0) {
			}
			// Deletes account out of the hostAccount table
			String deleteHostAccountQuery = "DELETE FROM HostAccount WHERE  email = ?";
			PreparedStatement deleteHostAccount = connection.prepareStatement(deleteHostAccountQuery);
			deleteHostAccount.setString(1, model.getEmail());
			int j = deleteHostAccount.executeUpdate();
			if (j > 0) {
			}
			// Deletes account out of the guestAccount table
			String deleteGuestAccountQuery = "DELETE FROM GuestAccount WHERE  email = ?";
			PreparedStatement deleteGuestAccount = connection.prepareStatement(deleteGuestAccountQuery);
			deleteGuestAccount.setString(1, model.getEmail());
			int k = deleteGuestAccount.executeUpdate();
			if (k > 0) {
			}
			// Deletes account out of the account table
			String deleteAccountQuery = "DELETE FROM Account WHERE email = ?";
			PreparedStatement deleteAccount = connection.prepareStatement(deleteAccountQuery);
			deleteAccount.setString(1, model.getEmail());
			int i = deleteAccount.executeUpdate();
			if (i > 0) {
				frame.dispose();
				JOptionPane.showMessageDialog(this, "Account deleted.");
			}
			connection.close();

		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
}
//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW