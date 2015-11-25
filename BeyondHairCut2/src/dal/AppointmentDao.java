package dal;
import model.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;



public class AppointmentDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static AppointmentDao instance = null;
	protected AppointmentDao() {
		connectionManager = new ConnectionManager();
	}
	public static AppointmentDao getInstance() {
		if(instance == null) {
			instance = new AppointmentDao();
		}
		return instance;
	}
	
	public Appointment create(Appointment appointment) throws SQLException {
		String insertAppointment = 
				"INSERT INTO Appointment "
				+ "(storeId, Date, CustomerId, BarberId, Style)"
				+ " VALUES (?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try{
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAppointment, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, appointment.getStoreId());
			insertStmt.setTimestamp(2, new Timestamp(appointment.getDate().getTime()));
			insertStmt.setInt(3, appointment.getCustomerId());
			insertStmt.setInt(4, appointment.getBarberId());
			insertStmt.setString(5, appointment.getStyle());

			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int appointmentId = -1;
			if(resultKey.next()) {
				appointmentId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			appointment.setAppointmentId(appointmentId);
			return appointment;
			
		}catch (SQLException e) {
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
	
	public List<Appointment> getAppointmentsFromCustomerId(int CustomerID) throws SQLException{
		List<Appointment> appointments = new ArrayList<Appointment>();
		String selectAppointmentsbyCustomerID = 
				
				"SELECT StoreId,Date,CustomerId, BarberId,Style FROM Appointment WHERE CustomerId = ?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAppointmentsbyCustomerID);
			selectStmt.setInt(1, CustomerID);
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				int custId = results.getInt("CustomerId");
				int storeid = results.getInt("StoreId");
				Date date = results.getDate("Date");
				int bId = results.getInt("BarberId");
				String style = results.getString("Style");
				Appointment appoint = 
						new Appointment(storeid, date, custId, bId, style);
				appointments.add(appoint);
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
		
		return appointments;
	}
	public Appointment SelectAppFromBidDate(int barberid, Date datetime) throws SQLException{
		String selectApp = "SELECT * FROM APPOINTMENT WHERE BarberId = ? AND Date = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectApp);
			selectStmt.setInt(1, barberid);
			
			java.sql.Date sqldate = new java.sql.Date(datetime.getTime());
			selectStmt.setDate(2, sqldate);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int storeid = results.getInt("StoreId");
			    datetime = results.getDate("Date");
				int customerid = results.getInt("CustomerId");
				String style = results.getString("Style");
				System.out.print(barberid +" this ");
				
				Appointment app = new Appointment(storeid, datetime, customerid, barberid, style);
				return app;
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
	
	
    public Appointment delete(Appointment appointment) throws SQLException {
		
		String deleteAppointment = "DELETE FROM Appointment WHERE AppointmentId = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAppointment);
			deleteStmt.setLong(1, appointment.getAppointmentId());
			deleteStmt.executeUpdate();
			
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
		
		return null;
	}

}
