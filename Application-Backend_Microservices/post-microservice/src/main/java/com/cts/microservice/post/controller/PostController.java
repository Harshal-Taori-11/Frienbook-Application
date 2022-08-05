package com.cts.microservice.post.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.microservice.post.model.Post;
import com.cts.microservice.post.model.PostComment;
import com.cts.microservice.post.model.PostLike;
import com.cts.microservice.post.service.PostService;

@RestController
@CrossOrigin
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping("/post/users/posts")
	public List<Post> getAll() {
		return postService.findAll();
	}

	@DeleteMapping("/post/delete/{id}")
	public void deletePost(@PathVariable Long id) {
		postService.deletePost(id);
	}
	
	@GetMapping("/post/users/{username}/posts")
	public List<Post> getAllUserPost(@PathVariable String username) {
		return postService.getAllUserPost(username);
	}

	@GetMapping("/post/users/{username}/posts/{id}")
	public Post getPost(@PathVariable String username, @PathVariable long id) {
		return postService.getPost(username, id);
	}

	@GetMapping("/post/users/{username}/posts/{id}/comments")
	public List<PostComment> getComments(@PathVariable String username, @PathVariable long id) {
		return postService.getComments(username, id);
	}

	@PostMapping("/post/users/{username}/posts/{id}/comments")
	public ResponseEntity<Void> addComent(@PathVariable String username, @PathVariable long id,
			@RequestBody PostComment comment) {
		postService.addPostComment(username, id, comment);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/post/users/{username}/posts/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable String username, @PathVariable long id) {
		postService.deletePost(username, id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/post/users/{username}/posts/{id}")
	public ResponseEntity<Post> updatePost(@PathVariable String username, @PathVariable long id,
			@RequestBody Post post) {

		postService.updatePost(username, id, post);

		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}

	@PostMapping("/post/users/{username}/posts")
	public ResponseEntity<Void> createPost(@PathVariable String username, @RequestBody Post post) {

		URI uri = postService.createPost(username, post);

		return ResponseEntity.created(uri).build();
	}

	/**
	*like API 
	*@author Punit
	*@param LikeDTO
	*@return {@link ResponseEntity}
	*/
	
	@PostMapping("/post/like")
	public ResponseEntity<String> addLike(@RequestBody PostLike like) {

		String status = postService.addLike(like);
		return ResponseEntity.ok(status);
	}
	
	/**
	*unlike API 
	*@author Punit
	*@param LikeDTO
	*@return {@link ResponseEntity}
	*/

	@PostMapping("/post/unlike")
	public ResponseEntity<String> unLike(@RequestBody PostLike like) {

		postService.unLikePost(like);

		return ResponseEntity.ok("Done!");
	}
}
