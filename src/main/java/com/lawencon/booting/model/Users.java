package com.lawencon.booting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;


@Entity
@Table(name = "tb_m_users")
public class Users extends BaseModel{

//	@Id
//	private String id;
	
	@NotNull
	@Column(nullable = false, unique = true)
	private String nip;
	
	@ManyToOne
	@JoinColumn(name = "photo_profile")
	private PhotoProfile idPhoto;

	@ManyToOne
	@JoinColumn(name = "id_company")
	private Companies idCompany;
	
	@ManyToOne
	@JoinColumn(name = "id_role")
	private Roles idRole;
	
	private String address;
	private String contact;
	private String name;
	 
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
	public String getNip() {
		return nip;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	public Companies getIdCompany() {
		return idCompany;
	}
	public void setIdCompany(Companies idCompany) {
		this.idCompany = idCompany;
	}
	public Roles getIdRole() {
		return idRole;
	}
	public void setIdRole(Roles idRole) {
		this.idRole = idRole;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PhotoProfile getIdPhoto() {
		return idPhoto;
	}
	public void setIdPhoto(PhotoProfile idPhoto) {
		this.idPhoto = idPhoto;
	}
	
	
}
