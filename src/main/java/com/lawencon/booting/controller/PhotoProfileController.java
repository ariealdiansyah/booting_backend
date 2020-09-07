package com.lawencon.booting.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lawencon.booting.model.PhotoProfile;
import com.lawencon.booting.service.PhotoProfileService;
import com.lawencon.booting.utility.ResponseFile;
import com.lawencon.booting.utility.ResponseMessage;


@RestController
@RequestMapping("/photo-profile")
public class PhotoProfileController {

	@Autowired
	private PhotoProfileService photoProfileService;
	
	@PostMapping("/uploads")
	  public ResponseEntity<ResponseMessage> uploadsFile(@RequestParam("files") MultipartFile file) {
		String message = "";
	    try {
	    	photoProfileService.store(file);

	      message = "Uploaded the file successfully: " + file.getOriginalFilename();
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	  }
	
	@GetMapping("/all")
	  public ResponseEntity<List<ResponseFile>> getListFiles() {
	    List<ResponseFile> files = photoProfileService.getAllFiles().map(dbFile -> {
	      String fileDownloadUri = ServletUriComponentsBuilder
	          .fromCurrentContextPath()
	          .path("/files/")
	          .path(dbFile.getId())
	          .toUriString();

	      return new ResponseFile(
	          dbFile.getName(),
	          fileDownloadUri,
	          dbFile.getType(),
	          dbFile.getData().length);
	    }).collect(Collectors.toList());

	    return ResponseEntity.status(HttpStatus.OK).body(files);
	  }

	  @GetMapping("/files/{id}")
	  public ResponseEntity<byte[]> getFile(@PathVariable String id) {
		PhotoProfile photo = new PhotoProfile();
		photo.setId(id);
	    PhotoProfile fileDB = photoProfileService.getFile(photo);

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
	        .body(fileDB.getData());
	  }
	
}
