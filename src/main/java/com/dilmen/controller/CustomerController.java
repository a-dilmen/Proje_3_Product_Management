package com.dilmen.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.dilmen.entity.Customer;
import com.dilmen.entity.Product;
import com.dilmen.entity.ProductEvaluate;
import com.dilmen.service.CategoryService;
import com.dilmen.service.CustomerService;
import com.dilmen.service.ProductEvaluateService;
import com.dilmen.service.ProductService;

public class CustomerController {
	CategoryService categoryService = new CategoryService();
	ProductService productService = new ProductService();
	ProductEvaluateService productEvaluateService = new ProductEvaluateService();
	CustomerService customerService = new CustomerService();

	public Customer register(Scanner scanner) {
		System.out.print("\tİsminiz: ");
		String name = scanner.nextLine();
		System.out.print("\tSoy İsminiz: ");
		String surname = scanner.nextLine();
		System.out.print("\tEmailiniz: ");
		String email = scanner.nextLine();
		System.out.print("\tTC Kimlik Numaranız: ");
		String identity = scanner.nextLine();
		System.out.print("\tŞifreniz: ");
		String pass = scanner.nextLine();
		Customer customer = new Customer(name, surname, email, pass, identity);
		if (customerService.findByIdentityNumber(identity) == null) {
			customerService.create(customer);
			System.out.println("kayıt başarılı");
			return customer;
		}
		System.err.println("Aynı TC no ile daha önce kayıt yapılmış");
		return null;
	}

	public Customer login(Scanner scanner) {
		System.out.print("\tEmailiniz: ");
		String email = scanner.nextLine();
		System.out.print("\tŞifreniz: ");
		String pass = scanner.nextLine();
		Customer customer = customerService.findByEmail(email);
		if (customer == null) {
			System.err.println("Hatalı email adresi -> tekrar deneyin");
		} else {
			if (customer.getEmail().equals(pass)) {
				return customer;
			} else {
				System.err.println("Hatalı şifre -> tekrar deneyin");
			}
		}
		return null;
	}

	public void buyProduct(Scanner scanner, Customer customer) {
		System.out.print("\tSatın almak istediğiniz ürünün id si : ");
		int id = scanner.nextInt();
		scanner.nextLine();
		Product product = productService.find(id);
		if (product == null) {
			System.err.println("Bu id ile bir ürün bulunamdı");
			return;
		} else {
			System.out.print("\tkaç adet almak istiyorsunuz : ");
			int count = scanner.nextInt();
			scanner.nextLine();
			if (product.getStock() < count) {
				System.err.println("Mevcut stock " + product.getStock() + " tekrar deneyiniz");
			} else {
				if (customer.getProducts() == null) {
					customer.setProducts(Arrays.asList());
				} else {
					customer.getProducts().add(product);
				}
				product.setStock(product.getStock() - count);
				customerService.update(customer.getId(), customer);
				productService.update(product.getId(), product);
			}
		}

	}

	public void evaluateProduct(Scanner scanner, Customer customer) {
		System.out.print("\tDeğerlendirmek istediğiniz ürünün id si : ");
		int id = scanner.nextInt();
		scanner.nextLine();
		Product product = productService.find(id);
		if (product == null) {
			System.err.println("Bu id ile bir ürün bulunamdı");
			return;
		} else {
			System.out.print("\tYorumunuz : ");
			String comment = scanner.nextLine();
			System.out.print("\tPuanınız (1-5) : ");
			Double points = scanner.nextDouble();
			ProductEvaluate evaluate = new ProductEvaluate(comment, points, customer, product);
			productEvaluateService.create(evaluate);
		}

	}

	public void listLowOnStock() {
		List<Product> products = productService.listLowOnStock();
		products.forEach(System.out::println);
		System.out.println();
	}

	public void listAll() {
		List<Product> products = productService.findAll();
		products.forEach(System.out::println);
		System.out.println();
	}

	public void getEvaluation(Scanner scanner, Customer customer) {
		System.out.print("\tGörmek istediğiniz ürünün id si : ");
		int id = scanner.nextInt();
		scanner.nextLine();
		Product product = productService.find(id);
		if (product == null) {
			System.err.println("Bu id ile bir ürün bulunamdı");
			return;
		} else {
			List<ProductEvaluate> evaluates = productEvaluateService.findAll().stream()
					.filter(pE -> pE.getProduct().getId() == id).toList();
			if (evaluates == null) {
				System.out.println("Bu ürün ile ilgili yorum yok");
			} else {
				evaluates.stream().forEach(e -> System.out.println(e.getPoints() + " --> " + e.getComment()));
			}

		}

	}

}
