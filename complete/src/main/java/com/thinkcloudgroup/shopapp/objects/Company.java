package com.thinkcloudgroup.shopapp.objects;

import org.springframework.data.annotation.Id;

public class Company {
	@Id private String id;

	private String name;
	private String description;
	private String type;
	private String status;
	
	// Getters
	public String getId() { return id; }
	public String getName() { return name; }
	public String getDescription() { return description; }
	public String getType() { return type; }
	public String getStatus() { return status; }
	
	// Setters
	public void setId(String id) { this.id = id; }
	public void setName(String name) { this.name = name; }
	public void setDescription(String description) { this.description = description; }
	public void setType(String type) { this.type = type; }
	public void setStatus(String status) { this.status = status; }
}
