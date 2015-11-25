package model;

import java.awt.Image;
import java.sql.Blob;


public class Hairstyle {
	protected String style;
	protected String gender;
	protected Image picture;
	protected Image instance;
	protected int price;
	protected int duration;
	
	public Hairstyle(String style, String gender, Image picture,Image instance,
			int price, int duration){
		this.style = style;
		this.gender = gender;
		this.picture= picture;
		this.instance = instance;
		this.price = price;
		this.duration = duration;
	}

	
	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public String getStyle() {
		return style;
	}


	public void setStyle(String style) {
		this.style = style;
	}


	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Image getPicture() {
		return picture;
	}


	public void setPicture(Image picture) {
		this.picture = picture;
	}


	public Image getInstance() {
		return instance;
	}


	public void setInstance(Image instance) {
		this.instance = instance;
	}


	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
			
	
}
