package GUI;
import Controller.*;
import Model.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Property {

	private JFrame frame;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	
	private Controller controller;
	private Model model;
	private MainModule mainModule;
	
	public Property(MainModule mainModule, Controller controller, Model model) {
		//initializeHomePage();
		this.model=model;
		this.mainModule=mainModule;
	}


	 public void close() {
		 	frame.dispose();
	    }	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeProperty() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel navBarPanel = new JPanel();
		navBarPanel.setBackground(new Color(51, 255, 255));
		frame.getContentPane().add(navBarPanel, BorderLayout.NORTH);
		
		JButton navHomeButton = new JButton("Home");
		navHomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				//Homepage sp = new Homepage();
			}
		});
		navBarPanel.add(navHomeButton);
	
		
		JButton navSearchButton = new JButton("Search");
		navSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				//Search sp = new Search();
			}
		});
		navBarPanel.add(navSearchButton);
		
		JButton navRegisterButton = new JButton("Register");
		navRegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				//Register sp = new Register();
			}
		});
		navBarPanel.add(navRegisterButton);
		
		JButton navLoginButton = new JButton("Login");
		navLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						close();
					//	Login sp = new Login();
			}
		});
		navBarPanel.add(navLoginButton);
		
		frame.setVisible(true);
	}

}
