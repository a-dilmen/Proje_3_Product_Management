package com.dilmen.service;

import java.util.List;

import com.dilmen.dao.CustomerDao;
import com.dilmen.entity.Customer;

public class CustomerService implements IService<Customer> {

	CustomerDao customerDao;

	public CustomerService() {
		super();
		customerDao = new CustomerDao();
	}

	@Override
	public void create(Customer entity) {
		customerDao.create(entity);
	}

	@Override
	public Customer find(long id) {
		return customerDao.find(id);
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	@Override
	public void update(long id, Customer obj) {
		customerDao.update(id, obj);
	}

	@Override
	public void delete(long id) {
		customerDao.delete(id);
	}

	public Customer findByIdentityNumber(String identity) {
		return customerDao.findByIdentityNumber(identity);
	}

	public Customer findByEmail(String email) {
		return customerDao.findByEmail(email);		
	}
}
