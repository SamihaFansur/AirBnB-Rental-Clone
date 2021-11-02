package GUI;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class homePage extends JFrame{

	private JFrame frame;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
					homePage window = new homePage();
					window.frame.setVisible(true);
	}
	

	 public void close() {
		 	frame.dispose();
	    }	
	 

	/**	
	 * Create the application.
	 */
	 
	public homePage() {
		initializeHomePage();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeHomePage() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(204, 255, 255));
		
		JPanel navBarPanel = new JPanel();
		navBarPanel.setBackground(new Color(51, 255, 255));
		frame.getContentPane().add(navBarPanel, BorderLayout.NORTH);
		
		JButton navHomeButton = new JButton("Home");
		navHomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				homePage sp = new homePage();
			}
		});
		navBarPanel.add(navHomeButton);
	
		
		JButton navSearchButton = new JButton("Search");
		navSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				search sp = new search();
			}
		});
		navBarPanel.add(navSearchButton);
		
		JButton navRegisterButton = new JButton("Register");
		navRegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				register sp = new register();
			}
		});
		navBarPanel.add(navRegisterButton);
		
		JButton navLoginButton = new JButton("Login");
		navLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						close();
						login sp = new login();
			}
		});
		navBarPanel.add(navLoginButton);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(loginPanel, BorderLayout.CENTER);
		loginPanel.setLayout(null);
		
		JLabel homePageLabel = new JLabel("Home Page");
		homePageLabel.setFont(new Font("Arial Black", Font.PLAIN, 26));
		homePageLabel.setBounds(202, -27, 222, 152);
		loginPanel.add(homePageLabel);
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		frame.setVisible(true);
	}
}
