package com.lawencon.booting.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.TicketsDao;
import com.lawencon.booting.model.Accounts;
import com.lawencon.booting.model.Classifications;
import com.lawencon.booting.model.ClientProducts;
import com.lawencon.booting.model.Companies;
import com.lawencon.booting.model.Priorities;
import com.lawencon.booting.model.Products;
import com.lawencon.booting.model.Status;
import com.lawencon.booting.model.TemplateEmail;
import com.lawencon.booting.model.TicketCharts;
import com.lawencon.booting.model.TicketDetails;
import com.lawencon.booting.model.TicketHeader;
import com.lawencon.booting.model.TicketPriority;
import com.lawencon.booting.model.TicketStatus;
import com.lawencon.booting.model.Tickets;
import com.lawencon.booting.model.Users;
import com.lawencon.booting.utility.Mail;

@Service
@Transactional
public class TicketsServiceImpl extends BaseService implements TicketsService {

	@Autowired
	private AccountsService accountsService;

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
	private StatusService statusService;

	@Autowired
	private CompaniesService companiesService;

	@Autowired
	private AgentRelationsService agentRelationsService;

	@Autowired
	private Mail mail;

	@Autowired
	private TemplateEmailService templateEmailService;

	@Autowired
	private ClientProductsService clientProductsService;

	@Override
	public Tickets insert(Tickets data) throws Exception {
		Tickets ticket = new Tickets();
		ticket.setCode(code());
		ticket.setSubject(data.getSubject());

		Products product = productsService.getProductsByCode(data.getIdProduct());
		ticket.setIdProduct(product);

		Priorities priority = prioritiesService.getPrioritiesByCode(data.getIdPriority());
		ticket.setIdPriority(priority);

		Classifications classification = classificationsService.getClassificationsByCode(data.getIdClassification());
		ticket.setIdClassification(classification);

		Status status = statusService.getStatusesByCode(data.getIdStatus());
		ticket.setIdStatus(status);

		Users us = usersService.getUserByNip(data.getIdCustomer());
		ticket.setIdCustomer(us);

		String code = priority.getCode();
		TicketPriority tp = new TicketPriority();

		ClientProducts cp = new ClientProducts();
		tp.setIdCompany(us.getIdCompany().getId());
		tp.setIdProduct(product.getId());

		cp = clientProductsService.getData(tp);

		if ("URG".equals(code)) {
			if (cp.getTicketUrgent() != 0) {
				cp.setTicketUrgent(cp.getTicketUrgent() - 1);
				cp.setUpdatedBy(data.getCreatedBy());
				cp.setUpdatedAt(new Date());
				clientProductsService.update(cp);
			} else {
				return null;
			}
		}
		if ("MED".equals(code)) {
			if (cp.getTicketMedium() != 0) {
				cp.setTicketMedium(cp.getTicketMedium() - 1);
				cp.setUpdatedBy(data.getCreatedBy());
				cp.setUpdatedAt(new Date());
				clientProductsService.update(cp);
			} else {
				return null;
			}
		}

		ticket.setCreatedAt(new Date());

		Accounts acc = new Accounts();
		acc.setIdUser(us);
		acc = accountsService.findByUser(acc);
		TemplateEmail template = new TemplateEmail();
		template.setCode("TKTTMPL");
		template = templateEmailService.getTemplateEmailByCode(template);
		mail.init(acc.getEmail(), "Created Tickets", template.getTemplate(), us.getName(), ticket).sendMail();

		return ticketsDao.insert(ticket);
	}

	@Override
	public Tickets insert(TicketDetails data) throws Exception {
		Tickets ticket = new Tickets();
		TicketDetails ticketDtl = new TicketDetails();
		ticketDtl = data;
		ticket = data.getIdTicket();
		ticket.setCode(code());

		Products product = productsService.getProductsByCode(data.getIdTicket().getIdProduct());
		ticket.setIdProduct(product);

		Priorities priority = prioritiesService.getPrioritiesByCode(data.getIdTicket().getIdPriority());
		ticket.setIdPriority(priority);

		Classifications classification = classificationsService
				.getClassificationsByCode(data.getIdTicket().getIdClassification());
		ticket.setIdClassification(classification);

		Status status = statusService.getStatusesByCode(data.getIdTicket().getIdStatus());
		ticket.setIdStatus(status);

		Users us = usersService.getUserByNip(data.getIdTicket().getIdCustomer());
		ticket.setIdCustomer(us);

		ticket.setCreatedAt(new Date());
		ticket.setCreatedBy(data.getSender());

		ticket = ticketsDao.insert(ticket);
		ticketDtl.setIdTicket(ticket);
		ticketDtl.setSender(data.getSender());

		return ticket;
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
	public List<Tickets> getListByIdUser(Users data) throws Exception {
		data = usersService.getUserByNip(data);
		return ticketsDao.getListByIdUser(data.getId());
	}

	public String code() {
		String codeA = RandomStringUtils.randomAlphabetic(4);
		String codeB = RandomStringUtils.randomNumeric(3);
		return codeA + "-" + codeB;
	}

	@Override
	public List<Tickets> getListByIdCompany(Companies data) throws Exception {
		data = companiesService.getCompanyByCode(data);
		return ticketsDao.getListByIdCompany(data.getId());
	}

	@Override
	public List<Tickets> getListByIdAgent(Users data) throws Exception {
		data = usersService.getUserByNip(data);
		List<String> listData = agentRelationsService.getListCompanies(data);
		if (listData.isEmpty()) {
			return null;
		} else {
			return ticketsDao.getListByIdAgent(listData);
		}
	}

	@Override
	public TicketStatus selectStatus() throws Exception {
		return ticketsDao.selectStatus();
	}

	@Override
	public List<TicketCharts> getListTicketCharts(Long data) throws Exception {
		return ticketsDao.getListTicketCharts(data);
	}

	@Override
	public TicketStatus statusAgent(Users data) throws Exception {
		data = usersService.getUserByNip(data);
		List<String> listData = agentRelationsService.getListCompanies(data);
		if (listData.isEmpty()) {
			return null;
		} else {
			return ticketsDao.statusAgent(listData);
		}
	}

	@Override
	public TicketStatus statusClient(Companies data) throws Exception {
		data = companiesService.getCompanyByCode(data);
		return ticketsDao.statusClient(data.getId());
	}

	@Override
	public TicketStatus statusCustomer(Users data) throws Exception {
		data = usersService.getUserByNip(data);
		return ticketsDao.statusCustomer(data.getId());
	}

	@Override
	public TicketHeader getTicket(Tickets data) throws Exception {
		TicketHeader ticketHead = new TicketHeader();

		data = ticketsDao.getTicketByCode(data);
		ticketHead.setIdTicket(data);

		Users user = agentRelationsService.getAgentByCompany(data.getIdCustomer().getIdCompany());
		ticketHead.setIdAgent(user);

		return ticketHead;
	}

	@Override
	public List<TicketCharts> getChartsByClient(Companies data) throws Exception {
		return ticketsDao.getChartsByClient(data);
	}

	@Override
	public List<TicketCharts> getChartsByAgent(Users data) throws Exception {
		data = usersService.getUserByNip(data);
		if (data == null) {
			return null;
		}
		List<String> listData = agentRelationsService.getListCompanies(data);
		if (listData.isEmpty()) {
			return null;
		} else {
			return ticketsDao.getChartsByAgent(listData);
		}
	}

	@Override
	public List<?> getListRelations(String data) throws Exception {
		return ticketsDao.getListRelations(data);
	}

	@Override
	public Tickets update(Tickets data) throws Exception {
		Status sts = statusService.getStatusesByCode(data.getIdStatus());
		data.setIdStatus(sts);
		data.setUpdatedAt(new Date());
		return ticketsDao.update(data);
	}

}
