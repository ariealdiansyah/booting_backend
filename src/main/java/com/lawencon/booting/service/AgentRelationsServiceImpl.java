package com.lawencon.booting.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.booting.dao.AgentRelationsDao;
import com.lawencon.booting.model.AgentRelations;

@Service
@Transactional
public class AgentRelationsServiceImpl implements AgentRelationsService {

	@Autowired
	private AgentRelationsDao agentRelationsDao;

	@Override
	public AgentRelations insert(AgentRelations data) throws Exception {
		return agentRelationsDao.insert(data);
	}

	@Override
	public AgentRelations update(AgentRelations data) throws Exception {
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

}
