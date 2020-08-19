package com.lawencon.booting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.AccountsDao;
import com.lawencon.booting.model.Accounts;

@Service
@Transactional
public class AccountsServiceImpl implements AccountsService{

	@Autowired
	private AccountsDao accountsDao;

	@Override
	public Accounts insert(Accounts data) throws Exception {
		return accountsDao.insert(data);
	}

	@Override
	public Accounts update(Accounts data) throws Exception {
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
}
