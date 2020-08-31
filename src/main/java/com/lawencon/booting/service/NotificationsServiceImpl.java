package com.lawencon.booting.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.booting.dao.NotificationsDao;
import com.lawencon.booting.model.Notifications;

@Service
@Transactional
public class NotificationsServiceImpl extends BaseService implements NotificationsService{

	@Autowired
	private NotificationsDao notificationsDao;
	
	@Override
	public Notifications insert(Notifications data) throws Exception {
		return notificationsDao.insert(data);
	}

	@Override
	public Notifications update(Notifications data) throws Exception {
		return notificationsDao.update(data);
	}

}
