package com.thinkcloudgroup.shopapp.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.Param;

import com.thinkcloudgroup.shopapp.objects.User;

public interface IUserService {
	public List<User> getAllObjects();
	
	public User create(User obj);
	
	public void delete(String id);
	
	public User update(String id, User user);

	public User findById(String id);

	public List<User> findByUsername(String username);
}
