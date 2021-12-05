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
import Model.Model;

public class Register extends JFrame {

	private JButton registerButton = new JButton("Register");
	private JPasswordField passwordTextField;
	private JTextField firstNameTextField;
	private JTextField surnameTextField;
	private JTextField streetNameTextField;
	private JTextField postcodeTextField;
	private JTextField houseNumberTextField;
	private JComboBox cityComboBox;
	private JTextField emailAddressTextField;
	private JTextField mobileNumberTextField;
	private JComboBox accountTypeComboBox;
	private JComboBox registerTitleComboBox;
	private JPanel registerPanel = new JPanel();
	private Model model;
	private Controller controller;
	private MainModule mainModule;
	private NavEnquirer navBeforeLogin = new NavEnquirer();
	private JFrame frame;

	Connection connection = null;

	/**
	 * Create the application.
	 */
	public Register(MainModule mainModule, Controller controller, Model model) {
		// initializeRegister();
		this.mainModule = mainModule;
		this.model = model;
		this.controller = controller;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	Boolean validateFirstNameInput = false;
	Boolean validateSurnameInput = false;
	Boolean validateEmailInput = false;
	Boolean validateMobileNumberInput = false;
	Boolean validateHouseNameNumberInput = false;
	Boolean validateStreetNameInput = false;
	Boolean validatePostcodeInput = false;
	Boolean emailAlreadyInDB = false;
	Boolean validatePasswordInput = false;

	public void initializeRegister() {
		mainModule.currentState = STATE.SELF_REGISTRATION;
		try {
			frame = new JFrame();
			navBeforeLogin.addNavBeforeLogin(frame, mainModule);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		registerPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(registerPanel, BorderLayout.CENTER);
		registerPanel.setLayout(null);

		JLabel registerLabel = new JLabel("Register");
		registerLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		registerLabel.setBounds(264, 11, 183, 57);
		registerPanel.add(registerLabel);

		JLabel registerTitleLabel = new JLabel("Title");
		registerTitleLabel.setBounds(82, 79, 118, 45);
		registerPanel.add(registerTitleLabel);

		String titles[] = { "Mr", "Mrs", "Miss", "Ms", "Dr" };
		registerTitleComboBox = new JComboBox(titles);
		registerTitleComboBox.setBounds(217, 93, 276, 23);
		registerPanel.add(registerTitleComboBox);

		JLabel firstNameLabel = new JLabel("First Name");
		firstNameLabel.setBounds(82, 124, 118, 45);
		registerPanel.add(firstNameLabel);

		firstNameTextField = new JTextField();
		firstNameTextField.setBounds(217, 135, 276, 23);
		registerPanel.add(firstNameTextField);
		firstNameTextField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Surname");
		lblNewLabel.setBounds(82, 158, 118, 45);
		registerPanel.add(lblNewLabel);

		surnameTextField = new JTextField();
		surnameTextField.setBounds(217, 169, 276, 23);
		registerPanel.add(surnameTextField);
		surnameTextField.setColumns(10);

		JLabel emailAddressLabel = new JLabel("Email Address");
		emailAddressLabel.setBounds(82, 195, 118, 45);
		registerPanel.add(emailAddressLabel);

		emailAddressTextField = new JTextField();
		emailAddressTextField.setColumns(10);
		emailAddressTextField.setBounds(217, 203, 276, 29);
		registerPanel.add(emailAddressTextField);

		JLabel mobileLabel = new JLabel("Mobile Number");
		mobileLabel.setBounds(82, 251, 125, 14);
		registerPanel.add(mobileLabel);

		mobileNumberTextField = new JTextField();
		mobileNumberTextField.setColumns(10);
		mobileNumberTextField.setBounds(217, 239, 276, 29);
		registerPanel.add(mobileNumberTextField);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(82, 289, 75, 14);
		registerPanel.add(passwordLabel);

		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(217, 283, 276, 26);
		registerPanel.add(passwordTextField);

		JLabel houseNumberLabel = new JLabel("House Name/Number");
		houseNumberLabel.setBounds(82, 329, 125, 14);
		registerPanel.add(houseNumberLabel);

		houseNumberTextField = new JTextField();
		houseNumberTextField.setBounds(217, 322, 276, 29);
		registerPanel.add(houseNumberTextField);

		JLabel streetNameLabel = new JLabel("Street Name");
		streetNameLabel.setBounds(82, 368, 125, 14);
		registerPanel.add(streetNameLabel);

		streetNameTextField = new JTextField();
		streetNameTextField.setBounds(217, 362, 276, 27);
		registerPanel.add(streetNameTextField);

		JLabel cityLabel = new JLabel("City/Town");
		cityLabel.setBounds(82, 408, 125, 14);
		registerPanel.add(cityLabel);

		String cityNames[] = { "Bath", "Birmingham", "Bradford", "Brighton and Hove", "Bristol", "Cambridge",
				"Canterbury", "Carlisle", "Chelmsford", "Chester", "Chichester", "Coventry", "Derby", "Durham", "Ely",
				"Exeter", "Gloucester", "Hereford", "Kingston upon Hull", "Lancaster", "Leeds", "Leicester",
				"Lichfield", "Lincoln", "Liverpool", "London", "Manchester", "Newcastle upon Tyne", "Norwich",
				"Nottingham", "Oxford", "Peterborough", "Plymouth", "Portsmouth", "Preston", "Ripon", "Salford",
				"Salisbury", "Sheffield", "Southampton", "St Albans", "Stoke-on-Trent", "Sunderland", "Truro",
				"Wakefield", "Wells", "Westminster", "Winchester", "Wolverhampton", "Worcester", "York" };
		cityComboBox = new JComboBox(cityNames);
		cityComboBox.setBounds(217, 400, 276, 31);
		registerPanel.add(cityComboBox);

		JLabel postcodeLabel = new JLabel("Postcode");
		postcodeLabel.setBounds(82, 452, 125, 14);
		registerPanel.add(postcodeLabel);

		postcodeTextField = new JTextField();
		postcodeTextField.setBounds(217, 447, 276, 23);
		registerPanel.add(postcodeTextField);

		JLabel accountTypeLabel = new JLabel("Register as");
		accountTypeLabel.setBounds(82, 489, 125, 14);
		registerPanel.add(accountTypeLabel);

		String accountTypes[] = { "Host", "Guest", "Both (Host & Guest)" };
		accountTypeComboBox = new JComboBox(accountTypes);
		accountTypeComboBox.setBounds(217, 489, 276, 23);
		registerPanel.add(accountTypeComboBox);

		registerButton.setBounds(321, 553, 91, 23);
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				validateFirstNameInput = validateName(firstNameTextField.getText());
				validateSurnameInput = validateName(surnameTextField.getText());

				// validating email input
				validateEmailInput = validateEmail(emailAddressTextField.getText());
				// checking if email exists in DB:
				emailAlreadyInDB = emailExistsInDB(emailAddressTextField.getText());
				// assumes UK number
				validateMobileNumberInput = validateMobileNumber(mobileNumberTextField.getText());
				
				validatePasswordInput = checkPasswordStrength(passwordTextField.getText());

				// first checking if the street number is a house name or number
				// first checking if it's a number
				String houseNameNumberInput = houseNumberTextField.getText();
				for (int k = 0; k < houseNameNumberInput.length(); k++) {
					// going through the input
					if (!Character.isDigit(houseNameNumberInput.charAt(k))) {
						validateHouseNameNumberInput = false;
						break; // breaks out of loop if it finds something that is not a digit
					} else {
						validateHouseNameNumberInput = true;
					}
				}

				// if not a number then it must be a String
				if (!validateHouseNameNumberInput) {
					String[] houseNameNumberInputArray = houseNumberTextField.getText().split(" ");
					for (int i = 0; i < houseNameNumberInputArray.length; i++) {
						// validating one string at a time:
						validateHouseNameNumberInput = validateName(houseNameNumberInputArray[i]);
						if (!validateHouseNameNumberInput) {
							// if one of the strings is not between a-z or A-Z then
							i = houseNameNumberInputArray.length;
							break;
						} else {
							validateHouseNameNumberInput = true;
						}
					}
				}

				// looping through the street name to validate each of the stings:
				// validateName(streetNameTextField.getText());
				String[] streetNameInputArray = streetNameTextField.getText().split(" ");
				for (int i = 0; i < streetNameInputArray.length; i++) {
					// validating one string at a time:
					validateStreetNameInput = validateName(streetNameInputArray[i]);
					if (!validateStreetNameInput) {
						// if one of the strings is not between a-z or A-Z then
						i = streetNameInputArray.length;
						break;
					} else {
						validateStreetNameInput = true;
					}
				}

				// see postcode method for the validation for this.
				validatePostcodeInput = validatePostcode(postcodeTextField.getText().toUpperCase());
				
				if (validateFirstNameInput && validateSurnameInput && validateEmailInput && !emailAlreadyInDB
						&& validateMobileNumberInput && validateHouseNameNumberInput && validateStreetNameInput
						&& validatePostcodeInput && validatePasswordInput) {
					model.setTitle(registerTitleComboBox.getSelectedItem().toString());
					model.setFirstName(firstNameTextField.getText());
					model.setSurname(surnameTextField.getText());
					model.setEmail(emailAddressTextField.getText());
					model.setMobileNumber(mobileNumberTextField.getText());
					model.setPassword(passwordTextField.getText());
					model.setHouseNameNum(houseNumberTextField.getText());
					model.setStreetName(streetNameTextField.getText());
					model.setCity(cityComboBox.getSelectedItem().toString());
					model.setPostcode(postcodeTextField.getText());
					model.setAccountType(accountTypeComboBox.getSelectedItem().toString());
					submit();
					frame.dispose();
					mainModule.currentState = STATE.HOMEPAGE;
					mainModule.userState = USER.ENQUIRER;
					MainModule.controller.drawNewView();
					Login sp = new Login(mainModule, controller, model);
				} else {
					displayError();
				}
			}
		});
		registerPanel.add(registerButton);

		JButton resetRegisterButton = new JButton("Reset");
		resetRegisterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				registerTitleComboBox.setSelectedItem("Mr");
				firstNameTextField.setText("");
				surnameTextField.setText("");
				emailAddressTextField.setText("");
				mobileNumberTextField.setText("");
				passwordTextField.setText("");
				houseNumberTextField.setText("");
				streetNameTextField.setText("");
				cityComboBox.setSelectedItem("Bath");
				postcodeTextField.setText("");
				accountTypeComboBox.setSelectedItem("Host");
			}
		});
		resetRegisterButton.setBounds(185, 553, 91, 23);
		registerPanel.add(resetRegisterButton);

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public boolean validateName(String name) {
		if (!name.matches("[a-zA-Z]*") || name.matches("")) {
			return false;
		} else {
			return true;
		}
	}

	private boolean emailIsValid;
	private boolean emailDoesNotAlreadyExistsInDB = false;

	public boolean validateEmail(String email) {
		// https://www.regular-expressions.info/email.html
		if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}")) {
			return false;
		} else {
			return true;
		}
	}

	public boolean emailExistsInDB(String email) {
		boolean existsInDB = false;
		try {
			connection = ConnectionManager.getConnection();
			String emailCheckQuery = "SELECT email from Account where email = ?";
			model.setEmail(emailAddressTextField.getText());
			PreparedStatement email_check = connection.prepareStatement(emailCheckQuery);
			email_check.setString(1, email);
			ResultSet rs = email_check.executeQuery();
			if (rs.next()) {
				existsInDB = true;
			} else {
				existsInDB = false;
			}
			connection.close();
		} catch (Exception e) {
		}
		return existsInDB;
	}

	public boolean validateMobileNumber(String mobile) {
		if (mobile.matches("[0-9]*") && (mobile.length() == 11)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean validatePostcode(String postcode) {
		if (postcode.matches("^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$")) {
			/*
			 * regular expressions for postcode copied used from this java website:
			 * https://howtodoinjava.com/java/regex/uk-postcode-validation/
			 */
			return true;
		} else {
			return false;
		}
	}


	private static boolean checkPasswordStrength(String password) {
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
		 *  if password contains 1 upper case letter then add 1 to rating.
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
	public void displayError() {
		ArrayList<String> arlist = new ArrayList<>();
		if (!validateFirstNameInput) {
			arlist.add(" First name input is invalid");
		}
		if (!validateSurnameInput) {
			arlist.add(" Surname input is invalid");
		}
		if (!validateEmailInput) {
			arlist.add(" This email input is invalid, please choose one of the form example@example.com");
		}
		if (!validateMobileNumberInput) {
			arlist.add(" Mobile number input is invalid, please choose 11 digits for your mobile number");
		}
		if (!validateHouseNameNumberInput) {
			arlist.add(" House name/number input is invalid");
		}
		if (!validateStreetNameInput) {
			arlist.add(" Street name input is invalid");
		}
		if (!validatePostcodeInput) {
			arlist.add(" Postcode input is invalid");
		}
		if (emailAlreadyInDB) {
			arlist.add(" This email address already exists, please choose another");

		}
		if (!validatePasswordInput) {
			arlist.add(" Password is not strong enough, it has to contain at least 1 digit, 1 lowercase, 1 uppercase letter, a special character out of ~!@#$%^&*()_-"
					+ "and has more than 8 characters.");
		}
		JOptionPane.showMessageDialog(this, arlist);
	}

	public void submit() {

		try {
			connection = ConnectionManager.getConnection();
			String insertAccountQuery = "insert into Account values(?,?,?,?,?,?,(SELECT address_id FROM Address WHERE houseNameNumber = ? AND postcode = ?))";
//			String insertAccountQuery = "insert into Account values(?,?,?,?,?,AES_ENCRYPT(?, 'key'),(SELECT address_id FROM Address WHERE houseNameNumber = ? AND postcode = ?))";
			String insertAddressQuery = "insert into Address(houseNameNumber, streetName, placeName, postcode) values(?,?,?,?) ";
			String insertIntoHostAccountTable = "insert into HostAccount (email) "
					+ "values((SELECT email FROM Account WHERE email=?))";
			String insertIntoGuestAccountTable = "insert into GuestAccount (email) "
					+ "values((SELECT email FROM Account WHERE email=?))";

			PreparedStatement ps_address = connection.prepareStatement(insertAddressQuery);
			ps_address.setString(1, model.getHouseNameNum());
			ps_address.setString(2, model.getStreetName());
			ps_address.setString(3, model.getCity());
			ps_address.setString(4, model.getPostcode());

			int y = ps_address.executeUpdate();
			if (y > 0) {
				JOptionPane.showMessageDialog(this, "Successful registration!");
			}

			PreparedStatement ps_account = connection.prepareStatement(insertAccountQuery);

			ps_account.setString(1, model.getEmail());
			ps_account.setString(2, model.getTitle());
			ps_account.setString(3, model.getFirstName());
			ps_account.setString(4, model.getSurname());
			ps_account.setString(5, model.getMobileNumber());
			ps_account.setString(6, model.getPassword());
			ps_account.setString(7, model.getHouseNameNum());
			ps_account.setString(8, model.getPostcode());

			int i = ps_account.executeUpdate();

			PreparedStatement ps_guestAccount = connection.prepareStatement(insertIntoGuestAccountTable);
			PreparedStatement ps_hostAccount = connection.prepareStatement(insertIntoHostAccountTable);

			if (model.getAccountType() == "Host") {
				ps_hostAccount.setString(1, model.getEmail());

				int h = ps_hostAccount.executeUpdate();

			} else if (model.getAccountType() == "Guest") {
				ps_guestAccount.setString(1, model.getEmail());

				int g = ps_guestAccount.executeUpdate();

			} else if (model.getAccountType() == "Both (Host & Guest)") {

				ps_hostAccount.setString(1, model.getEmail());
				int h = ps_hostAccount.executeUpdate();

				ps_guestAccount.setString(1, model.getEmail());
				int g = ps_guestAccount.executeUpdate();
			}

			connection.close();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
}
//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW & MAKE ALL HEIGHTS OF TEXTBOXES SAME