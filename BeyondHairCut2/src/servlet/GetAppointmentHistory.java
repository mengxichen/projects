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


@WebServlet("/getappointmenhistory")
public class GetAppointmentHistory extends HttpServlet {
	
	protected AppointmentDao appointmentDao;
	protected CustomerDao customerDao;
	
	@Override
	public void init() throws ServletException {
		appointmentDao = AppointmentDao.getInstance();
		customerDao = CustomerDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		// Map for storing messages.
        Map<String, String> message = new HashMap<String, String>();
        req.setAttribute("messages", message);

        List<Appointment> appointments = new ArrayList<Appointment>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the URL query string.
        String username = req.getParameter("username");
        
        if (username == null || username.trim().isEmpty()) {
        	message.put("success", "Please enter a valid username.");
        } else {
        	try {
        		Customer customer = customerDao.getCustomerFromUserName(username);
        		
        		if(customer == null){
        			message.put("success", "There is no appointment for this user");
        		}else{
        			
        			int customerid = customer.getCustomerID();
        			appointments = appointmentDao.getAppointmentsFromCustomerId(customerid);
        			message.put("title", "Appointments for  " + username);
        		}
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	// message.put("success", "Displaying results for " + username);

        }
        req.setAttribute("appointments", appointments);
        
        req.getRequestDispatcher("/GetAppointmentHistory.jsp").forward(req, resp);
	
	}
	

}