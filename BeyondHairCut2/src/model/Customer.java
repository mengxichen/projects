package model;
import java.util.Date;

public class Customer {
	protected int customerID;
	protected String gender;
	protected String userName;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected Date dob;
	protected int AddressId;
	
	public Customer(){
		
	}

	public Customer(String gender, String userName, String password, String firstName,
			String lastName, Date dob, int address) {
		this.gender = gender;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.AddressId = address;
	}
	
	
	public Customer(int customerID, String gender, String userName, String password,
			String firstName, String lastName, Date dob, int addressid) {
		this.customerID = customerID;
		this.gender = gender;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.AddressId = addressid;
	}

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getAddressId() {
		return AddressId;
	}


	public void setAddressId(int addressId) {
		AddressId = addressId;
	}

	public Customer(int customerID) {
		super();
		this.customerID = customerID;
	}


	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	
}
