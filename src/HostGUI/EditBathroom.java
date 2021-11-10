
package hostGUI;

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

public class EditBathroom extends JFrame{


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
	 public EditBathroom(MainModule mainModule, Controller controller, Model model) {
		//initializeHomePage();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditBathroom() {
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

		JLabel editBathroomLabel = new JLabel("Edit Bathroom");
		editBathroomLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editBathroomLabel.setBounds(217, 55, 183, 57);
		registerPanel.add(editBathroomLabel);
		
		JLabel toiletLabel = new JLabel("Toilet");
		toiletLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toiletLabel.setBounds(170, 150, 167, 34);
		registerPanel.add(toiletLabel);
		
		JLabel bathLabel = new JLabel("Bathtub");
		bathLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bathLabel.setBounds(170, 226, 167, 34);
		registerPanel.add(bathLabel);
		
		JLabel showerLabel = new JLabel("Shower");
		showerLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		showerLabel.setBounds(170, 315, 167, 34);
		registerPanel.add(showerLabel);
		
		JLabel sharedHostLabel = new JLabel("Shared Bathroom(host)");
		sharedHostLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sharedHostLabel.setBounds(170, 392, 167, 34);
		registerPanel.add(sharedHostLabel);
		
		JRadioButton toiletRadioBtn = new JRadioButton("");
		toiletRadioBtn.setBounds(364, 161, 21, 23);
		registerPanel.add(toiletRadioBtn);
		
		JRadioButton showerRadioBtn = new JRadioButton("");
		showerRadioBtn.setBounds(364, 315, 21, 23);
		registerPanel.add(showerRadioBtn);
		
		JRadioButton bathtubRadioBtn = new JRadioButton("");
		bathtubRadioBtn.setBounds(364, 237, 21, 23);
		registerPanel.add(bathtubRadioBtn);
		
		JRadioButton sharedBathroomRadioBtn = new JRadioButton("");
		sharedBathroomRadioBtn.setBounds(364, 403, 21, 23);
		registerPanel.add(sharedBathroomRadioBtn);


		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW