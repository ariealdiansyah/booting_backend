package com.lawencon.booting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.Status;

@Repository
public class StatusDaoImpl extends BaseDao implements StatusDao{

	@Override
	public Status insert(Status data) throws Exception {
		em.persist(data);
		return data;
	}

	@Override
	public Status update(Status data) throws Exception {
		em.merge(data);
		return data;
	}

	@Override
	public List<Status> getListStatuses() throws Exception {
		return em.createQuery("FROM Statuses", Status.class).getResultList();
	}

	@Override
	public void deleteStatuses(String id) throws Exception {
		em.createQuery("DELETE from Statuses where id = :id")
		.setParameter("id", id);
		
	}

	@Override
	public Status getStatusesByCode(String code) throws Exception {
		 List<Status> listStatuses = em.createQuery("FROM Status where code = :code ", Status.class)
				 .setParameter("code", code).getResultList();
		 return !listStatuses.isEmpty() ? listStatuses.get(0): null;
	}

	@Override
	public List<Status> getListStatusActive() throws Exception {
		return em.createQuery("FROM Status WHERE active = true", Status.class).getResultList();
	}

}
