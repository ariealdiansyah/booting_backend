package com.lawencon.booting.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.ProductsDao;
import com.lawencon.booting.model.Companies;
import com.lawencon.booting.model.Products;

@Service
@Transactional
public class ProductsServiceImpl extends BaseService implements ProductsService{

	@Autowired
	private ProductsDao productsDao;

	@Autowired
	private ClientProductsService clientProductsService;
	
	@Override
	public Products insert(Products data) throws Exception {
//		data.setId(getUuid());
		data.setCreatedAt(new Date());
		return productsDao.insert(data);
	}

	@Override
	public Products update(Products data) throws Exception {
		data.setUpdatedAt(new Date());
		return productsDao.update(data);
	}

	@Override
	public List<Products> getListProducts() throws Exception {
		return productsDao.getListProducts();
	}
	
	@Override
	public List<Products> getListProductsActive() throws Exception {
		return productsDao.getListProductsActive();
	}

	@Override
	public void deleteProducts(String id) throws Exception {
		productsDao.deleteProducts(id);
		
	}

	@Override
	public Products getProductsByCode(Products code) throws Exception {
		return productsDao.getProductsByCode(code.getCode());
	}

	@Override
	public void deletePath(String id) throws Exception {
		productsDao.deletePath(id);
	}

	@Override
	public List<Products> getListByCompany(Companies data) throws Exception {
		List<String> listData = clientProductsService.getListIdCompany(data);
		return productsDao.getListByCompany(listData);
	}
}
