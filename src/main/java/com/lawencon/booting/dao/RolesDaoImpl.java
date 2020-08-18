package com.lawencon.booting.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.Roles;

@Repository
public class RolesDaoImpl extends BaseDao implements RolesDao{

	@Override
	public Roles insert(Roles data) throws Exception {
		em.persist(data);
		return data;
	}

}
