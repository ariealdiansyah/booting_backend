package com.lawencon.booting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.UsersDao;
import com.lawencon.booting.model.Users;

@Service
@Transactional
public class UsersServiceImpl extends BaseService implements UsersService {

	@Autowired
	private UsersDao usersDao;

	@Override
	public Users insert(Users data) throws Exception {
		data.setId(getUuid());		
		return usersDao.insert(data);
	}

	@Override
	public Users update(Users data) throws Exception {
		return usersDao.update(data);
	}

	@Override
	public List<Users> getListUsers() throws Exception {
		return usersDao.getListUsers();
	}

	@Override
	public void delete(String data) throws Exception {
		usersDao.delete(data);
	}

}
