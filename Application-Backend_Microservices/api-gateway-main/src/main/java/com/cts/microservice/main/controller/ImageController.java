package com.cts.microservice.main.controller;

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

import com.cts.microservice.main.model.ImageFile;
import com.cts.microservice.main.model.Profile;
import com.cts.microservice.main.model.UploadFileResponse;
import com.cts.microservice.main.service.ImageFileStorageService;
import com.cts.microservice.main.service.ProfileService;


@CrossOrigin
@RestController
public class ImageController {
	
	@Autowired
	private ImageFileStorageService fileService;
	
	@Autowired
	private ProfileService profileService;
	
	@PostMapping("/jpa/uploadAvatar/{username}")
    public UploadFileResponse uploadAvatar( @PathVariable String username, @RequestBody MultipartFile file) {
		
		ImageFile dbFile = fileService.storeFile(file);
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/avatar/")
                .path(username)
                .toUriString();
		System.out.println("This user: " + username);
        System.out.println("File Avatar URL: " + fileDownloadUri);
        
        ImageFile toDelete =  fileService.findByFileURL(fileDownloadUri);
        
        if(toDelete != null){
            fileService.deleteById(toDelete.getId());
        }
        
        dbFile.setFileURL(fileDownloadUri);
        
        fileService.save(dbFile);
        System.out.println(dbFile);
        
        Profile updated = profileService.assignAvatar(username, dbFile);
        
        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
	}
	
	@GetMapping("/avatar/{username}")
    public ResponseEntity<Resource> downloadAvatarFile(@PathVariable String username) {
        // Load file from database
        ImageFile dbFile = fileService.getAvatarByProfile(username);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }
	
	@PostMapping("/jpa/uploadBackground/{username}")
    public UploadFileResponse uploadBackground( @PathVariable String username, @RequestBody MultipartFile file) {
    	
        ImageFile dbFile = fileService.storeFile(file);


        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/background/")
                .path(username)
                .toUriString();

        ImageFile toDelete =  fileService.findByFileURL(fileDownloadUri);

        if(toDelete != null){
            fileService.deleteById(toDelete.getId());
        }

        dbFile.setFileURL(fileDownloadUri);
        fileService.save(dbFile);


        Profile updated = profileService.assignBackground(username, dbFile);


        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }
	
	@GetMapping("/background/{username}")
    public ResponseEntity<Resource> downloadBackgroundFile(@PathVariable String username) {
        // Load file from database
        ImageFile dbFile = fileService.getBackgroundByProfile(username);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }
	
}
