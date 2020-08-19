package com.lawencon.booting.service;

import java.util.List;

import com.lawencon.booting.model.Statuses;

public interface StatusesService {

	Statuses insert(Statuses data)throws Exception;
	Statuses update(Statuses data) throws Exception;
	List<Statuses> getListStatuses() throws Exception;
	void deleteStatuses(String id) throws Exception;
}
