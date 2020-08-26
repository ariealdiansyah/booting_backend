package com.lawencon.booting.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lawencon.booting.model.Accounts;
import com.lawencon.booting.service.AccountsService;


@Service
public class ApiSecurityServiceImpl implements UserDetailsService{

	@Autowired
	private AccountsService accountService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Accounts account = new Accounts();
		account.setEmail(email);
		try {
			Accounts accDb = accountService.findByEmail(account);
			if(accDb != null) {
				return new User(accDb.getEmail(), accDb.getPass(), new ArrayList<>());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}

}
