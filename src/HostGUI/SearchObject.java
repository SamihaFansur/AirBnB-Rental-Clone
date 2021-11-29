package HostGUI;

public class SearchObject {

	private int property_id, guestCapacity;
	private String shortName, placeName, hostName;
    private boolean superhost, breakfast;
	    
//	    private String startDate;
//	    private String endDate;

	public SearchObject(int property_id, String shortName, int guestCapacity, 
						String placeName, 
//						String hostName, 
						boolean breakfast
//						boolean superhost
						) {

		this.property_id = property_id;
		this.shortName = shortName;
		this.guestCapacity = guestCapacity;
        this.placeName = placeName;
//        this.hostName = hostName;
//        this.superhost = superhost;
        this.breakfast = breakfast;

	}

	public int getPropertyId() {
		return property_id;
	}

	public String getShortName() {
		return shortName;
	}

	public int getGuestCapacity() {
		return guestCapacity;
	}

    public String getPlaceName() {
        return placeName;
    }

    public String getHostName() {
        return hostName;
    }

    public boolean getSuperhost() {
        return superhost;
    }

    public boolean getBreakfast() {
        return breakfast;
    }
    
    

}