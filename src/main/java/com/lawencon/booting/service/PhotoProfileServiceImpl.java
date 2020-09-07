package com.lawencon.booting.service;

import java.io.IOException;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.booting.dao.PhotoProfileDao;
import com.lawencon.booting.model.PhotoProfile;

@Transactional
@Service
public class PhotoProfileServiceImpl extends BaseService implements PhotoProfileService{

	@Autowired
	private PhotoProfileDao photoProfileDao;
	
	@Override
	public PhotoProfile store(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		PhotoProfile FileDB = new PhotoProfile(fileName, file.getContentType(), file.getBytes());
		FileDB.setId(getUuid());
	    return photoProfileDao.store(FileDB);
	}

	@Override
	public PhotoProfile getFile(PhotoProfile id) {
		return photoProfileDao.getFile(id);
	}

	@Override
	public Stream<PhotoProfile> getAllFiles() {
		return photoProfileDao.getAllFiles();
	}

}
