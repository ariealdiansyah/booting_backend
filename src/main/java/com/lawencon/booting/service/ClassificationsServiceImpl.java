package com.lawencon.booting.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.ClassificationsDao;
import com.lawencon.booting.model.Classifications;

@Service
@Transactional
public class ClassificationsServiceImpl extends BaseService implements ClassificationsService {

	@Autowired
	private ClassificationsDao classificationsDao;
	
	@Override
	public Classifications insert(Classifications data) throws Exception {
//		data.setId(getUuid());
		data.setCreatedAt(new Date());
		return classificationsDao.insert(data);
	}

	@Override
	public Classifications update(Classifications data) throws Exception {
		data.setUpdatedAt(new Date());
		return classificationsDao.update(data);
	}

	@Override
	public List<Classifications> getListClassifications() throws Exception {
		return classificationsDao.getListClassifications();
	}

	@Override
	public void deleteClassifications(String id) throws Exception {
		classificationsDao.deleteClassifications(id);
		
	}

	@Override
	public Classifications getClassificationsByCode(Classifications data) throws Exception {
		return classificationsDao.getClassificationsByCode(data.getCode());
	}

}
