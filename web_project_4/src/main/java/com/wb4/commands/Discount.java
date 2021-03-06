package com.wb4.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wb4.services.ConstantValues;
import com.wb4.services.DiscountService;

public class Discount implements Commands {
	protected final static String DISCOUNT = "discount";
	
	private Discount() {}
	
	private static class Holder {
		private static Discount INSTANCE = new Discount();
	}
	
	public static Discount getInstance() {
		return Discount.Holder.INSTANCE;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Double discount = Double.parseDouble(request.getParameter(DISCOUNT));
		DiscountService.getInstance().updateDiscount(discount);
		
		return ConstantValues.MANAGER_PAGE;
	}
	
}
