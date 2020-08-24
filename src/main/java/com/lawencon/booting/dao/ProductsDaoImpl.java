package com.lawencon.booting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.Products;

@Repository
public class ProductsDaoImpl extends BaseDao implements ProductsDao{

	@Override
	public Products insert(Products data) throws Exception {
		em.persist(data);
		return data;
	}

	@Override
	public Products update(Products data) throws Exception {
		em.merge(data);
		return data;
	}

	@Override
	public List<Products> getListProducts() throws Exception {
		return em.createQuery("FROM Products", Products.class).getResultList();
	}

	@Override
	public void deleteProducts(String id) throws Exception {
		em.createQuery("DELETE from Products where id = :id")
		.setParameter("id", id);
		
		
	}

	@Override
	public Products getProductsByCode(String code) throws Exception {
		 List<Products> listProducts = em.createQuery("FROM Products where code = :code ", Products.class)
				 .setParameter("code", code).getResultList();
		 return !listProducts.isEmpty() ? listProducts.get(0): null;
	}

}
