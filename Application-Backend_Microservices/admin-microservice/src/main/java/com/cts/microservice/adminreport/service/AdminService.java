package com.cts.microservice.adminreport.service;

import java.util.List;

import com.cts.microservice.adminreport.model.PostDTO;

public interface AdminService {
	
	PostDTO[] getAllReportedPosts();
	
	String deleteThePost(Long id);
	
	String removeThePost(Long id);
	
}
