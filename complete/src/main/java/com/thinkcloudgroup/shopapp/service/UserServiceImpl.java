package com.thinkcloudgroup.shopapp.service;

import java.util.List;
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
	public User update(String id, User user) {
		User updatedUser = findById(id);
		updatedUser.setFirstName(user.getFirstName());
		updatedUser.setLastName(user.getLastName());
		updatedUser.setUsername(user.getUsername());
		updatedUser.setPassword(user.getPassword());
		updatedUser.setAddress(user.getAddress());
		updatedUser.setCity(user.getCity());
		updatedUser.setCountry(user.getCountry());
		updatedUser.setActivated(user.getActivated());
		updatedUser.setActivationCode(user.getActivationCode());
//		updatedUser.setRole(user.getRole());
		return repo.save(updatedUser);
	}

	@Override
	public User findById(String id) {
		return repo.findOne(id);
	}

	@Override
	public User findByUsername(String username) {
		return repo.findByUsername(username);
	}
}
