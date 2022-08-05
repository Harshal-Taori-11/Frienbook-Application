package com.cts.microservice.post.model;

import javax.persistence.Embeddable;

@Embeddable
public class PostReport {
	
	private long idOfPost;
	private String reporter;

	public long getIdOfPost() {
		return idOfPost;
	}

	public void setIdOfPost(long idOfPost) {
		this.idOfPost = idOfPost;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
}
