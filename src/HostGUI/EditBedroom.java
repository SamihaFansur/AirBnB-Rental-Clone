
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

public class EditBedroom extends JFrame{


	private JFrame frame;
	private JTextField Bed1NoOfPeopleTextField;
	private JTextField Bed2NoOfPeopleTextField;
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
	 public EditBedroom(MainModule mainModule, Controller controller, Model model) {
		//initializeEditBedroom();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditBedroom() {
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		JPanel editBedroomPanel = new JPanel();
		editBedroomPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(editBedroomPanel, BorderLayout.CENTER);
		editBedroomPanel.setLayout(null);

		JLabel editBedroomLabel = new JLabel("Edit Bedroom");
		editBedroomLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editBedroomLabel.setBounds(248, 47, 183, 57);
		editBedroomPanel.add(editBedroomLabel);
		
		JLabel bed1Label = new JLabel("Bed 1:");
		bed1Label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bed1Label.setBounds(170, 135, 167, 34);
		editBedroomPanel.add(bed1Label);
		
		JLabel televisionLabel = new JLabel("Bed Type:");
		televisionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		televisionLabel.setBounds(199, 188, 167, 34);
		editBedroomPanel.add(televisionLabel);
		
		JLabel bed1NoOfPeopleLabel = new JLabel("No Of People");
		bed1NoOfPeopleLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bed1NoOfPeopleLabel.setBounds(199, 251, 167, 34);
		editBedroomPanel.add(bed1NoOfPeopleLabel);
		
		JLabel bed2Label = new JLabel("Bed 2:");
		bed2Label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bed2Label.setBounds(170, 310, 167, 34);
		editBedroomPanel.add(bed2Label);
		
		JLabel bed2TypeLabel = new JLabel("Bed Type");
		bed2TypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bed2TypeLabel.setBounds(199, 369, 167, 34);
		editBedroomPanel.add(bed2TypeLabel);
		
		JRadioButton bed1RadioBtn = new JRadioButton("");
		bed1RadioBtn.setBounds(364, 146, 21, 23);
		editBedroomPanel.add(bed1RadioBtn);
		
		JRadioButton bed2RadioBtn = new JRadioButton("");
		bed2RadioBtn.setBounds(364, 310, 21, 23);
		editBedroomPanel.add(bed2RadioBtn);
		
		JLabel bed2NoOfPeopleLabel = new JLabel("No Of People");
		bed2NoOfPeopleLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bed2NoOfPeopleLabel.setBounds(199, 435, 167, 34);
		editBedroomPanel.add(bed2NoOfPeopleLabel);
		
		JComboBox Bed1TypeComboBox = new JComboBox();
		Bed1TypeComboBox.setBounds(364, 196, 67, 22);
		editBedroomPanel.add(Bed1TypeComboBox);
		
		JComboBox Bed2TypeComboBox_1 = new JComboBox();
		Bed2TypeComboBox_1.setBounds(364, 377, 67, 22);
		editBedroomPanel.add(Bed2TypeComboBox_1);
		
		Bed1NoOfPeopleTextField = new JTextField();
		Bed1NoOfPeopleTextField.setBounds(364, 260, 67, 20);
		editBedroomPanel.add(Bed1NoOfPeopleTextField);
		Bed1NoOfPeopleTextField.setColumns(10);
		
		Bed2NoOfPeopleTextField = new JTextField();
		Bed2NoOfPeopleTextField.setColumns(10);
		Bed2NoOfPeopleTextField.setBounds(364, 444, 67, 20);
		editBedroomPanel.add(Bed2NoOfPeopleTextField);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(22, 69, 91, 23);
		editBedroomPanel.add(backButton);
		
		JButton addBedrooms = new JButton("Save");
		addBedrooms.setBounds(264, 518, 91, 23);
		editBedroomPanel.add(addBedrooms);


		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW