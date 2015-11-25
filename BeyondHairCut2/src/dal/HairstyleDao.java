package dal;

import model.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;


/**
* Data access object (DAO) class to interact with the underlying Persons table in your MySQL
* instance. This is used to store {@link Persons} into your MySQL instance and retrieve 
* {@link Persons} from MySQL instance.
*/
public class HairstyleDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static HairstyleDao instance = null;
	protected HairstyleDao() {
		connectionManager = new ConnectionManager();
	}
	public static HairstyleDao getInstance() {
		if(instance == null) {
			instance = new HairstyleDao();
		}
		return instance;
	}

	/**
	 * Save the Persons instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Hairstyle create(Hairstyle hairstyle) throws SQLException {
		String insertHairstyle = "INSERT INTO Hairstyle(Style,Gender,Picture,Instance,Price,Duration) VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertHairstyle);
			insertStmt.setString(1, hairstyle.getStyle());
			insertStmt.setString(2, hairstyle.getGender());
			insertStmt.setNull(3, Types.BLOB);
			insertStmt.setNull(4, Types.BLOB);
			insertStmt.setInt(5, hairstyle.getPrice());
			insertStmt.setInt(6, hairstyle.getDuration());


			insertStmt.executeUpdate();
			
			return hairstyle;
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
	
	public int getPriceFromStyle(String style) throws SQLException{
		String getHairstyle = "SELECT Hairstyle.price from Hairstyle WHERE style = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(getHairstyle);
			selectStmt.setString(1, style);

			results = selectStmt.executeQuery();

			if(results.next()) {
				String styl = results.getString("Style");
				String gender = results.getString("Gender");
				// first get the Blob, then convert blob into image
				Blob pic = results.getBlob("Picture");
				byte[] datapic = pic.getBytes(0, (int) pic.length());
				BufferedImage picture = ImageIO.read(new ByteArrayInputStream(datapic));
				
				Blob inst = results.getBlob("Instance");
				byte[] datainst = inst.getBytes(0, (int) inst.length());
				BufferedImage instance = ImageIO.read(new ByteArrayInputStream(datainst));
				int price = results.getInt("Price");
				int duration = results.getInt("Duration");
				Hairstyle hairstyle = new Hairstyle(styl, gender, picture, instance ,price,duration);
				return hairstyle.getPrice();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return 0;
	}

	public Hairstyle getHairFromStyle(String style) throws SQLException{
		String getHairstyle = "SELECT Hairstyle from Hairstyle WHERE style = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(getHairstyle);
			selectStmt.setString(1, style);
			System.out.println(style);
			
			results = selectStmt.executeQuery();
			System.out.println(results);
			
			if(results.next()) {
				String styl = results.getString("Style");
				String gender = results.getString("Gender");
				// first get the Blob, then convert blob into image
				Blob pic = results.getBlob("Picture");
				byte[] datapic = pic.getBytes(0, (int) pic.length());
				BufferedImage picture = ImageIO.read(new ByteArrayInputStream(datapic));
				
				Blob inst = results.getBlob("Instance");
				byte[] datainst = inst.getBytes(0, (int) inst.length());
				BufferedImage instance = ImageIO.read(new ByteArrayInputStream(datainst));
				int price = results.getInt("Price");
				int duration = results.getInt("Duration");
				Hairstyle hairstyle = new Hairstyle(styl, gender, picture, instance ,price,duration);
				return hairstyle;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public List<Hairstyle> getAllHairstyles() throws SQLException{
		List<Hairstyle> hairstyles = new ArrayList<Hairstyle>();
		String selectAllHairstyles =
				"SELECT * From Hairstyle";  
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAllHairstyles);
			results = selectStmt.executeQuery();

			while(results.next()) {
				String styl = results.getString("Style");
				String gender = results.getString("Gender");
				// first get the Blob, then convert blob into image
				Blob pic = results.getBlob("Picture");
				byte[] datapic = pic.getBytes(0, (int) pic.length());
				BufferedImage picture = ImageIO.read(new ByteArrayInputStream(datapic));
				
				Blob inst = results.getBlob("Instance");
				byte[] datainst = inst.getBytes(0, (int) inst.length());
				BufferedImage instance = ImageIO.read(new ByteArrayInputStream(datainst));
				int price = results.getInt("Price");
				int duration = results.getInt("Duration");
				
				Hairstyle hairstyle = new Hairstyle(styl, gender, picture, instance ,price,duration);
				hairstyles.add(hairstyle);
			}
		} catch (SQLException |IOException e) {
			e.printStackTrace();
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
		return hairstyles ;
	}



	/**
	 * Delete the Persons instance.
	 * This runs a DELETE statement.
	 */
	public Hairstyle delete(Hairstyle hairstyle) throws SQLException {
		String deleteHairstyle = "DELETE FROM Items WHERE Name=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteHairstyle);
			deleteStmt.setString(1, hairstyle.getStyle());
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