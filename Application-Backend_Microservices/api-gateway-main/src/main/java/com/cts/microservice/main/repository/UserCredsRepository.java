package com.cts.microservice.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.microservice.main.model.UserCredential;

@Repository
public interface UserCredsRepository extends JpaRepository<UserCredential, Long> {

	List<UserCredential> findByUsername(String username);
    List<UserCredential> findAll();
}
