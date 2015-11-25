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


@WebServlet("/hairstyles")
public class AllHairstyles extends HttpServlet {
	protected HairstyleDao hairstyledao;
	@Override
	public void init() throws ServletException {
		hairstyledao = HairstyleDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// Map for storing messages.
		Map<String, String> message = new HashMap<String, String>();
		req.setAttribute("messages", message);

		List<Hairstyle> hairstyles = new ArrayList<Hairstyle>();

		
		try {
			hairstyles = hairstyledao.getAllHairstyles();
			System.out.print(hairstyles.size());
			message.put("title", "Here are all the Hairstyles:");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		// message.put("success", "Displaying results for all the barbers");

		req.setAttribute("hairstyles", hairstyles);

		req.getRequestDispatcher("/Hairstyles.jsp").forward(req, resp);

	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		
		
        req.getRequestDispatcher("/Hairstyles.jsp").forward(req, resp);
	
	}

}
