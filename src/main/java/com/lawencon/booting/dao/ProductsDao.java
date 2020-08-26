package com.lawencon.booting.dao;

import java.util.List;

import com.lawencon.booting.model.Products;

public interface ProductsDao {

	Products insert(Products data)throws Exception;
	Products update(Products data) throws Exception;
	List<Products> getListProducts() throws Exception;
	Products getProductsByCode(String code) throws Exception;
	void deleteProducts(String code) throws Exception;
}
