package GUI;
import Controller.*;
import GUI.MainModule.STATE;
import GUI.MainModule;
import Model.*;

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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class Contact extends JFrame{

	private JFrame frame;


	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//	
//			Homepage window = new Homepage();
//			window.frame.setVisible(true);
//	}
	

	 public void close() {
		 	this.frame.dispose();
	 }	
	 

	/**	
	 * Create the application.
	 */
	 private Controller controller;
	 private Model model;
	 private MainModule mainModule;
	 public Contact(MainModule mainModule, Controller controller, Model model) {
		//initializeHomePage();
		this.model = model;
		this.mainModule = mainModule;
		this.controller = controller;
	}


	/**
	 * Initialize the contents of the frame.
	 */
		public void initializeContact() {
			frame = new JFrame();
			frame.getContentPane().setBackground(new Color(204, 255, 255));

			JPanel navBarPanel = new JPanel();
			navBarPanel.setBackground(new Color(51, 255, 255));
			frame.getContentPane().add(navBarPanel, BorderLayout.NORTH);
			
			JButton navHomeButton = new JButton("Home");
			navHomeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Homepage sp = new Homepage();

					mainModule.currentState=STATE.HOMEPAGE;
					MainModule.controller.drawNewView();
					close();
				}
			});
			navBarPanel.add(navHomeButton);
		
			
			JButton navSearchButton = new JButton("Search");
			navSearchButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainModule.currentState=STATE.SEARCH;
					MainModule.controller.drawNewView();
					close();
				}
			});
			navBarPanel.add(navSearchButton);
			
			JButton navRegisterButton = new JButton("Register");
			navRegisterButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainModule.currentState=STATE.SELF_REGISTRATION;
					MainModule.controller.drawNewView();
					close();
				}
			});
			navBarPanel.add(navRegisterButton);
			
			JButton navLoginButton = new JButton("Login");
			navLoginButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainModule.currentState=STATE.LOGIN;
					MainModule.controller.drawNewView();
					close();
							//Login sp = new Login();
				}
			});
			navBarPanel.add(navLoginButton);
			
			JPanel contactPanel = new JPanel();
			contactPanel.setBackground(new Color(204, 255, 255));
			frame.getContentPane().add(contactPanel, BorderLayout.CENTER);
			contactPanel.setLayout(null);

			JLabel contactLabel = new JLabel("Contact Us");
			contactLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
			contactLabel.setBounds(253, 41, 183, 57);
			contactPanel.add(contactLabel);

			JLabel nameLabel = new JLabel("Name");
			nameLabel.setBounds(54, 132, 118, 45);
			contactPanel.add(nameLabel);

			JTextField nameTextField = new JTextField();
			nameTextField.setBounds(189, 143, 276, 23);
			contactPanel.add(nameTextField);
			nameTextField.setColumns(10);
			
			JLabel emailAddressLabel = new JLabel("Email Address");
			emailAddressLabel.setBounds(54, 196, 118, 45);
			contactPanel.add(emailAddressLabel);

			JTextField emailAddressTextField = new JTextField();
			emailAddressTextField.setColumns(10);
			emailAddressTextField.setBounds(189, 204, 276, 29);
			contactPanel.add(emailAddressTextField);
			
			JLabel subjectLabel = new JLabel("Subject");
			subjectLabel.setBounds(54, 263, 125, 14);
			contactPanel.add(subjectLabel);

			JTextField subjectTextField = new JTextField();
			subjectTextField.setColumns(10);
			subjectTextField.setBounds(189, 256, 276, 29);
			contactPanel.add(subjectTextField);
			
			JLabel messageLabel = new JLabel("Message");
			messageLabel.setBounds(59, 310, 75, 14);
			contactPanel.add(messageLabel);

			JTextField messageTextField = new JTextField();
			messageTextField.setBounds(189, 306, 276, 205);
			contactPanel.add(messageTextField);
					
//			resetEmailButton.setBounds(356, 480, 91, 23);
//			resetEmailButton.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					firstNameTextField.setText("");
//					emailAddressTextField.setText("");
//					mobileNumberTextField.setText("");
//					passwordTextField.setText("");
//				}
//			});
			//registerPanel.add(resetEmailButton);

			JButton sendEmailButton = new JButton("Send Email");
			sendEmailButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Controller.SendEmail();
				}
			});
			sendEmailButton.setBounds(253, 550, 120, 23);
			contactPanel.add(sendEmailButton);


			frame.setBounds(100, 100, 600, 700);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			frame.setLocationRelativeTo(null);
			
			frame.setVisible(true);
		

	}
}
