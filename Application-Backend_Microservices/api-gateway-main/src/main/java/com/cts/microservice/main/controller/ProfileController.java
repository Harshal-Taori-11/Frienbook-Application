package com.cts.microservice.main.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.microservice.main.model.JwtTokenResponse;
import com.cts.microservice.main.model.Profile;
import com.cts.microservice.main.model.UserCredential;
import com.cts.microservice.main.service.ProfileService;
import com.cts.microservice.main.service.UserValidationService;
import com.cts.microservice.main.util.JwtUtil;

@RestController
@CrossOrigin
public class ProfileController {

	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserValidationService userValidationService;
	
	@Autowired
	private ProfileService pService;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@GetMapping("/hello")
	public String hello() {

		return "Hello World";
	}
	
	@GetMapping("/jpa/users/")
	public List<UserCredential> getAllUser() {
		return userValidationService.getAllUsers();
	}
	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserCredential authenticationRequest)
			throws Exception {

		String password= bcryptEncoder.encode(authenticationRequest.getPassword());
		System.out.println("PWD : "+ password);
		Objects.requireNonNull(authenticationRequest.getUsername());
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (DisabledException e) {
//			throw new AuthenticationException("USER_DISABLED", e);
			System.out.println("USER_DISABLED");
		} catch (BadCredentialsException e) {
//			throw new AuthenticationException("INVALID_CREDENTIALS", e);
//			System.out.println("INVALID_CREDENTIALS");
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		
		
		final UserDetails userDetails = userValidationService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtTokenResponse(token));
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserCredential user) throws Exception {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		UserCredential result = userValidationService.save(user);
		if (result != null) {
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.badRequest().body("Duplicated User");
		}
	}
	
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public ResponseEntity<?> updateProfile(@RequestBody Profile profile) throws Exception {
		Profile updated = userValidationService.update(profile);

		return ResponseEntity.ok(updated);
	}
	
	@GetMapping("/jpa/checkuser/username/{username}")
	public boolean checkDuplicateUser(@PathVariable String username) {
		boolean exist = userValidationService.checkUsername(username);
		return exist;

	}

	@GetMapping("/jpa/checkuser/phonenumber/{phonenumber}")
	public boolean checkDuplicateStudentnumber(@PathVariable String phonenumber) {
		boolean exist = userValidationService.checkPhone(phonenumber);
		return exist;

	}
	
	@GetMapping("/jpa/checkuser/email/{email}")
	public boolean checkDuplicateEmail(@PathVariable String email) {
		boolean exist = userValidationService.checkEmail(email);
		return exist;

	}
	
	@GetMapping("/jpa/users/{username}/profile/details")
	public Profile getProfileDetails(@PathVariable String username) {
		return pService.fetchByUserName(username);
	}
	
}
