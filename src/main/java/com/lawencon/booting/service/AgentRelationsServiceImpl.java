package com.lawencon.booting.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.booting.dao.AgentRelationsDao;
import com.lawencon.booting.model.AgentRelations;
import com.lawencon.booting.model.ReportAllListClient;
import com.lawencon.booting.model.Users;

@Service
@Transactional
public class AgentRelationsServiceImpl extends BaseService implements AgentRelationsService {

	@Autowired
	private AgentRelationsDao agentRelationsDao;

	@Override
	public AgentRelations insert(AgentRelations data) throws Exception {
//		data.setId(getUuid());
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

	@Override
	public void delete(String id) throws Exception {
		agentRelationsDao.delete(id);
	}

	@Override
	public List<AgentRelations> getListByIdUser(AgentRelations data) throws Exception {
		return agentRelationsDao.getListByIdUser(data);
	}

	@Override
	public List<String> getListCompanies(Users data) throws Exception {
		return agentRelationsDao.getListCompanies(data);
	}

}
