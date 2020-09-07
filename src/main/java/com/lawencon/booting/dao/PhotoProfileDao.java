package com.lawencon.booting.dao;

import java.io.IOException;
import java.util.stream.Stream;

import com.lawencon.booting.model.PhotoProfile;


public interface PhotoProfileDao {
	
	PhotoProfile store(PhotoProfile file) throws IOException;
	PhotoProfile getFile(PhotoProfile id);
	Stream<PhotoProfile> getAllFiles();

}
