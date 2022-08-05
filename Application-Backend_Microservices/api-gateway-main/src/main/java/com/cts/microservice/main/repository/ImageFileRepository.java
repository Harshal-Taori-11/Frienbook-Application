package com.cts.microservice.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.microservice.main.model.ImageFile;

@Repository
public interface ImageFileRepository extends JpaRepository<ImageFile, String> {
	ImageFile findByUsername(String username);

	ImageFile findByFileURL(String url);

	ImageFile deleteByFileURL(String url);
}
