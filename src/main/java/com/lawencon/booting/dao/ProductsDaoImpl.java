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
	public List<Products> getListProductsActive() throws Exception {
		return em.createQuery("FROM Products WHERE actice = true", Products.class).getResultList();
	}

	@Override
	public void deleteProducts(String id) throws Exception {
		em.createQuery("DELETE from Products where id = :id")
		.setParameter("id", id).executeUpdate();
	}

	@Override
	public Products getProductsByCode(String code) throws Exception {
		 List<Products> listProducts = em.createQuery("FROM Products where code = :code ", Products.class)
				 .setParameter("code", code).getResultList();
		 return !listProducts.isEmpty() ? listProducts.get(0): null;
	}

	@Override
	public List<Products> getListByCompany(List<String> data) throws Exception {
		return em.createQuery("FROM Products WHERE id in (:id) AND active = true", Products.class)
				.setParameter("id", data)
				.getResultList();
	}

	@Override
	public void deletePath(String id) throws Exception {
		em.createQuery("UPDATE Products SET active = false WHERE id = :id")
		.setParameter("id", id).executeUpdate();
	}

}
