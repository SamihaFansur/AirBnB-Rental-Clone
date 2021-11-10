
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

public class EditUtility extends JFrame{


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
	 public EditUtility(MainModule mainModule, Controller controller, Model model) {
		//initializeHomePage();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditUtility() {
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

		JLabel utilityLabel = new JLabel("Utility");
		utilityLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		utilityLabel.setBounds(248, 47, 183, 57);
		registerPanel.add(utilityLabel);
		
		JLabel heatingLabel = new JLabel("Heating");
		heatingLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		heatingLabel.setBounds(170, 135, 167, 34);
		registerPanel.add(heatingLabel);
		
		JLabel washingMachineLabel = new JLabel("Washing Machine");
		washingMachineLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		washingMachineLabel.setBounds(170, 188, 167, 34);
		registerPanel.add(washingMachineLabel);
		
		JLabel fireExtinguisherLabel = new JLabel("Fire Extinguisher");
		fireExtinguisherLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fireExtinguisherLabel.setBounds(170, 254, 167, 34);
		registerPanel.add(fireExtinguisherLabel);
		
		JLabel dryingMachineLabel = new JLabel("Drying Machine");
		dryingMachineLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dryingMachineLabel.setBounds(170, 310, 167, 34);
		registerPanel.add(dryingMachineLabel);
		
		JLabel smokeAlarmLabel = new JLabel("Smoke Alarm");
		smokeAlarmLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		smokeAlarmLabel.setBounds(170, 369, 167, 34);
		registerPanel.add(smokeAlarmLabel);
		
		JRadioButton heatingRadioBtn = new JRadioButton("");
		heatingRadioBtn.setBounds(364, 146, 21, 23);
		registerPanel.add(heatingRadioBtn);
		
		JRadioButton fireExtinguisherRadioBtn = new JRadioButton("");
		fireExtinguisherRadioBtn.setBounds(364, 262, 21, 23);
		registerPanel.add(fireExtinguisherRadioBtn);
		
		JRadioButton washingMachineRadioBtn = new JRadioButton("");
		washingMachineRadioBtn.setBounds(364, 199, 21, 23);
		registerPanel.add(washingMachineRadioBtn);
		
		JRadioButton dryingMachineRadioBtn = new JRadioButton("");
		dryingMachineRadioBtn.setBounds(364, 310, 21, 23);
		registerPanel.add(dryingMachineRadioBtn);
		
		JRadioButton smokeAlarmRadioBtn = new JRadioButton("");
		smokeAlarmRadioBtn.setBounds(364, 380, 21, 23);
		registerPanel.add(smokeAlarmRadioBtn);
		
		JLabel firstAidKitLabel = new JLabel("First Aid Kit");
		firstAidKitLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		firstAidKitLabel.setBounds(170, 424, 167, 34);
		registerPanel.add(firstAidKitLabel);
		
		JRadioButton firstAidKitRadioBtn = new JRadioButton("");
		firstAidKitRadioBtn.setBounds(364, 435, 21, 23);
		registerPanel.add(firstAidKitRadioBtn);


		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW