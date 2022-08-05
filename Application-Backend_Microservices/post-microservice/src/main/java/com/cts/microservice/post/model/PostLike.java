package com.cts.microservice.post.model;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Embeddable
public class PostLike {
//	@Id
//	@GeneratedValue
//	private Long id;
	private long idOfPost;
	private String liker;
	
//	public long getId() {
//		return id;
//	}
//	public void setId(long id) {
//		this.id = id;
//	}
	public String getLiker() {
		return liker;
	}
	public void setLiker(String liker) {
		this.liker = liker;
	}
	public long getIdOfPost() {
		return idOfPost;
	}
	public void setIdOfPost(long idOfPost) {
		this.idOfPost = idOfPost;
	}
}
