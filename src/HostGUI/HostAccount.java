
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

public class HostAccount extends JFrame{


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
	 public HostAccount(MainModule mainModule, Controller controller, Model model) {
		//initializeHomePage();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeHostAccount() {
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
				mainModule.userState = STATE.ENQUIRER;
				MainModule.controller.drawNewView();
//				close();
			}
		});
		navBarPanel.add(navLogoutButton);

		JPanel registerPanel = new JPanel();
		registerPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(registerPanel, BorderLayout.CENTER);
		registerPanel.setLayout(null);


		JLabel hostAccountLabel = new JLabel("HostAccount");
		hostAccountLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		hostAccountLabel.setBounds(222, 53, 183, 57);
		registerPanel.add(hostAccountLabel);
		
		JButton editAccountButton = new JButton("Edit Account");
		editAccountButton.setBounds(203, 177, 183, 34);
		registerPanel.add(editAccountButton);
		
		JButton propertiesButton = new JButton("Properties List");
		propertiesButton.setBounds(203, 235, 183, 34);
		registerPanel.add(propertiesButton);
		
		JButton addPropertyButton = new JButton("Add Property");
		addPropertyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addPropertyButton.setBounds(203, 294, 183, 34);
		registerPanel.add(addPropertyButton);
		
		JButton bookingsButton = new JButton("Bookings List");
		bookingsButton.setBounds(203, 351, 183, 34);
		registerPanel.add(bookingsButton);
		
		JButton provisionalBookingsButton = new JButton("Provisional Bookings");
		provisionalBookingsButton.setBounds(203, 406, 183, 34);
		registerPanel.add(provisionalBookingsButton);


		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW