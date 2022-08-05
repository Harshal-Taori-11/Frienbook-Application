package com.cts.microservice.adminreport.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cts.microservice.adminreport.config.SecurityConfig;
import com.cts.microservice.adminreport.model.PostDTO;

@Service
public class AdminServiceImpl implements AdminService {

	static Logger log = Logger.getLogger(AdminServiceImpl.class);
	
	RestTemplate restTemplate;
	
	@Autowired
	public AdminServiceImpl(RestTemplateBuilder restTemplateBuilder){
		restTemplate = restTemplateBuilder.build();
	}
	
	@Override
	public PostDTO[] getAllReportedPosts() {
		
		log.debug("Inside get all reported post service");
		
		String uri = "http://localhost:8082/post/reported";
		PostDTO[] result = restTemplate.getForObject(uri, PostDTO[].class);
		
		log.debug("Executed get all reported post service");
		
		return result;
	}

	@Override
	public String deleteThePost(Long id) {
		
		log.debug("Inside delete post service");
		
		String uri = "http://localhost:8082/post/delete/{id}";
		Map<String, Long> param = new HashMap<String, Long>();
		param.put("id", id);
		restTemplate.delete(uri, param);
		
		log.debug("Executed delete post service");
		
		return "Success";
		
	}

	@Override
	public String removeThePost(Long id) {
		log.debug("Inside remove post service");
		
		String uri = "http://localhost:8082/post/unreport/{id}";
		Map<String, Long> param = new HashMap<String, Long>();
		param.put("id", id);
		restTemplate.put(uri, null, param);
		
		log.debug("Executed remove post service");
		
		return "Success";
	}

}
