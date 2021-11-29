
package HostGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import GUI.ConnectionManager;
import GUI.MainModule;
import GUI.MainModule.EDITPROPERTY;
import GUI.MainModule.USER;
import Model.Model;

public class EditBathroom extends JFrame {

	private JFrame frame;
	private NavHost navForHost = new NavHost();

	public void close() {
		frame.dispose();
	}

	/*
	 * Create the application.
	 */

	private Controller controller;
	private Model model;
	private MainModule mainModule;
	private JRadioButton toiletRadioBtn;
	private JRadioButton showerRadioBtn;
	private JRadioButton bathRadioBtn;
	private JRadioButton sharedBathroomRadioBtn;
	private JTable table = new JTable();
	private JRadioButton toiletRadioButton;
	private JRadioButton showerRadioButton;
	private JRadioButton bathRadioButton;
	private JRadioButton sharedRadioButton;
	private String toilet;
	private String shower;
	private String bath;
	private String shared;

	private JTextField bathroomId;

	Connection connection = null;

	private int idAfter;
	private int facilitiesidAfter;

	public EditBathroom(MainModule mainModule, Controller controller, Model model) {
		// initializeEditBathroom();
		this.model = model;
		this.mainModule = mainModule;
		this.controller = controller;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditBathroom(int facilitiesId, int id) {

		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		idAfter = id;
		facilitiesidAfter = facilitiesId;
		
		JPanel editBathroomPanel = new JPanel();
		editBathroomPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(editBathroomPanel, BorderLayout.CENTER);
		editBathroomPanel.setLayout(null);

		JLabel editBathroomLabel = new JLabel("Add Bathrooms");
		editBathroomLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editBathroomLabel.setBounds(213, 72, 290, 57);
		editBathroomPanel.add(editBathroomLabel);

		Object[] colomns = { "Bathroom Id", "Toilet", "Bath", "Shower", "Shared" };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(colomns);
		table.setModel(model);

		bathroomId = new JTextField();
		bathroomId.setBounds(134, 198, 133, 20);
		editBathroomPanel.add(bathroomId);
		bathroomId.setColumns(10);

		JLabel lblBathroomId = new JLabel("Bathroom ID");
		lblBathroomId.setBounds(349, 197, 95, 20);
		editBathroomPanel.add(lblBathroomId);

		JLabel lblToilet = new JLabel("Toilet");
		lblToilet.setBounds(349, 228, 95, 20);
		editBathroomPanel.add(lblToilet);

		JLabel lblBath = new JLabel("Bath");
		lblBath.setBounds(349, 259, 95, 20);
		editBathroomPanel.add(lblBath);

		JLabel lblShower = new JLabel("Shower");
		lblShower.setBounds(349, 290, 95, 20);
		editBathroomPanel.add(lblShower);

		JLabel lblShared = new JLabel("Shared");
		lblShared.setBounds(349, 321, 95, 20);
		editBathroomPanel.add(lblShared);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(68, 486, 464, 115);
		editBathroomPanel.add(scrollPane);

		JButton addButton = new JButton("Add Bathroom");
		JButton updateButton = new JButton("Update Bathroom");

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Add form data
				model.addRow(new Object[] { bathroomId.getText(), toilet, bath, shower, shared });
				addBathTypeDetails(idAfter);
				// Delete form after adding data
				bathroomId.setText("");
				toiletRadioButton.setText("");
				bathRadioButton.setText("");
				showerRadioButton.setText("");
				sharedRadioButton.setText("");
			}
		});
		addButton.setBounds(224, 393, 142, 23);
		editBathroomPanel.add(addButton);

		updateButton.setBounds(224, 427, 142, 23);
		editBathroomPanel.add(updateButton);

		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainModule.userState = USER.HOST;
				mainModule.editPropertyState = EDITPROPERTY.EDIT_BATHING;
				MainModule.controller.editPropertyView(facilitiesidAfter, idAfter); // fix params
//					close();
				frame.dispose();
			}
		});
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(20, 27, 91, 23);
		editBathroomPanel.add(backButton);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int i = table.getSelectedRow();
				bathroomId.setText((String) model.getValueAt(i, 0));
				toiletRadioButton.setText((String) model.getValueAt(i, 1));
				bathRadioButton.setText((String) model.getValueAt(i, 2));
				showerRadioButton.setText((String) model.getValueAt(i, 3));
				sharedRadioButton.setText((String) model.getValueAt(i, 4));
			}
		});

		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Update the form
				int i = table.getSelectedRow();
				model.setValueAt(bathroomId.getText(), i, 0);
				model.setValueAt(toiletRadioButton.getText(), i, 1);
				model.setValueAt(bathRadioButton.getText(), i, 2);
				model.setValueAt(showerRadioButton.getText(), i, 3);
				model.setValueAt(sharedRadioButton.getText(), i, 4);

				updateBathTypeDetails(idAfter);
			}
		});

		toiletRadioButton = new JRadioButton("Toilet");
		toiletRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (toiletRadioButton.isSelected()) {
					toilet = "Yes";
				} else {
					toilet = "No";
				}
			}
		});
		toiletRadioButton.setBounds(134, 227, 111, 23);
		editBathroomPanel.add(toiletRadioButton);

		bathRadioButton = new JRadioButton("Bath");
		bathRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (bathRadioButton.isSelected()) {
					bath = "Yes";
				} else {
					bath = "No";
				}
			}
		});
		bathRadioButton.setBounds(134, 258, 111, 23);
		editBathroomPanel.add(bathRadioButton);

		showerRadioButton = new JRadioButton("Shower");
		showerRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (showerRadioButton.isSelected()) {
					shower = "Yes";
				} else {
					shower = "No";
				}
			}
		});
		showerRadioButton.setBounds(134, 289, 111, 23);
		editBathroomPanel.add(showerRadioButton);

		sharedRadioButton = new JRadioButton("Shared with Host");
		sharedRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (sharedRadioButton.isSelected()) {
					shared = "Yes";
				} else {
					shared = "No";
				}
			}
		});

		sharedRadioButton.setBounds(134, 320, 111, 23);
		editBathroomPanel.add(sharedRadioButton);

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void addBathTypeDetails(int id) {
		System.out.println("id fed into addBathTypeDetails func = " + id);
		try {
			connection = ConnectionManager.getConnection();

			model.setBathroomId(Integer.parseInt(bathroomId.getText()));
			model.setToilet(toiletRadioButton.isSelected());
			model.setBath(bathRadioButton.isSelected());
			model.setShower(showerRadioButton.isSelected());
			model.setShared(sharedRadioButton.isSelected());

			String updateBathingBathTypeQuery = "insert into Bathing_BathType (bathing_id, bathType_id, toilet, bath, shower, shared)"
					+ " values(?,?,?,?,?,?)";
			PreparedStatement ps_bathingBathType = connection.prepareStatement(updateBathingBathTypeQuery);

			ps_bathingBathType.setInt(1, id);
			ps_bathingBathType.setInt(2, model.getBathroomId());
			ps_bathingBathType.setBoolean(3, model.getToilet());
			ps_bathingBathType.setBoolean(4, model.getBath());
			ps_bathingBathType.setBoolean(5, model.getShower());
			ps_bathingBathType.setBoolean(6, model.getShared());

			System.out.println(ps_bathingBathType);
			ps_bathingBathType.executeUpdate();

			String addNoOfBathroomsInBathing = "update Bathing set noOfBathrooms=? where bathing_id=?";
			String getNoOfBathroomsAddedInBathingBathType = "select * from Bathing_BathType where bathing_id = ?";

			PreparedStatement ps_gettingNoOfBathroomsAddedInBathingBathType = connection
					.prepareStatement(getNoOfBathroomsAddedInBathingBathType);

			ps_gettingNoOfBathroomsAddedInBathingBathType.setInt(1, id);

			int noOfBathroomsAdded = 0;
			ResultSet rs = ps_gettingNoOfBathroomsAddedInBathingBathType.executeQuery();
			while (rs.next()) {
				noOfBathroomsAdded = rs.getRow();
			}

			PreparedStatement ps_addingNoOfBathroomsInBathing = connection.prepareStatement(addNoOfBathroomsInBathing);

			ps_addingNoOfBathroomsInBathing.setInt(1, noOfBathroomsAdded); // add length of resultset as 2nd param
			ps_addingNoOfBathroomsInBathing.setInt(2, id);
			ps_addingNoOfBathroomsInBathing.executeUpdate();

			connection.close();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}

	public void updateBathTypeDetails(int id) {
		try {
			connection = ConnectionManager.getConnection();

			model.setBathroomId(Integer.parseInt(bathroomId.getText()));
			model.setToilet(Boolean.parseBoolean(toiletRadioButton.getText()));
			model.setBath(Boolean.parseBoolean(bathRadioButton.getText()));
			model.setShower(Boolean.parseBoolean(showerRadioButton.getText()));
			model.setShared(Boolean.parseBoolean(sharedRadioButton.getText()));

			String updateBathingBathTypeQuery = "update Bathing_BathType set toilet=?, bath=?, shower=?, shared=? where bathType_id=? and bathing_id=?";

			PreparedStatement ps_BathingBathType = connection.prepareStatement(updateBathingBathTypeQuery);

			ps_BathingBathType.setBoolean(1, model.getToilet());
			ps_BathingBathType.setBoolean(2, model.getBath());
			ps_BathingBathType.setBoolean(3, model.getShower());
			ps_BathingBathType.setBoolean(4, model.getShared());
			ps_BathingBathType.setInt(5, model.getBathroomId());
			ps_BathingBathType.setInt(6, id);
			ps_BathingBathType.executeUpdate();

			connection.close();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW