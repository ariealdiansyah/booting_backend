package com.lawencon.booting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.AccountsDao;
import com.lawencon.booting.model.Accounts;

@Service
@Transactional
public class AccountsServiceImpl extends BaseService implements AccountsService {

	@Autowired
	private AccountsDao accountsDao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
//	@Autowired
//	private UsersServiceImpl users;
//	
//	@Autowired
//	private TicketsServiceImpl ticket;

	@Override
	public Accounts insert(Accounts data) throws Exception {
		data.setId(getUuid());
		data.setPass(encoder.encode(data.getPass()));
		return accountsDao.insert(data);
	}

	@Override
	public Accounts update(Accounts data) throws Exception {
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
