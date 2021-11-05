package Controller;
import GUI.*;
import Model.Model;

public class Controller extends MainModule{

	private Model model;
	private MainModule mainModule;
	// views
	private Homepage homepage;
	private Register register;
	private Search search;
	private Account account;
	private Login login;
	
	
	public Controller(MainModule mainModule, Model model, Homepage homepage, Register register, Search search, Account account, Login login) {
		System.out.println("now in controller");
		this.mainModule=mainModule;
		this.model=model;
		//views
		this.homepage=homepage;
		this.register=register;
		this.search=search;
		this.account=account;
		this.login=login;
	}
	
	public static void main(String [] args) {
	}
	
	public void setAccountId(int id) {
		model.setId(id);
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
	
	public void setMobileNumber(int mobileNumber) {
		model.setMobileNumber(mobileNumber);
	}
	
	public void setGuest(Boolean guest) {
		model.setGuest(guest);
	}
	
	public void setHost(Boolean host) {
		model.setHost(host);
	}
	
	//checking the state of the system:
	public void drawNewView() {
		System.out.println("in drawNewView");
		System.out.println("1----------"+mainModule.currentState);
		//checking if we're in the homepage state.
		if (mainModule.currentState == STATE.HOMEPAGE){
			//call the function to update the JPanel in homepage
			homepage.initializeHomePage();
		}
		//checking if in SELF_REGISTRATION state, then update the JPanel in SELF_REGISTRATION 
		// (at the moment the window is made in SELF_REGISTRATION)
		else if (mainModule.currentState == STATE.SELF_REGISTRATION){
			//some test code
			//System.out.println("in the draw controller method here");
			register.initializeRegister();
			//some test code
			//System.out.println("dfv sdvdvdvdr method here");
		}
		else if (mainModule.currentState == STATE.ACCOUNT){
			account.initialize();
		}
		else if (mainModule.currentState == STATE.LOGIN){
			
			//login.initia
		}
		else if (mainModule.currentState == STATE.SEARCH){
			System.out.println("HEREEE");
			search.initializeSearch();
		}
	
	
		
	}
	
	
}
