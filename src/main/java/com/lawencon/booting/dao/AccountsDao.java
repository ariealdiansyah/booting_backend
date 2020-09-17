package com.lawencon.booting.dao;

import com.lawencon.booting.model.Accounts;

public interface AccountsDao {

	Accounts insert(Accounts data) throws Exception;

	Accounts update(Accounts data) throws Exception;

	void deleteAccounts(String id) throws Exception;
	
	void delete(String id) throws Exception;
	
	Accounts findByEmail(Accounts data) throws Exception;
	
	Accounts findByUser(String id)throws Exception;	
	
}
