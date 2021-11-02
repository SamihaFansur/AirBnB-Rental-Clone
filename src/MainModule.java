import java.sql.Connection;
import java.sql.DriverManager;

//Remove GUI mainModule later, test webhook comment
public class MainModule {

	private Model model;
	private Controller controller;
	private SelfRegistration selfRegistration;
	private Homepage homepage;
	
	
	//use enum to register the state of the system
	public enum STATE{
		HOMEPAGE,
		SELF_REGISTRATION,
		//could have others that correspond to new pages.
		HELP
	}
	
	public STATE currentState = STATE.SELF_REGISTRATION;
	
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
	
	public static void main (String [] args){
		/*
		Connection con = null; // a Connection object
	    try {
	        con = DriverManager.getConnection(
	        "jdbc:mysql://stusql.dcs.shef.ac.uk/team018", "team018", "7854a03f");
	        // use the open connection
	        // for several queries
	    }
	    catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    finally {
	        if (con != null) con.close();
	    }
		*/
		
		MainModule mainModule = new MainModule();
		
		//creating the model
		Model model = new Model();
		//creating an instance of SelfRegistration class
		SelfRegistration selfRegistration = new SelfRegistration();
		//some test code
		System.out.println("testing");
		//creating an instance of Homepage class
		Homepage homepage = new Homepage();
		//some test code
		System.out.println("reached here");
		//creating the controller
		Controller controller = new Controller(mainModule, model, homepage, selfRegistration);
		//calling the draw method in the controller:
		controller.drawNewView();
		
		
		}
	
}
