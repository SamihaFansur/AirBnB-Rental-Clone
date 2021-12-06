package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
	private JPasswordField passwordTextField;
	private JButton addEditPropertyButton;
	private JComboBox titleComboBox;

	private String title, firstName, surname, password;
	Connection connection = null;

	private Controller controller;
	private Model model;
	private MainModule mainModule;
	private JPasswordField newPasswordTextField;
	private JPasswordField confirmNewPasswordTextField;

	private boolean validateFirstNameInput = false;
	private boolean validateSurnameInput = false;
	private boolean validateOldPassword = false;
	private boolean checkNewPasswordFieldMatch = false;
	private boolean checkNewPasswordStrength = false;

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
			String selectAccountRecord = "SELECT title, firstName, surname from Account where email = ?";

			PreparedStatement selectingAccountValues = connection.prepareStatement(selectAccountRecord);
			selectingAccountValues.setString(1, model.getEmail());
			ResultSet rs = selectingAccountValues.executeQuery();

			// Creates variables using account information from account primed for editing
			while (rs.next()) {
				title = rs.getString("title");
				firstName = rs.getString("firstName");
				surname = rs.getString("surname");
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

		passwordTextField = new JPasswordField(password);
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(245, 362, 274, 34);
		editACcountPanel.add(passwordTextField);

		// Button to save the changes of the edited account and update the database.
		addEditPropertyButton = new JButton("Save");
		addEditPropertyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				validateFirstNameInput = validateName(firstNameTextField.getText());
				validateSurnameInput = validateName(surnameTextField.getText());
				validateOldPassword = checkOldPassword(passwordTextField.getText());
				checkNewPasswordFieldMatch = false;
				checkNewPasswordStrength = false;

				if ((newPasswordTextField.getText()).matches(confirmNewPasswordTextField.getText())) {
					checkNewPasswordFieldMatch = true;
					if (checkNewPasswordStrength(newPasswordTextField.getText())) {
						checkNewPasswordStrength = true;
					}
				}
				// Checks if the information in the text fields is valid
				if (validateFirstNameInput && validateSurnameInput && validateOldPassword && checkNewPasswordFieldMatch
						&& checkNewPasswordStrength) {
					// Sets the values of the edited Account using the information in
					// the textFields

					model.setTitle(titleComboBox.getSelectedItem().toString());
					model.setFirstName(firstNameTextField.getText());
					model.setSurname(surnameTextField.getText());
					model.setPassword(newPasswordTextField.getText());
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

		newPasswordTextField = new JPasswordField((String) null);
		newPasswordTextField.setColumns(10);
		newPasswordTextField.setBounds(245, 419, 274, 34);
		editACcountPanel.add(newPasswordTextField);

		JLabel confirmNewPasswordLabel = new JLabel("Confirm New Password:");
		confirmNewPasswordLabel.setBounds(103, 483, 132, 34);
		editACcountPanel.add(confirmNewPasswordLabel);

		confirmNewPasswordTextField = new JPasswordField((String) null);
		confirmNewPasswordTextField.setColumns(10);
		confirmNewPasswordTextField.setBounds(245, 483, 274, 34);
		editACcountPanel.add(confirmNewPasswordTextField);

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
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
		ArrayList<String> arlist = new ArrayList<>();
		if (!validateFirstNameInput) {
			arlist.add(" First name input is invalid");
		}
		if (!validateSurnameInput) {
			arlist.add(" Surname input is invalid");
		}
		if (!validateOldPassword) {
			arlist.add("Old password is incorrect");
		}
		if (!checkNewPasswordFieldMatch) {
			arlist.add("New password fields mismatch");
		}
		if (!checkNewPasswordStrength) {
			arlist.add(
					" Password is not strong enough, it has to contain at least 1 digit, 1 lowercase, 1 uppercase letter, a special character out of ~!@#$%^&*()_-"
							+ "and has more than 8 characters.");
		}
		JOptionPane.showMessageDialog(this, arlist);
	}

	public boolean checkOldPassword(String password) {
		boolean passwordCorrect = false;
		try {
			String oldpasswordsalt = "";
			connection = ConnectionManager.getConnection();
			String getSaltQuery = "SELECT salt from Account where email = ?";
			PreparedStatement saltQuery = connection.prepareStatement(getSaltQuery);
			saltQuery.setString(1, model.getEmail());
			ResultSet rsSalt = saltQuery.executeQuery();
			while (rsSalt.next()) {
				oldpasswordsalt = rsSalt.getString("salt");
			}
			String securePassword = Password.get_SHA_512_SecurePassword(passwordTextField.getText(), oldpasswordsalt);
			String query = "Select password from Account where email=?";
			PreparedStatement passwordQuery = connection.prepareStatement(query);
			passwordQuery.setString(1, model.getEmail());
			ResultSet rs = passwordQuery.executeQuery();
			if (rs.next()) {
				if (securePassword.matches(rs.getString("password"))) {
					passwordCorrect = true;
				} else {
					passwordCorrect = false;
				}

			}
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		return passwordCorrect;

	}

	// Updates the database with the new information for the account
	public void addEditAccountDetails() {
		try {
			connection = ConnectionManager.getConnection();

			String salt = Password.getSalt();
			String securePassword = Password.get_SHA_512_SecurePassword(model.getPassword(), salt);

			// Updates account with new user information in the database
			String updateAccountQuery = "UPDATE Account SET title = ?, firstName = ?, surname = ?, password = ?, salt = ? WHERE email = ?";

			PreparedStatement updateAccount = connection.prepareStatement(updateAccountQuery);
			updateAccount.setString(1, model.getTitle());
			updateAccount.setString(2, model.getFirstName());
			updateAccount.setString(3, model.getSurname());
			updateAccount.setString(4, securePassword);
			updateAccount.setString(5, salt);
			updateAccount.setString(6, model.getEmail());

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

	private static boolean checkNewPasswordStrength(String password) {
		// https://www.javacodeexamples.com/check-password-strength-in-java-example/668
		int passwordRating = 0;

		if (password.length() < 8) {
			passwordRating = 0;
		} else {
			passwordRating += 1;
		}
		/*
		 * if password contains 1 digit add 1 to rating
		 */
		if (password.matches("(?=.*[0-9]).*"))
			passwordRating += 1;

		// if password contains 1 lower case letter, add 1 to rating
		if (password.matches("(?=.*[a-z]).*"))
			passwordRating += 1;

		/*
		 * if password contains 1 upper case letter then add 1 to rating.
		 */

		if (password.matches("(?=.*[A-Z]).*"))
			passwordRating += 1;

		/*
		 * if password contains 1 special character add 1 to rating.
		 */
		if (password.matches("(?=.*[~!@#$%^&*()_-]).*"))
			passwordRating += 1;

		// if passwordRating is less than 5, then it is not strong enough
		if (passwordRating < 5) {
			return false;
		} else {
			return true;
		}
	}

	// Function for deleting account and removing all related information from the
	// database
	public void deleteAccount() {
//		if (mainModule.userState == USER.HOST) {
			try {
				connection = ConnectionManager.getConnection();
				String getHostId = "SELECT host_id from HostAccount WHERE email = ?";
				PreparedStatement stmt = connection.prepareStatement(getHostId);

				stmt.setString(1, model.getEmail());
				ResultSet rs_host = stmt.executeQuery();
				while (rs_host.next()) {
					model.setHostId(rs_host.getInt("host_id"));
				}
				String getGuestId = "SELECT guest_id from GuestAccount WHERE email = ?";
				PreparedStatement stmt_guest = connection.prepareStatement(getGuestId);
				stmt_guest.setString(1, model.getEmail());
				ResultSet rs_guest = stmt_guest.executeQuery();
				while (rs_guest.next()) {
					model.setGuestId(rs_guest.getInt("guest_id"));
				}
				// Gets all of the properties that the account owns if it is a host
				String selectPropertyRecord = "SELECT property_id,address_id from Property where host_id = ?";

				PreparedStatement selectPropertyValues = connection.prepareStatement(selectPropertyRecord,
						ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
				int address = 0;
				selectPropertyValues.setInt(1, model.getHostId());
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
						PreparedStatement deleteSleeping_BedType = connection
								.prepareStatement(deleteSleeping_BedTypeQuery);
						deleteSleeping_BedType.setInt(1, sleeping);
						deleteSleeping_BedType.executeUpdate();

						String deleteSleepingQuery = "DELETE FROM Sleeping WHERE sleeping_id = ?";
						PreparedStatement deleteSleeping = connection.prepareStatement(deleteSleepingQuery);
						deleteSleeping.setInt(1, sleeping);
						deleteSleeping.executeUpdate();

						String deleteOutdoorsQuery = "DELETE FROM Outdoors WHERE outdoors_id = ?";
						PreparedStatement deleteOutdoors = connection.prepareStatement(deleteOutdoorsQuery);
						deleteOutdoors.setInt(1, outdoors);
						deleteOutdoors.executeUpdate();

						String deleteKitchenQuery = "DELETE FROM Kitchen WHERE kitchen_id = ?";
						PreparedStatement deleteKitchen = connection.prepareStatement(deleteKitchenQuery);
						deleteKitchen.setInt(1, kitchen);
						deleteKitchen.executeUpdate();

						String deleteLivingQuery = "DELETE FROM Living WHERE living_id = ?";
						PreparedStatement deleteLiving = connection.prepareStatement(deleteLivingQuery);
						deleteLiving.setInt(1, living);
						deleteLiving.executeUpdate();

						String deleteUtilityQuery = "DELETE FROM Utility WHERE utility_id = ?";
						PreparedStatement deleteUtility = connection.prepareStatement(deleteUtilityQuery);
						deleteUtility.setInt(1, utility);
						deleteUtility.executeUpdate();

						String deleteBathing_BathTypeQuery = "DELETE FROM Bathing_BathType WHERE bathing_id = ?";
						PreparedStatement deleteBathing_BathType = connection
								.prepareStatement(deleteBathing_BathTypeQuery);
						deleteBathing_BathType.setInt(1, bathing);
						deleteBathing_BathType.executeUpdate();

						String deleteBathingQuery = "DELETE FROM Bathing WHERE bathing_id = ?";
						PreparedStatement deleteBathing = connection.prepareStatement(deleteBathingQuery);
						deleteBathing.setInt(1, bathing);
						deleteBathing.executeUpdate();

					}
				
					// Deletes the account from the address database

					String deleteChargeBandsQuery = "DELETE FROM ChargeBands WHERE property_id = ?";
					PreparedStatement deleteChargeBands = connection.prepareStatement(deleteChargeBandsQuery);
					deleteChargeBands.setInt(1, model.getPropertyId());
					deleteChargeBands.executeUpdate();

					String deleteReviewsQuery = "DELETE FROM Review WHERE property_id = ?";
					PreparedStatement deleteReviews = connection.prepareStatement(deleteReviewsQuery);
					deleteReviews.setInt(1, model.getPropertyId());
					deleteReviews.executeUpdate();

					String deleteBookingQuery = "DELETE FROM Booking WHERE property_id = ?";
					PreparedStatement deleteBooking = connection.prepareStatement(deleteBookingQuery);
					deleteBooking.setInt(1, model.getPropertyId());
					deleteBooking.executeUpdate();
					rs.deleteRow();
					String deleteAddressQuery = "DELETE FROM Address WHERE address_id = ?";
					PreparedStatement deleteAddress = connection.prepareStatement(deleteAddressQuery);
					deleteAddress.setInt(1, address);
					deleteAddress.executeUpdate();
					
				}

				String deleteBookingGuestQuery = "DELETE FROM Booking WHERE guest_id = ? ";
				PreparedStatement deleteBookingGuest = connection.prepareStatement(deleteBookingGuestQuery);
				deleteBookingGuest.setInt(1, model.getGuestId());
				deleteBookingGuest.executeUpdate();

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
	//	} else {
//			try {
//				
//			} catch (Exception e) {
//				
//			}
//		}
	}

}
//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW