package manager;
import records.Patient;
import service.Service;

import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;


public class main_Menu {

	private static int transaction;
	private static String variable;
	private static String dump;

	public static void main(String[] args) {
		// Constructor
		File f1 = new File("Patients.txt");
		File f2 = new File("Services.txt");
		ArrayList<Patient> patients = new ArrayList<Patient>();
		ArrayList<Service> services = new ArrayList<Service>();

		main_Menu menu = new main_Menu();

		if(f1.exists())
			menu.readPatients(patients);
		if(f2.exists())
			menu.readServices(services);

		//menu.printPatients(patients);
		records.ManagePatientRecords record = new records.ManagePatientRecords();
		service.ManageServices service = new service.ManageServices();

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
							record.AddNewPatient(patients);
							break;
						case "2":
							// Edit
							record.ProcessPatientRecord(patients, 2);
							break;
						case "3":
							// Delete
							record.ProcessPatientRecord(patients, 3);
							break;
						case "4":
							//Search
							//try {
								record.ProcessPatientRecord(patients, 4);
							//} catch (FileNotFoundException e) {
							//	e.printStackTrace();
							//}
							break;
						case "X":
							main_Menu.main(null);
							break;
					}
					break;
				case 2:
				menu.displayManageServices();
				variable = sc.nextLine();
				switch (variable) {
					case "1":
						// Add
						service.AddNewService(services);

						break;
					case "2":
						service.ProcessService(services, 2);
					
					case "3":
						// Delete
					
						break;
					case "4":
						// Edit
					
						break;
					case "X":
						main_Menu.main(null);
						break;
				}
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

	public void displayManageServices() {
		System.out.println("Manage Patient Records");
		System.out.println("[1] Add New Service");
		System.out.println("[2] Search Service");
		System.out.println("[3] Delete Service");
		System.out.println("[4] Search Edit Service");
		System.out.println("[X] Return to Main Menu\n");
		System.out.print("Select a transaction: ");
	}

	/*puts all patient data from patients.txt excluding the deleted patients
	 * 
	 */
	public void readPatients(ArrayList<Patient> patients){
		String filepath = "Patients.txt";

		try {
			Scanner x = new Scanner(new File(filepath));
			//x.useDelimiter(";");
			System.out.println(x);

			while (x.hasNext()) {
				String fullString = x.nextLine();
				
				String[] splitString = fullString.split(";");
					
					//System.out.println("deleted");	//<===
				if(splitString.length <= 9 ){
					//System.out.println(fullString);	//<===
					patients.add(new Patient(fullString, splitString[0], splitString[1], splitString[2], splitString[3], Long.parseLong(splitString[4]), splitString[5].charAt(0), splitString[6], splitString[7], splitString[8]));
				}else{
					//System.out.println("reason = );	//<===
					patients.add(new Patient(fullString, splitString[0], splitString[1], splitString[2], splitString[3], Long.parseLong(splitString[4]), splitString[5].charAt(0), splitString[6], splitString[7], splitString[8], splitString[10]));
					
				}
					
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//for testing
	public void printPatients(ArrayList<Patient> patients){
		System.out.println("Patients:");
		for(int i=0; i<patients.size();i++){
			System.out.println(patients.get(i).getFullString());
		}
	}

	public void readServices(ArrayList<Service> services){
		String filepath = "Services.txt";

		try {
			Scanner x = new Scanner(new File(filepath));
			//x.useDelimiter(";");
			System.out.println(x);

			while (x.hasNext()) {
				String fullString = x.nextLine();
				
				String[] splitString = fullString.split(";");
					
				
				if(splitString.length > 3 ){
					//System.out.println(fullString);	//<===
					services.add(new Service(fullString, splitString[0], splitString[1], Integer.parseInt(splitString[2]), splitString[3]));
				}else{
					//System.out.println("reason = );	//<===
					services.add(new Service(fullString, splitString[0], splitString[1], Integer.parseInt(splitString[2])));
					
				}
					
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
