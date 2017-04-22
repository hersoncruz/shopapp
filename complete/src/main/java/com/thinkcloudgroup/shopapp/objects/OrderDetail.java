package com.thinkcloudgroup.shopapp.objects;


import org.springframework.data.annotation.Id;

public class OrderDetail {
	@Id private String id;

	private String orderId;
	private String productId;
	private String quantity;
	private Double unitPrice;
	private Double tax;
	private Double discount=0.00;
	private Double total;
	
	// Getters
	public String getId() { return id; }
	public String getOrderId() { return orderId; }
	public String getProductId() { return productId; }
	public String getQuantity() { return quantity; }
	public Double getUnitPrice() { return unitPrice; }
	public Double getTax() { return tax; }
	public Double getDiscount() { return discount; }
	public Double getTotal() { return total; }
	
	// Setters
	public void setId(String id) { this.id = id; }
	public void setOrderId(String orderId) { this.orderId = orderId; }
	public void setProductId(String productId) { this.productId = productId; }
	public void setDate(String quantity) { this.quantity = quantity; }
	public void setStatus(Double unitPrice) { this.unitPrice = unitPrice; }
	public void setUserId(Double tax) { this.tax = tax; }
	public void setDiscount(Double discount) { this.discount = discount; }
	public void setTotal(Double total) { this.total = total; }
	
	public Double calcualteTotal() {
		total = (Integer.valueOf(this.quantity) * this.unitPrice) + this.tax - this.discount;
		return total;
	}
}
	
	