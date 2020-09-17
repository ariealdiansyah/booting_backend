package com.lawencon.booting.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.AccountsDao;
import com.lawencon.booting.model.Accounts;
import com.lawencon.booting.model.Companies;
import com.lawencon.booting.model.ForgotPass;
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


	@Override
	public Accounts insert(Accounts data) throws Exception {
		boolean check = true;
		String pwd = code();
		data.setPass(encoder.encode(pwd));
		data.setCreatedAt(new Date());
		List<Companies> listComp = new ArrayList<>();
		listComp = companiesService.getListCompanies();
		for (int i = 0; i < listComp.size(); i++) {
			if (listComp.get(i).getName().equalsIgnoreCase(data.getIdUser().getIdCompany().getName())) {
				data.getIdUser().setIdCompany(listComp.get(i));
				check = false;
				break;
			}
		}
		if (check == true) {
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
		mail.init(data.getEmail(), "Created Account", template.getTemplate(), data, pwd).sendMail();
		return accountsDao.insert(data);
	}


	@Override
	public Accounts update(ForgotPass data) throws Exception {
		Accounts acc = accountsDao.findByEmail(data.getIdAccount());
		boolean check = encoder.matches(data.getPass(), acc.getPass());
		if (!check) {
			return null;
		} else {
			System.out.println(data.getIdAccount().getPass());
			acc.setPass(encoder.encode(data.getIdAccount().getPass()));
			return accountsDao.update(acc);
		}
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
	public void delete(String id) throws Exception {
		accountsDao.delete(id);
	}

	@Override
	public Accounts findByUser(Accounts data) throws Exception {
		return accountsDao.findByUser(data.getIdUser().getId());
	}

	public String code() {
		return RandomStringUtils.randomAlphanumeric(8);
	}

	@Override
	public Accounts forgotPass(Accounts data) throws Exception {
		data = accountsDao.findByEmail(data);
		String pwd = code();
		System.out.println(pwd);
		data.setPass(encoder.encode(pwd));
		TemplateEmail template = new TemplateEmail();
		template.setCode("TMPL0");
		template = templateEmailService.getTemplateEmailByCode(template);
		mail.init(data.getEmail(), "Password Changed", template.getTemplate(), data, pwd).sendMail();
		return accountsDao.update(data);
	}

}
