package com.wb4.commands;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wb4.entity.Tour;
import com.wb4.entity.User;
import com.wb4.services.ConstantValues;
import com.wb4.services.TourBuilderService;
import com.wb4.services.TourService;
import com.wb4.utilites.MethodsUtils;


//This class show all users tours, which he was chosen
public class UsersTours implements Commands {
	protected static UsersTours instance = null;
	protected UsersTours() {}

	public static UsersTours getInstance() {
		if (instance == null) {
			instance = new UsersTours();
		}
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("USER");
		String id = request.getParameter("tourId");
		List<Tour> userTourList;
		
		if (id != null) {
			Integer t_id = Integer.parseInt(id);
			
			if (TourService.getInstance()
					.checkTourInUserList(user.getId(), t_id)
					.isPresent()) {
				request.setAttribute("error", "You already have this tour.");
			} else {
				TourService.getInstance().createUserTour(user.getId(), t_id);
			}
		} else {
			request.setAttribute("error", "Please, choose any tour.");
		}
		
		userTourList = TourBuilderService.getInstance().getUserTours(user.getId());
		MethodsUtils.setPrice(userTourList, user);
	
		request.setAttribute("userTourList", userTourList);
		return ConstantValues.USERS_TOURS;
	}
}
