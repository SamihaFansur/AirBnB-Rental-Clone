package GuestGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Controller.Controller;
import GUI.ConnectionManager;
import GUI.MainModule;
import GUI.NavEnquirer;
import Model.Model;

/*
 * GUI that allows user to leave a review for a booking
 */
public class Review extends JFrame {
	//Gets a connection to the database
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
	private int hostId;
	private double reviewRating;
	
	private Controller controller;
	private Model model;
	private MainModule mainModule;
	private NavEnquirer navBeforeLogin = new NavEnquirer();
	private JFrame frame;

	//Constructor for a review 
	public Review(MainModule mainModule, Controller controller, Model model) {
		this.mainModule = mainModule;
		this.controller = controller;
		this.model = model;
	}

	/**
	 * Initialize the contents of the frame so that it can be called from other GUI pages.
	 */
	public void initializeReview(int pId, int bId) {
		propertyId = pId;
		booking_id = bId;
		
		//Adds a frame and a NavBar panel
		try {
			frame = new JFrame();
			navBeforeLogin.addNavBeforeLogin(frame, mainModule);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		//Adds the main review panel
		JPanel reviewPanel = new JPanel();

		//Adds labels for textfields onto the panel
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

		//Button to add review to database
		//Takes information for textfields and creates a review object within the database.
		JButton addReview = new JButton("Add Review");
		addReview.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
				connection = ConnectionManager.getConnection();
				String hostIdfromProperty = "Select host_id from Property where property_id=?";

				PreparedStatement getHosstId = connection.prepareStatement(hostIdfromProperty);
				getHosstId.setInt(1, pId);

				ResultSet gettingHostID = getHosstId.executeQuery();

				while (gettingHostID.next()) {
					hostId = gettingHostID.getInt("host_id");
				}
				}catch(Exception f) {
					System.err.println("Got an exception2!");
					System.err.println(f.getMessage());
				}
				// INSERT Review INFO INTO TEXT FIELDS on the GUI
				try {
					connection = ConnectionManager.getConnection();
					//calculate average rating for review
					Double accuracy1 = (Double.parseDouble(AccuracyRatingTextField.getText()));
					Double location1 = (Double.parseDouble(locationRatingTextField.getText()));
					Double value1 = (Double.parseDouble(valueRatingTextField.getText()));
					Double communication1 = (Double.parseDouble(communicationRatingTextField.getText()));
					Double cleanliness1 = (Double.parseDouble(cleanlinessRatingTextField.getText()));					
					Double five = 5.0;
					//Calculates the average rating from all the parameters for review
					//and displays it in review rating textField
					Double reviewRating1 = (accuracy1 + location1 + value1 + communication1 + cleanliness1 ) / five;
					

					Double reviewRating2 = ((reviewRating1 * 100.00)/100.00);

					String averageReviewRating = Double.toString(reviewRating2);
					
					//Adds a review object into the database
					String addReview = "insert into Review (property_id, booking_id,host_id, accuracy, location, valueForMoney, communication, cleanliness, description, averageRating)"
							+ " values(?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement ps_review = connection.prepareStatement(addReview);
		
					if(Double.parseDouble(AccuracyRatingTextField.getText())<=5.0 && Double.parseDouble(locationRatingTextField.getText())<=5.0 &&
							(Double.parseDouble(valueRatingTextField.getText())<=5.0)&& (Double.parseDouble(cleanlinessRatingTextField.getText())<=5.0)
							&& (Double.parseDouble(communicationRatingTextField.getText())<=5.0)) {
					ps_review.setInt(1, propertyId);
					ps_review.setInt(2, booking_id);
					ps_review.setInt(3, hostId);
					ps_review.setDouble(4, Double.parseDouble(AccuracyRatingTextField.getText()));
					ps_review.setDouble(5, Double.parseDouble(locationRatingTextField.getText()));
					ps_review.setDouble(6, Double.parseDouble(valueRatingTextField.getText()));
					ps_review.setDouble(7, Double.parseDouble(communicationRatingTextField.getText()));
					ps_review.setDouble(8, Double.parseDouble(cleanlinessRatingTextField.getText()));
					ps_review.setString(9, descriptionTextField.getText());
					ps_review.setDouble(10, Double.parseDouble(averageReviewRating));
					ps_review.executeUpdate();
					}else {
						JOptionPane.showMessageDialog(frame,"Rating must be number smaller than 5",
							    "Inane error",
							    JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception f) {
					System.err.println("Got an exception!");
					System.err.println(f.getMessage());
				}
						
				//Calculates average rating of all reviews for a host
				try {
					connection = ConnectionManager.getConnection();

					String getAverageReview = "Select avg(averageRating) as averageReviewRating from Review where host_id=?";

					PreparedStatement getAverageRatingReview = connection.prepareStatement(getAverageReview);
					getAverageRatingReview.setInt(1, hostId);

					ResultSet gettingReviewRatings = getAverageRatingReview.executeQuery();

					while (gettingReviewRatings.next()) {
						reviewRating = gettingReviewRatings.getInt(1);
					}				

					connection.close();
				} catch (Exception f) {
					System.err.println("Got an exception234!");
					System.err.println(f.getMessage());
				}
				
				
				//Checks hosts overall Review Ratings on all properties, if they average above 4.7 then 
				//host is set to superhost
				try {
					boolean superhost = false;
					
					if(reviewRating > 4.7) {
						System.out.println(reviewRating);
						superhost = true;
					}
					
					connection = ConnectionManager.getConnection();

					String updateSuperHost = "update HostAccount set superHost=? where host_id =?";

					PreparedStatement updatingSuperHostValue = connection.prepareStatement(updateSuperHost);
					updatingSuperHostValue.setBoolean(1, superhost);
					updatingSuperHostValue.setInt(2, hostId);
	
					System.out.println(updatingSuperHostValue.toString());

					updatingSuperHostValue.executeUpdate();

					connection.close();
				} catch (Exception f) {
					System.err.println("Got an exception!");
					System.err.println(f.getMessage());
				}
				
				
			}
		});
		
		//Defines the textfields and their attributes then adds them to panel.
		addReview.setFont(new Font("Tahoma", Font.PLAIN, 17));
		addReview.setBounds(217, 609, 180, 23);
		reviewPanel.add(addReview);

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
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
	}
}
