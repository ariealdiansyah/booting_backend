package com.lawencon.booting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.AgentRelations;

@Repository
public class AgentRelationsDaoImpl extends BaseDao implements AgentRelationsDao{

	@Override
	public AgentRelations insert(AgentRelations data) throws Exception {
		em.persist(data);
		return data;
	}

	@Override
	public AgentRelations update(AgentRelations data) throws Exception {
		return em.merge(data);
	}

	@Override
	public List<AgentRelations> getListAgentRelations() throws Exception {
		return em.createQuery("FROM AgentRelations", AgentRelations.class).getResultList();
	}

	@Override
	public void delete(String id) throws Exception {
		em.createQuery("DELETE from AgentRelations where id = :id")
		.setParameter("id", id);		
	}

}
