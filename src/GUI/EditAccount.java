package GUI;

import java.awt.EventQueue;
import javax.swing.*;

import Controller.Controller;
import GUI.ConnectionManager;
import GUI.Login;
import GUI.MainModule;
import GUI.MainModule.EDITPROPERTY;
import GUI.MainModule.STATE;
import GUI.MainModule.USER;
import HostGUI.NavHost;
import GuestGUI.NavGuest;
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

	/**
	 * Create the application.
	 */

	private Controller controller;
	private Model model;
	private MainModule mainModule;

	public EditAccount(MainModule mainModule, Controller controller, Model model) {
		// initializeEditAccount();
		this.model = model;
		this.mainModule = mainModule;
		this.controller = controller;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditAccount() {
		System.out.println("0");

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
		System.out.println("email" + model.getEmail());

		JPanel editACcountPanel = new JPanel();
		editACcountPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(editACcountPanel, BorderLayout.CENTER);
		editACcountPanel.setLayout(null);

		JLabel editAccountLabel = new JLabel("Edit Account");
		editAccountLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editAccountLabel.setBounds(222, 53, 183, 57);
		editACcountPanel.add(editAccountLabel);

		try {
			connection = ConnectionManager.getConnection();

			// String selectAccountRecord = "SELECT title, firstName, surname,
			// AES_DECRYPT(password,'key') as decrypt from Account where email = ?";
			String selectAccountRecord = "SELECT title, firstName, surname, password from Account where email = ?";

			PreparedStatement selectingAccountValues = connection.prepareStatement(selectAccountRecord);

			selectingAccountValues.setString(1, model.getEmail());
			ResultSet rs = selectingAccountValues.executeQuery();

			while (rs.next()) {
				title = rs.getString("title");
				firstName = rs.getString("firstName");
				surname = rs.getString("surname");
				password = rs.getString("password");
				// password = rs.getString("decrypt");
			}
			connection.close();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		JLabel TitleLabel = new JLabel("Title:");
		TitleLabel.setBounds(104, 268, 93, 34);
		editACcountPanel.add(TitleLabel);

		String titles[] = { "Mr", "Mrs", "Miss", "Ms", "Dr" };

		System.out.println(titles[2] + title);
		for (int i = 0; i < titles.length; i++) {
			if (titles[i].equals(title)) {
				System.out.println(titles[i]);
				String temp = titles[0];
				titles[0] = titles[i];
				titles[i] = temp;
			}
		}

		JComboBox titleComboBox = new JComboBox(titles);
		JLabel firstNameLabel = new JLabel("First Name:");
		firstNameLabel.setBounds(104, 336, 93, 34);
		editACcountPanel.add(firstNameLabel);

		firstNameTextField = new JTextField(firstName);
		firstNameTextField.setColumns(10);
		firstNameTextField.setBounds(207, 336, 274, 34);
		editACcountPanel.add(firstNameTextField);

		JLabel surnameLabel = new JLabel("Surname:");
		surnameLabel.setBounds(104, 402, 93, 34);
		editACcountPanel.add(surnameLabel);

		surnameTextField = new JTextField(surname);
		surnameTextField.setColumns(10);
		surnameTextField.setBounds(207, 402, 274, 34);
		editACcountPanel.add(surnameTextField);

		System.out.println("1");
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(104, 456, 93, 34);
		editACcountPanel.add(passwordLabel);

		passwordTextField = new JTextField(password);
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(207, 456, 274, 34);
		editACcountPanel.add(passwordTextField);

		addEditPropertyButton = new JButton("Save");
		addEditPropertyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean validateFirstNameInput = validateName(firstNameTextField.getText());
				boolean validateSurnameInput = validateName(surnameTextField.getText());
				if (validateFirstNameInput && validateSurnameInput) {
					model.setTitle(titleComboBox.getSelectedItem().toString());
					model.setFirstName(firstNameTextField.getText());
					model.setSurname(surnameTextField.getText());
					model.setPassword(passwordTextField.getText());
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
		addEditPropertyButton.setBounds(368, 548, 113, 23);
		editACcountPanel.add(addEditPropertyButton);

		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(22, 75, 91, 23);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Homepage sp = new Homepage();

				if (mainModule.userState == USER.HOST) {
					mainModule.currentState = STATE.HOST_ACCOUNT;
					mainModule.userState = USER.HOST;
				} else if (mainModule.userState == USER.GUEST) {
					mainModule.currentState = STATE.GUEST_ACCOUNT;
					mainModule.userState = USER.GUEST;
				}
				MainModule.controller.drawNewView();
//				close();
				model.setEditPropertyPostcode(null);
				frame.dispose();

			}
		});
		editACcountPanel.add(backButton);

		JButton deleteAccountButton = new JButton("Delete Account");
		deleteAccountButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		deleteAccountButton.setBounds(207, 548, 113, 23);
		editACcountPanel.add(deleteAccountButton);
		deleteAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAccount();
				mainModule.currentState = STATE.HOMEPAGE;
				mainModule.userState = USER.ENQUIRER;
				MainModule.controller.drawNewView();
			}
		}

		);

		titleComboBox.setBounds(207, 274, 276, 23);
		editACcountPanel.add(titleComboBox);

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		System.out.println("3");
	}

	public boolean validateName(String name) {
		if (!name.matches("[a-zA-Z]*") || name.matches("")) {
			System.out.println(name + " IS NOT VALID NAME");
			return false;
		} else {
			System.out.println(name + " IS VALID");
			return true;
		}
	}

	public void displayError() {
		JOptionPane.showMessageDialog(this, "Invalid input, please try again.");
	}

	public void addEditAccountDetails() {
		try {
			connection = ConnectionManager.getConnection();
//			String updateAccountQuery = "UPDATE Account set title = ?, firstName = ?, surname = ?, password = AES_ENCRYPT(?,'key') where email = ?";
			String updateAccountQuery = "UPDATE Account set title = ?, firstName = ?, surname = ?, password = ? where email = ?";

			PreparedStatement updateAccount = connection.prepareStatement(updateAccountQuery);
			updateAccount.setString(1, model.getTitle());
			updateAccount.setString(2, model.getFirstName());
			updateAccount.setString(3, model.getSurname());
			updateAccount.setString(4, model.getPassword());
			updateAccount.setString(5, model.getEmail());

			int i = updateAccount.executeUpdate();
			if (i > 0) {
				System.out.println(this);
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

	public void deleteAccount() {
		try {
			connection = ConnectionManager.getConnection();

			String deleteOutdoorsQuery = "DELETE FROM Outdoors WHERE (SELECT outdoors_id FROM Property WHERE (SELECT property_id FROM Property WHERE houseNameNumber = ? AND postcode = ?))";
			PreparedStatement deleteOutdoors = connection.prepareStatement(deleteOutdoorsQuery);
			deleteOutdoors.setString(1, model.getHouseNameNum());
			deleteOutdoors.setString(2, model.getPostcode());
			int z = deleteOutdoors.executeUpdate();
			if (z > 0) {
				System.out.println(this);
				// remove later
			}

			String deleteKitchenQuery = "DELETE FROM Kitchen WHERE (SELECT kitchen_id FROM Property WHERE (SELECT property_id FROM Property WHERE houseNameNumber = ? AND postcode = ?))";
			PreparedStatement deleteKitchen = connection.prepareStatement(deleteKitchenQuery);
			deleteKitchen.setString(1, model.getHouseNameNum());
			deleteKitchen.setString(2, model.getPostcode());
			int a = deleteKitchen.executeUpdate();
			if (a > 0) {
				System.out.println(this);
				// remove later
			}

			String deleteLivingQuery = "DELETE FROM Living WHERE (SELECT living_id FROM Property WHERE (SELECT property_id FROM Property WHERE houseNameNumber = ? AND postcode = ?))";
			PreparedStatement deleteLiving = connection.prepareStatement(deleteLivingQuery);
			deleteLiving.setString(1, model.getHouseNameNum());
			deleteLiving.setString(2, model.getPostcode());
			int b = deleteKitchen.executeUpdate();
			if (b > 0) {
				System.out.println(this);
				// remove later
			}

			String deleteUtilityQuery = "DELETE FROM Utility WHERE (SELECT utility_id FROM Property WHERE (SELECT property_id FROM Property WHERE houseNameNumber = ? AND postcode = ?))";
			PreparedStatement deleteUtility = connection.prepareStatement(deleteUtilityQuery);
			deleteUtility.setString(1, model.getHouseNameNum());
			deleteUtility.setString(2, model.getPostcode());
			int c = deleteUtility.executeUpdate();
			if (c > 0) {
				System.out.println(this);
				// remove later
			}

			String deleteSleepingQuery = "DELETE FROM Sleeping WHERE (SELECT sleeping_id FROM Property WHERE (SELECT property_id FROM Property WHERE houseNameNumber = ? AND postcode = ?))";
			PreparedStatement deleteSleeping = connection.prepareStatement(deleteSleepingQuery);
			deleteSleeping.setString(1, model.getHouseNameNum());
			deleteSleeping.setString(2, model.getPostcode());
			int d = deleteSleeping.executeUpdate();
			if (d > 0) {
				System.out.println(this);
				// remove later
			}

			String deleteBathingQuery = "DELETE FROM Bathing WHERE (SELECT bathing_id FROM Property WHERE (SELECT property_id FROM Property WHERE houseNameNumber = ? AND postcode = ?))";
			PreparedStatement deleteBathing = connection.prepareStatement(deleteBathingQuery);
			deleteBathing.setString(1, model.getHouseNameNum());
			deleteBathing.setString(2, model.getPostcode());
			int e = deleteBathing.executeUpdate();
			if (e > 0) {
				System.out.println(this);
				// remove later
			}

			String deleteFacilitiesQuery = "DELETE FROM Facilities WHERE (SELECT property_id FROM Property WHERE houseNameNumber = ? AND postcode = ?)";
			PreparedStatement deleteFacilities = connection.prepareStatement(deleteFacilitiesQuery);
			deleteFacilities.setString(1, model.getHouseNameNum());
			deleteFacilities.setString(2, model.getPostcode());
			int y = deleteFacilities.executeUpdate();
			if (y > 0) {
				System.out.println(this);
				// remove later
			}
			String deletePropertyQuery = "DELETE FROM Property WHERE houseNameNumber = ? AND postcode = ?";
			PreparedStatement deleteProperty = connection.prepareStatement(deletePropertyQuery);
			deleteProperty.setString(1, model.getHouseNameNum());
			deleteProperty.setString(2, model.getPostcode());
			int x = deleteProperty.executeUpdate();
			if (x > 0) {
				System.out.println(this);
				// remove later
			}

			String deleteAddressQuery = "DELETE FROM Address WHERE houseNameNumber = ? AND postcode = ?";
			PreparedStatement deleteAddress = connection.prepareStatement(deleteAddressQuery);
			deleteAddress.setString(1, model.getHouseNameNum());
			deleteAddress.setString(2, model.getPostcode());
			int l = deleteAddress.executeUpdate();
			if (l > 0) {
				System.out.println(this);
				// remove later
			}
			// String deleteHostAccountQuery = "DELETE FROM HostAccount WHERE EXISTS (SELECT
			// * FROM Account WHERE Account.email = HostAccount.email and email = ?)";
			String deleteHostAccountQuery = "DELETE FROM HostAccount WHERE  email = ?";
			PreparedStatement deleteHostAccount = connection.prepareStatement(deleteHostAccountQuery);
			deleteHostAccount.setString(1, model.getEmail());
			System.out.println("do u work");
			int j = deleteHostAccount.executeUpdate();
			if (j > 0) {
				System.out.println(this);
				// remove later
			}
			String deleteGuestAccountQuery = "DELETE FROM GuestAccount WHERE  email = ?";
			PreparedStatement deleteGuestAccount = connection.prepareStatement(deleteGuestAccountQuery);
			deleteGuestAccount.setString(1, model.getEmail());
			int k = deleteGuestAccount.executeUpdate();
			if (k > 0) {
				System.out.println(this);
				// remove later
			}
			String deleteAccountQuery = "DELETE FROM Account WHERE email = ?";
			PreparedStatement deleteAccount = connection.prepareStatement(deleteAccountQuery);
			deleteAccount.setString(1, model.getEmail());
			int i = deleteAccount.executeUpdate();
			if (i > 0) {
				System.out.println(this);
				frame.dispose();
				JOptionPane.showMessageDialog(this, "Account deleted.");

				// remove later
			}
			connection.close();

		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW