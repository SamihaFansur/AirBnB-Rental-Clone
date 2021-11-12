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
	
	//utility variables
	private boolean heating;
	private boolean washingMachine;
	private boolean fireExtinguisher;
	private boolean dryingMachine;
	private boolean smokeAlarm;
	private boolean firstAidKit;
	
	//outdoors variables
	private boolean freeOnSiteParking;
	private boolean onRoadParking;
	private boolean paidCarPark;
	private boolean patio;
	private boolean barbeque;
	
	//living variables
	private boolean wifi;
	private boolean television;
	private boolean satellite;
	private boolean streaming;
	private boolean dvdPlayer;
	private boolean boardGames;
	
	
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
	
	//getters and setters for utility facility

	public void setHeating(boolean heating) {
		this.heating = heating;
	}

	public boolean getHeating() {
		return heating;
	}
	public void setWashingMachine(boolean washingMaching) {
		this.washingMachine = washingMachine;
	}

	public boolean getWashingMaching() {
		return washingMachine;
	}
	public void setFireExtinguisher(boolean fireExtinguisher) {
		this.fireExtinguisher = fireExtinguisher;
	}

	public boolean getFireExtinguisher() {
		return fireExtinguisher;
	}
	public void setDryingMachine(boolean dryingMachine) {
		this.dryingMachine = dryingMachine;
	}

	public boolean getDryingMachine() {
		return dryingMachine;
	}
	public void setSmokeAlarm(boolean smokeAlarm) {
		this.smokeAlarm = smokeAlarm;
	}

	public boolean getSmokeAlarm() {
		return smokeAlarm;
	}
	public void setFirstAidKit(boolean firstAidKit) {
		this.firstAidKit= firstAidKit;
	}

	public boolean getFirstAidKit() {
		return firstAidKit;
	}
	
	//outdoors getters and setters
	public void setFreeOnSiteParking(boolean freeOnSiteParking) {
		this.freeOnSiteParking = freeOnSiteParking;
	}

	public boolean getFreeOnSiteParking() {
		return freeOnSiteParking;
	}
	
	public void setOnRoadParking(boolean onRoadParking) {
		this.onRoadParking = onRoadParking;
	}

	public boolean getOnRoadParking() {
		return onRoadParking;
	}
	
	public void setPaidCarPark(boolean paidCarPark) {
		this.paidCarPark = paidCarPark;
	}

	public boolean getPaidCarPark() {
		return paidCarPark;
	}
	
	public void setPatio(boolean patio) {
		this.patio = patio;
	}

	public boolean getPatio() {
		return patio;
	}
	
	public void setBarbeque(boolean barbeque) {
		this.barbeque = barbeque;
	}

	public boolean getBarbeque() {
		return barbeque;
	}
	
	//living getters and setters
	
	public void setWifi(boolean wifi) {
		this.wifi= wifi;
	}

	public boolean getWifi() {
		return wifi;
	}
	
	public void setTelevision(boolean television) {
		this.television = television;
	}

	public boolean getTelevision() {
		return television;
	}
	
	public void setSatellite(boolean satellite) {
		this.satellite = satellite;
	}

	public boolean getSatellite() {
		return satellite;
	}
	
	public void setStreaming(boolean streaming) {
		this.streaming = streaming;
	}

	public boolean getStreaming() {
		return streaming;
	}
	
	public void setDvdPlayer(boolean dvdPlayer) {
		this.dvdPlayer = dvdPlayer;
	}

	public boolean getDvdPlayer() {
		return dvdPlayer;
	}
	
	public void setBoardGames(boolean boardGames) {
		this.boardGames = boardGames;
	}

	public boolean getBoardGames() {
		return boardGames;
	}
}
