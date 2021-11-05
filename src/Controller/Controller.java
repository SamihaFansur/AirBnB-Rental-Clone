package Controller;
import GUI.Account;
import GUI.Homepage;
import GUI.Login;
import GUI.MainModule;
import GUI.Register;
import GUI.Search;
import GUI.MainModule.STATE;
import Model.*;

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
	

	public void setAddressLine1(String addressLine1){
		model.setAddressLine1(addressLine1);
	}

	public void setHouseNameNumber(String houseNameNumber){
		model.setHouseNameNum(houseNameNumber);
	}

	public void setPostcode(String postcode){
		model.setPostcode(postcode);
	}

	public void setAccountType(String accountType){
		model.setAccountType(accountType);
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
			login.initializeLogin();
			//login.initia
		}
		else if (mainModule.currentState == STATE.SEARCH){
			System.out.println("HEREEE");
			search.initializeSearch();
		}
	
	
		
	}
	
	
}
