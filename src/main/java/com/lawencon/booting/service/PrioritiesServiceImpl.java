package com.lawencon.booting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.PrioritiesDao;
import com.lawencon.booting.model.Priorities;

@Service
@Transactional
public class PrioritiesServiceImpl implements PrioritiesService{

	@Autowired
	private PrioritiesDao prioritiesDao;
	
	@Override
	public Priorities insert(Priorities data) throws Exception {
		return prioritiesDao.insert(data);
	}

}
