package com.lawencon.booting.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.UsersDao;
import com.lawencon.booting.model.Users;
import com.lawencon.booting.utility.Mail;

@Service
@Transactional
public class UsersServiceImpl extends BaseService implements UsersService {

	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private Mail mail;	
	
	@Autowired
	private TemplateEmailService templateEmailService;

	@Override
	public Users insert(Users data) throws Exception {
		data.setId(getUuid());		
		data.setCreatedAt(new Date());
		return usersDao.insert(data);
	}

	@Override
	public Users update(Users data) throws Exception {
		data.setUpdatedAt(new Date());
		
		return usersDao.update(data);
	}

	@Override
	public List<Users> getListUsers() throws Exception {
		return usersDao.getListUsers();
	}
	
	@Override
	public List<Users> getListUsersActive() throws Exception {
		return usersDao.getListUsersActive();
	}

	@Override
	public void delete(String data) throws Exception {
		usersDao.delete(data);
	}

	@Override
	public Users getUserByNip(Users data) throws Exception {
		return usersDao.getUserByNip(data);
	}

	@Override
	public void deletePath(String id) throws Exception {
		usersDao.deletePath(id);
	}

}
