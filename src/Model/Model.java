package Model;

public class Model {

	private String title;
	private String firstName;
	private String surname;
	private String email;	
	private String password;
	private String houseNameNum;
	private String streetName;
	private String city;
	private String postcode;
	private String accType;
	private String mobileNumber;
	
	//edit property variables
	private String editPropertyHouseNameNum;
	private String editPropertyStreetName;
	private String editPropertyCity;
	private String editPropertyPostcode;
	
	public void setTitle(String title) {
		this.title=title;
	}

	public String getTitle() {
		return title;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getSurname() {
		return surname;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
		
	public void setHouseNameNum(String houseNameHum) {
		this.houseNameNum = houseNameHum;
	}

	public String getHouseNameNum() {
		return houseNameNum;
	}

	public void setStreetName(String streetName) {
		this.streetName= streetName;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}
	
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPostcode() {
		return postcode;
	}
	
	public void setAccountType(String accType) {
		this.accType = accType;
	}

	public String getAccountType() {
		return accType;
	}
	
	
	//edit property getters and setters
	public void setEditPropertyHouseNameNum(String editPropertyHouseNameHum) {
		this.editPropertyHouseNameNum = editPropertyHouseNameHum;
	}

	public String getEditPropertyHouseNameNum() {
		return editPropertyHouseNameNum;
	}

	public void setEditPropertyStreetName(String editPropertyStreetName) {
		this.editPropertyStreetName= editPropertyStreetName;
	}

	public String getEditPropertyStreetName() {
		return editPropertyStreetName;
	}

	public void setEditPropertyCity(String editPropertyCity) {
		this.editPropertyCity = editPropertyCity;
	}

	public String getEditPropertyCity() {
		return editPropertyCity;
	}
	
	public void setEditPropertyPostcode(String editPropertyPostcode) {
		this.editPropertyPostcode = editPropertyPostcode;
	}

	public String getEditPropertyPostcode() {
		return editPropertyPostcode;
	}
	
	
	
}
