package com.lawencon.booting.service;

import com.lawencon.booting.model.TemplateEmail;

public interface TemplateEmailService {

	TemplateEmail insert(TemplateEmail data)throws Exception;
	TemplateEmail getTemplateEmailByCode(TemplateEmail code) throws Exception;
}
