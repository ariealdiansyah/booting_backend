package com.lawencon.booting.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.TicketsDao;
import com.lawencon.booting.model.Tickets;

@Service
@Transactional
public class TicketsServiceImpl extends BaseService implements TicketsService {

	@Autowired
	private TicketsDao ticketsDao;
	
	@Override
	public Tickets insert(Tickets data) throws Exception {
		data.setId(getUuid());
		data.setCode(code());
		
		return null;
	}
	
	public String code() {
		String codeA = RandomStringUtils.randomAlphabetic(4);
		String codeB = RandomStringUtils.randomNumeric(4);
		return codeA + "-" + codeB;
	}

}
