package com.thinkcloudgroup.shopapp.objects;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Product {
	@Id private String id;

	private String name;
	private String description;
	private String partNumber;
	private String barCode;
	private Float price;
	private Float bestPrice;
	private Float cost;
	private Date bestPriceStart;
	private Date bestPriceEnds;
	
	// Getters
	public String getId() { return id; }
	public String getName() { return name; }
	public String getDescription() { return description; }
	public String getPartNumber() { return partNumber; }
	public String getBarCode() { return barCode; }
	
	// Setters
	public void setId(String id) { this.id = id; }
	public void setName(String name) { this.name = name; }
	public void setDescription(String description) { this.description = description; }
	public void setPartNumber(String partNumber) { this.partNumber = partNumber; }
	public void setBarCode(String barCode) { this.barCode = barCode; }
}
