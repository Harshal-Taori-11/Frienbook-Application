package com.cts.microservice.post.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cts.microservice.post.model.Post;
import com.cts.microservice.post.model.PostComment;
import com.cts.microservice.post.model.PostLike;
import com.cts.microservice.post.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository pRepository;
	
	public List<Post> findAll(){
		return pRepository.findAll();
	}
	
	public void deletePost(Long id) {
		pRepository.deleteById(id);
	}
	
	public List<Post> getAllUserPost(@PathVariable String username){
		return pRepository.findByUsername(username);
	}
	
	public Post getPost(String username,Long id){
		return pRepository.findById(id).get();
	}
	
	public List<PostComment> getComments(String username,Long id){
		return pRepository.findById(id).get().getComments();
	}
	
	public void addPostComment(String username,Long id,PostComment comment) {
		Post updatedPost = pRepository.findById(id).get().addComment(comment);
		updatedPost.setUsername(username);

		pRepository.save(updatedPost);
	}
	
	public void deletePost(String username, Long id) {
		pRepository.deleteById(id);
	}
	
	public void savePost(Post post) {
		pRepository.save(post);
	}
	
	public Post updatePost(String username, Long id,Post post) {
		post.setUsername(username);
		post.setComments(pRepository.findById(id).get().getComments());
		post.setNewLikes(pRepository.findById(id).get().getLikes());
		pRepository.save(post);
		return post;
	}
	
	public URI createPost(String username, Post post) {
		post.setUsername(username);
		Post createdPost = pRepository.save(post);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdPost.getId()).toUri();
		
		return uri;
	}
	
	public String addLike(PostLike like) {
		
		Post post= pRepository.findById(like.getIdOfPost()).get();
		List<PostLike> likes= post.getLikes();
		int flag=0;
		for(PostLike l: likes) {
			if(l.getLiker().equals(like.getLiker()))
			{
				flag=1;
			}
		}
		if(flag==0) {
			post.addLikes(like);
			pRepository.save(post);
			return "Like Added";
		}
		else {
			return "Already Present";
		}	
	}
	
	public void unLikePost(PostLike like) {
		
		Post post= pRepository.findById(like.getIdOfPost()).get();
		List<PostLike> likes= post.getLikes();
		PostLike pl= null;
		for(PostLike l: likes) {
			if((l.getLiker().equals(like.getLiker()))&&(l.getIdOfPost()==like.getIdOfPost()))
			{
				pl= l;
				break;
			}
		}
		if(pl!=null) {
			post.removeLikeObject(pl);
			pRepository.save(post);
		}
		
	}
}
