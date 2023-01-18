package com.dilmen.service;

import java.util.List;

import com.dilmen.dao.CategoryDao;
import com.dilmen.entity.Category;

public class CategoryService implements IService<Category> {

	CategoryDao categoryDao;

	public CategoryService() {
		super();
		categoryDao = new CategoryDao();
	}

	@Override
	public void create(Category entity) {
		categoryDao.create(entity);
	}

	@Override
	public Category find(long id) {
		return categoryDao.find(id);
	}

	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public void update(long id, Category obj) {
		categoryDao.update(id, obj);
	}

	@Override
	public void delete(long id) {
		categoryDao.delete(id);
	}
}
