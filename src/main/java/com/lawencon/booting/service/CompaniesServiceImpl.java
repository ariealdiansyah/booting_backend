package com.lawencon.booting.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.booting.dao.CompaniesDao;
import com.lawencon.booting.model.Companies;

@Service
@Transactional
public class CompaniesServiceImpl implements CompaniesService{

	@Autowired
	private CompaniesDao companiesDao;
	
	@Override
	public Companies insert(Companies data) throws Exception {
		return companiesDao.insert(data);
	}

}
