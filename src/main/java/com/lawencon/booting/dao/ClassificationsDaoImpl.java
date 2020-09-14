package com.lawencon.booting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.Classifications;

@Repository
public class ClassificationsDaoImpl extends BaseDao implements ClassificationsDao {

	@Override
	public Classifications insert(Classifications data) throws Exception {
		em.persist(data);
		return data;
	}

	@Override
	public Classifications update(Classifications data) throws Exception {
		em.merge(data);
		return data;
	}

	@Override
	public List<Classifications> getListClassifications() throws Exception {
		return em.createQuery("FROM Classifications", Classifications.class).getResultList();
	}

	@Override
	public void deleteClassifications(String id) throws Exception {
		em.createQuery("DELETE from Classifications where id = :id").setParameter("id", id);

	}

	@Override
	public Classifications getClassificationsByCode(String code) throws Exception {
		List<Classifications> listClassifications = em
				.createQuery("FROM Classifications where code = :code ", Classifications.class)
				.setParameter("code", code).getResultList();
		return !listClassifications.isEmpty() ? listClassifications.get(0) : null;
	}

	@Override
	public List<Classifications> getListClassificationsActive() throws Exception {
		return em.createQuery("FROM Classifications WHERE active = TRUE", Classifications.class).getResultList();
	}

}
