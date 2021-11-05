package GUI;
import Controller.*;
import Model.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class Search extends JFrame{

	private JFrame frame;
	private final JButton filterButton = new JButton("Apply Filters");
	private JTextField numberOfGuestsFilter;
	private JTextField minPriceFilter;
	private JTextField maxPriceFilter;
	private JTable propertiesTable;
	/**
	 * @wbp.nonvisual location=718,143
	 */

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the application.
	 */
	
	private Controller controller;
	private Model model;
	private MainModule mainModule;
	public Search(MainModule mainModule, Controller controller, Model model) {
		//initializeSearch();
		this.controller=controller;
		this.model=model;
		this.mainModule=mainModule;
	}

	 public void close() {
		 	frame.dispose();
	    }	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeSearch() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		
		JPanel navBarPanel = new JPanel();
		navBarPanel.setBackground(new Color(51, 255, 255));
		frame.getContentPane().add(navBarPanel, BorderLayout.NORTH);
		
		JButton navHomeButton = new JButton("Home");
		navHomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				controller.drawNewView();
			//	Homepage sp = new Homepage();
			}
		});
		navBarPanel.add(navHomeButton);
	
		
		JButton navSearchButton = new JButton("Search");
		navSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			//	Search sp = new Search();
			}
		});
		navBarPanel.add(navSearchButton);
		
		JButton navRegisterButton = new JButton("Register");
		navRegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			//	Register sp = new Register();
			}
		});
		navBarPanel.add(navRegisterButton);
		
		JButton navLoginButton = new JButton("Login");
		navLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						close();
			//			Login sp = new Login();
			}
		});
		navBarPanel.add(navLoginButton);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(loginPanel, BorderLayout.CENTER);
		loginPanel.setLayout(null);
		
		JLabel numberOfGuestsLabel = new JLabel("Number of Guests");
		numberOfGuestsLabel.setBounds(70, 45, 115, 45);
		loginPanel.add(numberOfGuestsLabel);
		filterButton.setBounds(226, 161, 141, 36);
		loginPanel.add(filterButton);
		
		JLabel minPriceFilterLabel = new JLabel("Min Price Per Night");
		minPriceFilterLabel.setBounds(70, 109, 100, 14);
		loginPanel.add(minPriceFilterLabel);
		
		numberOfGuestsFilter = new JTextField();
		numberOfGuestsFilter.setBounds(194, 52, 63, 31);
		loginPanel.add(numberOfGuestsFilter);
		numberOfGuestsFilter.setColumns(10);
		
		JLabel maxPriceFilterLabel = new JLabel("Max Price Per Night");
		maxPriceFilterLabel.setBounds(318, 109, 100, 14);
		loginPanel.add(maxPriceFilterLabel);
		
		JLabel locationFilterLabel = new JLabel("Location");
		locationFilterLabel.setBounds(318, 45, 115, 45);
		loginPanel.add(locationFilterLabel);
		
		JComboBox locationComboBox = new JComboBox();
		locationComboBox.setBounds(423, 51, 63, 33);
		loginPanel.add(locationComboBox);
		
		minPriceFilter = new JTextField();
		minPriceFilter.setColumns(10);
		minPriceFilter.setBounds(194, 106, 63, 31);
		loginPanel.add(minPriceFilter);
		
		maxPriceFilter = new JTextField();
		maxPriceFilter.setColumns(10);
		maxPriceFilter.setBounds(423, 106, 63, 31);
		loginPanel.add(maxPriceFilter);
		
		propertiesTable = new JTable();
		propertiesTable.setBounds(97, 229, 428, 244);
		loginPanel.add(propertiesTable);
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}
}
