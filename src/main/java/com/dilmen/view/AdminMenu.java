package com.dilmen.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.dilmen.controller.AdminController;

public class AdminMenu {
	AdminController adminControler = new AdminController();
	public void menu(Scanner scanner) {
		boolean flag = true;
		while (flag) {
			memuPrompt();
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
				adminControler.addCategory(scanner);
				break;
			case 2:
				adminControler.addProduct(scanner);
				break;
			case 3:
				adminControler.deleteProduct(scanner);
				break;
			case 4:
				adminControler.listCustomers();
				break;
			case 0:
				flag = false;
			default:
				break;
			}
		}
		
	}

	private void memuPrompt() {
		System.out.println("\t(1) .... Add Category");
		System.out.println("\t(2) .... Add Product");
		System.out.println("\t(3) .... Delete Product");
		System.out.println("\t(4) .... List Customers");
		System.out.println("\t(0) .... Bir üst menüye dön");
		System.out.print("\n\tLütfen seçiminizi yapınız: ");
		
	}

}
