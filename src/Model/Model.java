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
	private int noOfBathrooms, bathroomId;
	
	//sleeping variables
	private boolean bedLinen, towels;
	private int noOfBedrooms, bedroomId;
	
	//bedType variables
	private boolean bed1, bed2 ;
	private String bed1Type, bed2Type;
	private int bed1Capacity, bed2Capacity;
	
	//facilities variables
	private int kitchenId, sleepingId, bathingId, utilityId, livingId, outdoorsId;
	
	//chargeband variables
	private String startDate, endDate;
	private double pricePerNight, serviceCharge, cleaningCharge;
	
	
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
	public void setBathroomId(int bathroomId) {
		this.bathroomId = bathroomId;
	}

	public int getBathroomId() {
		return bathroomId;
	}
	
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
	

	//sleeping getters and setters

	public void setBedLinen(boolean bedLinen) {
		this.bedLinen = bedLinen;
	}

	public boolean getBedLinen() {
		return bedLinen;
	}
	
	public void setTowels(boolean towels) {
		this.towels = towels;
	}

	public boolean getTowels() {
		return towels;
	}
	
	public void setNoOfBedrooms(int noOfBedrooms) {
		this.noOfBedrooms= noOfBedrooms;
	}

	public int getNoOfBedrooms() {
		return noOfBedrooms;
	}
	
	//bedType getters and setters
	public void setBedroomId(int bedroomId) {
		this.bedroomId = bedroomId;
	}

	public int getBedroomId() {
		return bedroomId;
	}

	public void setBed1(boolean bed1) {
		this.bed1 = bed1;
	}

	public boolean getBed1() {
		return bed1;
	}
	
	public void setBed1Type(String bed1Type) {
		this.bed1Type = bed1Type;
	}

	public String getBed1Type() {
		return bed1Type;
	}
	
	public void setBed1Capacity(int bed1Capacity) {
		this.bed1Capacity = bed1Capacity;
	}

	public int getBed1Capacity() {
		return bed1Capacity;
	}

	public void setBed2(boolean bed2) {
		this.bed2 = bed2;
	}

	public boolean getBed2() {
		return bed2;
	}
	
	public void setBed2Type(String bed2Type) {
		this.bed2Type = bed2Type;
	}

	public String getBed2Type() {
		return bed2Type;
	}
	
	public void setBed2Capacity(int bed2Capacity) {
		this.bed2Capacity = bed2Capacity;
	}

	public int getBed2Capacity() {
		return bed2Capacity;
	}
	/*
	private int sleepingId;
	private int bathingId;
	private int utilityId;
	private int livingId;
	private int outdoorsId;
	*/
	public void setCurrentKitchedId(int kitchedId) {
		this.kitchenId=kitchedId;
	}
	public int getCurrentKitchedId() {
		return kitchenId;
	}
	public void setCurrentBathingId(int bathingId) {
		this.bathingId=bathingId;
	}
	public int getCurentBathingId() {
		return bathingId;
	}
	public void setCurrentUtilityId(int utilityId) {
		this.utilityId=utilityId;
	}
	public int getCurrentUtilityId() {
		return utilityId;
	}
	public void setCurrentLivingId(int livingId) {
		this.livingId=livingId;
	}
	public int getCurrentLivingId() {
		return livingId;
	}
	public void setCurrentOutdoorsId(int outdoorsId) {
		this.outdoorsId=outdoorsId;
	}
	public int getCurrentOutdoorsId() {
		return outdoorsId;
	}
	public void setCurrentSleepingId(int sleepingId) {
		this.sleepingId=sleepingId;
	}
	public int getCurrentSleepingId() {
		return sleepingId;
	}
	
	//chargeband getts and setters
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}
	public double getPricePerNight() {
		return pricePerNight;
	}
	public void setServiceCharge(double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public double getServiceCharge() {
		return serviceCharge;
	}
	public void setCleaningCharge(double cleaningCharge) {
		this.cleaningCharge = cleaningCharge;
	}
	public double getCleaningCharge() {
		return cleaningCharge;
	}
	
}
