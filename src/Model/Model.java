package Model;

public class Model {

	private int id;
	private String firstName;
	private String surname;
	private String email;
	private int mobileNumber;
	private Boolean guest;
	private Boolean host;
	
	public void setId(int id) {
		this.id=id;
	}

	public int getId() {
		return id;
	}
	
	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setSurname(String surname) {
		this.surname=surname;
	}

	public String getSurame() {
		return surname;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}

	public String getEmail() {
		return email;
	}
	
	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber=mobileNumber;
	}
	
	public int getMobileNumber() {
		return mobileNumber;
	}

	public void setGuest(Boolean guest) {
		this.guest=guest;
	}
	
	public Boolean getGuest() {
		return guest;
	}

	public void setHost(Boolean host) {
		this.host=host;
	}
    
	public Boolean getHost() {
		return host;
	}
	
	
	
}
