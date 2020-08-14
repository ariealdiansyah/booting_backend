package com.lawencon.booting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "tb_m_accounts")
public class Accounts {

	@Id
	private String id;
	
	@NotNull
	@Column(nullable = false, unique = true)
	private String email;
	
	@NotNull
	@Column(nullable = false)
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "id_roles")
	private Roles idRoles;
	
	@ManyToOne
	@JoinColumn(name = "id_users")
	private Users idUsers;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Roles getIdRoles() {
		return idRoles;
	}

	public void setIdRoles(Roles idRoles) {
		this.idRoles = idRoles;
	}

	public Users getIdUsers() {
		return idUsers;
	}

	public void setIdUsers(Users idUsers) {
		this.idUsers = idUsers;
	}

}
