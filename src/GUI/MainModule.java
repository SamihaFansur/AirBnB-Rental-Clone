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
//	private Homepage homepage;
	private Account Account;
	private Homepage homepage;
	private Login login;
	private Search search;
	
	
	//use enum to register the state of the system
	public enum STATE{
		HOMEPAGE,
		SELF_REGISTRATION,
		ACCOUNT,
		LOGIN,
		SEARCH,
		//could have others that correspond to new pages.
		CONTACT_US
	}
	
	public STATE currentState = STATE.HOMEPAGE;
	
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
		//some test code
		System.out.println("reached here");
		//creating the controller
		controller = new Controller(mainModule, model, homepage, register, search, account, login);
		//calling the draw method in the controller:
		System.out.println("-------controller made");
		controller.drawNewView();
		
		}
	
	
}
