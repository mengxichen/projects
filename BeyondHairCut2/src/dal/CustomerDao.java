package dal;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.*;
public class CustomerDao {
protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static CustomerDao instance = null;
	protected CustomerDao() {
		connectionManager = new ConnectionManager();
	}
	public static CustomerDao getInstance() {
		if(instance == null) {
			instance = new CustomerDao();
		}
		return instance;
	}
	
	public Customer create(Customer customer) throws SQLException {
		String insertCustomer = 
				"INSERT INTO Customer(Gender,UserName,PassWord,FirstName,LastName,DOB, AddressId) "
				+ "VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
		try {
				connection = connectionManager.getConnection();
				 insertStmt = connection.prepareStatement(insertCustomer,
		    				Statement.RETURN_GENERATED_KEYS);
				//insertStmt.setInt(1, customer.getCustomerID());
				insertStmt.setString(1, customer.getGender());
				insertStmt.setString(2, customer.getUserName());
				insertStmt.setString(3, customer.getPassword());
				insertStmt.setString(4, customer.getFirstName());
				insertStmt.setString(5, customer.getLastName());
				insertStmt.setTimestamp(6, new Timestamp(customer.getDob().getTime()));
				insertStmt.setInt(7, customer.getAddressId());
				insertStmt.executeUpdate();
		         // Retrieve the auto-generated key and set it, so it can be used by the caller.
     			resultKey = insertStmt.getGeneratedKeys();
     			int customerID = -1;
     			if(resultKey.next()) {
     				customerID = resultKey.getInt(1);
     			} else {
     				throw new SQLException("Unable to retrieve auto-generated key.");
     			}
     			customer.setCustomerID(customerID);
        
        return customer;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	public Customer updateLastName(Customer customer, String newLastName) throws SQLException {
		String updatePerson = "UPDATE Customer SET LastName=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePerson);
			updateStmt.setString(1, newLastName);
			updateStmt.setString(2, customer.getUserName());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			customer.setLastName(newLastName);
			return customer;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	public Customer updatePassword(Customer customer, String newPassword) throws SQLException {
		String updatePerson = "UPDATE Customer SET PassWord=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePerson);
			updateStmt.setString(1, newPassword);
			updateStmt.setString(2, customer.getUserName());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			customer.setLastName(newPassword);
			return customer;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	public Customer getCustomerFromUserName(String userName) throws SQLException {
		String selectCustomer = "SELECT CustomerId, UserName, Password, FirstName,LastName,Gender,"
				+ "dob, AddressId FROM Customer WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCustomer);
			selectStmt.setString(1, userName);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				int customerId = results.getInt("customerId");
				String gender = results.getString("Gender");
				String resultUserName = results.getString("UserName");
				String password = results.getString("Password");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				Date dob = results.getDate("dob");
				int addressId = results.getInt("AddressId");
				
				Customer customer = new Customer(customerId, gender, resultUserName, password, firstName, lastName,dob,addressId);
				return customer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	public Customer delete(Customer customer) throws SQLException {
		String deleteCustomer = "DELETE FROM Customer WHERE CustomerId = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCustomer);
			deleteStmt.setInt(1, customer.getCustomerID());
			deleteStmt.executeUpdate();
			

			// Return null so the caller can no longer operate on the Persons instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

}
