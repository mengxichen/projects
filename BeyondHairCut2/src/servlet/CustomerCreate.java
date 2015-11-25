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


@WebServlet("/customercreate")
public class CustomerCreate extends HttpServlet {
	
	protected CustomerDao customerDao;
	protected AddressDao addressDao;
	
	@Override
	public void init() throws ServletException {
		customerDao = CustomerDao.getInstance();
		addressDao = AddressDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/CustomerCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String userName = req.getParameter("userName");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        }else
			try {
				if(customerDao.getCustomerFromUserName(userName)!=null){
					messages.put("success","this username has already been used");
				}else {
					// Create the BlogUser.
					String password = req.getParameter("password");
					String gender = req.getParameter("gender");
					String firstName = req.getParameter("firstName");
					String lastName = req.getParameter("lastName");
					//String addressId = req.getParameter("addressId");
					String street = req.getParameter("street");
					String city = req.getParameter("city");
					String state = req.getParameter("state");
					String zip = req.getParameter("zip"); 
					String country = req.getParameter("country");
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String stringDob = req.getParameter("dob");
					System.out.print(stringDob);
					Date dob = null;
					
					try {
						dob = dateFormat.parse(stringDob);
						System.out.print(dob);
					} catch (ParseException e) {
						e.printStackTrace();
						throw new IOException(e);
					}
				    try {
				    	Address address = new Address(street, city, state, Integer.parseInt(zip), country);
				    	address = addressDao.create(address);
				    	Customer customer = new Customer(gender,userName, password,firstName, lastName, dob,
				    			address.getAddressId());
				    	customer = customerDao.create(customer);
				    	messages.put("success", "Thanks for Signing up! You have successfully created " + userName + "!");
				    } catch (SQLException e) {
						e.printStackTrace();
						throw new IOException(e);
				    }
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
        req.getRequestDispatcher("/CustomerCreate.jsp").forward(req, resp);
    }
}