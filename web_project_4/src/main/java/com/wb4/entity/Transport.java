package com.wb4.entity;

import com.wb4.enums.TransportType;

public class Transport {
	protected int id;
	protected final TransportType generalTransportType;
	protected final Double generalTransportPrice;
	
	public static class Builder {
		protected TransportType generalTransportType;
		protected Double generalTransportPrice;
		
		public Builder setGeneralTransportType(TransportType generalTransportType) {
			this.generalTransportType = generalTransportType;
			return this;
		}

		public Builder setGeneralTransportPrice(Double generalTransportPrice) {
			this.generalTransportPrice = generalTransportPrice;
			return this;
		}

		public Transport build() {
			return new Transport(this);
		}
	}
	
	protected Transport(Builder builder) {
		this.generalTransportType = builder.generalTransportType;
		this.generalTransportPrice = builder.generalTransportPrice;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public TransportType getGeneralTransportType() {
		return generalTransportType;
	}

	public Double getGeneralTransportPrice() {
		return generalTransportPrice;
	}
	
	@SuppressWarnings("null")
	public String toString() {
		StringBuffer info = null;
		
		info.append("General transport: " + generalTransportType + "\n" +
					"Transportation price: " + generalTransportPrice + "\n");
		
		return info.toString();
	}
}
