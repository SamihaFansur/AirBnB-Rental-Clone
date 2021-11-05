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

public class Homepage extends JFrame{

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
		 	frame.dispose();
	 }	
	 

	/**	
	 * Create the application.
	 */
	 private Controller controller;
	 private Model model;
	 private MainModule mainModule;
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
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(204, 255, 255));
		
		JPanel navBarPanel = new JPanel();
		navBarPanel.setBackground(new Color(51, 255, 255));
		frame.getContentPane().add(navBarPanel, BorderLayout.NORTH);
		
		System.out.println("Initialise homepage");
		
		JButton navHomeButton = new JButton("Home");
		navHomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// remove all objects on the screen. 
				// call the method to draw the homepage
				// change STATE of the program
				//Homepage sp = new Homepage();
				System.out.println("action performed homepage");
				mainModule.currentState=STATE.HOMEPAGE;
				MainModule.controller.drawNewView();
//				close();
			}
		});
		navBarPanel.add(navHomeButton);
	
		
		JButton navSearchButton = new JButton("Search");
		navSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// clear panels on the screen
				// update STATE of the system
				// call search.initialise();
				//Search sp = new Search();
				//MainModule=STATE.SEARCH;
				System.out.println("action performed search btn");
				mainModule.currentState=STATE.SEARCH;
		
				System.out.println(mainModule.currentState);
				try {
					MainModule.controller.drawNewView();
				} catch (Exception s) {
					System.err.println(s.getMessage());
				}
				//search.initializeSearch();
				//close(); 
			}
		});
		navBarPanel.add(navSearchButton);
		
		JButton navRegisterButton = new JButton("Register");
		navRegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mainModule.currentState=STATE.SELF_REGISTRATION;
				MainModule.controller.drawNewView();
				//close();
				//Register sp = new Register();
			}
		});
		navBarPanel.add(navRegisterButton);
		
		JButton navLoginButton = new JButton("Login");
		navLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.currentState=STATE.LOGIN;
				MainModule.controller.drawNewView();
				//close();
						//Login sp = new Login();
			}
		});
		navBarPanel.add(navLoginButton);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(loginPanel, BorderLayout.CENTER);
		loginPanel.setLayout(null);
		
		JLabel homePageLabel = new JLabel("Home Page");
		homePageLabel.setFont(new Font("Arial Black", Font.PLAIN, 26));
		homePageLabel.setBounds(202, -27, 222, 152);
		loginPanel.add(homePageLabel);
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		frame.setVisible(true);
	}
}
