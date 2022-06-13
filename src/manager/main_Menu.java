package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main_Menu {

	private static int transaction;
	private static String variable;
	private static String dump;

	public static void main(String[] args) {
		// Constructor
		main_Menu menu = new main_Menu();
		records.ManagePatientRecords record = new records.ManagePatientRecords();

		try (Scanner sc = new Scanner(System.in)) {
			menu.displayMainMenu();
			transaction = sc.nextInt();
			dump = sc.nextLine();

			switch (transaction) {
				case 1:
					menu.displayManagePatientRecords();
					variable = sc.nextLine();
					switch (variable) {
						case "1":
							// Add
							record.AddNewPatient();
							break;
						case "2":
							// Edit
							record.EditPatient();
							break;
						case "3":
							// Delete
							record.DeletePatientRecord();
							break;
						case "4":
							try {
								record.SearchPatientRecord();
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
							break;
						case "X":
							main_Menu.main(null);
							break;
					}
					break;
				case 2:
					System.out.println("Manage Services");
					break;
				case 3:
					System.out.println("Manage Laboratory Results");
					break;
				default:
					System.out.println("\nInvalid input\n");
					break;
			}
		}
	}

	/**
	 * Menu - menu for displaying options.
	 */
	public void displayMainMenu() {
		System.out.println("Medical Laboratory Information System");
		System.out.println("[1] Manage Patient Records");
		System.out.println("[2] Manage Services");
		System.out.println("[3] Manage Laboratory Results");
		System.out.println("[4] Exit\n");
		System.out.print("Select a transaction: ");
	}

	public void displayManagePatientRecords() {
		System.out.println("Manage Patient Records");
		System.out.println("[1] Add New Patient");
		System.out.println("[2] Edit Patient Record");
		System.out.println("[3] Delete Patient Record");
		System.out.println("[4] Search Patient Record");
		System.out.println("[X] Return to Main Menu\n");
		System.out.print("Select a transaction: ");
	}
}
