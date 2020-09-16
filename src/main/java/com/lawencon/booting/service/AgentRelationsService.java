package com.lawencon.booting.service;

import java.util.List;

import com.lawencon.booting.model.AgentRelations;
import com.lawencon.booting.model.Companies;
import com.lawencon.booting.model.Users;

public interface AgentRelationsService {

	AgentRelations insert(AgentRelations data) throws Exception;

	AgentRelations update(AgentRelations data) throws Exception;

	List<AgentRelations> getListAgentRelations() throws Exception;

	List<AgentRelations> getListByIdUser(AgentRelations data) throws Exception;

	List<String> getListCompanies(Users data) throws Exception;
	
	Users getAgentByCompany(Companies data) throws Exception;
	
}
