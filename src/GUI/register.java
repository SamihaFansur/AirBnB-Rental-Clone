package GUI;

import java.awt.EventQueue;
import javax.swing.*;

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

public class register extends JFrame{

	private JFrame frame;
	private final JButton registerButton = new JButton("Register");
	private JPasswordField passwordTextField;
	private JTextField firstNameTextField;
	private JTextField surnameTextField;
	//private JPasswordField passwordField_1; //unused
	private JTextField addressLine1Field;
	private JTextField postcodeTextField;
	private JTextField houseNumberTextField;
	private JTextField emailAddressTextField;
	private JTextField mobileNumberTextField;

	public void close() {
		frame.dispose();
	}

	/**
	 * Create the application.
	 */

	public register() {
		initializeRegister();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeRegister() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(204, 255, 255));

		JPanel navBarPanel = new JPanel();
		navBarPanel.setBackground(new Color(51, 255, 255));
		frame.getContentPane().add(navBarPanel, BorderLayout.NORTH);

		JButton navHomeButton = new JButton("Home");
		navHomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				homePage sp = new homePage();
			}
		});
		navBarPanel.add(navHomeButton);
		JButton navSearchButton = new JButton("Search");
		navSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				search sp = new search();
			}
		});
		navBarPanel.add(navSearchButton);

		JButton navRegisterButton = new JButton("Register");
		navRegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				register sp = new register();
			}
		});
		navBarPanel.add(navRegisterButton);

		JButton navLoginButton = new JButton("Login");
		navLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				login sp = new login();
			}
		});
		navBarPanel.add(navLoginButton);

		JPanel registerPanel = new JPanel();
		registerPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(registerPanel, BorderLayout.CENTER);
		registerPanel.setLayout(null);

		JLabel registerTitleLabel = new JLabel("Title");
		registerTitleLabel.setBounds(99, 57, 118, 45);
		registerPanel.add(registerTitleLabel);

		String titles[] = { "Mr", "Mrs", "Miss", "Ms", "Dr" };
		JComboBox registerTitleComboBox = new JComboBox(titles);
		registerTitleComboBox.setBounds(189, 71, 276, 23);
		registerPanel.add(registerTitleComboBox);

		JLabel firstNameLabel = new JLabel("First Name");
		firstNameLabel.setBounds(99, 102, 118, 45);
		registerPanel.add(firstNameLabel);

		firstNameTextField = new JTextField();
		firstNameTextField.setBounds(189, 113, 276, 23);
		registerPanel.add(firstNameTextField);
		firstNameTextField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Surname");
		lblNewLabel.setBounds(99, 136, 118, 45);
		registerPanel.add(lblNewLabel);

		surnameTextField = new JTextField();
		surnameTextField.setBounds(189, 147, 276, 23);
		registerPanel.add(surnameTextField);
		surnameTextField.setColumns(10);

		JLabel emailAddressLabel = new JLabel("Email Address");
		emailAddressLabel.setBounds(99, 173, 118, 45);
		registerPanel.add(emailAddressLabel);

		emailAddressTextField = new JTextField();
		emailAddressTextField.setColumns(10);
		emailAddressTextField.setBounds(189, 181, 276, 29);
		registerPanel.add(emailAddressTextField);

		JLabel mobileLabel = new JLabel("Mobile Number");
		mobileLabel.setBounds(99, 229, 125, 14);
		registerPanel.add(mobileLabel);

		mobileNumberTextField = new JTextField();
		mobileNumberTextField.setColumns(10);
		mobileNumberTextField.setBounds(189, 217, 276, 29);
		registerPanel.add(mobileNumberTextField);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(99, 267, 75, 14);
		registerPanel.add(passwordLabel);

		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(189, 261, 276, 26);
		registerPanel.add(passwordTextField);

		JLabel addressLine1Label = new JLabel("Address Line 1");
		addressLine1Label.setBounds(99, 307, 125, 14);
		registerPanel.add(addressLine1Label);

		addressLine1Field = new JTextField();
		addressLine1Field.setBounds(189,  300, 276, 29);
		registerPanel.add(addressLine1Field);

		JLabel houseNumberLabel = new JLabel("House Number");
		houseNumberLabel.setBounds(99, 346, 125, 14);
		registerPanel.add(houseNumberLabel);

		houseNumberTextField = new JTextField();
		houseNumberTextField.setBounds(189, 340 , 276, 27);
		registerPanel.add(houseNumberTextField);

		JLabel postcodeLabel = new JLabel("Postcode");
		postcodeLabel.setBounds(99, 386, 125, 14);
		registerPanel.add(postcodeLabel);

		postcodeTextField = new JTextField();
		postcodeTextField.setBounds(189, 378, 276, 31);
		registerPanel.add(postcodeTextField);

		JLabel accountTypeLabel = new JLabel("Register as");
		accountTypeLabel.setBounds(99, 430, 125, 14);
		registerPanel.add(accountTypeLabel);

		String accountTypes[] = { "Host", "Guest", "Both (Host & Guest)" };
		JComboBox accountTypeComboBox = new JComboBox(accountTypes);
		accountTypeComboBox.setBounds(189,  426, 276, 23);
		registerPanel.add(accountTypeComboBox);

		registerButton.setBounds(264, 550, 120, 31);
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstName = firstNameTextField.getText();
				String surname = surnameTextField.getText();
				String email = emailAddressTextField.getText();
				String mobile = mobileNumberTextField.getText();
				String password = passwordTextField.getText();
				String addressLine = addressLine1Field.getText();
				String houseNumber = houseNumberTextField.getText();
				String postcode = postcodeTextField.getText();
				JComboBox accountType = (JComboBox) accountTypeComboBox.getSelectedItem();

				close();
				login sp = new login();
			}
		});
		registerPanel.add(registerButton);

		JButton register = new JButton("Register");
		register.setBounds(356, 480, 91, 23);
		registerPanel.add(register);

		JLabel registerLabel = new JLabel("Register");
		registerLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		registerLabel.setBounds(264, 11, 183, 57);
		registerPanel.add(registerLabel);

		JButton resetRegisterButton = new JButton("Reset");
		resetRegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerTitleComboBox.setSelectedItem("Mr");
				firstNameTextField.setText("");
				surnameTextField.setText("");
				emailAddressTextField.setText("");
				mobileNumberTextField.setText("");
				passwordTextField.setText("");
				addressLine1Field.setText("");
				houseNumberTextField.setText("");
				postcodeTextField.setText("");
				accountTypeComboBox.setSelectedItem("Host");
			}
		});
		resetRegisterButton.setBounds(220, 480, 91, 23);
		registerPanel.add(resetRegisterButton);


		frame.setBounds(100, 100, 600, 599);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW