package com.lawencon.booting.dao;

import com.lawencon.booting.model.Notifications;

public interface NotificationsDao {

	Notifications insert(Notifications data)throws Exception;
	
	Notifications update(Notifications data)throws Exception;
	
}
