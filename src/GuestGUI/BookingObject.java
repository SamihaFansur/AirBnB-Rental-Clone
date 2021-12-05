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
	private int host_id;
	private int guest_id;
	private int review_id;
	private String provisional;
	private Double totalPrice;
	private String startDate;
	private String endDate;

	
	//Constructor for Booking Objects
	public BookingObject(int booking_id, int property_id, int host_id, int guest_id, String provisional,
			Double totalPrice, String startDate, String endDate) {
		this.booking_id = booking_id;
		this.property_id = property_id;
		this.host_id = host_id;
		this.guest_id = guest_id;
		this.provisional = provisional;
		this.totalPrice = totalPrice;
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

	public int getHost_id() {
		return host_id;
	}

	public int getGuest_id() {
		return guest_id;
	}

	public int getReview_id() {
		return review_id;
	}

	public String getProvisional() {
		return provisional;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

}