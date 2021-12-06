package HostGUI;

/*
 * This class constructs Review objects and is the helper class
 * for other classes relating to Reviews.
 */
public class ReviewObject {

	private int review_id;
	private int property_id;
	private Double accuracy;
	private Double location;
	private Double valueForMoney;
	private Double communication;
	private Double cleanliness;
	private String description;

	//Constructor for review objects
	public ReviewObject(int review_id, int property_id, Double accuracy, Double location, Double valueForMoney,
			Double communication, Double cleanliness, String description) {
		this.review_id = review_id;
		this.property_id = property_id;
		this.accuracy = accuracy;
		this.location = location;
		this.valueForMoney = valueForMoney;
		this.communication = communication;
		this.cleanliness = cleanliness;
		this.description = description;
	}

	//Getters and setters for information relating to reviews
	public int getReview_id() {
		return review_id;
	}

	public int getProperty_id() {
		return property_id;
	}

	public Double getAccuracy() {
		return accuracy;
	}

	public Double getLocation() {
		return location;
	}

	public Double getValueForMoney() {
		return valueForMoney;
	}

	public Double getCommunication() {
		return communication;
	}

	public Double getCleanliness() {
		return cleanliness;
	}

	public String getDescription() {
		return description;
	}

}