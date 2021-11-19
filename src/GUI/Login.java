package GUI;

import Controller.*;
import Model.*;
import GUI.MainModule.STATE;
import GUI.MainModule.USER;
import Model.*;
import java.awt.EventQueue;
import java.sql.Statement;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class Login extends JFrame {

	private JButton loginButton;
	private JButton registerButton;
	private JPasswordField passwordField;
	private JTextField usernameField;
	private JLabel passwordLabel;
	private JPanel registerPanel;
	private JLabel usernameLabel;

	Connection connection = null;

	private String userName_login;
	private String password_login;
	private int accountSelected;
	private Controller controller;
	private Model model;
	private MainModule mainModule;
	private NavEnquirer navBeforeLogin = new NavEnquirer();
	private JFrame frame;

	public Login(MainModule mainModule, Controller controller, Model model) {
		// initializeLogin();
		this.mainModule = mainModule;
		this.controller = controller;
		this.model = model;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeLogin() {
		mainModule.currentState = STATE.LOGIN;
		try {
			frame = new JFrame();
			navBeforeLogin.addNavBeforeLogin(frame, mainModule);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

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

		JTextField passwordField = new JPasswordField();
		passwordField.setBounds(168, 323, 295, 31);
		loginPanel.add(passwordField);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(72, 329, 75, 14);
		loginPanel.add(passwordLabel);

		JButton loginButton = new JButton("Login");
		loginButton.setBounds(200, 432, 100, 36);
		loginPanel.add(loginButton);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				model.setEmail(usernameField.getText());
				model.setPassword(passwordField.getText());

				userName_login = model.getEmail();
				password_login = model.getPassword();

				logUserIn();

//				mainModule.currentState = STATE.HOST_ACCOUNT;
//				mainModule.userState = USER.HOST;
				MainModule.controller.drawNewView();
//				close();

//				System.out.println(passwordField.getText());
//				
//				userName_login = model.getEmail();
//				password_login = model.getPassword();
//				System.out.println(userName_login);
//				System.out.println(password_login);
//				
				// close();
				// Search sp = new Search();
			}

		});

		JButton resetLogin = new JButton("Reset");
		resetLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usernameField.setText("");
				passwordField.setText("");
			}
		});

		resetLogin.setBounds(320, 432, 100, 36);
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

	private PreparedStatement loginQuery = null;
	private ResultSet rs = null;

	public void logUserIn() {

		try {
			connection = ConnectionManager.getConnection();

			PreparedStatement loginQuery = (PreparedStatement) connection
					.prepareStatement("Select email, password from Account where email=? and password=?");

			loginQuery.setString(1, userName_login);
			loginQuery.setString(2, password_login);
			ResultSet rs = loginQuery.executeQuery();

			/*
			 * Need to check if the email belongs to a "host and guest" account. If so, ask
			 * user to log in as either a host or a guest so GUI can be built according to
			 * this.
			 */

			if (rs.next()) {
				// checking if the username/email belongs to a "host and guest" account
				PreparedStatement hostAccountQuery = (PreparedStatement) connection
						.prepareStatement("Select email from HostAccount where email=?");
				hostAccountQuery.setString(1, userName_login);
				ResultSet rsHost = hostAccountQuery.executeQuery();
				PreparedStatement guestAccountQuery = (PreparedStatement) connection
						.prepareStatement("Select email from GuestAccount where email=?");
				guestAccountQuery.setString(1, userName_login);
				ResultSet rsGuest = guestAccountQuery.executeQuery();

				boolean hostLogin, guestLogin;
				hostLogin = rsHost.next();
				guestLogin = rsGuest.next();
				System.out.println("host?:" + hostLogin);
				System.out.println("guest?:" + guestLogin);

				if (hostLogin && guestLogin) {
					String[] options = { "Host", "Guest" };
					accountSelected = JOptionPane.showOptionDialog(this, "Please log in as a Host or Guest", "Message",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
					System.out.println("THE RESULT: " + accountSelected);
					if (accountSelected == 0) {
						mainModule.currentState = STATE.HOST_ACCOUNT;
						mainModule.userState = USER.HOST;

					} else if (accountSelected == 1) {
						mainModule.currentState = STATE.GUEST_ACCOUNT;
						mainModule.userState = USER.GUEST;
					}
					JOptionPane.showMessageDialog(this, "You have successfully logged in");
					System.out.println("logggin in: " + model.getEmail());
					frame.dispose();
				} else if (hostLogin) {
					mainModule.currentState = STATE.HOST_ACCOUNT;
					mainModule.userState = USER.HOST;
					JOptionPane.showMessageDialog(this, "You have successfully logged in");
					System.out.println("logggin in: " + model.getEmail());
					frame.dispose();
				} else if (guestLogin) {
					mainModule.currentState = STATE.GUEST_ACCOUNT;
					mainModule.userState = USER.GUEST;
					JOptionPane.showMessageDialog(this, "You have successfully logged in");
					System.out.println("logggin in: " + model.getEmail());
					frame.dispose();
				}

				hostAccountQuery.close();
				guestAccountQuery.close();

			} else {
				JOptionPane.showMessageDialog(this, "Wrong Username & Password");
				frame.dispose();
				System.out.println("Current email: " + model.getEmail());
				System.out.println("Current pword: " + model.getPassword());
				mainModule.currentState = STATE.LOGIN;
				mainModule.userState = USER.ENQUIRER;
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
			loginQuery.close();

		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}

}
