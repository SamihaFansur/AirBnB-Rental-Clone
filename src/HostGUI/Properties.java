package HostGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Controller.Controller;
import GUI.ConnectionManager;
import GUI.MainModule;
import GUI.MainModule.EDITPROPERTY;
import GUI.MainModule.STATE;
import GUI.MainModule.USER;
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
import javax.swing.SwingConstants;

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
	 private JFrame frame;
	 private NavHost navForHost = new NavHost();
	 
	    // Variables declaration - do not modify                     
	    private javax.swing.JButton jButton_Delete;
	    private javax.swing.JButton jButton_Update;
	    private javax.swing.JButton jButton_EditFacilities;
	    private javax.swing.JButton jButton_EditChargeBands;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel_description;
	    private javax.swing.JPanel jPanel1;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JTable jTable_Display_Properties;
	    private javax.swing.JTextField jTextField_Description;
	    private javax.swing.JTextField jTextField_property_id;


	    private JButton backButton;
	    private static int hostId;
	    private JLabel jLabel_shortname;
	    private JTextField jTextField_shortName;
	    private JTextField jTextField_shortName_1;
	    private JTextField jTextField_guestCapacity;
	    private JTextField jTextField_guestCapacity_1;
	    private JLabel jLabel_guestCapacity;
	    private JPanel panel;
	    private JButton jButton_Reviews;
	
	 
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
        	   property = new PropertyObject(rs.getInt("property_id"),rs.getInt("address_id"),rs.getInt("host_id"),rs.getString("description"),rs.getString("shortName"),rs.getInt("guestCapacity"));
               propertiesList.add(property);
           }
           connection.close();
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
           row[1] = list.get(i).getDescription();
           row[2] = list.get(i).getShortName();
           row[3] = list.get(i).getGuestCapacity();
           
           model.addRow(row);
       }
    }
        
   // Execute The Insert Update And Delete Querys
   public void executeSQlQuery(String query, String message)
   {
       Connection connection = getConnection();
       Statement st;
       try{
           st = connection.createStatement();
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
           connection.close();
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
        jLabel_description = new javax.swing.JLabel();
       
        
        
        jTextField_property_id = new javax.swing.JTextField();
        jTextField_Description = new javax.swing.JTextField();
     
        jTextField_shortName = new javax.swing.JTextField();
        jTextField_guestCapacity = new javax.swing.JTextField();
        
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Display_Properties = new javax.swing.JTable();
        jButton_Update = new javax.swing.JButton();
        jButton_Delete = new javax.swing.JButton();
        jButton_EditFacilities = new javax.swing.JButton();
        jButton_EditChargeBands = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 18)); 
        jLabel1.setText("Property ID:");

        jLabel_description.setFont(new java.awt.Font("Verdana", 0, 18)); 
        jLabel_description.setText("Description");
        

        //NAVBAR

        jTextField_property_id.setFont(new java.awt.Font("Verdana", 0, 14));

        jTextField_Description.setFont(new java.awt.Font("Verdana", 0, 14)); 
        jTextField_Description.addActionListener(new java.awt.event.ActionListener() {
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
                "property_id", "description", "shortName", "guestCapacity"
            }
        ));
        
        
        jTable_Display_Properties.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Display_UsersMouseClicked(evt);
                setpropertyID();
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
        
        jButton_EditChargeBands.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton_EditChargeBands.setIcon(new javax.swing.ImageIcon(getClass().getResource("assets/refresh.png"))); // NOI18N
        jButton_EditChargeBands.setText("Edit Property Charge Bands");
        jButton_EditChargeBands.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jButton_UpdateChargeBandsActionPerformed(evt);
            }
        });
        
        backButton = new JButton("Back");
        backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
        backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Homepage sp = new Homepage();

				mainModule.currentState=STATE.HOST_ACCOUNT;
				mainModule.userState=USER.HOST;
				MainModule.controller.drawNewView();
//				close();
				model.setEditPropertyPostcode(null);
				setVisible(false);
				
				//jPanel1.setVisible(false);
				
			}
		});
        
        jLabel_shortname = new JLabel();
        jLabel_shortname.setText("Short Name:");
        jLabel_shortname.setFont(new Font("Verdana", Font.PLAIN, 18));
        
        jTextField_shortName_1 = new JTextField();
        jTextField_shortName_1.setFont(new Font("Verdana", Font.PLAIN, 14));
        
        jTextField_guestCapacity_1 = new JTextField();
        jTextField_guestCapacity_1.setFont(new Font("Verdana", Font.PLAIN, 14));
        
        jLabel_guestCapacity = new JLabel();
        jLabel_guestCapacity.setText("Guest Capacity:");
        jLabel_guestCapacity.setFont(new Font("Verdana", Font.PLAIN, 18));
        
        jButton_Reviews = new JButton();
        jButton_Reviews.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		mainModule.editPropertyState= EDITPROPERTY.REVIEWS;
        	    //needs to take in the properyId and hostId
        		model.setPropertyId(Integer.parseInt(jTextField_property_id.getText()));
        		MainModule.controller.editPropertyView(Integer.parseInt(jTextField_property_id.getText()), model.getHostId());
        	}
        });
        
        jButton_Reviews.setText("Reviews");
        jButton_Reviews.setFont(new Font("Verdana", Font.BOLD, 14));
		
        
        
		
		
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(33)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        						.addComponent(jButton_Reviews, GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE)
        						.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        								.addComponent(jButton_EditChargeBands, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
        								.addComponent(jButton_Delete, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
        								.addComponent(jButton_EditFacilities, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
        								.addComponent(jButton_Update, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE))
        							.addGroup(jPanel1Layout.createSequentialGroup()
        								.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        									.addGroup(jPanel1Layout.createSequentialGroup()
        										.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        											.addGroup(jPanel1Layout.createSequentialGroup()
        												.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        													.addComponent(jLabel_description)
        													.addComponent(jLabel_shortname, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
        												.addGap(97))
        											.addGroup(jPanel1Layout.createSequentialGroup()
        												.addComponent(jLabel_guestCapacity, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
        												.addGap(18)))
        										.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        											.addComponent(jTextField_Description, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        											.addComponent(jTextField_shortName_1, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        											.addComponent(jTextField_guestCapacity_1, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
        									.addGroup(jPanel1Layout.createSequentialGroup()
        										.addComponent(jLabel1)
        										.addGap(111)
        										.addComponent(jTextField_property_id, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
        								.addGap(24))))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(21)
        					.addComponent(backButton)
        					.addGap(18)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jTextField_property_id, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jLabel1))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jLabel_description)
        						.addComponent(jTextField_Description, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jTextField_shortName_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jLabel_shortname, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jTextField_guestCapacity_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jLabel_guestCapacity, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
        					.addComponent(jButton_Reviews, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jButton_Update, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jButton_EditFacilities, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jButton_EditChargeBands, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jButton_Delete, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap())
        );
        jPanel1.setLayout(jPanel1Layout);
        
        panel = new JPanel();
        panel.setBackground(new Color(51, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
        		.addComponent(panel, GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        			.addComponent(panel, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 541, GroupLayout.PREFERRED_SIZE))
        );
        	
        	
        	JButton navHomeButton = new JButton("Home");
        	navHomeButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
        	panel.add(navHomeButton);
        	navHomeButton.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			//Homepage sp = new Homepage();

        			mainModule.currentState=STATE.HOST_ACCOUNT;
        			mainModule.userState=USER.HOST;
        			MainModule.controller.drawNewView();
//				close();
        			setVisible(false);
        		}
        	});
        	
        	
        	JButton navLogoutButton = new JButton("Logout");
        	navLogoutButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
        	navLogoutButton.setForeground(new Color(0, 0, 0));
        	panel.add(navLogoutButton);
        	navLogoutButton.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			mainModule.currentState = STATE.HOMEPAGE;
        			mainModule.userState = USER.ENQUIRER;
        			frame.dispose();
        			MainModule.controller.drawNewView();
//				close();
        			setVisible(false);
        		}
        	});
        
        	
        	JButton navSearchButton = new JButton("Search");
        	navSearchButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
        	panel.add(navSearchButton);
        	navSearchButton.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			mainModule.currentState=STATE.SEARCH;
        			mainModule.userState=USER.HOST;
        			MainModule.controller.drawNewView();
//				close();
        			setVisible(false);
        		}
        	});
        getContentPane().setLayout(layout);
        
        

        pack();
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
        jTextField_Description.setText(model.getValueAt(i,1).toString());
        
        jTextField_shortName_1.setText(model.getValueAt(i,2).toString());
        
        jTextField_guestCapacity_1.setText(model.getValueAt(i,3).toString());
        
        
    }                                                 

    private void setpropertyID() {
        model.setPropertyId(Integer.parseInt(jTextField_property_id.getText()));

    }

 // Charge Bands Update
    private void jButton_UpdateChargeBandsActionPerformed(java.awt.event.ActionEvent evt) {
    	System.out.println("PROPERTY ID ENTERED IN TEXTBOX = "+jTextField_property_id.getText());
		mainModule.editPropertyState= EDITPROPERTY.CHARGEBANDS;
    //needs to take in the properyId and hostId
		model.setPropertyId(Integer.parseInt(jTextField_property_id.getText()));
		System.out.println("This is the host id in PROPERTIES.JAVA: "+model.getHostId());
		model.setPreviouslyInPropertiesList(true);
		MainModule.controller.editPropertyView(Integer.parseInt(jTextField_property_id.getText()), model.getHostId());
		setVisible(false);
    }  
    

 // Button Update
    private void jButton_UpdateActionPerformed(java.awt.event.ActionEvent evt) {                                               
    	 try {
           String updatePropertyQuery = "update Property set  description=?, "
    				+ "shortName=?, guestcapacity=? "
    				+ "where property_id=?";
    
           Connection connection = getConnection();
           PreparedStatement updatePropertyStatement= connection.prepareStatement(updatePropertyQuery);
    

           updatePropertyStatement.setString(1, jTextField_Description.getText());
           updatePropertyStatement.setString(2,jTextField_shortName_1.getText());
           updatePropertyStatement.setInt(3, Integer.parseInt(jTextField_guestCapacity_1.getText()));
           updatePropertyStatement.setInt(4, Integer.parseInt(jTextField_property_id.getText()));
    
           updatePropertyStatement.executeUpdate();
           
           connection.close();
        } catch(Exception e) {
    		System.err.println("Got an exception!");
    		System.err.println(e.getMessage());
        }              
    	 
    	 	mainModule.editPropertyState = EDITPROPERTY.PROPERTIES;
			MainModule.controller.editPropertyView(model.getFacilitiesId(), 0);
			setVisible(false);
    }


 // Button Delete
    private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {                                               
     	try {
			Connection connection = getConnection();
			
			Statement stmt = connection.createStatement();
			String getIds = "SELECT facilities_id,outdoors_id, utility_id, living_id, bathing_id, sleeping_id, kitchen_id FROM Facilities WHERE property_id = " + model.getPropertyId();
			System.out.println(getIds);
			ResultSet rs = stmt.executeQuery(getIds);
			int facilities = 0, outdoors = 0, utility = 0,living = 0, bathing = 0, sleeping = 0,kitchen = 0;
			while(rs.next()) {
				facilities = rs.getInt("facilities_id");
				outdoors = rs.getInt("outdoors_id");
				utility = rs.getInt("utility_id");
				living = rs.getInt("living_id");
				bathing = rs.getInt("bathing_id");
				sleeping = rs.getInt("sleeping_id");
				System.out.println(rs.getInt("sleeping_id"));
				System.out.println("s: " + sleeping);
				kitchen = rs.getInt("kitchen_id");
			}
			System.out.println("o: " + outdoors);
			System.out.println("u: " + utility);
			System.out.println("l: " + living);
			System.out.println("b: " + bathing);

			System.out.println("k: " + kitchen);

//			String deleteFacilitiesQuery ="DELETE FROM Facilities WHERE facilities_id = ?";
//			PreparedStatement deleteFacilities = connection.prepareStatement(deleteFacilitiesQuery);
//			deleteFacilities.setInt(1, facilities);
//			int y = deleteFacilities.executeUpdate();
//			if(y>0) {
//				System.out.println(this);
//				// remove later 
//			}
//			String deleteSleeping_BedTypeQuery = "DELETE FROM Sleeping_BedType WHERE sleeping_id = ?";
//			PreparedStatement deleteSleeping_BedType = connection.prepareStatement(deleteSleeping_BedTypeQuery);
//			deleteSleeping_BedType.setInt(1, sleeping);
//			int e = deleteSleeping_BedType.executeUpdate();
//			if(e>0) {
//				System.out.println(this);
//				// remove later 
//			}
//			String deleteSleepingQuery = "DELETE FROM Sleeping WHERE sleeping_id = ?";
//			PreparedStatement deleteSleeping = connection.prepareStatement(deleteSleepingQuery);
//			deleteSleeping.setInt(1, sleeping);
//			int d = deleteSleeping.executeUpdate();
//			if(d>0) {
//				System.out.println(this);
//				// remove later 
//			}
//			String deleteOutdoorsQuery = "DELETE FROM Outdoors WHERE outdoors_id = ?";
//			PreparedStatement deleteOutdoors = connection.prepareStatement(deleteOutdoorsQuery);
//			deleteOutdoors.setInt(1, outdoors);
//			int f = deleteOutdoors.executeUpdate();
//			if(f>0) {
//				System.out.println(this);
//				// remove later 
//			}
//			String deleteKitchenQuery = "DELETE FROM Kitchen WHERE kitchen_id = ?";
//			PreparedStatement deleteKitchen = connection.prepareStatement(deleteKitchenQuery);
//			deleteKitchen.setInt(1, kitchen);
//			int g = deleteKitchen.executeUpdate();
//			if(g>0) {
//				System.out.println(this);
//				// remove later 
//			}
//			String deleteLivingQuery = "DELETE FROM Living WHERE living_id = ?";
//			PreparedStatement deleteLiving = connection.prepareStatement(deleteLivingQuery);
//			deleteLiving.setInt(1, living);
//			int h = deleteLiving.executeUpdate();
//			if(h>0) {
//				System.out.println(this);
//				// remove later 
//			}
//			String deleteUtilityQuery = "DELETE FROM Utiltiy WHERE utility_id = ?";
//			PreparedStatement deleteUtility = connection.prepareStatement(deleteUtilityQuery);
//			deleteUtility.setInt(1, utility);
//			int i = deleteUtility.executeUpdate();
//			if(i>0) {
//				System.out.println(this);
//				// remove later 
//			}
//			String deleteBathing_BathTypeQuery = "DELETE FROM Bathing_BathType WHERE bathing_id = ?";
//			PreparedStatement deleteBathing_BathType= connection.prepareStatement(deleteBathing_BathTypeQuery);
//			deleteBathing_BathType.setInt(1, bathing);
//			int j = deleteBathing_BathType.executeUpdate();
//			if(j>0) {
//				System.out.println(this);
//				// remove later 
//			}
//			String deleteBathingQuery = "DELETE FROM Bathing WHERE bathing_id = ?";
//			PreparedStatement deleteBathing= connection.prepareStatement(deleteBathingQuery);
//			deleteBathing.setInt(1, bathing);
//			int k = deleteBathing.executeUpdate();
//			if(k>0) {
//				System.out.println(this);
//				// remove later 
//			}
//			String getaddressId = "SELECT address_id FROM Property WHERE property_id = " + model.getPropertyId();
//			System.out.println(getaddressId);
//			ResultSet rs_address = stmt.executeQuery(getaddressId);
//			int address_id = 0;
//			while(rs_address.next()) {
//			address_id = rs_address.getInt("address_id");
//			}
//			System.out.println(address_id);
//
//			String deletePropertyQuery = "DELETE FROM Property WHERE property_id = ?";
//			PreparedStatement deleteProperty= connection.prepareStatement(deletePropertyQuery);
//			deleteProperty.setInt(1, model.getPropertyId());
//			int l = deleteProperty.executeUpdate();
//			if(l>0) {
//				System.out.println(this);
//				// remove later 
//			}
//			String deleteAddressQuery = "DELETE FROM Address WHERE address_id = ?";
//			PreparedStatement deleteAddress= connection.prepareStatement(deleteAddressQuery);
//			deleteAddress.setInt(1, address_id);
//			int m = deleteAddress.executeUpdate();
//			if(m>0) {
//				System.out.println(this);
//				// remove later 
//			}

			connection.close();
			
			} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
    }   
    
    //Button Edit Facilities
    private void jButton_EditFacilitiesActionPerformed(java.awt.event.ActionEvent evt) {                                               
//        String query = "DELETE FROM `Property` WHERE property_id = "+jTextField_property_id.getText();
//         executeSQlQuery(query, "Deleted");
    	System.out.println("OK GOING TO EDIT FACILITIES PAGE");
    	mainModule.editPropertyState= EDITPROPERTY.FACILITIES;
		MainModule.controller.editPropertyView(Integer.parseInt(jTextField_property_id.getText()), 0); //fix the params
		setVisible(false);
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
}

//code partially from https://1bestcsharp.blogspot.com/2016/01/java-and-mysql-insert-update-delete-display.html
