package com.lawencon.booting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.StatusesDao;
import com.lawencon.booting.model.Statuses;

@Service
@Transactional
public class StatusesServiceImpl extends BaseService implements StatusesService{

	@Autowired
	private StatusesDao statusesDao;
	
	@Override
	public Statuses insert(Statuses data) throws Exception {
		data.setId(getUuid());
		return statusesDao.insert(data);
	}

	@Override
	public Statuses update(Statuses data) throws Exception {
		return statusesDao.update(data);
	}

	@Override
	public List<Statuses> getListStatuses() throws Exception {
		return statusesDao.getListStatuses();
	}

	@Override
	public void deleteStatuses(String id) throws Exception {
		statusesDao.deleteStatuses(id);
		
	}

}
