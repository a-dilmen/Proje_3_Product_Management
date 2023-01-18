package com.dilmen.dao;

import java.util.List;

import org.hibernate.Session;

import com.dilmen.utils.HibernateUtils;

public interface IDao<T> {

	void create(T obj);

	T find(long id);

	List<T> findAll();

	void update(long id, T obj);

	void delete(long id);

	default Session dataBaseConnectionHibernate() {
		return HibernateUtils.getSessionFactory().openSession();
	}

}
