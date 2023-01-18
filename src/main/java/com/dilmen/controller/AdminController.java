package com.dilmen.controller;

import java.util.Arrays;
import java.util.Scanner;

import com.dilmen.entity.Category;
import com.dilmen.entity.Customer;
import com.dilmen.entity.Product;
import com.dilmen.service.CategoryService;
import com.dilmen.service.CustomerService;
import com.dilmen.service.ProductService;

import java.util.List;

public class AdminController {
	CategoryService categoryService = new CategoryService();
	ProductService productService = new ProductService();
	CustomerService customerService = new CustomerService();
	
	public void addCategory(Scanner scanner) {
		System.out.print("\tLütfen eklemek istediğiniz kategori adını giriniz: ");
		String categoryName = scanner.nextLine();
		long a = categoryService.findAll().stream()
				.filter(category -> category.getName().equalsIgnoreCase(categoryName)).count();

		if (a == 0) {
			categoryService.create(new Category(categoryName));
		} else {
			System.err.println("Kategori zaten mevcut");
		}
	}

	public void addProduct(Scanner scanner) {
		System.out.print("\tLütfen eklemek istediğiniz ürün adını giriniz: ");
		String productName = scanner.nextLine();
		System.out.print("\tLütfen eklemek istediğiniz ürün fiyatını giriniz: ");
		double price = scanner.nextDouble();
		System.out.print("\tLütfen eklemek istediğiniz ürün stok adetini giriniz: ");
		int stock = scanner.nextInt();
		System.out.print("\tLütfen eklemek istediğiniz ürün categori idsini giriniz: ");
		int categoryId = scanner.nextInt();
		scanner.nextLine();
		Category category = categoryService.find(categoryId);
		if (category != null) {
			System.out.println("Category name: " + category.getName());
			Product product = new Product(productName, price, stock, Arrays.asList(category));
			productService.create(product);
		} else {
			System.err.println("Category bulunamadı");
			List<Category> cates = categoryService.findAll();
			System.out.println("\t***");
			cates.stream().forEach(cat -> System.out.println("\t" +cat.getId() +" --> "+cat.getName()));
			System.out.println("\t***");
		}

	}

	public void deleteProduct(Scanner scanner) {
		System.out.println("id ile silmek için 1");
		System.out.println("Ürünler isim ve id listesi görmek için 2");
		System.out.println("Bir üst menü için 0");
		int key = scanner.nextInt();
		if (key == 1) {
			System.out.print("\tLütfen silmek istediğiniz ürün id sini giriniz: ");
			int productId = scanner.nextInt();
			Product product = productService.find(productId);
			if (product!= null) {
				productService.delete(productId);
			} else {
				System.err.println("ürün bulanamadığı için silinemedi");
			}
		} else if (key == 2) {
			List<Product> products = productService.findAll();
			products.forEach(System.out::println);
		} else if(key == 0) {
			return;
		} else {
			System.err.println("Hatalı giriş yaptınız tekrar deneyiniz");
			return;
		}
		
	}

	public void listCustomers() {
		List<Customer> customers = customerService.findAll();
		customers.forEach(System.out::println);
	}

}
