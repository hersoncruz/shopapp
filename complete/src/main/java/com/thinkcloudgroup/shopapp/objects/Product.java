package com.thinkcloudgroup.shopapp.objects;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Product {
	@Id private String id;

	private String name;
	private String description;
	private String partNumber;
	private String barCode;
	private Double price;
	private Double bestPrice;
	private Double cost;
	private Date bestPriceBegin;
	private Date bestPriceEnd;
	private List<String> categories;
	
	// Getters
	public String getId() { return id; }
	public String getName() { return name; }
	public String getDescription() { return description; }
	public String getPartNumber() { return partNumber; }
	public String getBarCode() { return barCode; }
	public Double getPrice() { return price; }
	public Double getBestPrice() { return bestPrice; }
	public Double getCost() { return cost; }
	public Date bestPriceBegin() { return bestPriceBegin; }
	public Date bestPriceEnd() { return bestPriceEnd; }
	public List<String> categories() { return categories; }
	
	// Setters
	public void setId(String id) { this.id = id; }
	public void setName(String name) { this.name = name; }
	public void setDescription(String description) { this.description = description; }
	public void setPartNumber(String partNumber) { this.partNumber = partNumber; }
	public void setBarCode(String barCode) { this.barCode = barCode; }
	public void setPrice(Double price) { this.price = price; }
	public void setBestPrice(Double bestPrice) { this.bestPrice = bestPrice; }
	public void setcost(Double cost) { this.cost = cost; }
	public void setBestPriceStart(Date bestPriceBegin) { this.bestPriceBegin = bestPriceBegin; }
	public void setBestPriceEnd(Date bestPriceEnd) { this.bestPriceEnd = bestPriceEnd; }
}
