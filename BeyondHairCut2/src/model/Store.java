package model;
public class Store {
	//StoreId INT AUTO_INCREMENT,
	//AddressId Int NOT NULL,
	//Phone INT NOT NULL,
	
	protected int storeId;
	protected int addressId;
	protected String phone;
	
	public Store(int storeId, int addressId, String phone) {
		this.storeId = storeId;
		this.addressId = addressId;
		this.phone = phone;
	}
	public Store(){
		
	}
	public Store(int storeId) {
		this.storeId = storeId;
	}
	
	public Store(int addressId, String phone) {
		this.addressId = addressId;
		this.phone = phone;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
