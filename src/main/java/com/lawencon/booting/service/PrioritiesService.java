package com.lawencon.booting.service;

import java.util.List;

import com.lawencon.booting.model.Priorities;

public interface PrioritiesService {

	Priorities insert(Priorities data) throws Exception;

	Priorities update(Priorities data) throws Exception;

	List<Priorities> getListPriorities() throws Exception;

	Priorities getPrioritiesByCode(Priorities data) throws Exception;

	void deletePriorities(String id) throws Exception;
	
	List<Priorities> getListPrioritiesActive() throws Exception;
}
