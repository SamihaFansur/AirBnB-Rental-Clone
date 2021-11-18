package GUI;
import Controller.*;
import GUI.MainModule.STATE;
import Model.*;
import java.awt.EventQueue;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class Register extends JFrame{

	private JButton registerButton = new JButton("Register");
	private JPasswordField passwordTextField;
	private JTextField firstNameTextField;
	private JTextField surnameTextField;
	private JTextField streetNameTextField;
	private JTextField postcodeTextField;
	private JTextField houseNumberTextField;
	private JTextField cityTextField;
	private JTextField emailAddressTextField;
	private JTextField mobileNumberTextField;
	private JComboBox accountTypeComboBox;
	private JComboBox registerTitleComboBox;
	
//	private JFrame frame;
	private Model model;
	private Controller controller;
	private MainModule mainModule;
	private NavEnquirer navBeforeLogin = new NavEnquirer();
	private JFrame frame ;
	
	Connection connection = null;
	
	/**
	 * Create the application.
	 */

	public Register(MainModule mainModule, Controller controller, Model model) {
		//initializeRegister();
		this.mainModule=mainModule;
		this.model=model;
		this.controller=controller;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeRegister() {
		mainModule.currentState = STATE.SELF_REGISTRATION;
		try {
			frame = new JFrame();
			//System.out.println("in register: "+frame);
			navBeforeLogin.addNavBeforeLogin(frame, mainModule);
			//System.out.println("after nav in register = "+mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}		

		JPanel registerPanel = new JPanel();
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

		cityTextField = new JTextField();
		cityTextField.setBounds(217, 400, 276, 31);
		registerPanel.add(cityTextField);

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
		accountTypeComboBox.setBounds(217,  489, 276, 23);
		registerPanel.add(accountTypeComboBox);
		
		registerButton.setBounds(321, 553, 91, 23);
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Boolean validateFirstNameInput =  validateName(firstNameTextField.getText());
				Boolean validateSurnameInput = validateName(surnameTextField.getText());
				
				Boolean validateEmailInput = validateEmail(emailAddressTextField.getText());

				// assumes UK number
				Boolean validateMobileNumberInput = validateMobileNumber(mobileNumberTextField.getText());
				
				// first checking if the street number is a house name or number
				Boolean validateHouseNameNumberInput=false;
				//first checking if it's a number
				String houseNameNumberInput = houseNumberTextField.getText();
				for(int k=0;k<houseNameNumberInput.length();k++) {
					//going through the input 
					
					if(Character.isDigit(houseNameNumberInput.charAt(k))==false) {
						//System.out.println(houseNameNumberInput+" IS NOT VALID");
						System.out.println(houseNameNumberInput+" is a string not an integer. So break out of here");
						validateHouseNameNumberInput = false;
						break;	//breaks out of loop if it finds something that is not a digit
						//k=houseNameNumberInput.length();
					}
					else {
						validateHouseNameNumberInput = true;
					}
				}
				if(validateHouseNameNumberInput==true) {
					System.out.println(houseNameNumberInput+" IS A NUMBER ONLY AND IS VALID");	
				}
				
				//if not a number then it must be a String
				if(validateHouseNameNumberInput==false) {
					String[] houseNameNumberInputArray = houseNumberTextField.getText().split(" ");
					for(int i=0;i<houseNameNumberInputArray.length;i++) {
						// validating one string at a time:
						System.out.println(i);
						validateHouseNameNumberInput = validateName(houseNameNumberInputArray[i]);
						if (validateHouseNameNumberInput == false) {
							// if one of the strings is not between a-z or A-Z then 
							System.out.println(houseNameNumberInputArray+" IS NOT VALID");	
							System.out.println(houseNameNumberInputArray[i]+"does not contain a-z or A-Z");
							i = houseNameNumberInputArray.length;
							break;
						}else {
							System.out.println(i+"time");
							validateHouseNameNumberInput = true;
						}
					}
				}
				
				//looping through the street name to validate each of the stings:
				Boolean validateStreetNameInput = false; //validateName(streetNameTextField.getText());
				String[] streetNameInputArray = streetNameTextField.getText().split(" ");
				for(int i=0;i<streetNameInputArray.length;i++) {
					// validating one string at a time:
					System.out.println("HEREEE");
					System.out.println(streetNameInputArray[i]);
					validateStreetNameInput = validateName(streetNameInputArray[i]);
					if (validateStreetNameInput == false) {
						// if one of the strings is not between a-z or A-Z then 
						i = streetNameInputArray.length;
						break;
					}else {
						validateStreetNameInput = true;
					}
				}
				
				Boolean validateCityNameInput = validateName(cityTextField.getText());
				
				// see postcode method for the validation for this.
				Boolean validatePostcodeInput = validatePostcode(postcodeTextField.getText().toUpperCase());
			
				
				System.out.println("end result for streetName: "+validateStreetNameInput);
				
				if(validateFirstNameInput && validateSurnameInput && validateEmailInput && validateMobileNumberInput && validateHouseNameNumberInput && validateStreetNameInput && validateCityNameInput && validatePostcodeInput) {
					model.setTitle(registerTitleComboBox.getSelectedItem().toString());
					model.setFirstName(firstNameTextField.getText());
					model.setSurname(surnameTextField.getText());
					model.setEmail(emailAddressTextField.getText());
					model.setMobileNumber(mobileNumberTextField.getText());
					model.setPassword(passwordTextField.getText());
					model.setHouseNameNum(houseNumberTextField.getText());
					model.setStreetName(streetNameTextField.getText());
					model.setCity(cityTextField.getText()); 
					model.setPostcode(postcodeTextField.getText());
					model.setAccountType(accountTypeComboBox.getSelectedItem().toString());
					submit();
					Login sp = new Login(mainModule, controller, model);
				}
			/*
				
				submit();
				Login sp = new Login(mainModule,controller,model);
			*/
			}
		});
		registerPanel.add(registerButton);

		JButton resetRegisterButton = new JButton("Reset");
		resetRegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerTitleComboBox.setSelectedItem("Mr");
				firstNameTextField.setText("");
				surnameTextField.setText("");
				emailAddressTextField.setText("");
				mobileNumberTextField.setText("");
				passwordTextField.setText("");
				houseNumberTextField.setText("");
				streetNameTextField.setText("");
				cityTextField.setText("");
				postcodeTextField.setText("");
				accountTypeComboBox.setSelectedItem("Host");
			}
		});
		resetRegisterButton.setBounds(185, 553, 91, 23);
		registerPanel.add(resetRegisterButton);
		
		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	
	
	public boolean validateName(String name) {
		if (!name.matches("[a-zA-Z]*")) {
			System.out.println(name+" IS NOT VALID NAME");	
			return false;
		}
		else{
			System.out.println(name+" IS VALID");	
			return true;
		}
	}

	public boolean validateEmail(String email) {
		// https://www.regular-expressions.info/email.html
		if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}")) {
			System.out.println(email+" IS NOT VALID");	
			return false;
		}else{
			return emailExists(email);

		}
	}

	public boolean emailExists(String email) {
		boolean exists = false;
		try {
			connection = ConnectionManager.getConnection();
			String emailCheckQuery = "SELECT email from Account where email = ?";			
			model.setEmail(emailAddressTextField.getText());
			PreparedStatement email_check = connection.prepareStatement(emailCheckQuery);
			email_check.setString(1, email);
			ResultSet rs = email_check.executeQuery();
			if (rs.next()) {
				exists =  false;		
			} else {
				exists =  true;
			}
		} catch (Exception e) {
			System.out.println("error");
		}
		return exists; 
	}
	public boolean validateMobileNumber(String mobile) {
		if (mobile.matches("[0-9]*") && (mobile.length() == 11)) {
			//System.out.println("First name contains a characters not between a-z or A-Z");
			System.out.println(mobile+" IS  VALID");	

			return true;
		}
		else {
			System.out.println(mobile+" IS NOT VALID");	
			return false;
		}

	}



	public boolean validatePostcode(String postcode) {
		if(postcode.matches("^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$")) {
			/* regular expressions for postcode copied used from this java website:
			https://howtodoinjava.com/java/regex/uk-postcode-validation/
				*/
			System.out.println(postcode+" IS VALID");	
			return true;
		}else {
			System.out.println(postcode+" IS NOT VALID");	
			return false;
		}
	}
		
	
	
	public void submit() {
		try {
			connection = ConnectionManager.getConnection();
			String insertAccountQuery = "insert into Account values(?,?,?,?,?,?,?,?)";
			String insertAddressQuery = "insert into Address values(?,?,?,?) ";
			String insertIntoHostAccountTable = "insert into HostAccount (email) "
					+ "values((SELECT email FROM Account WHERE email=?))";		
			String insertIntoGuestAccountTable = "insert into GuestAccount (email) "
					+ "values((SELECT email FROM Account WHERE email=?))";			

			PreparedStatement ps_account = connection.prepareStatement(insertAccountQuery);
			
			ps_account.setString(1, model.getEmail());
			ps_account.setString(2, model.getTitle());
			ps_account.setString(3, model.getFirstName());
			ps_account.setString(4, model.getSurname());
			ps_account.setString(5, model.getMobileNumber());
			ps_account.setString(6, model.getPassword());
			ps_account.setString(7, model.getHouseNameNum());
			ps_account.setString(8, model.getPostcode());

			System.out.println(ps_account);
			int i  = ps_account.executeUpdate();
			if(i>0) {
				System.out.println("7");
				System.out.println(this);
				 //remove later
			}
			PreparedStatement ps_address = connection.prepareStatement(insertAddressQuery);
			ps_address.setString(1, model.getHouseNameNum());
			ps_address.setString(2, model.getStreetName());
			ps_address.setString(3, model.getCity());
			ps_address.setString(4, model.getPostcode());
			
			int  y = ps_address.executeUpdate();
			System.out.println("6");
			if(y>0) {
				System.out.println("tryagain");
				System.out.println(this);
				JOptionPane.showMessageDialog(this, "Successful registration!"); //remove later
			}
			

			PreparedStatement ps_guestAccount = connection.prepareStatement(insertIntoGuestAccountTable);
			PreparedStatement ps_hostAccount = connection.prepareStatement(insertIntoHostAccountTable);
			
			if(model.getAccountType() == "Host") {
				ps_hostAccount.setString(1, model.getEmail());
				System.out.println(ps_hostAccount);
				
				int h  = ps_hostAccount.executeUpdate();
				if(h>0) {
					System.out.println("7");
					System.out.println(this);
					 //remove later
				}
				
			}else if (model.getAccountType() == "Guest") {
				ps_guestAccount.setString(1, model.getEmail());
				System.out.println(ps_guestAccount);
				
				int g  = ps_guestAccount.executeUpdate();
				if(g>0) {
					System.out.println("7");
					System.out.println(this);
					 //remove later
				}
			}else if(model.getAccountType() == "Both (Host & Guest)") {

				ps_hostAccount.setString(1, model.getEmail());
				System.out.println(ps_hostAccount);
				
				int h  = ps_hostAccount.executeUpdate();
				if(h>0) {
					System.out.println("7");
					System.out.println(this);
					 //remove later
				}
				
				ps_guestAccount.setString(1, model.getEmail());
				System.out.println(ps_guestAccount);
				
				int g  = ps_guestAccount.executeUpdate();
				if(g>0) {
					System.out.println("7");
					System.out.println(this);
					 //remove later
				}
			}
			
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
	
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW & MAKE ALL HEIGHTS OF TEXTBOXES SAME