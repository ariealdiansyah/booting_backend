package com.lawencon.booting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.ClientProducts;

@Repository
public class ClientProductsDaoImpl extends BaseDao implements ClientProductsDao {

	@Override
	public ClientProducts insert(ClientProducts data) throws Exception {
		em.persist(data);
		return data;
	}

	@Override
	public ClientProducts update(ClientProducts data) throws Exception {
		return em.merge(data);
	}

	@Override
	public List<ClientProducts> getListClientProducts() throws Exception {
		return em.createQuery("FROM ClientProducts", ClientProducts.class).getResultList();
	}

	@Override
	public void delete(String id) throws Exception {
		em.createQuery("DELETE from ClientProducts where id = :id").setParameter("id", id);
	}

	@Override
	public List<ClientProducts> getListByCompany(ClientProducts data) throws Exception {
		return em.createQuery("FROM ClientProducts WHERE idCompany = :idCompany", ClientProducts.class)
				.setParameter("idCompany", data.getIdCompany().getId())
				.getResultList();
	}

}
