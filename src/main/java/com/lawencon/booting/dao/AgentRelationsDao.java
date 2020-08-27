package com.lawencon.booting.dao;

import java.util.List;

import com.lawencon.booting.model.AgentRelations;
import com.lawencon.booting.model.Users;

public interface AgentRelationsDao {

	AgentRelations insert(AgentRelations data) throws Exception;

	AgentRelations update(AgentRelations data) throws Exception;

	List<AgentRelations> getListAgentRelations() throws Exception;

	void delete(String id) throws Exception;
	
	List<AgentRelations> getListByIdUser(AgentRelations data) throws Exception;
	
	List<String> getListCompanies(Users data) throws Exception;

}
