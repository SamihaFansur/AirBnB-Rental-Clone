package GUI;
import Controller.*;
import Model.*;
import GUI.MainModule.STATE;
import Model.*;
import java.awt.EventQueue;
import java.sql.Statement;
import javax.swing.*;
import java.sql.ResultSet;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class Login extends JFrame{

	private JFrame frame;
	private JButton loginButton = new JButton("Login");
	private JButton registerButton = new JButton("Register");
	private JTextField passwordField;
	private JTextField usernameField;
	private JLabel passwordLabel;
	private JPanel registerPanel;
	private JLabel usernameLabel;

	Connection connection = null;
	
	 public void close() {
		 	this.frame.dispose();
	    }	
	 

	/**	
	 * Create the application.
	 */

	 
	private String userName_login;
	private String password_login;
	 
	private Controller controller;
	private Model model;
	private MainModule mainModule;
	public Login(MainModule mainModule, Controller controller, Model model) {
		//initializeLogin();
		this.mainModule=mainModule;
		this.controller=controller;
		this.model=model;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeLogin() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(204, 255, 255));
		
		JPanel navBarPanel = new JPanel();
		navBarPanel.setBackground(new Color(51, 255, 255));
		frame.getContentPane().add(navBarPanel, BorderLayout.NORTH);
		
		System.out.println("Initialise homepage");
		
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
		
		JButton navContactButton = new JButton("Contact");
		navContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mainModule.currentState=STATE.CONTACT_US;
				MainModule.controller.drawNewView();
				close();
				//Register sp = new Register();
			}
		});
		navBarPanel.add(navContactButton);
		
		JPanel loginPanel = new JPanel();
		
		loginPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(loginPanel, BorderLayout.CENTER);
		loginPanel.setLayout(null);
		
		JLabel usernameLabel = new JLabel("Username/email");
		usernameLabel.setBounds(72, 221, 118, 45);
		loginPanel.add(usernameLabel);
		
		JTextField usernameField = new JTextField();
		usernameField.setBounds(168, 228, 295, 31);
		loginPanel.add(usernameField);
		usernameField.setColumns(10);
		
		JTextField passwordField = new JTextField();
		passwordField.setBounds(168, 323, 295, 31);
		loginPanel.add(passwordField);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(72, 329, 75, 14);
		loginPanel.add(passwordLabel);
		

		loginButton.setBounds(124, 432, 100, 36);
		loginPanel.add(loginButton);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				model.setEmail(usernameField.getText());
				model.setPassword(passwordField.getText());
				
				
				
				userName_login = model.getEmail();
				password_login = model.getPassword();
				
				logUserIn();
				
//				System.out.println(usernameField.getText());
//				System.out.println(passwordField.getText());
//				
//				userName_login = model.getEmail();
//				password_login = model.getPassword();
//				System.out.println(userName_login);
//				System.out.println(password_login);
//				
				//close();
			//	Search sp = new Search();
			}
		});
		
		JButton registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			//	Register sp = new Register();
			}
		});
		
		registerButton.setBounds(253, 432, 100, 36);
		loginPanel.add(registerButton);
		
		JButton resetLogin = new JButton("Reset");
		resetLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					usernameField.setText("");
					passwordField.setText("");
			}
		});
		
		resetLogin.setBounds(381, 432, 100, 36);
		loginPanel.add(resetLogin);
		
		JLabel loginLabel = new JLabel("Login");
		loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		loginLabel.setBounds(253, 73, 118, 31);
		loginPanel.add(loginLabel);
		
		
		
		frame.setBounds(100, 100, 601, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
	}
	
	public void logUserIn() {
		
		System.out.println("here");		
//		String email = model.getEmail();
//		String password = model.getPassword();
		System.out.println("emaillll "+userName_login);
		System.out.println(password_login);
		
		try {
			connection = ConnectionManager.getConnection();

			PreparedStatement loginQuery = (PreparedStatement) connection.prepareStatement("Select email, password from Account where email=? and password=?");
			

			loginQuery.setString(1, userName_login);
			loginQuery.setString(2, password_login);
			ResultSet rs = loginQuery.executeQuery();
		
			
			/*Need to check if the email belongs to a "host and guest" account.
			 * If so, ask user to log in as either a host or a guest so 
			 * GUI can be built according to this. 
			 */
			
			if (rs.next()) {
				JOptionPane.showMessageDialog(this,"You have successfully logged in");
				System.out.println("logggin in: "+model.getEmail());
			} else {
				JOptionPane.showMessageDialog(this, "Wrong Username & Password");
			}
//			PreparedStatement ps = connection.prepareStatement(loginQuery);
//			System.out.println("4");
//			ps.setString(1, getUsername());;
//			ps.setString(6, getPasword());
//			System.out.println("5");
//			System.out.println(ps);
//			int i  = ps.executeQuery();
//			System.out.println("6");
//			if(i>0) {
//				System.out.println("7");
//				System.out.println(this);
//				JOptionPane.showMessageDialog(this, "saved ok"); //remove later
//				System.out.println("logged in");
//			}
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
	


	
}
