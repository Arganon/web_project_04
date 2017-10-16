package com.wb4.utilites;

import java.util.List;

import com.wb4.entity.Tour;
import com.wb4.entity.User;
import com.wb4.services.DiscountService;

public class MethodsUtils {
	
	public static void setPrice(List<Tour> tourList, User user) {
		Double discount = DiscountService.getInstance().getDiscount().get();
		
		for (Tour t : tourList) {
			Double price = t.getTourDuration()*t.getAccommodation().getPricePerNight() + t.getTransport().getGeneralTransportPrice();
			if (user.getClientType().toString().equals("REGULAR")) {
				price *= discount;
			}
			t.setPrice(price);
		}
	}
	
	public static void sortByPrice(List<Tour> tourList) {
		tourList.sort((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()));
	}
}
