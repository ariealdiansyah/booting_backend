package com.lawencon.booting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.Users;

@Repository
public class UsersDaoImpl extends BaseDao implements UsersDao {

	@Override
	public Users insert(Users data) throws Exception {
		em.persist(data);
		return data;
	}

	@Override
	public Users update(Users data) throws Exception {
		em.merge(data);
		return null;
	}

	@Override
	public List<Users> getListUsers() throws Exception {
	return em.createQuery("FROM Users", Users.class).getResultList();
	}

	@Override
	public void delete(String data) throws Exception {
		em.remove(data);
	}
	
	

}
