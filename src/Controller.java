

public class Controller extends MainModule{

	private Model model;
	private MainModule mainModule;
	// views
	private Homepage homepage;
	private SelfRegistration selfRegistration;
	
	
	public Controller(MainModule mainModule, Model model, Homepage homepage, SelfRegistration selfRegistration) {
		System.out.println("now in controller");
		this.mainModule=mainModule;
		this.model=model;
		//views
		this.homepage=homepage;
		this.selfRegistration=selfRegistration;
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
		//checking if we're in the homepage state.
		if (mainModule.currentState == STATE.HOMEPAGE){
			//call the function to update the JPanel in homepage
		}
		//checking if in SELF_REGISTRATION state, then update the JPanel in SELF_REGISTRATION 
		// (at the moment the window is made in SELF_REGISTRATION)
		else if (mainModule.currentState == STATE.SELF_REGISTRATION){
			//some test code
			System.out.println("in the draw controller method here");
			selfRegistration.updateView();
			//some test code
			System.out.println("dfv sdvdvdvdr method here");
		}
	
		
	}
	
	
}
