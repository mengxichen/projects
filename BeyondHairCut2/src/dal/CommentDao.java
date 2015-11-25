package  dal;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class CommentDao {

	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static CommentDao instance = null;
	protected CommentDao() {
		connectionManager = new ConnectionManager();
	}
	public static CommentDao getInstance() {
		if(instance == null) {
			instance = new CommentDao();
		}
		return instance;
	}

	public Comment create(Comment c) throws SQLException {
		String insertItems = "INSERT INTO Comment (Content, BarberId, CustomerId)"
				+ " VALUES (?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;

		try{
			connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertItems,
    				Statement.RETURN_GENERATED_KEYS);
			//insertStmt.setInt(1, c.getCommentId());
			insertStmt.setString(1, c.getContent());
			insertStmt.setInt(2, c.getBarberId());
			insertStmt.setInt(3, c.getCustomerId());
			insertStmt.executeUpdate();
	         // Retrieve the auto-generated key and set it, so it can be used by the caller.
 			resultKey = insertStmt.getGeneratedKeys();
 			int commentId = -1;
 			if(resultKey.next()) {
 				commentId = resultKey.getInt(1);
 			} else {
 				throw new SQLException("Unable to retrieve auto-generated key.");
 			}
 			c.setCommentId(commentId);
			return c;

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

	public Comment getContentByCommentId(int cId) throws SQLException {
		String selectContentByCommentId = "SELECT CommentId, Content, BarberId, CustomerId FROM COMMENT WHERE CommentId = ? ;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectContentByCommentId);
			selectStmt.setInt(1, cId);

			results = selectStmt.executeQuery();

			if(results.next()) {
				int cid = results.getInt(cId);
				String content = results.getString("Content");
				int bid = results.getInt("BarberId");
				int customerId = results.getInt("CustomerId");

				Comment Comment = new Comment(cid, content, bid, customerId);
				return Comment;
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

	//SELECT a list of comments on a barber
	//From Barber INNER JOIN Comment 
	//Where Barber.BarberId = Comment.BarberId

	public List<Comment> getCommentsForBarber(int BarberId) throws SQLException {
		List<Comment> commentList = new ArrayList<Comment>();
		String selectCommentsByBarberId =
				"SELECT Comment.CommentId, Comment.Content, Comment.BarberId, Comment.CustomerId "
				+ "From Comment Where Comment.BarberId = ?";  
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCommentsByBarberId);
			selectStmt.setInt(1, BarberId);
			results = selectStmt.executeQuery();

			while(results.next()) {
				int cmtId = results.getInt("Comment.CommentId");
				String content = results.getString("Comment.Content");
				int bId = results.getInt("Comment.BarberId");
				int cstmid = results.getInt("Comment.CustomerId");

				Comment comment = new Comment(cmtId, content, bId, cstmid);
				commentList.add(comment);
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
		return commentList ;
	}

	// delete a piece of content
	public Comment delete(Comment c) throws SQLException {

		String deleteComment = "DELETE FROM Comment WHERE CommentId = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;

		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteComment);
			deleteStmt.setInt(1, c.getCommentId());
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


