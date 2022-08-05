package com.cts.microservice.post.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Post")
public class Post {
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String description;
	private Date targetDate;
	private String image;
	private boolean isDone;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name="post_comment")
	private List<PostComment> comments = new ArrayList<>();
	
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name="post_like")
	private List<PostLike> likes = new ArrayList<>();

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name="post_report")
	private List<PostReport> reports = new ArrayList<>() ;

	public Post() {
		
	}


	public Post(long id, String username, String description, Date targetDate, boolean isDone) {
		super();
		this.id = id;
		this.username = username;
		this.description = description;
		this.targetDate = targetDate;
		this.isDone = isDone;
		this.comments = new ArrayList<PostComment>();
		this.likes = new ArrayList<PostLike>();
		this.reports = new ArrayList<PostReport>();
	}
	
	public List<PostReport> getReports(){
		return this.reports;
	}


	public void setReports(List<PostReport> reports) {		
		this.reports.addAll(reports);
	}
	
	public Post addLikes(PostLike like) {
		this.likes.add(like);
		return this;
	}

	public void removeReportObject(PostReport report) {
		this.reports.remove(report);
	}
	
	public Post addReports(PostReport report) {
		this.reports.add(report);
		return this;
	}
	
	public List<PostLike> getLikes(){
		return this.likes;
	}
	
	public void setNewLikes(List<PostLike> list) {
		this.likes.addAll(list);
	}
	
	public void removeLikeObject(PostLike like) {
		this.likes.remove(like);
	}
	
	//TODO
	public void removeReports() {
		this.reports.clear();
	}
	
	public Post addComment(PostComment comment) {
		this.comments.add(comment);
		return this;
	}
	

	public String getPostImage() {
		return image;
	}


	public void setPostImage(String image) {
		this.image = image;
	}


	public List<PostComment> getComments() {
		return this.comments;
	}

	public void setComments(List<PostComment> list) {
		this.comments = list;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

//	public void printComments() {
//		System.out.println("Print comments");
//		System.out.println(Arrays.toString(comments.toArray()));
//		System.out.println("Print first comment");
//		System.out.println(comments.get(0).toString());
//	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + (int) (id ^ (id >>> 32));
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Post other = (Post) obj;
//		if (id != other.id)
//			return false;
//		return true;
//	}

	
}