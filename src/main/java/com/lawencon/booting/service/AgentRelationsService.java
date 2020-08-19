package com.lawencon.booting.service;

import java.util.List;

import com.lawencon.booting.model.AgentRelations;

public interface AgentRelationsService {

	AgentRelations insert(AgentRelations data) throws Exception;

	AgentRelations update(AgentRelations data) throws Exception;

	List<AgentRelations> getListAgentRelations() throws Exception;

	void delete(String id) throws Exception;
}
