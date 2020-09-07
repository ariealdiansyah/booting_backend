package com.lawencon.booting.model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "tb_m_photo_profile")
public class PhotoProfile extends BaseModel{
	
	 private String name;

	  private String type;

	  @Lob
	  private byte[] data;

	  public PhotoProfile() {
	  }

	  public PhotoProfile(String name, String type, byte[] data) {
	    this.name = name;
	    this.type = type;
	    this.data = data;
	  }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	  
	  

}
