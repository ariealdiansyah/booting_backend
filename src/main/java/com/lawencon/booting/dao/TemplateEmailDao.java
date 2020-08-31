package com.lawencon.booting.dao;

import com.lawencon.booting.model.TemplateEmail;

public interface TemplateEmailDao {

	TemplateEmail insert(TemplateEmail data)throws Exception;
	TemplateEmail getTemplateEmailByCode(String code) throws Exception;
	
}
