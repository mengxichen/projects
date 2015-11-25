package servlet;


import dal.*;

import model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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


@WebServlet("/commentcreate")
public class CommentCreate extends HttpServlet {

	protected CommentDao commentDao;
	protected BarberDao barderDao;
	protected CustomerDao customerDao;

	@Override
	public void init() throws ServletException {
		commentDao = CommentDao.getInstance();
		barderDao = BarberDao.getInstance();
		customerDao = CustomerDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		//Just render the JSP.   
		req.getRequestDispatcher("/CommentCreate.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Retrieve and validate name.
		String customerId = req.getParameter("customerid");
		if (customerId == null || customerId.trim().isEmpty()) {
			messages.put("success", "Invalid CustomerId");
		} else {
			// Create the BlogUser.
			String customerid = req.getParameter("customerid");
			String barberid = req.getParameter("barberId");
			String content = req.getParameter("content");

			try {

				Comment comment = new Comment(content, 
						Integer.parseInt(barberid),
						Integer.parseInt(customerid));
				comment = commentDao.create(comment);
				messages.put("success", "Successfully created a comment for barber No." + barberid);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}

		req.getRequestDispatcher("/CommentCreate.jsp").forward(req, resp);
	}

}
