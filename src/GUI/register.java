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
	private final JButton registerButton = new JButton("register");
	private JPasswordField passwordTextField;
	private JTextField usernameTextField;
	private JPasswordField passwordField_1;
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
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(90, 88, 118, 45);
		registerPanel.add(lblNewLabel);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(168, 245, 295, 31);
		registerPanel.add(passwordTextField);
		

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(90, 253, 75, 14);
		registerPanel.add(passwordLabel);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(168, 95, 295, 31);
		registerPanel.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		emailAddressTextField = new JTextField();
		emailAddressTextField.setColumns(10);
		emailAddressTextField.setBounds(168, 144, 295, 31);
		registerPanel.add(emailAddressTextField);
		
		mobileNumberTextField = new JTextField();
		mobileNumberTextField.setColumns(10);
		mobileNumberTextField.setBounds(168, 198, 295, 31);
		registerPanel.add(mobileNumberTextField);
		
		JLabel mobileLabel = new JLabel("Mobile Number");
		mobileLabel.setBounds(90, 206, 75, 14);
		registerPanel.add(mobileLabel);
		
		JLabel emailAddressLabel = new JLabel("Email Address");
		emailAddressLabel.setBounds(90, 137, 118, 45);
		registerPanel.add(emailAddressLabel);
		
		JComboBox accountTypeComboBox = new JComboBox();
		accountTypeComboBox.setBounds(168, 299, 295, 31);
		registerPanel.add(accountTypeComboBox);
		
		JLabel accountTypeLabel = new JLabel("Account Type");
		accountTypeLabel.setBounds(90, 307, 75, 14);
		registerPanel.add(accountTypeLabel);
		
		JLabel registerTitleLabel = new JLabel("Title");
		registerTitleLabel.setBounds(90, 40, 118, 45);
		registerPanel.add(registerTitleLabel);
		
		JComboBox registerTitleComboBox = new JComboBox();
		registerTitleComboBox.setBounds(168, 46, 295, 31);
		registerPanel.add(registerTitleComboBox);
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}
}
