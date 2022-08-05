package com.cts.microservice.adminreport.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
	public static final int post1 = 0;
	private Long id;
	private String username;
	private String description;
	private Date targetDate;
	private String postImage;
	private boolean isDone;
	private List<PostReport> reports = new ArrayList<>();
}
