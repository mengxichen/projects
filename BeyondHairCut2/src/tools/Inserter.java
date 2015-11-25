package tools;

import dal.*;

import model.*;

import java.sql.SQLException;
import java.util.Date;
import java.awt.Image;

/**
 * main() runner, used for the app demo.
 * 
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/86a11H.
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class Inserter {

	private static final Image Image = null;

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		AddressDao addressDao = AddressDao.getInstance();
		AdvertisementDao adsDao = AdvertisementDao.getInstance();
		AppointmentDao appDao = AppointmentDao.getInstance();
		BarberDao barberDao = BarberDao.getInstance();
		CommentDao commentDao = CommentDao.getInstance();
		CreditCardDao creditCardDao = CreditCardDao.getInstance();
		CustomerDao customerDao = CustomerDao.getInstance();
		HairstyleDao hairstyleDao = HairstyleDao.getInstance();
		OtherserviceDao otherserviceDao = OtherserviceDao.getInstance();
		StoreDao storeDao = StoreDao.getInstance();
		
		Date date = new Date();
		Address address= new Address( "10756 boardway", "seattle", "WA", 98105, "USA");
		address = addressDao.create(address);
		Store store1 = new Store(address.getAddressId(), "2063308039");
		store1 = storeDao.create(store1);
		Barber barber1 = new Barber("Mengzhe", "Xu", null, "good", 1, "Female", store1.getStoreId());
		barber1 = barberDao.create(barber1);
		Barber barber2 = new Barber("Mengzhe", "Xu", null, "good", 2, "Female", store1.getStoreId());
		barber2 = barberDao.create(barber2);
		Customer customer1 = new Customer("male", "bruceyang", "bruce123", "bruce","yang",date, address.getAddressId());
		customer1 = customerDao.create(customer1);
		Customer customer2 = new Customer("female", "mingzhe", "mingzhe123","mingzhe", "xu", date,address.getAddressId());
		customer2 = customerDao.create(customer2);
		CreditCard creditcard1 = new CreditCard("12344", date, customer1.getCustomerID());
		creditcard1 = creditCardDao.create(creditcard1);
		CreditCard creditcard2 = new CreditCard("22222", date, customer2.getCustomerID());
		creditcard2 = creditCardDao.create(creditcard2);
		Comment comment1 = new Comment("he is a good barber.", barber1.getBarberId(), customer1.getCustomerID());
		comment1 = commentDao.create(comment1);
		Hairstyle style1 = new Hairstyle("duckass", "male", Image, Image, 20, 30);
		style1 = hairstyleDao.create(style1);
		Comment comment2 = new Comment("this is a good experience." , barber2.getBarberId(),customer2.getCustomerID());
		comment2 = commentDao.create(comment2);
		Appointment appoint1 = new Appointment(store1.getStoreId(), date, 
				customer1.getCustomerID(), barber1.getBarberId(), style1.getStyle()); 
		appoint1 = appDao.create(appoint1);
		

	}
}
