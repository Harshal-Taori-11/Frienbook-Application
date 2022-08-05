package com.cts.microservice.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.microservice.post.model.DBFile;

@Repository
public interface MediaRepository extends JpaRepository<DBFile, String>{
	DBFile findByFileURL(String url);
    DBFile deleteByFileURL(String url);
}