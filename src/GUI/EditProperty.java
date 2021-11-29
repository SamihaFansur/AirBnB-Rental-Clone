package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Controller.Controller;
import GUI.MainModule.STATE;
import Model.Model;

public class EditProperty extends JFrame{

	Connection connection = null;

	private JTextField descriptionTextField = new JTextField();
	private JTextField shortNameTextField;
	private JTextField guestCapacityTextField;

	private Controller controller;
	private Model model;
	private MainModule mainModule;
	private NavEnquirer navBeforeLogin = new NavEnquirer();
	private JFrame frame ;
	private int id;
	private String houseNameNumber;
	private String postcode;
	private String shortName;

	 public int getId() {
	        return id;
	    }
	    public void setId(int id) {
	        this.id = id;
	    }
	    public String getHouseNameNumber() {
	        return houseNameNumber;
	    }
	    public void setHouseNameNumber(String houseNameNumber) {
	        this.houseNameNumber = houseNameNumber;
	    }
	    public String getPostcode() {
	        return postcode;
	    }
	    public void setPostcode(String postcode) {
	        this.postcode = postcode;
	    }
	    public String getShortName() {
	        return shortName;
	    }
	    public void setShortName(String shortName) {
	        this.shortName = shortName;
	    }




	public EditProperty(MainModule mainModule, Controller controller, Model model) {
		initializeProperty();
		this.mainModule=mainModule;
		this.controller=controller;
		this.model=model;
	}



	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeProperty() {
		mainModule.currentState = STATE.PROPERTIES;
		try {
			frame = new JFrame();
			navBeforeLogin.addNavBeforeLogin(frame, mainModule);

		}catch(Exception e) {
			System.err.println(e.getMessage());
		}

		JPanel propertyPanel = new JPanel();

		propertyPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(propertyPanel, BorderLayout.CENTER);
		propertyPanel.setLayout(null);

		JLabel propertyLabel = new JLabel("Property");
		propertyLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		propertyLabel.setBounds(253, 73, 118, 31);
		propertyPanel.add(propertyLabel);

		JLabel shortNameLabel = new JLabel("Short Name:");
		shortNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		shortNameLabel.setBounds(127, 194, 167, 34);
		propertyPanel.add(shortNameLabel);

		JLabel guestCapacityLabel = new JLabel("Guest Capacity:");
		guestCapacityLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		guestCapacityLabel.setBounds(127, 321, 167, 34);
		propertyPanel.add(guestCapacityLabel);

		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(48, 59, 91, 23);
		propertyPanel.add(backButton);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDescription.setBounds(127, 437, 167, 34);
		propertyPanel.add(lblDescription);

		JButton addKitchen = new JButton("Save");
		addKitchen.setBounds(259, 593, 91, 23);
		propertyPanel.add(addKitchen);

		descriptionTextField = new JTextField();
		descriptionTextField.setBounds(127, 471, 352, 109);
		propertyPanel.add(descriptionTextField);
		descriptionTextField.setColumns(10);

		shortNameTextField = new JTextField();
		shortNameTextField.setBounds(234, 202, 244, 23);
		propertyPanel.add(shortNameTextField);
		shortNameTextField.setColumns(10);

		guestCapacityTextField = new JTextField();
		guestCapacityTextField.setColumns(10);
		guestCapacityTextField.setBounds(388, 329, 91, 23);
		propertyPanel.add(guestCapacityTextField);

		frame.setBounds(100, 100, 601, 700);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
	}
}




