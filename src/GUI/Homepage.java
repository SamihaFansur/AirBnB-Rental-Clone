package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import Controller.Controller;
import GUI.MainModule.STATE;
import GUI.MainModule.USER;
import Model.Model;

public class Homepage extends JFrame{
	/**
	 * Create the application.
	 */
	private Controller controller;
	private Model model;
	private MainModule mainModule;
	private NavEnquirer navBeforeLogin = new NavEnquirer();
	private JFrame frame ;

	 public Homepage(MainModule mainModule, Controller controller, Model model) {
		//initializeHomePage();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	 public void initializeHomePage() {
		 if(mainModule.userState == USER.ENQUIRER) {
			 mainModule.currentState = STATE.HOMEPAGE;
				try {
					frame = new JFrame();
					navBeforeLogin.addNavBeforeLogin(frame, mainModule);
				}catch(Exception e) {
					System.err.println(e.getMessage());
				}
			JPanel homepagePanel = new JPanel();
			homepagePanel.setBackground(new Color(204, 255, 255));
			frame.getContentPane().add(homepagePanel, BorderLayout.CENTER);
			homepagePanel.setLayout(null);

			JLabel homePageLabel = new JLabel("HomeBreaks Plc");
			homePageLabel.setFont(new Font("Arial Black", Font.PLAIN, 26));
			homePageLabel.setBounds(190, -27, 250, 152);
			homepagePanel.add(homePageLabel);
			
			String description = "HomeBreaks Plc manages a collection of small properties for short-term "
								+ "lease by hosts to renting guests. This software will enable you to sign "
								+ "up as a either as a host or a guest or both.\nIf you would like to advertise "
								+ "your properties to be rented please create a host account. If you would like to "
								+ "rent properties please create a guest account. If you would like to have access to "
								+ "both advertising and renting properties you can create a joint host and guest account. "
								+ "\nEach advertised property is available for certain time periods. A property offers a "
								+ "number of facilities, where each facility is themed after a zone in the property. "
								+ "The six possible kinds of facility have standard names: sleeping, bathing, kitchen, "
								+ "living, utility and outdoors. \nIf you'd like to search from available properties use "
								+ "the 'Search' page to do so. To use the system features please login.";
	        JTextArea textArea = new JTextArea(description);  
	        textArea.setLineWrap(true);
			textArea.setBounds(75,100,430,400);		
			textArea.setFont(new Font("Arial", Font.PLAIN, 16));	
			homepagePanel.add(textArea);
			
			frame.setBounds(100, 100, 600, 700);
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		 }
		 else if (mainModule.userState==USER.HOST) {
		 }
		 else if (mainModule.userState==USER.GUEST) {
		 }
	}
}
