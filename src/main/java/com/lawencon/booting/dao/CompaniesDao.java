package com.lawencon.booting.dao;

import java.util.List;

import com.lawencon.booting.model.Companies;

public interface CompaniesDao {

	Companies insert(Companies data) throws Exception;
	Companies update(Companies data) throws Exception;
	List<Companies> getListCompanies() throws Exception;
	void delete(String id) throws Exception;
}
