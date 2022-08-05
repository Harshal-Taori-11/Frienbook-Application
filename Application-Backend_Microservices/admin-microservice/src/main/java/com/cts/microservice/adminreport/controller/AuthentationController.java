package com.cts.microservice.adminreport.controller;

import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.microservice.adminreport.config.SecurityConfig;
import com.cts.microservice.adminreport.exceptionhandling.AuthenticationException;
import com.cts.microservice.adminreport.model.JwtAuthCredentials;
import com.cts.microservice.adminreport.model.JwtTokenResponse;
import com.cts.microservice.adminreport.service.UserValidationService;
import com.cts.microservice.adminreport.util.JwtUtil;


@RestController
@CrossOrigin
public class AuthentationController {
	
	static Logger log = Logger.getLogger(AuthentationController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserValidationService userValidationService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;

	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthCredentials authenticationRequest)
			throws AuthenticationException {
		
		log.debug("Authenticating Admin");
		
		Objects.requireNonNull(authenticationRequest.getUsername());
		Objects.requireNonNull(authenticationRequest.getPassword());

		
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
					(authenticationRequest.getUsername(), authenticationRequest.getPassword()));	
		
		
		final UserDetails userDetails = userValidationService
				.loadUserByUsername(authenticationRequest.getUsername());
		
		log.debug("Generating JWT Token");

		final String token = jwtTokenUtil.generateToken(userDetails);

		log.debug("Token creation completed, Sending token to admin ,Authentication done");
		
		return ResponseEntity.ok(new JwtTokenResponse(token));
	}
}
