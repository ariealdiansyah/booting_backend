package com.lawencon.booting.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.Notifications;

@Repository
public class NotificationsDaoImpl extends BaseDao implements NotificationsDao{

	@Override
	public Notifications insert(Notifications data) throws Exception {
		em.persist(data);
		return data;
	}

	@Override
	public Notifications update(Notifications data) throws Exception {
		em.merge(data);
		return data;
	}

}
