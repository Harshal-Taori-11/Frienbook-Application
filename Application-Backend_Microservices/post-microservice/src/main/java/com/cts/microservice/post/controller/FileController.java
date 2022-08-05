package com.cts.microservice.post.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cts.microservice.post.model.DBFile;
import com.cts.microservice.post.model.FileUploadResponse;
import com.cts.microservice.post.model.Post;
import com.cts.microservice.post.service.MediaService;
import com.cts.microservice.post.service.PostService;

@RestController
@CrossOrigin
public class FileController {

	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private PostService postService;
    
    @PostMapping("/post/uploadPost/{username}")
    public FileUploadResponse uploadPost( @PathVariable String username, @RequestBody MultipartFile file) {
    	
        DBFile dbFile = mediaService.storeFile(file);
        
        Date timeStamp= new Date();
        
        Post picPost= new Post();
        picPost.setUsername(username);
        picPost.setTargetDate(timeStamp);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/post/picpost/")
                .path(timeStamp.toString().replaceAll(" ", ""))
                .toUriString();
        System.out.println("This user: " + username);
        System.out.println("File Pic URL: " + fileDownloadUri);

        dbFile.setFileURL(fileDownloadUri);

        mediaService.save(dbFile);
        System.out.println(dbFile);


        picPost.setPostImage(dbFile.getFileURL());
        postService.savePost(picPost);


        return new FileUploadResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }
    
    @GetMapping("/post/picpost/{endpoint}")
    public ResponseEntity<Resource> downloadPostFile(@PathVariable String endpoint) {
        
    	DBFile file= mediaService.getPostImage(endpoint);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(new ByteArrayResource(file.getData()));
    }
}