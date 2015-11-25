package model;


public class Advertisement {
	protected int advertisementID;
	protected String description;
	protected String url;
	
	public Advertisement(int advertisementID, String description, String url) {
		this.advertisementID = advertisementID;
		this.description = description;
		this.url = url;
	}
	
	
	public Advertisement(String Description, String url) {
		this.description = Description;
		this.url = url;
	}

	public int getAdvertisementID() {
		return advertisementID;
	}

	public void setAdvertisementID(int advertisementID) {
		this.advertisementID = advertisementID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}