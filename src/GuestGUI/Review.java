package GuestGUI;
import Controller.*;
import Model.*;
import GUI.ConnectionManager;
import GUI.MainModule;
import GUI.NavEnquirer;
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


public class Review extends JFrame{



	Connection connection = null;
		 
	private JTextField descriptionTextField = new JTextField();
	private JTextField AccuracyRatingTextField = new JTextField();
	private JTextField locationRatingTextField = new JTextField();
	private JTextField valueRatingTextField = new JTextField();
	private JTextField communicationRatingTextField = new JTextField();
	private JTextField cleanlinessRatingTextField;
	private int propertyId;
	private int booking_id;
	private int Id;

	private Controller controller;
	private Model model;
	private MainModule mainModule;
	private NavEnquirer navBeforeLogin = new NavEnquirer();
	private JFrame frame ;

	
	public Review(MainModule mainModule, Controller controller, Model model) {
		//initializeReview();
		this.mainModule=mainModule;
		this.controller=controller;
		this.model=model;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeReview(int pId, int bId) {
		propertyId = pId;
    	booking_id = bId;
    	
		try {
			frame = new JFrame();
			navBeforeLogin.addNavBeforeLogin(frame, mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}	
		
		JPanel reviewPanel = new JPanel();
		
		reviewPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(reviewPanel, BorderLayout.CENTER);
		reviewPanel.setLayout(null);
		
		JLabel reviewLabel = new JLabel("Review");
		reviewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		reviewLabel.setBounds(253, 73, 118, 31);
		reviewPanel.add(reviewLabel);
		
		JLabel accuracyRatingLabel = new JLabel("Accuracy Rating");
		accuracyRatingLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		accuracyRatingLabel.setBounds(127, 148, 167, 34);
		reviewPanel.add(accuracyRatingLabel);
		
		JLabel lblLocationRating = new JLabel("Location rating");
		lblLocationRating.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLocationRating.setBounds(127, 204, 167, 34);
		reviewPanel.add(lblLocationRating);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(48, 59, 91, 23);
		reviewPanel.add(backButton);
		
		JLabel lblValueRating = new JLabel("Value Rating");
		lblValueRating.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblValueRating.setBounds(127, 267, 167, 34);
		reviewPanel.add(lblValueRating);
		
		JLabel lblCommunicationRating = new JLabel("Communication rating");
		lblCommunicationRating.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCommunicationRating.setBounds(127, 323, 167, 34);
		reviewPanel.add(lblCommunicationRating);
		
		JLabel lblCleanlinessrating = new JLabel("CleanlinessRating");
		lblCleanlinessrating.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCleanlinessrating.setBounds(127, 382, 167, 34);
		reviewPanel.add(lblCleanlinessrating);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDescription.setBounds(127, 437, 167, 34);
		reviewPanel.add(lblDescription);
		
		JButton addKitchen = new JButton("Add Review");
		addKitchen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//INSERT Property INFO INTO TEXT FIELDS
				
				try {
					
					connection = ConnectionManager.getConnection();


					String addReview = "insert into Review (property_id, booking_id, accuracy, location, valueForMoney, communication, cleanliness, description)"
												+ " values(?,?,?,?,?,?,?,?)";
					PreparedStatement ps_review = connection.prepareStatement(addReview);

					ps_review.setInt(1, propertyId);
					ps_review.setInt(2, booking_id);
					ps_review.setDouble(3, Integer.parseInt(AccuracyRatingTextField.getText()));
					ps_review.setDouble(4, Integer.parseInt(locationRatingTextField.getText()));
					ps_review.setDouble(5, Integer.parseInt(valueRatingTextField.getText()));
					ps_review.setDouble(6, Integer.parseInt(communicationRatingTextField.getText()));
					ps_review.setDouble(7, Integer.parseInt(cleanlinessRatingTextField.getText()));
					ps_review.setString(8, descriptionTextField.getText());

					System.out.println(ps_review);
					ps_review.executeUpdate();
				
				} catch(Exception f) {
					System.err.println("Got an exception!");
					System.err.println(f.getMessage());
				}
				
			}
		});
		addKitchen.setFont(new Font("Tahoma", Font.PLAIN, 17));
		addKitchen.setBounds(217, 609, 180, 23);
		reviewPanel.add(addKitchen);
		
		descriptionTextField = new JTextField();
		descriptionTextField.setBounds(127, 471, 351, 109);
		reviewPanel.add(descriptionTextField);
		descriptionTextField.setColumns(10);
		
		AccuracyRatingTextField = new JTextField();
		AccuracyRatingTextField.setBounds(359, 154, 91, 23);
		reviewPanel.add(AccuracyRatingTextField);
		AccuracyRatingTextField.setColumns(10);
		
		locationRatingTextField = new JTextField();
		locationRatingTextField.setColumns(10);
		locationRatingTextField.setBounds(359, 213, 91, 23);
		reviewPanel.add(locationRatingTextField);
		
		valueRatingTextField = new JTextField();
		valueRatingTextField.setColumns(10);
		valueRatingTextField.setBounds(359, 276, 91, 23);
		reviewPanel.add(valueRatingTextField);
		
		communicationRatingTextField = new JTextField();
		communicationRatingTextField.setColumns(10);
		communicationRatingTextField.setBounds(359, 332, 91, 23);
		reviewPanel.add(communicationRatingTextField);
		
		cleanlinessRatingTextField = new JTextField();
		cleanlinessRatingTextField.setBounds(359, 391, 91, 20);
		reviewPanel.add(cleanlinessRatingTextField);
		cleanlinessRatingTextField.setColumns(10);
		
		
		
		
		
		
		
		frame.setBounds(100, 100, 601, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
	}
}	

				
	

