package com.lawencon.booting.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_m_template_emails")
public class TemplateEmail extends BaseModel{

//	@Id
//	private String id;
	
	private String code;
	private String name;
	private String template;
	
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
}
