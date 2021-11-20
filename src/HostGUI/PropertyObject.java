package HostGUI;


public class PropertyObject {
	
	    private int property_id;
	    private String houseNameNumber;
	    private String shortName;
	    private String description;
	    private String postcode;
	    private int guestCapacity;
	    private int host_id;
	    
	    
	    
	    /**
	     * @wbp.parser.entryPoint
	     */
	    public PropertyObject(int property_id, String houseNameNumber, String postcode, int host_id, String description, String shortName, int guestCapacity)
	    {
	        this.property_id = property_id;
	        this.houseNameNumber = houseNameNumber;
	        this.postcode = postcode;
	        this.host_id = host_id;
	        this.description = description;
	        this.shortName = shortName;
	        this.guestCapacity = guestCapacity;
	        
	    }
	    
	    public int getPropertyId()
	    {
	        return property_id;
	    }
	    
	    public String getHouseNameNumber()
	    {
	        return houseNameNumber;
	    }
	    
	    public String getPostcode()
	    {
	        return postcode;
	    }
	    public int getHostId()
	    {
	        return host_id;
	    }
	    
	    public String getDescription()
	    {
	        return description;
	    }
	    public String getShortName()
	    {
	        return shortName;
	    }
	    public int getGuestCapacity()
	    {
	        return guestCapacity;
	    }
}