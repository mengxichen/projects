package model;

import java.util.Date;

public class CreditCard {
	protected String cardNumber;
	protected Date expiration;
	protected int CustomerId;
	
	public CreditCard( String cardNumber,Date expiration, int CustomerId) {

		this.cardNumber = cardNumber;
		this.expiration = expiration;
		this.CustomerId = CustomerId;
		
	}


	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public int getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(int CustomerId) {
		this.CustomerId = CustomerId;
	}
	

}


