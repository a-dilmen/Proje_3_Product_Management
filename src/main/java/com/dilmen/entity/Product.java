package com.dilmen.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private double price;
	
	private int stock;
	
	@ManyToMany
	private List<Category> categories;

	public Product() {
	}

	public Product(long id, String name, double price, int stock, List<Category> categories) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.categories = categories;
	}

	public Product(String name, double price, int stock, List<Category> categories) {
		super();
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.categories = categories;
	}

	
	
	@Override
	public String toString() {
		return "Product id : " + id + ", name : " + name + ", price : " + price + ", stock = " + stock;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	} 
		
}
