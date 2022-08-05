package com.cts.microservice.adminreport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostReport {
	
	private Long idOfPost;
	private String reporter;
}
