package com.lawencon.booting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.PrioritiesDao;
import com.lawencon.booting.model.Priorities;

@Service
@Transactional
public class PrioritiesServiceImpl extends BaseService implements PrioritiesService{

	@Autowired
	private PrioritiesDao prioritiesDao;
	
	@Override
	public Priorities insert(Priorities data) throws Exception {
		data.setId(getUuid());
		return prioritiesDao.insert(data);
	}

	@Override
	public Priorities update(Priorities data) throws Exception {
		return prioritiesDao.update(data);
	}

	@Override
	public List<Priorities> getListPriorities() throws Exception {
		return prioritiesDao.getListPriorities();
	}

	@Override
	public void deletePriorities(String id) throws Exception {
		prioritiesDao.deletePriorities(id);
		
	}

	@Override
	public Priorities getPrioritiesByCode(Priorities data) throws Exception {
		return prioritiesDao.getPrioritiesByCode(data.getCode());
	}

}
