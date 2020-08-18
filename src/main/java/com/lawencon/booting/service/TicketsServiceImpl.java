package com.lawencon.booting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.TicketsDao;
import com.lawencon.booting.model.Tickets;

@Service
@Transactional
public class TicketsServiceImpl implements TicketsService {

	@Autowired
	private TicketsDao ticketsDao;
	
	@Override
	public Tickets insert(Tickets data) throws Exception {
		
		return null;
	}

}
