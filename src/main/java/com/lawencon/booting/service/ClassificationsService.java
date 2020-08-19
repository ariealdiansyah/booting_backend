package com.lawencon.booting.service;

import java.util.List;

import com.lawencon.booting.model.Classifications;

public interface ClassificationsService {

	Classifications insert(Classifications data) throws Exception;
	Classifications update(Classifications data) throws Exception;
	List<Classifications> getListClassifications() throws Exception;
	void deleteClassifications(Long id) throws Exception;
	
	
}
