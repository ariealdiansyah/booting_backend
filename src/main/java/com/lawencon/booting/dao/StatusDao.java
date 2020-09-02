package com.lawencon.booting.dao;

import java.util.List;

import com.lawencon.booting.model.Status;

public interface StatusDao {

	Status insert(Status data) throws Exception;

	Status update(Status data) throws Exception;

	List<Status> getListStatuses() throws Exception;

	Status getStatusesByCode(String code) throws Exception;

	void deleteStatuses(String id) throws Exception;
	
	List<Status> getListStatusActive() throws Exception;
}
