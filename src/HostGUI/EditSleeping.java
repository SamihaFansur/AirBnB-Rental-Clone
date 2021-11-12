package HostGUI;

import java.awt.EventQueue;
import javax.swing.*;

import Controller.Controller;
import GUI.Login;
import GUI.MainModule;
import GUI.MainModule.EDITPROPERTY;
import GUI.MainModule.STATE;
import GUI.MainModule.USER;
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

public class EditSleeping extends JFrame{


	private JFrame frame;
	private JTextField noOfBedroomsTextField;

	public void close() {
		frame.dispose();
	}

	/**
	 * Create the application.
	 */

	 private Controller controller;
	 private Model model;
	 private MainModule mainModule;
	 public EditSleeping(MainModule mainModule, Controller controller, Model model) {
		//initializeHomePage();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditSleeping() {
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

		JButton navLogoutButton = new JButton("Logout");
		navLogoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.currentState = STATE.HOMEPAGE;
				mainModule.userState = USER.ENQUIRER;
				MainModule.controller.drawNewView();
//				close();
			}
		});
		navBarPanel.add(navLogoutButton);

		JPanel registerPanel = new JPanel();
		registerPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(registerPanel, BorderLayout.CENTER);
		registerPanel.setLayout(null);

		JLabel editSleepingLabel = new JLabel("Sleeping");
		editSleepingLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editSleepingLabel.setBounds(248, 47, 183, 57);
		registerPanel.add(editSleepingLabel);
		
		JLabel bedLinenLabel = new JLabel("Bed Linen");
		bedLinenLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bedLinenLabel.setBounds(170, 135, 167, 34);
		registerPanel.add(bedLinenLabel);
		
		JLabel towelsLabel = new JLabel("Towels");
		towelsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		towelsLabel.setBounds(170, 191, 167, 34);
		registerPanel.add(towelsLabel);
		
		JLabel noOfBedroomsLabel = new JLabel("Number Of Bedrooms");
		noOfBedroomsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		noOfBedroomsLabel.setBounds(170, 254, 167, 34);
		registerPanel.add(noOfBedroomsLabel);
		
		JRadioButton refrigeratorRadioBtn = new JRadioButton("");
		refrigeratorRadioBtn.setBounds(364, 146, 21, 23);
		registerPanel.add(refrigeratorRadioBtn);
		
		JRadioButton microwaveRadioBtn = new JRadioButton("");
		microwaveRadioBtn.setBounds(364, 199, 21, 23);
		registerPanel.add(microwaveRadioBtn);
		
		noOfBedroomsTextField = new JTextField();
		noOfBedroomsTextField.setBounds(347, 254, 106, 29);
		registerPanel.add(noOfBedroomsTextField);
		noOfBedroomsTextField.setColumns(10);
		
		JButton addBedroomButton = new JButton("Add Bedroom");
		addBedroomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState= EDITPROPERTY.EDIT_BEDROOM;
				MainModule.controller.editPropertyView();
			}
		});
		addBedroomButton.setBounds(199, 405, 209, 46);
		registerPanel.add(addBedroomButton);


		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW