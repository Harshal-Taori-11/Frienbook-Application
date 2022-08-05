package com.cts.restfulwebservices.follow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.restfulwebservices.follow.model.Follow;
import com.cts.restfulwebservices.follow.repository.FollowRepository;

@Service
public class FollowService {
	
	@Autowired
	private FollowRepository followRepository;
	
	public List<String> getAllFollows(String user){
		List<String> findAllFollowers = followRepository.findAllFollowing(user);
		return findAllFollowers;
	}
	
	public boolean addFollow(String user, String username) {
		Follow follow = new Follow();
		follow.setUsername(user);
		follow.setFollowing(username);
		
		List<String> follows = followRepository.findAllFollowing(user);
		if(follows.contains(username)) {
			return false;
		}else {
		followRepository.save(follow);
			return true;
		}
	}
	
	public boolean deleteFollowRecord(String user, String username) {
		followRepository.deleteFollowRecord(user,username);
		return true;
	}
	

}
