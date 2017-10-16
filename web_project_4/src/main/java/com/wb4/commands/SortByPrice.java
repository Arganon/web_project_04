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
import com.wb4.utilites.MethodsUtils;

public class SortByPrice implements Commands {
	
	private static class Holder {
		private static SortByPrice INSTANCE = new SortByPrice();
	}
	
	public static SortByPrice getInstance() {
		return SortByPrice.Holder.INSTANCE;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path_to_go = (String) request.getSession().getAttribute("pageName");
		
		@SuppressWarnings("unchecked")
		List<Tour> tourList = (List<Tour>) request.getSession().getAttribute("tourList");
		
		MethodsUtils.sortByPrice(tourList);
		
		return path_to_go;
	}

}
