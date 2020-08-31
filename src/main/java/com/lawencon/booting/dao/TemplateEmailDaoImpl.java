package com.lawencon.booting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.TemplateEmail;

@Repository
public class TemplateEmailDaoImpl extends BaseDao implements TemplateEmailDao{

	@Override
	public TemplateEmail insert(TemplateEmail data) throws Exception {
		em.persist(data);
		return data;
	}

	@Override
	public TemplateEmail getTemplateEmailByCode(String code) throws Exception {
		List<TemplateEmail> listTemplateEmail = em.createQuery("FROM TemplateEmail where code = :code ", TemplateEmail.class)
				 .setParameter("code", code).getResultList();
		 return !listTemplateEmail.isEmpty() ? listTemplateEmail.get(0): null;
	}

}
