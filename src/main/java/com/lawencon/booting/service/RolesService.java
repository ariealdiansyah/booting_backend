package com.lawencon.booting.service;

import java.util.List;

import com.lawencon.booting.model.Roles;

public interface RolesService {

	Roles insert(Roles data)throws Exception;
	Roles update(Roles data) throws Exception;
	List<Roles> getListRoles() throws Exception;
	Roles getRolesByCode(Roles code) throws Exception;
	void deleteRoles(String id) throws Exception;
}
