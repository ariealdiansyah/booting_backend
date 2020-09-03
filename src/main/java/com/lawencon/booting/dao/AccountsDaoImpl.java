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
		em.merge(data);
		return data;
	}

	@Override
	public List<Accounts> getListAccounts() throws Exception {
		return em.createQuery("FROM Accounts", Accounts.class).getResultList();
	}

	@Override
	public void deleteAccounts(String id) throws Exception {
		em.createQuery("DELETE from Accounts where id = :id").setParameter("id", id).executeUpdate();
	}

	@Override
	public Accounts findByEmail(Accounts data) throws Exception {
//		StringBuilder sql = new StringBuilder();
//		sql.append("SELECT acc.idUser.id, acc.idUser.nip, acc.idUser.name, acc.idUser.contact, acc.idUser.address, ");
//		sql.append("acc.idUser.idRole.code, acc.idUser.idRole.name, acc.idUser.idCompany.id, acc.idUser.idCompany.name, ");
//		sql.append("acc.pass FROM Accounts acc WHERE email = :email");
		
//		Object[] obj = em.createQuery("SELECT acc.idUser.id, acc.idUser.nip, acc.idUser.name, acc.idUser.contact, acc.idUser.address, acc.idUser.idRole.code, acc.idUser.idRole.name, acc.idUser.idCompany.id, acc.idUser.idCompany.name FROM Accounts acc WHERE email = :email", Object[].class)
//				.setParameter("email", data.getEmail()).getSingleResult();
//		
//		Object[] obj = em.createQuery(sql.toString(), Object[].class)
//				.setParameter("email", data.getEmail()).getSingleResult();
//		Users user = new Users();
//		Roles rl = new Roles();
//		Companies comp = new Companies();
//		user.setId((String) obj[0]);
//		user.setNip((String) obj[1]);
//		user.setName((String) obj[2]);
//		user.setContact((String) obj[3]);
//		user.setAddress((String) obj[4]);
//		rl.setCode((String) obj[5]);
//		rl.setName((String) obj[6]);
//		user.setIdRole(rl);
//		comp.setId((String) obj[7]);
//		comp.setName((String) obj[8]);
//		user.setIdCompany(comp);
//		data.setIdUser(user);
//		data.setPass((String) obj[9]);
//		return data;
		if("superadmin".equals(data.getEmail())) {
			List<Accounts> listAccounts  = em.createQuery("FROM Accounts WHERE email = 'superadmin'", Accounts.class)
					.getResultList();
			return !listAccounts.isEmpty() ? listAccounts.get(0) : null;
		} else {
			List<Accounts> listAccounts  = em.createQuery("FROM Accounts WHERE email = :email AND active = true", Accounts.class)
					.setParameter("email", data.getEmail()).getResultList();
			return !listAccounts.isEmpty() ? listAccounts.get(0) : null;
		}
	}

	@Override
	public List<Accounts> getListAccountsActive() throws Exception {
		return em.createQuery("FROM Accounts WHERE active = true", Accounts.class).getResultList();
	}

	@Override
	public void deletePath(String id) throws Exception {
		em.createQuery("UPDATE Accounts SET active = false WHERE id = :id").setParameter("id", id).executeUpdate();
	}

	@Override
	public Accounts findByUser(String id) throws Exception {
		List<Accounts> listAccounts  = em.createQuery("FROM Accounts where idUser.id = :id", Accounts.class)
		.setParameter("id", id).getResultList();
		return !listAccounts.isEmpty() ? listAccounts.get(0) : null;
	}
	
}
