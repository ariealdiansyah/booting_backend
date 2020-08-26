package com.lawencon.booting.service;

import java.util.UUID;

public abstract class BaseService {

	String getUuid() {
		return UUID.randomUUID().toString();
	}
}
