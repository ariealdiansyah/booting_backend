package com.lawencon.booting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.ClassificationsDao;
import com.lawencon.booting.model.Classifications;

@Service
@Transactional
public class ClassificationsServiceImpl implements ClassificationsService {

	@Autowired
	private ClassificationsDao classificationsDao;
	
	@Override
	public Classifications insert(Classifications data) throws Exception {
		return classificationsDao.insert(data);
	}

}
