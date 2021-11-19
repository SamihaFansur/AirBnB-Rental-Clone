package HostGUI;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import GUI.ConnectionManager;
import GUI.Login;
import GUI.MainModule;
import GUI.MainModule.EDITPROPERTY;
import GUI.MainModule.STATE;
import GUI.MainModule.USER;
import HostGUI.NavHost;
import Model.Model;

import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

import java.util.Date;
import java.time.Month;
import java.time.LocalDate;

public class ChargeBands extends JFrame{

	private NavHost navForHost = new NavHost();
	private JFrame frame;
	private JTextField pricePerNightTextField;
	private JTextField serviceChargeTextField;
	private JTextField cleaningChargeTextField;
	private JButton editChargebandButton;
	private JComboBox titleComboBox = new JComboBox();
	

	 private JTable table = new JTable();

	 private JTextField startDate;
	 private JTextField endDate;
	 private JTextField pricePerNight;
	 private JTextField serviceCharge;
	 private JTextField cleaningCharge;
	 
	 private String oldStartDate;
	 
	Connection connection = null;

	/**
	 * Create the application.
	 */

	 private Controller controller;
	 private Model model;
	 private MainModule mainModule;

//	private int idAfter;
	private int propertyIdAfter;
	 
	 public ChargeBands(MainModule mainModule, Controller controller, Model model) {
//		initializeChargeBands();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeChargeBands(int propertyId, int id) {
		System.out.println("IN CHARGE BANDS.JAVA");
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		propertyIdAfter = propertyId;
		System.out.println("PROPERTY ID IN CHARGEBANDS = "+propertyIdAfter);
		
		JPanel chargeBandsPanel = new JPanel();
		chargeBandsPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(chargeBandsPanel, BorderLayout.CENTER);
		chargeBandsPanel.setLayout(null);

		JLabel chargeBandsLabel = new JLabel("Charge bands");
		chargeBandsLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		chargeBandsLabel.setBounds(222, 53, 183, 57);
		chargeBandsPanel.add(chargeBandsLabel);
		
		Object [] colomns={"Start date", "End date", "Price per Night (£)", "Service Charge", "Cleaning Charge"};
		  DefaultTableModel model= new DefaultTableModel();
		  model.setColumnIdentifiers(colomns);
		  table.setModel(model);
		  
		  startDate = new JTextField();
		  startDate.setBounds(134, 198, 133, 20);
		  chargeBandsPanel.add(startDate);
		  startDate.setColumns(10);
		  
		  endDate = new JTextField();
		  endDate.setBounds(134, 229, 133, 20);
		  chargeBandsPanel.add(endDate);
		  endDate.setColumns(10);
		  
		  pricePerNight = new JTextField();
		  pricePerNight.setBounds(134, 260, 133, 20);
		  chargeBandsPanel.add(pricePerNight);
		  pricePerNight.setColumns(10);
		  
		  serviceCharge = new JTextField();
		  serviceCharge.setBounds(134, 291, 133, 20);
		  chargeBandsPanel.add(serviceCharge);
		  serviceCharge.setColumns(10);
		  
		  cleaningCharge = new JTextField();
		  cleaningCharge.setBounds(134, 321, 133, 20);
		  chargeBandsPanel.add(cleaningCharge);
		  cleaningCharge.setColumns(10);
		  
		  JLabel lblStartDate= new JLabel("Start Date");
		  lblStartDate.setBounds(349, 197, 95, 20);
		  chargeBandsPanel.add(lblStartDate);
		  
		  JLabel lblEndDate = new JLabel("End Date");
		  lblEndDate.setBounds(349, 228, 95, 20);
		  chargeBandsPanel.add(lblEndDate);
		  
		  JLabel lblPricePerNight= new JLabel("Price per Night (£)");
		  lblPricePerNight.setBounds(349, 259, 95, 20);
		  chargeBandsPanel.add(lblPricePerNight);

		  JLabel lblServiceCharge= new JLabel("Sevice charge per Night (£)");
		  lblServiceCharge.setBounds(349, 290, 95, 20);
		  chargeBandsPanel.add(lblServiceCharge);
		  
		  JLabel lblCleaningCharge = new JLabel("Cleaning Charge per Night (£)");
		  lblCleaningCharge.setBounds(349, 321, 95, 20);
		  chargeBandsPanel.add(lblCleaningCharge);
		  
		  JScrollPane scrollPane = new JScrollPane(table);
		  scrollPane.setBounds(67, 446, 464, 115);
		  chargeBandsPanel.add(scrollPane);
		  
		  JButton addButton = new JButton("Add Chargeband");
	        JButton updateButton = new JButton("Edit Chargeband");
	    
	        addButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	              //Add form data
	              model.addRow(
	                   new Object[]{
	                         startDate.getText(), 
	                         endDate.getText(),
	                         pricePerNight.getText(),
	                         serviceCharge.getText(),
	                         cleaningCharge.getText()

	                   }
	                   
	              );
	              System.out.println("START DATE ============== "+startDate.getText());
	              ///----------------for validating in dd/mm/yyyy format and to check if dates dont overlap etc
	              String dateParts[] = startDate.getText().split("/");
	              
	              // Getting day, month, and year
	              // from date
	              String day = dateParts[0];
	              String month = dateParts[1];
	              String year = dateParts[2];
	       
	              // Printing the day, month, and year
	              System.out.println("Day: " + day);
	              System.out.println("Month: " + month);
	              System.out.println("Year: " + year);
	          
	              System.out.println("propertyidAfter in addbtn before calling addAChargeBand = "+propertyIdAfter);
	              addAChargeBand(propertyIdAfter);
	              //Delete form after adding data
	              startDate.setText("");
	              endDate.setText("");
	              pricePerNight.setText("");
	              serviceCharge.setText("");
	              cleaningCharge.setText("");
	            }
	        });
		  addButton.setBounds(223, 366, 142, 23);
		  chargeBandsPanel.add(addButton);

		  updateButton.setBounds(223, 400, 142, 23);
		  chargeBandsPanel.add(updateButton);
		  
		  table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){ 
	          @Override
	          public void valueChanged(ListSelectionEvent e) {
	                int i = table.getSelectedRow();
	                startDate.setText((String)model.getValueAt(i, 0));
	                endDate.setText((String)model.getValueAt(i, 1));
	                pricePerNight.setText((String)model.getValueAt(i, 2));
	                serviceCharge.setText((String)model.getValueAt(i, 3));
	                cleaningCharge.setText((String)model.getValueAt(i, 4));
	            }
	        });
		
		  updateButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	               //Update the form
	               int i = table.getSelectedRow();
	               model.setValueAt(startDate.getText(), i, 0);
	               model.setValueAt(endDate.getText(), i, 1);
	               model.setValueAt(pricePerNight.getText(), i, 2);
	               model.setValueAt(serviceCharge.getText(), i, 3);
	               model.setValueAt(cleaningCharge.getText(), i, 4);

	               System.out.println("old start date in edit btn = "+oldStartDate);
	               updateAChargeBand(propertyIdAfter);
	            }
	        });
		
//		editChargebandButton.setBounds(245, 548, 91, 23);
//		chargeBandsPanel.add(editChargebandButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(22, 75, 91, 23);
		chargeBandsPanel.add(backButton);
		

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void addAChargeBand(int propertyId) {
        System.out.println("id fed into addAChargeBand func = "+propertyId);
		try {
			connection = ConnectionManager.getConnection();
			
			oldStartDate = startDate.getText();
			System.out.println("OG START DATE === "+oldStartDate);
			
			model.setStartDate(startDate.getText());
			model.setEndDate(endDate.getText());
			model.setPricePerNight(Double.parseDouble(pricePerNight.getText()));
			model.setServiceCharge(Double.parseDouble(serviceCharge.getText()));
			model.setCleaningCharge(Double.parseDouble(cleaningCharge.getText()));
			
			String addAChargeBandQuery = "insert into ChargeBands (property_id, startDate, endDate, "
												+ "pricePerNight, serviceCharge, cleaningCharge, totalPricePerNight)"
												+ " values(?,?,?,?,?,?,?)";
			PreparedStatement addingAchargeBand= connection.prepareStatement(addAChargeBandQuery);
			
			double totalPricePerNight = model.getPricePerNight() + model.getServiceCharge() + model.getCleaningCharge();
			
			addingAchargeBand.setInt(1, propertyId);
			addingAchargeBand.setString(2, model.getStartDate());
			addingAchargeBand.setString(3, model.getEndDate());
			addingAchargeBand.setDouble(4, model.getPricePerNight());
			addingAchargeBand.setDouble(5, model.getServiceCharge());
			addingAchargeBand.setDouble(6, model.getCleaningCharge());
			addingAchargeBand.setDouble(7, totalPricePerNight);
			
			System.out.println(addingAchargeBand);
			addingAchargeBand.executeUpdate();
						
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
	
	public void updateAChargeBand(int propertyId) {
        System.out.println("id fed into updateAChargeBand func = "+propertyId);
        System.out.println("old start date in update func = "+oldStartDate);
		try {
			connection = ConnectionManager.getConnection();

			model.setStartDate(startDate.getText());
			model.setEndDate(endDate.getText());
			model.setPricePerNight(Double.parseDouble(pricePerNight.getText()));
			model.setServiceCharge(Double.parseDouble(serviceCharge.getText()));
			model.setCleaningCharge(Double.parseDouble(cleaningCharge.getText()));

	        System.out.println("old date in update func after setting new date (should remain same as before updating) = "+oldStartDate);
	        
			String updateAChargebandQuery = "update ChargeBands set startDate=?, endDate=?, pricePerNight=?, "
											+ "serviceCharge=?, cleaningCharge=?, totalPricePerNight=? "
											+ "where property_id=? and startDate=?";
			
			PreparedStatement updatingAChargebandQuery= connection.prepareStatement(updateAChargebandQuery);

			double totalPricePerNight = model.getPricePerNight() + model.getServiceCharge() + model.getCleaningCharge();
			
			updatingAChargebandQuery.setString(1, model.getStartDate());
			updatingAChargebandQuery.setString(2, model.getEndDate());
			updatingAChargebandQuery.setDouble(3, model.getPricePerNight());
			updatingAChargebandQuery.setDouble(4, model.getServiceCharge());
			updatingAChargebandQuery.setDouble(5, model.getCleaningCharge());
			updatingAChargebandQuery.setDouble(6, totalPricePerNight);
			updatingAChargebandQuery.setInt(7, propertyId);
			updatingAChargebandQuery.setString(8, oldStartDate);
			
			System.out.println(updatingAChargebandQuery);
			updatingAChargebandQuery.executeUpdate();
						
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
	
}


//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW