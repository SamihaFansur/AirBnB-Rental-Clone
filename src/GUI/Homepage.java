package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

//import hostGUI.*;
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

			JLabel homePageLabel = new JLabel("Home Page");
			homePageLabel.setFont(new Font("Arial Black", Font.PLAIN, 26));
			homePageLabel.setBounds(202, -27, 222, 152);
			homepagePanel.add(homePageLabel);
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
