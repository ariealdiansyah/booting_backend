package com.lawencon.booting.service;

import java.util.List;

import com.lawencon.booting.model.Users;

public interface UsersService {

	Users insert(Users data) throws Exception;

	Users update(Users data) throws Exception;

	List<Users> getListUsers() throws Exception;

	void delete(String data) throws Exception;
}
