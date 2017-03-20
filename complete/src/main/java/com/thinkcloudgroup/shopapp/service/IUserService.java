package com.thinkcloudgroup.shopapp.service;

import java.util.List;

import com.thinkcloudgroup.shopapp.objects.User;

public interface IUserService {
	public List<User> getAllObjects();
	
	public User create(User obj);
	
	public void delete(String id);
	
	public User update(String id, String str);

	public User findById(String id);
}
