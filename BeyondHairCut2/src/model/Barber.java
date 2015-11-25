package model;

import java.sql.Blob;
public class Barber {
	protected int barberId;
	protected String firstName;
	protected String lastName;
	protected Blob picture;
	protected String about;
	protected int rating;
    protected String gender;
    protected int storeId;
	
    
    
	public Barber(int barberId, String firstName,String lastName, Blob picture,
			String about, int rating, String gender,int storeId) {
		this.barberId = barberId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.picture = picture;
		this.about = about;
		this.rating = rating;
		this.gender= gender;
		this.storeId = storeId;
		
	}
	
    public Barber(int barberId){
    	this.barberId = barberId;
    	
    }
    
    
	public Barber( String firstName,String lastName, Blob picture,
			String about, int rating, String gender,int storeId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.picture = picture;
		this.about = about;
		this.rating = rating;
		this.gender= gender;
		this.storeId = storeId;
		
	}
	
	public Barber( String firstName,String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;;
		
	}
    
    


	public int getBarberId() {
		return barberId;
	}

	public void setBarberId(int barberId) {
		this.barberId = barberId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob picture) {
		this.picture = picture;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}


	
	
	
	
	

}
