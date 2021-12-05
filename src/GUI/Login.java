package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Controller.Controller;
import GUI.MainModule.STATE;
import GUI.MainModule.USER;
import Model.Model;

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
	private int hostId;
	private int guestId;

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
			@Override
			public void actionPerformed(ActionEvent e) {

				model.setEmail(usernameField.getText());
				model.setPassword(passwordField.getText());

				userName_login = model.getEmail();
				password_login = model.getPassword();

				logUserIn();
				MainModule.controller.drawNewView();
			}

		});

		JButton resetLogin = new JButton("Reset");
		resetLogin.addActionListener(new ActionListener() {
			@Override
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
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
	}

	private PreparedStatement loginQuery = null;
	private ResultSet rs = null;

	public void logUserIn() {

		try {
			String salt = "";
			connection = ConnectionManager.getConnection();
			String getSaltQuery = "SELECT salt from Account where email = ?";
			PreparedStatement saltQuery = connection.prepareStatement(getSaltQuery);
			saltQuery.setString(1, userName_login);
			ResultSet rsSalt = saltQuery.executeQuery();
			while (rsSalt.next()) {
				salt = rsSalt.getString("salt");
			}
			String securePassword = Password.get_SHA_512_SecurePassword(password_login, salt);

			String query = "Select email, password from Account where email=? and password= ?";
			PreparedStatement loginQuery = connection
					.prepareStatement(query);

			loginQuery.setString(1, userName_login);
			loginQuery.setString(2, securePassword);
			ResultSet rs = loginQuery.executeQuery();
			/*
			 * Need to check if the email belongs to a "host and guest" account. If so, ask
			 * user to log in as either a host or a guest so GUI can be built according to
			 * this.
			 */

			if (rs.next()) {
				// checking if the username/email belongs to a "host and guest" account
				PreparedStatement hostAccountQuery = connection
						.prepareStatement("Select email from HostAccount where email=?");
				hostAccountQuery.setString(1, userName_login);
				ResultSet rsHost = hostAccountQuery.executeQuery();
				PreparedStatement guestAccountQuery = connection
						.prepareStatement("Select email from GuestAccount where email=?");
				guestAccountQuery.setString(1, userName_login);
				ResultSet rsGuest = guestAccountQuery.executeQuery();

				boolean hostLogin, guestLogin;
				hostLogin = rsHost.next();
				guestLogin = rsGuest.next();

				if (hostLogin && guestLogin) {
					String[] options = { "Host", "Guest" };
					JOptionPane.showMessageDialog(this, "You have successfully logged in");
					accountSelected = JOptionPane.showOptionDialog(this, "Please log in as a Host or Guest", "Message",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
					if (accountSelected == 0) {
						mainModule.currentState = STATE.HOST_ACCOUNT;
						mainModule.userState = USER.HOST;
						
						hostId = gettingThehostId(userName_login);
						
						
					} else if (accountSelected == 1) {
						mainModule.currentState = STATE.GUEST_ACCOUNT;
						mainModule.userState = USER.GUEST;

						guestId = gettingTheGuestId(userName_login);
						
						model.setGuestId(guestId);
						System.out.println("GUEST ID ON LOGIN = "+model.getGuestId());
						
					}
					frame.dispose();
				} else if (hostLogin) {
					mainModule.currentState = STATE.HOST_ACCOUNT;
					mainModule.userState = USER.HOST;
					
					hostId = gettingThehostId(userName_login);
					
					JOptionPane.showMessageDialog(this, "You have successfully logged in");
					frame.dispose();
				} else if (guestLogin) {
					mainModule.currentState = STATE.GUEST_ACCOUNT;
					mainModule.userState = USER.GUEST;
					
					guestId = gettingTheGuestId(userName_login);
					model.setGuestId(guestId);
					System.out.println("GUEST ID ON LOGIN = "+model.getGuestId());
					JOptionPane.showMessageDialog(this, "You have successfully logged in");
					frame.dispose();
				}

				hostAccountQuery.close();
				guestAccountQuery.close();

			} else {
				JOptionPane.showMessageDialog(this, "Wrong Username & Password");
				frame.dispose();
				mainModule.currentState = STATE.LOGIN;
				mainModule.userState = USER.ENQUIRER;
			}
			loginQuery.close();
			connection.close();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		
		
	}
	
	private int gettingThehostId(String username) {
		ResultSet rs2 = null;
		String query2 = "Select host_id from HostAccount where email=?";
//		String query = "Select email, password from Account where email=? and password= AES_ENCRYPT(?, 'key')";
		PreparedStatement loginQuery2;
		try {
			loginQuery2 = connection.prepareStatement(query2);
			loginQuery2.setString(1, userName_login);
			rs2 = loginQuery2.executeQuery();
			while (rs2.next()){
				hostId = rs2.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("THIS IS THE HOST ID IN LOGIN: "+hostId);
		model.setHostId(hostId);
		
		return hostId;
		
	}
	
	private int gettingTheGuestId(String username) {
		ResultSet rs2 = null;
		String query2 = "Select guest_id from GuestAccount where email=?";
//		String query = "Select email, password from Account where email=? and password= AES_ENCRYPT(?, 'key')";
		PreparedStatement loginQuery2;
		try {
			loginQuery2 = connection.prepareStatement(query2);
			loginQuery2.setString(1, userName_login);
			rs2 = loginQuery2.executeQuery();
			while (rs2.next()){
				guestId = rs2.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("THIS IS THE GUEST ID IN LOGIN: "+guestId);
		model.setGuestId(guestId);
		
		return guestId;
		
	}

}
