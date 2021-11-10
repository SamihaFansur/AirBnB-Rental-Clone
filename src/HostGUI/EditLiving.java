
package HostGUI;

import java.awt.EventQueue;
import javax.swing.*;

import Controller.Controller;
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
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class EditLiving extends JFrame{


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
	 public EditLiving(MainModule mainModule, Controller controller, Model model) {
		//initializeHomePage();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditLiving() {
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

		JLabel editLivingLabel = new JLabel("Living");
		editLivingLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editLivingLabel.setBounds(248, 47, 183, 57);
		registerPanel.add(editLivingLabel);
		
		JLabel wifiLabel = new JLabel("Wifi");
		wifiLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		wifiLabel.setBounds(170, 135, 167, 34);
		registerPanel.add(wifiLabel);
		
		JLabel televisionLabel = new JLabel("Television");
		televisionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		televisionLabel.setBounds(170, 191, 167, 34);
		registerPanel.add(televisionLabel);
		
		JLabel satelliteLabel = new JLabel("Satellite");
		satelliteLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		satelliteLabel.setBounds(170, 254, 167, 34);
		registerPanel.add(satelliteLabel);
		
		JLabel streamingLabel = new JLabel("Streaming");
		streamingLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		streamingLabel.setBounds(170, 310, 167, 34);
		registerPanel.add(streamingLabel);
		
		JLabel dvdPlayerLabel = new JLabel("DVD Player");
		dvdPlayerLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dvdPlayerLabel.setBounds(170, 369, 167, 34);
		registerPanel.add(dvdPlayerLabel);
		
		JRadioButton wifiRadioBtn = new JRadioButton("");
		wifiRadioBtn.setBounds(364, 146, 21, 23);
		registerPanel.add(wifiRadioBtn);
		
		JRadioButton satelliteRadioBtn = new JRadioButton("");
		satelliteRadioBtn.setBounds(364, 262, 21, 23);
		registerPanel.add(satelliteRadioBtn);
		
		JRadioButton televisionRadioBtn = new JRadioButton("");
		televisionRadioBtn.setBounds(364, 199, 21, 23);
		registerPanel.add(televisionRadioBtn);
		
		JRadioButton streamingRadioBtn = new JRadioButton("");
		streamingRadioBtn.setBounds(364, 310, 21, 23);
		registerPanel.add(streamingRadioBtn);
		
		JRadioButton dvdPlayerRadioBtn = new JRadioButton("");
		dvdPlayerRadioBtn.setBounds(364, 380, 21, 23);
		registerPanel.add(dvdPlayerRadioBtn);
		
		JLabel boardGamesLabel = new JLabel("Board Games");
		boardGamesLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		boardGamesLabel.setBounds(170, 424, 167, 34);
		registerPanel.add(boardGamesLabel);
		
		JRadioButton boardGamesRadioBtn = new JRadioButton("");
		boardGamesRadioBtn.setBounds(364, 435, 21, 23);
		registerPanel.add(boardGamesRadioBtn);


		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW