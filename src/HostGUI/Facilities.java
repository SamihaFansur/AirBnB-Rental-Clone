
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

public class Facilities extends JFrame{


	private JFrame frame;
	private JTable facilitiesTable;
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
	 public Facilities(MainModule mainModule, Controller controller, Model model) {
		//initializeFacilities();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeFacilities() {
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

		JLabel facilitiesLabel = new JLabel("Facilities");
		facilitiesLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		facilitiesLabel.setBounds(237, 36, 183, 57);
		registerPanel.add(facilitiesLabel);
		
		facilitiesTable = new JTable();
		facilitiesTable.setBounds(55, 121, 485, 487);
		registerPanel.add(facilitiesTable);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(20, 58, 91, 23);
		registerPanel.add(backButton);


		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW