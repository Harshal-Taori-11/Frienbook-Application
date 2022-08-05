package com.cts.microservice.post.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.microservice.post.model.Post;
import com.cts.microservice.post.model.PostReport;
import com.cts.microservice.post.service.PostReportService;

@RestController
@CrossOrigin
public class PostReportController {

	@Autowired
	PostReportService postReportService;
	
	@GetMapping("/post/reported")
	public List<Post> reportedPosts(){
		return postReportService.reportedPosts();
	}
	
	@PostMapping("/post/report")
	public String reportPost(@RequestBody PostReport postReport){
		return postReportService.reportPost(postReport);
	}
	
	@PutMapping("/post/unreport/{id}")
	public ResponseEntity<Void> unreportPost(@PathVariable Long id){
		postReportService.unreportPost(id);
		return ResponseEntity.noContent().build();
	}
}
