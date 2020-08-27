package com.lawencon.booting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.ProductsDao;
import com.lawencon.booting.model.Products;

@Service
@Transactional
public class ProductsServiceImpl extends BaseService implements ProductsService{

	@Autowired
	private ProductsDao productsDao;

	@Override
	public Products insert(Products data) throws Exception {
//		data.setId(getUuid());
		return productsDao.insert(data);
	}

	@Override
	public Products update(Products data) throws Exception {
		return productsDao.update(data);
	}

	@Override
	public List<Products> getListProducts() throws Exception {
		return productsDao.getListProducts();
	}

	@Override
	public void deleteProducts(String id) throws Exception {
		productsDao.deleteProducts(id);
		
	}

	@Override
	public Products getProductsByCode(Products code) throws Exception {
		return productsDao.getProductsByCode(code.getCode());
	}
}
