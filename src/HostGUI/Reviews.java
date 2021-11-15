
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

public class Reviews extends JFrame{


	private JFrame frame;
	private JTable reviewsTable;
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
	 public Reviews(MainModule mainModule, Controller controller, Model model) {
		//initializeReviews();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeReviews() {
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}

		JPanel reviewsPanel = new JPanel();
		reviewsPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(reviewsPanel, BorderLayout.CENTER);
		reviewsPanel.setLayout(null);

		JLabel reviewsLabel = new JLabel("Reviews");
		reviewsLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		reviewsLabel.setBounds(249, 39, 183, 57);
		reviewsPanel.add(reviewsLabel);
		
		reviewsTable = new JTable();
		reviewsTable.setBounds(55, 121, 485, 487);
		reviewsPanel.add(reviewsTable);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(20, 58, 91, 23);
		reviewsPanel.add(backButton);


		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW