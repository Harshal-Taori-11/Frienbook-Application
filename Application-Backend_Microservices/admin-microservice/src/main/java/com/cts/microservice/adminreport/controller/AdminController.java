package com.cts.microservice.adminreport.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.microservice.adminreport.config.SecurityConfig;
import com.cts.microservice.adminreport.model.PostDTO;
import com.cts.microservice.adminreport.service.AdminService;

@RestController
@CrossOrigin
public class AdminController {
	
	static Logger log = Logger.getLogger(AdminController.class);
	
	@Autowired
	AdminService adminServiceImpl;
	
	@GetMapping("/admin/post/reported")
	public PostDTO[] reportedPosts() {
		log.debug("Inside get all reported posts endpoint");
		
		return adminServiceImpl.getAllReportedPosts();
	}
	
	
	@DeleteMapping("/admin/{id}")
	public void deletePost(@PathVariable Long id) {
		
		log.debug("Inside delete post endpoint");
		String ans = adminServiceImpl.deleteThePost(id);
	}
	
	@PutMapping("/admin/post/remove/{id}")
	public void removePost(@PathVariable Long id) {
		
		log.debug("Inside remove post endpoint");
		String ans =adminServiceImpl.removeThePost(id);
	}
}
