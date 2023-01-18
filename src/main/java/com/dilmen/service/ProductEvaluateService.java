package com.dilmen.service;

import java.util.List;

import com.dilmen.dao.ProductEvaluateDao;
import com.dilmen.entity.ProductEvaluate;

public class ProductEvaluateService implements IService<ProductEvaluate> {

	ProductEvaluateDao productEvaluateDao;

	public ProductEvaluateService() {
		super();
		productEvaluateDao = new ProductEvaluateDao();
	}

	@Override
	public void create(ProductEvaluate entity) {
		productEvaluateDao.create(entity);
	}

	@Override
	public ProductEvaluate find(long id) {
		return productEvaluateDao.find(id);
	}

	@Override
	public List<ProductEvaluate> findAll() {
		return productEvaluateDao.findAll();
	}

	@Override
	public void update(long id, ProductEvaluate obj) {
		productEvaluateDao.update(id, obj);
	}

	@Override
	public void delete(long id) {
		productEvaluateDao.delete(id);
	}
}
