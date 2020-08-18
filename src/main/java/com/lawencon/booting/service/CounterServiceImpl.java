package com.lawencon.booting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.CounterDao;

@Service
@Transactional
public class CounterServiceImpl implements CounterService{

	@Autowired
	private CounterDao counterDao;
}
