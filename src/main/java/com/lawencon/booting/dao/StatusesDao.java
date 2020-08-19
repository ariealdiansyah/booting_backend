package com.lawencon.booting.dao;

import java.util.List;

import com.lawencon.booting.model.Statuses;

public interface StatusesDao {

	Statuses insert(Statuses data)throws Exception;
	Statuses update(Statuses data) throws Exception;
	List<Statuses> getListStatuses() throws Exception;
	void deleteStatuses(Long id) throws Exception;
}
