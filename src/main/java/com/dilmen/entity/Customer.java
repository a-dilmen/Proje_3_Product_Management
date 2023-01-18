package com.dilmen.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Customer extends User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Enumerated(EnumType.STRING)
	private EUserType usertype = EUserType.CUSTOMER;

	private String password;

	@Column(unique = true)
	private String identityNumber;
	
	@ManyToMany
	private List<Product> products;

	public Customer() {
		super();
	}

	public Customer(String firstName, String lastName, String email, long id, String password,
			String identityNumber) {
		super(firstName, lastName, email);
		this.id = id;
		this.password = password;
		this.identityNumber = identityNumber;
	}

	public Customer(String firstName, String lastName, String email, String password,
			String identityNumber) {
		super(firstName, lastName, email);
		this.password = password;
		this.identityNumber = identityNumber;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Customer id = " + id + ", usertype = " + usertype + ", password = " + password + ", identityNumber = "
				+ identityNumber + ", products=" + products.size() + ", First Name = " + getFirstName() + ", getLastName = "
				+ getLastName() + ", Email = " + getEmail();
	}


	

}
