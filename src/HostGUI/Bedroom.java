package HostGUI;
/*
 * This class constructs Bedroom objects and is the helper class
 * for other classes relating to Bedrooms.
 */
public class Bedroom {
 private int sleeping_id;
 private int bedType_id;
 private int peopleInBedroom, bed1People, bed2People;
 private boolean bed1, bed2;
 private String bed1ChoiceField, bed2ChoiceField;
 
 public Bedroom(int sleeping_id, int bedType_id, int peopleInBedroom, boolean bed1, 
		 String bed1ChoiceField, int bed1People, boolean bed2, String bed2ChoiceField, int bed2P) {
	 this.sleeping_id = sleeping_id;
	 this.bedType_id = bedType_id;
	 this.peopleInBedroom = peopleInBedroom;
	 this.bed1 = bed1;
	 this.bed1ChoiceField = bed1ChoiceField;
	 this.bed1People = bed1People;
	 this.bed2 = bed2;
	 this.bed2ChoiceField = bed2ChoiceField; 
	 this.bed2People = bed2P;

}

	//Getters and setters for information relating to a bedroom


	public int getbedType_id() {
		return bedType_id;
	}

	public int getpeopleInBedroom() {
		return peopleInBedroom;
	}

	public boolean getbed1() {
		return bed1;
	}

	public String getbed1ChoiceField() {
		return bed1ChoiceField;
	}
	public int getbed1People() {
		return bed1People;
	}
	public boolean  getbed2() {
		return bed2;
	}

	public String getbed2ChoiceField() {
		return bed2ChoiceField;
	}
	public int getbed2People() {
		return bed2People;
	}
}