package com.cts.microservice.post.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.microservice.post.model.Post;
import com.cts.microservice.post.model.PostReport;
import com.cts.microservice.post.repository.PostRepository;

@Service
public class PostReportService {
	
	@Autowired
	PostRepository postRepository;
	
	public String reportPost(PostReport report) {
		Post post= postRepository.findById(report.getIdOfPost()).get();
		List<PostReport> reports= post.getReports();
		int flag=0;
		for(PostReport r: reports) {
			if(r.getReporter().equals(report.getReporter()))
			{
				flag=1;
			}
		}
		if(flag==0) {
			post.addReports(report);
			postRepository.save(post);
			return "Reported";
		}
		else {
			return "Already reported";
		}	
	}
	
	public void unreportPost(Long id) {
		
		Post post= postRepository.findById(id).get();
		post.removeReports();
		postRepository.save(post);
//		List<PostReport> reports= post.getReports();
//		PostReport pr = null;
//		for(PostReport r: reports) {
//			if((r.getReporter().equals(report.getReporter()))&&(r.getIdOfPost()==report.getIdOfPost()))
//			{
//				pr= r;
//				break;
//			}
//		}
//		if(pr!=null) {
//			post.removeReportObject(report);
//			postRepository.save(post);
//		}
	}
	
	public List<Post> reportedPosts(){
		List<Post> reportedPosts =  postRepository.findAll();
		List<Post> result = new ArrayList<>();
		for(int i=0;i<reportedPosts.size();i++) {
			if(reportedPosts.get(i).getReports().size() >= 1) {
				result.add(reportedPosts.get(i));
			}
		}
		Collections.sort(result,(o1,o2)->
			o2.getReports().size()-o1.getReports().size()
		);
		return result;
		
	}
}
