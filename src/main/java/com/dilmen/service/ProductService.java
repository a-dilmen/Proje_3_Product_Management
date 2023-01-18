package com.dilmen.service;

import java.util.List;

import com.dilmen.dao.ProductDao;
import com.dilmen.entity.Product;

public class ProductService implements IService<Product> {

	ProductDao productDao;

	public ProductService() {
		super();
		productDao = new ProductDao();
	}

	@Override
	public void create(Product entity) {
		productDao.create(entity);
	}

	@Override
	public Product find(long id) {
		return productDao.find(id);
	}

	@Override
	public List<Product> findAll() {
		return productDao.findAll();
	}

	@Override
	public void update(long id, Product obj) {
		productDao.update(id, obj);
	}

	@Override
	public void delete(long id) {
		productDao.delete(id);
	}

	public List<Product> listLowOnStock() {
		return productDao.listLowOnStock() ;
	}
}
