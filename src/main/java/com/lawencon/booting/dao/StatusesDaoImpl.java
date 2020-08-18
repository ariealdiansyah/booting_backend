package com.lawencon.booting.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.Statuses;

@Repository
public class StatusesDaoImpl extends BaseDao implements StatusesDao{

	@Override
	public Statuses insert(Statuses data) throws Exception {
		em.persist(data);
		return data;
	}

}
