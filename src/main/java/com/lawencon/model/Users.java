package com.lawencon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;


@Entity
@Table(name = "tb_m_users")
public class Users {

	@Id
	private String id;
	
	@NotNull
	@Column(nullable = false, unique = true)
	private String nip;

	@ManyToOne
	@JoinColumn(name = "id_companies")
	private Companies idCompanies;
	
	private String name;
	private String reportTo;
	
	public Companies getIdCompany() {
		return idCompanies;
	}
	public void setIdCompany(Companies idCompany) {
		this.idCompanies = idCompany;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNip() {
		return nip;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReport() {
		return reportTo;
	}
	public void setReport(String report) {
		this.reportTo = report;
	}
}
