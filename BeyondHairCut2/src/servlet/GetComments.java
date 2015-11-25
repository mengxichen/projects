package servlet;

import dal.*;

import model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/getcomments")
public class GetComments extends HttpServlet {
	
	protected CommentDao commentDao;
	@Override
	public void init() throws ServletException {
		commentDao = CommentDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		// Map for storing messages.
        Map<String, String> message = new HashMap<String, String>();
        req.setAttribute("messages", message);

        List<Comment> comments = new ArrayList<Comment>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the URL query string.
        String barberId = req.getParameter("barberId");
        if (barberId == null || barberId.trim().isEmpty()) {
        	message.put("success", "Please enter a valid barber ID.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		comments = commentDao.getCommentsForBarber(Integer.parseInt(barberId));
	        	message.put("title", "Comments for barberId " + barberId);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	message.put("success", "Displaying results for " + barberId);

        }
        req.setAttribute("comments", comments);
        
        req.getRequestDispatcher("/GetComments.jsp").forward(req, resp);
	
	}
	

}
