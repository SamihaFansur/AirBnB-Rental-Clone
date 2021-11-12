package GUI;
//import hostGUI.*;
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

public class Homepage extends JFrame{
	/**	
	 * Create the application.
	 */
	 private Controller controller;
	 private Model model;
	 private MainModule mainModule;
	private NavEnquirer navBeforeLogin = new NavEnquirer();
	private JFrame frame ;
	 
	 public Homepage(MainModule mainModule, Controller controller, Model model) {
		//initializeHomePage();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	}


	/**
	 * Initialize the contents of the frame.
	 */
	 public void initializeHomePage() {
		 
		 if(mainModule.userState == USER.ENQUIRER) {
			
			 mainModule.currentState = STATE.HOMEPAGE;
				try {
					frame = new JFrame();
					//System.out.println("in register: "+frame);
					navBeforeLogin.addNavBeforeLogin(frame, mainModule);
					//System.out.println("after nav in register = "+mainModule);
					
				}catch(Exception e) {
					System.err.println(e.getMessage());
				}	
			
			JPanel loginPanel = new JPanel();
			loginPanel.setBackground(new Color(204, 255, 255));
			frame.getContentPane().add(loginPanel, BorderLayout.CENTER);
			loginPanel.setLayout(null);
			
			JLabel homePageLabel = new JLabel("Home Page");
			homePageLabel.setFont(new Font("Arial Black", Font.PLAIN, 26));
			homePageLabel.setBounds(202, -27, 222, 152);
			loginPanel.add(homePageLabel);
			frame.setBounds(100, 100, 600, 700);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		 }
		 else if (mainModule.userState==USER.HOST) {
			 /*Homepage for host should display list of host properties and buttons in the nav for:
			  * 	add property
			  * 	charge bands
			  * 	provision bookings
			  * 	accepted bookings
			  * 	search
			  * 	contact
			  * 
			  * 
			  */
			 
		 }
		 else if (mainModule.userState==USER.GUEST) {
			 
		 }
		 
	}
}
