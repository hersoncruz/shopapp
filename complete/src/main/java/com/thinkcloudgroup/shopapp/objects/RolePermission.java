package com.thinkcloudgroup.shopapp.objects;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

@Validated
public class RolePermission {
	@Id private String id;
	private String roleId;
	private String permissionId;
    public Date created;
    public String createdBy;
    public Date modified;
    public String modifiedBy;

	// Getters
	public String getId() { return id; }
	public String getRoleId() { return roleId; }
	public String getPermissionId() { return permissionId; }
	public Date getCreated() { return created; }
	public String getCreatedBy() { return createdBy; }
	public Date getModified() { return modified; }
	public String getModifiedBy() { return modifiedBy; }

	// Setters
	public void setId(String id) { this.id = id; }
	public void setRoleId(String roleId) { this.roleId = roleId; }
	public void setPermissionId(String permissionId) { this.permissionId = permissionId; }
	public void setCreated(Date created) {this.created = created; }
	public void setCreatedBy(String createdBy) {this.createdBy = createdBy; }
	public void setModified(Date modified) {this.modified = modified; }
	public void setModifiedBy(String modifiedBy) {this.modifiedBy = modifiedBy; }
}
