package com.cts.microservice.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.microservice.main.model.Profile;
import com.cts.microservice.main.model.UserCredential;
import com.cts.microservice.main.repository.ProfileRepository;
import com.cts.microservice.main.repository.UserCredsRepository;

@Service
public class UserValidationService implements UserDetailsService{
	
	static Logger log = LogManager.getLogger(UserValidationService.class);
	
	private static final String usernameRegex = "^[a-zA-Z0-9._-]*$";
	private static final String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
	
	@Autowired
	private UserCredsRepository credsRepository;
	
	@Autowired
	private ProfileRepository profRepository;

	
	 @Override
	    public UserDetails loadUserByUsername(String username) {
	        boolean validUsername = Pattern.matches(usernameRegex, username);
			if (validUsername == false) {
//				throw new InvalidInputException("Username: " + username + " is invalid");
				log.info("invalid username"+ username);
			}

			List<UserCredential> users= credsRepository.findByUsername(username);
			if (users.isEmpty()) {
				throw new UsernameNotFoundException("User not found with username: " + username);
			}
			return new org.springframework.security.core.userdetails.User(users.get(0).getUsername(), users.get(0).getPassword(),
					new ArrayList<>());
	    }
	 
	 public List<UserCredential> getAllUsers(){
		 return credsRepository.findAll();
	 }
	 
	 public UserCredential save(UserCredential user) throws Exception {
			boolean validUsername = Pattern.matches(usernameRegex, user.getUsername());
			boolean validPassword = Pattern.matches(passwordRegex, user.getPassword());

			if (validUsername == false) {
				throw new Exception("Username: " + user.getUsername() + " is invalid");
			}

			if (validPassword == false) {
				throw new Exception("Password: " + user.getPassword() + " is invalid");

			}

			List<UserCredential> exists = credsRepository.findByUsername(user.getUsername());
			if (exists.isEmpty()) {
				UserCredential newUser = new UserCredential();
				newUser.setUsername(user.getUsername());
				newUser.setPassword((user.getPassword()));
				return credsRepository.save(newUser);
			} else {
				throw new Exception("User with username: " + user.getUsername() + " already existed");

			}
		}
	 
	 public Profile update(Profile profile) throws Exception {


			Profile exists = profRepository.findByUsername(profile.getUsername());
			Profile newProfile = new Profile();

			if (exists == null) {
				newProfile.setUsername(profile.getUsername());
				newProfile.setAvatar("https://i.imgur.com/mri28UW.jpg");
				newProfile.setBackground("https://i.imgur.com/0UCsTsa.png");

			} else {
				newProfile = exists;

			}

			newProfile.setFirstname(profile.getFirstname());
			newProfile.setLastname(profile.getLastname());
//			newProfile.setIdnumber(profile.getStudentnumber());
			newProfile.setPhonenumber(profile.getPhonenumber());
			newProfile.setEmail(profile.getEmail());
			newProfile.setAboutme(profile.getAboutme());
			
			UserCredential dUser= credsRepository.findByUsername(profile.getUsername()).get(0);
			newProfile.setUser(dUser);

			return profRepository.save(newProfile);

		}
	 
	 public boolean checkUsername(String username) {
			boolean exist = false;
			List<UserCredential> found = credsRepository.findByUsername(username);
			if (found.isEmpty()) {
				exist = false;
			} else {
				exist = true;
			}
			return exist;
	}
	 
	 public boolean checkPhone(String phone) {
			boolean exist = false;
			Profile found = profRepository.findByPhonenumber(phone);
			if (found==null) {
				exist = false;
			} else {
				exist = true;
			}
			return exist;
	}
	 
	 public boolean checkEmail(String email) {
			boolean exist = false;
			Profile found = profRepository.findByEmail(email);
			if (found == null) {
				exist = false;
			} else {
				exist = true;
			}
			return exist;
	}
	
}
