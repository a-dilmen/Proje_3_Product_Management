package com.dilmen.view;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

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

public class Main {

	static AdminMenu adminMenu = new AdminMenu();
	static CustomerMenu customerMenu = new CustomerMenu();
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		fillDummies();
		boolean flag = true;
		while (flag) {
			memuPrompt();
			int key = Integer.MAX_VALUE;
			try {
				key = scanner.nextInt();
				scanner.nextLine();

				switch (key) {
				case 1:
					adminMenu.menu(scanner);
					break;
				case 2:
					customerMenu.loginMenu(scanner);
					break;
				case 0:
					flag = false;
				default:
					break;
				}
			} catch (InputMismatchException e) {
				flag = false;
				System.err.println("Programı tekrar başlatınız ve lütfen bir sayı giriniz");
			}
		}
		scanner.close();
	}

	static void memuPrompt() {
		System.out.println("\t (1) .... Admin");
		System.out.println("\t (2) .... Customer");
		System.out.println("\t (0) .... Exit");
		System.out.print("\n\tLütfen Seçiminizi yapınız: ");
	}

	static void fillDummies() {
		CustomerDao customerDao = new CustomerDao();
		AdminDao adminDao = new AdminDao();
		CategoryDao categoryDao = new CategoryDao();
		ProductDao productDao = new ProductDao();
		ProductEvaluateDao productEvaluateDao = new ProductEvaluateDao();

		Customer customer = new Customer("Ali", "Kaya", "ali@asd.com", "123", "12312312322");
		Customer customer2 = new Customer("Su", "Zan", "su@asd.co", "123", "12312312321");
		Customer customer3 = new Customer("1", "1", "1", "1", "1");
		customerDao.create(customer);
		customerDao.create(customer2);
		customerDao.create(customer3);
		Admin admin = new Admin("Veli", "Can", "veli@asd.com");
		adminDao.create(admin);

		Category category = new Category("Electronics");
		Category category1 = new Category("White Goods");
		categoryDao.create(category);
		categoryDao.create(category1);

		Product product = new Product("asus Tablet", 4999.90, 11, Arrays.asList(category));
		Product product2 = new Product("Samsung Tablet", 5999.90, 9, Arrays.asList(category));
		productDao.create(product);
		productDao.create(product2);
		if (customer.getProducts() == null) {
			customer.setProducts(Arrays.asList(product, product2));
		} else {
			customer.getProducts().add(product2);
			customer.getProducts().add(product);
		}
		customerDao.update(customer.getId(), customer);

		ProductEvaluate productEvaluate = new ProductEvaluate("Harika bir ürün herkese tavsiye edirim", 5, customer,
				product2);
		ProductEvaluate productEvaluate2 = new ProductEvaluate("Okey bir ürün fp fena değil", 4, customer2, product);
		productEvaluateDao.create(productEvaluate);
		productEvaluateDao.create(productEvaluate2);
	}
}
