package com.thinkcloudgroup.rest;


import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Order {
	@Id private String id;

	private List<String> productList;
	private String orderId;
	private Date date;
	private String status;
	private String userId;
	private Double amount;
	
	// Getters
	public String getId() { return id; }
	public List<String> getProductList() { return productList; }
	public String getOrderId() { return orderId; }
	public Date getDate() { return date; }
	public String getStatus() { return status; }
	public String getUserId() { return userId; }
	public Double getAmount() { return amount; }
	
	// Setters
	public void setId(String id) { this.id = id; }
	public void setProductList(List<String> productList) { this.productList = productList; }
	public void setOrderId(String orderId) { this.orderId = orderId; }
	public void setDate(Date date) { this.date = date; }
	public void setStatus(String status) { this.status = status; }
	public void setUserId(String userId) { this.userId = userId; }
	public void setAmount(Double amount) { this.amount = amount; }
}
	
	