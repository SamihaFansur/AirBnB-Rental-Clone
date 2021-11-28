
package HostGUI;

import java.awt.EventQueue;
import javax.swing.*;

import Controller.Controller;
import GUI.ConnectionManager;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	 private int idAfter; 
	private int facilitiesidAfter;
	 private JButton addLiving;
	 
	 private boolean wifi, television, satellite, streaming, dvdPlayer, boardGames;
	 
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
	public void initializeEditLiving(int facilitiesId, int id) {
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}

		idAfter = id;
		facilitiesidAfter = facilitiesId;
		System.out.println("FACILITY ID FOR WHICH AM CREATING LIVING RN = "+facilitiesidAfter);
		System.out.println("id after in init edit LIVING func = "+idAfter);

		JPanel editLivingPanel = new JPanel();
		editLivingPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(editLivingPanel, BorderLayout.CENTER);
		editLivingPanel.setLayout(null);

		JLabel editLivingLabel = new JLabel("Add Living facility");
		editLivingLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editLivingLabel.setBounds(183, 54, 183, 57);
		editLivingPanel.add(editLivingLabel);
		
		try {
			connection = ConnectionManager.getConnection();

			String selectLivingRecord = "select wifi, television, satellite, streaming, "
										+ "dvdPlayer, boardGames from Living "
										+ "where living_id=?";
			
			PreparedStatement selectingLivingValues= connection.prepareStatement(selectLivingRecord);
			
			selectingLivingValues.setInt(1, id);
			ResultSet rs = selectingLivingValues.executeQuery();
			
			while (rs.next()) {
				wifi = rs.getBoolean("wifi");
                System.out.println(wifi);
                television = rs.getBoolean("television");
                System.out.println(television);
                satellite = rs.getBoolean("satellite");
                System.out.println(satellite);
                streaming = rs.getBoolean("streaming");
                System.out.println(streaming);
                dvdPlayer = rs.getBoolean("dvdPlayer");
                System.out.println(dvdPlayer);
                boardGames = rs.getBoolean("boardGames");
                System.out.println(boardGames);
            }		
			connection.close();
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
				
		JLabel wifiLabel = new JLabel("Wifi");
		wifiLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		wifiLabel.setBounds(170, 135, 167, 34);
		editLivingPanel.add(wifiLabel);

		wifiRadioBtn = new JRadioButton("Wifi", wifi);
		wifiRadioBtn.setBounds(375, 146, 21, 23);
		editLivingPanel.add(wifiRadioBtn);
		
		JLabel televisionLabel = new JLabel("Television");
		televisionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		televisionLabel.setBounds(170, 191, 167, 34);
		editLivingPanel.add(televisionLabel);

		televisionRadioBtn = new JRadioButton("Television", television);
		televisionRadioBtn.setBounds(375, 199, 21, 23);
		editLivingPanel.add(televisionRadioBtn);
		
		JLabel satelliteLabel = new JLabel("Satellite");
		satelliteLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		satelliteLabel.setBounds(170, 254, 167, 34);
		editLivingPanel.add(satelliteLabel);

		satelliteRadioBtn = new JRadioButton("Satellite", satellite);
		satelliteRadioBtn.setBounds(375, 262, 21, 23);
		editLivingPanel.add(satelliteRadioBtn);
		
		JLabel streamingLabel = new JLabel("Streaming");
		streamingLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		streamingLabel.setBounds(170, 310, 167, 34);
		editLivingPanel.add(streamingLabel);

		streamingRadioBtn = new JRadioButton("Streaming", streaming);
		streamingRadioBtn.setBounds(375, 310, 21, 23);
		editLivingPanel.add(streamingRadioBtn);
		
		JLabel dvdPlayerLabel = new JLabel("DVD Player");
		dvdPlayerLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dvdPlayerLabel.setBounds(170, 369, 167, 34);
		editLivingPanel.add(dvdPlayerLabel);
				
		dvdPlayerRadioBtn = new JRadioButton("DVD Player", dvdPlayer);
		dvdPlayerRadioBtn.setBounds(375, 380, 21, 23);
		editLivingPanel.add(dvdPlayerRadioBtn);
		
		JLabel boardGamesLabel = new JLabel("Board Games");
		boardGamesLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		boardGamesLabel.setBounds(170, 424, 167, 34);
		editLivingPanel.add(boardGamesLabel);
		
		boardGamesRadioBtn = new JRadioButton("Board Games", boardGames);
		boardGamesRadioBtn.setBounds(375, 435, 21, 23);
		editLivingPanel.add(boardGamesRadioBtn);

		addLiving = new JButton("Save");
		addLiving.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateLivingDetails();
			}
		});
		addLiving.setBounds(256, 502, 91, 23);
		editLivingPanel.add(addLiving);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(26, 76, 91, 23);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Homepage sp = new Homepage();

			  //  mainModule.currentState=STATE.EDIT_PROPERTY;
				mainModule.userState=USER.HOST;
				mainModule.editPropertyState = EDITPROPERTY.EDIT_PROPERTY_FACILITIES;
				MainModule.controller.editPropertyView(facilitiesidAfter, idAfter); //fix params
//				close();
				frame.dispose();
				
			}
		});	
		editLivingPanel.add(backButton);
		
		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void updateLivingDetails() {
		System.out.println("idafter in updateLivingDetails = "+idAfter);
		try {
			connection = ConnectionManager.getConnection();

			model.setWifi(wifiRadioBtn.isSelected());
			model.setTelevision(televisionRadioBtn.isSelected());
			model.setSatellite(satelliteRadioBtn.isSelected());
			model.setStreaming(streamingRadioBtn.isSelected());
			model.setDvdPlayer(dvdPlayerRadioBtn.isSelected());
			model.setBoardGames(boardGamesRadioBtn.isSelected());

			String updateLivingRecord = "update Living set wifi=?, television=?, "
										+ "satellite=?, streaming=?, dvdPlayer=?, boardGames=? "
										+ "where living_id=?";
			
			PreparedStatement updatingLivingValues= connection.prepareStatement(updateLivingRecord);
			updatingLivingValues.setBoolean(1, model.getWifi());
			updatingLivingValues.setBoolean(2, model.getTelevision());
			updatingLivingValues.setBoolean(3, model.getSatellite());
			updatingLivingValues.setBoolean(4, model.getStreaming());
			updatingLivingValues.setBoolean(5, model.getDvdPlayer());
			updatingLivingValues.setBoolean(6, model.getBoardGames());
			updatingLivingValues.setInt(7, idAfter);
			updatingLivingValues.executeUpdate();
			System.out.println(updatingLivingValues.toString());
			

			String updateLivingIdInFacilities = "update Facilities set Living_id=? where facilities_id=?";
			
			PreparedStatement updatingLivingIdInFacilities = connection.prepareStatement(updateLivingIdInFacilities);
			updatingLivingIdInFacilities.setInt(1, idAfter);
			updatingLivingIdInFacilities.setInt(2, facilitiesidAfter);

			updatingLivingIdInFacilities.executeUpdate();
			System.out.println(updatingLivingIdInFacilities.toString());	
			connection.close();
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
	
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW