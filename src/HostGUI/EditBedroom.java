
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class EditBedroom extends JFrame{


	private JFrame frame;
	private NavHost navForHost = new NavHost();

	 private JButton addBedType;
	 private JTable table = new JTable();

	 private JTextField bedroomId;
	 private JTextField bed1;
	 private JTextField bed2;
	 private JTextField bed1ChoiceField;
	 private JTextField bed2ChoiceField;
	 private JTextField bed1People;
	 private JTextField bed2People;
	 
	 
	Connection connection = null;

	private int idAfter;
	private int facilitiesidAfter;
	
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
	public void initializeEditBedroom(int facilitiesId, int id) {
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}

		idAfter = id;
		facilitiesidAfter = facilitiesId;
		System.out.println("FACILITY ID FOR WHICH AM CREATING BATHING RN = "+facilitiesidAfter);
		System.out.println("id after in init edit BATHING func = "+idAfter);
		
		JPanel editBedroomPanel = new JPanel();
		editBedroomPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(editBedroomPanel, BorderLayout.CENTER);
		editBedroomPanel.setLayout(null);

		JLabel editBedroomLabel = new JLabel("Add beds in bedroom");
		editBedroomLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editBedroomLabel.setBounds(248, 47, 183, 57);
		editBedroomPanel.add(editBedroomLabel);
		
		
		Object [] colomns={"Bedroom Id", "Bed 1", "Bed 1 Type", "Bed 1 capacity", "Bed 2", "Bed 2 Type", "Bed 2 capacity"};
		  DefaultTableModel model= new DefaultTableModel();
		  model.setColumnIdentifiers(colomns);
		  table.setModel(model);
		  
		  bedroomId = new JTextField();
		  bedroomId.setBounds(10, 11, 133, 20);
		  editBedroomPanel.add(bedroomId);
		  bedroomId.setColumns(10);
		  
		  bed1 = new JTextField();
		  bed1.setBounds(10, 42, 133, 20);
		  editBedroomPanel.add(bed1);
		  bed1.setColumns(10);

		  bed1ChoiceField = new JTextField();
		  bed1ChoiceField.setBounds(10, 73, 133, 20);
		  editBedroomPanel.add(bed1ChoiceField);
		  bed1ChoiceField.setColumns(10);
		  
		  bed1People= new JTextField();
		  bed1People.setBounds(10, 104, 133, 20);
		  editBedroomPanel.add(bed1People);
		  bed1People.setColumns(10);

		  bed2 = new JTextField();
		  bed2.setBounds(10, 135, 133, 20);
		  editBedroomPanel.add(bed2);
		  bed2.setColumns(10);

		  bed2ChoiceField = new JTextField();
		  bed2ChoiceField.setBounds(10, 166, 133, 20);
		  editBedroomPanel.add(bed2ChoiceField);
		  bed2ChoiceField.setColumns(10);
		  
		  bed2People = new JTextField();
		  bed2People.setBounds(10, 197, 133, 20);
		  editBedroomPanel.add(bed2People);
		  bed2People.setColumns(10);
		  
		  JLabel lblBedroomId = new JLabel("Bedroom ID");
		  lblBedroomId.setBounds(151, 11, 95, 20);
		  editBedroomPanel.add(lblBedroomId);
		  
		  JLabel lblBed1 = new JLabel("Bed 1");
		  lblBed1.setBounds(151, 42, 95, 20);
		  editBedroomPanel.add(lblBed1);
		  
		  JLabel lblBed1Type = new JLabel("Bed 1 Type");
		  lblBed1Type.setBounds(151, 73, 95, 20);
		  editBedroomPanel.add(lblBed1Type);

		  JLabel lblBed1People= new JLabel("Bed 1 Capacity");
		  lblBed1People.setBounds(151, 104, 95, 20);
		  editBedroomPanel.add(lblBed1People);
		  	  
		  JLabel lblBed2 = new JLabel("Bed 2");
		  lblBed2.setBounds(151, 135, 95, 20);
		  editBedroomPanel.add(lblBed2);
		  
		  JLabel lblBed2Type = new JLabel("Bed 2 Type");
		  lblBed2Type.setBounds(151, 166, 95, 20);
		  editBedroomPanel.add(lblBed2Type);

		  JLabel lblBed2People= new JLabel("Bed 2 Capacity");
		  lblBed2People.setBounds(151, 197, 95, 20);
		  editBedroomPanel.add(lblBed2People);
		  
		  
		  JScrollPane scrollPane = new JScrollPane(table);
		  scrollPane.setBounds(10, 220, 464, 115);
		  editBedroomPanel.add(scrollPane);
		  
		  JButton addButton = new JButton("Add Bed Details");
	        JButton updateButton = new JButton("Update Bed Details");
	    
	        addButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	              //Add form data
	              model.addRow(
	                   new Object[]{
	                		 bedroomId.getText(), 
	                		 bed1.getText(),
	                		 bed1ChoiceField.getText(),
	                		 bed1People.getText(),
	                		 bed2.getText(),
	                		 bed2ChoiceField.getText(),
	                		 bed2People.getText()
	                   }
	              );
	              System.out.println("idAfter in addbtn before calling addBathTypeDetails = "+idAfter);
	              addBedTypeDetails(idAfter);
	              //Delete form after adding data
	              bedroomId.setText("");
	              bed1.setText("");
	              bed1ChoiceField.setText("");
	              bed1People.setText("");
	              bed2.setText("");
	              bed2ChoiceField.setText("");
	              bed2People.setText("");
	            }
	        });
		  addButton.setBounds(230, 89, 140, 23);
		  editBedroomPanel.add(addButton);

		  updateButton.setBounds(230, 120, 140, 23);
		  editBedroomPanel.add(updateButton);
		  
		  table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){ 
	          @Override
	          public void valueChanged(ListSelectionEvent e) {
	                int i = table.getSelectedRow();
	                bedroomId.setText((String)model.getValueAt(i, 0));
	                bed1.setText((String)model.getValueAt(i, 1));
	                bed1ChoiceField.setText((String)model.getValueAt(i, 2));
	                bed1People.setText((String)model.getValueAt(i, 3));
	                bed2.setText((String)model.getValueAt(i, 4));
	                bed2ChoiceField.setText((String)model.getValueAt(i, 5));
	                bed2People.setText((String)model.getValueAt(i, 6));
	            }
	        });
		
		  updateButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	               //Update the form
	               int i = table.getSelectedRow();
	               model.setValueAt(bedroomId.getText(), i, 0);
	               model.setValueAt(bed1.getText(), i, 1);
	               model.setValueAt(bed1ChoiceField.getText(), i, 2);
	               model.setValueAt(bed1People.getText(), i, 3);
	               model.setValueAt(bed2.getText(), i, 4);
	               model.setValueAt(bed2ChoiceField.getText(), i, 5);
	               model.setValueAt(bed2People.getText(), i, 6);
	               
	               updateBedTypeDetails(idAfter);
	            }
	        });
		
		
//		JLabel bed1Label = new JLabel("Bed 1:");
//		bed1Label.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		bed1Label.setBounds(170, 135, 167, 34);
//		editBedroomPanel.add(bed1Label);
//		
//		JLabel televisionLabel = new JLabel("Bed Type:");
//		televisionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		televisionLabel.setBounds(199, 188, 167, 34);
//		editBedroomPanel.add(televisionLabel);
//		
//		JLabel bed1NoOfPeopleLabel = new JLabel("No Of People");
//		bed1NoOfPeopleLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		bed1NoOfPeopleLabel.setBounds(199, 251, 167, 34);
//		editBedroomPanel.add(bed1NoOfPeopleLabel);
//		
//		JLabel bed2Label = new JLabel("Bed 2:");
//		bed2Label.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		bed2Label.setBounds(170, 310, 167, 34);
//		editBedroomPanel.add(bed2Label);
//		
//		JLabel bed2TypeLabel = new JLabel("Bed Type");
//		bed2TypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		bed2TypeLabel.setBounds(199, 369, 167, 34);
//		editBedroomPanel.add(bed2TypeLabel);
//		
//		JRadioButton bed1RadioBtn = new JRadioButton("");
//		bed1RadioBtn.setBounds(364, 146, 21, 23);
//		editBedroomPanel.add(bed1RadioBtn);
//		
//		JRadioButton bed2RadioBtn = new JRadioButton("");
//		bed2RadioBtn.setBounds(364, 310, 21, 23);
//		editBedroomPanel.add(bed2RadioBtn);
//		
//		JLabel bed2NoOfPeopleLabel = new JLabel("No Of People");
//		bed2NoOfPeopleLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		bed2NoOfPeopleLabel.setBounds(199, 435, 167, 34);
//		editBedroomPanel.add(bed2NoOfPeopleLabel);
//		
//		JComboBox Bed1TypeComboBox = new JComboBox();
//		Bed1TypeComboBox.setBounds(364, 196, 67, 22);
//		editBedroomPanel.add(Bed1TypeComboBox);
//		
//		JComboBox Bed2TypeComboBox_1 = new JComboBox();
//		Bed2TypeComboBox_1.setBounds(364, 377, 67, 22);
//		editBedroomPanel.add(Bed2TypeComboBox_1);
//		
//		Bed1NoOfPeopleTextField = new JTextField();
//		Bed1NoOfPeopleTextField.setBounds(364, 260, 67, 20);
//		editBedroomPanel.add(Bed1NoOfPeopleTextField);
//		Bed1NoOfPeopleTextField.setColumns(10);
//		
//		Bed2NoOfPeopleTextField = new JTextField();
//		Bed2NoOfPeopleTextField.setColumns(10);
//		Bed2NoOfPeopleTextField.setBounds(364, 444, 67, 20);
//		editBedroomPanel.add(Bed2NoOfPeopleTextField);
//		
//		JButton backButton = new JButton("Back");
//		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		backButton.setBounds(22, 69, 91, 23);
//		editBedroomPanel.add(backButton);
		
		
		JButton addBedrooms= new JButton("Save");
		addBedrooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//should do nth cz add/update btns have the queries
				//should just go back to addfaciltiy page
			}
		});
		addBedrooms.setBounds(264, 518, 91, 23);
		editBedroomPanel.add(addBedrooms);


		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void addBedTypeDetails(int id) {
        System.out.println("id fed into addBedTypeDetails func = "+id);
		try {
			connection = ConnectionManager.getConnection();

			model.setBedroomId(Integer.parseInt(bedroomId.getText()));
			model.setBed1(Boolean.parseBoolean(bed1.getText()));
			model.setBed1Type(bed1ChoiceField.getText());
			model.setBed1Capacity(Integer.parseInt(bed1People.getText()));
			model.setBed2(Boolean.parseBoolean(bed2.getText()));
			model.setBed2Type(bed2ChoiceField.getText());
			model.setBed2Capacity(Integer.parseInt(bed2People.getText()));
			
			String updateSleepingBedTypeQuery = "insert into Sleeping_BedType (sleeping_id, bedType_id, bed1, "
												+ "bed1ChoiceField, bed1People, bed2, bed2ChoiceField, bed2People)"
												+ " values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps_sleepingBedType= connection.prepareStatement(updateSleepingBedTypeQuery);

			ps_sleepingBedType.setInt(1, id);
			ps_sleepingBedType.setInt(2, model.getBedroomId());
			ps_sleepingBedType.setBoolean(3, model.getBed1());
			ps_sleepingBedType.setString(4, model.getBed1Type());
			ps_sleepingBedType.setInt(5, model.getBed1Capacity());
			ps_sleepingBedType.setBoolean(6, model.getBed2());
			ps_sleepingBedType.setString(7, model.getBed2Type());
			ps_sleepingBedType.setInt(8, model.getBed2Capacity());
			
			System.out.println(ps_sleepingBedType);
			ps_sleepingBedType.executeUpdate();
			

			String addNoOfBedroomsInSleeping = "update Sleeping set noOfBedrooms=? where sleeping_id=?";
			String getNoOfBedroomsAddedInSleepingBedType = "select * from Sleeping_BedType where sleeping_id = ?";
			
			PreparedStatement ps_gettingNoOfbedroomsAddedInSleepingBedType = connection.prepareStatement(getNoOfBedroomsAddedInSleepingBedType);
			ps_gettingNoOfbedroomsAddedInSleepingBedType.setInt(1, id);
			
			int noOfBedroomsAdded = 0;
			ResultSet rs = ps_gettingNoOfbedroomsAddedInSleepingBedType.executeQuery();
			while (rs.next()) {
				noOfBedroomsAdded = rs.getRow();
				System.out.println("LENGTH OF RESULT SET IS = "+rs.getRow());
				System.out.println("no of bedrooms added var = "+noOfBedroomsAdded);
            }	

			System.out.println("no of bedrooms added var outside = "+noOfBedroomsAdded);
			
			PreparedStatement ps_addingNoOfBedroomsInSleeping = connection.prepareStatement(addNoOfBedroomsInSleeping);
			
			ps_addingNoOfBedroomsInSleeping.setInt(1, noOfBedroomsAdded);
			ps_addingNoOfBedroomsInSleeping.setInt(2, id);

			System.out.println(ps_addingNoOfBedroomsInSleeping);
			ps_addingNoOfBedroomsInSleeping.executeUpdate();
			
			String addNoOfPeopleInBedroom = "select bed1People, bed2People from Sleeping_BedType where sleeping_id=?";
			PreparedStatement getNoOfPeopleInBedroom = connection.prepareStatement(addNoOfPeopleInBedroom);
			getNoOfPeopleInBedroom.setInt(1, id);
			
			int bed1People = 0;
			int bed2People = 0;
			int peopleInBedroom = 0;
			ResultSet totalPeople = getNoOfPeopleInBedroom.executeQuery();
			while (totalPeople.next()) {
				bed1People = totalPeople.getInt(1);
				bed2People = totalPeople.getInt(2);
            }
			peopleInBedroom = bed1People + bed2People;
			System.out.println("no of people totally added var outside = "+peopleInBedroom);
			
			String addNoOfPeopleInSleepingBedType = "update Sleeping_BedType set peopleInBedroom=? where sleeping_id=? and bedType_id=?";
			
			PreparedStatement ps_addingNoOfPeopleInSleepingBedType = connection.prepareStatement(addNoOfPeopleInSleepingBedType);
			
			ps_addingNoOfPeopleInSleepingBedType.setInt(1,peopleInBedroom);
			ps_addingNoOfPeopleInSleepingBedType.setInt(2, id);
			ps_addingNoOfPeopleInSleepingBedType.setInt(3, model.getBedroomId());

			System.out.println(ps_addingNoOfPeopleInSleepingBedType);
			ps_addingNoOfPeopleInSleepingBedType.executeUpdate();
			
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
	
	public void updateBedTypeDetails(int id) {
        System.out.println("id fed into updateBathTypeDetails func = "+id);
		try {
			connection = ConnectionManager.getConnection();

			model.setBedroomId(Integer.parseInt(bedroomId.getText()));
			model.setBed1(Boolean.parseBoolean(bed1.getText()));
			model.setBed1Type(bed1ChoiceField.getText());
			model.setBed1Capacity(Integer.parseInt(bed1People.getText()));
			model.setBed2(Boolean.parseBoolean(bed2.getText()));
			model.setBed2Type(bed2ChoiceField.getText());
			model.setBed2Capacity(Integer.parseInt(bed2People.getText()));

			String updateSleepingBedTypeQuery = "update Sleeping_BedType set bed1=?, bed1ChoiceField=?, bed1People=?, "
												+ "bed2=?, bed2ChoiceField=?, bed2People=? "
												+ "where bedType_id=? and sleeping_id=?";
			
			PreparedStatement ps_SleepingBedType= connection.prepareStatement(updateSleepingBedTypeQuery);

			ps_SleepingBedType.setBoolean(1, model.getBed1());
			ps_SleepingBedType.setString(2, model.getBed1Type());
			ps_SleepingBedType.setInt(3, model.getBed1Capacity());
			ps_SleepingBedType.setBoolean(4, model.getBed2());
			ps_SleepingBedType.setString(5, model.getBed2Type());
			ps_SleepingBedType.setInt(6, model.getBed2Capacity());
			ps_SleepingBedType.setInt(7, model.getBedroomId());
			ps_SleepingBedType.setInt(8, id);
			
			System.out.println(ps_SleepingBedType);
			ps_SleepingBedType.executeUpdate();
			
			String addNoOfPeopleInBedroom = "select bed1People, bed2People from Sleeping_BedType where sleeping_id=?";
			PreparedStatement getNoOfPeopleInBedroom = connection.prepareStatement(addNoOfPeopleInBedroom);
			getNoOfPeopleInBedroom.setInt(1, id);
			
			int bed1People = 0;
			int bed2People = 0;
			int peopleInBedroom = 0;
			ResultSet totalPeople = getNoOfPeopleInBedroom.executeQuery();
			while (totalPeople.next()) {
				bed1People = totalPeople.getInt(1);
				bed2People = totalPeople.getInt(2);
            }
			peopleInBedroom = bed1People + bed2People;
			System.out.println("no of people totally added var outside = "+peopleInBedroom);
			
			String addNoOfPeopleInSleepingBedType = "update Sleeping_BedType set peopleInBedroom=? where sleeping_id=? and bedType_id=?";
			
			PreparedStatement ps_addingNoOfPeopleInSleepingBedType = connection.prepareStatement(addNoOfPeopleInSleepingBedType);
			
			ps_addingNoOfPeopleInSleepingBedType.setInt(1,peopleInBedroom);
			ps_addingNoOfPeopleInSleepingBedType.setInt(2, id);
			ps_addingNoOfPeopleInSleepingBedType.setInt(3, model.getBedroomId());

			System.out.println(ps_addingNoOfPeopleInSleepingBedType);
			ps_addingNoOfPeopleInSleepingBedType.executeUpdate();
			
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}

}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW