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
		registerTitleLabel.setBounds(90, 40, 118, 45);
		registerPanel.add(registerTitleLabel);
		
		String titles[] = { "Mr", "Mrs", "Miss", "Ms", "Dr" };		
		JComboBox registerTitleComboBox = new JComboBox(titles);
		registerTitleComboBox.setBounds(180, 46, 295, 31);
		registerPanel.add(registerTitleComboBox);
		
		JLabel firstNameLabel = new JLabel("First Name");
		firstNameLabel.setBounds(90, 88, 118, 45);
		registerPanel.add(firstNameLabel);
		
		firstNameTextField = new JTextField();
		firstNameTextField.setBounds(180, 94, 295, 31);
		registerPanel.add(firstNameTextField);
		firstNameTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Surname");
		lblNewLabel.setBounds(90, 136, 118, 45);
		registerPanel.add(lblNewLabel);
		
		surnameTextField = new JTextField();
		surnameTextField.setBounds(180, 142, 295, 31);
		registerPanel.add(surnameTextField);
		surnameTextField.setColumns(10);
		
		JLabel emailAddressLabel = new JLabel("Email Address");
		emailAddressLabel.setBounds(90, 184, 118, 45);
		registerPanel.add(emailAddressLabel);
				
		emailAddressTextField = new JTextField();
		emailAddressTextField.setColumns(10);
		emailAddressTextField.setBounds(180, 190, 295, 31);
		registerPanel.add(emailAddressTextField);

		JLabel mobileLabel = new JLabel("Mobile Number");
		mobileLabel.setBounds(90, 244, 125, 14);
		registerPanel.add(mobileLabel);
		
		mobileNumberTextField = new JTextField();
		mobileNumberTextField.setColumns(10);
		mobileNumberTextField.setBounds(180, 238, 295, 31);
		registerPanel.add(mobileNumberTextField);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(90, 290, 75, 14);
		registerPanel.add(passwordLabel);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(180, 285, 295, 31);
		registerPanel.add(passwordTextField);
		
		JLabel addressLine1Label = new JLabel("Address Line 1");
		addressLine1Label.setBounds(90, 336, 125, 14);
		registerPanel.add(addressLine1Label);
		
		addressLine1Field = new JTextField();
		addressLine1Field.setBounds(180,  328, 295, 31);
		registerPanel.add(addressLine1Field);
		
		JLabel houseNumberLabel = new JLabel("House Number");
		houseNumberLabel.setBounds(90, 384, 125, 14);
		registerPanel.add(houseNumberLabel);
		
		houseNumberTextField = new JTextField();
		houseNumberTextField.setBounds(180, 380 , 295, 31);
		registerPanel.add(houseNumberTextField);
		
		JLabel postcodeLabel = new JLabel("Postcode");
		postcodeLabel.setBounds(90, 432, 125, 14);
		registerPanel.add(postcodeLabel);
		
		postcodeTextField = new JTextField();
		postcodeTextField.setBounds(180, 428, 295, 31);
		registerPanel.add(postcodeTextField);

		JLabel accountTypeLabel = new JLabel("Register as");
		accountTypeLabel.setBounds(90, 480, 125, 14);
		registerPanel.add(accountTypeLabel);

		String accountTypes[] = { "Host", "Guest", "Both (Host & Guest)" };	
		JComboBox accountTypeComboBox = new JComboBox(accountTypes);
		accountTypeComboBox.setBounds(180,  474, 295, 31);
		registerPanel.add(accountTypeComboBox);
		
		registerButton.setBounds(260, 530, 120, 31);
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						close();
						login sp = new login();
			}
		});
		registerPanel.add(registerButton);
		
		
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW