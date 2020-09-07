package com.lawencon.booting.service;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.booting.model.PhotoProfile;


public interface PhotoProfileService {

	PhotoProfile store(MultipartFile file) throws IOException;
	PhotoProfile getFile(PhotoProfile id);
	Stream<PhotoProfile> getAllFiles();
}
