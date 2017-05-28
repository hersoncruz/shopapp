package com.thinkcloudgroup.shopapp.service;

import java.util.List;

import com.thinkcloudgroup.shopapp.objects.User;

public interface IUserService {
	public List<User> getAllObjects();
	
	public User create(User obj);
	
	public void delete(String id);
	
	public User update(String id, User user);

	public User findById(String id);

	public User findByUsername(String username);

	public boolean isAccountNonLocked();
}
