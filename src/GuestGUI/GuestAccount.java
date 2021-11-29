
package GuestGUI;

import java.awt.EventQueue;
import javax.swing.*;


import Controller.Controller;
import GUI.ConnectionManager;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	 Connection connection = null;

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
		
		JButton provisionalBookingsButton = new JButton("Provisional Bookings");
		provisionalBookingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mainModule.editPropertyState=EDITPROPERTY.PROVISIONAL_BOOKINGS;
				int id = 0;
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				try {

					connection = ConnectionManager.getConnection();
					
					String getGuestIDOfUser = "select guest_id from GuestAccount where email=?";	
					
					PreparedStatement guestIDfromGuestAccountTable = connection.prepareStatement(getGuestIDOfUser);
					guestIDfromGuestAccountTable.setString(1, model.getEmail());
					
					ResultSet g_id = guestIDfromGuestAccountTable.executeQuery();
					while (g_id.next()) {
					 id = g_id.getInt(1);
					 System.out.println("guest id = "+id);
					}
					
					 System.out.println("guest id  after = "+id);
					 connection.close();
				}catch(Exception ex) {
					System.err.println(ex.getMessage());
				}
				 
				System.out.println(model.getEmail());
				model.setGuestId(id);
				
				MainModule.controller.editPropertyView(0, id);
				frame.dispose();
			}
		});
		provisionalBookingsButton.setBounds(203, 269, 183, 34);
		registerPanel.add(provisionalBookingsButton);
		
		JButton bookingsButton = new JButton("Bookings List");
		bookingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState=EDITPROPERTY.BOOKINGS;
				int id = 0;
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				try {

					connection = ConnectionManager.getConnection();
					
					String getGuestIDOfUser = "select guest_id from GuestAccount where email=?";	
					
					PreparedStatement guestIDfromGuestAccountTable = connection.prepareStatement(getGuestIDOfUser);
					guestIDfromGuestAccountTable.setString(1, model.getEmail());
					
					ResultSet h_id = guestIDfromGuestAccountTable.executeQuery();
					while (h_id.next()) {
					 id = h_id.getInt(1);
					 System.out.println("guest id = "+id);
					}
					
					 System.out.println("guest id  after = "+id);
					 connection.close();
				}catch(Exception ex) {
					System.err.println(ex.getMessage());
				}
				 
				System.out.println(model.getEmail());
				model.setGuestId(id);
				
				MainModule.controller.editPropertyView(0, id);
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