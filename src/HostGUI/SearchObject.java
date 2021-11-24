package HostGUI;


public class SearchObject {
	
	    private int property_id;
//	    private int address_id;
	    private String houseNameNumber;
	    private String postcode;
	    private String shortName;
	    private String description;
	    private int guestCapacity;
//	    private float minPrice;
//	    private float maxPrice;
//	    private String placeName;
//	    private String startDate;
//	    private String endDate;
	    
	    /**
	     * @wbp.parser.entryPoint
	     */
	    public SearchObject(int property_id,String houseNameNumber, String postcode, String description, 
    						String shortName, int guestCapacity
//    						,float minPrice, float maxPrice, 
//    						String placeName, String startDate, String endDate
    						) {
	    	
	        this.property_id = property_id;
	        this.houseNameNumber = houseNameNumber;
	        this.postcode = postcode;
	        this.description = description;
	        this.shortName = shortName;
	        this.guestCapacity = guestCapacity;
//	        this.minPrice = minPrice;
//	        this.maxPrice = maxPrice;
//	        this.placeName = placeName;
//	        this.startDate = startDate;
//	        this.endDate = endDate;
	        
	    }
	    
	    public int getPropertyId() {
	        return property_id;
	    }
	    
	    public String getHouseNameNumber() {
	        return houseNameNumber;
	    }
	    
	    public String getPostcode() {
	        return postcode;
	    }

	    public String getDescription() {
	        return description;
	    }
	    
	    public String getShortName() {
	        return shortName;
	    }
	    
	    public int getGuestCapacity() {
	        return guestCapacity;
	    }
	    
//	    public float getMinPrice() {
//	        return minPrice;
//	    }
//	    
//	    public float getMaxPrice() {
//	        return maxPrice;
//	    }
	    
//	    public String getPlaceName() {
//	        return placeName;
//	    }
//	    
//	    public String getStartDate() {
//	        return startDate;
//	    }
//	    
//	    public String getEndDate() {
//	        return endDate;
//	    }
}