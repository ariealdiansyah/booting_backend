package com.lawencon.booting.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_r_dtl_ticket")
public class TicketDetails extends BaseModel{

	@Id
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "id_ticket")
	private Tickets idTicket;
	
	@ManyToOne
	@JoinColumn(name = "id_status_before")
	private Statuses statusBefore;
	
	@ManyToOne
	@JoinColumn(name = "id_status_after")
	private Statuses statusAfter;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Tickets getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(Tickets idTicket) {
		this.idTicket = idTicket;
	}

	public Statuses getStatusBefore() {
		return statusBefore;
	}

	public void setStatusBefore(Statuses statusBefore) {
		this.statusBefore = statusBefore;
	}

	public Statuses getStatusAfter() {
		return statusAfter;
	}

	public void setStatusAfter(Statuses statusAfter) {
		this.statusAfter = statusAfter;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
