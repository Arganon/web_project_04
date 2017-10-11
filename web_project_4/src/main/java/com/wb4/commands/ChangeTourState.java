package com.wb4.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wb4.entity.Tour;
import com.wb4.entity.User;
import com.wb4.services.TourService;

public class ChangeTourState implements Commands {	
	private ChangeTourState() {}
	
	private static class Holder {
		protected static ChangeTourState INSTANCE = new ChangeTourState();
	}
	
	public static ChangeTourState getInstance() {
		return ChangeTourState.Holder.INSTANCE;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		@SuppressWarnings("unchecked")
		List<Tour> toursList = (ArrayList<Tour>) request.getSession().getAttribute("tours");
		
		if (toursList != null) {
			for (Tour t : toursList) {
				String tourState = request.getParameter(Integer.toString(t.getId()));
				if (!tourState.equals("STAY")) {
					TourService.getInstance().changeTourState(t.getId(), tourState);
				}
			}
		}	
	
		return UsersPageRedirect.getInstance().getUserPage(((User)request
				.getSession().getAttribute("USER"))
				.getUserRole().toString());
	}

}
