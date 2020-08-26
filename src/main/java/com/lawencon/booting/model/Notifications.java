package com.lawencon.booting.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_m_notifications")
public class Notifications extends BaseModel{
	
	@Id
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	private Users idUser;
	
	private String notif;
	private String link;
	private Boolean status;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Users getIdUser() {
		return idUser;
	}
	public void setIdUser(Users idUser) {
		this.idUser = idUser;
	}
	public String getNotif() {
		return notif;
	}
	public void setNotif(String notif) {
		this.notif = notif;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
}
