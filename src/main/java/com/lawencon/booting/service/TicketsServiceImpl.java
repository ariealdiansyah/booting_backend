package com.lawencon.booting.service;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.TicketsDao;
import com.lawencon.booting.model.TicketDetails;
import com.lawencon.booting.model.Tickets;
import com.lawencon.booting.model.Users;

@Service
@Transactional
public class TicketsServiceImpl extends BaseService implements TicketsService {

	@Autowired
	private TicketsDao ticketsDao;
	
	@Autowired
	private UsersService userService;
	
	
	@Override
	public Tickets insert(Tickets data) throws Exception {
		data.setId(getUuid());
		data.setCode(code());
		
		return ticketsDao.insert(data);
	}
	
	@Override
	public Tickets insert(TicketDetails data) throws Exception {
		Tickets tickets = data.getIdTicket();
		tickets.setId(getUuid());
		tickets.setCode(code());
		ticketsDao.insert(tickets);
		return tickets;
	}

	@Override
	public Tickets update(Tickets data) throws Exception {
		return ticketsDao.update(data);
	}

	@Override
	public List<Tickets> getListTickets() throws Exception {
		return ticketsDao.getListTickets();
	}

	@Override
	public void delete(String id) throws Exception {
		ticketsDao.delete(id);
	}

	@Override
	public List<Tickets> getListByIdUser(String data) throws Exception {
		Users user = new Users();
		user.setNip(data);
		user = userService.getUserByNip(user);
		return getListByIdUser(user.getId());
	}
	
	public String code() {
		String codeA = RandomStringUtils.randomAlphabetic(4);
		String codeB = RandomStringUtils.randomNumeric(3);
		return codeA + "-" + codeB;
	}

}
