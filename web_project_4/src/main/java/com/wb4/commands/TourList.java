package com.wb4.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wb4.entity.Tour;
import com.wb4.entity.User;
import com.wb4.services.ConstantValues;
import com.wb4.services.TourBuilderService;
import com.wb4.utilites.MethodsUtils;

public class TourList implements Commands {
	protected List<Tour> tourList = new ArrayList<Tour>();
	
	protected TourList() {}
	
	private static class Holder {
		private static TourList INSTANCE = new TourList();
	}
	
	public static TourList getInstance() {
		return TourList.Holder.INSTANCE;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path_to_go = ConstantValues.TOUR_LIST;
		List<Tour> tourList = TourBuilderService.getInstance().getAllTours();
		User user = (User) request.getSession().getAttribute("USER");
		
		MethodsUtils.setPrice(tourList, user);

		String userRole = user.getUserRole().toString();
		
		request.setAttribute("role", userRole);
		request.setAttribute("tourList", tourList);
		
		if (userRole.equals("MANAGER")) {
			path_to_go = ConstantValues.MANAGER_PANEL;
		}
		return path_to_go;
	}
}
