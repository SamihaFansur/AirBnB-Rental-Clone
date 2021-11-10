package GUI;
import Controller.*;
import GUI.MainModule.STATE;
import GUI.MainModule.USER;
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
	/**	
	 * Create the application.
	 */
	 private Controller controller;
	 private Model model;
	 private MainModule mainModule;
	private NavEnquirer navBeforeLogin = new NavEnquirer();
	private JFrame frame ;
		
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
			mainModule.currentState = STATE.CONTACT_US;
			try {
				frame = new JFrame();
				System.out.println("in register: "+frame);
				navBeforeLogin.addNavBeforeLogin(frame, mainModule);
				System.out.println("after nav in register = "+mainModule);
				
			}catch(Exception e) {
				System.err.println(e.getMessage());
			}	
			
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
