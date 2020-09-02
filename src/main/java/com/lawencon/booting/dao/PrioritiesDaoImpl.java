package com.lawencon.booting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.Priorities;

@Repository
public class PrioritiesDaoImpl extends BaseDao implements PrioritiesDao {

	@Override
	public Priorities insert(Priorities data) throws Exception {
		em.persist(data);
		return data;
	}

	@Override
	public Priorities update(Priorities data) throws Exception {
		em.merge(data);
		return data;
	}

	@Override
	public List<Priorities> getListPriorities() throws Exception {
		return em.createQuery("FROM Priorities", Priorities.class).getResultList();
	}

	@Override
	public void deletePriorities(String id) throws Exception {
		em.createQuery("DELETE from Priorities where id = :id")
		.setParameter("id", id);
		
	}

	@Override
	public Priorities getPrioritiesByCode(String code) throws Exception {
		List<Priorities> listPriorities = em.createQuery("FROM Priorities where code = :code ", Priorities.class)
				 .setParameter("code", code).getResultList();
		 return !listPriorities.isEmpty() ? listPriorities.get(0): null;
	}

	@Override
	public List<Priorities> getListPrioritiesActive() throws Exception {
		return em.createQuery("FROM Priorities WHERE active = TRUE", Priorities.class).getResultList();
	}

}
