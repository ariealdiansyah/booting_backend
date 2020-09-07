package com.lawencon.booting.dao;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.PhotoProfile;

@Repository
public class PhotoProfileDaoImpl extends BaseDao implements PhotoProfileDao{

	@Override
	public PhotoProfile store(PhotoProfile file) throws IOException {
	em.persist(file);
		return file;
	}

	@Override
	public PhotoProfile getFile(PhotoProfile file) {
		List<PhotoProfile> photo = em.createQuery("FROM PhotoProfile where id = :id",PhotoProfile.class)
		.setParameter("id", file.getId()).getResultList();
		return !photo.isEmpty() ? photo.get(0):null;
		
	}

	@Override
	public Stream<PhotoProfile> getAllFiles() {
		return em.createQuery("FROM PhotoProfile", PhotoProfile.class).getResultList().stream();
	}

}
