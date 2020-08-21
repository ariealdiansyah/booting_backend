package com.lawencon.booting.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_m_client_products")
public class ClientProducts extends BaseModel{

	@Id
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "id_company")
	private Companies idCompany;
	
	@ManyToOne
	@JoinColumn(name = "id_product")
	private Products idProduct;
	
	private Integer ticketUrgent;
	private Integer ticketMedium;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Companies getIdCompany() {
		return idCompany;
	}
	public void setIdCompany(Companies idCompany) {
		this.idCompany = idCompany;
	}
	public Products getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(Products idProduct) {
		this.idProduct = idProduct;
	}
	public Integer getTicketUrgent() {
		return ticketUrgent;
	}
	public void setTicketUrgent(Integer ticketUrgent) {
		this.ticketUrgent = ticketUrgent;
	}
	public Integer getTicketMedium() {
		return ticketMedium;
	}
	public void setTicketMedium(Integer ticketMedium) {
		this.ticketMedium = ticketMedium;
	}
}
