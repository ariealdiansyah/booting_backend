package com.lawencon.booting.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.booting.dao.TicketDetailsDao;
import com.lawencon.booting.model.TicketDetails;

@Service
@Transactional
public class TicketDetailServiceImpl extends BaseService implements TicketDetailService{
	
	@Autowired
	private TicketDetailsDao ticketDetailsDao;

	@Override
	public TicketDetails insert(TicketDetails data) throws Exception {
//		data.setId(getUuid()); 
		data.setCreatedAt(new Date());
		data.setDateCreated(data.getCreatedAt());
		data.setCreatedBy(data.getSender());
		return ticketDetailsDao.insert(data);
	}

	@Override
	public List<TicketDetails> getListTicketDetails(String data) throws Exception {
		return ticketDetailsDao.getListTicketDetails(data);
	}

}
