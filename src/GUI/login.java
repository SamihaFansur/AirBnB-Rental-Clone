package GUI;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class login extends JFrame{

	private JFrame frame;
	private final JButton loginButton = new JButton("Login");
	private JPasswordField passwordField;
	private JTextField usernameField;

	 public void close() {
		 	frame.dispose();
	    }	
	 

	/**	
	 * Create the application.
	 */
	
	public login() {
		initializeLogin();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeLogin() {
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
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(104, 223, 118, 45);
		registerPanel.add(usernameLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(168, 293, 295, 31);
		registerPanel.add(passwordField);
		
		

		loginButton.setBounds(236, 393, 141, 36);
		registerPanel.add(loginButton);
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				search sp = new search();

			}
		});
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(104, 301, 75, 14);
		registerPanel.add(passwordLabel);
		
		usernameField = new JTextField();
		usernameField.setBounds(168, 230, 295, 31);
		registerPanel.add(usernameField);
		usernameField.setColumns(10);
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}
}
