package com.lawencon.booting.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.AccountsDao;
import com.lawencon.booting.model.Accounts;
import com.lawencon.booting.model.Companies;
import com.lawencon.booting.model.Roles;
import com.lawencon.booting.model.TemplateEmail;
import com.lawencon.booting.model.Users;
import com.lawencon.booting.utility.Mail;

@Service
@Transactional
public class AccountsServiceImpl extends BaseService implements AccountsService {

	@Autowired
	private AccountsDao accountsDao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private CompaniesService companiesService;
	
	@Autowired
	private RolesService roleService;
	
	@Autowired
	private Mail mail;	
	
	@Autowired
	private TemplateEmailService templateEmailService;
	
//	@Autowired
//	private TicketsServiceImpl ticket;

	@Override
	public Accounts insert(Accounts data) throws Exception {
//		data.setId(getUuid());
		boolean check = true;
		String pwd = data.getPass();
		data.setPass(encoder.encode(data.getPass()));
		data.setCreatedAt(new Date());
		List<Companies> listComp = new ArrayList<>();
		listComp = companiesService.getListCompanies();
		for(int i = 0; i < listComp.size(); i++) {
			if(listComp.get(i).getName().equalsIgnoreCase(data.getIdUser().getIdCompany().getName())) {
				data.getIdUser().setIdCompany(listComp.get(i));
				check = false;
				break;
			}
		}
		if(check == true) {
			Companies comp = new Companies();
			comp = companiesService.insert(data.getIdUser().getIdCompany());
			data.getIdUser().setIdCompany(comp);
		}
		Roles rl = roleService.getRolesByCode(data.getIdUser().getIdRole());
		data.getIdUser().setIdRole(rl);
		Users us = new Users();
		us = usersService.insert(data.getIdUser());
		data.setIdUser(us);
		TemplateEmail template = new TemplateEmail();
		template.setCode("TMPL0");
		template = templateEmailService.getTemplateEmailByCode(template);
		mail
		.init(data.getEmail(), "Created Account", template.getTemplate(),data, pwd)
		.sendMail();
		return accountsDao.insert(data);
	}

	@Override
	public Accounts update(Accounts data) throws Exception {
		data.setUpdatedAt(new Date());
		data.setPass(encoder.encode(data.getPass()));
		return accountsDao.update(data);
	}

	@Override
	public List<Accounts> getListAccounts() throws Exception {
		return accountsDao.getListAccounts();
	}

	@Override
	public void deleteAccounts(String id) throws Exception {
		accountsDao.deleteAccounts(id);
	}

	@Override
	public Accounts findByEmail(Accounts data) throws Exception {
		return accountsDao.findByEmail(data);
	}

	@Override
	public List<Accounts> getListAccountsActive() throws Exception {
		return accountsDao.getListAccountsActive();
	}

	@Override
	public void deletePath(String id) throws Exception {
		accountsDao.deletePath(id);
	}

	@Override
	public Accounts findByUser(Accounts data) throws Exception {
		return accountsDao.findByUser(data.getIdUser().getId());
	}
	
//	private void test() {
//		for(int i = 0; i<10; i++ ) {
//			System.out.println("");
//			Users data = new Users();
//			try {
//				data = users.insert(data);
//				System.out.println(data.getId() + " == " + ticket.code());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}		
//	}
}
