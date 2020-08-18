package com.lawencon.booting.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.Companies;

@Repository
public class CompaniesDaoImpl extends BaseDao implements CompaniesDao {

	@Override
	public Companies insert(Companies data) throws Exception {
		em.persist(data);
		return data;
	}

	
	
}
