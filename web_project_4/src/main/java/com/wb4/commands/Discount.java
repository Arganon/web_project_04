package com.wb4.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wb4.services.ConstantValues;
import com.wb4.services.DiscountService;

public class Discount implements Commands {
	protected static Discount instance = null;
	
	protected Discount() {}
	
	public static Discount getInstance() {
		if (instance == null) {
			instance = new Discount();
		}
		return instance;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Double discount = Double.parseDouble(request.getParameter("discount"));
		DiscountService.getInstance().updateDiscount(discount);
		System.out.println(discount + " from discount");
		
		return ConstantValues.MANAGER_PAGE;
	}
	
}
