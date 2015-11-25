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


@WebServlet("/customerdelete")
public class CustomerDelete extends HttpServlet {
	
	protected CustomerDao customerDao;
	
	@Override
	public void init() throws ServletException {
		customerDao = CustomerDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Customer");        
        req.getRequestDispatcher("/CustomerDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String customerID = req.getParameter("customerID");
        if (customerID == null || customerID.trim().isEmpty()) {
            messages.put("title", "Invalid CustomerID");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
	        Customer customer = new Customer(Integer.parseInt(customerID));
	        try {
	        	customer = customerDao.delete(customer);
	        	// Update the message.
		        if (customer == null) {
		            messages.put("title", "Successfully deleted " + customerID);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + customerID);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CustomerDelete.jsp").forward(req, resp);
    }
}