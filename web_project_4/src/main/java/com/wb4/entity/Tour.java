package com.wb4.entity;

import com.wb4.enums.City;
import com.wb4.enums.Country;
import com.wb4.enums.TourState;
import com.wb4.enums.TourType;

public class Tour {
	protected Integer id;
	protected Country country;
	protected City city;
	protected Integer tourDuration;
	
	protected TourState tourState;
	protected TourType tourType;
	
	protected Accommodation accommodation;
	protected Transport transport;
	protected Double price;
	
	public static class Builder {
		protected Integer id;
		protected Country country;
		protected City city;
		protected Integer tourDuration = 1;
		
		protected TourState tourState = null;
		protected TourType tourType = null;
		
		public Builder(Country country, City city) {
			this.country = country;
			this.city = city;
		}
		
		public Builder setId(Integer id) {
			this.id = id;
			return this;
		}

		public Builder setDayQuantity(Integer tourDuration) {
			this.tourDuration = tourDuration;
			return this;
		}

		public Builder setTourState(TourState tourState) {
			this.tourState = tourState;
			return this;
		}

		public Builder setTourType(TourType tourType) {
			this.tourType = tourType;
			return this;
		}
		
		public Tour build() {
			return new Tour(this);
		}
	}
	
	protected Tour(Builder builder) {
		this.id = builder.id;
		this.country = builder.country;
		this.city = builder.city;
		this.tourDuration = builder.tourDuration;
		this.tourState = builder.tourState;
		this.tourType = builder.tourType;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public int getTourDuration() {
		return tourDuration;
	}

	public void setTourDuration(Integer tourDuration) {
		this.tourDuration = tourDuration;
	}

	public TourState getTourState() {
		return tourState;
	}

	public void setTourState(TourState tourState) {
		this.tourState = tourState;
	}

	public TourType getTourType() {
		return tourType;
	}

	public void setTourType(TourType tourType) {
		this.tourType = tourType;
	}

	public Accommodation getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(Accommodation accommodation) {
		this.accommodation = accommodation;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}

