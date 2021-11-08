package GUI;
import Controller.*;
import GUI.MainModule.STATE;
import Model.*;
import java.awt.EventQueue;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import java.awt.Font;

public class Register extends JFrame{

	private JFrame frame;
	private JButton registerButton = new JButton("Register");
	private JTextField passwordTextField;
	private JTextField firstNameTextField;
	private JTextField surnameTextField;
	private JTextField streetNameTextField;
	private JTextField postcodeTextField;
	private JTextField houseNumberTextField;
	private JTextField cityTextField;
	private JTextField emailAddressTextField;
	private JTextField mobileNumberTextField;
	private JComboBox accountTypeComboBox;
	private JComboBox registerTitleComboBox;
	
//	private Controller controller;
	private Model model;
	private Controller controller;
	private MainModule mainModule;
	
	Connection connection = null;

	public void close() {
		
		this.frame.dispose();
	}

	/**
	 * Create the application.
	 */

	public Register(MainModule mainModule, Controller controller, Model model) {
		//initializeRegister();
		this.mainModule=mainModule;
		this.model=model;
		this.controller=controller;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeRegister() {
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

		JPanel registerPanel = new JPanel();
		registerPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(registerPanel, BorderLayout.CENTER);
		registerPanel.setLayout(null);

		JLabel registerLabel = new JLabel("Register");
		registerLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		registerLabel.setBounds(264, 11, 183, 57);
		registerPanel.add(registerLabel);

		JLabel registerTitleLabel = new JLabel("Title");
		registerTitleLabel.setBounds(82, 79, 118, 45);
		registerPanel.add(registerTitleLabel);

		String titles[] = { "Mr", "Mrs", "Miss", "Ms", "Dr" };
		registerTitleComboBox = new JComboBox(titles);
		registerTitleComboBox.setBounds(217, 93, 276, 23);
		registerPanel.add(registerTitleComboBox);
		
		JLabel firstNameLabel = new JLabel("First Name");
		firstNameLabel.setBounds(82, 124, 118, 45);
		registerPanel.add(firstNameLabel);

		firstNameTextField = new JTextField();
		firstNameTextField.setBounds(217, 135, 276, 23);
		registerPanel.add(firstNameTextField);
		firstNameTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Surname");
		lblNewLabel.setBounds(82, 158, 118, 45);
		registerPanel.add(lblNewLabel);

		surnameTextField = new JTextField();
		surnameTextField.setBounds(217, 169, 276, 23);
		registerPanel.add(surnameTextField);
		surnameTextField.setColumns(10);
		
		JLabel emailAddressLabel = new JLabel("Email Address");
		emailAddressLabel.setBounds(82, 195, 118, 45);
		registerPanel.add(emailAddressLabel);

		emailAddressTextField = new JTextField();
		emailAddressTextField.setColumns(10);
		emailAddressTextField.setBounds(217, 203, 276, 29);
		registerPanel.add(emailAddressTextField);
		
		JLabel mobileLabel = new JLabel("Mobile Number");
		mobileLabel.setBounds(82, 251, 125, 14);
		registerPanel.add(mobileLabel);

		mobileNumberTextField = new JTextField();
		mobileNumberTextField.setColumns(10);
		mobileNumberTextField.setBounds(217, 239, 276, 29);
		registerPanel.add(mobileNumberTextField);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(82, 289, 75, 14);
		registerPanel.add(passwordLabel);

		passwordTextField = new JTextField();
		passwordTextField.setBounds(217, 283, 276, 26);
		registerPanel.add(passwordTextField);
				
		JLabel houseNumberLabel = new JLabel("House Name/Number");
		houseNumberLabel.setBounds(82, 329, 125, 14); 
		registerPanel.add(houseNumberLabel);

		houseNumberTextField = new JTextField();
		houseNumberTextField.setBounds(217, 322, 276, 29);
		registerPanel.add(houseNumberTextField);
		
		JLabel streetNameLabel = new JLabel("Street Name");
		streetNameLabel.setBounds(82, 368, 125, 14);
		registerPanel.add(streetNameLabel);

		streetNameTextField = new JTextField();
		streetNameTextField.setBounds(217, 362, 276, 27);
		registerPanel.add(streetNameTextField);
		
		JLabel cityLabel = new JLabel("City/Town");
		cityLabel.setBounds(82, 408, 125, 14);
		registerPanel.add(cityLabel);

		cityTextField = new JTextField();
		cityTextField.setBounds(217, 400, 276, 31);
		registerPanel.add(cityTextField);

		JLabel postcodeLabel = new JLabel("Postcode");
		postcodeLabel.setBounds(82, 452, 125, 14);
		registerPanel.add(postcodeLabel);

		postcodeTextField = new JTextField();
		postcodeTextField.setBounds(217, 447, 276, 23);
		registerPanel.add(postcodeTextField);
		
		JLabel accountTypeLabel = new JLabel("Register as");
		accountTypeLabel.setBounds(82, 489, 125, 14);
		registerPanel.add(accountTypeLabel);

		String accountTypes[] = { "Host", "Guest", "Both (Host & Guest)" };
		accountTypeComboBox = new JComboBox(accountTypes);
		accountTypeComboBox.setBounds(217,  489, 276, 23);
		registerPanel.add(accountTypeComboBox);
		
		registerButton.setBounds(321, 553, 91, 23);
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submit();
				close();
				Login sp = new Login(mainModule,controller,model);
			}
		});
		registerPanel.add(registerButton);

		JButton resetRegisterButton = new JButton("Reset");
		resetRegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerTitleComboBox.setSelectedItem("Mr");
				firstNameTextField.setText("");
				surnameTextField.setText("");
				emailAddressTextField.setText("");
				mobileNumberTextField.setText("");
				passwordTextField.setText("");
				houseNumberTextField.setText("");
				streetNameTextField.setText("");
				cityTextField.setText("");
				postcodeTextField.setText("");
				accountTypeComboBox.setSelectedItem("Host");
			}
		});
		resetRegisterButton.setBounds(185, 553, 91, 23);
		registerPanel.add(resetRegisterButton);
		
		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void submit() {
		try {
			connection = ConnectionManager.getConnection();
			String insertAccountQuery = "insert into Account values(?,?,?,?,?,?,?,?)";
			String insertAddressQuery = "insert into Address values(?,?,?,?) ";
			String insertIntoHostAccountTable = "insert into HostAccount (email) "
					+ "values((SELECT email FROM Account WHERE email=?))";		
			String insertIntoGuestAccountTable = "insert into GuestAccount (email) "
					+ "values((SELECT email FROM Account WHERE email=?))";			
			
//			INSERT INTO joke(joke_text, joke_date, author_id)
//			VALUES (‘Humpty Dumpty had a great fall.’, ‘1899–03–13’, 
//			        (SELECT id FROM author WHERE author_name = ‘Famous Anthony’));

			model.setTitle(registerTitleComboBox.getSelectedItem().toString());
			model.setFirstName(firstNameTextField.getText());
			model.setSurname(surnameTextField.getText());
			model.setEmail(emailAddressTextField.getText());
			model.setMobileNumber(mobileNumberTextField.getText());
			model.setPassword(passwordTextField.getText());
			model.setHouseNameNum(houseNumberTextField.getText());
			model.setStreetName(streetNameTextField.getText());
			model.setCity(cityTextField.getText()); 
			model.setPostcode(postcodeTextField.getText());
			model.setAccountType(accountTypeComboBox.getSelectedItem().toString());
			PreparedStatement ps_account = connection.prepareStatement(insertAccountQuery);
			
			ps_account.setString(1, model.getEmail());
			ps_account.setString(2, model.getTitle());
			ps_account.setString(3, model.getFirstName());
			ps_account.setString(4, model.getSurname());
			ps_account.setString(5, model.getMobileNumber());
			ps_account.setString(6, model.getPassword());
			ps_account.setString(7, model.getHouseNameNum());
			ps_account.setString(8, model.getPostcode());

			System.out.println(ps_account);
			int i  = ps_account.executeUpdate();
			if(i>0) {
				System.out.println("7");
				System.out.println(this);
				 //remove later
			}
			PreparedStatement ps_address = connection.prepareStatement(insertAddressQuery);
			ps_address.setString(1, model.getHouseNameNum());
			ps_address.setString(2, model.getStreetName());
			ps_address.setString(3, model.getCity());
			ps_address.setString(4, model.getPostcode());
			
			int  y = ps_address.executeUpdate();
			System.out.println("6");
			if(y>0) {
				System.out.println("tryagain");
				System.out.println(this);
				JOptionPane.showMessageDialog(this, "Successful registration!"); //remove later
			}
			

			PreparedStatement ps_guestAccount = connection.prepareStatement(insertIntoGuestAccountTable);
			PreparedStatement ps_hostAccount = connection.prepareStatement(insertIntoHostAccountTable);
			
			if(model.getAccountType() == "Host") {
				ps_hostAccount.setString(1, model.getEmail());
				System.out.println(ps_hostAccount);
				
				int h  = ps_hostAccount.executeUpdate();
				if(h>0) {
					System.out.println("7");
					System.out.println(this);
					 //remove later
				}
				
			}else if (model.getAccountType() == "Guest") {
				ps_guestAccount.setString(1, model.getEmail());
				System.out.println(ps_guestAccount);
				
				int g  = ps_guestAccount.executeUpdate();
				if(g>0) {
					System.out.println("7");
					System.out.println(this);
					 //remove later
				}
			}else if(model.getAccountType() == "Both (Host & Guest)") {

				ps_hostAccount.setString(1, model.getEmail());
				System.out.println(ps_hostAccount);
				
				int h  = ps_hostAccount.executeUpdate();
				if(h>0) {
					System.out.println("7");
					System.out.println(this);
					 //remove later
				}
				
				ps_guestAccount.setString(1, model.getEmail());
				System.out.println(ps_guestAccount);
				
				int g  = ps_guestAccount.executeUpdate();
				if(g>0) {
					System.out.println("7");
					System.out.println(this);
					 //remove later
				}
			}
			
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
	
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW & MAKE ALL HEIGHTS OF TEXTBOXES SAME