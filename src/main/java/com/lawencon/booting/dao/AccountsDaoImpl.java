package com.lawencon.booting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.Accounts;

@Repository
public class AccountsDaoImpl extends BaseDao implements AccountsDao {

	@Override
	public Accounts insert(Accounts data) throws Exception {
		em.persist(data);
		return data;
	}

	@Override
	public Accounts update(Accounts data) throws Exception {
		return em.merge(data);
	}

	@Override
	public void deleteAccounts(String id) throws Exception {
		em.createQuery("DELETE from Accounts where id = :id").setParameter("id", id).executeUpdate();
	}

	@Override
	public Accounts findByEmail(Accounts data) throws Exception {
		if ("superadmin".equals(data.getEmail())) {
			List<Accounts> listAccounts = em.createQuery("FROM Accounts WHERE email = 'superadmin'", Accounts.class)
					.getResultList();
			return !listAccounts.isEmpty() ? listAccounts.get(0) : null;
		} else {
			List<Accounts> listAccounts = em
					.createQuery("FROM Accounts WHERE email = :email AND active = true", Accounts.class)
					.setParameter("email", data.getEmail()).getResultList();
			return !listAccounts.isEmpty() ? listAccounts.get(0) : null;
		}
	}

	@Override
	public void deletePath(String id) throws Exception {
		em.createQuery("UPDATE Accounts SET active = false WHERE id = :id").setParameter("id", id).executeUpdate();
	}

	@Override
	public Accounts findByUser(String id) throws Exception {
		List<Accounts> listAccounts = em.createQuery("FROM Accounts where idUser.id = :id", Accounts.class)
				.setParameter("id", id).getResultList();
		return !listAccounts.isEmpty() ? listAccounts.get(0) : null;
	}

}
