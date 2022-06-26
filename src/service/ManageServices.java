package service;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import manager.main_Menu;

//TODO: Verify service code

public class ManageServices {
	// Properties
	private Scanner sc = new Scanner(System.in);
	private String servCode;
	private String description;
	private String price;
	private String transaction;
	private AddNewService add = new AddNewService();
	private SearchService search = new SearchService();
	private DeleteService delete = new DeleteService();
	private EditService edit = new EditService();

	// Constructor
	public void AddNewService(ArrayList<Service> services) {
		File f1 = new File("services.txt");

		System.out.println("Add Service");
		System.out.println("");
		System.out.println("Service Code: ");
		servCode = sc.nextLine();
		while (add.isServCodeUnique(services, servCode) == false && f1.exists()) {
			System.out.println("Service code already exists or has been previously deleted! Enter a new one.");
			System.out.println("Service code: ");
			servCode = sc.nextLine();
		}

		System.out.println("Description: ");
		description = sc.nextLine();
		System.out.println("Price: ");
		price = sc.nextLine();

		System.out.println("Save Service[Y/N]? ");
		transaction = sc.nextLine();

		do {
			if (transaction.equals("Y") || transaction.equals("y")) {
				// Save service

				String fullString = "" + servCode + ";" + description + ";" + price + ";";
				services.add(new Service(fullString, servCode, description, Long.parseLong(price)));

				System.out.println("services length = " + services.size()); // <===

				System.out.println("fullstring = " + services.get(services.size() - 1).getFullString()); // <====
				add.SaveRecord(services.get(services.size() - 1));

				main_Menu.main(null);
			} else if (transaction.equals("N") || transaction.equals("n")) {
				System.out.println("Patient Record was not saved.");
				main_Menu.main(null);
			}
		} while (!(transaction.equals("Y") || transaction.equals("y") || transaction.equals("N")
				|| transaction.equals("n")));
	}

	// Methods
	public void ProcessService(ArrayList<Service> services, int choice) {
		/*
		 * System.out.println("Input Unique Identifier: ");
		 * input = sc.nextLine();
		 * delete.DeleteRecord();
		 */
		String UI;

		System.out.println("Search Patient Record");
		System.out.println("");

		System.out.println("Select Prefered Input:");
		System.out.println("[1] Service Code");
		System.out.println("[2] Description Keyword");
		System.out.println("[X] Return");
		String transaction = sc.nextLine();

		switch (transaction) {
			case "1":
				System.out.println("Input service code: ");
				UI = sc.nextLine();
				switch (choice) {
					case 2: // edit
						search.SearchService(Integer.parseInt(transaction), UI, services);
						break;
					case 3: // delete
						delete.SearchService(Integer.parseInt(transaction), UI, services);
						break;
				}
				break;
			case "2":
				// If Last Name, First Name & Birthday
				System.out.println("Enter description keyword: ");
				String descKey = sc.nextLine();

				switch (choice) {
					case 2: // search
						search.SearchService(Integer.parseInt(transaction), descKey, services);
						break;
					case 3: // delete
						delete.SearchService(Integer.parseInt(transaction), descKey, services);
						break;
					/*
					 * case 4: //search
					 * search.SearchRecord(Integer.parseInt(transaction), combine, this.list,
					 * patients);
					 * break;
					 */

				}
				break;

			case "X":
				main_Menu.main(null);
				break;
		}
	}

	public void EditService(ArrayList<Service> services) {
		Scanner sc = new Scanner(System.in);
		String transaction;
		int i = 0, resIndex = -1;
		boolean found = false;

		System.out.println("Edit Service");
		System.out.println(
				"\nThe services cannot be edited. If you would like to edit an existing service,");
		System.out.println(
				"the service will first be deleted, and new service will be created. Would you like to proceed? [Y/N]");
		System.out.print("Enter choice: ");
		String decision = sc.nextLine();

		if (decision.equals("y") || decision.equals("Y")) {
			System.out.println("Select Prefered Input:");
			System.out.println("[1] Service Code");
			System.out.println("[2] Description Keyword");
			System.out.println("[X] Return");
			transaction = sc.nextLine();

			switch (transaction) {
				case "1":
					System.out.println("Input service code: ");
					String servCode = sc.nextLine();
					edit.SearchService(1, servCode, services);
					break;
				case "2":
					System.out.println("Enter description keyword: ");
					String descKey = sc.nextLine();
					edit.SearchService(2, descKey, services);
					break;

				case "X":
					main_Menu.main(null);
					break;
			}
		} else {
			System.out.println("");
			System.out.println("Back to Main Menu...");
			main_Menu.main(null);
		}
	}

}
