package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Controller.Controller;
import GUI.MainModule.EDITPROPERTY;
import GUI.MainModule.STATE;
import GUI.MainModule.USER;
import GUI.SearchObject;
import Model.Model;

public class Search extends javax.swing.JFrame {

	private Controller controller;
	private Model model;
	private MainModule mainModule;
	Connection connection = null;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable_Display_Search;
	private JButton searchBtn;
	private JButton viewPropertyButton;
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
	private Date startd;
	private Date endd;

	public Search(MainModule mainModule, Controller controller, Model model) {
		this.model = model;
		this.mainModule = mainModule;
		this.controller = controller;

		initComponents();
		//setting values entered as search criteria
		model.setSD(startDateTextField.getText());
		model.setED(endDateTextField.getText());
		model.setMaxPPN(Double.parseDouble(minPriceTextField.getText()));
		model.setMinPPN(Double.parseDouble(maxPriceTextField.getText()));
		model.setGuestCap(Integer.parseInt(guestCapacityTextField.getText()));
		model.setPlaceName(locationComboBox.getSelectedItem().toString()); //was ""

		Show_Search_In_JTable();
	}

	// get the connection

	private static String serverName = "jdbc:mysql://stusql.dcs.shef.ac.uk/team018";
	private static String username = "team018";
	private static String pwd = "7854a03f";
	DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
	private JLabel lblNewLabel;

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

	//Converts String to a Date object
	public static Date parseDate(String date) {
		try {
			return new SimpleDateFormat("dd/mm/yyyy").parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	//Checks if the dates entered are valid for the dates in 2022
	public Boolean validateDate(int day, int month, int year) {

		boolean dateAccepted = false;
		if (year == 2022 && month >= 1 && month <= 12 && day >= 1) {

			switch (month) {
			// jan
			case 1:
				if (day <= 31)
					dateAccepted = true;
				break;
			// feb
			case 2:
				if (day <= 28)
					dateAccepted = true;
				break;
			// march
			case 3:
				if (day <= 31)
					dateAccepted = true;
				break;
			// april
			case 4:
				if (day <= 30)
					dateAccepted = true;
				break;
			// may
			case 5:
				if (day <= 31)
					dateAccepted = true;
				break;
			// june
			case 6:
				if (day <= 30)
					dateAccepted = true;

				break;
			// july
			case 7:
				if (day <= 31)
					dateAccepted = true;
				break;
			// aug
			case 8:
				if (day <= 31)
					dateAccepted = true;

				break;
			// sept
			case 9:
				if (day <= 30)
					dateAccepted = true;
				break;
			// oct
			case 10:
				if (day <= 31)
					dateAccepted = true;
				break;
			// nov
			case 11:
				if (day <= 30)
					dateAccepted = true;
				break;
			// dec
			case 12:
				if (day <= 31)
					dateAccepted = true;
				break;

			default:
				return dateAccepted = false;
			}

		} else {
			return dateAccepted = false;
		}

		return dateAccepted;

	}
	
	// get a list of properties and their details from database
	public ArrayList<SearchObject> getSearchList() {
		Connection connection = getConnection();
		ArrayList<SearchObject> searchList = new ArrayList<>();
		double minPPN = model.getMinPPN();
		double maxPPN = model.getMaxPPN();
		int guestCap = model.getGuestCap();
		String sd = model.getSD();
		String ed = model.getED();
		
		try {

			startd = sourceFormat.parse(sd);
			endd = sourceFormat.parse(ed);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		String placeName = model.getPlaceName();// city field
		
		///////////////////////////////////////// no search criteria////////////////////////////////////////////////////
		if (minPPN == 0.0 && maxPPN == 0.0 && guestCap == 0 && sd.equals("01/01/2022") && ed.equals("31/12/2022")
				&& placeName.equals("")) {
			int addressId;
			String city;
			try {
				SearchObject search;

				String propertyFromPid = "Select property_id, address_id, shortName, guestCapacity, breakfast from Property";

				PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);

				ResultSet gettingProperty = getProperty.executeQuery();
				while (gettingProperty.next()) {
					addressId = gettingProperty.getInt("address_id");

					String placeNameFromAid = "Select placeName from Address where address_id=?";

					PreparedStatement getPlaceName = connection.prepareStatement(placeNameFromAid);
					getPlaceName.setInt(1, addressId);
					ResultSet gettingPlaceName = getPlaceName.executeQuery();

					while (gettingPlaceName.next()) {
						city = gettingPlaceName.getString("placeName");

						search = new SearchObject(gettingProperty.getInt("property_id"),
								gettingProperty.getString("shortName"), gettingProperty.getInt("guestCapacity"), city,
								gettingProperty.getBoolean("breakfast"));
						searchList.add(search);
					}
				}

				// removes duplicate entries from table
				for (int i = 0; i < searchList.size(); i++) {
					for (int j = i + 1; j < searchList.size(); j++) {
						if (searchList.get(i).getPropertyId() == searchList.get(j).getPropertyId()) {
							searchList.remove(j);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		///////////////////////////////////////// 1 search criteria////////////////////////////////
		// startEndDate
		if (minPPN == 0 && maxPPN == 0 && guestCap == 0 && !sd.equals("") && !ed.equals("") && placeName.equals("")) {
			int addressId, propId;
			String city;

			try {
				SearchObject search;

				String allCb = "select property_id, startDate, endDate from ChargeBands";
				PreparedStatement getAllCb = connection.prepareStatement(allCb);
				ResultSet gettingAllCb = getAllCb.executeQuery();

				while (gettingAllCb.next()) {
					// if the start date and end date entered are equal, before or after chargeband start date end date
					if ((sourceFormat.parse(gettingAllCb.getString("startDate")).equals(startd) &&
							sourceFormat.parse(gettingAllCb.getString("endDate")).equals(endd))
							|| (startd.before(sourceFormat.parse(gettingAllCb.getString("startDate")))
									&& endd.after(sourceFormat.parse(gettingAllCb.getString("startDate"))))) {
						propId = gettingAllCb.getInt("property_id");

						String propertyFromPid = "Select property_id, address_id, shortName, guestCapacity, breakfast from Property where property_id=?";

						PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
						getProperty.setInt(1, propId);

						ResultSet gettingProperty = getProperty.executeQuery();

						while (gettingProperty.next()) {
							addressId = gettingProperty.getInt("address_id");

							String placeNameFromAid = "Select placeName from Address where address_id=?";

							PreparedStatement getPlaceName = connection.prepareStatement(placeNameFromAid);
							getPlaceName.setInt(1, addressId);
							ResultSet gettingPlaceName = getPlaceName.executeQuery();

							while (gettingPlaceName.next()) {
								city = gettingPlaceName.getString("placeName");

								search = new SearchObject(gettingProperty.getInt("property_id"),
										gettingProperty.getString("shortName"), gettingProperty.getInt("guestCapacity"),
										city, gettingProperty.getBoolean("breakfast"));
								searchList.add(search);
							}
						}
					}
				}
				// removes duplicate entries from table
				for (int i = 0; i < searchList.size(); i++) {
					for (int j = i + 1; j < searchList.size(); j++) {
						if (searchList.get(i).getPropertyId() == searchList.get(j).getPropertyId()) {
							searchList.remove(j);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// city
		if (minPPN == 0.0 && maxPPN == 0.0 && guestCap == 0 && sd.equals("01/01/2022") && ed.equals("31/12/2022")
				&& !placeName.equals("")) {
			int addressId;
			String city;

			try {
				SearchObject search;

				String propertyFromPid = "Select property_id, address_id, shortName, guestCapacity, breakfast from Property";

				PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);

				ResultSet gettingProperty = getProperty.executeQuery();

				while (gettingProperty.next()) {
					addressId = gettingProperty.getInt("address_id");

					String placeNameFromAid = "Select placeName from Address where address_id=? and placeName=?";

					PreparedStatement getPlaceName = connection.prepareStatement(placeNameFromAid);
					getPlaceName.setInt(1, addressId);
					getPlaceName.setString(2, placeName);

					ResultSet gettingPlaceName = getPlaceName.executeQuery();

					while (gettingPlaceName.next()) {
						city = gettingPlaceName.getString("placeName");

						search = new SearchObject(gettingProperty.getInt("property_id"),
								gettingProperty.getString("shortName"), gettingProperty.getInt("guestCapacity"), city,
								gettingProperty.getBoolean("breakfast"));

						searchList.add(search);
					}
				}

				// removes duplicate entries from table
				for (int i = 0; i < searchList.size(); i++) {
					for (int j = i + 1; j < searchList.size(); j++) {
						if (searchList.get(i).getPropertyId() == searchList.get(j).getPropertyId()) {
							searchList.remove(j);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// guest
		if (minPPN == 0.0 && maxPPN == 0.0 && guestCap != 0 && sd.equals("01/01/2022") && ed.equals("31/12/2022")
				&& placeName.equals("")) {
			int addressId;
			String city;

			try {
				SearchObject search;

				String propertyFromPid = "Select property_id, address_id, shortName, guestCapacity, breakfast from Property where guestCapacity=?";

				PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
				getProperty.setInt(1, guestCap);

				ResultSet gettingProperty = getProperty.executeQuery();

				while (gettingProperty.next()) {
					addressId = gettingProperty.getInt("address_id");

					String placeNameFromAid = "Select placeName from Address where address_id=?";

					PreparedStatement getPlaceName = connection.prepareStatement(placeNameFromAid);
					getPlaceName.setInt(1, addressId);

					ResultSet gettingPlaceName = getPlaceName.executeQuery();

					while (gettingPlaceName.next()) {
						city = gettingPlaceName.getString("placeName");

						search = new SearchObject(gettingProperty.getInt("property_id"),
								gettingProperty.getString("shortName"), gettingProperty.getInt("guestCapacity"), city,
								gettingProperty.getBoolean("breakfast"));

						searchList.add(search);
					}
				}
				// removes duplicate entries from table
				for (int i = 0; i < searchList.size(); i++) {
					for (int j = i + 1; j < searchList.size(); j++) {
						if (searchList.get(i).getPropertyId() == searchList.get(j).getPropertyId()) {
							searchList.remove(j);
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// minMax
		if (minPPN != 0 && maxPPN != 0 && guestCap == 0 && sd.equals("01/01/2022") && ed.equals("31/12/2022")
				&& placeName.equals("")) {
			int addressId, propId;
			String city;

			try {
				SearchObject search;

				String allCb = "select property_id from ChargeBands where totalPricePerNight>=? and totalPricePerNight<=?";
				PreparedStatement getAllCb = connection.prepareStatement(allCb);
				getAllCb.setDouble(1, minPPN);
				getAllCb.setDouble(2, maxPPN);

				ResultSet gettingAllCb = getAllCb.executeQuery();

				while (gettingAllCb.next()) {
					propId = gettingAllCb.getInt("property_id");

					String propertyFromPid = "Select property_id, address_id, shortName, guestCapacity, breakfast from Property where property_id=?";

					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
					getProperty.setInt(1, propId);

					ResultSet gettingProperty = getProperty.executeQuery();

					while (gettingProperty.next()) {
						addressId = gettingProperty.getInt("address_id");

						String placeNameFromAid = "Select placeName from Address where address_id=?";

						PreparedStatement getPlaceName = connection.prepareStatement(placeNameFromAid);
						getPlaceName.setInt(1, addressId);
						ResultSet gettingPlaceName = getPlaceName.executeQuery();

						while (gettingPlaceName.next()) {
							city = gettingPlaceName.getString("placeName");

							search = new SearchObject(gettingProperty.getInt("property_id"),
									gettingProperty.getString("shortName"), gettingProperty.getInt("guestCapacity"),
									city, gettingProperty.getBoolean("breakfast"));
							searchList.add(search);
						}
					}
				}
				// removes duplicate entries from table
				for (int i = 0; i < searchList.size(); i++) {
					for (int j = i + 1; j < searchList.size(); j++) {
						if (searchList.get(i).getPropertyId() == searchList.get(j).getPropertyId()) {
							searchList.remove(j);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//////////////////////////////////////////////////// 2 search criteria///////////////////////////////////////////////
		// minMax, startEndDate
		if (minPPN != 0 && maxPPN != 0 && guestCap == 0 && !sd.equals("") && !ed.equals("") && placeName.equals("")) {
			int addressId, propId;
			String city;

			try {
				SearchObject search;

				String allCb = "select property_id, startDate, endDate from ChargeBands where totalPricePerNight>=? and totalPricePerNight<=?";
				PreparedStatement getAllCb = connection.prepareStatement(allCb);
				getAllCb.setDouble(1, minPPN);
				getAllCb.setDouble(2, maxPPN);
				ResultSet gettingAllCb = getAllCb.executeQuery();

				while (gettingAllCb.next()) {
					if ((sourceFormat.parse(gettingAllCb.getString("startDate")).equals(startd)
							&& sourceFormat.parse(gettingAllCb.getString("endDate")).equals(endd))
							|| (startd.before(sourceFormat.parse(gettingAllCb.getString("startDate")))
									&& endd.after(sourceFormat.parse(gettingAllCb.getString("startDate"))))) {

						propId = gettingAllCb.getInt("property_id");

						String propertyFromPid = "Select property_id, address_id, shortName, guestCapacity, breakfast from Property where property_id=?";

						PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
						getProperty.setInt(1, propId);

						ResultSet gettingProperty = getProperty.executeQuery();

						while (gettingProperty.next()) {
							addressId = gettingProperty.getInt("address_id");

							String placeNameFromAid = "Select placeName from Address where address_id=?";

							PreparedStatement getPlaceName = connection.prepareStatement(placeNameFromAid);
							getPlaceName.setInt(1, addressId);

							ResultSet gettingPlaceName = getPlaceName.executeQuery();

							while (gettingPlaceName.next()) {
								city = gettingPlaceName.getString("placeName");

								search = new SearchObject(gettingProperty.getInt("property_id"),
										gettingProperty.getString("shortName"), gettingProperty.getInt("guestCapacity"),
										city, gettingProperty.getBoolean("breakfast"));
								searchList.add(search);
							}
						}
					}
				}
				// removes duplicate entries from table
				for (int i = 0; i < searchList.size(); i++) {
					for (int j = i + 1; j < searchList.size(); j++) {
						if (searchList.get(i).getPropertyId() == searchList.get(j).getPropertyId()) {
							searchList.remove(j);
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// minMax, city
		if (minPPN != 0 && maxPPN != 0 && guestCap == 0 && sd.equals("01/01/2022") && ed.equals("31/12/2022")
				&& !placeName.equals("")) {
			int addressId, propId;
			String city;

			try {
				SearchObject search;

				String allCb = "select property_id from ChargeBands where totalPricePerNight>=? and totalPricePerNight<=?";
				PreparedStatement getAllCb = connection.prepareStatement(allCb);
				getAllCb.setDouble(1, minPPN);
				getAllCb.setDouble(2, maxPPN);
				ResultSet gettingAllCb = getAllCb.executeQuery();

				while (gettingAllCb.next()) {
					propId = gettingAllCb.getInt("property_id");

					String propertyFromPid = "Select property_id, address_id, shortName, guestCapacity, breakfast from Property where property_id=?";

					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
					getProperty.setInt(1, propId);

					ResultSet gettingProperty = getProperty.executeQuery();

					while (gettingProperty.next()) {
						addressId = gettingProperty.getInt("address_id");

						String placeNameFromAid = "Select placeName from Address where address_id=? and placeName=?";

						PreparedStatement getPlaceName = connection.prepareStatement(placeNameFromAid);
						getPlaceName.setInt(1, addressId);
						getPlaceName.setString(2, placeName);

						ResultSet gettingPlaceName = getPlaceName.executeQuery();

						while (gettingPlaceName.next()) {
							city = gettingPlaceName.getString("placeName");

							search = new SearchObject(gettingProperty.getInt("property_id"),
									gettingProperty.getString("shortName"), gettingProperty.getInt("guestCapacity"),
									city, gettingProperty.getBoolean("breakfast"));
							searchList.add(search);
						}
					}

				}

				// removes duplicate entries from table
				for (int i = 0; i < searchList.size(); i++) {
					for (int j = i + 1; j < searchList.size(); j++) {
						if (searchList.get(i).getPropertyId() == searchList.get(j).getPropertyId()) {
							searchList.remove(j);
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// minMax, guest
		if (minPPN != 0 && maxPPN != 0 && guestCap != 0 && sd.equals("01/01/2022") && ed.equals("31/12/2022")
				&& placeName.equals("")) {
			int addressId, propId;
			String city;

			try {
				SearchObject search;

				String allCb = "select property_id from ChargeBands where totalPricePerNight>=? and totalPricePerNight<=?";
				PreparedStatement getAllCb = connection.prepareStatement(allCb);
				getAllCb.setDouble(1, minPPN);
				getAllCb.setDouble(2, maxPPN);
				ResultSet gettingAllCb = getAllCb.executeQuery();

				while (gettingAllCb.next()) {
					propId = gettingAllCb.getInt("property_id");

					String propertyFromPid = "Select property_id, address_id, shortName, guestCapacity, breakfast from Property where property_id=? and guestCapacity=?";

					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
					getProperty.setInt(1, propId);
					getProperty.setInt(2, guestCap);

					ResultSet gettingProperty = getProperty.executeQuery();

					while (gettingProperty.next()) {
						addressId = gettingProperty.getInt("address_id");

						String placeNameFromAid = "Select placeName from Address where address_id=?";

						PreparedStatement getPlaceName = connection.prepareStatement(placeNameFromAid);
						getPlaceName.setInt(1, addressId);

						ResultSet gettingPlaceName = getPlaceName.executeQuery();

						while (gettingPlaceName.next()) {
							city = gettingPlaceName.getString("placeName");

							search = new SearchObject(gettingProperty.getInt("property_id"),
									gettingProperty.getString("shortName"), gettingProperty.getInt("guestCapacity"),
									city, gettingProperty.getBoolean("breakfast"));
							searchList.add(search);
						}
					}

				}
				// removes duplicate entries from table
				for (int i = 0; i < searchList.size(); i++) {
					for (int j = i + 1; j < searchList.size(); j++) {
						if (searchList.get(i).getPropertyId() == searchList.get(j).getPropertyId()) {
							searchList.remove(j);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// startEndDate, city
		if (minPPN == 0 && maxPPN == 0 && guestCap == 0 && !sd.equals("") && !ed.equals("") && !placeName.equals("")) {
			int addressId, propId;
			String city;

			try {
				SearchObject search;

				String allCb = "select property_id, startDate, endDate from ChargeBands";
				PreparedStatement getAllCb = connection.prepareStatement(allCb);

				ResultSet gettingAllCb = getAllCb.executeQuery();

				while (gettingAllCb.next()) {
					if ((sourceFormat.parse(gettingAllCb.getString("startDate")).equals(startd)
							&& sourceFormat.parse(gettingAllCb.getString("endDate")).equals(endd))
							|| (startd.before(sourceFormat.parse(gettingAllCb.getString("startDate")))
									&& endd.after(sourceFormat.parse(gettingAllCb.getString("startDate"))))) {

						propId = gettingAllCb.getInt("property_id");

						String propertyFromPid = "Select property_id, address_id, shortName, guestCapacity, breakfast from Property where property_id=?";

						PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
						getProperty.setInt(1, propId);

						ResultSet gettingProperty = getProperty.executeQuery();

						while (gettingProperty.next()) {
							addressId = gettingProperty.getInt("address_id");

							String placeNameFromAid = "Select placeName from Address where address_id=? and placeName=?";

							PreparedStatement getPlaceName = connection.prepareStatement(placeNameFromAid);
							getPlaceName.setInt(1, addressId);
							getPlaceName.setString(2, placeName);

							ResultSet gettingPlaceName = getPlaceName.executeQuery();
							
							while (gettingPlaceName.next()) {
								city = gettingPlaceName.getString("placeName");

								search = new SearchObject(gettingProperty.getInt("property_id"),
										gettingProperty.getString("shortName"), gettingProperty.getInt("guestCapacity"),
										city, gettingProperty.getBoolean("breakfast"));
								searchList.add(search);
							}
						}
					}
				}

				// removes duplicate entries from table
				for (int i = 0; i < searchList.size(); i++) {
					for (int j = i + 1; j < searchList.size(); j++) {
						if (searchList.get(i).getPropertyId() == searchList.get(j).getPropertyId()) {
							searchList.remove(j);
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// startEndDate, guest
		if (minPPN == 0 && maxPPN == 0 && guestCap != 0 && !sd.equals("") && !ed.equals("") && placeName.equals("")) {
			int addressId, propId;
			String city;

			try {
				SearchObject search;

				String allCb = "select property_id, startDate, endDate from ChargeBands";
				PreparedStatement getAllCb = connection.prepareStatement(allCb);

				ResultSet gettingAllCb = getAllCb.executeQuery();

				while (gettingAllCb.next()) {
					if ((sourceFormat.parse(gettingAllCb.getString("startDate")).equals(startd)
							&& sourceFormat.parse(gettingAllCb.getString("endDate")).equals(endd))
							|| (startd.before(sourceFormat.parse(gettingAllCb.getString("startDate")))
									&& endd.after(sourceFormat.parse(gettingAllCb.getString("startDate"))))) {

						propId = gettingAllCb.getInt("property_id");

						String propertyFromPid = "Select property_id, address_id, shortName, guestCapacity, breakfast from Property where property_id=? and guestCapacity=?";

						PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
						getProperty.setInt(1, propId);
						getProperty.setInt(2, guestCap);

						ResultSet gettingProperty = getProperty.executeQuery();

						while (gettingProperty.next()) {
							addressId = gettingProperty.getInt("address_id");

							String placeNameFromAid = "Select placeName from Address where address_id=?";

							PreparedStatement getPlaceName = connection.prepareStatement(placeNameFromAid);
							getPlaceName.setInt(1, addressId);

							ResultSet gettingPlaceName = getPlaceName.executeQuery();

							while (gettingPlaceName.next()) {
								city = gettingPlaceName.getString("placeName");

								search = new SearchObject(gettingProperty.getInt("property_id"),
										gettingProperty.getString("shortName"), gettingProperty.getInt("guestCapacity"),
										city, gettingProperty.getBoolean("breakfast"));
								searchList.add(search);
							}
						}
					}
				}
				// removes duplicate entries from table
				for (int i = 0; i < searchList.size(); i++) {
					for (int j = i + 1; j < searchList.size(); j++) {
						if (searchList.get(i).getPropertyId() == searchList.get(j).getPropertyId()) {
							searchList.remove(j);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// city, guest
		if (minPPN == 0 && maxPPN == 0 && guestCap != 0 && sd.equals("01/01/2022") && ed.equals("31/12/2022")
				&& !placeName.equals("")) {
			int addressId, propId;
			String city;

			try {
				SearchObject search;

				String allCb = "select property_id from ChargeBands";
				PreparedStatement getAllCb = connection.prepareStatement(allCb);

				ResultSet gettingAllCb = getAllCb.executeQuery();

				while (gettingAllCb.next()) {
					propId = gettingAllCb.getInt("property_id");

					String propertyFromPid = "Select property_id, address_id, shortName, guestCapacity, breakfast from Property where property_id=? and guestCapacity=?";

					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
					getProperty.setInt(1, propId);
					getProperty.setInt(2, guestCap);

					ResultSet gettingProperty = getProperty.executeQuery();

					while (gettingProperty.next()) {
						addressId = gettingProperty.getInt("address_id");

						String placeNameFromAid = "Select placeName from Address where address_id=? and placeName=?";

						PreparedStatement getPlaceName = connection.prepareStatement(placeNameFromAid);
						getPlaceName.setInt(1, addressId);
						getPlaceName.setString(2, placeName);

						ResultSet gettingPlaceName = getPlaceName.executeQuery();

						while (gettingPlaceName.next()) {
							city = gettingPlaceName.getString("placeName");

							search = new SearchObject(gettingProperty.getInt("property_id"),
									gettingProperty.getString("shortName"), gettingProperty.getInt("guestCapacity"),
									city, gettingProperty.getBoolean("breakfast"));
							searchList.add(search);
						}
					}

				}
				// removes duplicate entries from table
				for (int i = 0; i < searchList.size(); i++) {
					for (int j = i + 1; j < searchList.size(); j++) {
						if (searchList.get(i).getPropertyId() == searchList.get(j).getPropertyId()) {
							searchList.remove(j);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//////////////////////////////////////////////////// 3 search criteria////////////////////////////////////////////////
		// minMax, startEndDate, city
		if (minPPN != 0 && maxPPN != 0 && guestCap == 0 && !sd.equals("") && !ed.equals("") && !placeName.equals("")) {
			int addressId, propId;
			String city;

			try {
				SearchObject search;

				String allCb = "select property_id, startDate, endDate from ChargeBands where totalPricePerNight>=? and totalPricePerNight<=?";
				PreparedStatement getAllCb = connection.prepareStatement(allCb);
				getAllCb.setDouble(1, minPPN);
				getAllCb.setDouble(2, maxPPN);
				ResultSet gettingAllCb = getAllCb.executeQuery();

				while (gettingAllCb.next()) {
					if ((sourceFormat.parse(gettingAllCb.getString("startDate")).equals(startd)
							&& sourceFormat.parse(gettingAllCb.getString("endDate")).equals(endd))
							|| (startd.before(sourceFormat.parse(gettingAllCb.getString("startDate")))
									&& endd.after(sourceFormat.parse(gettingAllCb.getString("startDate"))))) {

						propId = gettingAllCb.getInt("property_id");

						String propertyFromPid = "Select property_id, address_id, shortName, guestCapacity, breakfast from Property where property_id=?";

						PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
						getProperty.setInt(1, propId);

						ResultSet gettingProperty = getProperty.executeQuery();

						while (gettingProperty.next()) {
							addressId = gettingProperty.getInt("address_id");

							String placeNameFromAid = "Select placeName from Address where address_id=? and placeName=?";

							PreparedStatement getPlaceName = connection.prepareStatement(placeNameFromAid);
							getPlaceName.setInt(1, addressId);
							getPlaceName.setString(2, placeName);

							ResultSet gettingPlaceName = getPlaceName.executeQuery();

							while (gettingPlaceName.next()) {
								city = gettingPlaceName.getString("placeName");

								search = new SearchObject(gettingProperty.getInt("property_id"),
										gettingProperty.getString("shortName"), gettingProperty.getInt("guestCapacity"),
										city, gettingProperty.getBoolean("breakfast"));
								searchList.add(search);
							}
						}
					}
				}
				// removes duplicate entries from table
				for (int i = 0; i < searchList.size(); i++) {
					for (int j = i + 1; j < searchList.size(); j++) {
						if (searchList.get(i).getPropertyId() == searchList.get(j).getPropertyId()) {
							searchList.remove(j);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// minMax, startEndDate, guest
		if (minPPN != 0 && maxPPN != 0 && guestCap != 0 && !sd.equals("") && !ed.equals("") && placeName.equals("")) {
			int addressId, propId;
			String city;

			try {
				SearchObject search;

				String allCb = "select property_id, startDate, endDate from ChargeBands where totalPricePerNight>=? and totalPricePerNight<=?";
				PreparedStatement getAllCb = connection.prepareStatement(allCb);
				getAllCb.setDouble(1, minPPN);
				getAllCb.setDouble(2, maxPPN);
				ResultSet gettingAllCb = getAllCb.executeQuery();

				while (gettingAllCb.next()) {
					if ((sourceFormat.parse(gettingAllCb.getString("startDate")).equals(startd)
							&& sourceFormat.parse(gettingAllCb.getString("endDate")).equals(endd))
							|| (startd.before(sourceFormat.parse(gettingAllCb.getString("startDate")))
									&& endd.after(sourceFormat.parse(gettingAllCb.getString("startDate"))))) {

						propId = gettingAllCb.getInt("property_id");

						String propertyFromPid = "Select property_id, address_id, shortName, guestCapacity, breakfast from Property where property_id=? and guestCapacity=?";

						PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
						getProperty.setInt(1, propId);
						getProperty.setInt(2, guestCap);

						ResultSet gettingProperty = getProperty.executeQuery();

						while (gettingProperty.next()) {
							addressId = gettingProperty.getInt("address_id");

							String placeNameFromAid = "Select placeName from Address where address_id=?";

							PreparedStatement getPlaceName = connection.prepareStatement(placeNameFromAid);
							getPlaceName.setInt(1, addressId);

							ResultSet gettingPlaceName = getPlaceName.executeQuery();

							while (gettingPlaceName.next()) {
								city = gettingPlaceName.getString("placeName");

								search = new SearchObject(gettingProperty.getInt("property_id"),
										gettingProperty.getString("shortName"), gettingProperty.getInt("guestCapacity"),
										city, gettingProperty.getBoolean("breakfast"));
								searchList.add(search);
							}
						}
					}
				}
				// removes duplicate entries from table
				for (int i = 0; i < searchList.size(); i++) {
					for (int j = i + 1; j < searchList.size(); j++) {
						if (searchList.get(i).getPropertyId() == searchList.get(j).getPropertyId()) {
							searchList.remove(j);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// minMax, city, guest
		if (minPPN != 0 && maxPPN != 0 && guestCap != 0 && sd.equals("01/01/2022") && ed.equals("31/12/2022")
				&& !placeName.equals("")) {
			int addressId, propId;
			String city;

			try {
				SearchObject search;

				String allCb = "select property_id from ChargeBands where totalPricePerNight>=? and totalPricePerNight<=?";
				PreparedStatement getAllCb = connection.prepareStatement(allCb);
				getAllCb.setDouble(1, minPPN);
				getAllCb.setDouble(2, maxPPN);
				ResultSet gettingAllCb = getAllCb.executeQuery();

				while (gettingAllCb.next()) {
					propId = gettingAllCb.getInt("property_id");

					String propertyFromPid = "Select property_id, address_id, shortName, guestCapacity, breakfast from Property where property_id=? and guestCapacity=?";

					PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
					getProperty.setInt(1, propId);
					getProperty.setInt(2, guestCap);

					ResultSet gettingProperty = getProperty.executeQuery();

					while (gettingProperty.next()) {
						addressId = gettingProperty.getInt("address_id");

						String placeNameFromAid = "Select placeName from Address where address_id=? and placeName=?";

						PreparedStatement getPlaceName = connection.prepareStatement(placeNameFromAid);
						getPlaceName.setInt(1, addressId);
						getPlaceName.setString(2, placeName);

						ResultSet gettingPlaceName = getPlaceName.executeQuery();

						while (gettingPlaceName.next()) {
							city = gettingPlaceName.getString("placeName");

							search = new SearchObject(gettingProperty.getInt("property_id"),
									gettingProperty.getString("shortName"), gettingProperty.getInt("guestCapacity"),
									city, gettingProperty.getBoolean("breakfast"));
							searchList.add(search);
						}
					}
				}

				// removes duplicate entries from table
				for (int i = 0; i < searchList.size(); i++) {
					for (int j = i + 1; j < searchList.size(); j++) {
						if (searchList.get(i).getPropertyId() == searchList.get(j).getPropertyId()) {
							searchList.remove(j);
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// startEndDate, city, guest
		if (minPPN == 0 && maxPPN == 0 && guestCap != 0 && !sd.equals("") && !ed.equals("") && !placeName.equals("")) {
			int addressId, propId;
			String city;

			try {
				SearchObject search;

				String allCb = "select property_id, startDate, endDate from ChargeBands";
				PreparedStatement getAllCb = connection.prepareStatement(allCb);

				ResultSet gettingAllCb = getAllCb.executeQuery();

				while (gettingAllCb.next()) {
					if ((sourceFormat.parse(gettingAllCb.getString("startDate")).equals(startd)
							&& sourceFormat.parse(gettingAllCb.getString("endDate")).equals(endd))
							|| (startd.before(sourceFormat.parse(gettingAllCb.getString("startDate")))
									&& endd.after(sourceFormat.parse(gettingAllCb.getString("startDate"))))) {

						propId = gettingAllCb.getInt("property_id");

						String propertyFromPid = "Select property_id, address_id, shortName, guestCapacity, breakfast from Property where property_id=? and guestCapacity=?";

						PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
						getProperty.setInt(1, propId);
						getProperty.setInt(2, guestCap);

						ResultSet gettingProperty = getProperty.executeQuery();

						while (gettingProperty.next()) {
							addressId = gettingProperty.getInt("address_id");

							String placeNameFromAid = "Select placeName from Address where address_id=? and placeName=?";

							PreparedStatement getPlaceName = connection.prepareStatement(placeNameFromAid);
							getPlaceName.setInt(1, addressId);
							getPlaceName.setString(2, placeName);

							ResultSet gettingPlaceName = getPlaceName.executeQuery();

							while (gettingPlaceName.next()) {
								city = gettingPlaceName.getString("placeName");

								search = new SearchObject(gettingProperty.getInt("property_id"),
										gettingProperty.getString("shortName"), gettingProperty.getInt("guestCapacity"),
										city, gettingProperty.getBoolean("breakfast"));
								searchList.add(search);
							}
						}
					}
				}
				// removes duplicate entries from table
				for (int i = 0; i < searchList.size(); i++) {
					for (int j = i + 1; j < searchList.size(); j++) {
						if (searchList.get(i).getPropertyId() == searchList.get(j).getPropertyId()) {
							searchList.remove(j);
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		////////////////////////////////////////////////// ALL 4 SEARCH CRITERIA//////////////////////////////////////////////
		if (minPPN != 0 && maxPPN != 0 && guestCap != 0 && !sd.equals("") && !ed.equals("") && !placeName.equals("")) {
			int addressId, propId;
			String city;

			try {
				SearchObject search;

				String allCb = "select property_id, startDate, endDate from ChargeBands where totalPricePerNight>=? and totalPricePerNight<=?";
				PreparedStatement getAllCb = connection.prepareStatement(allCb);
				getAllCb.setDouble(1, minPPN);
				getAllCb.setDouble(2, maxPPN);
				ResultSet gettingAllCb = getAllCb.executeQuery();

				while (gettingAllCb.next()) {
					if ((sourceFormat.parse(gettingAllCb.getString("startDate")).equals(startd)
							&& sourceFormat.parse(gettingAllCb.getString("endDate")).equals(endd))
							|| (startd.before(sourceFormat.parse(gettingAllCb.getString("startDate")))
									&& endd.after(sourceFormat.parse(gettingAllCb.getString("startDate"))))) {

						propId = gettingAllCb.getInt("property_id");

						String propertyFromPid = "Select property_id, address_id, shortName, guestCapacity, breakfast from Property where property_id=? and guestCapacity=?";

						PreparedStatement getProperty = connection.prepareStatement(propertyFromPid);
						getProperty.setInt(1, propId);
						getProperty.setInt(2, guestCap);

						ResultSet gettingProperty = getProperty.executeQuery();

						while (gettingProperty.next()) {
							addressId = gettingProperty.getInt("address_id");

							String placeNameFromAid = "Select placeName from Address where address_id=? and placeName=?";

							PreparedStatement getPlaceName = connection.prepareStatement(placeNameFromAid);
							getPlaceName.setInt(1, addressId);
							getPlaceName.setString(2, placeName);

							ResultSet gettingPlaceName = getPlaceName.executeQuery();

							while (gettingPlaceName.next()) {
								city = gettingPlaceName.getString("placeName");

								search = new SearchObject(gettingProperty.getInt("property_id"),
										gettingProperty.getString("shortName"), gettingProperty.getInt("guestCapacity"),
										city, gettingProperty.getBoolean("breakfast"));
								searchList.add(search);
							}
						}
					}
				}
				// removes duplicate entries from table
				for (int i = 0; i < searchList.size(); i++) {
					for (int j = i + 1; j < searchList.size(); j++) {
						if (searchList.get(i).getPropertyId() == searchList.get(j).getPropertyId()) {
							searchList.remove(j);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return searchList;
	}

	// Display Data In Table

	public void Show_Search_In_JTable() {

		ArrayList<SearchObject> list = getSearchList();
		DefaultTableModel model = (DefaultTableModel) jTable_Display_Search.getModel();
		model.setRowCount(0); //clears table every time 'search' button clicked
		
		Object[] row = new Object[6];
		
		
		Connection connection = getConnection();
		
		//Displays property details in table rows
		for (SearchObject element : list) {
			row[0] = element.getPropertyId();
			row[1] = element.getShortName();
			row[2] = element.getGuestCapacity();
			row[3] = element.getPlaceName();
			
			if (element.getBreakfast()) {
				row[4] = "Yes";
			} else {
				row[4] = "No";
			}
			
			
			int host_id = 0;
			boolean superhost = false;
			try {
			String hostIdFromProperty = "Select host_id from Property where property_id=?";

			PreparedStatement hostIdValue = connection.prepareStatement(hostIdFromProperty);
			hostIdValue.setInt(1, element.getPropertyId());

			ResultSet hostIds = hostIdValue.executeQuery();

			while (hostIds.next()) {
				host_id = hostIds.getInt("host_id");
			}
			
			String superHostFromHost = "Select superhost from HostAccount where host_id=?";

			PreparedStatement superHostValue = connection.prepareStatement(superHostFromHost);
			superHostValue.setInt(1, host_id);

			ResultSet superHosts = superHostValue.executeQuery();

			while (superHosts.next()) {
				superhost = superHosts.getBoolean("superhost");
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
			

			if (superhost) {
				row[5] = "Yes";
			} else {
				row[5] = "No";
			}
			
			model.addRow(row);
		}
		
	}
	
	public void displayMessageEmptyPropertyId() {
		JOptionPane.showMessageDialog(this,
				"Please click on a row to select a property.");
	}

	@SuppressWarnings("unchecked")
	public void initComponents() {
		jPanel1 = new javax.swing.JPanel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();

		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane1.setBounds(318, 13, 565, 634);
		jTable_Display_Search = new javax.swing.JTable();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jPanel1.setBackground(new java.awt.Color(204, 255, 255));

		jLabel5.setFont(new java.awt.Font("Verdana", 0, 18));
		jLabel5.setText("Short Name:");

		jLabel6.setFont(new java.awt.Font("Verdana", 0, 18));
		jLabel6.setText("Guest Capacity:");

		jTable_Display_Search.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, 
				//Column names
				new String[] { "Property ID", "Short Name", "Guest Capacity", "City", "Breakfast Offered", "SuperHost" }
		));

		jTable_Display_Search.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jTable_Display_SearchMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(jTable_Display_Search);

		viewPropertyButton = new JButton("View Property");
		viewPropertyButton.setBounds(82, 580, 127, 38);
		viewPropertyButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		viewPropertyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// get value of propertyId in textbox
				// pass property ID into controller, so can display Property 
				//information on the next page for the selected property
				if (propertyIDTextField.getText().isEmpty()) {
					displayMessageEmptyPropertyId();
				} else {
					mainModule.editPropertyState = EDITPROPERTY.BOOK_PROPERTY;
					MainModule.controller.editPropertyView(Integer.parseInt(propertyIDTextField.getText()), 0);
				}
			}
		});
		
		

		searchBtn = new JButton("Search");
		searchBtn.setBounds(100, 506, 79, 38);
		searchBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// checking for empty fields
				// if minprice, maxprice, or guest capacity are empty then set them to 0
				// if start date is empty set it to 01/01/2022 (set as string. Is converted
				// later in the query)
				// if end date is empty set to 31/12/2022
				// location is not chosen then set to an empty string: ""
				Boolean startDateIsEmpty = startDateTextField.getText().isBlank();
				Boolean endDateIsEmpty = endDateTextField.getText().isBlank();
				if (!startDateIsEmpty && !endDateIsEmpty) {
					// checking date format:
					Boolean startDateValidation = startDateTextField.getText().matches("\\d{2}/\\d{2}/\\d{4}");
					Boolean endDateValidation = endDateTextField.getText().matches("\\d{2}/\\d{2}/\\d{4}");

					// checking if date matches DATE object format
					if (startDateValidation && endDateValidation) {

						Date formattedStartDate = null;
						Date formattedEndDate = null;
						try {
							formattedStartDate = sourceFormat.parse(startDateTextField.getText());
							formattedEndDate = sourceFormat.parse(endDateTextField.getText());

						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						Boolean timeCheck = formattedStartDate.before(formattedEndDate);

						// checking if dates are in the year 2022
						String startDateParts[] = startDateTextField.getText().split("/");
						String endDateParts[] = endDateTextField.getText().split("/");

						// Getting day, month, and year for start date:
						int startDay = Integer.parseInt(startDateParts[0]);
						int startMonth = Integer.parseInt(startDateParts[1]);
						int startYear = Integer.parseInt(startDateParts[2]);

						// getting day, month, and year for end date:
						int endDay = Integer.parseInt(endDateParts[0]);
						int endMonth = Integer.parseInt(endDateParts[1]);
						int endYear = Integer.parseInt(endDateParts[2]);

						// call function to validate the date:
						Boolean startDateAccepted = validateDate(startDay, startMonth, startYear);
						Boolean endDateAccepted = validateDate(endDay, endMonth, endYear);

						// finally checking if the start time is less than the end time:
						if (!timeCheck || !startDateAccepted || !endDateAccepted) {
							displayInvalidStartEndTimeMessage();
						}
					} else {
						displayInvalidDateMessage();

					}

				} else {
					displayEmptyStringsMessage();

				}
				
				//setting minPrice
				if (minPriceTextField.getText().isEmpty()) {
					model.setMinPPN(0);
				} else {
					if(maxPriceTextField.getText().equals("0") || maxPriceTextField.getText().isEmpty()) {
						model.setMaxPPN(1);
					}
					model.setMinPPN(Double.parseDouble(minPriceTextField.getText().toString()));
				}

				// seting max price
				if (maxPriceTextField.getText().isEmpty()) {
					model.setMaxPPN(0);
				} else {
					if(minPriceTextField.getText().equals("0") || minPriceTextField.getText().isEmpty()) {
						model.setMinPPN(1);
					}
					model.setMaxPPN(Double.parseDouble(maxPriceTextField.getText().toString()));
				}

				// setting start date
				if (startDateTextField.getText().isEmpty()) {
					model.setSD("01/01/2022");

				} else {
					model.setSD(startDateTextField.getText().toString());
				}

				// setting end date
				if (endDateTextField.getText().isEmpty()) {
					model.setED("31/12/2022");

				} else {
					model.setED(endDateTextField.getText().toString());
				}

				// setting guest capacity
				if (guestCapacityTextField.getText().isEmpty()) {
					model.setGuestCap(0);
				} else {
					model.setGuestCap(Integer.parseInt(guestCapacityTextField.getText().toString()));
				}

				model.setPlaceName(locationComboBox.getSelectedItem().toString());
				Show_Search_In_JTable();
			}
		});

		//search textfields and buttons
		minPriceTextField = new JTextField();
		minPriceTextField.setBounds(175, 66, 133, 31);
		minPriceTextField.setText("0");
		minPriceTextField.setColumns(10);

		maxPriceTextField = new JTextField();
		maxPriceTextField.setBounds(175, 108, 133, 31);
		maxPriceTextField.setText("0");
		maxPriceTextField.setColumns(10);

		startDateTextField = new JTextField();
		startDateTextField.setBounds(175, 152, 133, 31);
		startDateTextField.setText("01/01/2022");
		startDateTextField.setColumns(10);

		endDateTextField = new JTextField();
		endDateTextField.setBounds(175, 194, 133, 31);
		endDateTextField.setText("31/12/2022");
		endDateTextField.setColumns(10);

		guestCapacityTextField = new JTextField();
		guestCapacityTextField.setBounds(175, 236, 133, 31);
		guestCapacityTextField.setText("0");
		guestCapacityTextField.setColumns(10);

		String cityNames[] = { "", "Bath", "Birmingham", "Bradford", "Brighton and Hove", "Bristol", "Cambridge",
				"Canterbury", "Carlisle", "Chelmsford", "Chester", "Chichester", "Coventry", "Derby", "Durham", "Ely",
				"Exeter", "Gloucester", "Hereford", "Kingston upon Hull", "Lancaster", "Leeds", "Leicester",
				"Lichfield", "Lincoln", "Liverpool", "London", "Manchester", "Newcastle upon Tyne", "Norwich",
				"Nottingham", "Oxford", "Peterborough", "Plymouth", "Portsmouth", "Preston", "Ripon", "Salford",
				"Salisbury", "Sheffield", "Southampton", "St Albans", "Stoke-on-Trent", "Sunderland", "Truro",
				"Wakefield", "Wells", "Westminster", "Winchester", "Wolverhampton", "Worcester", "York" };
		locationComboBox = new JComboBox(cityNames);
		locationComboBox.setBounds(175, 278, 133, 31);

		JLabel minPriceLabel = new JLabel("Minimum Price Per Night");
		minPriceLabel.setBounds(10, 66, 239, 31);
		minPriceLabel.setFont(new Font("Tahoma", Font.BOLD, 13));

		maxPriceLabel = new JLabel("Maximum Price Per Night");
		maxPriceLabel.setBounds(10, 110, 239, 31);
		maxPriceLabel.setFont(new Font("Tahoma", Font.BOLD, 13));

		startDateLabel = new JLabel("Start Date");
		startDateLabel.setBounds(10, 152, 239, 31);
		startDateLabel.setFont(new Font("Tahoma", Font.BOLD, 13));

		endDateLabel = new JLabel("End Date");
		endDateLabel.setBounds(10, 196, 239, 31);
		endDateLabel.setFont(new Font("Tahoma", Font.BOLD, 13));

		guestCapacityLabel = new JLabel("Guest Capacity");
		guestCapacityLabel.setBounds(10, 238, 239, 31);
		guestCapacityLabel.setFont(new Font("Tahoma", Font.BOLD, 13));

		locationLabel = new JLabel("Location");
		locationLabel.setBounds(10, 280, 239, 31);
		locationLabel.setFont(new Font("Tahoma", Font.BOLD, 13));

		propertyIdLabel = new JLabel("Property ID");
		propertyIdLabel.setBounds(13, 430, 239, 31);
		propertyIdLabel.setFont(new Font("Tahoma", Font.BOLD, 13));

		propertyIDTextField = new JTextField();
		propertyIDTextField.setBounds(162, 431, 130, 31);
		propertyIDTextField.setColumns(10);

		JButton backButton = new JButton("Back");
		backButton.setBounds(66, 11, 91, 29);
		backButton.addActionListener(new ActionListener() {
			//setting appropriate user state so can return to correct page in the correct user state
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mainModule.userState == USER.GUEST) {
					mainModule.currentState = STATE.GUEST_ACCOUNT;
					MainModule.controller.drawNewView();
					setVisible(false);
				} else if (mainModule.userState == USER.HOST) {
					mainModule.currentState = STATE.HOST_ACCOUNT;
					MainModule.controller.drawNewView();
					setVisible(false);
				} else {
					mainModule.currentState = STATE.HOMEPAGE;
					MainModule.controller.drawNewView();
					setVisible(false);
				}
			}
		});

		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));

		navBarPanel = new JPanel();
		navBarPanel.setBackground(new Color(51, 255, 255));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(navBarPanel, GroupLayout.DEFAULT_SIZE, 893, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				layout.createSequentialGroup().addComponent(navBarPanel, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 666, GroupLayout.PREFERRED_SIZE)));
		jPanel1.setLayout(null);
		jPanel1.add(locationLabel);
		jPanel1.add(guestCapacityLabel);
		jPanel1.add(startDateLabel);
		jPanel1.add(endDateLabel);
		jPanel1.add(maxPriceLabel);
		jPanel1.add(minPriceLabel);
		jPanel1.add(backButton);
		jPanel1.add(locationComboBox);
		jPanel1.add(maxPriceTextField);
		jPanel1.add(minPriceTextField);
		jPanel1.add(startDateTextField);
		jPanel1.add(endDateTextField);
		jPanel1.add(guestCapacityTextField);
		jPanel1.add(viewPropertyButton);
		jPanel1.add(propertyIdLabel);
		jPanel1.add(propertyIDTextField);
		jPanel1.add(searchBtn);
		jPanel1.add(jScrollPane1);

		lblNewLabel = new JLabel("View a Property to Book:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 367, 227, 31);
		jPanel1.add(lblNewLabel);

		// NAVBAR

		if (mainModule.userState == USER.GUEST || mainModule.userState == USER.HOST) {

			JButton navHomeButton = new JButton("Home");
			navHomeButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
			navBarPanel.add(navHomeButton);
			navHomeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (mainModule.userState == USER.GUEST) {
						mainModule.currentState = STATE.GUEST_ACCOUNT;
						MainModule.controller.drawNewView();
						setVisible(false);
					} else {
						mainModule.currentState = STATE.HOST_ACCOUNT;
						MainModule.controller.drawNewView();
						setVisible(false);
					}
				}
			});

			JButton navLogoutButton = new JButton("Logout");
			navLogoutButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
			navLogoutButton.setForeground(new Color(0, 0, 0));
			navBarPanel.add(navLogoutButton);
			navLogoutButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mainModule.currentState = STATE.HOMEPAGE;
					mainModule.userState = USER.ENQUIRER;
					MainModule.controller.drawNewView();
					setVisible(false);
				}
			});

			JButton navSearchButton = new JButton("Search");
			navSearchButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
			navBarPanel.add(navSearchButton);
			navSearchButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mainModule.currentState = STATE.SEARCH;
					MainModule.controller.drawNewView();
					setVisible(false);
				}
			});

		} else {
			JButton navHomeButton = new JButton("Home");
			navHomeButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
			navBarPanel.add(navHomeButton);
			navHomeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mainModule.currentState = STATE.HOMEPAGE;
					MainModule.controller.drawNewView();
					setVisible(false);
				}
			});

			JButton navSearchButton = new JButton("Search");
			navSearchButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
			navBarPanel.add(navSearchButton);
			navSearchButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mainModule.currentState = STATE.SEARCH;
					MainModule.controller.drawNewView();
					setVisible(false);
				}
			});
		}
		getContentPane().setLayout(layout);
		pack();
	}
	public void displayInvalidDateMessage() {
		JOptionPane.showMessageDialog(this,
				"Please type dates in the format DD/MM/YYYY.");
	}

	public void displayEmptyStringsMessage() {
		JOptionPane.showMessageDialog(this, "Please do not leave fields empty");
	}

	public void displayInvalidStartEndTimeMessage() {
		JOptionPane.showMessageDialog(this,
				"The start date is after end date or the dates you have picked are not in 2022. ");
	}
	
	// show jtable row data in jtextfields in the mouse clicked event
	private void jTable_Display_SearchMouseClicked(java.awt.event.MouseEvent evt) {
		// Get The Index Of The Slected Row
		int i = jTable_Display_Search.getSelectedRow();

		TableModel model = jTable_Display_Search.getModel();
		// Display Slected Row In JTexteFields
		propertyIDTextField.setText(model.getValueAt(i, 0).toString());
	}
	
	
	public void initializeSearch() {
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
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				setLocationRelativeTo(null);
				new Search(mainModule, controller, model).setVisible(true);
			}
		});
	}
}
//code partially from https://1bestcsharp.blogspot.com/2016/01/java-and-mysql-insert-update-delete-display.html