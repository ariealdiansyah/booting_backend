package com.lawencon.booting.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.TicketsDao;
import com.lawencon.booting.model.Classifications;
import com.lawencon.booting.model.Companies;
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
	
	@Autowired
	private CompaniesService companiesService;
	
	@Autowired
	private AgentRelationsService agentRelationsService;
	
	@Override
	public Tickets insert(Tickets data) throws Exception {
//		data.setId(getUuid());
		data.setCode(code());
		
		return ticketsDao.insert(data);
	}
	
	@Override
	public Tickets insert(TicketDetails data) throws Exception {
		Tickets ticket = new Tickets();
		TicketDetails ticketDtl = new TicketDetails();
		ticket = data.getIdTicket();
//		ticket.setId(getUuid());
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
		return ticketsDao.getListByIdUser(user.getId());
	}
	
	public String code() {
		String codeA = RandomStringUtils.randomAlphabetic(4);
		String codeB = RandomStringUtils.randomNumeric(3);
		return codeA + "-" + codeB;
	}

	@Override
	public List<Tickets> getListByIdCompany(String data) throws Exception {
		Companies company = new Companies();
		company.setCode(data);
		company = companiesService.getCompanyByCode(company);
		return ticketsDao.getListByIdCompany(company.getId());
	}

	@Override
	public List<Tickets> getListByIdAgent(String data) throws Exception {
		Users user = new Users();
		user.setNip(data);
		user = usersService.getUserByNip(user);
		List<String> listData = agentRelationsService.getListCompanies(user);
		if(listData.isEmpty()) {
			return null;
		}else if(listData.size() == 1) {
			System.out.println(listData.get(0));
			return ticketsDao.getListByIdAgent(listData.get(0));
		}else {
			String comp = ("(" + listData.get(0));
			for(int i = 1; i < listData.size(); i++) {
				System.out.println(comp + listData.get(i));
				comp += (", " + listData.get(i));
			}
			comp += ")";
			return ticketsDao.getListByIdAgent(comp);
		}
	}

}
