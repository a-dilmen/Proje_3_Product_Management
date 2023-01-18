package com.dilmen.service;

import java.util.List;

import com.dilmen.dao.AdminDao;
import com.dilmen.entity.Admin;

public class AdminService implements IService<Admin> {

	AdminDao adminDao;

	public AdminService() {
		super();
		adminDao = new AdminDao();
	}

	@Override
	public void create(Admin entity) {
		adminDao.create(entity);
	}

	@Override
	public Admin find(long id) {
		return adminDao.find(id);
	}

	@Override
	public List<Admin> findAll() {
		return adminDao.findAll();
	}

	@Override
	public void update(long id, Admin obj) {
		adminDao.update(id, obj);
	}

	@Override
	public void delete(long id) {
		adminDao.delete(id);
	}
}
