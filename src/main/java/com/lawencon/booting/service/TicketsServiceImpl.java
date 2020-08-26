package com.lawencon.booting.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.TicketsDao;
import com.lawencon.booting.model.Classifications;
import com.lawencon.booting.model.Priorities;
import com.lawencon.booting.model.Products;
import com.lawencon.booting.model.Status;
import com.lawencon.booting.model.TicketDetails;
import com.lawencon.booting.model.Tickets;
import com.lawencon.booting.model.Users;

@Service
@Transactional
public class TicketsServiceImpl extends BaseService implements TicketsService {

	@Autowired
	private TicketsDao ticketsDao;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private ProductsService productsService;
	
	@Autowired
	private PrioritiesService prioritiesService;
	
	@Autowired
	private ClassificationsService classificationsService;
	
	@Autowired
	private TicketDetailService ticketDetailService;
	
	@Autowired
	private StatusService statusService;	
	
	@Override
	public Tickets insert(Tickets data) throws Exception {
		data.setId(getUuid());
		data.setCode(code());
		
		return ticketsDao.insert(data);
	}
	
	@Override
	public Tickets insert(TicketDetails data) throws Exception {
		Tickets ticket = new Tickets();
		TicketDetails ticketDtl = new TicketDetails();
		ticket = data.getIdTicket();
		ticket.setId(getUuid());
		ticket.setCode(code());
		
		Products product = productsService.getProductsByCode(data.getIdTicket().getIdProduct());
		ticket.setIdProduct(product);
		
		Priorities priority = prioritiesService.getPrioritiesByCode(data.getIdTicket().getIdPriority());
		ticket.setIdPriority(priority);
		
		Classifications classification = classificationsService.getClassificationsByCode(data.getIdTicket().getIdClassification());
		ticket.setIdClassification(classification);
		
		Status status = statusService.getStatusesByCode(data.getIdTicket().getIdStatus());
		ticket.setIdStatus(status);
		
		ticket.setCreatedAt(new Date());
		ticket.setCreatedBy(data.getIdTicket().getIdCustomer().getName());
		
		ticket = ticketsDao.insert(ticket);
		ticketDtl.setIdTicket(ticket);
		ticketDtl.setSender(data.getSender());
		
		ticketDetailService.insert(ticketDtl);
		
		return ticket;
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
		user = usersService.getUserByNip(user);
		return getListByIdUser(user.getId());
	}
	
	public String code() {
		String codeA = RandomStringUtils.randomAlphabetic(4);
		String codeB = RandomStringUtils.randomNumeric(3);
		return codeA + "-" + codeB;
	}

}
