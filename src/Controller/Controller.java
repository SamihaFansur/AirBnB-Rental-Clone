package Controller;

import GUI.Homepage;
import GUI.Login;
import GUI.MainModule;
import GUI.Register;
import GUI.Search;
import GUI.Contact;
import GUI.MainModule.STATE;
import GUI.MainModule.USER;
import Model.*;
import HostGUI.*;
import HostGUI.AddFacility;
import HostGUI.EditBathing;
import HostGUI.EditBathroom;
import HostGUI.EditBedroom;
import HostGUI.EditKitchen;
import HostGUI.EditLiving;
import HostGUI.EditOutdoors;
import HostGUI.EditProperty;
import HostGUI.EditSleeping;
import HostGUI.EditUtility;
import HostGUI.Facilities;
import HostGUI.HostAccount;
import HostGUI.Properties;

import java.util.*;   

public class Controller extends MainModule{

	private Model model;
	private MainModule mainModule;
	// views
	private Homepage homepage;
	private Register register;
	private Search search;
	private Login login;
	private Contact contact;
	
	//hostGUI
	private AddFacility addFacility;
	private EditBathing editBathing;
	private EditBathroom editBathroom;
	private EditBedroom editBedroom;
	private EditKitchen editKitchen;
	private EditLiving editLiving;
	private EditOutdoors editOutdoors;
	private EditProperty editProperty;
	private EditSleeping editSleeping;
	private EditUtility editUtility;
	private Facilities facilities;
	private HostAccount hostAccount;
	private Properties properties;

//	private Properties properties;
	
	
	public Controller(MainModule mainModule, 
			Model model, 
			Homepage homepage, 
			Register register, 
			Search search, 
			Login login, 
			Contact contact,
			
			AddFacility addFacility,
			EditBathing editBathing,
			EditBathroom editBathroom,
			EditBedroom editBedroom,
			EditKitchen editKitchen,
			EditLiving editLiving,
			EditOutdoors editOutdoors,
			EditProperty editProperty,
			EditSleeping editSleeping,
			EditUtility editUtility,
			Facilities facilities,
			HostAccount hostAccount,
			Properties properties) {
		
		System.out.println("now in controller");
		this.mainModule=mainModule;
		this.model=model;
		//views
		this.homepage=homepage;
		this.register=register;
		this.search=search;
		this.login=login;
		this.contact=contact;
		this.addFacility=addFacility;
		this.editBathing=editBathing;
		this.editBathroom=editBathroom;
		this.editBedroom=editBedroom;
		this.editKitchen=editKitchen;
		this.editLiving=editLiving;
		this.editOutdoors=editOutdoors;
		this.editProperty=editProperty;
		this.editSleeping=editSleeping;
		this.editUtility=editUtility;
		this.facilities=facilities;
		this.hostAccount=hostAccount;
		this.properties=properties;
		
	}
	public void setTitle(String title){
		model.setTitle(title);
	}

	public void setPassword(String password){
		model.setPassword(password);
	}
	
	public void setFirstName(String firstName) {
		model.setFirstName(firstName);
	}
	
	public void setSurname(String surname) {
		model.setSurname(surname);
	}
	
	public void setEmail(String email) {
		model.setEmail(email);
	}
	
	public void setMobileNumber(String mobileNumber) {
		model.setMobileNumber(mobileNumber);
	}
	
	public void setHouseNameNumber(String houseNameNumber){
		model.setHouseNameNum(houseNameNumber);
	}

	public void setStreetName(String streetName){
		model.setStreetName(streetName);
	}

	public void setCity(String city){
		model.setCity(city);
	}

	public void setPostcode(String postcode){
		model.setPostcode(postcode);
	}

	public void setAccountType(String accountType){
		model.setAccountType(accountType);
	}
	
	//edit property setters
	public void setEditPropertyHouseNameNum(String houseNameNum){
		model.setEditPropertyHouseNameNum(houseNameNum);
	}

	public void setEditPropertyStreetName(String streetName){
		model.setEditPropertyStreetName(streetName);
	}

	public void setEditPropertyCity(String city){
		model.setEditPropertyCity(city);
	}

	public void setEditPropertyPostcode(String postcode){
		model.setEditPropertyPostcode(postcode);
	}
	
	//checking the state of the system:
	public void drawNewView() {
		System.out.println("in drawNewView");
		System.out.println("1----------"+mainModule.currentState);
		if (mainModule.currentState == STATE.HOMEPAGE && mainModule.userState==USER.ENQUIRER){
			homepage.initializeHomePage();
		}
		//checking if in SELF_REGISTRATION state, then update the JPanel in SELF_REGISTRATION 
		// (at the moment the window is made in SELF_REGISTRATION)
		else if (mainModule.currentState == STATE.SELF_REGISTRATION && mainModule.userState==USER.ENQUIRER){
			register.initializeRegister();
		}
		else if (mainModule.currentState == STATE.LOGIN && mainModule.userState==USER.ENQUIRER){
			login.initializeLogin();
			//login.initia
		}
		else if (mainModule.currentState == STATE.SEARCH && (mainModule.userState==USER.ENQUIRER || mainModule.userState==USER.HOST)){
			//System.out.println("HEREEE");
			search.initializeSearch();
		}
		else if (mainModule.currentState == STATE.CONTACT_US && mainModule.userState==USER.ENQUIRER){
			System.out.println("HEREEE");
			contact.initializeContact();
		}
		//added for HOST GUI:
		else if (mainModule.currentState == STATE.HOST_ACCOUNT && mainModule.userState==USER.HOST){
			System.out.println("HEREEE");
			System.out.println("STATE = "+mainModule.currentState+" USER = "+mainModule.userState);
			hostAccount.initializeHostAccount();
		}
		
	}
	
	public void editPropertyView(int id) {
		System.out.println("in editPropertyView");
		System.out.println("1----------"+mainModule.editPropertyState);
		System.out.println("editPropertyView--id from add facility = "+id);
		if (mainModule.userState == USER.HOST && mainModule.editPropertyState == EDITPROPERTY.EDIT_PROPERTY){
			editProperty.initializeEditProperty();
		}
		else if (mainModule.userState == USER.HOST && mainModule.editPropertyState == EDITPROPERTY.ADD_FACILITY){
			addFacility.initializeAddFacility();
		}
		else if (mainModule.userState == USER.HOST && mainModule.editPropertyState == EDITPROPERTY.EDIT_SLEEPING){
			editSleeping.initializeEditSleeping(id);
		}
		else if (mainModule.userState == USER.HOST && mainModule.editPropertyState == EDITPROPERTY.EDIT_BATHING){
			editBathing.initializeEditBathing(id);
		}
		else if (mainModule.userState == USER.HOST && mainModule.editPropertyState == EDITPROPERTY.EDIT_KITCHEN){
			editKitchen.initializeEditKitchen(id);
		}
		else if (mainModule.userState == USER.HOST && mainModule.editPropertyState == EDITPROPERTY.EDIT_UTILITY){
			editUtility.initializeEditUtility(id);
		}
		else if (mainModule.userState == USER.HOST && mainModule.editPropertyState == EDITPROPERTY.EDIT_LIVING){
			editLiving.initializeEditLiving(id);
		}
		else if (mainModule.userState == USER.HOST && mainModule.editPropertyState == EDITPROPERTY.EDIT_OUTDOORS){
			editOutdoors.initializeEditOutdoors(id);
		}
		else if (mainModule.userState == USER.HOST && mainModule.editPropertyState == EDITPROPERTY.EDIT_BEDROOM){
			editBedroom.initializeEditBedroom(id);
		}
		else if (mainModule.userState == USER.HOST && mainModule.editPropertyState == EDITPROPERTY.EDIT_BATHROOM){
			editBathroom.initializeEditBathroom(id);
		}
	}
 	
//	public static void SendEmail() { //doesn't work yet
//	
//	      String to = "egoodbrand1@sheffield.ac.uk";//change accordingly  
//	      String from = Contact.emailAddressTextField.getText();//change accordingly  
//	      String host = "localhost";//or IP address  
//	  
//	     //Get the session object  
//	      Properties properties = System.getProperties();  
//	      properties.setProperty("mail.smtp.host", host);  
//	      Session session = Session.getDefaultInstance(properties);  
//	  
//	     //compose the message  
//	      try{  
//	         MimeMessage message = new MimeMessage(session);  
//	         message.setFrom(new InternetAddress(from));  
//	         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
//	         message.setSubject("Ping");  
//	         message.setText("Hello, this is example of sending email  ");  
//	  
//	         // Send message  
//	         Transport.send(message);  
//	         System.out.println("message sent successfully....");  
//	  
//	      }catch (MessagingException mex) {mex.printStackTrace();}  
//	   }  
	 
	
}
