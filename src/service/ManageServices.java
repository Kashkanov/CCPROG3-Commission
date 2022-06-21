package service;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import manager.main_Menu;

	//TODO: Verify service code

public class ManageServices {
	//Properties
	private Scanner sc = new Scanner(System.in);
	private String servCode;
	private String description;
	private String price;
	private String transaction;
	private AddNewService add = new AddNewService();

	private manager.main_Menu menu = new manager.main_Menu();
	
	//Constructor
	public void AddNewService(ArrayList<Service> services) {
		File f1 = new File("services.txt");
		
		System.out.println("Add Service");
		System.out.println("");
		System.out.println("Service Code: ");
		servCode = sc.nextLine();
		System.out.println("Description: ");
		description = sc.nextLine();
		System.out.println("Price: ");
		price = sc.nextLine();

		System.out.println("Save Service[Y/N]? ");
		transaction = sc.nextLine();
		
		do {
			if (transaction.equals("Y") || transaction.equals("y")) {
				// Save service
			
				String fullString = "" + servCode + ";" + description + ";" + price + ";" ;
				services.add(new Service(fullString, servCode, description, Long.parseLong(price)));
				
				System.out.println("services length = " + services.size());	//<===
				
				System.out.println("fullstring = " + services.get(services.size()-1).getFullString());	//<====
				add.SaveRecord(services.get(services.size()-1));
				
				main_Menu.main(null);
			} else if (transaction.equals("N") || transaction.equals("n")) {
				System.out.println("Patient Record was not saved.");
				main_Menu.main(null);
			}
		} while (!(transaction.equals("Y") || transaction.equals("y") || transaction.equals("N")
				|| transaction.equals("n")));
	}
		

	
	
	
	//Methods
	public void SearchService() {
		
	}
	
	public void DeleteService() {
		
	}
	
	public void EditService() {
		
	}
	
}
