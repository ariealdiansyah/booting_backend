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
		return data;
	}

	@Override
	public List<Users> getListUsers() throws Exception {
		return em.createQuery("FROM Users ORDER BY createdAt DESC", Users.class).getResultList();
	}

	@Override
	public List<Users> getListUsersActive() throws Exception {
		return em.createQuery("FROM Users WHERE active = true ORDER BY createdAt DESC", Users.class).getResultList();
	}

	@Override
	public void delete(String data) throws Exception {
		em.createQuery("DELETE from Users where id = :id").setParameter("id", data).executeUpdate();
	}

	@Override
	public Users getUserByNip(Users data) throws Exception {
		List<Users> listUsers = em.createQuery("FROM Users where nip = :nip", Users.class)
				.setParameter("nip", data.getNip()).getResultList();
		return !listUsers.isEmpty() ? listUsers.get(0) : null;
	}

	@Override
	public void deletePath(String id) throws Exception {
		em.createQuery("UPDATE Users SET active = false WHERE id = :id").setParameter("id", id).executeUpdate();
	}

	@Override
	public List<Users> getListUsersByClient(Users data) throws Exception {
		return em.createQuery("FROM Users WHERE active = TRUE and idRole.code ='CTM' and idCompany.name = :company ORDER BY createdAt DESC",
				Users.class).setParameter("company", data.getIdCompany().getName()).getResultList();
	}

	@Override
	public List<Users> getListAgent() throws Exception {
		return em.createQuery("FROM Users WHERE idRole.code = 'AGT' ORDER BY createdAt DESC", Users.class).getResultList();
	}

	@Override
	public List<Users> getListClients() throws Exception {
		return em.createQuery("FROM Users WHERE idRole.code = 'CLI' ORDER BY createdAt DESC", Users.class).getResultList();
	}

}
