package com.lawencon.booting.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.booting.dao.CompaniesDao;
import com.lawencon.booting.model.Companies;

@Service
@Transactional
public class CompaniesServiceImpl extends BaseService implements CompaniesService {

	@Autowired
	private CompaniesDao companiesDao;

	@Override
	public Companies insert(Companies data) throws Exception {
//		data.setId(getUuid());
		return companiesDao.insert(data);
	}

	@Override
	public Companies update(Companies data) throws Exception {
		return companiesDao.update(data);
	}

	@Override
	public List<Companies> getListCompanies() throws Exception {
		return companiesDao.getListCompanies();
	}

	@Override
	public void delete(String id) throws Exception {
		companiesDao.delete(id);
	}

	@Override
	public Companies getCompanyByCode(Companies data) throws Exception {
		return companiesDao.getCompanyByCode(data);
	}

}
