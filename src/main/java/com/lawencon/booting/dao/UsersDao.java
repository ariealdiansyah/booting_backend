package com.lawencon.booting.dao;

import java.util.List;

import com.lawencon.booting.model.Users;

public interface UsersDao {

	Users insert(Users data) throws Exception;

	Users update(Users data) throws Exception;

	List<Users> getListUsers() throws Exception;
	
	List<Users> getListUsersByClient(Users data) throws Exception;

	List<Users> getListAgent() throws Exception;

	List<Users> getListUsersActive() throws Exception;

	void delete(String data) throws Exception;
	
	Users getUserByNip(Users data) throws Exception;
	
	void deletePath(String id) throws Exception;
	
}
