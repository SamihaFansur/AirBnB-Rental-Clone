package HostGUI;

import java.awt.EventQueue;
import javax.swing.*;

import Controller.Controller;
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
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class EditSleeping extends JFrame{


	private JFrame frame;
	private JTextField noOfBedroomsTextField;
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
	 public EditSleeping(MainModule mainModule, Controller controller, Model model) {
		//initializeEditSleeping();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditSleeping() {
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		JPanel editSleepingPanel = new JPanel();
		editSleepingPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(editSleepingPanel, BorderLayout.CENTER);
		editSleepingPanel.setLayout(null);

		JLabel editSleepingLabel = new JLabel("Sleeping");
		editSleepingLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editSleepingLabel.setBounds(248, 47, 183, 57);
		editSleepingPanel.add(editSleepingLabel);
		
		JLabel bedLinenLabel = new JLabel("Bed Linen");
		bedLinenLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bedLinenLabel.setBounds(170, 135, 167, 34);
		editSleepingPanel.add(bedLinenLabel);
		
		JLabel towelsLabel = new JLabel("Towels");
		towelsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		towelsLabel.setBounds(170, 191, 167, 34);
		editSleepingPanel.add(towelsLabel);
		
		JLabel noOfBedroomsLabel = new JLabel("Number Of Bedrooms");
		noOfBedroomsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		noOfBedroomsLabel.setBounds(170, 254, 167, 34);
		editSleepingPanel.add(noOfBedroomsLabel);
		
		JRadioButton refrigeratorRadioBtn = new JRadioButton("");
		refrigeratorRadioBtn.setBounds(364, 146, 21, 23);
		editSleepingPanel.add(refrigeratorRadioBtn);
		
		JRadioButton microwaveRadioBtn = new JRadioButton("");
		microwaveRadioBtn.setBounds(364, 199, 21, 23);
		editSleepingPanel.add(microwaveRadioBtn);
		
		noOfBedroomsTextField = new JTextField();
		noOfBedroomsTextField.setBounds(347, 254, 106, 29);
		editSleepingPanel.add(noOfBedroomsTextField);
		noOfBedroomsTextField.setColumns(10);
		
		JButton addBedroomButton = new JButton("Add Bedroom");
		addBedroomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainModule.editPropertyState= EDITPROPERTY.EDIT_BEDROOM;
				MainModule.controller.editPropertyView(0);
			}
		});
		addBedroomButton.setBounds(199, 405, 209, 46);
		editSleepingPanel.add(addBedroomButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(27, 69, 91, 23);
		editSleepingPanel.add(backButton);
		
		JButton saveSleeping = new JButton("Save");
		saveSleeping.setBounds(248, 331, 91, 23);
		editSleepingPanel.add(saveSleeping);


		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW