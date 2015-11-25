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


@WebServlet("/getstoreinformation")
public class GetStoreInformation extends HttpServlet {
	
	protected StoreDao storeDao;
	protected AddressDao addressDao;
	
	@Override
	public void init() throws ServletException {
		storeDao = StoreDao.getInstance();
		addressDao = AddressDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		// Map for storing messages.
        Map<String, String> message = new HashMap<String, String>();
        req.setAttribute("messages", message);
        Address address = new Address();
        Store store = new Store();
        
        // Retrieve and validate name.
        // firstname is retrieved from the URL query string.
        String storeid = req.getParameter("storeid");
        
        if (storeid == null || storeid.trim().isEmpty()) {
        	message.put("success", "Please enter a valid storeId.");
        } else {
        	try {
        		store = storeDao.getStoreInforbyStoreId(Integer.parseInt(storeid));
        	    address = addressDao.getAddressById(store.getAddressId());
        		message.put("title", "This is the Address for Store" + storeid);
        		
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }

        	message.put("success", "Displaying results for store " + storeid);

        }
        req.setAttribute("address", address);
        
        req.getRequestDispatcher("/GetStoreInformation.jsp").forward(req, resp);
	
	}
	

}
