package com.lawencon.booting.dao;

import java.util.List;

import com.lawencon.booting.model.ClientProducts;

public interface ClientProductsDao {

	ClientProducts insert(ClientProducts data) throws Exception;

	ClientProducts update(ClientProducts data) throws Exception;

	List<ClientProducts> getListClientProducts() throws Exception;

	void delete(String id) throws Exception;
}
