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


@WebServlet("/barbers")
public class AllBarbers extends HttpServlet{
	protected BarberDao barberdao;
	@Override
	public void init() throws ServletException {
		barberdao = BarberDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// Map for storing messages.
		Map<String, String> message = new HashMap<String, String>();
		req.setAttribute("messages", message);

		List<Barber> barbers = new ArrayList<Barber>();

		// Retrieve and validate name.
		// firstname is retrieved from the URL query string.

		// Retrieve BlogUsers, and store as a message.
		try {
			barbers = barberdao.getAllBarbers();
			message.put("title", "Here are all the Barbers:");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		// message.put("success", "Displaying results for all the barbers");

		req.setAttribute("barbers", barbers);

		req.getRequestDispatcher("/Barbers.jsp").forward(req, resp);

	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		
		// 
		// 
        /*	try {
        		Customer customer = customerDao.getCustomerFromUserName(userName);
        		if(customer == null) {
        			messages.put("success", "UserName does not exist.");
        		} else if (userName == null || userName.trim().isEmpty()) {
        			messages.put("success", "Invalid username");
        		} else if(customer.getPassword().equals(password)){
        			messages.put("success", "successfully log in!");
        		} else{
        			messages.put("success", "wrong password!");
        		}
        		req.setAttribute("customer", customer);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        
        	}*/
        req.getRequestDispatcher("/Barbers.jsp").forward(req, resp);
	
	}

}
