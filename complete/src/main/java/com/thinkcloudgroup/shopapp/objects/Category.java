package com.thinkcloudgroup.shopapp.objects;

import org.springframework.data.annotation.Id;

public class Category {
	@Id private String id;

	private String name;
	private String description;
	private String type; // Where does it apply? Company, Product, User
	private String status;
	private String parent="0";
	
	// Getters
	public String  getId() { return id; }
	public String  getName() { return name; }
	public String  getDescription() { return description; }
	public String  getType() {return type; }
	public String  getStatus() { return status; }
	public String  getParent() { return parent; }

	// Setters
	public void setId(String id) { this.id = id; }
	public void setName(String name) { this.name = name; }
	public void setDescription(String description) { this.description = description; }
	public void setType(String type) { this.type = type; }
	public void setStatus(String status) { this.status = status; }
	public void setParent(String parent) { this.parent = parent; }
}
