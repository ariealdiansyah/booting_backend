package com.lawencon.booting.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.Priorities;

@Repository
public class PrioritiesDaoImpl extends BaseDao implements PrioritiesDao {

	@Override
	public Priorities insert(Priorities data) throws Exception {
		em.persist(data);
		return data;
	}

}
