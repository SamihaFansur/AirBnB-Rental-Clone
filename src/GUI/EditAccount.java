package GUI;

import java.awt.EventQueue;
import javax.swing.*;

import Controller.Controller;
import GUI.ConnectionManager;
import GUI.Login;
import GUI.MainModule;
import GUI.MainModule.EDITPROPERTY;
import GUI.MainModule.STATE;
import GUI.MainModule.USER;
import HostGUI.NavHost;
import Model.Model;

import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class EditAccount extends JFrame{

	private NavHost navForHost = new NavHost();
	private JFrame frame;
	private JTextField firstNameField;
	private JTextField surnameTextField;
	private JTextField passwordTextField;
	private JButton addEditPropertyButton;
	private JComboBox titleComboBox = new JComboBox();
	 
	Connection connection = null;

	/**
	 * Create the application.
	 */

	 private Controller controller;
	 private Model model;
	 private MainModule mainModule;


	 
	 public EditAccount(MainModule mainModule, Controller controller, Model model) {
		//initializeEditAccount();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditAccount() {
		System.out.println("0");
		
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		JPanel editACcountPanel = new JPanel();
		editACcountPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(editACcountPanel, BorderLayout.CENTER);
		editACcountPanel.setLayout(null);

		JLabel editAccountLabel = new JLabel("Edit Account");
		editAccountLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editAccountLabel.setBounds(222, 53, 183, 57);
		editACcountPanel.add(editAccountLabel);
				
		JLabel TitleLabel = new JLabel("Title:");
		TitleLabel.setBounds(104, 268, 93, 34);
		editACcountPanel.add(TitleLabel);
		
		JLabel firstNameLabel = new JLabel("First Name:");
		firstNameLabel.setBounds(104, 336, 93, 34);
		editACcountPanel.add(firstNameLabel);
		
		firstNameField = new JTextField();
		firstNameField.setColumns(10);
		firstNameField.setBounds(207, 336, 274, 34);
		editACcountPanel.add(firstNameField);
		
		JLabel surnameLabel = new JLabel("Surname:");
		surnameLabel.setBounds(104, 402, 93, 34);
		editACcountPanel.add(surnameLabel);
		
		surnameTextField = new JTextField();
		surnameTextField.setColumns(10);
		surnameTextField.setBounds(207, 402, 274, 34);
		editACcountPanel.add(surnameTextField);
		
		System.out.println("1");
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(104, 456, 93, 34);
		editACcountPanel.add(passwordLabel);
		
		passwordTextField = new JTextField();
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(207, 456, 274, 34);
		editACcountPanel.add(passwordTextField);
		
		addEditPropertyButton = new JButton("Save");
		addEditPropertyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEditAccountDetails();
			}
		});
		addEditPropertyButton.setBounds(245, 548, 91, 23);
		editACcountPanel.add(addEditPropertyButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(22, 75, 91, 23);
		editACcountPanel.add(backButton);
		System.out.println("2");
		
		String titles[] = { "Mr", "Mrs", "Miss", "Ms", "Dr" };
		
		titleComboBox = new JComboBox(titles);
		titleComboBox.setBounds(207, 274, 276, 23);
		editACcountPanel.add(titleComboBox);

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		System.out.println("3");
	}
	
	public void addEditAccountDetails() {
		
		}
	}


//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW