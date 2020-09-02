package com.lawencon.booting.dao;

import java.util.List;

import com.lawencon.booting.model.Accounts;

public interface AccountsDao {

	Accounts insert(Accounts data) throws Exception;

	Accounts update(Accounts data) throws Exception;

	List<Accounts> getListAccounts() throws Exception;
	
	List<Accounts> getListAccountsActive() throws Exception;

	void deleteAccounts(String id) throws Exception;
	
	void deletePath(String id) throws Exception;
	
	Accounts findByEmail(Accounts data) throws Exception;
	
	
}
