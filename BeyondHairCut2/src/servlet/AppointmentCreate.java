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
import java.util.Locale;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/appointmentcreate")
public class AppointmentCreate extends HttpServlet{

	protected AppointmentDao appointmentDao;
	protected BarberDao barderDao;
	protected CustomerDao customerDao;
	protected HairstyleDao hairstyleDao;

	@Override
	public void init() throws ServletException {
		appointmentDao = AppointmentDao.getInstance();
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
		req.getRequestDispatcher("/AppointmentCreate.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Retrieve and validate name.
		String username = req.getParameter("userName");
		Customer customer;
		try {
			customer = customerDao.getCustomerFromUserName(username);
			//System.out.print(username);

			if (customer == null) {
				messages.put("success", "Invalid Username");
			}else {
				// Create the Appointment
				int customerId = customer.getCustomerID();
				// System.out.print(customer.getCustomerID());
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
				String stringdate = req.getParameter("datetime");
				String barberid = req.getParameter("barberid");
				if(barberid == ""){
					barberid = "1";
				}
				Date datetime = null;
				String storeid = req.getParameter("storeid");
				String style = req.getParameter("style");
				System.out.print(style);

				try {
					datetime = format.parse(stringdate);
					System.out.print(datetime);
					
				} catch ( ParseException e) {
					e.printStackTrace();
					//throw new IOException(e);
				}
				if(storeid == null || style == null){
					messages.put("success", "please tell us your prefered location and hairstyle");
				}else if(appointmentDao.SelectAppFromBidDate(Integer.parseInt(barberid),datetime) != null){
					System.out.print(datetime);
					messages.put("success", "barber No." + barberid + "is not available");
				}else{
					Appointment appointment = 
							new Appointment(Integer.parseInt(storeid), 
									datetime, customerId, 
									Integer.parseInt(barberid), style);
					appointment = appointmentDao.create(appointment);
					//messages.put("success", "Successfully make an appointment with barber No." + barberid);
					String schedule_suc = "Confirmation.jsp?username="+ username;
					resp.sendRedirect(schedule_suc);
					return;
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		req.getRequestDispatcher("/AppointmentCreate.jsp").forward(req, resp);
	}
}
