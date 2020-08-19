package com.lawencon.booting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.Accounts;

@Repository
public class AccountsDaoImpl extends BaseDao implements AccountsDao{

	@Override
	public Accounts insert(Accounts data) throws Exception {
		em.persist(data);
		return data;
	}

	@Override
	public Accounts update(Accounts data) throws Exception {
		em.persist(data);
		return data;
	}

	@Override
	public List<Accounts> getListAccounts() throws Exception {
		return em.createQuery("FROM Accounts", Accounts.class).getResultList();
	}

	@Override
	public void deleteAccounts(Long id) throws Exception {
		em.createQuery("DELETE from Accounts where id = :id")
		.setParameter("id", id);
	}

}
