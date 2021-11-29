
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
import javax.swing.JComboBox;
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

public class EditBedroom extends JFrame {

	private JFrame frame;
	private NavHost navForHost = new NavHost();

	private JButton addBedType;
	private JTable table = new JTable();

	private JTextField bedroomId;
	private JTextField bed1People;
	private JTextField bed2People;
	private JComboBox BedType1ComboBox;
	private JComboBox BedType2ComboBox;
	private JRadioButton Bed1RadioButton;
	private JRadioButton Bed2RadioButton;
	private String bed1Value;
	private String bed2Value;

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
		// initializeEditBedroom();
		this.model = model;
		this.mainModule = mainModule;
		this.controller = controller;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeEditBedroom(int facilitiesId, int id) {
		try {
			frame = new JFrame();
			navForHost.addHostNav(frame, mainModule);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		idAfter = id;
		facilitiesidAfter = facilitiesId;

		JPanel editBedroomPanel = new JPanel();
		editBedroomPanel.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(editBedroomPanel, BorderLayout.CENTER);
		editBedroomPanel.setLayout(null);

		JLabel editBedroomLabel = new JLabel("Add beds in bedroom");
		editBedroomLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		editBedroomLabel.setBounds(182, 55, 275, 57);
		editBedroomPanel.add(editBedroomLabel);

		Object[] colomns = { "Bedroom Id", "Bed 1", "Bed 1 Type", "Bed 1 capacity", "Bed 2", "Bed 2 Type",
				"Bed 2 capacity" };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(colomns);
		table.setModel(model);

		bedroomId = new JTextField();
		bedroomId.setBounds(146, 228, 133, 20);
		editBedroomPanel.add(bedroomId);
		bedroomId.setColumns(10);

		bed1People = new JTextField();
		bed1People.setBounds(146, 321, 133, 20);
		editBedroomPanel.add(bed1People);
		bed1People.setColumns(10);

		bed2People = new JTextField();
		bed2People.setBounds(146, 414, 133, 20);
		editBedroomPanel.add(bed2People);
		bed2People.setColumns(10);

		JLabel lblBedroomId = new JLabel("Bedroom ID");
		lblBedroomId.setBounds(316, 228, 95, 20);
		editBedroomPanel.add(lblBedroomId);

		JLabel lblBed1Type = new JLabel("Bed 1 Type");
		lblBed1Type.setBounds(316, 290, 95, 20);
		editBedroomPanel.add(lblBed1Type);

		JLabel lblBed1People = new JLabel("Bed 1 Capacity");
		lblBed1People.setBounds(316, 321, 95, 20);
		editBedroomPanel.add(lblBed1People);

		JLabel lblBed2Type = new JLabel("Bed 2 Type");
		lblBed2Type.setBounds(316, 383, 95, 20);
		editBedroomPanel.add(lblBed2Type);

		JLabel lblBed2People = new JLabel("Bed 2 Capacity");
		lblBed2People.setBounds(318, 414, 95, 20);
		editBedroomPanel.add(lblBed2People);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(67, 470, 464, 115);
		editBedroomPanel.add(scrollPane);

		String bedTypes[] = { "King", "Bunk", "Single", "Double" };

		BedType1ComboBox = new JComboBox(bedTypes);
		BedType1ComboBox.setBounds(146, 289, 133, 22);
		editBedroomPanel.add(BedType1ComboBox);

		BedType2ComboBox = new JComboBox(bedTypes);
		BedType2ComboBox.setBounds(146, 381, 133, 22);
		editBedroomPanel.add(BedType2ComboBox);

		Bed1RadioButton = new JRadioButton("Bed 1");
		Bed1RadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Bed1RadioButton.isSelected()) {
					bed1Value = "Yes";
				} else {
					bed1Value = "No";
				}
			}
		});
		Bed1RadioButton.setBounds(146, 255, 111, 23);
		editBedroomPanel.add(Bed1RadioButton);

		Bed2RadioButton = new JRadioButton("Bed 2");
		Bed2RadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Bed2RadioButton.isSelected()) {
					bed2Value = "Yes";
				} else {
					bed2Value = "No";
				}
			}
		});
		Bed2RadioButton.setBounds(146, 348, 111, 23);
		editBedroomPanel.add(Bed2RadioButton);

		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainModule.userState = USER.HOST;
				mainModule.editPropertyState = EDITPROPERTY.EDIT_SLEEPING;
				MainModule.controller.editPropertyView(facilitiesidAfter, idAfter); // fix params
				frame.dispose();
			}
		});
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(20, 27, 91, 23);
		editBedroomPanel.add(backButton);

		JButton addButton = new JButton("Add Bed Details");
		JButton updateButton = new JButton("Update Bed Details");

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Add form data
				model.addRow(new Object[] { bedroomId.getText(), bed1Value, BedType1ComboBox.getSelectedItem(),
						bed1People.getText(), bed2Value, BedType2ComboBox.getSelectedItem(), bed2People.getText() });
				addBedTypeDetails(idAfter);
				// Delete form after adding data
				bedroomId.setText("");
				Bed1RadioButton.setSelected(false);
				BedType1ComboBox.setSelectedItem("King");
				bed1People.setText("");
				BedType1ComboBox.setSelectedItem("King");
				Bed2RadioButton.setSelected(false);
				bed2People.setText("");
			}
		});
		addButton.setBounds(220, 150, 140, 23);
		editBedroomPanel.add(addButton);

		updateButton.setBounds(220, 184, 140, 23);
		editBedroomPanel.add(updateButton);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int i = table.getSelectedRow();
				bedroomId.setText((String) model.getValueAt(i, 0));
				Bed1RadioButton.setSelected((boolean) model.getValueAt(i, 1));
				BedType1ComboBox.setSelectedItem(model.getValueAt(i, 2));
				bed1People.setText((String) model.getValueAt(i, 3));
				BedType2ComboBox.setSelectedItem(model.getValueAt(i, 4));
				Bed2RadioButton.setSelected((boolean) model.getValueAt(i, 5));
				bed2People.setText((String) model.getValueAt(i, 6));
			}
		});

		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Update the form
				int i = table.getSelectedRow();
				model.setValueAt(bedroomId.getText(), i, 0);
				model.setValueAt(Bed1RadioButton.getText(), i, 1);
				model.setValueAt(BedType1ComboBox.getSelectedItem(), i, 2);
				model.setValueAt(bed1People.getText(), i, 3);
				model.setValueAt(Bed2RadioButton.getText(), i, 4);
				model.setValueAt(BedType2ComboBox.getSelectedItem(), i, 5);
				model.setValueAt(bed2People.getText(), i, 6);
				updateBedTypeDetails(idAfter);
			}
		});

		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void addBedTypeDetails(int id) {
		try {
			connection = ConnectionManager.getConnection();

			model.setBedroomId(Integer.parseInt(bedroomId.getText()));
			model.setBed1(Bed1RadioButton.isSelected());
			model.setBed1Type((String) BedType1ComboBox.getSelectedItem());
			model.setBed1Capacity(Integer.parseInt(bed1People.getText()));
			model.setBed2(Bed2RadioButton.isSelected());
			model.setBed2Type((String) BedType2ComboBox.getSelectedItem());
			model.setBed2Capacity(Integer.parseInt(bed2People.getText()));

			String updateSleepingBedTypeQuery = "insert into Sleeping_BedType (sleeping_id, bedType_id, bed1, "
					+ "bed1ChoiceField, bed1People, bed2, bed2ChoiceField, bed2People)" + " values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps_sleepingBedType = connection.prepareStatement(updateSleepingBedTypeQuery);

			ps_sleepingBedType.setInt(1, id);
			ps_sleepingBedType.setInt(2, model.getBedroomId());
			ps_sleepingBedType.setBoolean(3, model.getBed1());
			ps_sleepingBedType.setString(4, model.getBed1Type());
			ps_sleepingBedType.setInt(5, model.getBed1Capacity());
			ps_sleepingBedType.setBoolean(6, model.getBed2());
			ps_sleepingBedType.setString(7, model.getBed2Type());
			ps_sleepingBedType.setInt(8, model.getBed2Capacity());
			ps_sleepingBedType.executeUpdate();

			String addNoOfBedroomsInSleeping = "update Sleeping set noOfBedrooms=? where sleeping_id=?";
			String getNoOfBedroomsAddedInSleepingBedType = "select * from Sleeping_BedType where sleeping_id = ?";

			PreparedStatement ps_gettingNoOfbedroomsAddedInSleepingBedType = connection
					.prepareStatement(getNoOfBedroomsAddedInSleepingBedType);
			ps_gettingNoOfbedroomsAddedInSleepingBedType.setInt(1, id);

			int noOfBedroomsAdded = 0;
			ResultSet rs = ps_gettingNoOfbedroomsAddedInSleepingBedType.executeQuery();
			while (rs.next()) {
				noOfBedroomsAdded = rs.getRow();
				System.out.println("LENGTH OF RESULT SET IS = " + rs.getRow());
				System.out.println("no of bedrooms added var = " + noOfBedroomsAdded);
			}

			PreparedStatement ps_addingNoOfBedroomsInSleeping = connection.prepareStatement(addNoOfBedroomsInSleeping);

			ps_addingNoOfBedroomsInSleeping.setInt(1, noOfBedroomsAdded);
			ps_addingNoOfBedroomsInSleeping.setInt(2, id);
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

			String addNoOfPeopleInSleepingBedType = "update Sleeping_BedType set peopleInBedroom=? where sleeping_id=? and bedType_id=?";

			PreparedStatement ps_addingNoOfPeopleInSleepingBedType = connection
					.prepareStatement(addNoOfPeopleInSleepingBedType);

			ps_addingNoOfPeopleInSleepingBedType.setInt(1, peopleInBedroom);
			ps_addingNoOfPeopleInSleepingBedType.setInt(2, id);
			ps_addingNoOfPeopleInSleepingBedType.setInt(3, model.getBedroomId());
			ps_addingNoOfPeopleInSleepingBedType.executeUpdate();

			connection.close();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}

	public void updateBedTypeDetails(int id) {
		System.out.println("id fed into updateBathTypeDetails func = " + id);
		try {
			connection = ConnectionManager.getConnection();

			model.setBedroomId(Integer.parseInt(bedroomId.getText()));
			model.setBed1(Boolean.parseBoolean(Bed1RadioButton.getText()));
			model.setBed1Type((String) BedType1ComboBox.getSelectedItem());
			model.setBed1Capacity(Integer.parseInt(bed1People.getText()));
			model.setBed2(Boolean.parseBoolean(Bed2RadioButton.getText()));
			model.setBed2Type((String) BedType2ComboBox.getSelectedItem());
			model.setBed2Capacity(Integer.parseInt(bed2People.getText()));

			String updateSleepingBedTypeQuery = "update Sleeping_BedType set bed1=?, bed1ChoiceField=?, bed1People=?, "
					+ "bed2=?, bed2ChoiceField=?, bed2People=? " + "where bedType_id=? and sleeping_id=?";

			PreparedStatement ps_SleepingBedType = connection.prepareStatement(updateSleepingBedTypeQuery);

			ps_SleepingBedType.setBoolean(1, model.getBed1());
			ps_SleepingBedType.setString(2, model.getBed1Type());
			ps_SleepingBedType.setInt(3, model.getBed1Capacity());
			ps_SleepingBedType.setBoolean(4, model.getBed2());
			ps_SleepingBedType.setString(5, model.getBed2Type());
			ps_SleepingBedType.setInt(6, model.getBed2Capacity());
			ps_SleepingBedType.setInt(7, model.getBedroomId());
			ps_SleepingBedType.setInt(8, id);
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

			String addNoOfPeopleInSleepingBedType = "update Sleeping_BedType set peopleInBedroom=? where sleeping_id=? and bedType_id=?";

			PreparedStatement ps_addingNoOfPeopleInSleepingBedType = connection
					.prepareStatement(addNoOfPeopleInSleepingBedType);

			ps_addingNoOfPeopleInSleepingBedType.setInt(1, peopleInBedroom);
			ps_addingNoOfPeopleInSleepingBedType.setInt(2, id);
			ps_addingNoOfPeopleInSleepingBedType.setInt(3, model.getBedroomId());
			ps_addingNoOfPeopleInSleepingBedType.executeUpdate();

			connection.close();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
}

//NEED TO ALIGN CONTENT IN THE CENTER & RESIZE WINDOW