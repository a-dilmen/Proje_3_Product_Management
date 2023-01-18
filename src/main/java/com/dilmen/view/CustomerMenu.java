package com.dilmen.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.dilmen.controller.CustomerController;
import com.dilmen.entity.Customer;

public class CustomerMenu {
	CustomerController customerController = new CustomerController();
	Customer customer;
	
	public void loginMenu(Scanner scanner) {
		boolean flag = true;
		while (flag) {
			memuPromptLogin();
			int key = Integer.MAX_VALUE;
			try {
			key = scanner.nextInt();
			scanner.nextLine();
			} catch (InputMismatchException e) {
				System.err.println("lütfen bir sayı giriniz");
			}
			switch (key) {
			case 1:
				customer = customerController.register(scanner);
				if (customer == null) {
					System.err.println("\tKayıt Başarısız");
				} else {
					customerMenu(scanner);
				}
				break;
			case 2:
				customer = customerController.login(scanner);
				if (customer == null) {
					System.err.println("\tGiriş Başarısız");
				} else {
					customerMenu(scanner);
				}
				break;
			case 0:
				flag = false;
			default:
				break;
			}
		}
	}

	private void customerMenu(Scanner scanner) {
		boolean flag = true;
		while (flag) {
			memuPromptCustomer();
			int key = Integer.MAX_VALUE;
			try {
			key = scanner.nextInt();
			scanner.nextLine();
			} catch (InputMismatchException e) {
				//e.printStackTrace();
				System.err.println("lütfen bir sayı giriniz");
			}
			switch (key) {
			case 1:
				customerController.buyProduct(scanner,customer);
				break;
			case 2:
				customerController.evaluateProduct(scanner,customer);
				break;
			case 3:
				customerController.listLowOnStock();
				break;
			case 4:
				customerController.listAll();
				break;
			case 5:
				customerController.getEvaluation(scanner,customer);
				break;
			case 0:
				flag = false;
			default:
				break;
			}
		}
	}

	private void memuPromptLogin() {
		System.out.println("\t(1) .... Kayıt Ol");
		System.out.println("\t(2) .... Giriş yap");
		System.out.println("\t(0) .... Bir üst menüye dön");
		System.out.print("\n\tLütfen seçiminizi yapınız: ");
	}
	private void memuPromptCustomer() {
		System.out.println("\t(1) .... Satın Al");
		System.out.println("\t(2) .... Yorum ve puan ver");
		System.out.println("\t(3) .... Tükenmek üzere olan ürünleri getir");
		System.out.println("\t(4) .... Tüm ürünleri getir");
		System.out.println("\t(5) .... Ürüne göre Yorumları getir");
		System.out.println("\t(0) .... Çıkış yap");
		System.out.print("\n\tLütfen seçiminizi yapınız: ");
	}
}
