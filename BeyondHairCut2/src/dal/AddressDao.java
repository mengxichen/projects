package dal;

import java.sql.Connection;

import model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
* Data access object (DAO) class to interact with the underlying Persons table in your MySQL
* instance. This is used to store {@link Persons} into your MySQL instance and retrieve 
* {@link Persons} from MySQL instance.
*/
public class AddressDao {
    protected ConnectionManager connectionManager;
    
    // Single pattern: instantiation is limited to one object.
    private static AddressDao instance = null;
    protected AddressDao() {
        connectionManager = new ConnectionManager();
    }
    public static AddressDao getInstance() {
        if(instance == null) {
            instance = new AddressDao();
        }
        return instance;
    }

    /**
     * Save the Persons instance by storing it in your MySQL instance.
     * This runs a INSERT statement.
     */
    public Address create(Address address) throws SQLException {
        String insertAddress = "INSERT INTO Address(Street,City,State,Zip,Country) VALUES(?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertAddress,
    				Statement.RETURN_GENERATED_KEYS);
            //insertStmt.setInt(1, address.getAddressId());
            insertStmt.setString(1, address.getStreet());
            insertStmt.setString(2, address.getCity());
            insertStmt.setString(3, address.getState());
            insertStmt.setInt(4, address.getZip());
            insertStmt.setString(5, address.getCountry());

            insertStmt.executeUpdate();
         // Retrieve the auto-generated key and set it, so it can be used by the caller.
         			resultKey = insertStmt.getGeneratedKeys();
         			int addressId = -1;
         			if(resultKey.next()) {
         				addressId = resultKey.getInt(1);
         			} else {
         				throw new SQLException("Unable to retrieve auto-generated key.");
         			}
         			address.setAddressId(addressId);
            
            return address;
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
    // get address info by id
    public Address getAddressById(int AddressId) throws SQLException {
		String selectAddressbyId = 
				"SELECT AddressId, Street, City, State, Zip, Country FROM Address WHERE AddressId = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAddressbyId);
			selectStmt.setInt(1, AddressId);

			results = selectStmt.executeQuery();

			if(results.next()) {
				int aid = results.getInt("AddressId");
				String street = results.getString("Street");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("Zip");
				String country = results.getString("Country");

				Address address = new Address(aid, street,city, state, zip,country);
				return address;
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


    public Address delete(Address address) throws SQLException {
        String deleteItem = "DELETE FROM Items WHERE AddressId = ?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteItem);
            deleteStmt.setInt(1, address.getAddressId());
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