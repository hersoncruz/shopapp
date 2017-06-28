package com.thinkcloudgroup.shopapp.service;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.thinkcloudgroup.shopapp.model.SecUserDetails;
//import com.thinkcloudgroup.shopapp.model.SecUserDetails;
import com.thinkcloudgroup.shopapp.model.UserRepository;
import com.thinkcloudgroup.shopapp.model.VerificationToken;
import com.thinkcloudgroup.shopapp.model.VerificationTokenRepository;
import com.thinkcloudgroup.shopapp.objects.User;

import com.thinkcloudgroup.shopapp.service.IUserService;


/*
import src.main.java.org.baeldung.service.Override;
import src.main.java.org.baeldung.service.String;
import src.main.java.org.baeldung.service.VerificationToken;
import src.main.java.org.baeldung.service.VerificationTokenRepository;
*/
/*
import src.main.java.org.baeldung.service.Override;
import src.main.java.org.baeldung.service.PasswordResetToken;
import src.main.java.org.baeldung.service.String;
*/
@Service
public class UserServiceImpl implements IUserService {
	private final UserRepository repo;
	
	@Autowired
	private VerificationTokenRepository tokenRepository;
	
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
/*	
	 @Override
	 public User getUser(final String verificationToken) {
		 final VerificationToken token = tokenRepository.findByToken(verificationToken);
	     if (token != null) {
	        return token.getUser();
	     }
	     return null;
	 }

	@Override
	public VerificationToken generateNewVerificationToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	 @Override
	 public void createPasswordResetTokenForUser(final User user, final String token) {
		 //final PasswordResetToken myToken = new PasswordResetToken(token, user);
	//	 final PasswordResetToken myToken = new PasswordResetToken(token, user);
	//     passwordTokenRepository.save(myToken);
		 //return null;
	 }*/

	@Override
	public User createPasswordResetTokenForUser(User user, String token) {
		User updatedUserResetoken = findById(user.getId());
		updatedUserResetoken.setResetoken(token);
		return repo.save(updatedUserResetoken);
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean validatePasswordResetToken(String id, String token, String newpassword) {
		User passTokenUser = findById(id);
		
		if ((passTokenUser == null) || passTokenUser.getId().compareTo(id) != 0 || passTokenUser.getResetoken().compareTo(token) != 0) {
			return false;
		}else{
			passTokenUser.setPassword(newpassword);
			passTokenUser.setResetoken("");
			repo.save(passTokenUser);
			return true;
		}
		
		
		
		//return repo.save(updatedUserResetoken);
        /*final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);
        if ((passToken == null) || (passToken.getUser()
            .getId() != id)) {
            return "invalidToken";
        }

        final Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate()
            .getTime() - cal.getTime()
            .getTime()) <= 0) {
            return "expired";
        }

        final User user = passToken.getUser();
        final Authentication auth = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
        SecurityContextHolder.getContext()
            .setAuthentication(auth);
        return null;*/
    }
	
}
