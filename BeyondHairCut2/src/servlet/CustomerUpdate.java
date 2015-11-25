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


@WebServlet("/customerupdate")
public class CustomerUpdate extends HttpServlet {
	
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

        // Retrieve user and validate.
        String userName = req.getParameter("userName");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserName.");
        } else {
        	try {
        		Customer customer = customerDao.getCustomerFromUserName(userName);
        		if(customer == null) {
        			messages.put("success", "UserName does not exist.");
        		}
        		req.setAttribute("customer", customer);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CustomerUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String userName = req.getParameter("userName");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserName.");
        } else {
        	try {
        		Customer customer = customerDao.getCustomerFromUserName(userName);
        		if(customer == null) {
        			messages.put("success", "UserName does not exist. No update to perform.");
        		} else {
        			String newPassword = req.getParameter("password");
        			if (newPassword == null || newPassword.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid password.");
        	        } else {
        	        	customer = customerDao.updatePassword(customer, newPassword);
        	        	messages.put("success", "Successfully updated " + userName);
        	        }
        		}
        		req.setAttribute("customer", customer);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CustomerUpdate.jsp").forward(req, resp);
    }
}
