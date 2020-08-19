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
		return em.createQuery("FROM Companies", Companies.class).getResultList();
	}

	@Override
	public void delete(String id) throws Exception {
		em.createQuery("DELETE from Companies where id = :id").setParameter("id", id);
	}

}
