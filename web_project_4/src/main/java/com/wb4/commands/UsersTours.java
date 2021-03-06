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
	private final static String ERROR = "error";
	private final static String USER_HAS_THIS_TOUR = "You already have this tour.";
	private final static String TOUR_ID = "tourId";
	
	protected UsersTours() {}

	private static class Holder {
		private static UsersTours INSTANCE = new UsersTours();
	}
	
	public static UsersTours getInstance() {
		return UsersTours.Holder.INSTANCE;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("USER");
		String id = request.getParameter(TOUR_ID);
		List<Tour> userTourList;
		
		if (id != null) {
			Integer t_id = Integer.parseInt(id);
			
			if (TourService.getInstance()
					.checkTourInUserList(user.getId(), t_id)
					.isPresent()) {
				request.setAttribute(ERROR, USER_HAS_THIS_TOUR);
			} else {
				TourService.getInstance().createUserTour(user.getId(), t_id);
			}
		}
		
		userTourList = TourBuilderService.getInstance().getUserTours(user.getId());
		MethodsUtils.setPrice(userTourList, user);
	
		request.getSession().setAttribute("tourList", userTourList);
		return ConstantValues.USERS_TOURS;
	}
}
