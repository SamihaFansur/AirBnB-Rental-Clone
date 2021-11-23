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
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

import java.util.Date;
import java.time.Month;
import java.time.LocalDate;
import java.math.RoundingMode;

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
	private int hostId;
	private int faciltiesId;
	 
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
		
		
		faciltiesId = model.getFacilitiesId();
		propertyIdAfter = propertyId;
		hostId = id;
		System.out.println("PROPERTY ID IN CHARGEBANDS = "+propertyIdAfter);
		
		JPanel chargeBandsPanel = new JPanel();
		chargeBandsPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(chargeBandsPanel, BorderLayout.CENTER);
		chargeBandsPanel.setLayout(null);

		JLabel chargeBandsLabel = new JLabel("Charge bands");
		chargeBandsLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		chargeBandsLabel.setBounds(222, 53, 183, 57);
		chargeBandsPanel.add(chargeBandsLabel);
		
		Object [] colomns={"Start date", "End date", "Price per Night (�)", "Service Charge", "Cleaning Charge"};
		  DefaultTableModel model= new DefaultTableModel();
		  model.setColumnIdentifiers(colomns);
		  table.setModel(model);
		  //table.setEnabled(false);
		  
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
		  
		  JLabel lblPricePerNight= new JLabel("Price per Night (�)");
		  lblPricePerNight.setBounds(349, 259, 95, 20);
		  chargeBandsPanel.add(lblPricePerNight);

		  JLabel lblServiceCharge= new JLabel("Sevice charge per Night (�)");
		  lblServiceCharge.setBounds(349, 290, 95, 20);
		  chargeBandsPanel.add(lblServiceCharge);
		  
		  JLabel lblCleaningCharge = new JLabel("Cleaning Charge per Night (�)");
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
	            	
	            
	            //checking if strings are empty:
	            Boolean startDateIsEmpty = startDate.getText().isBlank();
	            Boolean endDateIsEmpty = endDate.getText().isBlank();
	            Boolean serviceChargeIsEmpty = serviceCharge.getText().isBlank();
	            Boolean cleaningChargeIsEmpty = cleaningCharge.getText().isBlank();
	            Boolean pricePerNightIsEmpty = pricePerNight.getText().isBlank();
	            
	            System.out.println("CODE RUNNING 1");
	            System.out.println(startDateIsEmpty+" "+endDateIsEmpty+" "+serviceChargeIsEmpty+" "+cleaningChargeIsEmpty+" "+pricePerNightIsEmpty);
	            
	            
	            
	            
	            
	            
	            
	            //checking id input fields are empty:
	            if (!startDateIsEmpty && !endDateIsEmpty && !serviceChargeIsEmpty && !cleaningChargeIsEmpty && !pricePerNightIsEmpty) {
	            	//checking date format: 	
		            Boolean startDateValidation = startDate.getText().matches("\\d{2}/\\d{2}/\\d{4}");
		            Boolean endDateValidation = endDate.getText().matches("\\d{2}/\\d{2}/\\d{4}");
		            //checking for doubles
		            Boolean pricePerNightIsDouble = checkForDouble(pricePerNight.getText());
		            Boolean cleaningChargeIsDouble = checkForDouble(cleaningCharge.getText());
		            Boolean serviceChargeIsDouble = checkForDouble(serviceCharge.getText());
		          
		            //checking if date matches DATE object format and if prices can exist as doubles:
		            if (startDateValidation && endDateValidation && pricePerNightIsDouble && cleaningChargeIsDouble && serviceChargeIsDouble) {
		            	Date formattedStartDate = parseDate(startDate.getText());
		 	            Date formattedEndDate = parseDate(endDate.getText());
		 	            //checking if start date < end date
		 	            //NOTE: cant do the following time check until dates are converted to date objects
		 	            Boolean timeCheck = formattedStartDate.before(formattedEndDate);
		 	           
		 	            //making all prices into doubles:
		 	            Double pricePerNightDoubleValidation = Double.parseDouble(pricePerNight.getText());
		 	            Double serviceChargeDoubleValidation = Double.parseDouble(serviceCharge.getText());
		 	            Double cleaningChargeDoubleValidation = Double.parseDouble(cleaningCharge.getText());
		 	            pricePerNightDoubleValidation = round(pricePerNightDoubleValidation,2);
		 	            serviceChargeDoubleValidation = round(serviceChargeDoubleValidation,2);
		 	            cleaningChargeDoubleValidation = round(cleaningChargeDoubleValidation,2);
		 	            
		 	            // checking if dates are in the year 2022
		 	           String startDateParts[] = startDate.getText().split("/");
			              String endDateParts[] = endDate.getText().split("/");
			              
			              
			              // Getting day, month, and year for start date:
			              int startDay = Integer.parseInt(startDateParts[0]);
			              int startMonth = Integer.parseInt(startDateParts[1]);
			              int startYear = Integer.parseInt(startDateParts[2]);
			              
			              
			              //getting day, month, and year for end date:
			              int endDay = Integer.parseInt(endDateParts[0]);
			              int endMonth = Integer.parseInt(endDateParts[1]);
			              int endYear = Integer.parseInt(endDateParts[2]);
			              
			       
			              // Printing the day, month, and year
			              System.out.println("Day: " + startDay);
			              System.out.println("Month: " + startMonth);
			              
			              System.out.println("Year: " + startYear);
			          
			              System.out.println("propertyidAfter in addbtn before calling addAChargeBand = "+propertyIdAfter);
			              
			              //call function to validate the date:
			              Boolean startDateAccepted = validateDate(startDay, startMonth, startYear);
			              Boolean endDateAccepted = validateDate(endDay, endMonth, endYear);
			           
		 	            
		 	            // finally checking if the start time is less than the end time:
		 	            if (timeCheck && startDateAccepted && endDateAccepted) {
		 	            	//System.out.println(pricePerNightDoubleValidation+" "+serviceChargeDoubleValidation+" "+cleaningChargeDoubleValidation);
		 	            	System.out.println("REACHED ALL SUCCESSFUL CHECKS");
		 	            	
		 	            	//checking if charge bands overlap here
		 	            	
		 	            	if(checkForOverlappingChargeBands(propertyId, formattedStartDate, formattedEndDate)) {
		 	            		System.out.println("checking for overlapping charge bands: "+checkForOverlappingChargeBands(propertyId, formattedStartDate, formattedEndDate));
		 	            		displayChargeBandNotPossibleMessage();
		 	            	}else {
		 	            		System.out.println("checking for overlapping charge bands: "+checkForOverlappingChargeBands(propertyId, formattedStartDate, formattedEndDate));
		 	            		
		 	            		 model.addRow(
					   	                   new Object[]{
					   	                         startDate.getText(), 
					   	                         endDate.getText(),
					   	                         pricePerNight.getText(),
					   	                         serviceCharge.getText(),
					   	                         cleaningCharge.getText()
					   	                      
					   	                   }
					   	                   
					   	              );
				            	  System.out.println("Start date in model: "+startDate.getText());
				            	  
				            	  addAChargeBand(propertyIdAfter);
					              //Delete form after adding data
					              startDate.setText("");
					              endDate.setText("");
					              pricePerNight.setText("");
					              serviceCharge.setText("");
					              cleaningCharge.setText("");
		 	            	}
		 	            	
		 	            }else {
		 	            	displayInvalidStartEndTimeMessage();
		 	            }
		 	            
		            }else {
		            	displayInvalidDateMessage();
			 	            
		            }
 	            	
	            }else {
	            	displayEmptyStringsMessage();
	            }
	            
	            System.out.println("CODE RUNNING 2");
	            

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
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Homepage sp = new Homepage();
				
				mainModule.currentState=STATE.HOST_ACCOUNT;
				mainModule.editPropertyState = EDITPROPERTY.PROPERTIES;
				mainModule.userState=USER.HOST;
				MainModule.controller.editPropertyView(hostId,propertyId);
//				close();
			//	model.setEditPropertyPostcode(null);
				frame.setVisible(false);
//				frame.dispose();
				
			}
		});
		
		chargeBandsPanel.add(backButton);
		

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	//returns true if charge band overlaps with an existing charge band
	public Boolean checkForOverlappingChargeBands(int propertyId, Date startDate, Date endDate) {
		Boolean chargeBandNotPossible = false;
		Statement chargeBandStmt = null;
		try {
			connection = ConnectionManager.getConnection();
			
			String getExistingChargeBandsQuery = "SELECT * FROM ChargeBands WHERE property_id = ?";
			PreparedStatement checkExistingChargeBandStmt= connection.prepareStatement(getExistingChargeBandsQuery);
			checkExistingChargeBandStmt.setInt(1, propertyId);
			ResultSet resultSet = checkExistingChargeBandStmt.executeQuery();
			
			while(resultSet.next()) {
				String startDateFromTable = resultSet.getString("startDate");
				String endDateFromTable = resultSet.getString("endDate");

				Date formattedStartDateFromTable = parseDate(startDateFromTable);
 	            Date formattedEndDateFromTable = parseDate(endDateFromTable);
 	            
				//checking if start date and end dates are equal
 	            Boolean sameDates  = startDate.equals(formattedStartDateFromTable) || endDate.equals(formattedEndDateFromTable);
 	            // check if users start date is within an existing start date and end date
 	            Boolean startDateInsideABand = startDate.after(formattedStartDateFromTable) && startDate.before(formattedEndDateFromTable);
 	            // check if users end date is within an existing start date and end date
 	            Boolean endDateInsideABand = endDate.after(formattedStartDateFromTable) && endDate.before(formattedEndDateFromTable);
 	            //check if users start date and end date is AFTER and BEFORE an existing start date and end date respectively
 	            // (checking for a charge band period being made inside another charge band period)
 	            Boolean overlappingBandCheckOne = startDate.after(formattedStartDateFromTable) && endDate.before(formattedEndDateFromTable);
 	            //check if users start date and end date is BEFORE and AFTER an existing start date and end date respectively
 	            Boolean overlappingBandCheckTwo = startDate.before(formattedStartDateFromTable) && endDate.after(formattedEndDateFromTable);

 	            //if any of the above are true the band cannot be made
 	            chargeBandNotPossible = sameDates || startDateInsideABand || endDateInsideABand || overlappingBandCheckOne || overlappingBandCheckTwo;
				if (chargeBandNotPossible) break;
				
			}
			
			checkExistingChargeBandStmt.close();
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		return chargeBandNotPossible;
	}
	
	public void addAChargeBand(int propertyId) {
        System.out.println("id fed into addAChargeBand func = "+propertyId);
        model.setPropertyId(propertyId);
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
	
	// from here:https://stackoverflow.com/questions/22326339/how-create-date-object-with-values-in-java/22326675
	 public static Date parseDate(String date) {
	     try {
	         return new SimpleDateFormat("dd/mm/yyyy").parse(date);
	     } catch (ParseException e) {
	         return null;
	     }
	  }
	 
     public Boolean validateDate(int day, int month, int year){

    	Boolean dateAccepted = false;
        if(year==2022 && month>=1 && month<=12 && day>=1) {
        	
        	switch (month) {
        	//jan
            case 1:
            	if(day<31) dateAccepted = true;
                break;
            //feb
            case 2:
            	if(day<28) dateAccepted = true;
                break;
            //march
            case 3:
            	if(day<31) dateAccepted = true;
            	break;
            //april
            case 4:
            	if(day<30) dateAccepted = true;
                break;
            //may
            case 5:
            	if(day<31) dateAccepted = true;
                break;
            //june
            case 6:
            	if(day<30) dateAccepted = true;
            	
                break;
            //july
            case 7:
            	if(day<31) dateAccepted = true;
                break;
            //aug
            case 8:
            	if(day<31) dateAccepted = true;
            	
                break;
            //sept
            case 9:
            	if(day<30) dateAccepted = true;
                break;
            //oct
            case 10:
            	if(day<31) dateAccepted = true;
                break;
            //nov
            case 11:
            	if(day<30) dateAccepted = true;
                break;
            //dec
            case 12:
            	if(day<31) dateAccepted = true;
            	break;
                
            default:
                return dateAccepted = false;
            }

        }
        else {
        	return dateAccepted = false;
        }
        
        return dateAccepted;
 
     }
     
     public void displayInvalidDateMessage(){
		 JOptionPane.showMessageDialog(this, "Please check entries for prices and dates. Prices should be numbers or decimals. Please type dates in the format DD/MM/YYYY.");
	}
     public void displayInvalidStartEndTimeMessage(){
		 JOptionPane.showMessageDialog(this, "The start date is after end date or the dates you have picked are not in 2022. ");
	}
    public boolean validatePricingFields(String money) {
    	System.out.println(" HEREE VALID");	

 		if (money.matches("[0-9]*") && (money.length() >= 1)) {
 			//System.out.println("First name contains a characters not between a-z or A-Z");
 			System.out.println(money+" IS  VALID (MONEY)");	

 			return true;
 		}
 		else {
 			System.out.println(money+" IS NOT VALID (MONEY)");	
 			return false;
 		}

 	}
    
    public void displayInvalidMoneyMessage(){
		 JOptionPane.showMessageDialog(this, "The value for service charge per night, price per night, and cleaning charge per night must be an integer.");
	}
    
    public void displayEmptyStringsMessage(){
		 JOptionPane.showMessageDialog(this, "All fields must be completed to add a chargeband");
	}
    
    public void displayChargeBandNotPossibleMessage(){
		 JOptionPane.showMessageDialog(this, "A charge band already exists between these dates. Please choose different dates.");
	}
    
    
    public Boolean checkForDouble(String money) {
    	Boolean isDouble;
    	try
    	{
    	  Double.parseDouble(money);
    	  isDouble=true;
    	}
    	catch(NumberFormatException e)
    	{
    	  isDouble= false;
    	}
    	return true;
    }
    
    //function to round double values copied from:
    // https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
	
}


//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW