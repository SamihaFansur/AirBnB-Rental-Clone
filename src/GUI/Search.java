package GUI;
import HostGUI.*;
import HostGUI.Properties;
import GuestGUI.*;
import Controller.*;
import GUI.MainModule.STATE;
import GUI.MainModule.USER;
import Model.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.ConnectionManager;
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.cj.protocol.Resultset;

import Controller.Controller;
import GUI.MainModule;
import GUI.MainModule.EDITPROPERTY;
import GUI.MainModule.STATE;
import Model.Model;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import java.awt.Font;

public class Search extends javax.swing.JFrame {
	
    /**
     * Creates new form Java_Insert_Update_Delete_Display
     */
//
	 private Controller controller;
	 private Model model;
	 private MainModule mainModule;
	 
    public Search(MainModule mainModule, Controller controller, Model model) {
    	this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
		
        initComponents();
        Show_Search_In_JTable();
    }
    
    // get the connection
    
    private static String serverName = "jdbc:mysql://stusql.dcs.shef.ac.uk/team018";
    private static String username = "team018";
    private static String pwd = "7854a03f";
 	
    public Connection getConnection()
    {
        Connection connection;
        try {
     	   connection = DriverManager.getConnection(serverName, username, pwd);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
   public static Date parseDate(String date) {
     try {
         return new SimpleDateFormat("dd/mm/yyyy").parse(date);
     } catch (ParseException e) {
         return null;
     }
  }
	 
   
 // get a list of properties from mysql database
   public ArrayList<SearchObject> getSearchList() {
	   Connection connection = getConnection();
       ArrayList<SearchObject> searchList = new ArrayList<SearchObject>();
//       System.out.println("you got this !!! cmonnn " + hostId);
       double minPPN = 24;
       double maxPPN = 27;
       int guestCap = 6;
       String sd = "03/11/2022";
       String ed = "11/11/2022"; 
       Date startd = parseDate(sd);
       Date endd = parseDate(ed);
       String placeName = "s2";//city field
       
       ///////////////////////////////////////// 1 search criteria queries/////////////////////////////////////////////
       
       //startDate
       if(minPPN == 0 && maxPPN == 0 && guestCap == 0 && sd != "" && ed =="" && placeName == "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;

    	   try {
    		   SearchObject search;
    		   
    		   String allCb = "select property_id, startDate from ChargeBands";
    		   PreparedStatement getAllCb = connection.prepareStatement(allCb);
    		   ResultSet gettingAllCb = getAllCb.executeQuery();

    		   System.out.println(getAllCb);
    		   while(gettingAllCb.next()) {
    			   if( parseDate(gettingAllCb.getString("startDate")).equals(startd) ||   startd.after(parseDate(gettingAllCb.getString("startDate")))) {
    				   propId = gettingAllCb.getInt("property_id");
    				   
    				   String propertyFromPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where property_id=?";
            		   
	   					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	   					getProperty.setInt(1, propId);
	   					
	   					ResultSet gettingProperty = getProperty.executeQuery();
	   					   
	   					while(gettingProperty.next()) {
	   						addressId = gettingProperty.getInt("address_id");
	   						
	   						String hnhnPcFromAid = "Select houseNameNumber, postcode from Address where address_id=?";
	   		        		   
	   						PreparedStatement getHnhnPc= connection.prepareStatement(hnhnPcFromAid);
	   						getHnhnPc.setInt(1, addressId);
	   						ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
	   						   
	   						while(gettingHnhnPc.next()) {
	   							houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	   							pc = gettingHnhnPc.getString("postcode");
	   							
	   							search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, pc, 
	   													gettingProperty.getString("description"), gettingProperty.getString("shortName"), 
	   													gettingProperty.getInt("guestCapacity"));
	   							searchList.add(search);
	   						}
	   					}   
    			   }
    		   }
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
       //endDate
       if(minPPN == 0 && maxPPN == 0 && guestCap == 0 && sd == "" && ed !="" && placeName == "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;

    	   try {
    		   SearchObject search;
    		   
    		   String allCb = "select property_id, endDate from ChargeBands";
    		   PreparedStatement getAllCb = connection.prepareStatement(allCb);
    		   ResultSet gettingAllCb = getAllCb.executeQuery();

    		   System.out.println(getAllCb);
    		   while(gettingAllCb.next()) {
    			   if( parseDate(gettingAllCb.getString("endDate")).equals(endd) ||   endd.before(parseDate(gettingAllCb.getString("endDate")))) {
    				   propId = gettingAllCb.getInt("property_id");
    				   
    				   String propertyFromPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where property_id=?";
            		   
	   					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	   					getProperty.setInt(1, propId);
	   					
	   					ResultSet gettingProperty = getProperty.executeQuery();
	   					   
	   					while(gettingProperty.next()) {
	   						addressId = gettingProperty.getInt("address_id");
	   						
	   						String hnhnPcFromAid = "Select houseNameNumber, postcode from Address where address_id=?";
	   		        		   
	   						PreparedStatement getHnhnPc= connection.prepareStatement(hnhnPcFromAid);
	   						getHnhnPc.setInt(1, addressId);
	   						ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
	   						   
	   						while(gettingHnhnPc.next()) {
	   							houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	   							pc = gettingHnhnPc.getString("postcode");
	   							
	   							search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, pc, 
	   													gettingProperty.getString("description"), gettingProperty.getString("shortName"), 
	   													gettingProperty.getInt("guestCapacity"));
	   							searchList.add(search);
	   						}
	   					}   
    			   }
    		   }
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
       
       //minMax 
       if(minPPN != 0 && maxPPN != 0 && guestCap == 0 && sd == "" && ed =="" && placeName == "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;
    	   try {
    		   SearchObject search;
    		   
    		   String minMaxToPid = "Select property_id from ChargeBands where totalPricePerNight>=? and totalPricePerNight<=?";
    		   PreparedStatement getPid = connection.prepareStatement(minMaxToPid);
    		   getPid.setDouble(1, minPPN);
    		   getPid.setDouble(2, maxPPN);
    		   System.out.println(getPid);
    		   ResultSet gettingPId = getPid.executeQuery();
    		   
    		   while(gettingPId.next()) {
    			   propId = gettingPId.getInt("property_id");
            	   System.out.println("property id min max = " + propId);
            	   
            	   String propertyFromPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where property_id=?";
        		   
					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
					getProperty.setInt(1, propId);
					
					ResultSet gettingProperty = getProperty.executeQuery();
					   
					while(gettingProperty.next()) {
						addressId = gettingProperty.getInt("address_id");
						
						String hnhnPcFromAid = "Select houseNameNumber, postcode from Address where address_id=?";
		        		   
						PreparedStatement getHnhnPc= connection.prepareStatement(hnhnPcFromAid);
						getHnhnPc.setInt(1, addressId);
						ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
						   
						while(gettingHnhnPc.next()) {
							houseNameNum = gettingHnhnPc.getString("houseNameNumber");
							pc = gettingHnhnPc.getString("postcode");
							
							search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, pc, 
													gettingProperty.getString("description"), gettingProperty.getString("shortName"), 
													gettingProperty.getInt("guestCapacity"));
							searchList.add(search);
						}
					}
    		   }
        	   
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
     //guestCap
       if(minPPN == 0 && maxPPN == 0 && guestCap != 0 && sd == "" && ed =="" && placeName == "" ) {
    	   int addressId;
           String houseNameNum, pc;
    	   try {
    		   SearchObject search;
    		   
    		   String guestCapToPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where guestCapacity=?";
    		   PreparedStatement getPid = connection.prepareStatement(guestCapToPid);
    		   getPid.setInt(1, guestCap);
    		   System.out.println(getPid);
    		   
    		   ResultSet gettingPId = getPid.executeQuery();
    		   
    		   while(gettingPId.next()) {
    			   addressId = gettingPId.getInt("address_id");
            	   
            	   String hnhnPcFromAid = "Select houseNameNumber, postcode from Address where address_id=?";
        		   
					PreparedStatement getHnhnPc= connection.prepareStatement(hnhnPcFromAid);
					getHnhnPc.setInt(1, addressId);
					ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
					   
					while(gettingHnhnPc.next()) {
						houseNameNum = gettingHnhnPc.getString("houseNameNumber");
						pc = gettingHnhnPc.getString("postcode");
						
						search = new SearchObject(gettingPId.getInt("property_id"), houseNameNum, pc, 
												gettingPId.getString("description"), gettingPId.getString("shortName"),gettingPId.getInt("guestCapacity"));
						searchList.add(search);
					}
    		   }
        	   
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
     //city
       if(minPPN == 0 && maxPPN == 0 && guestCap == 0 && sd == "" && ed =="" && placeName != "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;
    	   try {
    		   SearchObject search;
    		   
    		   String cityToHnhnPc = "Select address_id, houseNameNumber, postcode FROM `Address` WHERE placeName =?";
  			   String hnhnPcToPid = "Select property_id from Property where address_id=?";
  			   String propertyFromPid = "Select property_id, description, shortName, guestCapacity from Property where property_id=?";
  			   			 
  			   PreparedStatement getHnhnPc = connection.prepareStatement(cityToHnhnPc);
  			   getHnhnPc.setString(1, placeName);
 
  			   ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
  
	      	   while(gettingHnhnPc.next()) {
	      		   addressId = gettingHnhnPc.getInt("address_id");
	      		   houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	      		   pc = gettingHnhnPc.getString("postcode");
  
	      		   System.out.println("HNHN PC RS= "+houseNameNum+" pc = "+pc+" addressId = "+addressId);
      		   
	  			   PreparedStatement getPid = connection.prepareStatement(hnhnPcToPid);
	  			   getPid.setInt(1, addressId);
	  			   
	               ResultSet gettingPid = getPid.executeQuery();
	               
	      		   while(gettingPid.next()) {
	      			   propId = gettingPid.getInt("property_id");
	      			   	          			   
          			   PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
          			   getProperty.setInt(1, propId);
                         ResultSet gettingProperty = getProperty.executeQuery();
          			   
                         while(gettingProperty.next()) {
              			   System.out.println("final prop id ------"+gettingProperty.getInt("property_id"));
              			   
                      	   search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, 
                      			   					pc, gettingProperty.getString("description"), 
                  			   						gettingProperty.getString("shortName"),gettingProperty.getInt("guestCapacity"));
                             searchList.add(search);
                         }
                  }
      		   }
        	   
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
     
     ///////////////////////////////////////// 2 search criteria queries///////////////////////////////////////////// 
     //minMax, startDate
       if(minPPN != 0 && maxPPN != 0 && guestCap == 0 && sd != "" && ed =="" && placeName == "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;

    	   try {
    		   SearchObject search;
    		   
    		   String allCb = "select property_id, startDate from ChargeBands  where totalPricePerNight>=? and totalPricePerNight<=?";
    		   PreparedStatement getAllCb = connection.prepareStatement(allCb);
    		   getAllCb.setDouble(1, minPPN);
    		   getAllCb.setDouble(2, maxPPN);
        	   
    		   ResultSet gettingAllCb = getAllCb.executeQuery();

    		   System.out.println(getAllCb);
    		   while(gettingAllCb.next()) {
    			   if( parseDate(gettingAllCb.getString("startDate")).equals(startd) ||   startd.after(parseDate(gettingAllCb.getString("startDate")))) {
    				   propId = gettingAllCb.getInt("property_id");
    				   
    				   String propertyFromPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where property_id=?";
            		   
	   					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	   					getProperty.setInt(1, propId);
	   					
	   					ResultSet gettingProperty = getProperty.executeQuery();
	   					   
	   					while(gettingProperty.next()) {
	   						addressId = gettingProperty.getInt("address_id");
	   						
	   						String hnhnPcFromAid = "Select houseNameNumber, postcode from Address where address_id=?";
	   		        		   
	   						PreparedStatement getHnhnPc= connection.prepareStatement(hnhnPcFromAid);
	   						getHnhnPc.setInt(1, addressId);
	   						ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
	   						   
	   						while(gettingHnhnPc.next()) {
	   							houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	   							pc = gettingHnhnPc.getString("postcode");
	   							
	   							search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, pc, 
	   													gettingProperty.getString("description"), gettingProperty.getString("shortName"), 
	   													gettingProperty.getInt("guestCapacity"));
	   							searchList.add(search);
	   						}
	   					}   
    			   }
    		   }
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
     //minMax, endDate
       if(minPPN != 0 && maxPPN != 0 && guestCap == 0 && sd == "" && ed !="" && placeName == "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;

    	   try {
    		   SearchObject search;
    		   
    		   String allCb = "select property_id, endDate from ChargeBands  where totalPricePerNight>=? and totalPricePerNight<=?";
    		   PreparedStatement getAllCb = connection.prepareStatement(allCb);
    		   getAllCb.setDouble(1, minPPN);
    		   getAllCb.setDouble(2, maxPPN);
        	   
    		   ResultSet gettingAllCb = getAllCb.executeQuery();

    		   System.out.println(getAllCb);
    		   while(gettingAllCb.next()) {
    			   if( parseDate(gettingAllCb.getString("endDate")).equals(endd) ||   endd.before(parseDate(gettingAllCb.getString("endDate")))) {
    				   propId = gettingAllCb.getInt("property_id");
    				   
    				   String propertyFromPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where property_id=?";
            		   
	   					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	   					getProperty.setInt(1, propId);
	   					
	   					ResultSet gettingProperty = getProperty.executeQuery();
	   					   
	   					while(gettingProperty.next()) {
	   						addressId = gettingProperty.getInt("address_id");
	   						
	   						String hnhnPcFromAid = "Select houseNameNumber, postcode from Address where address_id=?";
	   		        		   
	   						PreparedStatement getHnhnPc= connection.prepareStatement(hnhnPcFromAid);
	   						getHnhnPc.setInt(1, addressId);
	   						ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
	   						   
	   						while(gettingHnhnPc.next()) {
	   							houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	   							pc = gettingHnhnPc.getString("postcode");
	   							
	   							search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, pc, 
	   													gettingProperty.getString("description"), gettingProperty.getString("shortName"), 
	   													gettingProperty.getInt("guestCapacity"));
	   							searchList.add(search);
	   						}
	   					}   
    			   }
    		   }
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
     //guestCap, startDate
       if(minPPN == 0 && maxPPN == 0 && guestCap != 0 && sd != "" && ed =="" && placeName == "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;

    	   try {
    		   SearchObject search;
    		   
    		   String allCb = "select property_id, startDate from ChargeBands";
    		   PreparedStatement getAllCb = connection.prepareStatement(allCb);
        	   
    		   ResultSet gettingAllCb = getAllCb.executeQuery();

    		   System.out.println(getAllCb);
    		   while(gettingAllCb.next()) {
    			   if( parseDate(gettingAllCb.getString("startDate")).equals(startd) ||   startd.after(parseDate(gettingAllCb.getString("startDate")))) {
    				   propId = gettingAllCb.getInt("property_id");
    				   
    				   String propertyFromPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where property_id=? and guestCapacity=?";
            		   
	   					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	   					getProperty.setInt(1, propId);
	   					getProperty.setInt(2, guestCap);
	   					
	   					ResultSet gettingProperty = getProperty.executeQuery();
	   					   
	   					while(gettingProperty.next()) {
	   						addressId = gettingProperty.getInt("address_id");
	   						
	   						String hnhnPcFromAid = "Select houseNameNumber, postcode from Address where address_id=?";
	   		        		   
	   						PreparedStatement getHnhnPc= connection.prepareStatement(hnhnPcFromAid);
	   						getHnhnPc.setInt(1, addressId);
	   						ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
	   						   
	   						while(gettingHnhnPc.next()) {
	   							houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	   							pc = gettingHnhnPc.getString("postcode");
	   							
	   							search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, pc, 
	   													gettingProperty.getString("description"), gettingProperty.getString("shortName"), 
	   													gettingProperty.getInt("guestCapacity"));
	   							searchList.add(search);
	   						}
	   					}   
    			   }
    		   }
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
     //guestCap, endDate
       if(minPPN == 0 && maxPPN == 0 && guestCap != 0 && sd == "" && ed !="" && placeName == "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;

    	   try {
    		   SearchObject search;
    		   
    		   String allCb = "select property_id, endDate from ChargeBands";
    		   PreparedStatement getAllCb = connection.prepareStatement(allCb);
        	   
    		   ResultSet gettingAllCb = getAllCb.executeQuery();

    		   System.out.println(getAllCb);
    		   while(gettingAllCb.next()) {
    			   if( parseDate(gettingAllCb.getString("endDate")).equals(endd) ||   endd.before(parseDate(gettingAllCb.getString("endDate")))) {
    				   propId = gettingAllCb.getInt("property_id");
    				   
    				   String propertyFromPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where property_id=? and guestCapacity=?";
            		   
	   					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	   					getProperty.setInt(1, propId);
	   					getProperty.setInt(2, guestCap);
	   					
	   					ResultSet gettingProperty = getProperty.executeQuery();
	   					   
	   					while(gettingProperty.next()) {
	   						addressId = gettingProperty.getInt("address_id");
	   						
	   						String hnhnPcFromAid = "Select houseNameNumber, postcode from Address where address_id=?";
	   		        		   
	   						PreparedStatement getHnhnPc= connection.prepareStatement(hnhnPcFromAid);
	   						getHnhnPc.setInt(1, addressId);
	   						ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
	   						   
	   						while(gettingHnhnPc.next()) {
	   							houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	   							pc = gettingHnhnPc.getString("postcode");
	   							
	   							search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, pc, 
	   													gettingProperty.getString("description"), gettingProperty.getString("shortName"), 
	   													gettingProperty.getInt("guestCapacity"));
	   							searchList.add(search);
	   						}
	   					}   
    			   }
    		   }
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
     //startDate, endDate
       if(minPPN == 0 && maxPPN == 0 && guestCap == 0 && sd != "" && ed !="" && placeName == "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;

    	   try {
    		   SearchObject search;
    		   
    		   String allCb = "select property_id, startDate, endDate from ChargeBands";
    		   PreparedStatement getAllCb = connection.prepareStatement(allCb);
    		   ResultSet gettingAllCb = getAllCb.executeQuery();

    		   System.out.println(getAllCb);
    		   while(gettingAllCb.next()) {
    			   if( parseDate(gettingAllCb.getString("startDate")).equals(startd) ||   startd.after(parseDate(gettingAllCb.getString("startDate"))) ||
					   parseDate(gettingAllCb.getString("endDate")).equals(endd) ||   endd.before(parseDate(gettingAllCb.getString("endDate")))) {
    				   propId = gettingAllCb.getInt("property_id");
    				   
    				   String propertyFromPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where property_id=?";
            		   
	   					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	   					getProperty.setInt(1, propId);
	   					
	   					ResultSet gettingProperty = getProperty.executeQuery();
	   					   
	   					while(gettingProperty.next()) {
	   						addressId = gettingProperty.getInt("address_id");
	   						
	   						String hnhnPcFromAid = "Select houseNameNumber, postcode from Address where address_id=?";
	   		        		   
	   						PreparedStatement getHnhnPc= connection.prepareStatement(hnhnPcFromAid);
	   						getHnhnPc.setInt(1, addressId);
	   						ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
	   						   
	   						while(gettingHnhnPc.next()) {
	   							houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	   							pc = gettingHnhnPc.getString("postcode");
	   							
	   							search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, pc, 
	   													gettingProperty.getString("description"), gettingProperty.getString("shortName"), 
	   													gettingProperty.getInt("guestCapacity"));
	   							searchList.add(search);
	   						}
	   					}   
    			   }
    		   }
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
     //startDate, city
       if(minPPN == 0 && maxPPN == 0 && guestCap == 0 && sd != "" && ed =="" && placeName != "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;
    	   int propIdFinalQuery;
    	   try {
    		   SearchObject search;
    		   
    		   String cityToHnhnPc = "Select address_id, houseNameNumber, postcode FROM Address where placeName =?";
  			   String hnhnPcToPid = "Select property_id from Property where address_id=?";
  			   String pidFromChargeBands = "Select property_id, startDate from ChargeBands where property_id=?";
  			   String propertyFromPid = "Select property_id, description, shortName, guestCapacity from Property where property_id=?";
  			   			 
  			   PreparedStatement getHnhnPc = connection.prepareStatement(cityToHnhnPc);
  			   getHnhnPc.setString(1, placeName);
 
  			   ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
  
	      	   while(gettingHnhnPc.next()) {
	      		   addressId = gettingHnhnPc.getInt("address_id");
	      		   houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	      		   pc = gettingHnhnPc.getString("postcode");
        		   
	  			   PreparedStatement getPid = connection.prepareStatement(hnhnPcToPid);
	  			   getPid.setInt(1, addressId);
	  			   
	               ResultSet gettingPid = getPid.executeQuery();
	               
	      		   while(gettingPid.next()) {
	      			   propId = gettingPid.getInt("property_id");
	      			   
	      			   
	      			   PreparedStatement getPidFromChargeBands = connection.prepareStatement(pidFromChargeBands);
	      			   getPidFromChargeBands.setInt(1, propId);
	            	   
	            	   ResultSet gettingPidFromChargeBands = getPidFromChargeBands.executeQuery();
	            	   while(gettingPidFromChargeBands.next()) {
	            		   if( parseDate(gettingPidFromChargeBands.getString("startDate")).equals(startd) || startd.after(parseDate(gettingPidFromChargeBands.getString("startDate")))) {
	            			   propIdFinalQuery = gettingPidFromChargeBands.getInt("property_id");
		            		   
		            		   PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
		            		   getProperty.setInt(1, propIdFinalQuery);
		            		   
		                       ResultSet gettingProperty = getProperty.executeQuery();
		                       while(gettingProperty.next()) {
		                    	   search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, 
		                    			   					pc, gettingProperty.getString("description"), 
	                    			   						gettingProperty.getString("shortName"),gettingProperty.getInt("guestCapacity"));
		                            searchList.add(search);
		                       }
	                       }
	            	   }
                  }
      		   }
        	   
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
     //endDate, city
       if(minPPN == 0 && maxPPN == 0 && guestCap == 0 && sd == "" && ed !="" && placeName != "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;
    	   int propIdFinalQuery;
    	   try {
    		   SearchObject search;
    		   
    		   String cityToHnhnPc = "Select address_id, houseNameNumber, postcode FROM Address where placeName =?";
  			   String hnhnPcToPid = "Select property_id from Property where address_id=?";
  			   String pidFromChargeBands = "Select property_id, endDate from ChargeBands where property_id=?";
  			   String propertyFromPid = "Select property_id, description, shortName, guestCapacity from Property where property_id=?";
  			   			 
  			   PreparedStatement getHnhnPc = connection.prepareStatement(cityToHnhnPc);
  			   getHnhnPc.setString(1, placeName);
 
  			   ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
  
	      	   while(gettingHnhnPc.next()) {
	      		   addressId = gettingHnhnPc.getInt("address_id");
	      		   houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	      		   pc = gettingHnhnPc.getString("postcode");
        		   
	  			   PreparedStatement getPid = connection.prepareStatement(hnhnPcToPid);
	  			   getPid.setInt(1, addressId);
	  			   
	               ResultSet gettingPid = getPid.executeQuery();
	               
	      		   while(gettingPid.next()) {
	      			   propId = gettingPid.getInt("property_id");
	      			   
	      			   
	      			   PreparedStatement getPidFromChargeBands = connection.prepareStatement(pidFromChargeBands);
	      			   getPidFromChargeBands.setInt(1, propId);
	            	   
	            	   ResultSet gettingPidFromChargeBands = getPidFromChargeBands.executeQuery();
	            	   while(gettingPidFromChargeBands.next()) {
	            		   if( parseDate(gettingPidFromChargeBands.getString("endDate")).equals(endd) || endd.before(parseDate(gettingPidFromChargeBands.getString("endDate")))) {
	            			   propIdFinalQuery = gettingPidFromChargeBands.getInt("property_id");
		            		   
		            		   PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
		            		   getProperty.setInt(1, propIdFinalQuery);
		            		   
		                       ResultSet gettingProperty = getProperty.executeQuery();
		                       while(gettingProperty.next()) {
		                    	   search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, 
		                    			   					pc, gettingProperty.getString("description"), 
	                    			   						gettingProperty.getString("shortName"),gettingProperty.getInt("guestCapacity"));
		                            searchList.add(search);
		                       }
	                       }
	            	   }
                  }
      		   }
        	   
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
       
     //minMax, guestCap
       if(minPPN != 0 && maxPPN != 0 && guestCap != 0 && sd == "" && ed =="" && placeName == "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;
    	   int propIdFinalQuery;
    	   try {
    		   SearchObject search;
    		   
    		   String guestCapToPid = "Select property_id from Property where guestCapacity=?";
    		   PreparedStatement getPid = connection.prepareStatement(guestCapToPid);
    		   getPid.setInt(1, guestCap);
    		   System.out.println(getPid);
    		   
    		   ResultSet gettingPId = getPid.executeQuery();
    		   
    		   while(gettingPId.next()) {
    			   propId = gettingPId.getInt("property_id");
            	   
            	   String pIdFromChargeBands = "Select property_id from ChargeBands where totalPricePerNight>=? and totalPricePerNight<=? and property_id=?";
            	   PreparedStatement getPidFromChargeBands = connection.prepareStatement(pIdFromChargeBands);
            	   getPidFromChargeBands.setDouble(1, minPPN);
            	   getPidFromChargeBands.setDouble(2, maxPPN);
            	   getPidFromChargeBands.setInt(3, propId);
            	   
            	   ResultSet gettingPidFromChargeBands = getPidFromChargeBands.executeQuery();
            	   while(gettingPidFromChargeBands.next()) {
            		   propIdFinalQuery = gettingPidFromChargeBands.getInt("property_id");
            		   
            		   String propertyFromPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where property_id=?";
            		   
    					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
    					getProperty.setInt(1, propIdFinalQuery);
    					ResultSet gettingProperty = getProperty.executeQuery();
    					   
    					while(gettingProperty.next()) {
    						addressId = gettingProperty.getInt("address_id");
    						
    						String hnhnPc = "select houseNameNumber, postcode from Address where address_id=?";
    						PreparedStatement getHnhnPc = connection.prepareStatement(hnhnPc);
    						getHnhnPc.setInt(1, addressId);
    						
    						ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
    						
    						while(gettingHnhnPc.next()) {
    							houseNameNum = gettingHnhnPc.getString("houseNameNUmber");
    							pc = gettingHnhnPc.getString("postcode");
    							
    							search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, 
									   					pc, gettingProperty.getString("description"), 
								   						gettingProperty.getString("shortName"),gettingProperty.getInt("guestCapacity"));
									      searchList.add(search);
    						}
    						
    					}   
            	   }   
    		   }
        	   
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
     //minMax, city
       if(minPPN != 0 && maxPPN != 0 && guestCap == 0 && sd == "" && ed =="" && placeName != "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;
    	   int propIdFinalQuery;
    	   try {
    		   SearchObject search;
    		   
    		   String cityToHnhnPc = "Select address_id, houseNameNumber, postcode FROM Address where placeName =?";
  			   String hnhnPcToPid = "Select property_id from Property where address_id=?";
  			   String pidFromChargeBands = "Select property_id from ChargeBands where totalPricePerNight>=? and totalPricePerNight<=? and property_id=?";
  			   String propertyFromPid = "Select property_id, description, shortName, guestCapacity from Property where property_id=?";
  			   			 
  			   PreparedStatement getHnhnPc = connection.prepareStatement(cityToHnhnPc);
  			   getHnhnPc.setString(1, placeName);
 
  			   ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
  
	      	   while(gettingHnhnPc.next()) {
	      		   addressId = gettingHnhnPc.getInt("address_id");
	      		   houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	      		   pc = gettingHnhnPc.getString("postcode");
        		   
	  			   PreparedStatement getPid = connection.prepareStatement(hnhnPcToPid);
	  			   getPid.setInt(1, addressId);
	  			   
	               ResultSet gettingPid = getPid.executeQuery();
	               
	      		   while(gettingPid.next()) {
	      			   propId = gettingPid.getInt("property_id");
	      			   
	      			   PreparedStatement getPidFromChargeBands = connection.prepareStatement(pidFromChargeBands);
	            	   getPidFromChargeBands.setDouble(1, minPPN);
	            	   getPidFromChargeBands.setDouble(2, maxPPN);
	            	   getPidFromChargeBands.setInt(3, propId); //getting chargebands where property id is propId
	            	   
	            	   ResultSet gettingPidFromChargeBands = getPidFromChargeBands.executeQuery();
	            	   while(gettingPidFromChargeBands.next()) {
	            		   propIdFinalQuery = gettingPidFromChargeBands.getInt("property_id");
	            		   
	            		   PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	            		   getProperty.setInt(1, propIdFinalQuery);
	            		   
	                       ResultSet gettingProperty = getProperty.executeQuery();
	                       while(gettingProperty.next()) {
	                    	   search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, 
	                    			   					pc, gettingProperty.getString("description"), 
                    			   						gettingProperty.getString("shortName"),gettingProperty.getInt("guestCapacity"));
	                            searchList.add(search);
	                       }
	            	   }
                  }
      		   }
        	   
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
       //city, guest
       if(minPPN == 0 && maxPPN == 0 && guestCap != 0 && sd == "" && ed =="" && placeName != "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;
    	   try {
    		   SearchObject search;
    		   
    		   String cityToHnhnPc = "SELECT address_id, houseNameNumber, postcode FROM `Address` WHERE placeName =?";
  			   String propertyFromPid = "Select property_id, description, shortName, guestCapacity from Property where address_id=? and guestCapacity=?";
  			   			 
  			   PreparedStatement getHnhnPc = connection.prepareStatement(cityToHnhnPc);
  			   getHnhnPc.setString(1, placeName);
 
  			   ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
  
	      	   while(gettingHnhnPc.next()) {
	      		   addressId = gettingHnhnPc.getInt("address_id");
	      		   houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	      		   pc = gettingHnhnPc.getString("postcode");
        		   
	      		   PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	      		   getProperty.setInt(1, addressId);
	      		   getProperty.setInt(2, guestCap);
	      		   
	      		   ResultSet gettingProperty = getProperty.executeQuery();
	      		   while(gettingProperty.next()) {
	      			 search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, 
							   					pc, gettingProperty.getString("description"), 
						   						gettingProperty.getString("shortName"),gettingProperty.getInt("guestCapacity"));
				      searchList.add(search);
      			   }
      		   }
        	   
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
       ///////////////////////////////////////// 3 search criteria queries/////////////////////////////////////////////
       
       //minMax, city, guest
       if(minPPN != 0 && maxPPN != 0 && guestCap != 0 && sd == "" && ed =="" && placeName != "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;
    	   int propIdFinalQuery;
    	   
    	   try {
    		   SearchObject search;
    		   
    		   String cityToHnhnPc = "Select address_id, houseNameNumber, postcode from Address where placeName =?";
  			   String hnhnPcGcToPid = "Select property_id from Property where address_id=? and guestCapacity=?";
  			   String pidFromChargeBands = "Select property_id from ChargeBands where totalPricePerNight>=? and totalPricePerNight<=? and property_id=?";
  			   String propertyFromPid = "Select property_id, description, shortName, guestCapacity from Property where property_id=?";
  			   			 
  			   PreparedStatement getHnhnPc = connection.prepareStatement(cityToHnhnPc);
  			   getHnhnPc.setString(1, placeName);
 
  			   ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
  
	      	   while(gettingHnhnPc.next()) {
	      		   addressId = gettingHnhnPc.getInt("address_id");
	      		   houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	      		   pc = gettingHnhnPc.getString("postcode");
        		   
	  			   PreparedStatement getPid = connection.prepareStatement(hnhnPcGcToPid);
	  			   getPid.setInt(1, addressId);
	  			   getPid.setInt(2, guestCap);
	  			   
	               ResultSet gettingPid = getPid.executeQuery();
	      		   while(gettingPid.next()) {
	      			   propId = gettingPid.getInt("property_id");
	      			   
	      			   PreparedStatement getPidFromChargeBands = connection.prepareStatement(pidFromChargeBands);
	            	   getPidFromChargeBands.setDouble(1, minPPN);
	            	   getPidFromChargeBands.setDouble(2, maxPPN);
	            	   getPidFromChargeBands.setInt(3, propId); //getting chargebands where property id is propId
	            	   
	            	   ResultSet gettingPidFromChargeBands = getPidFromChargeBands.executeQuery();
	            	   while(gettingPidFromChargeBands.next()) {
	            		   propIdFinalQuery = gettingPidFromChargeBands.getInt("property_id");
	            		   
	            		   PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	            		   getProperty.setInt(1, propIdFinalQuery);
	            		   
	                       ResultSet gettingProperty = getProperty.executeQuery();
	                       while(gettingProperty.next()) {
	                    	   search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, 
	                    			   					pc, gettingProperty.getString("description"), 
                    			   						gettingProperty.getString("shortName"),gettingProperty.getInt("guestCapacity"));
	                            searchList.add(search);
	                       }
	            	   }
                  }
      		   }
        	   
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
       //minMax, startDate, guest
       if(minPPN != 0 && maxPPN != 0 && guestCap != 0 && sd != "" && ed =="" && placeName == "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;
    	   int propIdFinalQuery;
    	   
    	   try {
    		   SearchObject search;
    		   
    		   String PidFromAllCB = "select property_id, startDate from ChargeBands where totalPricePerNight>=? and totalPricePerNight<=?";
    		   
    		   PreparedStatement getPidFromAllCB = connection.prepareStatement(PidFromAllCB);
    		   getPidFromAllCB.setDouble(1, minPPN);
    		   getPidFromAllCB.setDouble(2, maxPPN);
    		   
    		   ResultSet gettingPidFromAllCB = getPidFromAllCB.executeQuery();
    		   
    		   while(gettingPidFromAllCB.next()) {
    			   if( parseDate(gettingPidFromAllCB.getString("startDate")).equals(startd) ||   startd.after(parseDate(gettingPidFromAllCB.getString("startDate")))) {
    				   propId = gettingPidFromAllCB.getInt("property_id");
    				   
    				   String propertyFromPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where property_id=? and guestCapacity=?";
            		   
	   					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	   					getProperty.setInt(1, propId);
	   					getProperty.setInt(2, guestCap);
	   					
	   					ResultSet gettingProperty = getProperty.executeQuery();
	   					   
	   					while(gettingProperty.next()) {
	   						addressId = gettingProperty.getInt("address_id");
	   						
	   						String hnhnPcFromAid = "Select houseNameNumber, postcode from Address where address_id=?";
	   		        		   
	   						PreparedStatement getHnhnPc= connection.prepareStatement(hnhnPcFromAid);
	   						getHnhnPc.setInt(1, addressId);
	   						ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
	   						   
	   						while(gettingHnhnPc.next()) {
	   							houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	   							pc = gettingHnhnPc.getString("postcode");
	   							
	   							search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, pc, 
	   													gettingProperty.getString("description"), gettingProperty.getString("shortName"), 
	   													gettingProperty.getInt("guestCapacity"));
	   							searchList.add(search);
	   						}
	   					}   
    			   }
    		   }
        	   
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
       //minMax, endDate, guest
       if(minPPN != 0 && maxPPN != 0 && guestCap != 0 && sd == "" && ed !="" && placeName == "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;
    	   int propIdFinalQuery;
    	   
    	   try {
    		   SearchObject search;
    		   
    		   String PidFromAllCB = "select property_id, endDate from ChargeBands where totalPricePerNight>=? and totalPricePerNight<=?";
    		   
    		   PreparedStatement getPidFromAllCB = connection.prepareStatement(PidFromAllCB);
    		   getPidFromAllCB.setDouble(1, minPPN);
    		   getPidFromAllCB.setDouble(2, maxPPN);
    		   
    		   ResultSet gettingPidFromAllCB = getPidFromAllCB.executeQuery();
    		   
    		   while(gettingPidFromAllCB.next()) {
    			   if( parseDate(gettingPidFromAllCB.getString("endDate")).equals(endd) ||   endd.before(parseDate(gettingPidFromAllCB.getString("endDate")))) {
    				   propId = gettingPidFromAllCB.getInt("property_id");
    				   
    				   String propertyFromPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where property_id=? and guestCapacity=?";
            		   
	   					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	   					getProperty.setInt(1, propId);
	   					getProperty.setInt(2, guestCap);
	   					
	   					ResultSet gettingProperty = getProperty.executeQuery();
	   					   
	   					while(gettingProperty.next()) {
	   						addressId = gettingProperty.getInt("address_id");
	   						
	   						String hnhnPcFromAid = "Select houseNameNumber, postcode from Address where address_id=?";
	   		        		   
	   						PreparedStatement getHnhnPc= connection.prepareStatement(hnhnPcFromAid);
	   						getHnhnPc.setInt(1, addressId);
	   						ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
	   						   
	   						while(gettingHnhnPc.next()) {
	   							houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	   							pc = gettingHnhnPc.getString("postcode");
	   							
	   							search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, pc, 
	   													gettingProperty.getString("description"), gettingProperty.getString("shortName"), 
	   													gettingProperty.getInt("guestCapacity"));
	   							searchList.add(search);
	   						}
	   					}   
    			   }
    		   }
        	   
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
       //minMax, startDate, endDate
       if(minPPN != 0 && maxPPN != 0 && guestCap == 0 && sd != "" && ed !="" && placeName == "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;
    	   int propIdFinalQuery;
    	   
    	   try {
    		   SearchObject search;
    		   
    		   String PidFromAllCB = "select property_id, startDate, endDate from ChargeBands where totalPricePerNight>=? and totalPricePerNight<=?";
    		   
    		   PreparedStatement getPidFromAllCB = connection.prepareStatement(PidFromAllCB);
    		   getPidFromAllCB.setDouble(1, minPPN);
    		   getPidFromAllCB.setDouble(2, maxPPN);
    		   
    		   ResultSet gettingPidFromAllCB = getPidFromAllCB.executeQuery();
    		   
    		   while(gettingPidFromAllCB.next()) {
    			   if( parseDate(gettingPidFromAllCB.getString("startDate")).equals(startd) ||   startd.after(parseDate(gettingPidFromAllCB.getString("startDate"))) ||
    					   parseDate(gettingPidFromAllCB.getString("endDate")).equals(endd) ||   endd.before(parseDate(gettingPidFromAllCB.getString("endDate")))) {
    				   propId = gettingPidFromAllCB.getInt("property_id");
    				   
    				   String propertyFromPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where property_id=?";
            		   
	   					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	   					getProperty.setInt(1, propId);
	   					
	   					ResultSet gettingProperty = getProperty.executeQuery();
	   					   
	   					while(gettingProperty.next()) {
	   						addressId = gettingProperty.getInt("address_id");
	   						
	   						String hnhnPcFromAid = "Select houseNameNumber, postcode from Address where address_id=?";
	   		        		   
	   						PreparedStatement getHnhnPc= connection.prepareStatement(hnhnPcFromAid);
	   						getHnhnPc.setInt(1, addressId);
	   						ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
	   						   
	   						while(gettingHnhnPc.next()) {
	   							houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	   							pc = gettingHnhnPc.getString("postcode");
	   							
	   							search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, pc, 
	   													gettingProperty.getString("description"), gettingProperty.getString("shortName"), 
	   													gettingProperty.getInt("guestCapacity"));
	   							searchList.add(search);
	   						}
	   					}   
    			   }
    		   }
        	   
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
     //minMax, startDate, city
       if(minPPN != 0 && maxPPN != 0 && guestCap == 0 && sd != "" && ed =="" && placeName != "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;
    	   int propIdFinalQuery;
    	   
    	   try {
    		   SearchObject search;
    		   
    		   String PidFromAllCB = "select property_id, startDate from ChargeBands where totalPricePerNight>=? and totalPricePerNight<=?";
    		   
    		   PreparedStatement getPidFromAllCB = connection.prepareStatement(PidFromAllCB);
    		   getPidFromAllCB.setDouble(1, minPPN);
    		   getPidFromAllCB.setDouble(2, maxPPN);
    		   
    		   ResultSet gettingPidFromAllCB = getPidFromAllCB.executeQuery();
    		   
    		   while(gettingPidFromAllCB.next()) {
    			   if( parseDate(gettingPidFromAllCB.getString("startDate")).equals(startd) ||   startd.after(parseDate(gettingPidFromAllCB.getString("startDate")))) {
    				   propId = gettingPidFromAllCB.getInt("property_id");
    				   
    				   String propertyFromPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where property_id=?";
            		   
	   					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	   					getProperty.setInt(1, propId);
	   					
	   					ResultSet gettingProperty = getProperty.executeQuery();
	   					   
	   					while(gettingProperty.next()) {
	   						addressId = gettingProperty.getInt("address_id");
	   						
	   						String hnhnPcFromAid = "Select houseNameNumber, postcode from Address where address_id=? and placeName=?";
	   		        		   
	   						PreparedStatement getHnhnPc= connection.prepareStatement(hnhnPcFromAid);
	   						getHnhnPc.setInt(1, addressId);
	   						getHnhnPc.setString(2, placeName);
	   						
	   						ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
	   						   
	   						while(gettingHnhnPc.next()) {
	   							houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	   							pc = gettingHnhnPc.getString("postcode");
	   							
	   							search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, pc, 
	   													gettingProperty.getString("description"), gettingProperty.getString("shortName"), 
	   													gettingProperty.getInt("guestCapacity"));
	   							searchList.add(search);
	   						}
	   					}   
    			   }
    		   }
        	   
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
     //minMax, endDate, city
       if(minPPN != 0 && maxPPN != 0 && guestCap == 0 && sd == "" && ed !="" && placeName != "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;
    	   int propIdFinalQuery;
    	   
    	   try {
    		   SearchObject search;
    		   
    		   String PidFromAllCB = "select property_id, endDate from ChargeBands where totalPricePerNight>=? and totalPricePerNight<=?";
    		   
    		   PreparedStatement getPidFromAllCB = connection.prepareStatement(PidFromAllCB);
    		   getPidFromAllCB.setDouble(1, minPPN);
    		   getPidFromAllCB.setDouble(2, maxPPN);
    		   
    		   ResultSet gettingPidFromAllCB = getPidFromAllCB.executeQuery();
    		   
    		   while(gettingPidFromAllCB.next()) {
    			   if( parseDate(gettingPidFromAllCB.getString("endDate")).equals(endd) ||   endd.after(parseDate(gettingPidFromAllCB.getString("endDate")))) {
    				   propId = gettingPidFromAllCB.getInt("property_id");
    				   
    				   String propertyFromPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where property_id=?";
            		   
	   					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	   					getProperty.setInt(1, propId);
	   					
	   					ResultSet gettingProperty = getProperty.executeQuery();
	   					   
	   					while(gettingProperty.next()) {
	   						addressId = gettingProperty.getInt("address_id");
	   						
	   						String hnhnPcFromAid = "Select houseNameNumber, postcode from Address where address_id=? and placeName=?";
	   		        		   
	   						PreparedStatement getHnhnPc= connection.prepareStatement(hnhnPcFromAid);
	   						getHnhnPc.setInt(1, addressId);
	   						getHnhnPc.setString(2, placeName);
	   						
	   						ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
	   						   
	   						while(gettingHnhnPc.next()) {
	   							houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	   							pc = gettingHnhnPc.getString("postcode");
	   							
	   							search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, pc, 
	   													gettingProperty.getString("description"), gettingProperty.getString("shortName"), 
	   													gettingProperty.getInt("guestCapacity"));
	   							searchList.add(search);
	   						}
	   					}   
    			   }
    		   }
        	   
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
       //startDate, endDate, guest
       if(minPPN == 0 && maxPPN == 0 && guestCap != 0 && sd != "" && ed !="" && placeName == "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;
    	   int propIdFinalQuery;
    	   
    	   try {
    		   SearchObject search;
    		   
    		   String PidFromAllCB = "select property_id, startDate, endDate from ChargeBands";
    		   
    		   PreparedStatement getPidFromAllCB = connection.prepareStatement(PidFromAllCB);
    		   
    		   ResultSet gettingPidFromAllCB = getPidFromAllCB.executeQuery();
    		   
    		   while(gettingPidFromAllCB.next()) {
    			   if( parseDate(gettingPidFromAllCB.getString("startDate")).equals(startd) ||   startd.after(parseDate(gettingPidFromAllCB.getString("startDate"))) ||
    					   parseDate(gettingPidFromAllCB.getString("endDate")).equals(endd) ||   endd.before(parseDate(gettingPidFromAllCB.getString("endDate")))) {
    				   propId = gettingPidFromAllCB.getInt("property_id");
    				   
    				   String propertyFromPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where property_id=? and guestCapacity=?";
            		   
	   					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	   					getProperty.setInt(1, propId);
	   					getProperty.setInt(2, guestCap);
	   					
	   					ResultSet gettingProperty = getProperty.executeQuery();
	   					   
	   					while(gettingProperty.next()) {
	   						addressId = gettingProperty.getInt("address_id");
	   						
	   						String hnhnPcFromAid = "Select houseNameNumber, postcode from Address where address_id=?";
	   		        		   
	   						PreparedStatement getHnhnPc= connection.prepareStatement(hnhnPcFromAid);
	   						getHnhnPc.setInt(1, addressId);
	   						ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
	   						   
	   						while(gettingHnhnPc.next()) {
	   							houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	   							pc = gettingHnhnPc.getString("postcode");
	   							
	   							search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, pc, 
	   													gettingProperty.getString("description"), gettingProperty.getString("shortName"), 
	   													gettingProperty.getInt("guestCapacity"));
	   							searchList.add(search);
	   						}
	   					}   
    			   }
    		   }
        	   
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
     //guestCap, endDate, city
       if(minPPN == 0 && maxPPN == 0 && guestCap != 0 && sd == "" && ed !="" && placeName != "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;

    	   try {
    		   SearchObject search;
    		   
    		   String allCb = "select property_id, endDate from ChargeBands";
    		   PreparedStatement getAllCb = connection.prepareStatement(allCb);
        	   
    		   ResultSet gettingAllCb = getAllCb.executeQuery();

    		   System.out.println(getAllCb);
    		   while(gettingAllCb.next()) {
    			   if( parseDate(gettingAllCb.getString("endDate")).equals(endd) ||   endd.before(parseDate(gettingAllCb.getString("endDate")))) {
    				   propId = gettingAllCb.getInt("property_id");
    				   
    				   String propertyFromPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where property_id=? and guestCapacity=?";
            		   
	   					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	   					getProperty.setInt(1, propId);
	   					getProperty.setInt(2, guestCap);
	   					
	   					ResultSet gettingProperty = getProperty.executeQuery();
	   					   
	   					while(gettingProperty.next()) {
	   						addressId = gettingProperty.getInt("address_id");
	   						
	   						String hnhnPcFromAid = "Select houseNameNumber, postcode from Address where address_id=? and placeName=?";
	   		        		   
	   						PreparedStatement getHnhnPc= connection.prepareStatement(hnhnPcFromAid);
	   						getHnhnPc.setInt(1, addressId);
	   						getHnhnPc.setString(2, placeName);
	   						
	   						ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
	   						   
	   						while(gettingHnhnPc.next()) {
	   							houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	   							pc = gettingHnhnPc.getString("postcode");
	   							
	   							search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, pc, 
	   													gettingProperty.getString("description"), gettingProperty.getString("shortName"), 
	   													gettingProperty.getInt("guestCapacity"));
	   							searchList.add(search);
	   						}
	   					}   
    			   }
    		   }
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
     //startDate, endDate, city
       if(minPPN == 0 && maxPPN == 0 && guestCap == 0 && sd != "" && ed !="" && placeName != "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;

    	   try {
    		   SearchObject search;
    		   
    		   String allCb = "select property_id, startDate, endDate from ChargeBands";
    		   PreparedStatement getAllCb = connection.prepareStatement(allCb);
    		   ResultSet gettingAllCb = getAllCb.executeQuery();

    		   System.out.println(getAllCb);
    		   while(gettingAllCb.next()) {
    			   if( parseDate(gettingAllCb.getString("startDate")).equals(startd) ||   startd.after(parseDate(gettingAllCb.getString("startDate"))) ||
					   parseDate(gettingAllCb.getString("endDate")).equals(endd) ||   endd.before(parseDate(gettingAllCb.getString("endDate")))) {
    				   propId = gettingAllCb.getInt("property_id");
    				   
    				   String propertyFromPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where property_id=?";
            		   
	   					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	   					getProperty.setInt(1, propId);
	   					
	   					ResultSet gettingProperty = getProperty.executeQuery();
	   					   
	   					while(gettingProperty.next()) {
	   						addressId = gettingProperty.getInt("address_id");
	   						
	   						String hnhnPcFromAid = "Select houseNameNumber, postcode from Address where address_id=? and placeName=?";
	   		        		   
	   						PreparedStatement getHnhnPc= connection.prepareStatement(hnhnPcFromAid);
	   						getHnhnPc.setInt(1, addressId);
	   						getHnhnPc.setString(2, placeName);
	   						
	   						ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
	   						   
	   						while(gettingHnhnPc.next()) {
	   							houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	   							pc = gettingHnhnPc.getString("postcode");
	   							
	   							search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, pc, 
	   													gettingProperty.getString("description"), gettingProperty.getString("shortName"), 
	   													gettingProperty.getInt("guestCapacity"));
	   							searchList.add(search);
	   						}
	   					}   
    			   }
    		   }
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
     //startDate, endDate, city
       if(minPPN == 0 && maxPPN == 0 && guestCap == 0 && sd != "" && ed !="" && placeName != "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;

    	   try {
    		   SearchObject search;
    		   
    		   String allCb = "select property_id, startDate, endDate from ChargeBands";
    		   PreparedStatement getAllCb = connection.prepareStatement(allCb);
    		   getAllCb.setDouble(1, minPPN);
    		   getAllCb.setDouble(2, maxPPN);
    		   
    		   ResultSet gettingAllCb = getAllCb.executeQuery();

    		   System.out.println(getAllCb);
    		   while(gettingAllCb.next()) {
    			   if( parseDate(gettingAllCb.getString("startDate")).equals(startd) ||   startd.after(parseDate(gettingAllCb.getString("startDate"))) ||
					   parseDate(gettingAllCb.getString("endDate")).equals(endd) ||   endd.before(parseDate(gettingAllCb.getString("endDate")))) {
    				   propId = gettingAllCb.getInt("property_id");
    				   
    				   String propertyFromPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where property_id=?";
            		   
	   					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	   					getProperty.setInt(1, propId);
	   					getProperty.setInt(2, guestCap);
	   					
	   					ResultSet gettingProperty = getProperty.executeQuery();
	   					   
	   					while(gettingProperty.next()) {
	   						addressId = gettingProperty.getInt("address_id");
	   						
	   						String hnhnPcFromAid = "Select houseNameNumber, postcode from Address where address_id=? and placeName=?";
	   		        		   
	   						PreparedStatement getHnhnPc= connection.prepareStatement(hnhnPcFromAid);
	   						getHnhnPc.setInt(1, addressId);
	   						getHnhnPc.setString(2, placeName);
	   						
	   						ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
	   						   
	   						while(gettingHnhnPc.next()) {
	   							houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	   							pc = gettingHnhnPc.getString("postcode");
	   							
	   							search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, pc, 
	   													gettingProperty.getString("description"), gettingProperty.getString("shortName"), 
	   													gettingProperty.getInt("guestCapacity"));
	   							searchList.add(search);
	   						}
	   					}   
    			   }
    		   }
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }

       /////////////////////////////////////////4 search criteria queries//////////////////////////////////////////////
     //minMax, startDate, endDate, guest
       if(minPPN != 0 && maxPPN != 0 && guestCap != 0 && sd != "" && ed !="" && placeName == "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;

    	   try {
    		   SearchObject search;
    		   
    		   String allCb = "select property_id, startDate, endDate from ChargeBands where totalPricePerNight>=? and totalPricePerNight<=?";
    		   PreparedStatement getAllCb = connection.prepareStatement(allCb);
    		   getAllCb.setDouble(1, minPPN);
    		   getAllCb.setDouble(2, maxPPN);
    		   
    		   ResultSet gettingAllCb = getAllCb.executeQuery();

    		   System.out.println(getAllCb);
    		   while(gettingAllCb.next()) {
    			   if( parseDate(gettingAllCb.getString("startDate")).equals(startd) ||   startd.after(parseDate(gettingAllCb.getString("startDate"))) ||
					   parseDate(gettingAllCb.getString("endDate")).equals(endd) ||   endd.before(parseDate(gettingAllCb.getString("endDate")))) {
    				   propId = gettingAllCb.getInt("property_id");
    				   
    				   String propertyFromPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where property_id=? and guestCapacity=?";
            		   
	   					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	   					getProperty.setInt(1, propId);
	   					getProperty.setInt(2, guestCap);
	   					
	   					ResultSet gettingProperty = getProperty.executeQuery();
	   					   
	   					while(gettingProperty.next()) {
	   						addressId = gettingProperty.getInt("address_id");
	   						
	   						String hnhnPcFromAid = "Select houseNameNumber, postcode from Address where address_id=?";
	   		        		   
	   						PreparedStatement getHnhnPc= connection.prepareStatement(hnhnPcFromAid);
	   						getHnhnPc.setInt(1, addressId);
	   						
	   						ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
	   						   
	   						while(gettingHnhnPc.next()) {
	   							houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	   							pc = gettingHnhnPc.getString("postcode");
	   							
	   							search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, pc, 
	   													gettingProperty.getString("description"), gettingProperty.getString("shortName"), 
	   													gettingProperty.getInt("guestCapacity"));
	   							searchList.add(search);
	   						}
	   					}   
    			   }
    		   }
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
       //minMax, startDate, city, guest
       if(minPPN != 0 && maxPPN != 0 && guestCap != 0 && sd != "" && ed =="" && placeName != "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;

    	   try {
    		   SearchObject search;
    		   
    		   String allCb = "select property_id, startDate from ChargeBands where totalPricePerNight>=? and totalPricePerNight<=?";
    		   PreparedStatement getAllCb = connection.prepareStatement(allCb);
    		   getAllCb.setDouble(1, minPPN);
    		   getAllCb.setDouble(2, maxPPN);
    		   
    		   ResultSet gettingAllCb = getAllCb.executeQuery();

    		   System.out.println(getAllCb);
    		   while(gettingAllCb.next()) {
    			   if( parseDate(gettingAllCb.getString("startDate")).equals(startd) ||   startd.after(parseDate(gettingAllCb.getString("startDate")))) {
    				   propId = gettingAllCb.getInt("property_id");
    				   
    				   String propertyFromPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where property_id=? and guestCapacity=?";
            		   
	   					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	   					getProperty.setInt(1, propId);
	   					getProperty.setInt(2, guestCap);
	   					
	   					ResultSet gettingProperty = getProperty.executeQuery();
	   					   
	   					while(gettingProperty.next()) {
	   						addressId = gettingProperty.getInt("address_id");
	   						
	   						String hnhnPcFromAid = "Select houseNameNumber, postcode from Address where address_id=? and placeName=?";
	   		        		   
	   						PreparedStatement getHnhnPc= connection.prepareStatement(hnhnPcFromAid);
	   						getHnhnPc.setInt(1, addressId);
	   						getHnhnPc.setString(2, placeName);
	   						
	   						ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
	   						   
	   						while(gettingHnhnPc.next()) {
	   							houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	   							pc = gettingHnhnPc.getString("postcode");
	   							
	   							search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, pc, 
	   													gettingProperty.getString("description"), gettingProperty.getString("shortName"), 
	   													gettingProperty.getInt("guestCapacity"));
	   							searchList.add(search);
	   						}
	   					}   
    			   }
    		   }
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
       //minMax, startDate, endDate, city
       if(minPPN != 0 && maxPPN != 0 && guestCap == 0 && sd != "" && ed !="" && placeName != "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;

    	   try {
    		   SearchObject search;
    		   
    		   String allCb = "select property_id, startDate, endDate from ChargeBands where totalPricePerNight>=? and totalPricePerNight<=?";
    		   PreparedStatement getAllCb = connection.prepareStatement(allCb);
    		   getAllCb.setDouble(1, minPPN);
    		   getAllCb.setDouble(2, maxPPN);
    		   
    		   ResultSet gettingAllCb = getAllCb.executeQuery();

    		   System.out.println(getAllCb);
    		   while(gettingAllCb.next()) {
    			   if( parseDate(gettingAllCb.getString("startDate")).equals(startd) ||   startd.after(parseDate(gettingAllCb.getString("startDate"))) ||
					   parseDate(gettingAllCb.getString("endDate")).equals(endd) ||   endd.before(parseDate(gettingAllCb.getString("endDate")))) {
    				   propId = gettingAllCb.getInt("property_id");
    				   
    				   String propertyFromPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where property_id=? and guestCapacity=?";
            		   
	   					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	   					getProperty.setInt(1, propId);
	   					getProperty.setInt(2, guestCap);
	   					
	   					ResultSet gettingProperty = getProperty.executeQuery();
	   					   
	   					while(gettingProperty.next()) {
	   						addressId = gettingProperty.getInt("address_id");
	   						
	   						String hnhnPcFromAid = "Select houseNameNumber, postcode from Address where address_id=? and placeName=?";
	   		        		   
	   						PreparedStatement getHnhnPc= connection.prepareStatement(hnhnPcFromAid);
	   						getHnhnPc.setInt(1, addressId);
	   						getHnhnPc.setString(2, placeName);
	   						
	   						ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
	   						   
	   						while(gettingHnhnPc.next()) {
	   							houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	   							pc = gettingHnhnPc.getString("postcode");
	   							
	   							search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, pc, 
	   													gettingProperty.getString("description"), gettingProperty.getString("shortName"), 
	   													gettingProperty.getInt("guestCapacity"));
	   							searchList.add(search);
	   						}
	   					}   
    			   }
    		   }
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
       //minMax, startDate, endDate, city
       if(minPPN != 0 && maxPPN != 0 && guestCap == 0 && sd != "" && ed !="" && placeName != "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;

    	   try {
    		   SearchObject search;
    		   
    		   String allCb = "select property_id, startDate, endDate from ChargeBands where totalPricePerNight>=? and totalPricePerNight<=?";
    		   PreparedStatement getAllCb = connection.prepareStatement(allCb);
    		   getAllCb.setDouble(1, minPPN);
    		   getAllCb.setDouble(2, maxPPN);
    		   
    		   ResultSet gettingAllCb = getAllCb.executeQuery();

    		   System.out.println(getAllCb);
    		   while(gettingAllCb.next()) {
    			   if( parseDate(gettingAllCb.getString("startDate")).equals(startd) ||   startd.after(parseDate(gettingAllCb.getString("startDate"))) ||
					   parseDate(gettingAllCb.getString("endDate")).equals(endd) ||   endd.before(parseDate(gettingAllCb.getString("endDate")))) {
    				   propId = gettingAllCb.getInt("property_id");
    				   
    				   String propertyFromPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where property_id=?";
            		   
	   					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	   					getProperty.setInt(1, propId);
	   					getProperty.setInt(2, guestCap);
	   					
	   					ResultSet gettingProperty = getProperty.executeQuery();
	   					   
	   					while(gettingProperty.next()) {
	   						addressId = gettingProperty.getInt("address_id");
	   						
	   						String hnhnPcFromAid = "Select houseNameNumber, postcode from Address where address_id=? and placeName=?";
	   		        		   
	   						PreparedStatement getHnhnPc= connection.prepareStatement(hnhnPcFromAid);
	   						getHnhnPc.setInt(1, addressId);
	   						getHnhnPc.setString(2, placeName);
	   						
	   						ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
	   						   
	   						while(gettingHnhnPc.next()) {
	   							houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	   							pc = gettingHnhnPc.getString("postcode");
	   							
	   							search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, pc, 
	   													gettingProperty.getString("description"), gettingProperty.getString("shortName"), 
	   													gettingProperty.getInt("guestCapacity"));
	   							searchList.add(search);
	   						}
	   					}   
    			   }
    		   }
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
     //minMax, endDate, city, guest
       if(minPPN != 0 && maxPPN != 0 && guestCap != 0 && sd == "" && ed !="" && placeName != "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;

    	   try {
    		   SearchObject search;
    		   
    		   String allCb = "select property_id, endDate from ChargeBands where totalPricePerNight>=? and totalPricePerNight<=?";
    		   PreparedStatement getAllCb = connection.prepareStatement(allCb);
    		   getAllCb.setDouble(1, minPPN);
    		   getAllCb.setDouble(2, maxPPN);
    		   
    		   ResultSet gettingAllCb = getAllCb.executeQuery();

    		   System.out.println(getAllCb);
    		   while(gettingAllCb.next()) {
    			   if(parseDate(gettingAllCb.getString("endDate")).equals(endd) ||   endd.before(parseDate(gettingAllCb.getString("endDate")))) {
    				   propId = gettingAllCb.getInt("property_id");
    				   
    				   String propertyFromPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where property_id=? and guestCapacity=?";
            		   
	   					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	   					getProperty.setInt(1, propId);
	   					getProperty.setInt(2, guestCap);
	   					
	   					ResultSet gettingProperty = getProperty.executeQuery();
	   					   
	   					while(gettingProperty.next()) {
	   						addressId = gettingProperty.getInt("address_id");
	   						
	   						String hnhnPcFromAid = "Select houseNameNumber, postcode from Address where address_id=? and placeName=?";
	   		        		   
	   						PreparedStatement getHnhnPc= connection.prepareStatement(hnhnPcFromAid);
	   						getHnhnPc.setInt(1, addressId);
	   						getHnhnPc.setString(2, placeName);
	   						
	   						ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
	   						   
	   						while(gettingHnhnPc.next()) {
	   							houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	   							pc = gettingHnhnPc.getString("postcode");
	   							
	   							search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, pc, 
	   													gettingProperty.getString("description"), gettingProperty.getString("shortName"), 
	   													gettingProperty.getInt("guestCapacity"));
	   							searchList.add(search);
	   						}
	   					}   
    			   }
    		   }
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
     //startDate, endDate, city, guest
       if(minPPN == 0 && maxPPN == 0 && guestCap != 0 && sd != "" && ed !="" && placeName != "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;

    	   try {
    		   SearchObject search;
    		   
    		   String allCb = "select property_id, startDate, endDate from ChargeBands";
    		   PreparedStatement getAllCb = connection.prepareStatement(allCb);
    		   getAllCb.setDouble(1, minPPN);
    		   getAllCb.setDouble(2, maxPPN);
    		   
    		   ResultSet gettingAllCb = getAllCb.executeQuery();

    		   System.out.println(getAllCb);
    		   while(gettingAllCb.next()) {
    			   if( parseDate(gettingAllCb.getString("startDate")).equals(startd) ||   startd.after(parseDate(gettingAllCb.getString("startDate"))) ||
					   parseDate(gettingAllCb.getString("endDate")).equals(endd) ||   endd.before(parseDate(gettingAllCb.getString("endDate")))) {
    				   propId = gettingAllCb.getInt("property_id");
    				   
    				   String propertyFromPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where property_id=? and guestCapacity=?";
            		   
	   					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	   					getProperty.setInt(1, propId);
	   					getProperty.setInt(2, guestCap);
	   					
	   					ResultSet gettingProperty = getProperty.executeQuery();
	   					   
	   					while(gettingProperty.next()) {
	   						addressId = gettingProperty.getInt("address_id");
	   						
	   						String hnhnPcFromAid = "Select houseNameNumber, postcode from Address where address_id=? and placeName=?";
	   		        		   
	   						PreparedStatement getHnhnPc= connection.prepareStatement(hnhnPcFromAid);
	   						getHnhnPc.setInt(1, addressId);
	   						getHnhnPc.setString(2, placeName);
	   						
	   						ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
	   						   
	   						while(gettingHnhnPc.next()) {
	   							houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	   							pc = gettingHnhnPc.getString("postcode");
	   							
	   							search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, pc, 
	   													gettingProperty.getString("description"), gettingProperty.getString("shortName"), 
	   													gettingProperty.getInt("guestCapacity"));
	   							searchList.add(search);
	   						}
	   					}   
    			   }
    		   }
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
       
       /////////////////////////////////////////All 5 search criteria queries//////////////////////////////////////////
       //minMax, startDate, endDate, city, guest
       if(minPPN != 0 && maxPPN != 0 && guestCap != 0 && sd != "" && ed !="" && placeName != "" ) {
    	   int addressId;
    	   int propId;
           String houseNameNum, pc;

    	   try {
    		   SearchObject search;
    		   
    		   String allCb = "select property_id, startDate, endDate from ChargeBands where totalPricePerNight>=? and totalPricePerNight<=?";
    		   PreparedStatement getAllCb = connection.prepareStatement(allCb);
    		   getAllCb.setDouble(1, minPPN);
    		   getAllCb.setDouble(2, maxPPN);
    		   
    		   ResultSet gettingAllCb = getAllCb.executeQuery();

    		   System.out.println(getAllCb);
    		   while(gettingAllCb.next()) {
    			   if( parseDate(gettingAllCb.getString("startDate")).equals(startd) ||   startd.after(parseDate(gettingAllCb.getString("startDate"))) ||
					   parseDate(gettingAllCb.getString("endDate")).equals(endd) ||   endd.before(parseDate(gettingAllCb.getString("endDate")))) {
    				   propId = gettingAllCb.getInt("property_id");
    				   
    				   String propertyFromPid = "Select property_id, address_id, description, shortName, guestCapacity from Property where property_id=? and guestCapacity=?";
            		   
	   					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
	   					getProperty.setInt(1, propId);
	   					getProperty.setInt(2, guestCap);
	   					
	   					ResultSet gettingProperty = getProperty.executeQuery();
	   					   
	   					while(gettingProperty.next()) {
	   						addressId = gettingProperty.getInt("address_id");
	   						
	   						String hnhnPcFromAid = "Select houseNameNumber, postcode from Address where address_id=? and placeName=?";
	   		        		   
	   						PreparedStatement getHnhnPc= connection.prepareStatement(hnhnPcFromAid);
	   						getHnhnPc.setInt(1, addressId);
	   						getHnhnPc.setString(2, placeName);
	   						
	   						ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
	   						   
	   						while(gettingHnhnPc.next()) {
	   							houseNameNum = gettingHnhnPc.getString("houseNameNumber");
	   							pc = gettingHnhnPc.getString("postcode");
	   							
	   							search = new SearchObject(gettingProperty.getInt("property_id"), houseNameNum, pc, 
	   													gettingProperty.getString("description"), gettingProperty.getString("shortName"), 
	   													gettingProperty.getInt("guestCapacity"));
	   							searchList.add(search);
	   						}
	   					}   
    			   }
    		   }
           }catch (Exception e) {
        	   e.printStackTrace();
           } 
       }
       
//       try {
//			 String cityToHnhnPc = "SELECT houseNameNumber, postcode FROM `Address` WHERE placeName =?";
//			 String hnhnPcToPid = "Select property_id from Property where houseNameNumber=? and postcode=?";
//			 String pidToSED  ="Select property_id from ChargeBands where property_id=? and startDate=?";
//			 String propertyFromPid = "Select property_id, houseNameNumber, postcode, description, shortName, guestCapacity from Property where property_id=?";
			 
			 
//    	   PreparedStatement getHnhnPc = connection.prepareStatement(cityToHnhnPc);
//    	   getHnhnPc.setString(1, placeName);
//
//           ResultSet gettingHnhnPc = getHnhnPc.executeQuery();
//
//           SearchObject search;
//           String houseNameNum, pc;
//           int p_id;
//           int p_idFinal;
//           
//    	   while(gettingHnhnPc.next()) {
//    		   houseNameNum = gettingHnhnPc.getString("houseNameNumber");
//    		   pc = gettingHnhnPc.getString("postcode");
//
////    		   System.out.println("HNHN PC RS= "+houseNameNum+" pc = "+pc);
//    		   
//			   PreparedStatement getPid = connection.prepareStatement(hnhnPcToPid);
//			   getPid.setString(1, houseNameNum);
//			   getPid.setString(2, pc);
//               ResultSet gettingPid = getPid.executeQuery();
//    		   while(gettingPid.next()) {
//    			   p_id = gettingPid.getInt("property_id");
////    			   System.out.println("P_id = "+p_id);
//    			   
//    			   PreparedStatement getSED = connection.prepareStatement(pidToSED);
//    			   getSED.setInt(1, p_id);
//    			   getSED.setString(2, sd);
//                   ResultSet gettingSED = getSED.executeQuery();
//                   while(gettingSED.next()) {
//                	   p_idFinal = gettingSED.getInt("property_id");
////        			   System.out.println("final prop id ------"+p_idFinal);
//        			   
//        			   PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
//        			   getProperty.setInt(1, p_idFinal);
//                       ResultSet gettingProperty = getProperty.executeQuery();
//        			   
//                       while(gettingProperty.next()) {
//            			   System.out.println("final prop id ------"+gettingProperty.getInt("property_id"));
//            			   
//                    	   search = new SearchObject(gettingProperty.getInt("property_id"), gettingProperty.getString("houseNameNumber"), 
//                    			   gettingProperty.getString("postcode"), gettingProperty.getString("description"), 
//                    			   gettingProperty.getString("shortName"),gettingProperty.getInt("guestCapacity"));
//                           searchList.add(search);
//                       }
//                   }
//    		   }
//    	   }
//           
//       } catch (Exception e) {
//           e.printStackTrace();
//       }
       return searchList;
   }
   
   // Display Data In JTable
   
   public void Show_Search_In_JTable()
   {
       ArrayList<SearchObject> list = getSearchList();
       DefaultTableModel model = (DefaultTableModel)jTable_Display_Search.getModel();
       Object[] row = new Object[6];
       for(int i = 0; i < list.size(); i++)
       {
           row[0] = list.get(i).getPropertyId();
           row[1] = list.get(i).getHouseNameNumber();
           row[2] = list.get(i).getPostcode();
           row[3] = list.get(i).getDescription();
           row[4] = list.get(i).getShortName();
           row[5] = list.get(i).getGuestCapacity();
//           row[6] = list.get(i).getStartDate();
//           row[7] = list.get(i).getEndDate();
//           row[8] = list.get(i).getPlaceName();
           
           model.addRow(row);
       }
    }
       
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
       
//        jTextField_startDate= new javax.swing.JTextField();
//        jTextField_endDate = new javax.swing.JTextField();
//        jTextField_city = new javax.swing.JTextField();
        
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Display_Search = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        
        jLabel5.setFont(new java.awt.Font("Verdana", 0, 18)); 
        jLabel5.setText("Short Name:");
        
        jLabel6.setFont(new java.awt.Font("Verdana", 0, 18)); 
        jLabel6.setText("Guest Capacity:");
        
      

//        jTextField_startDate.setFont(new java.awt.Font("Verdana", 0, 14)); 
//        jTextField_startDate.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jTextField_AgeActionPerformed(evt);
//            }
//        });
//
//        jTextField_endDate.setFont(new java.awt.Font("Verdana", 0, 14)); 
//        jTextField_endDate.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jTextField_AgeActionPerformed(evt);
//            }
//        });
//
//        jTextField_city.setFont(new java.awt.Font("Verdana", 0, 14)); 
//        jTextField_city.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jTextField_AgeActionPerformed(evt);
//            }
//        });
                 
        jTable_Display_Search.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Property ID", "House Name/Number", "Postcode", "Description", "Short Name", "Guest Capacity"
            }
        ));
        
        
        jTable_Display_Search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Display_SearchMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Display_Search);
        
        btnNewButton = new JButton("View Property");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        minPriceTextField = new JTextField();
        minPriceTextField.setColumns(10);
        
        maxPriceTextField = new JTextField();
        maxPriceTextField.setColumns(10);
        
        startDateTextField = new JTextField();
        startDateTextField.setColumns(10);
        
        endDateTextField = new JTextField();
        endDateTextField.setColumns(10);
        
        guestCapacityTextField = new JTextField();
        guestCapacityTextField.setColumns(10);
        
        locationComboBox = new JComboBox();
        
        JLabel minPriceLabel = new JLabel("Minimum Price Per Night");
        minPriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        maxPriceLabel = new JLabel("Maximum Price Per Night");
        maxPriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        startDateLabel = new JLabel("Start Date");
        startDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        endDateLabel = new JLabel("End Date");
        endDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        guestCapacityLabel = new JLabel("Guest Capacity");
        guestCapacityLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        locationLabel = new JLabel("Location");
        locationLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        propertyIdLabel = new JLabel("Property ID");
        propertyIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        propertyIDTextField = new JTextField();
        propertyIDTextField.setColumns(10);

		
		
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap(33, Short.MAX_VALUE)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        					.addGroup(jPanel1Layout.createSequentialGroup()
        						.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        							.addComponent(locationLabel, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
        							.addComponent(guestCapacityLabel, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
        							.addComponent(startDateLabel, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
        							.addComponent(endDateLabel, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
        							.addComponent(maxPriceLabel, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
        							.addComponent(minPriceLabel, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE))
        						.addGap(18)
        						.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
        							.addComponent(locationComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        							.addComponent(maxPriceTextField, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
        							.addComponent(minPriceTextField, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
        							.addComponent(startDateTextField, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
        							.addComponent(endDateTextField, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
        							.addComponent(guestCapacityTextField, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)))
        					.addGroup(jPanel1Layout.createSequentialGroup()
        						.addComponent(propertyIdLabel, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
        						.addGap(18)
        						.addComponent(propertyIDTextField, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(134)
        					.addComponent(btnNewButton)))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)
        			.addGap(22))
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(68)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(minPriceTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
        				.addComponent(minPriceLabel, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addComponent(maxPriceTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(startDateTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
        						.addComponent(startDateLabel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
        				.addComponent(maxPriceLabel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(endDateTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
        				.addComponent(endDateLabel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(guestCapacityTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
        				.addComponent(guestCapacityLabel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(locationLabel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
        				.addComponent(locationComboBox, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
        			.addGap(119)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(propertyIdLabel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
        				.addComponent(propertyIDTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
        			.addGap(110)
        			.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
        			.addGap(113))
        		.addGroup(Alignment.LEADING, jPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 634, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(98, Short.MAX_VALUE))
        );
        jPanel1.setLayout(jPanel1Layout);
        
        navBarPanel = new JPanel();
        navBarPanel.setBackground(new Color(51, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        		.addComponent(navBarPanel, GroupLayout.DEFAULT_SIZE, 893, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        			.addComponent(navBarPanel, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 666, GroupLayout.PREFERRED_SIZE))
        );
        getContentPane().setLayout(layout);
        
        //NAVBAR	
    	JButton navHomeButton = new JButton("Home");
    	navHomeButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
    	navBarPanel.add(navHomeButton);
    	navHomeButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			//Homepage sp = new Homepage();

    			mainModule.currentState=STATE.HOST_ACCOUNT;
    			mainModule.userState=USER.HOST;
    			MainModule.controller.drawNewView();
//			close();
    			setVisible(false);
    		}
    	});
    	
    	
    	JButton navLogoutButton = new JButton("Logout");
    	navLogoutButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
    	navLogoutButton.setForeground(new Color(0, 0, 0));
    	navBarPanel.add(navLogoutButton);
    	navLogoutButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			mainModule.currentState = STATE.HOMEPAGE;
    			mainModule.userState = USER.ENQUIRER;
    			
    			MainModule.controller.drawNewView();
//			close();
    			setVisible(false);
    		}
    	});
    
    	
    	JButton navSearchButton = new JButton("Search");
    	navSearchButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
    	navBarPanel.add(navSearchButton);
    	navSearchButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			mainModule.currentState=STATE.SEARCH;
    			mainModule.userState=USER.HOST;
    			MainModule.controller.drawNewView();
//			close();
    			setVisible(false);
    		}
    	});

        pack();
    }                     

    private void jTextField_AgeActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

 // show jtable row data in jtextfields in the mouse clicked event
    private void jTable_Display_SearchMouseClicked(java.awt.event.MouseEvent evt) {                                                  
       // Get The Index Of The Slected Row 
        int i = jTable_Display_Search.getSelectedRow();

        TableModel model = jTable_Display_Search.getModel();
        
         // Display Slected Row In JTexteFields
        	
        propertyIDTextField.setText(model.getValueAt(i,0).toString());
        
//        jTextField_startDate.setText(model.getValueAt(i,4).toString());
//        jTextField_endDate.setText(model.getValueAt(i,5).toString());
//        jTextField_city.setText(model.getValueAt(i,6).toString());
    }                                                 

    
    /**
     * @param args the command line arguments
     */
    public void initializeSearch() {
//    	hostId = host_Id;
//    	System.out.println("UGHHHHHHHHHHHHHHHHHHHHHHHH PROPERTY ID :"+hostId);
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
        
    		
            public void run() {
            
                new Search(mainModule, controller, model).setVisible(true);
                
            }
        });
    }
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
//    private javax.swing.JLabel jLabel7;
//    private javax.swing.JLabel jLabel8;
//    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Display_Search;
    private JButton btnNewButton;
    private JTextField minPriceTextField;
    private JTextField maxPriceTextField;
    private JTextField startDateTextField;
    private JTextField endDateTextField;
    private JTextField guestCapacityTextField;
    private JComboBox locationComboBox;
    private JLabel maxPriceLabel;
    private JLabel startDateLabel;
    private JLabel endDateLabel;
    private JLabel guestCapacityLabel;
    private JLabel locationLabel;
    private JLabel propertyIdLabel;
    private JTextField propertyIDTextField;
    private JPanel navBarPanel;
}

//code partially from https://1bestcsharp.blogspot.com/2016/01/java-and-mysql-insert-update-delete-display.html
