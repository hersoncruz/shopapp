package com.thinkcloudgroup.shopapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.thinkcloudgroup.shopapp.service.SecUserDetailsService;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	//UserServiceImpl userDetailsService;
	SecUserDetailsService userDetailsService;
	
    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
        // @formatter:off
    	/*auth.inMemoryAuthentication()
		  .withUser("john").password("123").roles("USER").and()
		  .withUser("tom").password("111").roles("ADMIN").and()
		  .withUser("user1").password("pass").roles("USER").and()
		  .withUser("admin").password("nimda").roles("ADMIN").and()
		  .withUser("darmandovargas").password("asjdflasjdf").roles("ADMIN");*/
    	auth.userDetailsService(userDetailsService);
    }// @formatter:on
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
    	return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
		http.authorizeRequests().antMatchers("/login").permitAll()
		.antMatchers("/registration").permitAll()
		.antMatchers("/forgotPassword").permitAll()
		.antMatchers("/resetPassword").permitAll()
		.antMatchers("/changePassword").permitAll()
		.antMatchers("/oauth/token/revokeById/**").permitAll()
		.antMatchers("/tokens/**").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().permitAll()
		.and().csrf().disable();
		// @formatter:on
    }

}
