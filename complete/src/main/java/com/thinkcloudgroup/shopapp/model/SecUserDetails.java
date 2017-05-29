package com.thinkcloudgroup.shopapp.model;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.thinkcloudgroup.shopapp.objects.User;
import com.thinkcloudgroup.shopapp.service.IUserService;

public class SecUserDetails implements UserDetails, IUserService, UserDetailsService {
	private UserRepository repo;

    private User user;

    public SecUserDetails(User user) {
    	
        this.user = user;
    }

    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<User> getAllObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User create(User obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		repo.delete(id);
	}

	@Override
	public User update(String id, User user) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return repo.findOne(id);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return this.user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//return this;
		 User user = repo.findByUsername(username);
	     if(user == null){
	         throw new UsernameNotFoundException(username);
	     }else{
	         UserDetails details = new SecUserDetails(user);
	         return details;
	     }
	    // return this;
	}

	
	
   
}
