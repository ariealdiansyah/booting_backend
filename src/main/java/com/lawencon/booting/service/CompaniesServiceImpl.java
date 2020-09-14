package com.lawencon.booting.service;

import java.util.Date;
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
		data.setCreatedAt(new Date());
		return companiesDao.insert(data);
	}

	@Override
	public Companies update(Companies data) throws Exception {
		data.setUpdatedAt(new Date());
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
	public Companies getCompanyByName(Companies data) throws Exception {
		return companiesDao.getCompanyByName(data);
	}

	@Override
	public void deletePath(String id) throws Exception {
		companiesDao.deletePath(id);
	}

	@Override
	public List<Companies> getListCompaniesActive() throws Exception {
		return companiesDao.getListCompaniesActive() ;
	}

}
