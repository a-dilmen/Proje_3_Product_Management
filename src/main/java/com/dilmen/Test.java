package com.dilmen;

import java.util.Arrays;

import com.dilmen.dao.AdminDao;
import com.dilmen.dao.CategoryDao;
import com.dilmen.dao.CustomerDao;
import com.dilmen.dao.ProductDao;
import com.dilmen.dao.ProductEvaluateDao;
import com.dilmen.entity.Admin;
import com.dilmen.entity.Category;
import com.dilmen.entity.Customer;
import com.dilmen.entity.Product;
import com.dilmen.entity.ProductEvaluate;
import com.dilmen.utils.HibernateUtils;

public class Test {
	public static void main(String[] args) {
		
		HibernateUtils.getSessionFactory().openSession();
		
		Customer customer = new Customer("Ali", "Kaya", "ali@asd.com", "123", "12312312322");
		Customer customer2 = new Customer("Su", "Zan", "su@asd.co", "123", "12312312321");
//		Customer customer3 = new Customer("Veli", "Tan", "@@@@@.com", "128", "12312312321");
		
		CustomerDao customerDao = new  CustomerDao();
		
		customerDao.create(customer);
		customerDao.create(customer2);
		
//		customerDao.update(2, customer3);
//		System.out.println("\n******\n");
//		customerDao.findAll() .stream().forEach(System.out::println);
//		System.out.println( "\n******\n");
//		customerDao.delete(1);
//		Customer c = customerDao.find(2);
//		c.getProducts().stream().forEach(System.err::println);
		
		
		Admin admin = new Admin("Veli", "Can", "veli@asd.com");
		
		AdminDao adminDao = new AdminDao();
		
		adminDao.create(admin);
		
		
		Category category = new Category("Electronics");
		
		CategoryDao categoryDao = new CategoryDao();
		
		categoryDao.create(category);
		
		
		Product product = new Product( "asus Tablet", 4999.90, 11, Arrays.asList(category));
		Product product2 = new Product( "Samsung Tablet", 5999.90, 13, Arrays.asList(category));
		
		ProductDao productDao = new ProductDao();
		
		productDao.create(product);
		productDao.create(product2);
		
		if (customer.getProducts() == null) {
			customer.setProducts(Arrays.asList(product,product2));
		} else {
			customer.getProducts().add(product2);
			customer.getProducts().add(product);
		}
		customerDao.update(customer.getId(), customer);
		
//		customer.getProducts().add(product2);
//		customer2.getProducts().add(product);
		
		ProductEvaluate productEvaluate = new ProductEvaluate("Harika bir ürün herkese tavsiye edirim", 5, customer2, product2);
		ProductEvaluate productEvaluate2 = new ProductEvaluate("Harika bir ürün herkese tavsiye edirim", 5, customer2, product2);
		ProductEvaluateDao productEvaluateDao = new ProductEvaluateDao();
		productEvaluateDao.create(productEvaluate);
		productEvaluateDao.create(productEvaluate2);
		
	}
}
