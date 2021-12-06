package GuestGUI;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/*
 * This class constructs Booking objects and is the helper class
 * for other classes relating to Bookings.
 */
public class BookingObject {

	private int booking_id;
	private int property_id;
	private double serviceCharge;
	private double cleaningCharge;
	private double overallPrice;
	private int noOfNights;
	private int review_id;
	private String provisional;
	private String startDate;
	private String endDate;

	
	//Constructor for Booking Objects
	public BookingObject(int booking_id, int property_id, double serviceCharge, double cleaningCharge,
						double overallPrice, int noOfNights, String provisional, String startDate, 
						String endDate) {
		this.booking_id = booking_id;
		this.property_id = property_id;
		this.serviceCharge = serviceCharge;
		this.cleaningCharge = cleaningCharge;
		this.overallPrice = overallPrice;
		this.noOfNights = noOfNights;
		this.provisional = provisional;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	//Getters and Setters for information relating to Booking objects.
	
	public int getBooking_id() {
		return booking_id;
	}

	public int getProperty_id() {
		return property_id;
	}

	public int getReview_id() {
		return review_id;
	}

	public double getServiceCharge() {
		return serviceCharge;
	}

	public double getCleaningCharge() {
		return cleaningCharge;
	}
	
	public double getOverallPrice() {
		return overallPrice;
	}
	
	public int getNoOfNights() {
		return noOfNights;
	}

	public String getProvisional() {
		return provisional;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

}