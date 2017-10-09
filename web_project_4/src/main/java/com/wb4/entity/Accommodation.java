package com.wb4.entity;

import com.wb4.enums.AccommodationType;

public class Accommodation {
	protected int id;
	protected final String accommodationName;
	protected final AccommodationType accommodationType;
	protected final Integer vacationistQuantity;
	protected final Double pricePerNight;
	
	public static class Builder {
		protected Integer id;
		protected String accommodationName;
		protected AccommodationType accommodationType;
		protected Integer vacationistQuantity = 1;
		protected Double pricePerNight;
		
		public Builder(String accommodationName, Double pricePerNight) {
			this.accommodationName = accommodationName;
			this.pricePerNight = pricePerNight;
		}
		
		public Builder setId(Integer id) {
			this.id = id;
			return this;
		}
		
		public Builder setAccommodationType(AccommodationType accommodationType) {
			this.accommodationType = accommodationType;
			return this;
		}
		
		public Builder setVacationistQuantity(Integer vacationistQuantity) {
			this.vacationistQuantity = vacationistQuantity;
			return this;
		}
		
		public Builder setPricePerNight(Double price) {
			this.pricePerNight = price;
			return this;
		}
		
		public Accommodation build() {
			return new Accommodation(this);
		}
	}
	
	protected Accommodation(Builder builder) {
		this.accommodationName = builder.accommodationName;
		this.accommodationType = builder.accommodationType;
		this.vacationistQuantity = builder.vacationistQuantity;
		this.pricePerNight = builder.pricePerNight;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getAccommodationName() {
		return accommodationName;
	}

	public AccommodationType getAccommodationType() {
		return accommodationType;
	}

	public int getVacationistQuantity() {
		return vacationistQuantity;
	}

	public Double getPricePerNight() {
		return pricePerNight;
	}
	
	@SuppressWarnings("null")
	public String toString() {
		StringBuffer info = null;
		
		info.append("Accommodation name: " + accommodationName + "\n" +
					"Accommodation type: " + accommodationType + "\n" +
					"Places quantity: " + vacationistQuantity + "\n" +
					"Accommodation price per night: " + pricePerNight + "\n\n");
		
		return info.toString();
	}
}

