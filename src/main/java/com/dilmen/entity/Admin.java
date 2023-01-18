package com.dilmen.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Admin extends User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Enumerated(EnumType.STRING)
	private EUserType usertype = EUserType.ADMIN;

	public Admin() {
		
	}

	public Admin(String firstName, String lastName, String email, long id) {
		super(firstName, lastName, email);
		this.id = id;
	}

	public Admin(String firstName, String lastName, String email) {
		super(firstName, lastName, email);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EUserType getUsertype() {
		return usertype;
	}

	public void setUsertype(EUserType usertype) {
		this.usertype = usertype;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", usertype=" + usertype + ", First Name = " + getFirstName() + ", Last Name = "
				+ getLastName() + ", Email = " + getEmail();
	}
	
	
	
	
	

}
