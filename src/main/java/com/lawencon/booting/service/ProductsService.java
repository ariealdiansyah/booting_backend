package com.lawencon.booting.service;

import java.util.List;

import com.lawencon.booting.model.Products;

public interface ProductsService {

	Products insert(Products data)throws Exception;
	Products update(Products data) throws Exception;
	List<Products> getListProducts() throws Exception;
	Products getProductsByCode(Products code) throws Exception;
	void deleteProducts(String id) throws Exception;
	
}
