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
public class AdvertisementDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static AdvertisementDao instance = null;
	protected AdvertisementDao() {
		connectionManager = new ConnectionManager();
	}
	public static AdvertisementDao getInstance() {
		if(instance == null) {
			instance = new AdvertisementDao();
		}
		return instance;
	}

	/**
	 * Save the Persons instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Advertisement create(Advertisement advertisement) throws SQLException {
		String insertAdvertisement = "INSERT INTO Advertisement(Description,URL) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertAdvertisement,
    				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, advertisement.getDescription());
			insertStmt.setString(2, advertisement.getUrl());

			insertStmt.executeUpdate();
	         // Retrieve the auto-generated key and set it, so it can be used by the caller.
 			resultKey = insertStmt.getGeneratedKeys();
 			int advertisementID = -1;
 			if(resultKey.next()) {
 				advertisementID = resultKey.getInt(1);
 			} else {
 				throw new SQLException("Unable to retrieve auto-generated key.");
 			}
 			advertisement.setAdvertisementID(advertisementID);
			
			return advertisement;
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



	/**
	 * Delete the Persons instance.
	 * This runs a DELETE statement.
	 */
	public Advertisement delete(Advertisement advertisement) throws SQLException {
		String deleteItem = "DELETE FROM Items WHERE AdvertisementID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteItem);
			deleteStmt.setInt(1, advertisement.getAdvertisementID());
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
	
	
	
	public Advertisement updateDescriptionById(Advertisement advertisement, String newDescription) throws SQLException {
		String updateAdvertisement = "UPDATE Advertisement SET Description=?, WHERE AdvertisementID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAdvertisement);
			updateStmt.setString(1, newDescription);
			updateStmt.setInt(1, advertisement.getAdvertisementID());
			updateStmt.executeUpdate();

			advertisement.setDescription(newDescription);
			return advertisement;
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
}
