package com.cts.microservice.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.microservice.post.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findByUsername(String username);
}
