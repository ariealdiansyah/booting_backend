package com.lawencon.booting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.StatusesDao;
import com.lawencon.booting.model.Statuses;

@Service
@Transactional
public class StatusesServiceImpl implements StatusesService{

	@Autowired
	private StatusesDao statusesDao;
	
	@Override
	public Statuses insert(Statuses data) throws Exception {
		return statusesDao.insert(data);
	}

}
