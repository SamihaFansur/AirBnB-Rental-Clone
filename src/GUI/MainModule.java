package GUI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Controller.Controller;
import Model.Model;


//Remove GUI mainModule later, test webhook comment
public class MainModule {

	private Model model;
	public static Controller controller;
	private Register register;
	private Account Account;
	private Homepage homepage;
	private Login login;
	private Search search;
	private Contact contact;
	
	//use enum to register the state of the system
	public enum STATE{
		HOMEPAGE,
		SELF_REGISTRATION,
		ACCOUNT,
		LOGIN,
		SEARCH,
		//could have others that correspond to new pages.
		CONTACT_US,
		// pages for host gui:
		
		/* new */
		// edit host account details
		// properties - showing the list of properties 
		// edit property
		// provisional bookings page - makes list of all provisional booking. Reeject/Accept booking here
		// active bookings page.
		
		// for checking who's logged in:
		ENQUIRER,
		HOST,
		GUEST
	}
	
	public STATE currentState = STATE.HOMEPAGE;
	public STATE userState = STATE.ENQUIRER;
	
	public MainModule() {
		/*
		this.currentState = STATE.SELF_REGISTRATION;
		Model model = new Model();
		SelfRegistration selfRegistration = new SelfRegistration();
		System.out.println("testing");
		Homepage homepage = new Homepage();
		System.out.println("reached here");
		System.out.println("sffvdf");
		*/
	}
	
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
		Account account = new Account(mainModule, controller, model);
		//creating an instance of search class
		Search search = new Search(mainModule, controller, model);
		//creating instance of contact class
		Contact contact = new Contact(mainModule, controller, model);
		
		//Objects for Host GUI:
		
								
		
		
		//some test code
		System.out.println("reached here");
		//creating the controller
		controller = new Controller(mainModule, model, homepage, register, search, account, login, contact);
		//calling the draw method in the controller:
		System.out.println("-------controller made");
		controller.drawNewView();
		
		}
	
	
}
