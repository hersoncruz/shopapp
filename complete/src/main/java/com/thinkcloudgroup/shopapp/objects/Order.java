package com.thinkcloudgroup.rest;


import java.util.List;

import org.springframework.data.annotation.Id;

public class Order {
	@Id private String id;

	private List<String> productList;
	
	private String orderid;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getProductList() {
		return productList;
	}
	public void setProductList(List<String> productList) {
		this.productList = productList;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
}
	
	