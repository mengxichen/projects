package dal;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OtherserviceDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static OtherserviceDao instance = null;
	protected OtherserviceDao() {
		connectionManager = new ConnectionManager();
	}
	public static OtherserviceDao getInstance() {
		if(instance == null) {
			instance = new OtherserviceDao();
		}
		return instance;
	}

	/**
	 * Save the Persons instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Otherservice create(Otherservice otherService) throws SQLException {
		String insertOtherService = "INSERT INTO Otherservice(Services,Duration,About,Price,) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertOtherService,
    				Statement.RETURN_GENERATED_KEYS);
			//insertStmt.setInt(1, otherService.getServiceId());
			insertStmt.setString(1, otherService.getServices());
			insertStmt.setInt(2, otherService.getDuration());
			insertStmt.setString(3, otherService.getAbout());
			insertStmt.setInt(4, otherService.getPrice());


			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int serviceId = -1;
			if(resultKey.next()) {
				serviceId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			otherService.setServiceId(serviceId);
			
			return otherService;
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



	public Otherservice delete(Otherservice otherservice) throws SQLException {
		String deleteItem = "DELETE FROM Items WHERE SeviceId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteItem);
			deleteStmt.setInt(1, otherservice.getServiceId());
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


	public Otherservice getOtherServiceById(int serviceId) throws SQLException {
		String selectOtherservice = "SELECT SeviceId,Services,Duration,About,Price FROM Otherservice WHERE SeviceId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectOtherservice);
			selectStmt.setInt(1, serviceId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultServiceId = results.getInt("SeviceId");
				String services = results.getString("Services");
				int duration = results.getInt("Duration");
				String about = results.getString("About");
				int price= results.getInt("Price");

				Otherservice otherService = new Otherservice(resultServiceId, services, duration,
						about,price);
				return otherService;
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
}

