
package HostGUI;

import java.awt.EventQueue;
import javax.swing.*;


import Controller.Controller;
import GUI.ConnectionManager;
import GUI.Login;
import GUI.MainModule;
import GUI.NavEnquirer;
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

public class HostAccount extends JFrame{


	private NavHost navForHost = new NavHost();
	private JFrame frame;
	

	Connection connection = null;

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
		//initializeHostAccount();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeHostAccount() {

		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		
		JPanel hostAccountPanel = new JPanel();
		hostAccountPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(hostAccountPanel, BorderLayout.CENTER);
		hostAccountPanel.setLayout(null);
		

		JLabel hostAccountLabel = new JLabel("Host Account");
		hostAccountLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		hostAccountLabel.setBounds(222, 53, 183, 57);
		hostAccountPanel.add(hostAccountLabel);
		
		JButton editAccountButton = new JButton("Edit Account");
		editAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.currentState=STATE.EDIT_ACCOUNT;
				MainModule.controller.drawNewView();
				frame.dispose();
				
			}
		});
		editAccountButton.setBounds(203, 177, 183, 34);
		hostAccountPanel.add(editAccountButton);
		
		JButton propertiesButton = new JButton("Properties List");
		propertiesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState = EDITPROPERTY.PROPERTIES;
				int id = 0;
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				try {

					connection = ConnectionManager.getConnection();
					
					String getHostIDOfUser = "select host_id from HostAccount where email=?";	
					
					PreparedStatement hostIDfromHostAccountTable = connection.prepareStatement(getHostIDOfUser);
					hostIDfromHostAccountTable.setString(1, model.getEmail());
					
					ResultSet h_id = hostIDfromHostAccountTable.executeQuery();
					while (h_id.next()) {
					 id = h_id.getInt(1);
					 System.out.println("host id = "+id);
					}
					
					 System.out.println("host id  after = "+id);
					 connection.close();
				}catch(Exception ex) {
					System.err.println(ex.getMessage());
				}
				 
				System.out.println(model.getEmail());
				model.setHostId(id);
				
				MainModule.controller.editPropertyView(id, 0);
				frame.dispose();
			}
		});
		propertiesButton.setBounds(203, 235, 183, 34);
		hostAccountPanel.add(propertiesButton);
		
		JButton addPropertyButton = new JButton("Add Property");
		addPropertyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState = EDITPROPERTY.EDIT_PROPERTY;
				
				int id = 0;
				try {

					connection = ConnectionManager.getConnection();
					
					String getHostIDOfUser = "select host_id from HostAccount where email=?";	
					
					PreparedStatement hostIDfromHostAccountTable = connection.prepareStatement(getHostIDOfUser);
					hostIDfromHostAccountTable.setString(1, model.getEmail());
					
					ResultSet h_id = hostIDfromHostAccountTable.executeQuery();
					while (h_id.next()) {
					 id = h_id.getInt(1);
					 System.out.println("host id = "+id);
					}
					connection.close();
				}catch(Exception ex) {
					System.err.println(ex.getMessage());
				}
				 
				System.out.println(model.getEmail());
				model.setHostId(id);
				
				
				
				System.out.println("IN HOST ACCOUNT, HOST ID:: "+model.getHostId());
				MainModule.controller.editPropertyView(0, model.getHostId());
				frame.dispose();
			}
		});
		addPropertyButton.setBounds(203, 294, 183, 34);
		hostAccountPanel.add(addPropertyButton);
		
		JButton bookingsButton = new JButton("Bookings List");
		bookingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState=EDITPROPERTY.BOOKINGS;
				int id = 0;
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				try {

					connection = ConnectionManager.getConnection();
					
					String getHostIDOfUser = "select host_id from HostAccount where email=?";	
					
					PreparedStatement hostIDfromHostAccountTable = connection.prepareStatement(getHostIDOfUser);
					hostIDfromHostAccountTable.setString(1, model.getEmail());
					
					ResultSet h_id = hostIDfromHostAccountTable.executeQuery();
					while (h_id.next()) {
					 id = h_id.getInt(1);
					 System.out.println("host id = "+id);
					}
					
					 System.out.println("host id  after = "+id);
					 connection.close();
				}catch(Exception ex) {
					System.err.println(ex.getMessage());
				}
				 
				System.out.println(model.getEmail());
				model.setHostId(id);
				
				MainModule.controller.editPropertyView(0, id);
				frame.dispose();
				
			}
		});
		bookingsButton.setBounds(203, 351, 183, 34);
		hostAccountPanel.add(bookingsButton);
		
		JButton provisionalBookingsButton = new JButton("Provisional Bookings");
		provisionalBookingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState=EDITPROPERTY.PROVISIONAL_BOOKINGS;
				int id = 0;
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				try {

					connection = ConnectionManager.getConnection();
					
					String getHostIDOfUser = "select host_id from HostAccount where email=?";	
					
					PreparedStatement hostIDfromHostAccountTable = connection.prepareStatement(getHostIDOfUser);
					hostIDfromHostAccountTable.setString(1, model.getEmail());
					
					ResultSet h_id = hostIDfromHostAccountTable.executeQuery();
					while (h_id.next()) {
					 id = h_id.getInt(1);
					 System.out.println("host id = "+id);
					}
					
					 System.out.println("host id  after = "+id);
					 connection.close();
				}catch(Exception ex) {
					System.err.println(ex.getMessage());
				}
				 
				System.out.println(model.getEmail());
				model.setHostId(id);
				
				MainModule.controller.editPropertyView(0, id);
				frame.dispose();
			}
		});
		provisionalBookingsButton.setBounds(203, 406, 183, 34);
		hostAccountPanel.add(provisionalBookingsButton);


		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW