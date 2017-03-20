package com.thinkcloudgroup.shopapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkcloudgroup.shopapp.model.UserRepository;
import com.thinkcloudgroup.shopapp.objects.User;

import com.thinkcloudgroup.shopapp.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	private final UserRepository repo;
	
	@Autowired
	public UserServiceImpl(UserRepository repo){
		this.repo = repo;
	}
	
	@Override
	public List<User> getAllObjects() {
		return repo.findAll();
	}

	@Override
	public User create(User obj) {
		return repo.save(obj);
	}

	@Override
	public void delete(String id) {
		repo.delete(id);
	}

	@Override
	public User update(String id, String str) {
		User object = findById(id);
		//object.setFirstName(str.firstName); //setFirs(str);
		return repo.save(object);
	}

	@Override
	public User findById(String id) {
		return repo.findOne(id);
	}
}
