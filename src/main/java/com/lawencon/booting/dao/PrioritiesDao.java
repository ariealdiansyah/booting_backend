package com.lawencon.booting.dao;

import java.util.List;

import com.lawencon.booting.model.Priorities;


public interface PrioritiesDao {
	
	Priorities insert(Priorities data)throws Exception;
	Priorities update(Priorities data) throws Exception;
	List<Priorities> getListPriorities() throws Exception;
	Priorities getPrioritiesByCode(String code) throws Exception;
	void deletePriorities(String id) throws Exception;
}
