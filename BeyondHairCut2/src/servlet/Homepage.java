package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Customer;
import dal.CustomerDao;

@WebServlet("/homepage")
public class Homepage extends HttpServlet{
	
	protected CustomerDao customerDao;
	
	@Override
	public void init() throws ServletException {
		customerDao = CustomerDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages. 
		// only for wrong inputs in this sign page -
		// if the user name&password are correct, no need to return message, just redirect
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		//Just render the JSP.   
		req.getRequestDispatcher("/Homepage.jsp").forward(req, resp);
	}
	
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Customer customer = new Customer();
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        customer.setUserName(userName);
        customer.setPassword(password);
        
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserName.");
        } else {
        	try {
        		Customer custmer = customerDao.getCustomerFromUserName(userName);
        		if(custmer == null) {
        			messages.put("success", "UserName does not exist.");
        		} else if (userName == null || userName.trim().isEmpty()) {
        			messages.put("success", "Invalid username");
        		} else if(custmer.getPassword().equals(password)){
        			// redirect the sign in request to the portal page if the password is correct
        			// "return" is added to make sure other req/resp can be handled in other scenarios
        			String username = custmer.getUserName();
        			String login_suc = "Portal.jsp?username="+ username;
        			session.setAttribute("customer", customer);
        		    resp.sendRedirect(login_suc);
        		    return;
        		} else{
        			messages.put("success", "wrong password!");
        		}
        		req.setAttribute("customer", custmer);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
		req.getRequestDispatcher("/Homepage.jsp").forward(req, resp);
	}

}
