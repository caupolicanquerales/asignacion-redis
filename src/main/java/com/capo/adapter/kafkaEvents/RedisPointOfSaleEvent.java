package com.capo.adapter.kafkaEvents;

public class RedisPointOfSaleEvent {
	private String id;
	private String location;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
