package HostGUI;

public class FacilitiesObject {

	private int facilities_id;
	private int utility_id;
	private int outdoors_id;
	private int kitchen_id;
	private int sleeping_id;
	private int bathing_id;
	private int living_id;

	/**
	 * @wbp.parser.entryPoint
	 */
	public FacilitiesObject(int facilities_id, int utility_id, int outdoors_id, int kitchen_id, int sleeping_id,
			int bathing_id, int living_id) {
		this.facilities_id = facilities_id;
		this.utility_id = utility_id;
		this.outdoors_id = outdoors_id;
		this.kitchen_id = kitchen_id;
		this.sleeping_id = sleeping_id;
		this.bathing_id = bathing_id;
		this.living_id = living_id;

	}

	public int getFacilitiesId() {
		return facilities_id;
	}

	public int getUtilityId() {
		return utility_id;
	}

	public int getOutdoorsId() {
		return outdoors_id;
	}

	public int getKitchenId() {
		return kitchen_id;
	}

	public int getSleepingId() {
		return sleeping_id;
	}

	public int getBathingId() {
		return bathing_id;
	}

	public int getLivingId() {
		return living_id;
	}

}