package com.rasthrabhasha.admin;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin {

	
	
	public long getAdmin_id() {
		return admin_id;
	}


	public void setAdmin_id(long admin_id) {
		this.admin_id = admin_id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long admin_id;
	
	
	@Column
	private String username;
	
	
	@Column
	private String password;



	


}
