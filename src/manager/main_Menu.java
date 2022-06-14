package manager;
import records.Patient;

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
		
		ArrayList<Patient> patients = new ArrayList<Patient>();

		main_Menu menu = new main_Menu();

		menu.readPatients(patients);
		menu.printPatients(patients);
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
							record.AddNewPatient(patients);
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
								record.SearchPatientRecord(patients);
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

	public void readPatients(ArrayList<Patient> patients){
		String filepath = "Patients.txt";
		boolean found;
		String UID, LastName, FirstName, MiddleName,
				Birthday, Gender, Address, Phone, NI;
		
		try {
			Scanner x = new Scanner(new File(filepath));
			x.useDelimiter(";");
			System.out.println(x);

			while (x.hasNext()) {
				UID = x.next();
				LastName = x.next();
				FirstName = x.next();
				MiddleName = x.next();
				Birthday = x.next();
				Gender = x.next();
				Address = x.next();
				Phone = x.next();
				NI = x.next();
				x.nextLine();
				//System.out.println(UID + " " + LastName + " " + Birthday + " " + Gender + " " + Address + " " + Phone + " " + NI);	//<===
				String fullString = "" + UID + ";" + LastName + ";" + FirstName + ";" + MiddleName + ";" + Birthday + ";" + Gender + ";" + Address  + ";" + Phone + ";" + NI;
				patients.add(new Patient(fullString, UID, LastName, FirstName, MiddleName, Long.parseLong(Birthday), Gender.charAt(0), Address, Phone, NI));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//for testing
	public void printPatients(ArrayList<Patient> patients){
		System.out.println("Patients:");
		for(int i=0; i<patients.size();i++){
			System.out.println(patients.get(i).getUID());
		}
	}
}
