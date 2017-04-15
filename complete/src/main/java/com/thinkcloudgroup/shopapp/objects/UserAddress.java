package com.thinkcloudgroup.shopapp.objects;

import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

@Validated
public class UserAddress {
	@Id private String id;
	private String userId;
	private String firstName;
	private String lastName;
	private String companyName;
    private String address;
	private String address2;
	private String city;
	private String state;
	private String country;
	private String postalCode;
	private Boolean isDefault;
	private String type; // Shipping, Billing

	// Getters
	public String getId() { return id; }
	public String getUserId() { return userId; }
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public String getCompanyName() { return companyName; }
	public String getAddress() { return address; }
	public String getAddress2() { return address2; }
	public String getCity() { return city; }
	public String getState() { return state; }
	public String getCountry() { return country; }
	public String getPostalCode() { return postalCode; }
	public Boolean getIsDefault() { return isDefault; }
	public String getType() { return type; }
	
	// Setters
	public void setId(String id) { this.id = id; }
	public void setUserId(String userId) { this.userId = userId; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public void setCompanyName(String companyName) { this.companyName = companyName; }
	public void setAddress(String address) { this.address = address; }
	public void setAddress2(String address2) { this.address2 = address2; }
	public void setCity(String city) { this.city = city; }
	public void setState(String state) { this.state = state; }
	public void setCountry(String country) { this.country = country; }
	public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
	public void setIsDefault(Boolean isDefault) { this.isDefault= isDefault; }
	public void setType(String type) { this.type = type; }
}
