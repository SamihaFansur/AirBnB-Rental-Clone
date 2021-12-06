package HostGUI;

public class Bathroom {
	private int bathing_id, bathType_id;
	private boolean toilet, bath, shower, shared;

	public Bathroom(int bathing_id, int bathType_id, boolean toilet, boolean bath, boolean shower, boolean shared) {
		this.bathing_id = bathing_id;
		this.bathType_id = bathType_id;
		this.toilet = toilet;
		this.bath = bath;
		this.shower = shower;
		this.shared = shared;
	}

	public int getBathing_id() {
		return bathing_id;
	}

	public int getBathType_id() {
		return bathType_id;
	}

	public boolean getToilet() {
		return toilet;
	}

	public boolean getBath() {
		return bath;
	}

	public boolean getShower() {
		return shower;
	}

	public boolean getShared() {
		return shared;
	}
}
