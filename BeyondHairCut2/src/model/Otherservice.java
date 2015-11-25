package model;

public class Otherservice {
	protected int serviceId;
	protected String services;
	protected int duration;
	protected String about;
	protected int price;
	
	public Otherservice(int serviceId, String services,int duration, String about, int price) {
		this.serviceId = serviceId;
		this.services = services;
		this.duration = duration;
		this.about = about;
		this.price = price;
		
	}
	public Otherservice(int serviceId){
		this.serviceId = serviceId;
	}
			
			

	public Otherservice(String services,int duration, String about, int price) {
		this.services = services;
		this.duration = duration;
		this.about = about;
		this.price = price;
		
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}