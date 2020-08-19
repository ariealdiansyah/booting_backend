package com.lawencon.booting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.Roles;

@Repository
public class RolesDaoImpl extends BaseDao implements RolesDao{

	@Override
	public Roles insert(Roles data) throws Exception {
		em.persist(data);
		return data;
	}

	@Override
	public Roles update(Roles data) throws Exception {
		em.persist(data);
		return data;
	}

	@Override
	public List<Roles> getListRoles() throws Exception {
		return em.createQuery("FROM Roles", Roles.class).getResultList();
	}

	@Override
	public void deleteRoles(Long id) throws Exception {
		em.createQuery("DELETE from Roles where id = :id")
		.setParameter("id", id);
		
	}

}
