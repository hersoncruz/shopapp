package com.thinkcloudgroup.shopapp.objects;

import org.springframework.data.annotation.Id;

public class Venue {
	@Id private String id;

	private String companyId;
	private String name;
	private String description;
	private String type;
	private String status;
	private String address;
	private String city;
	private String state;
	private String country;
	private String mapUrl;
	
	// Getters
	public String  getId() { return id; }
	public String  getCompanyId() { return companyId; }
	public String  getName() { return name; }
	public String  getDescription() { return description; }
	public String  getType() {return type; }
	public String  getStatus() { return status; }
	public String  getAddress() { return address; }
	public String  getCity() { return city; }
	public String  getState() { return state; }
	public String  getCountry() { return country; }
	public String  getMapUrl() { return mapUrl; }

	// Setters
	public void setId(String id) { this.id = id; }
	public void setCompanyId(String companyId) { this.companyId = companyId; }
	public void setName(String name) { this.name = name; }
	public void setDescription(String description) { this.description = description; }
	public void setType(String type) { this.type = type; }
	public void setStatus(String status) { this.status = status; }
	public void setAddress(String address) { this.address = address; }
	public void setCity(String city) { this.city= city; }
	public void setState(String state) { this.state = state; }
	public void setCountry(String country) { this.country = country; }
	public void setMapUrl(String mapUrl) { this.mapUrl = mapUrl; }
}
