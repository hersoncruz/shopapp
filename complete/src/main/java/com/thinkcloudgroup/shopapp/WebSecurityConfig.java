package com.thinkcloudgroup.shopapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

//import com.thinkcloudgroup.shopapp.model.SecUserDetails;
import com.thinkcloudgroup.shopapp.model.UserRepository;
import com.thinkcloudgroup.shopapp.objects.User;
//import com.thinkcloudgroup.shopapp.service.SecUserDetailsService;
import com.thinkcloudgroup.shopapp.service.UserServiceImpl;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserServiceImpl userDetailsService;
	//SecUserDetailsService userDetailsService;
	
    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
        // @formatter:off
	/*auth.inMemoryAuthentication()
	  .withUser("john").password("123").roles("USER").and()
	  .withUser("tom").password("111").roles("ADMIN").and()
	  .withUser("user1").password("pass").roles("USER").and()
	  .withUser("admin").password("nimda").roles("ADMIN");*/
    	auth.userDetailsService(userDetailsService);
    }// @formatter:on
    
   /* @Autowired
    public void configAuthBuilder(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService); 
    }
    */

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
       
    	/*@Component class SecUserDetailsService implements UserDetailsService{

    	    @Autowired
    	    private UserRepository userRepository;

    	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	        /*Here add user data layer fetching from the MongoDB.
    	          I have used userRepository*/
    	  /*      User user = userRepository.findByUsername(username);
    	        if(user == null){
    	            throw new UsernameNotFoundException(username);
    	        }else{
    	            UserDetails details = new SecUserDetails(user);
    	            return details;
    	        }
    	    }
    	}*/
    	
    	return super.authenticationManagerBean();

    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
		http.authorizeRequests().antMatchers("/login").permitAll()
		.antMatchers("/oauth/token/revokeById/**").permitAll()
		.antMatchers("/tokens/**").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().permitAll()
		.and().csrf().disable();
    	/*http.requestMatchers().antMatchers("/","/user/**","/venue/**")
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        .antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('read')")
        .antMatchers(HttpMethod.PATCH, "/**").access("#oauth2.hasScope('write')")
        .antMatchers(HttpMethod.POST, "/**").access("#oauth2.hasScope('write')")
        .antMatchers(HttpMethod.PUT, "/**").access("#oauth2.hasScope('write')")
        .antMatchers(HttpMethod.DELETE, "/**").access("#oauth2.hasScope('write')");
        */
		// @formatter:on
    }

}
