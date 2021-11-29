package HostGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Controller.Controller;
import GUI.MainModule;
import GUI.MainModule.EDITPROPERTY;
import GUI.MainModule.STATE;
import GUI.MainModule.USER;
import GuestGUI.BookingObject;
import Model.Model;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.util.Date;
import java.time.Month;
import java.time.LocalDate;
/**
 *
 * @author 1bestcsharp.blogspot.com
 */
public class ProvisionalBookings extends javax.swing.JFrame {

	private Controller controller;
	 private Model model;
	 private MainModule mainModule;
	 
    /**
     * Creates new form Java_Insert_Update_Delete_Display
     */
    public ProvisionalBookings(MainModule mainModule, Controller controller, Model model) {
    	this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
		
        initComponents();
        Show_Bookings_In_JTable();
    }
    
     // get the connection
    
   private static String serverName = "jdbc:mysql://stusql.dcs.shef.ac.uk/team018";
   private static String username = "team018";
   private static String pwd = "7854a03f";
	
   public Connection getConnection() {
       Connection connection;
       try {
    	   connection = DriverManager.getConnection(serverName, username, pwd);
           return connection;
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
   }
           
 // get a list of users from mysql database
   public ArrayList<BookingObject> getBookingsList() {
	   
       ArrayList<BookingObject> bookingsList = new ArrayList<BookingObject>();
       Connection connection = getConnection();
       
   
       if(mainModule.userState == USER.HOST) {
       
       String query = "SELECT * FROM `Booking` where host_id=" + Id + " and provisional=true";	
       
       System.out.println(query);
       Statement st;
       ResultSet rs;
       
       try {
           st = connection.createStatement();
           rs = st.executeQuery(query);
           BookingObject bookings; 
           while(rs.next())
           {
        	   bookings = new BookingObject(rs.getInt("booking_id"), rs.getInt("property_id"), rs.getInt("host_id"), rs.getInt("guest_id"), rs.getBoolean("provisional"), rs.getDouble("totalPrice"), rs.getDate("startDate"), rs.getDate("endDate"));
        	   bookingsList.add(bookings);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return bookingsList;
       
       }else {
           String query = "SELECT * FROM `Booking` where guest_id=" + Id + " and provisional=true";	
           
           System.out.println(query);
           Statement st;
           ResultSet rs;
           
           try {
               st = connection.createStatement();
               rs = st.executeQuery(query);
               BookingObject bookings; 
               while(rs.next())
               {
            	   bookings = new BookingObject(rs.getInt("booking_id"), rs.getInt("property_id"), rs.getInt("host_id"), rs.getInt("guest_id"), rs.getBoolean("provisional"), rs.getDouble("totalPrice"), rs.getDate("startDate"), rs.getDate("endDate"));
            	   bookingsList.add(bookings);
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
           return bookingsList;
            
       }
   }
   
   // Display Data In JTable
   
   public void Show_Bookings_In_JTable() {
       ArrayList<BookingObject> list = getBookingsList();
       DefaultTableModel model = (DefaultTableModel)jTable_Display_Reviews.getModel();
       Object[] row = new Object[8];
       for(int i = 0; i < list.size(); i++)
       {
           row[0] = list.get(i).getBooking_id();
           row[1] = list.get(i).getProperty_id();
           row[2] = list.get(i).getHost_id();
           row[3] = list.get(i).getGuest_id();
           row[4] = list.get(i).getProvisional();
           row[5] = list.get(i).getTotalPrice();
           row[6] = list.get(i).getStartDate();
           row[7] = list.get(i).getEndDate();
           
           model.addRow(row);
       }
    }
        
   // Execute The Insert Update And Delete Querys
   public void executeSQlQuery(String query, String message) {
       Connection con = getConnection();
       Statement st;
       try{
           st = con.createStatement();
           if((st.executeUpdate(query)) == 1)
           {
               // refresh jtable data
               DefaultTableModel model = (DefaultTableModel)jTable_Display_Reviews.getModel();
               model.setRowCount(0);
               Show_Bookings_In_JTable();
               
               JOptionPane.showMessageDialog(null, "Data "+message+" Successfully");
           }else{
               JOptionPane.showMessageDialog(null, "Data Not "+message);
           }
       }catch(Exception ex){
           ex.printStackTrace();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        
        
        jTextField_booking_id = new javax.swing.JTextField();
        jTextField_property_id = new javax.swing.JTextField();
        jTextField_host_id = new javax.swing.JTextField();
        jTextField_guest_id = new javax.swing.JTextField();
        jTextField_sleeping_id = new javax.swing.JTextField();
        jTextField_bathing_id = new javax.swing.JTextField();
        jTextField_living_id = new javax.swing.JTextField();
        
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Display_Reviews = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 18)); 
        jLabel1.setText("Booking_ID:");

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 18)); 
        jLabel2.setText("Property_ID");

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 18)); 
        jLabel3.setText("Host_ID");

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 18)); 
        jLabel4.setText("Guest ID:");
        
        jLabel5.setFont(new java.awt.Font("Verdana", 0, 18)); 
        jLabel5.setText("Sleeping:");
        
        jLabel6.setFont(new java.awt.Font("Verdana", 0, 18)); 
        jLabel6.setText("Bathing:");
        
        jLabel7.setFont(new java.awt.Font("Verdana", 0, 18)); 
        jLabel7.setText("Living:");
        
        
        //NAVBAR
        
 


	
        
 
        

        jTextField_booking_id.setFont(new java.awt.Font("Verdana", 0, 14)); 

        jTextField_property_id.setFont(new java.awt.Font("Verdana", 0, 14)); 
        jTextField_property_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_FirstNameActionPerformed(evt);
            }
        });

        jTextField_host_id.setFont(new java.awt.Font("Verdana", 0, 14)); 
        jTextField_host_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_LastNameActionPerformed(evt);
            }
        });

        jTextField_guest_id.setFont(new java.awt.Font("Verdana", 0, 14)); 
        jTextField_guest_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_AgeActionPerformed(evt);
            }
        });

        jTextField_sleeping_id.setFont(new java.awt.Font("Verdana", 0, 14));
        jTextField_sleeping_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_AgeActionPerformed(evt);
            }
        });
        
        jTextField_bathing_id.setFont(new java.awt.Font("Verdana", 0, 14)); 
        jTextField_bathing_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_AgeActionPerformed(evt);
            }
        });
        
        jTextField_living_id.setFont(new java.awt.Font("Verdana", 0, 14)); 
        jTextField_living_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_AgeActionPerformed(evt);
            }
        });
                
        jTable_Display_Reviews.setModel(new javax.swing.table.DefaultTableModel(
        		new Object [][] {}, new String [] {"Booking_ID", "Property_ID","Host_ID", "Guest_ID", 
        											"Provisional", "TotalPrice", "Start Date", "End Date"}));
        
        jTable_Display_Reviews.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Display_ReviewsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Display_Reviews);
        
        backButton = new JButton("Back");
        backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
        backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Homepage sp = new Homepage();
				
				mainModule.currentState=STATE.HOST_ACCOUNT;
				mainModule.editPropertyState = EDITPROPERTY.PROPERTIES;
				mainModule.userState=USER.HOST;
				MainModule.controller.editPropertyView(model.getHostId(),model.getPropertyId());
//				close();
			//	model.setEditPropertyPostcode(null);
				setVisible(false);
				
			}
		});
        
        lblCommunication = new JLabel();
        lblCommunication.setText("Provisional:");
        lblCommunication.setFont(new Font("Verdana", Font.PLAIN, 18));
        
        lblCleanliness = new JLabel();
        lblCleanliness.setText("Total Price:");
        lblCleanliness.setFont(new Font("Verdana", Font.PLAIN, 18));
        
        lblDescription = new JLabel();
        lblDescription.setText("Start Date:");
        lblDescription.setFont(new Font("Verdana", Font.PLAIN, 18));
        
        jTextField_provisional = new JTextField();
        jTextField_provisional.setFont(new Font("Verdana", Font.PLAIN, 14));
        
        jTextField_totalPrice = new JTextField();
        jTextField_totalPrice.setFont(new Font("Verdana", Font.PLAIN, 14));
        
        jTextField_startDate = new JTextField();
        jTextField_startDate.setFont(new Font("Verdana", Font.PLAIN, 14));
        
        jTextField_endDate = new JTextField();
        jTextField_endDate.setFont(new Font("Verdana", Font.PLAIN, 14));
        
        lblEndDate = new JLabel();
        lblEndDate.setText("End Date:");
        lblEndDate.setFont(new Font("Verdana", Font.PLAIN, 18));

		
		
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap(33, Short.MAX_VALUE)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addComponent(lblEndDate, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(jTextField_endDate, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
        						.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        							.addGroup(jPanel1Layout.createSequentialGroup()
        								.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
        								.addPreferredGap(ComponentPlacement.RELATED)
        								.addComponent(jTextField_startDate, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
        							.addGroup(jPanel1Layout.createSequentialGroup()
        								.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        									.addComponent(jLabel2)
        									.addComponent(jLabel4)
        									.addComponent(jLabel1)
        									.addComponent(jLabel3)
        									.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING, false)
        										.addComponent(lblCleanliness, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        										.addComponent(lblCommunication, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)))
        								.addPreferredGap(ComponentPlacement.RELATED)
        								.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        									.addComponent(jTextField_totalPrice, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        									.addComponent(jTextField_provisional, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        									.addComponent(jTextField_host_id, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        									.addComponent(jTextField_booking_id, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        									.addComponent(jTextField_property_id, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        									.addComponent(jTextField_guest_id, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))))
        					.addGap(18)
        					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(21)
        					.addComponent(backButton)
        					.addGap(18)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        								.addComponent(jTextField_booking_id, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
        								.addComponent(jLabel1))
        							.addGap(11)
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        								.addComponent(jLabel2)
        								.addComponent(jTextField_property_id, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
        							.addGap(18)
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        								.addComponent(jLabel3)
        								.addComponent(jTextField_host_id, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
        							.addGap(22)
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        								.addComponent(jTextField_guest_id, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
        								.addComponent(jLabel4))
        							.addGap(18)
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(lblCommunication, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
        								.addComponent(jTextField_provisional, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
        							.addGap(17)
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        								.addComponent(lblCleanliness, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
        								.addComponent(jTextField_totalPrice, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
        							.addGap(18)
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        								.addComponent(jTextField_startDate, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
        								.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addGap(361)
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(lblEndDate, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
        								.addComponent(jTextField_endDate, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(62)
        					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap())
        );
        jPanel1.setLayout(jPanel1Layout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 535, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);
        
        pack();
    }                     

    private void jTextField_FirstNameActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        // TODO add your handling code here:
    }                                                    

    private void jTextField_LastNameActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        // TODO add your handling code here:
    }                                                   

    private void jTextField_AgeActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

 // show jtable row data in jtextfields in the mouse clicked event
    private void jTable_Display_ReviewsMouseClicked(java.awt.event.MouseEvent evt) {                                                  
       // Get The Index Of The Slected Row 
        int i = jTable_Display_Reviews.getSelectedRow();

        TableModel model = jTable_Display_Reviews.getModel();
        
         // Display Slected Row In JTexteFields
        jTextField_booking_id.setText(model.getValueAt(i,0).toString());

        jTextField_property_id.setText(model.getValueAt(i,1).toString());

        jTextField_host_id.setText(model.getValueAt(i,2).toString());

        jTextField_guest_id.setText(model.getValueAt(i,3).toString());
        
        jTextField_provisional.setText(model.getValueAt(i,4).toString());
        
        jTextField_totalPrice.setText(model.getValueAt(i,5).toString());
        
        jTextField_startDate.setText(model.getValueAt(i,6).toString());
        
        jTextField_endDate.setText(model.getValueAt(i,7).toString());
    }                                                 

    /**
     * @param args the command line arguments
     */
    public void initializeProvisionalBookings(int fId, int id) {
    	propertyId = fId;
    	Id = id;
    	System.out.println("UGHHHHHHHHHHHHHHHHHHHHHHHH FACILITIES ID :"+propertyId);
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
            java.util.logging.Logger.getLogger(ProvisionalBookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProvisionalBookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProvisionalBookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProvisionalBookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
        
    		
            public void run() {
            
                new ProvisionalBookings(mainModule, controller, model).setVisible(true);
                
            }
        });
    }
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Display_Reviews;
    private javax.swing.JTextField jTextField_booking_id;
    private javax.swing.JTextField jTextField_property_id;
    private javax.swing.JTextField jTextField_host_id;
    private javax.swing.JTextField jTextField_guest_id;
    private javax.swing.JTextField jTextField_sleeping_id;
    private javax.swing.JTextField jTextField_bathing_id;
    private javax.swing.JTextField jTextField_living_id;
    private JButton backButton;
    private static int propertyId;
    private static int Id;
    private JLabel lblCommunication;
    private JLabel lblCleanliness;
    private JLabel lblDescription;
    private JTextField jTextField_provisional;
    private JTextField jTextField_totalPrice;
    private JTextField jTextField_startDate;
    private JTextField jTextField_endDate;
    private JLabel lblEndDate;
}

//code partially from https://1bestcsharp.blogspot.com/2016/01/java-and-mysql-insert-update-delete-display.html
