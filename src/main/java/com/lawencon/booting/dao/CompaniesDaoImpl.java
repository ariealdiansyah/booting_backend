package com.lawencon.booting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.Companies;

@Repository
public class CompaniesDaoImpl extends BaseDao implements CompaniesDao {

	@Override
	public Companies insert(Companies data) throws Exception {
		em.persist(data);
		return data;
	}

	@Override
	public Companies update(Companies data) throws Exception {
		return em.merge(data);
	}

	@Override
	public List<Companies> getListCompanies() throws Exception {
		return em.createQuery("FROM Companies ORDER BY createdAt DESC", Companies.class).getResultList();
	}

	@Override
	public void delete(String id) throws Exception {
		em.createQuery("DELETE from Companies where id = :id").setParameter("id", id).executeUpdate();
	}

	@Override
	public void deletePath(String id) throws Exception {
		em.createQuery("UPDATE Companies SET active = false WHERE id = :id").setParameter("id", id).executeUpdate();
	}

	@Override
	public Companies getCompanyByName(Companies data) throws Exception {
		List<Companies> listCompany = em.createQuery("FROM Companies where name = :name", Companies.class)
				.setParameter("name", data.getName()).getResultList();
		return !listCompany.isEmpty() ? listCompany.get(0) : null;
	}

}
