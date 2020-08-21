package com.lawencon.booting.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_m_attachments")
public class Attachments extends BaseModel{

	@Id
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "id_ticket_detail")
	private TicketDetails idTicketDetail;
	
	@Lob
	private Byte[] attach;
	
	private String extension;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TicketDetails getIdTicketDetail() {
		return idTicketDetail;
	}

	public void setIdTicketDetail(TicketDetails idTicketDetail) {
		this.idTicketDetail = idTicketDetail;
	}

	public Byte[] getAttach() {
		return attach;
	}

	public void setAttach(Byte[] attach) {
		this.attach = attach;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
}
