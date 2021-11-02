import java.awt.Dimension;
import java.sql.*;
import java.util.*;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.BasicStroke;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet.ColorAttribute;

public class SelfRegistration extends JPanel {

	private String email;
	private String password;
	private String firstName;
	private String surname;
	private String title;
	private int mobile;
	private Boolean guest;
	private Boolean host;
	
	
	
	
	//public static void main(String[] args){
		
		
	    /*
		Connection con = null;
			
		try{
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team018?user=team018&password7854a03f");
			
			//open connection now
			
		}
		catch(Exception ex){
			ex.printStackTrace();
			
		}
		finally {
			if (con!=null) con.close();
		}
		
		System.out.println(con.isClosed());
		
		*/
		
		
		/*
		JFrame frame = new JFrame("Registration");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//f/rame.getContentPane().add()
		frame.setSize(new Dimension(800,800));
		frame.setVisible(true);
		
		
		JPanel panel = new JPanel();
	//	panel.setLayout(new GridBagLayout());
		panel.setLayout(null);

		frame.getContentPane().add(panel);
		
		// label for First Name
		JLabel firstNameText = new JLabel("First Name: ");
		panel.add(firstNameText);
		firstNameText.setBounds(10,50,100,100);
		
		// text field for first name
		JTextField fisrtNameTextField = new JTextField(20);
		firstNameText.setLabelFor(fisrtNameTextField);
		fisrtNameTextField.setBounds(80,85,200,30);
		panel.add(fisrtNameTextField);

		// label for surname
		JLabel surnameText = new JLabel("Surname: ");
		panel.add(surnameText);
		surnameText.setBounds(10,100,100,100);
		
		//text field for surname
		JTextField surameTextField = new JTextField(20);
		surnameText.setLabelFor(surameTextField);
		surameTextField.setBounds(80,135,200,30);
		panel.add(surameTextField);
		
		System.out.println(surnameText);
		
		JTextArea firstNameTextArea = new JTextArea();
		JTextArea surnameTextArea = new JTextArea("Surname: ");
		JTextArea emailText = new JTextArea("Email: ");
		JTextArea passwordText = new JTextArea("Password: ");
		JTextArea titleDropDown = new JTextArea("Choose your title: ");
		JTextArea mobileInteger = new JTextArea("Mobile: ");
		
		panel.add(firstNameTextArea);
		// guest or host should be boolean so use radio buttons.
		
		
		
		//panel.flowlayout(grid); //change this
		//panel.add(somecomponent, BorderLayout.CENTER);
		//panel.add(ANOTHERCOMPONENT, BorderLayout.CENTER);
		
		*/
	//}
	
	//creating the Frame and window now:
	public void updateView() {
		System.out.println("in self registration");
		JFrame frame = new JFrame("Registration");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//f/rame.getContentPane().add()
		frame.setSize(new Dimension(800,800));
		frame.setVisible(true);
		
		
		JPanel panel = new JPanel();
	//	panel.setLayout(new GridBagLayout());
		panel.setLayout(null);

		frame.getContentPane().add(panel);
		
		// label for First Name
		JLabel firstNameText = new JLabel("First Name: ");
		panel.add(firstNameText);
		firstNameText.setBounds(10,50,100,100);
		
		// text field for first name
		JTextField fisrtNameTextField = new JTextField(20);
		firstNameText.setLabelFor(fisrtNameTextField);
		fisrtNameTextField.setBounds(80,85,200,30);
		panel.add(fisrtNameTextField);

		// label for surname
		JLabel surnameText = new JLabel("Surname: ");
		panel.add(surnameText);
		surnameText.setBounds(10,100,100,100);
		
		//text field for surname
		JTextField surameTextField = new JTextField(20);
		surnameText.setLabelFor(surameTextField);
		surameTextField.setBounds(80,135,200,30);
		panel.add(surameTextField);
		
		System.out.println(surnameText);
		
		JTextArea firstNameTextArea = new JTextArea();
		JTextArea surnameTextArea = new JTextArea("Surname: ");
		JTextArea emailText = new JTextArea("Email: ");
		JTextArea passwordText = new JTextArea("Password: ");
		JTextArea titleDropDown = new JTextArea("Choose your title: ");
		JTextArea mobileInteger = new JTextArea("Mobile: ");
		
		panel.add(firstNameTextArea);
		// guest or host should be boolean so use radio buttons.
		
		JButton submitButton = new JButton("Submit");
		submitButton.setBounds(80,165,200,30);
		submitButton.setVisible(true);
		panel.add(submitButton);
		
		//panel.flowlayout(grid); //change this
		//panel.add(somecomponent, BorderLayout.CENTER);
		//panel.add(ANOTHERCOMPONENT, BorderLayout.CENTER);
		
	}
	
	
	
	
}

