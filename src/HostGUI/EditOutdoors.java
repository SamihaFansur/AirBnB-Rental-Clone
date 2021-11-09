
package HostGUI;

import java.awt.EventQueue;
import javax.swing.*;

import Controller.Controller;
import GUI.Login;
import GUI.MainModule;
import GUI.MainModule.STATE;
import Model.Model;

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

public class EditOutdoors extends JFrame{


	private JFrame frame;

	public void close() {
		frame.dispose();
	}

	/**
	 * Create the application.
	 */

	 private Controller controller;
	 private Model model;
	 private MainModule mainModule;
	 public EditOutdoors(MainModule mainModule, Controller controller, Model model) {
		//initializeHomePage();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditOutdoors() {
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

		JLabel editOutdoorsLabel = new JLabel("Outdoors");
		editOutdoorsLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editOutdoorsLabel.setBounds(248, 61, 183, 57);
		registerPanel.add(editOutdoorsLabel);
		
		JLabel freeOnSiteParkingLabel = new JLabel("Free On Site Parking");
		freeOnSiteParkingLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		freeOnSiteParkingLabel.setBounds(173, 194, 167, 34);
		registerPanel.add(freeOnSiteParkingLabel);
		
		JLabel onRoadParkingLabel = new JLabel("On Road Parking");
		onRoadParkingLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		onRoadParkingLabel.setBounds(173, 250, 167, 34);
		registerPanel.add(onRoadParkingLabel);
		
		JLabel paidCarParkLabel = new JLabel("Paid Car Parking");
		paidCarParkLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		paidCarParkLabel.setBounds(173, 313, 167, 34);
		registerPanel.add(paidCarParkLabel);
		
		JLabel patioLabel = new JLabel("Patio");
		patioLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		patioLabel.setBounds(173, 383, 167, 34);
		registerPanel.add(patioLabel);
		
		JLabel barbequeLabel = new JLabel("Barbeque");
		barbequeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		barbequeLabel.setBounds(173, 446, 167, 34);
		registerPanel.add(barbequeLabel);
		
		JRadioButton freeOnSiteParkingRadioButton = new JRadioButton("");
		freeOnSiteParkingRadioButton.setBounds(367, 205, 21, 23);
		registerPanel.add(freeOnSiteParkingRadioButton);
		
		JRadioButton paidCarParkRadioButton = new JRadioButton("");
		paidCarParkRadioButton.setBounds(367, 321, 21, 23);
		registerPanel.add(paidCarParkRadioButton);
		
		JRadioButton onRoadParkingRadioButton = new JRadioButton("");
		onRoadParkingRadioButton.setBounds(367, 258, 21, 23);
		registerPanel.add(onRoadParkingRadioButton);
		
		JRadioButton patioRadioButton = new JRadioButton("");
		patioRadioButton.setBounds(367, 391, 21, 23);
		registerPanel.add(patioRadioButton);
		
		JRadioButton barbequeRadioButton = new JRadioButton("");
		barbequeRadioButton.setBounds(367, 454, 21, 23);
		registerPanel.add(barbequeRadioButton);


		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW