package com.lawencon.booting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.HelperDao;

@Service
@Transactional
public class HelperServiceImpl {

	@Autowired
	private HelperDao helperDao;
}
