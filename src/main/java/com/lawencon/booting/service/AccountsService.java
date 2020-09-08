package com.lawencon.booting.service;

import java.util.List;

import com.lawencon.booting.model.Accounts;
import com.lawencon.booting.model.ForgotPass;

public interface AccountsService {

	Accounts insert(Accounts data) throws Exception;

	Accounts update(ForgotPass data) throws Exception;

	List<Accounts> getListAccounts() throws Exception;

	List<Accounts> getListAccountsActive() throws Exception;

	void deleteAccounts(String id) throws Exception;

	void deletePath(String id) throws Exception;

	Accounts findByEmail(Accounts data) throws Exception;

	Accounts findByUser(Accounts data) throws Exception;

	Accounts forgotPass(Accounts data) throws Exception;
}
