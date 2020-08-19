package com.lawencon.booting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.Classifications;

@Repository
public class ClassificationsDaoImpl extends BaseDao implements ClassificationsDao{

	@Override
	public Classifications insert(Classifications data) throws Exception {
		 em.persist(data);
		 return data;
	}

	@Override
	public Classifications update(Classifications data) throws Exception {
		em.persist(data);
		return data;
	}

	@Override
	public List<Classifications> getListClassifications() throws Exception {
		return em.createQuery("FROM Classifications", Classifications.class).getResultList();
	}

	@Override
	public void deleteClassifications(Long id) throws Exception {
		em.createQuery("DELETE from Classifications where id = :id")
		.setParameter("id", id);
		
	}

}
