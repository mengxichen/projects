package dal;
import model.*;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;



/**
* Data access object (DAO) class to interact with the underlying Persons table in your MySQL
* instance. This is used to store {@link Persons} into your MySQL instance and retrieve 
* {@link Persons} from MySQL instance.
*/
public class BarberDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static BarberDao instance = null;
	protected BarberDao() {
		connectionManager = new ConnectionManager();
	}
	public static BarberDao getInstance() {
		if(instance == null) {
			instance = new BarberDao();
		}
		return instance;
	}

	/**
	 * Save the Persons instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Barber create(Barber barber) throws SQLException {
		String insertBarber = "INSERT INTO Barber(FirstName,LastName,Picture,"
				+ "About, Rating, Gender,StoreId) VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();           
			insertStmt = connection.prepareStatement(insertBarber,
    				Statement.RETURN_GENERATED_KEYS);
			//insertStmt.setInt(1, barber.getBarberId());
			insertStmt.setString(1, barber.getFirstName());
			insertStmt.setString(2, barber.getLastName());
			insertStmt.setNull(3, Types.BLOB);
			insertStmt.setString(4, barber.getAbout());
			insertStmt.setInt(5, barber.getRating());
			insertStmt.setString(6, barber.getGender());
			insertStmt.setInt(7, barber.getStoreId());

			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			
			int barberId = -1;
			if(resultKey.next()) {
				barberId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			barber.setBarberId(barberId);
			return barber;
			
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
	
	public List<Barber> getBarberInforbyBarberId(int barberid) throws SQLException {
		List<Barber> barbers = new ArrayList<Barber>();
	String selectContentByCommentId = 
			"SELECT FirstName,LastName FROM Barber WHERE BarberId = ? ;";
	Connection connection = null;
	PreparedStatement selectStmt = null;
	ResultSet results = null;
	try {
		connection = connectionManager.getConnection();
		selectStmt = connection.prepareStatement(selectContentByCommentId);
		selectStmt.setInt(1, barberid);

		results = selectStmt.executeQuery();

		if(results.next()) {
			String barberFirstName = results.getString("FirstName");
            String barberLastName = results.getString("LastName");
            
            Barber barberinfo = new Barber(barberFirstName, barberLastName);
			barbers.add(barberinfo);
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
	
	return barbers;
}
	
	
	
	
	
	

//	public int getBarberIdbyFirstName(String firstname) throws SQLException {
//		String selectContentByCommentId = 
//				"SELECT CommentId, Content, BarberId, CustomerId FROM Content WHERE CommentId = ? ;";
//		Connection connection = null;
//		PreparedStatement selectStmt = null;
//		ResultSet results = null;
//		try {
//			connection = connectionManager.getConnection();
//			selectStmt = connection.prepareStatement(selectContentByCommentId);
//			selectStmt.setInt(1, cId);
//
//			results = selectStmt.executeQuery();
//
//			if(results.next()) {
//				int cid = results.getInt(cId);
//				String content = results.getString("Content");
//				int bid = results.getInt("BarberId");
//				int customerId = results.getInt("CustomerId");
//
//				Comment Comment = new Comment(cid, content, bid, customerId);
//				return Comment;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			if(connection != null) {
//				connection.close();
//			}
//			if(selectStmt != null) {
//				selectStmt.close();
//			}
//			if(results != null) {
//				results.close();
//			}
//		}
//		return null;
//	}

	public List<Barber> getAllBarbers() throws SQLException{
		List<Barber> barbers = new ArrayList<Barber>();
		String selectAllBarbers =
				"SELECT * From Barber";  
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAllBarbers);
			results = selectStmt.executeQuery();

			while(results.next()) {
				int bId = results.getInt("Barber.BarberId");
				String firstname = results.getString("Barber.FirstName");
				String lastname = results.getString("Barber.LastName");
				Blob picture = results.getBlob("Barber.Picture");
				String about = results.getString("Barber.About");
				int rating = results.getInt("Barber.rating");
				String gender = results.getString("Barber.Gender");
				int storeId = results.getInt("Barber.StoreId");

				Barber barber = new Barber(bId, firstname, lastname, picture, about,rating,gender, storeId);
				barbers.add(barber);
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
		return barbers ;
	}

	
	
	public Barber delete(Barber barber) throws SQLException {
		String deleteBarber = "DELETE FROM Items WHERE BarberId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBarber);
			deleteStmt.setInt(1, barber.getBarberId());
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