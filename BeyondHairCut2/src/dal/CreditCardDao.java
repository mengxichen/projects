package dal;


import model.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;


/**
* Data access object (DAO) class to interact with the underlying Persons table in your MySQL
* instance. This is used to store {@link Persons} into your MySQL instance and retrieve 
* {@link Persons} from MySQL instance.
*/
public class CreditCardDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static CreditCardDao instance = null;
	protected CreditCardDao() {
		connectionManager = new ConnectionManager();
	}
	public static CreditCardDao getInstance() {
		if(instance == null) {
			instance = new CreditCardDao();
		}
		return instance;
	}

	/**
	 * Save the Persons instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public CreditCard create(CreditCard creditCard) throws SQLException {
		String insertCreditCard = "INSERT INTO CreditCard(CardNumber,Expiration,CustomerID) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCreditCard);
			insertStmt.setString(1, creditCard.getCardNumber());
			insertStmt.setTimestamp(2, new Timestamp(creditCard.getExpiration().getTime()));
			insertStmt.setInt(3, creditCard.getCustomerId());


			insertStmt.executeUpdate();
			
			return creditCard;
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
			
		}
	}



	/**
	 * Delete the Persons instance.
	 * This runs a DELETE statement.
	 */
	public CreditCard delete(CreditCard creditCard) throws SQLException {
		String deleteCreditCard = "DELETE FROM Items WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCreditCard);
			deleteStmt.setString(1, creditCard.getCardNumber());
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