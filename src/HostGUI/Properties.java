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
import Model.Model;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import java.awt.Font;

/**
 *
 * @author 1bestcsharp.blogspot.com
 */
public class Properties extends javax.swing.JFrame {
		
    /**
     * Creates new form Java_Insert_Update_Delete_Display
     */
//
	 private Controller controller;
	 private Model model;
	 private MainModule mainModule;
	 
    public Properties(MainModule mainModule, Controller controller, Model model) {
    	this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
		
        initComponents();
        Show_Users_In_JTable();
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
        
   	
   
 // get a list of users from mysql database
   public ArrayList<PropertyObject> getUsersList() {
       ArrayList<PropertyObject> propertiesList = new ArrayList<PropertyObject>();
       Connection connection = getConnection();
       System.out.println("you got this !!! cmonnn " + hostId);
       String query = "SELECT * FROM `Property` WHERE host_id = "+hostId;
       System.out.println(query);
       Statement st;
       ResultSet rs;
       
       try {
           st = connection.createStatement();
           rs = st.executeQuery(query);
           PropertyObject property;
           while(rs.next())
           {
        	   property = new PropertyObject(rs.getInt("property_id"),rs.getString("houseNameNumber"),rs.getString("postcode"),rs.getInt("host_id"),rs.getString("description"),rs.getString("shortName"),rs.getInt("guestCapacity"));
               propertiesList.add(property);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return propertiesList;
   }
   
   // Display Data In JTable
   
   public void Show_Users_In_JTable()
   {
       ArrayList<PropertyObject> list = getUsersList();
       DefaultTableModel model = (DefaultTableModel)jTable_Display_Properties.getModel();
       Object[] row = new Object[7];
       for(int i = 0; i < list.size(); i++)
       {
           row[0] = list.get(i).getPropertyId();
           row[1] = list.get(i).getHouseNameNumber();
           row[2] = list.get(i).getPostcode();
           row[3] = list.get(i).getHostId();
           row[4] = list.get(i).getDescription();
           row[5] = list.get(i).getShortName();
           row[6] = list.get(i).getGuestCapacity();
           
           model.addRow(row);
       }
    }
        
   // Execute The Insert Update And Delete Querys
   public void executeSQlQuery(String query, String message)
   {
       Connection con = getConnection();
       Statement st;
       try{
           st = con.createStatement();
           if((st.executeUpdate(query)) == 1)
           {
               // refresh jtable data
               DefaultTableModel model = (DefaultTableModel)jTable_Display_Properties.getModel();
               model.setRowCount(0);
               Show_Users_In_JTable();
               
               JOptionPane.showMessageDialog(null, "Data "+message+" Succefully");
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
        
        
        jTextField_property_id = new javax.swing.JTextField();
        jTextField_houseNameNumber = new javax.swing.JTextField();
        jTextField_postcode = new javax.swing.JTextField();
        jTextField_host_id = new javax.swing.JTextField();
        jTextField_description = new javax.swing.JTextField();
        jTextField_shortName = new javax.swing.JTextField();
        jTextField_guestCapacity = new javax.swing.JTextField();
        
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Display_Properties = new javax.swing.JTable();
        jButton_Update = new javax.swing.JButton();
        jButton_Delete = new javax.swing.JButton();
        jButton_EditFacilities = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 18)); 
        jLabel1.setText("Property ID:");

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 18)); 
        jLabel2.setText("House Name/Number:");

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 18)); 
        jLabel3.setText("Postcode:");

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 18)); 
        jLabel4.setText("Host ID");
        
        jLabel5.setFont(new java.awt.Font("Verdana", 0, 18)); 
        jLabel5.setText("description:");
        
        jLabel6.setFont(new java.awt.Font("Verdana", 0, 18)); 
        jLabel6.setText("shortName:");
        
        jLabel7.setFont(new java.awt.Font("Verdana", 0, 18)); 
        jLabel7.setText("guestCapacity:");
        
        
        //NAVBAR
        
 


	
        
 
        

        jTextField_property_id.setFont(new java.awt.Font("Verdana", 0, 14)); 

        jTextField_houseNameNumber.setFont(new java.awt.Font("Verdana", 0, 14)); 
        jTextField_houseNameNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_FirstNameActionPerformed(evt);
            }
        });

        jTextField_postcode.setFont(new java.awt.Font("Verdana", 0, 14)); 
        jTextField_postcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_LastNameActionPerformed(evt);
            }
        });

        jTextField_host_id.setFont(new java.awt.Font("Verdana", 0, 14)); 
        jTextField_host_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_AgeActionPerformed(evt);
            }
        });

        jTextField_description.setFont(new java.awt.Font("Verdana", 0, 14));
        jTextField_description.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_AgeActionPerformed(evt);
            }
        });
        
        jTextField_shortName.setFont(new java.awt.Font("Verdana", 0, 14)); 
        jTextField_shortName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_AgeActionPerformed(evt);
            }
        });
        
        jTextField_guestCapacity.setFont(new java.awt.Font("Verdana", 0, 14)); 
        jTextField_guestCapacity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_AgeActionPerformed(evt);
            }
        });
        
        
         
        jTable_Display_Properties.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "property_id", "houseNameNumber", "postcode", "host_id", "description", "shortName", "guestCapacity"
            }
        ));
        
        
        jTable_Display_Properties.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Display_UsersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Display_Properties);

        jButton_Update.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton_Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("assets/refresh.png"))); // NOI18N
        jButton_Update.setText("Edit");
        jButton_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_UpdateActionPerformed(evt);
            }
        });

        jButton_Delete.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("assets/delete.png"))); // NOI18N
        jButton_Delete.setText("Delete");
        jButton_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DeleteActionPerformed(evt);
            }
        });
        
        jButton_EditFacilities.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton_EditFacilities.setIcon(new javax.swing.ImageIcon(getClass().getResource("assets/refresh.png"))); // NOI18N
        jButton_EditFacilities.setText("Edit Property Facilities");
        jButton_EditFacilities.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EditFacilitiesActionPerformed(evt);
            }
        });
        
        backButton = new JButton("Back");
        backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));

		
		
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap(33, Short.MAX_VALUE)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(jLabel2)
        								.addComponent(jLabel4)
        								.addComponent(jLabel3)
        								.addComponent(jLabel1))
        							.addGap(10)
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(jTextField_property_id, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        								.addComponent(jTextField_houseNameNumber, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        								.addComponent(jTextField_postcode, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        								.addComponent(jTextField_host_id, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addGap(99)
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING, false)
            								.addComponent(jButton_Update, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            								.addComponent(jButton_EditFacilities, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        								.addComponent(jButton_Delete, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        					.addGap(24)
        					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(21)
        			.addComponent(backButton)
        			.addGap(18)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING, false)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap())
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jTextField_property_id, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jLabel1))
        					.addGap(11)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel2)
        						.addComponent(jTextField_houseNameNumber, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
        					.addGap(18)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jTextField_postcode, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jLabel3))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jTextField_host_id, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jLabel4))
        					.addGap(69)
        					.addComponent(jButton_Update, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
        					.addComponent(jButton_EditFacilities, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(jButton_Delete, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
        					.addGap(45))))
        );
        jPanel1.setLayout(jPanel1Layout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        

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
    private void jTable_Display_UsersMouseClicked(java.awt.event.MouseEvent evt) {                                                  
       // Get The Index Of The Slected Row 
        int i = jTable_Display_Properties.getSelectedRow();

        TableModel model = jTable_Display_Properties.getModel();
        
         // Display Slected Row In JTexteFields
        jTextField_property_id.setText(model.getValueAt(i,0).toString());

        jTextField_houseNameNumber.setText(model.getValueAt(i,1).toString());

        jTextField_postcode.setText(model.getValueAt(i,2).toString());

        jTextField_host_id.setText(model.getValueAt(i,3).toString());
        
        jTextField_description.setText(model.getValueAt(i,4).toString());
    }                                                 


   

 // Button Update
    private void jButton_UpdateActionPerformed(java.awt.event.ActionEvent evt) {                                               
       String query = "UPDATE `Property` SET `houseNameNumber`='"+jTextField_houseNameNumber.getText()+"',`postcode`='"+jTextField_postcode.getText()+"',`host_id`="+jTextField_host_id.getText()+" WHERE `property_id` = "+jTextField_property_id.getText();
       executeSQlQuery(query, "Updated");
    }                                              


 // Button Delete
    private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {                                               
        String query = "DELETE FROM `Property` WHERE property_id = "+jTextField_property_id.getText();
         executeSQlQuery(query, "Deleted");
    }  
    
    //Button Edit Facilities
    private void jButton_EditFacilitiesActionPerformed(java.awt.event.ActionEvent evt) {                                               
//        String query = "DELETE FROM `Property` WHERE property_id = "+jTextField_property_id.getText();
//         executeSQlQuery(query, "Deleted");
    	System.out.println("OK GOING TO EDIT FACILITIES PAGE");
    	mainModule.editPropertyState= EDITPROPERTY.FACILITIES;
		MainModule.controller.editPropertyView(Integer.parseInt(jTextField_property_id.getText()), 0); //fix the params
    }  

    /**
     * @param args the command line arguments
     */
    public void initializeProperties(int host_Id, int id) {
    	hostId = host_Id;
    	System.out.println("UGHHHHHHHHHHHHHHHHHHHHHHHH PROPERTY ID :"+hostId);
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
            java.util.logging.Logger.getLogger(Properties.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Properties.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Properties.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Properties.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
        
    		
            public void run() {
            
                new Properties(mainModule, controller, model).setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton_Delete;
    private javax.swing.JButton jButton_Update;
    private javax.swing.JButton jButton_EditFacilities;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Display_Properties;
    private javax.swing.JTextField jTextField_host_id;
    private javax.swing.JTextField jTextField_houseNameNumber;
    private javax.swing.JTextField jTextField_property_id;
    private javax.swing.JTextField jTextField_postcode;
    private javax.swing.JTextField jTextField_description;
    private javax.swing.JTextField jTextField_shortName;
    private javax.swing.JTextField jTextField_guestCapacity;
    private JButton backButton;
    private static int hostId;
}

//code partially from https://1bestcsharp.blogspot.com/2016/01/java-and-mysql-insert-update-delete-display.html
