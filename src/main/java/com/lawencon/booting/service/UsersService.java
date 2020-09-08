package com.lawencon.booting.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.booting.model.Users;

public interface UsersService {

	Users insert(Users data) throws Exception;

	Users update(Users data,MultipartFile file) throws Exception;

	List<Users> getListUsers() throws Exception;
	
	List<Users> getListAgents() throws Exception;
	
	List<Users> getListClients() throws Exception;
	
	List<Users> getListUsersByClient(Users data) throws Exception;
	
	List<Users> getListUsersActive() throws Exception;

	void delete(String data) throws Exception;
	
	Users getUserByNip(Users data) throws Exception;
	
	void deletePath(String id) throws Exception;

}
