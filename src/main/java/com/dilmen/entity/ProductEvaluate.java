package com.dilmen.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ProductEvaluate {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	private String comment;
	
	private double points;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;

	public ProductEvaluate() {
		super();
	}

	public ProductEvaluate(double points, Customer customer, Product product) {
		super();
		this.points = points;
		this.customer = customer;
		this.product = product;
	}

	public ProductEvaluate(String comment, Customer customer, Product product) {
		super();
		this.comment = comment;
		this.customer = customer;
		this.product = product;
	}

	public ProductEvaluate(String comment, double points, Customer customer, Product product) {
		super();
		this.comment = comment;
		this.points = points;
		this.customer = customer;
		this.product = product;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
	
	
}
