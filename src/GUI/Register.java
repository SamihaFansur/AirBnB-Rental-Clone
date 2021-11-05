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
	private JTextField addressLine1Field;
	private JTextField postcodeTextField;
	private JTextField houseNumberTextField;
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
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("failed to close conn");
			e.printStackTrace();
		} finally {
			System.out.println("conn closed!");
		}
		frame.dispose();
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

		JButton navHomeButton = new JButton("Home");
		navHomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.currentState = STATE.HOMEPAGE;
				MainModule.controller.drawNewView();
//				close();
			}
		});
		navBarPanel.add(navHomeButton);
		JButton navSearchButton = new JButton("Search");
		navSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.currentState = STATE.SEARCH;
				MainModule.controller.drawNewView();
//				close();
			}
		});
		navBarPanel.add(navSearchButton);

		JButton navRegisterButton = new JButton("Register");
		navRegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.currentState = STATE.SELF_REGISTRATION;
				MainModule.controller.drawNewView();
//				close();
			}
		});
		navBarPanel.add(navRegisterButton);

		JButton navLoginButton = new JButton("Login");
		navLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.currentState = STATE.LOGIN;
				MainModule.controller.drawNewView();
//				close();
			}
		});
		navBarPanel.add(navLoginButton);

		JPanel registerPanel = new JPanel();
		registerPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(registerPanel, BorderLayout.CENTER);
		registerPanel.setLayout(null);

		JLabel registerTitleLabel = new JLabel("Title");
		registerTitleLabel.setBounds(99, 57, 118, 45);
		registerPanel.add(registerTitleLabel);

		String titles[] = { "Mr", "Mrs", "Miss", "Ms", "Dr" };
		registerTitleComboBox = new JComboBox(titles);
		registerTitleComboBox.setBounds(189, 71, 276, 23);
		registerPanel.add(registerTitleComboBox);

		JLabel firstNameLabel = new JLabel("First Name");
		firstNameLabel.setBounds(99, 102, 118, 45);
		registerPanel.add(firstNameLabel);

		firstNameTextField = new JTextField();
		firstNameTextField.setBounds(189, 113, 276, 23);
		registerPanel.add(firstNameTextField);
		firstNameTextField.setColumns(10);

		setFirstName(firstNameTextField.getText());
		
		JLabel lblNewLabel = new JLabel("Surname");
		lblNewLabel.setBounds(99, 136, 118, 45);
		registerPanel.add(lblNewLabel);

		surnameTextField = new JTextField();
		surnameTextField.setBounds(189, 147, 276, 23);
		registerPanel.add(surnameTextField);
		surnameTextField.setColumns(10);

		setSurname(surnameTextField.getText());
		
		JLabel emailAddressLabel = new JLabel("Email Address");
		emailAddressLabel.setBounds(99, 173, 118, 45);
		registerPanel.add(emailAddressLabel);

		emailAddressTextField = new JTextField();
		emailAddressTextField.setColumns(10);
		emailAddressTextField.setBounds(189, 181, 276, 29);
		registerPanel.add(emailAddressTextField);
		
		setEmail(emailAddressTextField.getText());

		JLabel mobileLabel = new JLabel("Mobile Number");
		mobileLabel.setBounds(99, 229, 125, 14);
		registerPanel.add(mobileLabel);

		mobileNumberTextField = new JTextField();
		mobileNumberTextField.setColumns(10);
		mobileNumberTextField.setBounds(189, 217, 276, 29);
		registerPanel.add(mobileNumberTextField);
		
		setMobileNumber(mobileNumberTextField.getText());

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(99, 267, 75, 14);
		registerPanel.add(passwordLabel);

		passwordTextField = new JTextField();
		passwordTextField.setBounds(189, 261, 276, 26);
		registerPanel.add(passwordTextField);
		
		setPassword(passwordTextField.getText());
		
		JLabel addressLine1Label = new JLabel("Address Line 1");
		addressLine1Label.setBounds(99, 307, 125, 14);
		registerPanel.add(addressLine1Label);

		addressLine1Field = new JTextField();
		addressLine1Field.setBounds(189,  300, 276, 29);
		registerPanel.add(addressLine1Field);
		
		setFla(addressLine1Field.getText());

		JLabel houseNumberLabel = new JLabel("House Name/Number");
		houseNumberLabel.setBounds(99, 346, 125, 14);
		registerPanel.add(houseNumberLabel);

		houseNumberTextField = new JTextField();
		houseNumberTextField.setBounds(225, 340 , 276, 27);
		registerPanel.add(houseNumberTextField);
		
		setHnhn(houseNumberTextField.getText());

		JLabel postcodeLabel = new JLabel("Postcode");
		postcodeLabel.setBounds(99, 386, 125, 14);
		registerPanel.add(postcodeLabel);

		postcodeTextField = new JTextField();
		postcodeTextField.setBounds(189, 378, 276, 31);
		registerPanel.add(postcodeTextField);

		setPc(postcodeTextField.getText());
		
		JLabel accountTypeLabel = new JLabel("Register as");
		accountTypeLabel.setBounds(99, 430, 125, 14);
		registerPanel.add(accountTypeLabel);

		String accountTypes[] = { "Host", "Guest", "Both (Host & Guest)" };
		accountTypeComboBox = new JComboBox(accountTypes);
		accountTypeComboBox.setBounds(189,  426, 276, 23);
		registerPanel.add(accountTypeComboBox);

		setAccType(accountTypeComboBox.getSelectedItem().toString());
		
		registerButton.setBounds(356, 480, 91, 23);
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ALREADY HAVE GETTERS AND SETTERS BELOW
//				String firstName = firstNameTextField.getText();
//				String surname = surnameTextField.getText();
//				String email = emailAddressTextField.getText();
//				String mobile = mobileNumberTextField.getText();
//				String password = passwordTextField.getText();
//				String addressLine = addressLine1Field.getText();
//				String houseNumber = houseNumberTextField.getText();
//				String postcode = postcodeTextField.getText();
//				JComboBox accountType = (JComboBox) accountTypeComboBox.getSelectedItem();

				submit();
				close();
				Login sp = new Login(mainModule,controller,model);
			}
		});
		registerPanel.add(registerButton);

		JLabel registerLabel = new JLabel("Register");
		registerLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		registerLabel.setBounds(264, 11, 183, 57);
		registerPanel.add(registerLabel);

		JButton resetRegisterButton = new JButton("Reset");
		resetRegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerTitleComboBox.setSelectedItem("Mr");
				firstNameTextField.setText("");
				surnameTextField.setText("");
				emailAddressTextField.setText("");
				mobileNumberTextField.setText("");
				passwordTextField.setText("");
				addressLine1Field.setText("");
				houseNumberTextField.setText("");
				postcodeTextField.setText("");
				accountTypeComboBox.setSelectedItem("Host");
			}
		});
		resetRegisterButton.setBounds(220, 480, 91, 23);
		registerPanel.add(resetRegisterButton);


		frame.setBounds(100, 100, 600, 599);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void submit() {
		try {
			System.out.println("1");
			connection = ConnectionManager.getConnection();
			System.out.println("2");
			String insertQuery = "insert into ACCOUNT values(?,?,?,?,?,?,?,?,?,?)";
			System.out.println("3");
			PreparedStatement ps = connection.prepareStatement(insertQuery);
			System.out.println("4");
			ps.setString(1, getEmail());
			ps.setString(2, getTitle());
			ps.setString(3, getFirstName());
			ps.setString(4, getSurame());
			ps.setString(5, getMobileNumber());
			ps.setString(6, getPasword());
			ps.setString(7, getFla());
			ps.setString(8, getHnhn());
			ps.setString(9, getPc());
			ps.setString(10, getAccType());
			System.out.println("5");
			System.out.println(ps);
			int i  = ps.executeUpdate();
			System.out.println("6");
			if(i>0) {
				System.out.println("7");
				System.out.println(this);
				JOptionPane.showMessageDialog(this, "saved ok"); //remove later
			}
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
	

	//getters and setters:
	private String title;
	private String firstName;
	private String surname;
	private String email;
	private String mobileNumber;
	private String password;
	private String fla;
	private String pc;
	private String hnHn;
	private String accType;
	
	public void setPassword(String pasword) {
		this.password=pasword;
	}

	public String getPasword() {
		return passwordTextField.getText();
	}
	
	public void setFla(String fla) {
		this.fla=fla;
	}

	public String getFla() {
		return addressLine1Field.getText();
	}
	
	public void setPc(String pc) {
		this.pc=pc;
	}

	public String getPc() {
		return postcodeTextField.getText();
	}
	
	public void setHnhn(String hnHn) {
		this.hnHn=hnHn;
	}

	public String getHnhn() {
		return houseNumberTextField.getText();
	}
	
	public void setAccType(String accType) {
		this.accType=accType;
	}

	public String getAccType() {
		return accountTypeComboBox.getSelectedItem().toString();
	}
		
	public void setTitle(String title) {
		this.title=title;
	}

	public String getTitle() {
		return (String) registerTitleComboBox.getSelectedItem();
	}
	
	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}
	
	public String getFirstName() {
		return firstNameTextField.getText();
	}
	
	public void setSurname(String surname) {
		this.surname=surname;
	}

	public String getSurame() {
		return surnameTextField.getText();
	}
	
	public void setEmail(String email) {
		this.email=email;
	}

	public String getEmail() {
		return emailAddressTextField.getText();
	}
	
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber=mobileNumber;
	}
	
	public String getMobileNumber() {
		return mobileNumberTextField.getText();
	}

	
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW