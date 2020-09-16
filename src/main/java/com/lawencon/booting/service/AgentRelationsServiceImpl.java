package com.lawencon.booting.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.booting.dao.AgentRelationsDao;
import com.lawencon.booting.model.AgentRelations;
import com.lawencon.booting.model.Companies;
import com.lawencon.booting.model.Users;

@Service
@Transactional
public class AgentRelationsServiceImpl extends BaseService implements AgentRelationsService {

	@Autowired
	private AgentRelationsDao agentRelationsDao;
	
	@Autowired
	private CompaniesService companiesService;
	
	@Autowired
	private UsersService userService;

	@Override
	public AgentRelations insert(AgentRelations data) throws Exception {
		Companies comp = companiesService.getCompanyByName(data.getIdCompany());
		data.setIdCompany(comp);
		
		Users us = userService.getUserByNip(data.getIdAgent());
		data.setIdAgent(us);
		
		data.setCreatedAt(new Date());
		return agentRelationsDao.insert(data);
	}

	@Override
	public AgentRelations update(AgentRelations data) throws Exception {
		data.setUpdatedAt(new Date());
		return agentRelationsDao.update(data);
	}

	@Override
	public List<AgentRelations> getListAgentRelations() throws Exception {
		return agentRelationsDao.getListAgentRelations();
	}

	public List<AgentRelations> getListByIdUser(AgentRelations data) throws Exception {
		return agentRelationsDao.getListByIdUser(data);
	}

	@Override
	public List<String> getListCompanies(Users data) throws Exception {
		return agentRelationsDao.getListCompanies(data);
	}

	@Override
	public Users getAgentByCompany(Companies data) throws Exception {
		data = companiesService.getCompanyByName(data);
		return agentRelationsDao.getAgentByCompany(data);
	}

}
