package com.lawencon.booting.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.StatusDao;
import com.lawencon.booting.model.Status;

@Service
@Transactional
public class StatusServiceImpl extends BaseService implements StatusService{

	@Autowired
	private StatusDao statusesDao;
	
	@Override
	public Status insert(Status data) throws Exception {
		data.setCreatedAt(new Date());
		return statusesDao.insert(data);
	}

	@Override
	public Status update(Status data) throws Exception {
		data.setUpdatedAt(new Date());
		return statusesDao.update(data);
	}

	@Override
	public List<Status> getListStatuses() throws Exception {
		return statusesDao.getListStatuses();
	}

	@Override
	public void deleteStatuses(String id) throws Exception {
		statusesDao.deleteStatuses(id);
		
	}

	@Override
	public Status getStatusesByCode(Status data) throws Exception {
		return statusesDao.getStatusesByCode(data.getCode());
	}

	@Override
	public List<Status> getListStatusActive() throws Exception {
		return statusesDao.getListStatusActive();
	}

}
