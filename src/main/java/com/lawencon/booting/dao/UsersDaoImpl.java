package com.lawencon.booting.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.Users;

@Repository
public class UsersDaoImpl extends BaseDao implements UsersDao {

	@Override
	public Users insert(Users data) throws Exception {
		em.persist(data);
		return data;
	}

}
