package Model;

public class Model {

	//account variables
	private String title, firstName, surname, email, password, houseNameNum, 
					streetName, city, postcode, accType, mobileNumber;
	
	//edit property variables
	private String editPropertyHouseNameNum, editPropertyStreetName, editPropertyCity, editPropertyPostcode;
	
	//utility variables
	private boolean heating, washingMachine, fireExtinguisher, dryingMachine, smokeAlarm, firstAidKit;
	
	//outdoors variables
	private boolean freeOnSiteParking, onRoadParking, paidCarPark, patio, barbeque;
	
	//living variables
	private boolean wifi, television, satellite, streaming, dvdPlayer, boardGames;
	
	//kitchen variables
	private boolean refrigerator, microwave, oven, stove, dishwasher, tableware, cookware, basicProvisions;
	
	//bathtype variables
	private boolean toilet, bath, shower, shared;
	
	//bathing variables
	private boolean hairDryer, toiletPaper;
	private int noOfBathrooms;
	
	//account getters and setters
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
	public void setWashingMachine(boolean washingMachine) {
		this.washingMachine = washingMachine;
	}

	public boolean getWashingMachine() {
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
	
	//kitchen getters and setters
	public void setRefrigerator(boolean refrigerator) {
		this.refrigerator = refrigerator;
	}

	public boolean getRefrigerator() {
		return refrigerator;
	}

	public void setMicrowave(boolean microwave) {
		this.microwave = microwave;
	}

	public boolean getMicrowave() {
		return microwave;
	}

	public void setOven(boolean oven) {
		this.oven = oven;
	}

	public boolean getOven() {
		return oven;
	}

	public void setStove(boolean stove) {
		this.stove = stove;
	}

	public boolean getStove() {
		return stove;
	}

	public void setDishwasher(boolean dishwasher) {
		this.dishwasher = dishwasher;
	}

	public boolean getDishwasher() {
		return dishwasher;
	}

	public void setTableware(boolean tableware) {
		this.tableware = tableware;
	}

	public boolean getTableware() {
		return tableware;
	}

	public void setCookware(boolean cookware) {
		this.cookware = cookware;
	}

	public boolean getCookware() {
		return cookware;
	}

	public void setBasicProvisions(boolean basicProvisions) {
		this.basicProvisions = basicProvisions;
	}

	public boolean getBasicProvisions() {
		return basicProvisions;
	}
	
	//bathtype getters and setters

	public void setToilet(boolean toilet) {
		this.toilet = toilet;
	}

	public boolean getToilet() {
		return toilet;
	}

	public void setBath(boolean bath) {
		this.bath = bath;
	}

	public boolean getBath() {
		return bath;
	}

	public void setShower(boolean shower) {
		this.shower = shower;
	}

	public boolean getShower() {
		return shower;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}

	public boolean getShared() {
		return shared;
	}
	
	//bathing getters and setters

	public void setHairDryer(boolean hairDryer) {
		this.hairDryer = hairDryer;
	}

	public boolean getHairDryer() {
		return hairDryer;
	}
	
	public void setToiletPaper(boolean toiletPaper) {
		this.toiletPaper = toiletPaper;
	}

	public boolean getToiletPaper() {
		return toiletPaper;
	}
	
	public void setNoOfBathrooms(int noOfBathrooms) {
		this.noOfBathrooms= noOfBathrooms;
	}

	public int getNoOfBathrooms() {
		return noOfBathrooms;
	}
}
