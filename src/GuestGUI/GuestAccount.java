
package GuestGUI;

import java.awt.EventQueue;
import javax.swing.*;


import Controller.Controller;
import GUI.Login;
import GUI.MainModule;
import GUI.NavEnquirer;
import GuestGUI.NavGuest;
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

public class GuestAccount extends JFrame{


	private NavGuest navForGuest = new NavGuest();
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
	 public GuestAccount(MainModule mainModule, Controller controller, Model model) {
		//initializeGuestAccount();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeGuestAccount() {

		try {
			frame = new JFrame();
			navForGuest.addGuestNav(frame, mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		
		JPanel registerPanel = new JPanel();
		registerPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(registerPanel, BorderLayout.CENTER);
		registerPanel.setLayout(null);
		

		JLabel guestAccountLabel = new JLabel("Guest Account");
		guestAccountLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		guestAccountLabel.setBounds(222, 53, 183, 57);
		registerPanel.add(guestAccountLabel);
		
		JButton editAccountButton = new JButton("Edit Account");
		editAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.currentState=STATE.EDIT_ACCOUNT;
				MainModule.controller.drawNewView();
				frame.dispose();
				
			}
		});
		editAccountButton.setBounds(203, 177, 183, 34);
		registerPanel.add(editAccountButton);
		
		JButton provisionalBookingsLabel = new JButton("Provisional Bookings");
		provisionalBookingsLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState = EDITPROPERTY.EDIT_PROPERTY;
				//MainModule.controller.editPropertyView(0);
				frame.dispose();
			}
		});
		provisionalBookingsLabel.setBounds(203, 269, 183, 34);
		registerPanel.add(provisionalBookingsLabel);
		
		JButton bookingsButton = new JButton("Bookings List");
		bookingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.currentState=STATE.BOOKINGS;
				MainModule.controller.drawNewView();
				frame.dispose();
				
			}
		});
		bookingsButton.setBounds(203, 351, 183, 34);
		registerPanel.add(bookingsButton);
		
		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}