package com.lawencon.booting.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_r_hdr_tickets")
public class Tickets extends BaseModel{

	@Id
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "id_customer")
	private Users idCustomer;
	
	@ManyToOne
	@JoinColumn(name = "id_agent")
	private Users idAgent;
	
	@ManyToOne
	@JoinColumn(name = "id_product")
	private Products idProduct;
	
	@ManyToOne
	@JoinColumn(name = "id_priority")
	private Priorities idPriority;
	
	@ManyToOne
	@JoinColumn(name = "id_classification")
	private Classifications idClassification;
	
	@ManyToOne
	@JoinColumn(name = "id_status")
	private Statuses idStatus;
	
	private String code;
	private String subject;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Users getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(Users idCustomer) {
		this.idCustomer = idCustomer;
	}
	public Users getIdAgent() {
		return idAgent;
	}
	public void setIdAgent(Users idAgent) {
		this.idAgent = idAgent;
	}
	public Products getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(Products idProduct) {
		this.idProduct = idProduct;
	}
	public Priorities getIdPriority() {
		return idPriority;
	}
	public void setIdPriority(Priorities idPriority) {
		this.idPriority = idPriority;
	}
	public Classifications getIdClassification() {
		return idClassification;
	}
	public void setIdClassification(Classifications idClassification) {
		this.idClassification = idClassification;
	}
	public Statuses getIdStatus() {
		return idStatus;
	}
	public void setIdStatus(Statuses idStatus) {
		this.idStatus = idStatus;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
}
