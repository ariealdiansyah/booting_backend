package com.lawencon.booting.service;

import com.lawencon.booting.model.Notifications;

public interface NotificationsService {

	Notifications insert(Notifications data) throws Exception;

	Notifications update(Notifications data) throws Exception;
}
