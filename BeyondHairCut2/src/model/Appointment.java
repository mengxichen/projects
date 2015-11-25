package model;
import java.util.Date;

public class Appointment {
	protected int appointmentId;
	protected int storeId;
	protected Date Date;
	protected int CustomerId;
	protected int BarberId;
	protected String Style;
	
	public Appointment 
	(int storeId, Date Date, int CustomerId, int BarberId, 
	 String Style){
		this.storeId = storeId;
		this.Date = Date;
		//this.Duration = Duration;
		this.CustomerId = CustomerId;
		this.BarberId = BarberId;
		this.Style = Style;
	}
	
	public Appointment(int storeId, Date datetime, int customerid){
		this.storeId = storeId;
		this.Date = datetime;
		this.CustomerId = customerid;
	}
	
	public int getAppointmentId(){
		return appointmentId;
	}
	public void setAppointmentId(int a){
		this.appointmentId = a;
	}
	
	public int getStoreId(){
		return storeId;
	}
	
	public void setStoreId(int s){
		this.storeId = s;
	}
	
	public Date getDate(){
		return Date;
	}
	
	public int getBarberId() {
		return BarberId;
	}


	public void setBarberId(int barberId) {
		BarberId = barberId;
	}


	public String getStyle() {
		return Style;
	}

	public void setStyle(String style) {
		Style = style;
	}

	public void setCustomerId(int customerId) {
		CustomerId = customerId;
	}


	public void setDate(Date d){
		this.Date = d;
	}
	
	public int getCustomerId(){
		return CustomerId;
	}
	

}
