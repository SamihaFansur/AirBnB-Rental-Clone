package GUI;
import HostGUI.*;
import GuestGUI.*;
import Controller.*;
import GUI.MainModule.STATE;
import GUI.MainModule.USER;
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

	private final JButton filterButton = new JButton("Apply Filters");
	private JTextField numberOfGuestsFilter;
	private JTextField minPriceFilter;
	private JTextField maxPriceFilter;
	private JTable propertiesTable;
		
	private Controller controller;
	private Model model;
	private MainModule mainModule;
	private NavEnquirer navBeforeLogin;
	private NavHost navHost;
	private NavGuest navGuest;
	private JFrame frame ;
	
	public Search(MainModule mainModule, Controller controller, Model model) {
		//initializeSearch();
		this.controller=controller;
		this.model=model;
		this.mainModule=mainModule;
	}

	 public void close() {
		 	this.frame.dispose();
	    }	
	/**
	 * Initialize the contents of the frame.
	 */
	 public void initializeSearch() {
		 mainModule.currentState = STATE.SEARCH;
		 if(mainModule.userState==USER.ENQUIRER) {
			try {
				frame = new JFrame();
				navBeforeLogin = new NavEnquirer();
				navBeforeLogin.addNavBeforeLogin(frame, mainModule);
				
			}catch(Exception e) {
				System.err.println(e.getMessage());
			}
		 }else if(mainModule.userState==USER.HOST) {
			 try {
					frame = new JFrame();
					navHost = new NavHost();
					navHost.addHostNav(frame, mainModule);
				
				}catch(Exception e) {
					System.err.println(e.getMessage());
				}
		 } else if (mainModule.userState == USER.GUEST) {
			 try {

					frame = new JFrame();
					navGuest = new NavGuest();
					navGuest.addGuestNav(frame, mainModule);
					
					}catch(Exception e) {
						System.err.println(e.getMessage());
					}
		 }
			
			JPanel searchPanel = new JPanel();
			searchPanel.setBackground(new Color(204, 255, 255));
			frame.getContentPane().add(searchPanel, BorderLayout.CENTER);
			searchPanel.setLayout(null);
			
			JLabel minPriceFilterLabel = new JLabel("Min Price Per Night (�)");
			minPriceFilterLabel.setBounds(30, 45, 130, 45);
			searchPanel.add(minPriceFilterLabel);

			minPriceFilter = new JTextField();
			minPriceFilter.setColumns(10);
			minPriceFilter.setBounds(170, 52, 70, 31);
			searchPanel.add(minPriceFilter);
						
			JLabel numberOfGuestsLabel = new JLabel("Number of Guests");
			numberOfGuestsLabel.setBounds(30, 109, 130, 20);
			searchPanel.add(numberOfGuestsLabel);
			
			numberOfGuestsFilter = new JTextField();
			numberOfGuestsFilter.setBounds(170, 106, 70, 31);
			searchPanel.add(numberOfGuestsFilter);
			numberOfGuestsFilter.setColumns(10);

			JLabel maxPriceFilterLabel = new JLabel("Max Price Per Night (�)");
			maxPriceFilterLabel.setBounds(277, 55, 130, 20);
			searchPanel.add(maxPriceFilterLabel);

			maxPriceFilter = new JTextField();
			maxPriceFilter.setColumns(10);
			maxPriceFilter.setBounds(490, 52, 70, 31);
			searchPanel.add(maxPriceFilter);
			
			JLabel locationFilterLabel = new JLabel("Location");
			locationFilterLabel.setBounds(277, 100, 115, 45);
			searchPanel.add(locationFilterLabel);
			
			JComboBox locationComboBox = new JComboBox();
			locationComboBox.setBounds(340, 106, 220, 33);
			searchPanel.add(locationComboBox);
			
						
			propertiesTable = new JTable();
			propertiesTable.setBounds(41, 252, 524, 346);
			searchPanel.add(propertiesTable);

			filterButton.setBounds(165, 182, 141, 36);
			searchPanel.add(filterButton);
			
			JButton resetFiltersButton = new JButton("Reset");
			resetFiltersButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					numberOfGuestsFilter.setText("");
					minPriceFilter.setText("");
					maxPriceFilter.setText("");
					locationComboBox.setSelectedItem("");
					//while (propertiesTable.getRowCount()>0)
					//{
						//propertiesTable.removeRow(0);
					//}
				}
			});
			resetFiltersButton.setBounds(323, 182, 141, 36);
			searchPanel.add(resetFiltersButton);
			frame.setBounds(100, 100, 600, 700);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			frame.setLocationRelativeTo(null);
			
			frame.setVisible(true);
	}
}
