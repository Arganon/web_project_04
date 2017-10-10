package com.wb4.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wb4.entity.Accommodation;
import com.wb4.entity.Tour;
import com.wb4.entity.Transport;
import com.wb4.entity.User;
import com.wb4.enums.AccommodationType;
import com.wb4.enums.City;
import com.wb4.enums.ClientType;
import com.wb4.enums.Country;
import com.wb4.enums.TourState;
import com.wb4.enums.TourType;
import com.wb4.enums.TransportType;
import com.wb4.enums.UserRole;

public class EntityBuilder {

	public static User getUserObject(ResultSet resultSet) {
		User user = null;
	
		try {
			user = new User.Builder(resultSet.getString("u_login"), resultSet.getString("u_password"))
					.setId(resultSet.getInt("u_id"))
					.setFirstName(resultSet.getString("u_firstName"))
					.setMiddleName(resultSet.getString("u_middleName"))
					.setLastName(resultSet.getString("u_lastName"))
					.setEmail(resultSet.getString("u_email"))
					.setUserRole(UserRole.valueOf(resultSet.getString("u_role")))
					.setClientType(ClientType.valueOf(resultSet.getString("u_client_type")))
					.build();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return user;
	}
	
	public static Accommodation getAccommodationObject(ResultSet resultSet) {
		Accommodation accommodation = null;
		
		try {
			accommodation = new Accommodation.Builder(resultSet.getString("acmd_name"), resultSet.getDouble("price_per_night"))
					.setId(resultSet.getInt("acmd_id"))
					.setAccommodationType(AccommodationType.valueOf(resultSet.getString("acmd_type")))
					.setVacationistQuantity(resultSet.getInt("vac_quantity"))
					.setPricePerNight(resultSet.getDouble("price_per_night"))
					.build();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return accommodation;
	}
	
	public static Tour getTourObject(ResultSet resultSet) {
		Tour tour = null;
		
		try {
			tour = new Tour.Builder(Country.valueOf(resultSet.getString("country_name")), City.valueOf(resultSet.getString("city_name")))
					.setId(resultSet.getInt("t_id"))
					.setDayQuantity(resultSet.getInt("t_duration"))
					.setTourState(TourState.valueOf(resultSet.getString("t_state")))
					.setTourType(TourType.valueOf(resultSet.getString("t_type")))
					.build();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return tour;
	}
	
	public static Transport getTransportObject(ResultSet resultSet) {
		Transport transport = null;
		
		try {
			transport = new Transport.Builder()
					.setGeneralTransportType((TransportType.valueOf(resultSet.getString("trans_type"))))
					.setGeneralTransportPrice(resultSet.getDouble("trans_price"))
					.build();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return transport;
	}
	
	public static Integer getTourId(ResultSet resultSet) {
		Integer tourId = null;
		
		try {
			tourId = resultSet.getInt("t_id");
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return tourId;
	}
	
	public static Double makeDiscount(ResultSet resultSet) {
		Double discount = null;
		
		try {
			discount = resultSet.getDouble("d_discount");
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return discount;
	}
	
	public static ArrayList<Integer> getTourAcmdTrans(ResultSet resultSet) {
		ArrayList<Integer> tourAcmdTrans = new ArrayList<Integer>();
		
		try {
			tourAcmdTrans.add(resultSet.getInt("t_id"));
			tourAcmdTrans.add(resultSet.getInt("acmd_id"));
			tourAcmdTrans.add(resultSet.getInt("trans_id"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return tourAcmdTrans;
	}
}
