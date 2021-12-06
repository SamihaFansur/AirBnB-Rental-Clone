package HostGUI;

public class ChargeBand {
	private int property_id;
	private String startDate, endDate;
	private double pricePerNight, serviceCharge, cleaningCharge, totalPricePerNight;

	public ChargeBand(int property_id, String startDate, String endDate, double pricepp, double serviceCharge,
			double cleaningCharge, double totalPricepp) {
		this.property_id = property_id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.pricePerNight = pricepp;
		this.serviceCharge = serviceCharge;
		this.cleaningCharge = cleaningCharge;
		this.totalPricePerNight = totalPricepp;
	}
	public int getPropertyId() {
		return property_id;
	}
	
	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public double getPricePerNight() {
		return pricePerNight;
	}

	public double getServiceCharge() {
		return serviceCharge;
	}

	public double getCleaningCharge() {
		return cleaningCharge;
	}

	public double getTotalPricePerNight() {
		return totalPricePerNight;
	}
}
