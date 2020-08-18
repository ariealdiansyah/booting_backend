package com.lawencon.booting.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.Classifications;

@Repository
public class ClassificationsDaoImpl extends BaseDao implements ClassificationsDao{

	@Override
	public Classifications insert(Classifications data) throws Exception {
		 em.persist(data);
		 return data;
	}

}
