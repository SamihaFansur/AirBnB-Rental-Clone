
package HostGUI;

import java.awt.EventQueue;
import javax.swing.*;

import Controller.Controller;
import GUI.ConnectionManager;
import GUI.Login;
import GUI.MainModule;
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
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class EditLiving extends JFrame{


	private JFrame frame;
	private NavHost navForHost = new NavHost();

	public void close() {
		frame.dispose();
	}

	/**
	 * Create the application.
	 */

	 private Controller controller;
	 private Model model;
	 private MainModule mainModule;
	 private JRadioButton wifiRadioBtn;
	 private JRadioButton satelliteRadioBtn;
	 private JRadioButton televisionRadioBtn;
	 private JRadioButton streamingRadioBtn;
	 private JRadioButton dvdPlayerRadioBtn;
	 private JRadioButton boardGamesRadioBtn;	 
	 private JButton addLiving;
	 
	Connection connection = null;
	
	 public EditLiving(MainModule mainModule, Controller controller, Model model) {
		//initializeEditLiving();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditLiving() {
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		JPanel registerPanel = new JPanel();
		registerPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(registerPanel, BorderLayout.CENTER);
		registerPanel.setLayout(null);

		JLabel editLivingLabel = new JLabel("Add Living facility");
		editLivingLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editLivingLabel.setBounds(183, 54, 183, 57);
		registerPanel.add(editLivingLabel);
		
		JLabel wifiLabel = new JLabel("Wifi");
		wifiLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		wifiLabel.setBounds(170, 135, 167, 34);
		registerPanel.add(wifiLabel);

		wifiRadioBtn = new JRadioButton("Wifi", false);
		wifiRadioBtn.setBounds(375, 146, 21, 23);
		registerPanel.add(wifiRadioBtn);
		
		JLabel televisionLabel = new JLabel("Television");
		televisionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		televisionLabel.setBounds(170, 191, 167, 34);
		registerPanel.add(televisionLabel);

		televisionRadioBtn = new JRadioButton("Television", false);
		televisionRadioBtn.setBounds(375, 199, 21, 23);
		registerPanel.add(televisionRadioBtn);
		
		JLabel satelliteLabel = new JLabel("Satellite");
		satelliteLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		satelliteLabel.setBounds(170, 254, 167, 34);
		registerPanel.add(satelliteLabel);

		satelliteRadioBtn = new JRadioButton("Satellite", false);
		satelliteRadioBtn.setBounds(375, 262, 21, 23);
		registerPanel.add(satelliteRadioBtn);
		
		JLabel streamingLabel = new JLabel("Streaming");
		streamingLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		streamingLabel.setBounds(170, 310, 167, 34);
		registerPanel.add(streamingLabel);

		streamingRadioBtn = new JRadioButton("Streaming", false);
		streamingRadioBtn.setBounds(375, 310, 21, 23);
		registerPanel.add(streamingRadioBtn);
		
		JLabel dvdPlayerLabel = new JLabel("DVD Player");
		dvdPlayerLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dvdPlayerLabel.setBounds(170, 369, 167, 34);
		registerPanel.add(dvdPlayerLabel);
				
		dvdPlayerRadioBtn = new JRadioButton("DVD Player", false);
		dvdPlayerRadioBtn.setBounds(375, 380, 21, 23);
		registerPanel.add(dvdPlayerRadioBtn);
		
		JLabel boardGamesLabel = new JLabel("Board Games");
		boardGamesLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		boardGamesLabel.setBounds(170, 424, 167, 34);
		registerPanel.add(boardGamesLabel);
		
		boardGamesRadioBtn = new JRadioButton("Board Games", false);
		boardGamesRadioBtn.setBounds(375, 435, 21, 23);
		registerPanel.add(boardGamesRadioBtn);

		addLiving = new JButton("Save");
		addLiving.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addLivingDetails();
			}
		});
		addLiving.setBounds(256, 502, 91, 23);
		registerPanel.add(addLiving);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(26, 76, 91, 23);
		registerPanel.add(backButton);
		
		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void addLivingDetails() {
		try {
			connection = ConnectionManager.getConnection();

			model.setWifi(wifiRadioBtn.isSelected());
			model.setTelevision(televisionRadioBtn.isSelected());
			model.setSatellite(satelliteRadioBtn.isSelected());
			model.setStreaming(streamingRadioBtn.isSelected());
			model.setDvdPlayer(dvdPlayerRadioBtn.isSelected());
			model.setBoardGames(boardGamesRadioBtn.isSelected());
			
			String insertLivingQuery = "insert into Living (wifi, television, satellite, "
										+ "streaming, dvdPlayer, boardGames)"
										+ " values(?,?,?,?,?,?) ";
			PreparedStatement ps_living = connection.prepareStatement(insertLivingQuery);
			
			ps_living.setBoolean(1, model.getWifi());
			ps_living.setBoolean(2, model.getTelevision());
			ps_living.setBoolean(3, model.getSatellite());
			ps_living.setBoolean(4, model.getStreaming());
			ps_living.setBoolean(5, model.getDvdPlayer());
			ps_living.setBoolean(6, model.getBoardGames());

			System.out.println(ps_living);
			ps_living.executeUpdate();
			
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
	
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW