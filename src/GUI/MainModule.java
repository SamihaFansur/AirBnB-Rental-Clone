package GUI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Controller.Controller;
import GuestGUI.BookProperty;
import GuestGUI.Bookings;
import GuestGUI.GuestAccount;
import Model.Model;
import HostGUI.*;
import HostGUI.AddFacility;
import HostGUI.EditBathing;
import HostGUI.EditBathroom;
import HostGUI.EditBedroom;
import HostGUI.EditKitchen;
import HostGUI.EditLiving;
import HostGUI.EditOutdoors;
import HostGUI.AddProperty;
import HostGUI.EditSleeping;
import HostGUI.EditUtility;
import HostGUI.Reviews;
import HostGUI.HostAccount;
import HostGUI.Properties;



//Remove GUI mainModule later, test webhook comment
public class MainModule {

	private Model model;
	public static Controller controller;
	private Register register;
	private Homepage homepage;
	private Login login;
	private Search search;
	private Contact contact;
	
	//hostGUI
	private AddFacility addFacility;
	private EditBathing editBathing;
	private EditBathroom editBathroom;
	private EditBedroom editBedroom;
	private EditKitchen editKitchen;
	private EditLiving editLiving;
	private EditOutdoors editOutdoors;
	private AddProperty editProperty;
	private EditSleeping editSleeping;
	private EditUtility editUtility;
	private Facilities facilities;
	private HostAccount hostAccount;
	private Properties properties;
	private EditAccount editAccount;
	private Reviews reviews;
	private ChargeBands chargeBands;
	private GuestAccount guestAccount;
	private Bookings bookings;
	private BookProperty bookProperty;
	
	
	//Page user is on
	public enum STATE{
		HOMEPAGE,
		SELF_REGISTRATION,
		/*property class also not being used*/
		LOGIN,
		SEARCH,
		//could have others that correspond to new pages.
		CONTACT_US,
		// pages for host gui:
		LOGOUT,
		
		
		/* new */
		// edit host account details
		// properties - showing the list of properties 
		// edit property
		// provisional bookings page - makes list of all provisional booking. Reeject/Accept booking here
		// active bookings page.
		
		//for host GUI:
//		PROPERTY_EDIT,
//		ADD_FACILITY,
//		EDIT_BATHING,
//		EDIT_BATHROOM,
//		EDIT_BEDROOM,
//		EDIT_KITCHEN,
//		EDIT_LIVING,
//		EDIT_OUTDOORS,
//		EDIT_SLEEPING,
//		EDIT_UTILITY,
		FACILTIES, 
		HOST_ACCOUNT,
		GUEST_ACCOUNT,
		PROPERTIES,
		EDIT_ACCOUNT,
		
		
	}
	
	public enum EDITPROPERTY {
		EDIT_PROPERTY,
		ADD_FACILITY,
		EDIT_SLEEPING,
		EDIT_BATHING,
		EDIT_KITCHEN,
		EDIT_LIVING,
		EDIT_OUTDOORS,
		EDIT_UTILITY,
		EDIT_BATHROOM,
		EDIT_BEDROOM,
		PROPERTIES,
		REVIEWS,
		FACILITIES,
		CHARGEBANDS,
		EDIT_PROPERTY_FACILITIES,
		BOOKINGS,
		BOOK_PROPERTY
		
	}
	
	
	public enum USER {
		// for checking who's logged in:
		ENQUIRER,
		HOST,
		GUEST
	}
	
	public STATE currentState = STATE.HOMEPAGE;
	public USER userState = USER.ENQUIRER;
	public EDITPROPERTY editPropertyState = EDITPROPERTY.EDIT_PROPERTY;
	
	public static void main (String [] args) {
	
		MainModule mainModule = new MainModule();
		
		//creating the model
		Model model = new Model();
		//creating an instance of SelfRegistration class
		Register register = new Register(mainModule, controller, model);
		//some test code
		System.out.println("testing");
		//creating an instance of Homepage class
		Homepage homepage = new Homepage(mainModule, controller, model);
		//creating an instance of login class
		Login login = new Login(mainModule, controller, model);
		//creating an instance of search class
		Search search = new Search(mainModule, controller, model);
		//creating instance of contact class
		Contact contact = new Contact(mainModule, controller, model);
		
		//Objects for Guest GUI
		Bookings bookings = new Bookings(mainModule, controller, model);
		BookProperty bookProperty = new BookProperty(mainModule, controller, model);
		
		//Objects for Host GUI:
		AddFacility addFacility = new AddFacility(mainModule, controller, model);
		EditBathing editBathing = new EditBathing(mainModule, controller, model);
		EditBathroom editBathroom = new EditBathroom(mainModule, controller, model);
		EditBedroom editBedroom = new EditBedroom(mainModule, controller, model);
		EditKitchen editKitchen = new EditKitchen(mainModule, controller, model);
		EditLiving editLiving = new EditLiving(mainModule, controller, model);
		EditOutdoors editOutdoors = new EditOutdoors(mainModule, controller, model);
		AddProperty editProperty = new AddProperty(mainModule, controller, model);
		EditSleeping editSleeping = new EditSleeping(mainModule, controller, model);
		EditUtility editUtility = new EditUtility(mainModule, controller, model);
		Facilities facilities = new Facilities(mainModule, controller, model);
		EditAPropertysFacilities editAPropertysFacilities = new EditAPropertysFacilities(mainModule, controller, model);
		HostAccount hostAccount = new HostAccount(mainModule, controller, model);
		GuestAccount guestAccount = new GuestAccount(mainModule, controller, model);
		Properties properties = new Properties(mainModule, controller, model);
		EditAccount editAccount = new EditAccount(mainModule, controller, model);
		Reviews reviews = new Reviews(mainModule, controller, model);	
		ChargeBands chargeBands = new ChargeBands(mainModule, controller, model);
		
		//some test code
		System.out.println("reached here");
		//creating the controller
		controller = new Controller(mainModule, model, homepage, register, search, login, contact, addFacility, editBathing, editBathroom, editBedroom, editKitchen, editLiving, editOutdoors, editProperty, editSleeping, editUtility, facilities, editAPropertysFacilities, hostAccount, properties, editAccount, reviews, chargeBands, guestAccount, bookings, bookProperty);
		//calling the draw method in the controller:
		System.out.println("-------controller made");
		controller.drawNewView();
		
		}
	
	
}
