package com.cts.restfulwebservices.follow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.restfulwebservices.follow.model.Follow;
import com.cts.restfulwebservices.follow.repository.FollowRepository;
import com.cts.restfulwebservices.follow.service.FollowService;

@RestController
@CrossOrigin
public class FollowServiceController {

	@Autowired
	private FollowService followService;

	@GetMapping("/jpa/{user}/following")
	public ResponseEntity<?> getAllFollowing(@PathVariable String user) {
		List<String> following = followService.getAllFollows(user);
		if (following != null) {
			return ResponseEntity.ok(following);
		} else {
			return ResponseEntity.ok("");
		}
	}

	@PutMapping("/jpa/user/{user}/followuser/{username}")
	public ResponseEntity<?> followUserWithUsername(@PathVariable String user, @PathVariable String username) {
		boolean result= followService.addFollow(user, username);
		if(result==true) {
			return ResponseEntity.ok("SUCCESS");
		}
		else {
			return ResponseEntity.ok("FAILED");
		}
	}
	
	@PutMapping("/jpa/user/{user}/unfollowuser/{username}")
	public ResponseEntity<?> unfollowRequest(@PathVariable String user, @PathVariable String username) {
		boolean result= followService.deleteFollowRecord(user, username);
		if(result==true) {
			return ResponseEntity.ok("SUCCESS");
		}else {
			return ResponseEntity.ok("FAILED");
		}
	}


}
