package com.wb4.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wb4.commands.UsersTours;
import com.wb4.commands.ChangeTourState;
import com.wb4.commands.Commands;
import com.wb4.commands.Discount;
import com.wb4.commands.LogOut;
import com.wb4.commands.Login;
import com.wb4.commands.Registration;
import com.wb4.commands.SortByPrice;
import com.wb4.commands.TourList;
import com.wb4.commands.UserList;
import com.wb4.commands.UsersPageRedirect;
import com.wb4.services.ConstantValues;



@WebServlet(urlPatterns = "/rest/*")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected Map<String , Commands> commandsList = new HashMap<>();

    public FrontController() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
    		commandsList.put("POST:/login", Login.getInstance());
    		commandsList.put("GET:/login", Login.getInstance());
    		commandsList.put("POST:/logout", LogOut.getInstance());
    		commandsList.put("GET:/logout", LogOut.getInstance());
    		commandsList.put("POST:/tourList", TourList.getInstance());
    		commandsList.put("GET:/tourList", TourList.getInstance());
    		commandsList.put("POST:/discount", Discount.getInstance());
    		commandsList.put("POST:/usersTours", UsersTours.getInstance());
    		commandsList.put("GET:/usersTours", UsersTours.getInstance());
    		commandsList.put("POST:/userList", UserList.getInstance());
    		commandsList.put("GET:/userList", UserList.getInstance());
    		commandsList.put("POST:/registration", Registration.getInstance());
    		commandsList.put("GET:/registration", Registration.getInstance());
    		commandsList.put("POST:/usersPageRedirect", UsersPageRedirect.getInstance());
    		commandsList.put("GET:/usersPageRedirect", UsersPageRedirect.getInstance());
    		commandsList.put("POST:/changeTourState", ChangeTourState.getInstance());
    		commandsList.put("GET:/changeTourState", ChangeTourState.getInstance());
    		commandsList.put("POST:/sortByPrice", SortByPrice.getInstance());
    		commandsList.put("GET:/sortByPrice", SortByPrice.getInstance());
    		super.init(config);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getMethod().toUpperCase();
		String path = request.getRequestURI();

		path = path.replaceAll(".*/rest", "").replaceAll("\\d+", "");
		String key = method + ":" + path;

		Commands command = commandsList.getOrDefault(key, (req, resp) -> ConstantValues.LOGIN_PAGE);
		String viewPage = command.execute(request, response);
        
		request.setAttribute(request.getContextPath(), "contextPath");
        RequestDispatcher rd = request.getRequestDispatcher(viewPage);
        rd.forward(request, response);
	}
}
