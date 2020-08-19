package com.lawencon.booting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.Statuses;

@Repository
public class StatusesDaoImpl extends BaseDao implements StatusesDao{

	@Override
	public Statuses insert(Statuses data) throws Exception {
		em.persist(data);
		return data;
	}

	@Override
	public Statuses update(Statuses data) throws Exception {
		em.persist(data);
		return data;
	}

	@Override
	public List<Statuses> getListStatuses() throws Exception {
		return em.createQuery("FROM Statuses", Statuses.class).getResultList();
	}

	@Override
	public void deleteStatuses(String id) throws Exception {
		em.createQuery("DELETE from Statuses where id = :id")
		.setParameter("id", id);
		
	}

}
