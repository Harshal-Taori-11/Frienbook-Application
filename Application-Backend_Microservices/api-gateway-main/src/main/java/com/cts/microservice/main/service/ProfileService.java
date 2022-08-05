package com.cts.microservice.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.microservice.main.model.ImageFile;
import com.cts.microservice.main.model.Profile;
import com.cts.microservice.main.repository.ProfileRepository;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	
	public Profile fetchByUserName(String name) {
		return profileRepository.findByUsername(name);
	}
	
	public Profile assignAvatar(String username, ImageFile file) {
		Profile profile = profileRepository.findByUsername(username);
		profile.setAvatar(file.getFileURL());

		return profileRepository.save(profile);
	}

	public Profile assignBackground(String username, ImageFile file) {
		Profile profile = profileRepository.findByUsername(username);
		profile.setBackground(file.getFileURL());

		return profileRepository.save(profile);
	}
}
