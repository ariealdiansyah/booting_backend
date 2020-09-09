package com.lawencon.booting.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.AgentRelations;
import com.lawencon.booting.model.Companies;
import com.lawencon.booting.model.Users;

@Repository
public class AgentRelationsDaoImpl extends BaseDao implements AgentRelationsDao {

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
		em.createQuery("DELETE from AgentRelations where id = :id").setParameter("id", id);
	}

	@Override
	public List<AgentRelations> getListByIdUser(AgentRelations data) throws Exception {
		return em.createQuery("FROM AgentRelations WHERE idAgent.nip = :idAgent", AgentRelations.class)
				.setParameter("idAgent", data.getIdAgent().getNip())
				.getResultList();
	}

	@Override
	public List<String> getListCompanies(Users data) throws Exception {
		List<Object> listData =  em.createQuery("SELECT ar.idCompany.id FROM AgentRelations ar WHERE ar.idAgent.id = :id AND (current_timestamp > startDate AND current_timestamp < endDate)", Object.class)
				.setParameter("id", data.getId()).getResultList();
		List<String> listComp = new ArrayList<>();
		listData.forEach(l -> {
			listComp.add(l.toString());
		});
		return listComp;
	}

	@Override
	public Users getAgentByCompany(Companies data) throws Exception {
		Users us = new Users();
		AgentRelations ar = new AgentRelations();
		List<AgentRelations> listData  = em.createQuery("FROM AgentRelations WHERE idCompany.id = :id AND (current_timestamp > startDate AND current_timestamp < endDate)", AgentRelations.class)
				.setParameter("id", data.getId()).getResultList();
		ar = !listData.isEmpty() ? listData.get(0) : null;
		us = ar.getIdAgent();
		return us;
	}
	
	

}
