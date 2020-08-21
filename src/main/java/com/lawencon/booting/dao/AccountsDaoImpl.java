package com.lawencon.booting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.Accounts;
import com.lawencon.booting.model.Companies;
import com.lawencon.booting.model.Roles;
import com.lawencon.booting.model.Users;

@Repository
public class AccountsDaoImpl extends BaseDao implements AccountsDao {

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
	public void deleteAccounts(String id) throws Exception {
		em.createQuery("DELETE from Accounts where id = :id").setParameter("id", id);
	}

	@Override
	public Accounts findByEmail(Accounts data) throws Exception {
		Object[] obj = em.createQuery("SELECT acc.idUser.id, acc.idUser.nip, acc.idUser.name, acc.idUser.contact, acc.idUser.address, acc.idUser.idRole.code, acc.idUser.idRole.name, acc.idUser.idCompany.id, acc.idUser.idCompany.code, acc.idUser.idCompany.name, acc.idUser.idCompany.address, acc.pass FROM Accounts acc WHERE email = :email", Object[].class)
				.setParameter("email", data.getEmail()).getSingleResult();
		Users user = new Users();
		Roles rl = new Roles();
		Companies comp = new Companies();
		user.setId((String) obj[0]);
		user.setNip((String) obj[1]);
		user.setName((String) obj[2]);
		user.setContact((String) obj[3]);
		user.setAddress((String) obj[4]);
		rl.setCode((String) obj[5]);
		rl.setName((String) obj[6]);
		user.setIdRole(rl);
		comp.setId((String) obj[7]);
		comp.setCode((String) obj[8]);
		comp.setName((String) obj[9]);
		comp.setAddress((String) obj[10]);
		user.setIdCompany(comp);
		data.setIdUser(user);
		data.setPass((String) obj[11]);
		return data;
	}

}
