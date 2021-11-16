
package HostGUI;

import java.awt.EventQueue;
import javax.swing.*;
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
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.*;
import javax.swing.event.*;

public class EditBathroom extends JFrame{


	private JFrame frame;
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
	 private JRadioButton toiletRadioBtn;
	 private JRadioButton showerRadioBtn;
	 private JRadioButton bathRadioBtn;
	 private JRadioButton sharedBathroomRadioBtn;
	 private JButton addBathType;
	 private JTable table = new JTable();

	 private JTextField bathroomId;
	 private JTextField toilet;
	 private JTextField bath;
	 private JTextField shower;
	 private JTextField shared;
	 
	 
	Connection connection = null;
	
	 public EditBathroom(MainModule mainModule, Controller controller, Model model) {
		//initializeEditBathroom();
		this.model=model;
		this.mainModule=mainModule;
		this.controller=controller;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditBathroom() {
		
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		JPanel editBathroomPanel = new JPanel();
		editBathroomPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(editBathroomPanel, BorderLayout.CENTER);
		editBathroomPanel.setLayout(null);

		JLabel editBathroomLabel = new JLabel("Add Bathroom facilities");
		editBathroomLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editBathroomLabel.setBounds(220, 58, 290, 57);
		editBathroomPanel.add(editBathroomLabel);
				  
		  Object [] colomns={"Bathroom Id", "Toilet", "Bath", "Shower", "Shared"};
		  DefaultTableModel model= new DefaultTableModel();
		  model.setColumnIdentifiers(colomns);
		  table.setModel(model);
		  
		  bathroomId = new JTextField();
		  bathroomId.setBounds(10, 11, 133, 20);
		  editBathroomPanel.add(bathroomId);
		  bathroomId.setColumns(10);
		  
		  toilet = new JTextField();
		  toilet.setBounds(10, 42, 133, 20);
		  editBathroomPanel.add(toilet);
		  toilet.setColumns(10);
		  
		  bath = new JTextField();
		  bath.setBounds(10, 73, 133, 20);
		  editBathroomPanel.add(bath);
		  bath.setColumns(10);
		  
		  shower = new JTextField();
		  shower.setBounds(10, 104, 133, 20);
		  editBathroomPanel.add(shower);
		  shower.setColumns(10);
		  
		  shared = new JTextField();
		  shared.setBounds(10, 134, 133, 20);
		  editBathroomPanel.add(shared);
		  shared.setColumns(10);
		  
		  JLabel lblName = new JLabel("Bathroom ID");
		  lblName.setBounds(151, 11, 95, 20);
		  editBathroomPanel.add(lblName);
		  
		  JLabel lblPassword = new JLabel("Toilet");
		  lblPassword.setBounds(151, 42, 95, 20);
		  editBathroomPanel.add(lblPassword);
		  
		  JLabel lblEmpId = new JLabel("Bath");
		  lblEmpId.setBounds(151, 73, 95, 20);
		  editBathroomPanel.add(lblEmpId);

		  JLabel lblEmpSalary = new JLabel("Shower");
		  lblEmpSalary.setBounds(151, 104, 95, 20);
		  editBathroomPanel.add(lblEmpSalary);
		  
		  JLabel lblShared = new JLabel("Shared");
		  lblShared.setBounds(151, 135, 95, 20);
		  editBathroomPanel.add(lblShared);
		  
		  JScrollPane scrollPane = new JScrollPane(table);
		  scrollPane.setBounds(10, 175, 464, 115);
		  editBathroomPanel.add(scrollPane);
		  
		  JButton addButton = new JButton("Add Bathroom");
	        JButton updateButton = new JButton("Update Bathroom");
	    
	        addButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	              //Add form data
	              model.addRow(
	                   new Object[]{
	                         bathroomId.getText(), 
	                         toilet.getText(),
	                         bath.getText(),
	                         shower.getText(),
	                         shared.getText()
	                   }
	              );
	              addBathTypeDetails();
	              //Delete form after adding data
	              bathroomId.setText("");
	              toilet.setText("");
	              bath.setText("");
	              shower.setText("");
	              shared.setText("");
	            }
	        });
		  addButton.setBounds(230, 89, 89, 23);
		  editBathroomPanel.add(addButton);

		  updateButton.setBounds(230, 120, 89, 23);
		  editBathroomPanel.add(updateButton);
		  
		  table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){ 
	          @Override
	          public void valueChanged(ListSelectionEvent e) {
	                int i = table.getSelectedRow();
	                bathroomId.setText((String)model.getValueAt(i, 0));
	                toilet.setText((String)model.getValueAt(i, 1));
	                bath.setText((String)model.getValueAt(i, 2));
	                shower.setText((String)model.getValueAt(i, 3));
	                shared.setText((String)model.getValueAt(i, 4));
	            }
	        });
		
		  updateButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	               //Update the form
	               int i = table.getSelectedRow();
	               model.setValueAt(bathroomId.getText(), i, 0);
	               model.setValueAt(toilet.getText(), i, 1);
	               model.setValueAt(bath.getText(), i, 2);
	               model.setValueAt(shower.getText(), i, 3);
	               model.setValueAt(shared.getText(), i, 4);
	            }
	        });
		
		
//		JLabel toiletLabel = new JLabel("Toilet");
//		toiletLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		toiletLabel.setBounds(170, 150, 167, 34);
//		editBathroomPanel.add(toiletLabel);
//
//		toiletRadioBtn = new JRadioButton("Toilet", false);
//		toiletRadioBtn.setBounds(387, 161, 21, 23);
//		editBathroomPanel.add(toiletRadioBtn);
//		
//		JLabel bathLabel = new JLabel("Bath");
//		bathLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		bathLabel.setBounds(170, 226, 167, 34);
//		editBathroomPanel.add(bathLabel);
//
//		bathRadioBtn = new JRadioButton("Bath", false);
//		bathRadioBtn.setBounds(387, 237, 21, 23);
//		editBathroomPanel.add(bathRadioBtn);
//		
//		JLabel showerLabel = new JLabel("Shower");
//		showerLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		showerLabel.setBounds(170, 315, 167, 34);
//		editBathroomPanel.add(showerLabel);
//
//		showerRadioBtn = new JRadioButton("Shower", false);
//		showerRadioBtn.setBounds(387, 315, 21, 23);
//		editBathroomPanel.add(showerRadioBtn);
//		
//		JLabel sharedHostLabel = new JLabel("Shared Bathroom with host");
//		sharedHostLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		sharedHostLabel.setBounds(170, 395, 196, 34);
//		editBathroomPanel.add(sharedHostLabel);		
//		
//		sharedBathroomRadioBtn = new JRadioButton("Shared bathroom", false);
//		sharedBathroomRadioBtn.setBounds(387, 403, 21, 23);
//		editBathroomPanel.add(sharedBathroomRadioBtn);


		addBathType = new JButton("Save");
		addBathType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				addBathTypeDetails();
				//should do nth cz add/update btns have the queries
				//should just go back to addfaciltiy page
			}
		});
		addBathType.setBounds(259, 482, 91, 23);
		editBathroomPanel.add(addBathType);
		
//		JButton backButton = new JButton("Back");
//		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		backButton.setBounds(24, 77, 91, 23);
//		editBathroomPanel.add(backButton);

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void addBathTypeDetails() {
		try {
			connection = ConnectionManager.getConnection();

			model.setBathroomId(Integer.parseInt(bathroomId.getText()));
			model.setToilet(Boolean.parseBoolean(toilet.getText()));
			model.setBath(Boolean.parseBoolean(bath.getText()));
			model.setShower(Boolean.parseBoolean(shower.getText()));
			model.setShared(Boolean.parseBoolean(shared.getText()));
			
			String insertBathTypeQuery = "insert into BathType (bathtype_id, toilet, bath, shower, shared)"
										+ " values(?,?,?,?,?)";
			PreparedStatement ps_bathType= connection.prepareStatement(insertBathTypeQuery);

			ps_bathType.setInt(1, model.getBathroomId());
			ps_bathType.setBoolean(2, model.getToilet());
			ps_bathType.setBoolean(3, model.getBath());
			ps_bathType.setBoolean(4, model.getShower());
			ps_bathType.setBoolean(5, model.getShared());

			System.out.println(ps_bathType);
			ps_bathType.executeUpdate();
			
			
		} catch(Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
//	
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW