package com.thinkcloudgroup.rest;

import org.springframework.data.annotation.Id;

public class ProductMeta {
	@Id private String id;

	private String productId;
	private String key;
	private String value;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String id) {
		this.productId = id;
	}
	public String getKey() {
		return key;
	}
	public void seKeyDescription(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
