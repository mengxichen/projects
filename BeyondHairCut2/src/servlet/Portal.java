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

import model.Customer;
import dal.CustomerDao;

@WebServlet("/myportal")
public class Portal extends HttpServlet{
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
		
		//Just render the JSP.   
		req.getRequestDispatcher("/Portal.jsp").forward(req, resp);
	}
	
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		
		req.getRequestDispatcher("/Portal.jsp").forward(req, resp);
	}

}
