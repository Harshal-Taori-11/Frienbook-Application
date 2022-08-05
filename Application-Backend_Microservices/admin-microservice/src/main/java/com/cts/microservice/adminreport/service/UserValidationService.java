package com.cts.microservice.adminreport.service;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.microservice.adminreport.config.SecurityConfig;
import com.cts.microservice.adminreport.exceptionhandling.AuthenticationException;

@Service
public class UserValidationService implements UserDetailsService {
	
	static Logger log = Logger.getLogger(UserValidationService.class);
	
	@Value("${admin.username}")
	private String username;
	
	@Value("${admin.password}")
	private String password;

	@Override
	public UserDetails loadUserByUsername(String userName) throws AuthenticationException {
		
		log.debug("Inside Load user by usernaem service");
		return new User(username, password ,new ArrayList<>());
	}
}
