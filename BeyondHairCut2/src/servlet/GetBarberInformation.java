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


@WebServlet("/getbarberinformation")
public class GetBarberInformation extends HttpServlet {
	
	protected BarberDao barberDao;

	
	@Override
	public void init() throws ServletException {
		barberDao = BarberDao.getInstance();

	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		// Map for storing messages.
        Map<String, String> message = new HashMap<String, String>();
        req.setAttribute("messages", message);

        List<Barber> barber = new ArrayList<Barber>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the URL query string.
        String barberid = req.getParameter("barberid");
        
        if (barberid == null || barberid.trim().isEmpty()) {
        	message.put("success", "Please enter a valid barberId.");
        } else {
        	try {
        		
        		barber = barberDao.getBarberInforbyBarberId(Integer.parseInt(barberid));
        		message.put("title", "BarberId " + barberid + " gave me the haircut");
        		
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }

        	message.put("success", "Displaying results for " + barberid);
        

        }
        req.setAttribute("barbers", barber);
        
        req.getRequestDispatcher("/GetBarberInformation.jsp").forward(req, resp);
	
	}
	

}
