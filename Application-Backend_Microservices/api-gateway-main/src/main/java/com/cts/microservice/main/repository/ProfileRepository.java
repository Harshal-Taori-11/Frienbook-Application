package com.cts.microservice.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.microservice.main.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
	Profile findByUsername(String username);
	Profile findByEmail(String email);
	Profile findByPhonenumber(String phonenumber);
}
