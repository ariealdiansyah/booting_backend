package com.lawencon.booting.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_m_agent_relations")
public class AgentRelations {

	@Id
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "id_agent")
	private Users idAgent;
	
	@ManyToOne
	@JoinColumn(name = "id_client")
	private Users idClient;
	
	private Date startDate;
	private Date endDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Users getIdAgent() {
		return idAgent;
	}
	public void setIdAgent(Users idAgent) {
		this.idAgent = idAgent;
	}
	public Users getIdClient() {
		return idClient;
	}
	public void setIdClient(Users idClient) {
		this.idClient = idClient;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
