package com.lawencon.booting.service;

import java.util.List;

import com.lawencon.booting.model.Status;

public interface StatusService {

	Status insert(Status data) throws Exception;

	Status update(Status data) throws Exception;

	List<Status> getListStatuses() throws Exception;

	Status getStatusesByCode(Status data) throws Exception;

	void deleteStatuses(String id) throws Exception;
	
}
