package com.thinkcloudgroup.shopapp.objects;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

@Validated
public class UserCompany {
	@Id private String id;
	private String userId;
	private String companyId;
    public List<Role> roles;
    public Date created;
    public String createdBy;
    public Date modified;
    public String modifiedBy;

    // Getters
	public String getId() { return id; }
	public String getUserId() { return userId; }
	public String getCompanyId() { return companyId; }
	public List<Role> getRoles() { return roles; }
	public Date getCreated() { return created; }
	public String getCreatedBy() { return createdBy; }
	public Date getModified() { return modified; }
	public String getModifiedBy() { return modifiedBy; }
	
	// Setters
	public void setId(String id) { this.id = id; }
	public void setRole(List<Role> roles) { this.roles = roles; }
	public void setCreated(Date created) {this.created = created; }
	public void setCreatedBy(String createdBy) {this.createdBy = createdBy; }
	public void setModified(Date modified) {this.modified = modified; }
	public void setModifiedBy(String modifiedBy) {this.modifiedBy = modifiedBy; }
}
