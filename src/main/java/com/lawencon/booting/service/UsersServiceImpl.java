package com.lawencon.booting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.UsersDao;
import com.lawencon.booting.model.Users;

@Service
@Transactional
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersDao usersDao;
	
	@Override
	public Users insert(Users data) throws Exception {
		return usersDao.insert(data);
	}

}
