
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

public class EditKitchen extends JFrame{


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
	 public EditKitchen(MainModule mainModule, Controller controller, Model model) {
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

		JLabel editKitchenLabel = new JLabel("Kitchen");
		editKitchenLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editKitchenLabel.setBounds(248, 47, 183, 57);
		registerPanel.add(editKitchenLabel);
		
		JLabel refrigeratorLabel = new JLabel("Refrigerator");
		refrigeratorLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		refrigeratorLabel.setBounds(170, 135, 167, 34);
		registerPanel.add(refrigeratorLabel);
		
		JLabel microwaveLabel = new JLabel("Microwave");
		microwaveLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		microwaveLabel.setBounds(170, 191, 167, 34);
		registerPanel.add(microwaveLabel);
		
		JLabel ovenLabel = new JLabel("Oven");
		ovenLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ovenLabel.setBounds(170, 254, 167, 34);
		registerPanel.add(ovenLabel);
		
		JLabel stoveLabel = new JLabel("Stove");
		stoveLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		stoveLabel.setBounds(170, 310, 167, 34);
		registerPanel.add(stoveLabel);
		
		JLabel dishwasherLabel = new JLabel("Dishwasher");
		dishwasherLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dishwasherLabel.setBounds(170, 369, 167, 34);
		registerPanel.add(dishwasherLabel);
		
		JRadioButton refrigeratorRadioBtn = new JRadioButton("");
		refrigeratorRadioBtn.setBounds(364, 146, 21, 23);
		registerPanel.add(refrigeratorRadioBtn);
		
		JRadioButton ovenRadioBtn = new JRadioButton("");
		ovenRadioBtn.setBounds(364, 262, 21, 23);
		registerPanel.add(ovenRadioBtn);
		
		JRadioButton microwaveRadioBtn = new JRadioButton("");
		microwaveRadioBtn.setBounds(364, 199, 21, 23);
		registerPanel.add(microwaveRadioBtn);
		
		JRadioButton stoveRadioBtn = new JRadioButton("");
		stoveRadioBtn.setBounds(364, 310, 21, 23);
		registerPanel.add(stoveRadioBtn);
		
		JRadioButton dishwasherRadioBtn = new JRadioButton("");
		dishwasherRadioBtn.setBounds(364, 380, 21, 23);
		registerPanel.add(dishwasherRadioBtn);
		
		JLabel tablewareLabel = new JLabel("Tableware");
		tablewareLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tablewareLabel.setBounds(170, 424, 167, 34);
		registerPanel.add(tablewareLabel);
		
		JLabel CookwareLabel = new JLabel("Cookware");
		CookwareLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		CookwareLabel.setBounds(170, 480, 167, 34);
		registerPanel.add(CookwareLabel);
		
		JLabel lblBasicProvisions = new JLabel("Basic Provisions");
		lblBasicProvisions.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBasicProvisions.setBounds(170, 543, 167, 34);
		registerPanel.add(lblBasicProvisions);
		
		JRadioButton basicProvisionsRadioBtn = new JRadioButton("");
		basicProvisionsRadioBtn.setBounds(364, 551, 21, 23);
		registerPanel.add(basicProvisionsRadioBtn);
		
		JRadioButton tablewareRadioBtn = new JRadioButton("");
		tablewareRadioBtn.setBounds(364, 435, 21, 23);
		registerPanel.add(tablewareRadioBtn);
		
		JRadioButton cookwareRadioBtn = new JRadioButton("");
		cookwareRadioBtn.setBounds(364, 488, 21, 23);
		registerPanel.add(cookwareRadioBtn);


		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW