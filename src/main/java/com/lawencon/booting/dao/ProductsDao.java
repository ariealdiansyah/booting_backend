package com.lawencon.booting.dao;

import java.util.List;

import com.lawencon.booting.model.Products;

public interface ProductsDao {

	Products insert(Products data)throws Exception;
	Products update(Products data) throws Exception;
	List<Products> getListProducts() throws Exception;
	void deleteProducts(Long id) throws Exception;
}
