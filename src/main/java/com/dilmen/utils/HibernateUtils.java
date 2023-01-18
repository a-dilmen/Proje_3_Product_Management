package com.dilmen.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.dilmen.entity.Admin;
import com.dilmen.entity.Category;
import com.dilmen.entity.Customer;
import com.dilmen.entity.Product;
import com.dilmen.entity.ProductEvaluate;
import com.dilmen.entity.User;



public class HibernateUtils {
	private static final SessionFactory SESSION_FACTORY = sessionFactoryHibernate();

	private static SessionFactory sessionFactoryHibernate() {

		try {
			Configuration configuration = new Configuration();

			configuration.addAnnotatedClass(User.class);
			configuration.addAnnotatedClass(Customer.class);
			configuration.addAnnotatedClass(Admin.class);
			configuration.addAnnotatedClass(Product.class);
			configuration.addAnnotatedClass(Category.class);
			configuration.addAnnotatedClass(ProductEvaluate.class);

			SessionFactory factory = configuration.configure("hibernate.cfg2.xml").buildSessionFactory();

			return factory;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static SessionFactory getSessionFactory() {
		return SESSION_FACTORY;
	}
}
