package com.thinkcloudgroup.shopapp.objects;


import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Order {
	@Id private String id;

	private List<String> productList;
	private String orderId;
	private String userId;
	private Date created;
	private String createdBy;
	private String status;
	private Double tax;
	private Double discount;
	private Double total;
	
	// Getters
	public String getId() { return id; }
	public List<String> getProductList() { return productList; }
	public String getOrderId() { return orderId; }
	public String getUserId() { return userId; }
	public Date getCreated() { return created; }
	public String getCreatedBy() { return createdBy; }
	public String getStatus() { return status; }
	public Double getTax() { return tax; }
	public Double getDiscount() { return discount; }
	public Double getTotal() { return total; }
	
	// Setters
	public void setId(String id) { this.id = id; }
	public void setProductList(List<String> productList) { this.productList = productList; }
	public void setOrderId(String orderId) { this.orderId = orderId; }
	public void setUserId(String userId) { this.userId = userId; }
	public void setCreated(Date created) { this.created = created; }
	public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
	public void setStatus(String status) { this.status = status; }
	public void setTax(Double tax) { this.tax = tax; }
	public void setDiscount(Double discount) { this.discount = discount; }
	public void setTotal(Double total) { this.total = total; }
}
	
	